package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.dao.permission.SysPermissionDao;
import com.mlj.ecbiz.service.permission.SysPermissionService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysPermission管理接口
 * User: 
 * Date: 2017-10-03
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService{

 	@Autowired
    private SysPermissionDao sysPermissionDao;
    /**
     * 添加SysPermission
     * @param sysPermission 要添加的SysPermission
     * @return id
     */
    public Long addSysPermission(SysPermission sysPermission){
    	Long res = sysPermissionDao.addSysPermission(sysPermission);
    	return res;
    }
	public Long insertSysPermission(SysPermission sysPermission){
		Long res = sysPermissionDao.insertSysPermission(sysPermission);
		
    	return res;
	}
    /**
     * 根据id删除一个SysPermission
     * @param id 要删除的id
     */
    public Long deleteSysPermissionById(Long id){
    	return sysPermissionDao.deleteSysPermissionById(id);
    }
	public Long deleteSysPermissionByObj(SysPermission sysPermission){
        return sysPermissionDao.deleteSysPermissionByObj(sysPermission);
    }
    public Long deleteSysPermissionByIdList(List<Long > ids){
    	
    	return sysPermissionDao.deleteSysPermissionByIdList(ids);
    }
    /**
     * 修改SysPermission
     * @param sysPermission 要修改的SysPermission
     */
    public Long updateSysPermission(SysPermission sysPermission){
     	return sysPermissionDao.updateSysPermission(sysPermission);
    }
    
    public Long updateSysPermissionByObj(SysPermission sysPermission){
    	return sysPermissionDao.updateSysPermissionByObj(sysPermission);
    }
    
    public Long updateSysPermissionByObjAndConditions(SysPermission s,SysPermission c){
    	return sysPermissionDao.updateSysPermissionByObjAndConditions(s,c);
    }
	public void batchUpdateSysPermission(List<SysPermission> records){
		sysPermissionDao.batchUpdateSysPermission(records);
	}
    /**
     * 根据id获取单个SysPermission对象
     * @param id 要查询的id
     * @return SysPermission
     */
    
    public Integer getSysPermissionCountByObj(SysPermission sysPermission){
    	return sysPermissionDao.getSysPermissionCountByObj(sysPermission);
    }
    


    public SysPermission getSysPermissionById(Long id){
    	return sysPermissionDao.getSysPermissionById( id);
    }
    
     public SysPermission getSysPermissionByObj(SysPermission sysPermission) {
        return sysPermissionDao.getSysPermissionByObj(sysPermission);
    }


    
    public List<SysPermission> getSysPermissionListByObj(SysPermission sysPermission){
        return sysPermissionDao.getSysPermissionListByObj(sysPermission);
    }
    public List<SysPermission> getSysPermissionListPage(SysPermission sysPermission,Integer offset,Integer limit){
        return sysPermissionDao.getSysPermissionListPage(sysPermission,offset,limit);
    }
    
    public List<SysPermission> getSysPermissionPage(SysPermission sysPermission,PageEntity page) {
        return sysPermissionDao.getSysPermissionPage(sysPermission,page);
    }
	@Override
	public List<?> getAllPermission() {
		// TODO Auto-generated method stub
		return sysPermissionDao.getAllPermission();
	}
	@Override
	public List<?> getAllPermissionByPids(List<SysRolePermission> list) {
		// TODO Auto-generated method stub
		return sysPermissionDao.getAllPermissionByPids(list);
	}
}