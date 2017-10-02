package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysUserRole;
import com.mlj.ecbiz.dao.permission.SysUserRoleDao;
import com.mlj.ecbiz.service.permission.SysUserRoleService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysUserRole管理接口
 * User: 
 * Date: 2017-10-02
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService{

 	@Autowired
    private SysUserRoleDao sysUserRoleDao;
    /**
     * 添加SysUserRole
     * @param sysUserRole 要添加的SysUserRole
     * @return id
     */
    public Long addSysUserRole(SysUserRole sysUserRole){
    	Long res = sysUserRoleDao.addSysUserRole(sysUserRole);
    	return res;
    }
	public Long insertSysUserRole(SysUserRole sysUserRole){
		Long res = sysUserRoleDao.insertSysUserRole(sysUserRole);
		
    	return res;
	}
    /**
     * 根据id删除一个SysUserRole
     * @param rUId 要删除的id
     */
    public Long deleteSysUserRoleById(Long rUId){
    	return sysUserRoleDao.deleteSysUserRoleById(rUId);
    }
	public Long deleteSysUserRoleByObj(SysUserRole sysUserRole){
        return sysUserRoleDao.deleteSysUserRoleByObj(sysUserRole);
    }
    public Long deleteSysUserRoleByIdList(List<Long > ids){
    	
    	return sysUserRoleDao.deleteSysUserRoleByIdList(ids);
    }
    /**
     * 修改SysUserRole
     * @param sysUserRole 要修改的SysUserRole
     */
    public Long updateSysUserRole(SysUserRole sysUserRole){
     	return sysUserRoleDao.updateSysUserRole(sysUserRole);
    }
    
    public Long updateSysUserRoleByObj(SysUserRole sysUserRole){
    	return sysUserRoleDao.updateSysUserRoleByObj(sysUserRole);
    }
    
    public Long updateSysUserRoleByObjAndConditions(SysUserRole s,SysUserRole c){
    	return sysUserRoleDao.updateSysUserRoleByObjAndConditions(s,c);
    }
	public void batchUpdateSysUserRole(List<SysUserRole> records){
		sysUserRoleDao.batchUpdateSysUserRole(records);
	}
    /**
     * 根据id获取单个SysUserRole对象
     * @param rUId 要查询的id
     * @return SysUserRole
     */
    
    public Integer getSysUserRoleCountByObj(SysUserRole sysUserRole){
    	return sysUserRoleDao.getSysUserRoleCountByObj(sysUserRole);
    }
    


    public SysUserRole getSysUserRoleById(Long rUId){
    	return sysUserRoleDao.getSysUserRoleById( rUId);
    }
    
     public SysUserRole getSysUserRoleByObj(SysUserRole sysUserRole) {
        return sysUserRoleDao.getSysUserRoleByObj(sysUserRole);
    }


    
    public List<SysUserRole> getSysUserRoleListByObj(SysUserRole sysUserRole){
        return sysUserRoleDao.getSysUserRoleListByObj(sysUserRole);
    }
    public List<SysUserRole> getSysUserRoleListPage(SysUserRole sysUserRole,Integer offset,Integer limit){
        return sysUserRoleDao.getSysUserRoleListPage(sysUserRole,offset,limit);
    }
    
    public List<SysUserRole> getSysUserRolePage(SysUserRole sysUserRole,PageEntity page) {
        return sysUserRoleDao.getSysUserRolePage(sysUserRole,page);
    }
}