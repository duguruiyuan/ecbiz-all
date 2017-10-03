package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.dao.permission.SysRolePermissionDao;
import com.mlj.ecbiz.service.permission.SysRolePermissionService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysRolePermission管理接口
 * User: 
 * Date: 2017-10-03
 */
@Service("sysRolePermissionService")
public class SysRolePermissionServiceImpl implements SysRolePermissionService{

 	@Autowired
    private SysRolePermissionDao sysRolePermissionDao;
    /**
     * 添加SysRolePermission
     * @param sysRolePermission 要添加的SysRolePermission
     * @return id
     */
    public Long addSysRolePermission(SysRolePermission sysRolePermission){
    	Long res = sysRolePermissionDao.addSysRolePermission(sysRolePermission);
    	return res;
    }
	public Long insertSysRolePermission(SysRolePermission sysRolePermission){
		Long res = sysRolePermissionDao.insertSysRolePermission(sysRolePermission);
		
    	return res;
	}
    /**
     * 根据id删除一个SysRolePermission
     * @param id 要删除的id
     */
    public Long deleteSysRolePermissionById(Long id){
    	return sysRolePermissionDao.deleteSysRolePermissionById(id);
    }
	public Long deleteSysRolePermissionByObj(SysRolePermission sysRolePermission){
        return sysRolePermissionDao.deleteSysRolePermissionByObj(sysRolePermission);
    }
    public Long deleteSysRolePermissionByIdList(List<Long > ids){
    	
    	return sysRolePermissionDao.deleteSysRolePermissionByIdList(ids);
    }
    /**
     * 修改SysRolePermission
     * @param sysRolePermission 要修改的SysRolePermission
     */
    public Long updateSysRolePermission(SysRolePermission sysRolePermission){
     	return sysRolePermissionDao.updateSysRolePermission(sysRolePermission);
    }
    
    public Long updateSysRolePermissionByObj(SysRolePermission sysRolePermission){
    	return sysRolePermissionDao.updateSysRolePermissionByObj(sysRolePermission);
    }
    
    public Long updateSysRolePermissionByObjAndConditions(SysRolePermission s,SysRolePermission c){
    	return sysRolePermissionDao.updateSysRolePermissionByObjAndConditions(s,c);
    }
	public void batchUpdateSysRolePermission(List<SysRolePermission> records){
		sysRolePermissionDao.batchUpdateSysRolePermission(records);
	}
    /**
     * 根据id获取单个SysRolePermission对象
     * @param id 要查询的id
     * @return SysRolePermission
     */
    
    public Integer getSysRolePermissionCountByObj(SysRolePermission sysRolePermission){
    	return sysRolePermissionDao.getSysRolePermissionCountByObj(sysRolePermission);
    }
    


    public SysRolePermission getSysRolePermissionById(Long id){
    	return sysRolePermissionDao.getSysRolePermissionById( id);
    }
    
     public SysRolePermission getSysRolePermissionByObj(SysRolePermission sysRolePermission) {
        return sysRolePermissionDao.getSysRolePermissionByObj(sysRolePermission);
    }


    
    public List<SysRolePermission> getSysRolePermissionListByObj(SysRolePermission sysRolePermission){
        return sysRolePermissionDao.getSysRolePermissionListByObj(sysRolePermission);
    }
    public List<SysRolePermission> getSysRolePermissionListPage(SysRolePermission sysRolePermission,Integer offset,Integer limit){
        return sysRolePermissionDao.getSysRolePermissionListPage(sysRolePermission,offset,limit);
    }
    
    public List<SysRolePermission> getSysRolePermissionPage(SysRolePermission sysRolePermission,PageEntity page) {
        return sysRolePermissionDao.getSysRolePermissionPage(sysRolePermission,page);
    }
	@Override
	public List<?> getPermissionByRoleIds(String roleIds) {
		return sysRolePermissionDao.getPermissionByRoleIds(roleIds);
	}
}