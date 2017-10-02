package com.mlj.ecbiz.service.permission;

import java.util.List;
import com.mlj.ecbiz.model.permission.SysRole;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * SysRole管理接口
 * User: 
 * Date: 2017-08-20
 */
public interface SysRoleService {

    /**
     * 添加SysRole
     * @param sysRole 要添加的SysRole
     * @return id
     */
    public Long addSysRole(SysRole sysRole);
	public Long insertSysRole(SysRole sysRole);
    /**
     * 根据id删除一个SysRole
     * @param rId 要删除的id
     */
    public Long deleteSysRoleById(Long rId);
	public Long deleteSysRoleByObj(SysRole sysRole);
    public Long deleteSysRoleByIdList(List<Long > ids);
    /**
     * 修改SysRole
     * @param sysRole 要修改的SysRole
     */
    public Long updateSysRole(SysRole sysRole);
    public Long updateSysRoleByObj(SysRole sysRole);
    public Long updateSysRoleByObjAndConditions(SysRole s,SysRole c);
	public void batchUpdateSysRole(List<SysRole> records);
    /**
     * 根据id获取单个SysRole对象
     * @param rId 要查询的id
     * @return SysRole
     */
    public SysRole getSysRoleById(Long rId);
	public SysRole getSysRoleByObj(SysRole sysRole);
    /**
     * 根据条件获取SysRole列表
     * @param sysRole 查询条件
     * @return List<SysRole>
     */
    public List<SysRole> getSysRoleListByObj(SysRole sysRole);
    public List<SysRole> getSysRoleListPage(SysRole sysRole,Integer offset,Integer limit);
    public Integer getSysRoleCountByObj(SysRole sysRole);
    public List<SysRole> getSysRolePage(SysRole sysRole,PageEntity page);
    
    /**
     * 查询全部角色
     */
    public List<SysRole> findALLRole(SysRole sysRole);
    
}