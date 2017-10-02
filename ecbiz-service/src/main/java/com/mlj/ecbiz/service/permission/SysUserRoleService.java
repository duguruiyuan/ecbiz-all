package com.mlj.ecbiz.service.permission;

import java.util.List;
import com.mlj.ecbiz.model.permission.SysUserRole;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * SysUserRole管理接口
 * User: 
 * Date: 2017-10-02
 */
public interface SysUserRoleService {

    /**
     * 添加SysUserRole
     * @param sysUserRole 要添加的SysUserRole
     * @return id
     */
    public Long addSysUserRole(SysUserRole sysUserRole);
	public Long insertSysUserRole(SysUserRole sysUserRole);
    /**
     * 根据id删除一个SysUserRole
     * @param rUId 要删除的id
     */
    public Long deleteSysUserRoleById(Long rUId);
	public Long deleteSysUserRoleByObj(SysUserRole sysUserRole);
    public Long deleteSysUserRoleByIdList(List<Long > ids);
    /**
     * 修改SysUserRole
     * @param sysUserRole 要修改的SysUserRole
     */
    public Long updateSysUserRole(SysUserRole sysUserRole);
    public Long updateSysUserRoleByObj(SysUserRole sysUserRole);
    public Long updateSysUserRoleByObjAndConditions(SysUserRole s,SysUserRole c);
	public void batchUpdateSysUserRole(List<SysUserRole> records);
    /**
     * 根据id获取单个SysUserRole对象
     * @param rUId 要查询的id
     * @return SysUserRole
     */
    public SysUserRole getSysUserRoleById(Long rUId);
	public SysUserRole getSysUserRoleByObj(SysUserRole sysUserRole);
    /**
     * 根据条件获取SysUserRole列表
     * @param sysUserRole 查询条件
     * @return List<SysUserRole>
     */
    public List<SysUserRole> getSysUserRoleListByObj(SysUserRole sysUserRole);
    public List<SysUserRole> getSysUserRoleListPage(SysUserRole sysUserRole,Integer offset,Integer limit);
    public Integer getSysUserRoleCountByObj(SysUserRole sysUserRole);
    public List<SysUserRole> getSysUserRolePage(SysUserRole sysUserRole,PageEntity page);
    
}