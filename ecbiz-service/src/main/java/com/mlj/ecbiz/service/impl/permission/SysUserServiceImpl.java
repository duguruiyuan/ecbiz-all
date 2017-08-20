package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.dao.permission.SysUserDao;
import com.mlj.ecbiz.service.permission.SysUserService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysUser管理接口
 * User: 
 * Date: 2017-08-20
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{

 	@Autowired
    private SysUserDao sysUserDao;
    /**
     * 添加SysUser
     * @param sysUser 要添加的SysUser
     * @return id
     */
    public Long addSysUser(SysUser sysUser){
    	Long res = sysUserDao.addSysUser(sysUser);
    	return res;
    }
	public Long insertSysUser(SysUser sysUser){
		Long res = sysUserDao.insertSysUser(sysUser);
		
    	return res;
	}
    /**
     * 根据id删除一个SysUser
     * @param uid 要删除的id
     */
    public Long deleteSysUserById(Long uid){
    	return sysUserDao.deleteSysUserById(uid);
    }
	public Long deleteSysUserByObj(SysUser sysUser){
        return sysUserDao.deleteSysUserByObj(sysUser);
    }
    public Long deleteSysUserByIdList(List<Long > ids){
    	
    	return sysUserDao.deleteSysUserByIdList(ids);
    }
    /**
     * 修改SysUser
     * @param sysUser 要修改的SysUser
     */
    public Long updateSysUser(SysUser sysUser){
     	return sysUserDao.updateSysUser(sysUser);
    }
    
    public Long updateSysUserByObj(SysUser sysUser){
    	return sysUserDao.updateSysUserByObj(sysUser);
    }
    
    public Long updateSysUserByObjAndConditions(SysUser s,SysUser c){
    	return sysUserDao.updateSysUserByObjAndConditions(s,c);
    }
	public void batchUpdateSysUser(List<SysUser> records){
		sysUserDao.batchUpdateSysUser(records);
	}
    /**
     * 根据id获取单个SysUser对象
     * @param uid 要查询的id
     * @return SysUser
     */
    
    public Integer getSysUserCountByObj(SysUser sysUser){
    	return sysUserDao.getSysUserCountByObj(sysUser);
    }
    


    public SysUser getSysUserById(Long uid){
    	return sysUserDao.getSysUserById( uid);
    }
    
     public SysUser getSysUserByObj(SysUser sysUser) {
        return sysUserDao.getSysUserByObj(sysUser);
    }


    
    public List<SysUser> getSysUserListByObj(SysUser sysUser){
        return sysUserDao.getSysUserListByObj(sysUser);
    }
    public List<SysUser> getSysUserListPage(SysUser sysUser,Integer offset,Integer limit){
        return sysUserDao.getSysUserListPage(sysUser,offset,limit);
    }
    
    public List<SysUser> getSysUserPage(SysUser sysUser,PageEntity page) {
        return sysUserDao.getSysUserPage(sysUser,page);
    }
}