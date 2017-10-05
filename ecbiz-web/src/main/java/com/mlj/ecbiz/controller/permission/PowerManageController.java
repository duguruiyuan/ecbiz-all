package  com.mlj.ecbiz.controller.permission;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.ferrari.exception.DaoException;
import org.ferrari.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.alibaba.fastjson.JSONArray;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.service.permission.SysOperationService;
import com.mlj.ecbiz.service.permission.SysPermissionService;
import com.mlj.ecbiz.service.permission.SysResourceService;
import com.mlj.ecbiz.service.permission.SysRolePermissionService;
import com.mlj.ecbiz.service.permission.SysRoleService;
import com.mlj.ecbiz.service.permission.SysUserService;

@Controller
@RequestMapping("/power")
public class PowerManageController {

	private static final Logger logger = Logger.getLogger(PowerManageController.class);

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

	// 路径
	private String toList = "/permission/powermanage.httl";// 列表页
	private String toAdd = "/permission/sysUserAdd.httl";// 添加页面
	private String toEdit = "/permission/powermanage.httl";// 修改页
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView =new ModelAndView(toList);
		return modelAndView;
	}
	@RequestMapping("/loadResourceTree")
	public ModelAndView loadResourceTree(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		
		try {
			List<SysUser> list = sysUserService.getSysUserPage(query,page);
			HttpSession session = request.getSession();
			SysUser sysUser=new SysUser();
			sysUser.setName("master");
			session.setAttribute("user", sysUser);
			SysUser systemUser = (SysUser)session.getAttribute("user");
			String roleId = request.getParameter("roleId")==null?"1":request.getParameter("roleId");
			Map<SysResource,List<SysOperation>> resourceOperationMap;
			if(!systemUser.getName().equals("master")){
				List<SysRole> roleList =(List<SysRole>) session.getAttribute("roleList");
				resourceOperationMap = this.getRolePermissionIsNotAdmin(roleList);
			}else{
				resourceOperationMap = this.getAllPermission();
			}
			Set<SysResource> resourceSet = resourceOperationMap.keySet();
			if(resourceSet.size()>0){
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setContentType("text/xml; charset=UTF-8");
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = document.createElement("tree");
				
				List<SysResource> resourceList = new ArrayList<SysResource>();
				resourceList.addAll(resourceSet);
				//排序
				Collections.sort(resourceList,new SystemResourceComparator());
				root = recursiveResource(resourceList,root,Long.valueOf(0),document);							
				document.appendChild(root);
				DOMSource doms = new DOMSource(document);
				StreamResult sr = new StreamResult(response.getOutputStream());
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.setOutputProperty("encoding", "UTF-8");
				t.transform(doms, sr);
			}
		} catch (Exception e) {
			logger.error("SysUserController.listAll", e);
		}
		return null;
	}
	/**
	 * 
	 * @comment: <p>资源树递归生成</p>  
	 * @create.date: Aug 13, 2010 ( 2:29:31 PM )
	 * @author: yuliang
	 * @return:
	 */
	private Element recursiveResource(List<SysResource> resourceList,Element element,Long parentId,Document document)throws Exception{
		
		for(int i=0; i<resourceList.size(); i++){
			   SysResource b = resourceList.get(i);
		       if ((b!=null&&parentId==0 && b.getParentId()==0) 
		    		   || (b!=null&&parentId!=0 && parentId.equals(b.getParentId()))){
		    	   
		    	   Element elementNew = document.createElement("tree");
		    	   elementNew.setAttribute("text", b.getName().trim());
		    	   elementNew.setAttribute("dataId", b.getId().toString());
		    	   if(b.getParentId()==0){
		    	   elementNew.setAttribute("style","style=\"font-weight:bold;\"");   
		    	   }
	               element.appendChild(elementNew);
	               recursiveResource(resourceList,elementNew,b.getId(),document);
		     }  
		 }
		return element;
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
//	private Element recursiveRolePermission(Map<SysResource,List<SysOperation>> allResourceOperationMap,
//            Map<SysResource,List<SysOperation>> singleResourceOperationMap,
//            Element element,Integer parentId,Document document)throws Exception{
//		List<SysResource> resourceList = new ArrayList<SysResource>();
//		resourceList.addAll(allResourceOperationMap.keySet());
//		
//		//排序
//		Collections.sort(resourceList,new SystemResourceComparator());
//		
//		for(int i=0; i<resourceList.size(); i++){
//			SysResource b = resourceList.get(i);
//		
//		if ((b!=null&&parentId==0 && b.getParentId()==0) 
//		|| (b!=null&&parentId!=0 && parentId.equals(b.getParentId()))){
//		
//		Element elementNew = document.createElement("tree");
//		elementNew.setAttribute("text", b.getName());
//		elementNew.setAttribute("value", b.getId().toString());
//		System.out.println(elementNew.getAttribute("text"));
//		System.out.println(elementNew.getAttribute("value"));
//		if(singleResourceOperationMap.keySet().contains(b)){
//		elementNew.setAttribute("checked", "true");
//		}
//		
//		if(allResourceOperationMap.get(b)!=null){
//		String value = "";
//		List<SysOperation> allSystemOperationList = (List<SysOperation>)allResourceOperationMap.get(b);
//		List<SysOperation> singleSystemOperationList = (List<SysOperation>)singleResourceOperationMap.get(b);
//		
//		value = getSystemOperationValue(b.getId(),allSystemOperationList,singleSystemOperationList);
//		elementNew.setAttribute("addition", value);
//		}
//		
//		element.appendChild(elementNew);
//			recursiveRolePermission(allResourceOperationMap,singleResourceOperationMap,elementNew,b.getId(),document);
//		}  
//		}
//		return element;
//}
//	private Element recursiveRolePermission(Map<SysResource,List<SysOperation>> allResourceOperationMap,
//            Map<SysResource,List<SysOperation>> singleResourceOperationMap,
//            Element element,Long parentId,Document document)throws Exception{
//		List<SysResource> resourceList = new ArrayList<SysResource>();
//		resourceList.addAll(allResourceOperationMap.keySet());
//		
//		//排序
//		Collections.sort(resourceList,new SystemResourceComparator());
//		
//		for(int i=0; i<resourceList.size(); i++){
//		SysResource b = resourceList.get(i);
//		
//		if ((b!=null&&parentId==0 && b.getParentId()==0) 
//		|| (b!=null&&parentId!=0 && parentId.equals(b.getParentId()))){
//		
//		Element elementNew = document.createElement("tree");
//		elementNew.setAttribute("text", b.getName());
//		elementNew.setAttribute("value", b.getId().toString());
//		
//		if(singleResourceOperationMap.keySet().contains(b)){
//		elementNew.setAttribute("checked", "true");
//		}
//		
//		if(allResourceOperationMap.get(b)!=null){
//		String value = "";
//		List<SysOperation> allSystemOperationList = (List<SysOperation>)allResourceOperationMap.get(b);
//		List<SysOperation> singleSystemOperationList = (List<SysOperation>)singleResourceOperationMap.get(b);
//		
//		value = getSystemOperationValue(b.getId(),allSystemOperationList,singleSystemOperationList);
//		elementNew.setAttribute("addition", value);
//		}
//		
//		element.appendChild(elementNew);
//			recursiveRolePermission(allResourceOperationMap,singleResourceOperationMap,elementNew,b.getId(),document);
//		}  
//		}
//		return element;
//}
//	private String getSystemOperationValue(Long systemResourceId,List<SysOperation> allSystemOperationList,List<SysOperation> singleSystemOperationList){
//		StringBuffer buffer = new StringBuffer();
//		
//		if(singleSystemOperationList==null){
//			for(int i=0; i<allSystemOperationList.size();i++){
//				SysOperation systemOperation = allSystemOperationList.get(i);	
//				buffer.append(" | ");
//				buffer.append(systemOperation.getName()).append(" ");
//				buffer.append("<input type=\"checkbox\" name=\"permissionIds\" value=\""+systemResourceId+","+systemOperation.getId()+"\" ");
//				buffer.append(">");
//			}
//		}else{
//			for(int i=0; i<allSystemOperationList.size();i++){
//				SysOperation systemOperation = allSystemOperationList.get(i);	
//				buffer.append(" | ");
//				buffer.append(systemOperation.getName()).append(" ");
//				buffer.append("<input type=\"checkbox\" name=\"permissionIds\" value=\""+systemResourceId+","+systemOperation.getId()+"\" ");
//				buffer.append(singleSystemOperationList.contains(systemOperation)? "checked":"").append(">");
//			}
//		}
//		
//		return buffer.toString();
//	}
	public Map<SysResource, List<SysOperation>> getRolePermissionIsNotAdmin(List<SysRole> roleList) throws ServiceException {
		
		try{
			String roleIds = "";
			for(SysRole systemRole:roleList){
				roleIds = roleIds +systemRole.getRId().toString()+",";
			}
			if(roleIds.indexOf(",")>0){
				roleIds = roleIds.substring(0,roleIds.lastIndexOf(","));
				List<?> permissionList = (List<?>) sysRoleService.getSysRoleById(Long.valueOf(roleIds));
				
				if(permissionList!=null&&permissionList.size()>0){
					Map<String,String> permissionMap = new HashMap<String,String>();
					
					List<String> isAdmin = new ArrayList<String>();
					
					for(int i=0;i<permissionList.size();i++){
						HashMap map = (HashMap)permissionList.get(i);
						
						if(map.get("isAdmin").toString().equals("1")){
							isAdmin.add(map.get("resourceId").toString());
							continue;
						}
						
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
						if(!isAdmin.contains(resourceId)){
							SysResource systemResource = sysResourceService.getSysResourceById(Long.valueOf(resourceId));
						
							List<SysOperation> operationList = null;
						
							SysOperation systemOperation=new SysOperation();
							if(!permissionMap.get(resourceId).equals(""))
								systemOperation=sysOperationService.getSysOperationById(Long.valueOf(permissionMap.get(resourceId)));
							operationList.add(systemOperation);
						
							result.put(systemResource, operationList);
						}
					}
					return result;
				}		
			}
			return null;
		}catch(DaoException e){
			throw new ServiceException(e);
		}
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
					SysResource systemResource = sysResourceService.getSysResourceById(Long.parseLong(resourceId));
					List<SysOperation> operationList =null;
					if(!permissionMap.get(resourceId).equals(""))
						operationList=sysOperationService.batchSelect(permissionMap.get(resourceId));
						result.put(systemResource, operationList);
				}
				return result;
			}		
			return null;
		}catch(DaoException e){
			throw new ServiceException(e);
		}
	}
