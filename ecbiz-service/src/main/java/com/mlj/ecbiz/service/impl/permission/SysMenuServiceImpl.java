package com.mlj.ecbiz.service.impl.permission;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ferrari.exception.DaoException;
import org.ferrari.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mlj.ecbiz.dao.permission.SysOperationDao;
import com.mlj.ecbiz.dao.permission.SysPermissionDao;
import com.mlj.ecbiz.dao.permission.SysResourceDao;
import com.mlj.ecbiz.dao.permission.SysRolePermissionDao;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.service.permission.SysMenuService;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
    private SysOperationDao sysOperationDao;
	@Autowired
    private SysResourceDao sysResourceDao;
	@Autowired
    private SysPermissionDao sysPermissionDao;
	@Autowired
    private SysRolePermissionDao sysRolePermissionDao;
	@Override
	public Element recursiveRolePermission(
			Map<SysResource, List<SysOperation>> allResourceOperationMap,
			Map<SysResource, List<SysOperation>> singleResourceOperationMap,
			Element element, Long parentId, Document document) {
		List<SysResource> resourceList = new ArrayList<SysResource>();
		resourceList.addAll(allResourceOperationMap.keySet());
		//排序
		Collections.sort(resourceList,new SystemResourceComparator());
		for(int i=0; i<resourceList.size(); i++){
			SysResource b = resourceList.get(i);
			if ((b!=null&&parentId==0 && b.getParentId()==0)|| (b!=null&&parentId!=0 && parentId.equals(b.getParentId()))){
				Element elementNew = document.createElement("tree");
				elementNew.setAttribute("text", b.getName());
				elementNew.setAttribute("value", b.getId().toString());
				elementNew.setAttribute("url", b.getUrl());
				if(singleResourceOperationMap.keySet().contains(b))elementNew.setAttribute("checked", "true");
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
	@Override
	public String getSystemOperationValue(Long systemResourceId,
			List<SysOperation> allSystemOperationList,
			List<SysOperation> singleSystemOperationList) {
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
	@Override
	public Map<SysResource, List<SysOperation>> getPermissionByRoleId(
			String roleId) throws ServiceException {
		try{
			if(roleId!=null&&!roleId.equals("")){
				List<?> permissionList = (List<?>) sysRolePermissionDao.getPermissionByRoleIds(roleId);
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
						SysResource systemResource =sysResourceDao.getSysResourceById(Long.valueOf(resourceId));
						List<SysOperation> operationList = null;
						if(!permissionMap.get(resourceId).equals(""))
							operationList = (List<SysOperation>) sysOperationDao.systemOperation_batchSelect(permissionMap.get(resourceId));
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
	@Override
	public Map<SysResource, List<SysOperation>> getRolePermissionIsNotAdmin(List<SysRole> roleList) {
		List<SysRolePermission> rpList=(List<SysRolePermission>)sysRolePermissionDao.getRolePermissionByRoleIds(roleList);
		List<?> permissionList= sysPermissionDao.getAllPermissionByPids(rpList);
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
				SysResource systemResource = sysResourceDao.getSysResourceById(Long.parseLong(resourceId));
				List<SysOperation> operationList =null;
				if(!permissionMap.get(resourceId).equals(""))
					operationList=(List<SysOperation>) sysOperationDao.systemOperation_batchSelect(permissionMap.get(resourceId));
					result.put(systemResource, operationList);
			}
			return result;
		}	
	return null;
	}
	@Override
	public Map<SysResource, List<SysOperation>> getAllPermission()
			throws ServiceException, IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		try{
			List<?> permissionList = sysPermissionDao.getAllPermission();
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
					SysResource systemResource = sysResourceDao.getSysResourceById(Long.parseLong(resourceId));
					List<SysOperation> operationList =null;
					if(!permissionMap.get(resourceId).equals(""))
						operationList=(List<SysOperation>) sysOperationDao.systemOperation_batchSelect(permissionMap.get(resourceId));
						result.put(systemResource, operationList);
				}
				return result;
			}		
			return null;
		}catch(DaoException e){
			throw new ServiceException(e);
		}
	}
	public Element recursiveResource(List<SysResource> resourceList,
			Element element, Long parentId, Document document) throws Exception {
		Collections.sort(resourceList,new SystemResourceComparator());
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
}
class SystemResourceComparator implements Comparator{
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
