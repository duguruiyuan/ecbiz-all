package com.mlj.ecbiz.service.permission;

import java.util.List;

import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysRolePermission;
/**
 * SysRolePermission管理接口
 * User: 
 * Date: 2017-10-03
 */
public interface SysRolePermissionService {

    /**
     * 添加SysRolePermission
     * @param sysRolePermission 要添加的SysRolePermission
     * @return id
     */
    public Long addSysRolePermission(SysRolePermission sysRolePermission);
	public Long insertSysRolePermission(SysRolePermission sysRolePermission);
    /**
     * 根据id删除一个SysRolePermission
     * @param id 要删除的id
     */
    public Long deleteSysRolePermissionById(Long id);
	public Long deleteSysRolePermissionByObj(SysRolePermission sysRolePermission);
    public Long deleteSysRolePermissionByIdList(List<Long > ids);
    /**
     * 修改SysRolePermission
     * @param sysRolePermission 要修改的SysRolePermission
     */
    public Long updateSysRolePermission(SysRolePermission sysRolePermission);
    public Long updateSysRolePermissionByObj(SysRolePermission sysRolePermission);
    public Long updateSysRolePermissionByObjAndConditions(SysRolePermission s,SysRolePermission c);
	public void batchUpdateSysRolePermission(List<SysRolePermission> records);
    /**
     * 根据id获取单个SysRolePermission对象
     * @param id 要查询的id
     * @return SysRolePermission
     */
    public SysRolePermission getSysRolePermissionById(Long id);
	public SysRolePermission getSysRolePermissionByObj(SysRolePermission sysRolePermission);
    /**
     * 根据条件获取SysRolePermission列表
     * @param sysRolePermission 查询条件
     * @return List<SysRolePermission>
     */
    public List<SysRolePermission> getSysRolePermissionListByObj(SysRolePermission sysRolePermission);
    public List<SysRolePermission> getSysRolePermissionListPage(SysRolePermission sysRolePermission,Integer offset,Integer limit);
    public Integer getSysRolePermissionCountByObj(SysRolePermission sysRolePermission);
    public List<SysRolePermission> getSysRolePermissionPage(SysRolePermission sysRolePermission,PageEntity page);
    public List<?>  getPermissionByRoleIds(String roleIds);
    public List<?> getRolePermissionByRoleIds(List<SysRole> list);
    
}