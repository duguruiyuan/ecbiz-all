package com.mlj.ecbiz.service.permission;

import java.util.List;

import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * SysPermission管理接口
 * User: 
 * Date: 2017-10-03
 */
public interface SysPermissionService {

    /**
     * 添加SysPermission
     * @param sysPermission 要添加的SysPermission
     * @return id
     */
    public Long addSysPermission(SysPermission sysPermission);
	public Long insertSysPermission(SysPermission sysPermission);
    /**
     * 根据id删除一个SysPermission
     * @param id 要删除的id
     */
    public Long deleteSysPermissionById(Long id);
	public Long deleteSysPermissionByObj(SysPermission sysPermission);
    public Long deleteSysPermissionByIdList(List<Long > ids);
    /**
     * 修改SysPermission
     * @param sysPermission 要修改的SysPermission
     */
    public Long updateSysPermission(SysPermission sysPermission);
    public Long updateSysPermissionByObj(SysPermission sysPermission);
    public Long updateSysPermissionByObjAndConditions(SysPermission s,SysPermission c);
	public void batchUpdateSysPermission(List<SysPermission> records);
    /**
     * 根据id获取单个SysPermission对象
     * @param id 要查询的id
     * @return SysPermission
     */
    public SysPermission getSysPermissionById(Long id);
	public SysPermission getSysPermissionByObj(SysPermission sysPermission);
    /**
     * 根据条件获取SysPermission列表
     * @param sysPermission 查询条件
     * @return List<SysPermission>
     */
    public List<SysPermission> getSysPermissionListByObj(SysPermission sysPermission);
    public List<SysPermission> getSysPermissionListPage(SysPermission sysPermission,Integer offset,Integer limit);
    public Integer getSysPermissionCountByObj(SysPermission sysPermission);
    public List<SysPermission> getSysPermissionPage(SysPermission sysPermission,PageEntity page);
    public List<?> getAllPermission();
    public List<?> getAllPermissionByPids(List<SysRolePermission> list);
}