package com.mlj.ecbiz.controller.common;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.ferrari.exception.ActionException;
import org.ferrari.exception.DaoException;
import org.ferrari.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.service.permission.SysOperationService;
import com.mlj.ecbiz.service.permission.SysPermissionService;
import com.mlj.ecbiz.service.permission.SysResourceService;
import com.mlj.ecbiz.service.permission.SysRolePermissionService;
import com.mlj.ecbiz.service.permission.SysRoleService;
import com.mlj.ecbiz.service.permission.SysUserService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResourceService sysResourceService;
	
	@Autowired
	private SysOperationService sysOperationService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@RequestMapping("/loadMenuTree")
	public ModelAndView loadMenuTree(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		try{
			String roleId = request.getParameter("roleIds");
			HttpSession session = request.getSession();
			SysUser sysUser=new SysUser();
			sysUser.setName("master");
			session.setAttribute("user", sysUser);
			SysUser systemUser = (SysUser)session.getAttribute("user");
			//用户所有权限
			Map<SysResource,List<SysOperation>> allResourceOperationMap = null;
			//单个角色的权限
			Map<SysResource,List<SysOperation>> singleResourceOperationMap;
			
			if(systemUser.getName().equals("master")){
				allResourceOperationMap = this.getAllPermission();
			}else{
//				List<SysRole> roleList=new ArrayList<SysRole>();
//				SysRole sysRole=new SysRole();
//				sysRole.setRId(1L);
//				roleList.add(sysRole);
//				session.setAttribute("roleList", roleList);
				List<SysRole> roleList1 =(List<SysRole>) session.getAttribute("roleList");
				allResourceOperationMap = this.getRolePermissionIsNotAdmin(roleList1);
			}
			singleResourceOperationMap = this.getPermissionByRoleId(roleId);			
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/xml; charset=UTF-8");
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = document.createElement("tree");
			
			if(singleResourceOperationMap==null){
				singleResourceOperationMap = new HashMap<SysResource,List<SysOperation>>();
			}
			root = recursiveRolePermission(allResourceOperationMap,singleResourceOperationMap,root,0L,document);					
			
			
			document.appendChild(root);
			DOMSource doms = new DOMSource(document);
			StreamResult sr = new StreamResult(response.getOutputStream());
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", "UTF-8");
			t.transform(doms, sr);
		}catch(Exception ex){
			throw new ActionException(ex.getMessage());
		}
		return null;
	}
	private class SystemResourceComparator implements Comparator{

		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			SysResource systemResource1 = (SysResource)o1;
			SysResource systemResource2 = (SysResource)o2;
			if(systemResource1.getId()>systemResource2.getId()){
				return 1;
			}else if(systemResource1.getId()==systemResource2.getId()){
				return 0;
			}else{
				return -1;
			}
		}
		
	}
	
	private Element recursiveRolePermission(Map<SysResource,List<SysOperation>> allResourceOperationMap,
            Map<SysResource,List<SysOperation>> singleResourceOperationMap,
            Element element,Long parentId,Document document)throws Exception{
		
		   
			List<SysResource> resourceList = new ArrayList<SysResource>();
			resourceList.addAll(allResourceOperationMap.keySet());
			
			//排序
			Collections.sort(resourceList,new SystemResourceComparator());
			
			for(int i=0; i<resourceList.size(); i++){
			SysResource b = resourceList.get(i);
			
			if ((b!=null&&parentId==0 && b.getParentId()==0) 
			|| (b!=null&&parentId!=0 && parentId.equals(b.getParentId()))){
			
			Element elementNew = document.createElement("tree");
			elementNew.setAttribute("text", b.getName());
			elementNew.setAttribute("value", b.getId().toString());
			elementNew.setAttribute("url", b.getUrl());
			
			if(singleResourceOperationMap.keySet().contains(b)){
			elementNew.setAttribute("checked", "true");
			}
			
			if(allResourceOperationMap.get(b)!=null){
			String value = "";
			List<SysOperation> allSystemOperationList = (List<SysOperation>)allResourceOperationMap.get(b);
			List<SysOperation> singleSystemOperationList = (List<SysOperation>)singleResourceOperationMap.get(b);
			
			value = getSystemOperationValue(b.getId(),allSystemOperationList,singleSystemOperationList);
			elementNew.setAttribute("addition", value);
			}
			
			element.appendChild(elementNew);
			recursiveRolePermission(allResourceOperationMap,singleResourceOperationMap,elementNew,b.getId(),document);
			}  
			}
			return element;
}
	
	/**
	 * 
	 * @comment: <p>返回资源的操作</p>  
	 * @create.date: Aug 13, 2010 ( 2:31:50 PM )
	 * @author: yuliang
	 * @return:
	 */
	private String getSystemOperationValue(Long systemResourceId,List<SysOperation> allSystemOperationList,List<SysOperation> singleSystemOperationList){
		StringBuffer buffer = new StringBuffer();
		
		if(singleSystemOperationList==null){
			for(int i=0; i<allSystemOperationList.size();i++){
				SysOperation systemOperation = allSystemOperationList.get(i);	
				buffer.append(" | ");
				buffer.append(systemOperation.getName()).append(" ");
				buffer.append("<input type=\"checkbox\" name=\"permissionIds\" value=\""+systemResourceId+","+systemOperation.getId()+"\" ");
				buffer.append(">");
			}
		}else{
			for(int i=0; i<allSystemOperationList.size();i++){
				SysOperation systemOperation = allSystemOperationList.get(i);	
				buffer.append(" | ");
				buffer.append(systemOperation.getName()).append(" ");
				buffer.append("<input type=\"checkbox\" name=\"permissionIds\" value=\""+systemResourceId+","+systemOperation.getId()+"\" ");
				buffer.append(singleSystemOperationList.contains(systemOperation)? "checked":"").append(">");
			}
		}
		
		return buffer.toString();
	}
	public Map<SysResource, List<SysOperation>> getPermissionByRoleId(
			String roleId) throws ServiceException {
		try{
			if(roleId!=null&&!roleId.equals("")){
				List<?> permissionList = (List<?>) sysRolePermissionService.getPermissionByRoleIds(roleId);
				
				if(permissionList!=null&&permissionList.size()>0){
					Map<String,String> permissionMap = new HashMap<String,String>();
					
					for(int i=0;i<permissionList.size();i++){
						HashMap map = (HashMap)permissionList.get(i);
						if(permissionMap.containsKey(map.get("resourceId").toString())){
							String temp = permissionMap.get(map.get("resourceId").toString());
							if(map.get("operationId")!=null&&temp.indexOf(map.get("operationId").toString())==-1){
								temp = temp+","+map.get("operationId").toString();
								permissionMap.put(map.get("resourceId").toString(), temp);
							}
						}else{	
							permissionMap.put(map.get("resourceId").toString(), map.get("operationId")==null?"":map.get("operationId").toString());
						}
					}
					
					Map<SysResource, List<SysOperation>> result = new HashMap<SysResource, List<SysOperation>>();
					for(String resourceId:permissionMap.keySet()){
						SysResource systemResource =sysResourceService.getSysResourceById(Long.valueOf(resourceId));
						
						List<SysOperation> operationList = null;
						
						if(!permissionMap.get(resourceId).equals(""))
							operationList = sysOperationService.batchSelect(permissionMap.get(resourceId));
						
						result.put(systemResource, operationList);
					}
					return result;
				}		
			}
			return null;
		}catch(DaoException e){
			throw new ServiceException(e);
		}
	}
	private Map<SysResource,List<SysOperation>>  getRolePermissionIsNotAdmin(List<SysRole> roleList){
		List<SysRolePermission> rpList=(List<SysRolePermission>)sysRolePermissionService.getRolePermissionByRoleIds(roleList);
		List<?> permissionList= sysPermissionService.getAllPermissionByPids(rpList);
		if(permissionList!=null&&permissionList.size()>0){
			Map<String,String> permissionMap = new HashMap<String,String>();
			for(int i=0;i<permissionList.size();i++){
				HashMap map = (HashMap)permissionList.get(i);
				if(permissionMap.containsKey(map.get("resourceId").toString())){
					String temp = permissionMap.get(map.get("resourceId").toString());
					if(map.get("operationId")!=null&&temp.indexOf(map.get("operationId").toString())==-1){
						temp = temp+","+map.get("operationId").toString();
						permissionMap.put(map.get("resourceId").toString(), temp);
					}
				}else{	
					permissionMap.put(map.get("resourceId").toString(), map.get("operationId")==null?"":map.get("operationId").toString());
				}
			}
			Map<SysResource, List<SysOperation>> result = new HashMap<SysResource, List<SysOperation>>();
			
			for(String resourceId:permissionMap.keySet()){
				SysResource systemResource = sysResourceService.getSysResourceById(Long.parseLong(resourceId));
				List<SysOperation> operationList =null;
				if(!permissionMap.get(resourceId).equals(""))
					operationList=sysOperationService.batchSelect(permissionMap.get(resourceId));
					result.put(systemResource, operationList);
			}
			return result;
		}	
	return null;
	}
