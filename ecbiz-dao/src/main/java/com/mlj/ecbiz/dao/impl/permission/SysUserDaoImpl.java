package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.dao.permission.SysUserDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysUser
 * User:
 * Date: 2017-08-20
 */
 @Repository("sysUserDao")
public class SysUserDaoImpl extends GenericDaoImpl implements SysUserDao{

    public Long addSysUser(SysUser sysUser) {
        return this.insert("com.mlj.ecbiz.model.permission.SysUserMapper.createSysUser",sysUser);
    }
	public Long insertSysUser(SysUser sysUser){
		return this.insert("com.mlj.ecbiz.model.permission.SysUserMapper.insertSysUser",sysUser);
	}
    public Long deleteSysUserById(Long uid){
        return this.delete("com.mlj.ecbiz.model.permission.SysUserMapper.deleteSysUserById",uid);
    }
    public Long deleteSysUserByObj(SysUser sysUser){
        return this.delete("com.mlj.ecbiz.model.permission.SysUserMapper.deleteSysUserByObj",sysUser);
    }
    
	public Long deleteSysUserByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysUserMapper.deleteSysUserByIdList",ids);
	}
    public Long updateSysUser(SysUser sysUser) {
        return this.update("com.mlj.ecbiz.model.permission.SysUserMapper.updateSysUser",sysUser);
    }
    
    public Long updateSysUserByObj(SysUser sysUser){
    	return this.update("com.mlj.ecbiz.model.permission.SysUserMapper.updateSysUserByObj",sysUser);
    }
    
    public Long updateSysUserByObjAndConditions(SysUser s,SysUser c){
    	Map<String,SysUser> map = new HashMap<String,SysUser>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysUserMapper.updateSysUserByObjAndConditions",map);
    }
	public void batchUpdateSysUser(List<SysUser> records){
		this.update("com.mlj.ecbiz.model.permission.SysUserMapper.batchUpdateSysUser",records);
	}
    public SysUser getSysUserById(Long uid) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserById",uid);
    }
    
    public SysUser getSysUserByObj(SysUser sysUser) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserByObj",sysUser);
    }

    
    public List<SysUser> getSysUserListByObj(SysUser sysUser){
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserListByObj",sysUser);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysUser> getSysUserListPage(SysUser sysUser,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysUser);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysUserCountByObj(SysUser sysUser){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserCountByObj", sysUser);
    }
    
    public List<SysUser> getSysUserPage(SysUser sysUser,PageEntity page) {
        Integer objectscount = getSysUserCountByObj(sysUser);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysUserListPage(sysUser,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysUserIdByObj(SysUser sysUser) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserIdByObj",sysUser);
    }

    public List<Long> getSysUserIdList(SysUser sysUser) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserIdList",sysUser);
    }
    
    public List<Long> getSysUserIdListByObj(SysUser sysUser){
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserIdListByObj",sysUser);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysUserIdListPage(SysUser sysUser,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysUser);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysUserMapper.getSysUserIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysUserIdPage(SysUser sysUser,PageEntity page) {
        Integer objectscount = getSysUserCountByObj(sysUser);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysUserIdListPage(sysUser,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
