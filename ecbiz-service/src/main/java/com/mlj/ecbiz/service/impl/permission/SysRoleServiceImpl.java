package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.dao.permission.SysRoleDao;
import com.mlj.ecbiz.service.permission.SysRoleService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysRole管理接口
 * User: 
 * Date: 2017-08-20
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService{

 	@Autowired
    private SysRoleDao sysRoleDao;
    /**
     * 添加SysRole
     * @param sysRole 要添加的SysRole
     * @return id
     */
    public Long addSysRole(SysRole sysRole){
    	Long res = sysRoleDao.addSysRole(sysRole);
    	return res;
    }
	public Long insertSysRole(SysRole sysRole){
		Long res = sysRoleDao.insertSysRole(sysRole);
		
    	return res;
	}
    /**
     * 根据id删除一个SysRole
     * @param rId 要删除的id
     */
    public Long deleteSysRoleById(Long rId){
    	return sysRoleDao.deleteSysRoleById(rId);
    }
	public Long deleteSysRoleByObj(SysRole sysRole){
        return sysRoleDao.deleteSysRoleByObj(sysRole);
    }
    public Long deleteSysRoleByIdList(List<Long > ids){
    	
    	return sysRoleDao.deleteSysRoleByIdList(ids);
    }
    /**
     * 修改SysRole
     * @param sysRole 要修改的SysRole
     */
    public Long updateSysRole(SysRole sysRole){
     	return sysRoleDao.updateSysRole(sysRole);
    }
    
    public Long updateSysRoleByObj(SysRole sysRole){
    	return sysRoleDao.updateSysRoleByObj(sysRole);
    }
    
    public Long updateSysRoleByObjAndConditions(SysRole s,SysRole c){
    	return sysRoleDao.updateSysRoleByObjAndConditions(s,c);
    }
	public void batchUpdateSysRole(List<SysRole> records){
		sysRoleDao.batchUpdateSysRole(records);
	}
    /**
     * 根据id获取单个SysRole对象
     * @param rId 要查询的id
     * @return SysRole
     */
    
    public Integer getSysRoleCountByObj(SysRole sysRole){
    	return sysRoleDao.getSysRoleCountByObj(sysRole);
    }
    


    public SysRole getSysRoleById(Long rId){
    	return sysRoleDao.getSysRoleById( rId);
    }
    
     public SysRole getSysRoleByObj(SysRole sysRole) {
        return sysRoleDao.getSysRoleByObj(sysRole);
    }


    
    public List<SysRole> getSysRoleListByObj(SysRole sysRole){
        return sysRoleDao.getSysRoleListByObj(sysRole);
    }
    public List<SysRole> getSysRoleListPage(SysRole sysRole,Integer offset,Integer limit){
        return sysRoleDao.getSysRoleListPage(sysRole,offset,limit);
    }
    
    public List<SysRole> getSysRolePage(SysRole sysRole,PageEntity page) {
        return sysRoleDao.getSysRolePage(sysRole,page);
    }
	@Override
	public List<SysRole> findALLRole(SysRole sysRole) {
		// TODO Auto-generated method stub
		return sysRoleDao.findALLRole(sysRole);
	}
}