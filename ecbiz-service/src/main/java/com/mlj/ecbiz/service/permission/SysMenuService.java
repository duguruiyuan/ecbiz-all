package com.mlj.ecbiz.service.permission;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.ferrari.exception.ServiceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;

public interface SysMenuService {
	public Element recursiveRolePermission(Map<SysResource,List<SysOperation>> allResourceOperationMap,Map<SysResource,List<SysOperation>> singleResourceOperationMap,Element element,Long parentId,Document document);
	public String getSystemOperationValue(Long systemResourceId,List<SysOperation> allSystemOperationList,List<SysOperation> singleSystemOperationList);
	public Map<SysResource, List<SysOperation>> getPermissionByRoleId(String roleId) throws ServiceException;
	public Map<SysResource,List<SysOperation>>  getRolePermissionIsNotAdmin(List<SysRole> roleList);
	public Map<SysResource, List<SysOperation>> getAllPermission()throws ServiceException, IntrospectionException, IllegalAccessException, InvocationTargetException;
	
	public Element recursiveResource(List<SysResource> resourceList,Element element,Long parentId,Document document)throws Exception;
}