//	@RequestMapping(value="/add",method=RequestMethod.GET)
//	public ModelAndView toAdd(HttpServletRequest request) {
//		ModelAndView modelAndView = new ModelAndView(toAdd);
//		try {
//		} catch (Exception e) {
//			logger.error("SysUserController.toAdd", e);
//		}
//		return modelAndView;
//	}

	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public JSONArray toEdit(Long id,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		try {
			SysResource sysResource = sysResourceService.getSysResourceById(id);
			json.add(sysResource);
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(SysResource sysResource, HttpServletRequest request) {
		Long ret = -1L;
		sysResource.setCreateTime(new Date());
		Long num=sysResourceService.addSysResource(sysResource);
		SysPermission sysPermission=new SysPermission();
		sysPermission.setResourceId(sysResource.getId());
		sysPermissionService.addSysPermission(sysPermission);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	@ResponseBody
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public String editsave(SysResource sysResource, HttpServletRequest request) {
		Long ret = -1L;
		sysResource.setCreateTime(new Date());
		Long num=sysResourceService.updateSysResource(sysResource);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	@ResponseBody
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public String del(Long id, HttpServletRequest request) {
		Long ret = -1L;
		SysPermission sysPermission=new SysPermission();
		sysPermission.setResourceId(id);
		sysPermissionService.deleteSysPermissionByObj(sysPermission);
		Long num=sysResourceService.deleteSysResourceById(id);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	
}