public Map<SysResource, List<SysOperation>> getAllPermission()throws ServiceException, IntrospectionException, IllegalAccessException, InvocationTargetException {
		
		try{
			List<?> permissionList = sysPermissionService.getAllPermission();
			if(permissionList!=null&&permissionList.size()>0){
				Map<String,String> permissionMap = new HashMap<String,String>();
				for(int i=0;i<permissionList.size();i++){
					HashMap map = (HashMap)permissionList.get(i);
					if(permissionMap.containsKey(map.get("resourceId").toString())){
						String temp = permissionMap.get(map.get("resourceId").toString());
						if(map.get("operationId")!=null&&temp.indexOf(map.get("operationId").toString())==-1){
							temp = temp+","+map.get("operationId").toString();
							permissionMap.put(map.get("resourceId").toString(), temp);
						}
					}else{	
						permissionMap.put(map.get("resourceId").toString(), map.get("operationId")==null?"":map.get("operationId").toString());
					}
				}
				Map<SysResource, List<SysOperation>> result = new HashMap<SysResource, List<SysOperation>>();
				
				for(String resourceId:permissionMap.keySet()){
//					if(resourceId.equals("100001")){
					SysResource systemResource = sysResourceService.getSysResourceById(Long.parseLong(resourceId));
					List<SysOperation> operationList =null;
					if(!permissionMap.get(resourceId).equals(""))
						operationList=sysOperationService.batchSelect(permissionMap.get(resourceId));
						result.put(systemResource, operationList);
//					}
				}
				return result;
			}		
			return null;
		}catch(DaoException e){
			throw new ServiceException(e);
		}
	}
}
