package com.mlj.ecbiz.dao.permission;
import java.util.List;
import com.mlj.ecbiz.model.permission.SysUser;
import com.chexun.base.framework.core.entity.PageEntity;
/**
 * SysUser管理接口
 * User: 
 * Date: 2017-08-20
 */
public interface SysUserDao {

    /**
     * 添加SysUser
     * @param sysUser 要添加的SysUser
     * @return id
     */
    public Long addSysUser(SysUser sysUser);
	public Long insertSysUser(SysUser sysUser);
    /**
     * 根据id删除一个SysUser
     * @param uid 要删除的id
     */
    public Long deleteSysUserById(Long uid);
    
    public Long deleteSysUserByObj(SysUser sysUser);

	public Long deleteSysUserByIdList(List<Long > ids);

    /**
     * 修改SysUser
     * @param sysUser 要修改的SysUser
     */
    public Long updateSysUser(SysUser sysUser);
    
    public Long updateSysUserByObj(SysUser sysUser);
    
    public Long updateSysUserByObjAndConditions(SysUser s,SysUser c);
    public void batchUpdateSysUser(List<SysUser> records);

    /**
     * 根据id获取单个SysUser对象
     * @param uid 要查询的id
     * @return SysUser
     */
    public SysUser getSysUserById(Long uid);
	public SysUser getSysUserByObj(SysUser sysUser);
    /**
     * 根据条件获取SysUser列表
     * @param sysUser 查询条件
     * @return List<SysUser>
     */
    public List<SysUser> getSysUserListByObj(SysUser sysUser);
    public List<SysUser> getSysUserListPage(SysUser sysUser,Integer offset,Integer limit);
    public Integer getSysUserCountByObj(SysUser sysUser);
    public List<SysUser> getSysUserPage(SysUser sysUser,PageEntity page);
    
    /**
    *以下为缓存查询用
    */
    public Long getSysUserIdByObj(SysUser sysUser);
    /**
     * 根据条件获取SysUser列表
     * @param sysUser 查询条件
     * @return List<SysUser>
     */
    public List<Long> getSysUserIdList(SysUser sysUser);
    public List<Long> getSysUserIdListByObj(SysUser sysUser);
    public List<Long> getSysUserIdListPage(SysUser sysUser,Integer offset,Integer limit);
    public List<Long> getSysUserIdPage(SysUser sysUser,PageEntity page);
}