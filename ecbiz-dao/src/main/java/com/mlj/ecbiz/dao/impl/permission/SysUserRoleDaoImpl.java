package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysUserRole;
import com.mlj.ecbiz.dao.permission.SysUserRoleDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysUserRole
 * User:
 * Date: 2017-10-02
 */
 @Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends GenericDaoImpl implements SysUserRoleDao{

    public Long addSysUserRole(SysUserRole sysUserRole) {
        return this.insert("com.mlj.ecbiz.model.permission.SysUserRoleMapper.createSysUserRole",sysUserRole);
    }
	public Long insertSysUserRole(SysUserRole sysUserRole){
		return this.insert("com.mlj.ecbiz.model.permission.SysUserRoleMapper.insertSysUserRole",sysUserRole);
	}
    public Long deleteSysUserRoleById(Long rUId){
        return this.delete("com.mlj.ecbiz.model.permission.SysUserRoleMapper.deleteSysUserRoleById",rUId);
    }
    public Long deleteSysUserRoleByObj(SysUserRole sysUserRole){
        return this.delete("com.mlj.ecbiz.model.permission.SysUserRoleMapper.deleteSysUserRoleByObj",sysUserRole);
    }
    
	public Long deleteSysUserRoleByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysUserRoleMapper.deleteSysUserRoleByIdList",ids);
	}
    public Long updateSysUserRole(SysUserRole sysUserRole) {
        return this.update("com.mlj.ecbiz.model.permission.SysUserRoleMapper.updateSysUserRole",sysUserRole);
    }
    
    public Long updateSysUserRoleByObj(SysUserRole sysUserRole){
    	return this.update("com.mlj.ecbiz.model.permission.SysUserRoleMapper.updateSysUserRoleByObj",sysUserRole);
    }
    
    public Long updateSysUserRoleByObjAndConditions(SysUserRole s,SysUserRole c){
    	Map<String,SysUserRole> map = new HashMap<String,SysUserRole>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysUserRoleMapper.updateSysUserRoleByObjAndConditions",map);
    }
	public void batchUpdateSysUserRole(List<SysUserRole> records){
		this.update("com.mlj.ecbiz.model.permission.SysUserRoleMapper.batchUpdateSysUserRole",records);
	}
    public SysUserRole getSysUserRoleById(Long rUId) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleById",rUId);
    }
    
    public SysUserRole getSysUserRoleByObj(SysUserRole sysUserRole) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleByObj",sysUserRole);
    }

    
    public List<SysUserRole> getSysUserRoleListByObj(SysUserRole sysUserRole){
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleListByObj",sysUserRole);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysUserRole> getSysUserRoleListPage(SysUserRole sysUserRole,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysUserRole);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysUserRoleCountByObj(SysUserRole sysUserRole){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleCountByObj", sysUserRole);
    }
    
    public List<SysUserRole> getSysUserRolePage(SysUserRole sysUserRole,PageEntity page) {
        Integer objectscount = getSysUserRoleCountByObj(sysUserRole);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysUserRoleListPage(sysUserRole,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysUserRoleIdByObj(SysUserRole sysUserRole) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleIdByObj",sysUserRole);
    }

    public List<Long> getSysUserRoleIdList(SysUserRole sysUserRole) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleIdList",sysUserRole);
    }
    
    public List<Long> getSysUserRoleIdListByObj(SysUserRole sysUserRole){
        return this.selectList("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleIdListByObj",sysUserRole);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysUserRoleIdListPage(SysUserRole sysUserRole,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysUserRole);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysUserRoleMapper.getSysUserRoleIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysUserRoleIdPage(SysUserRole sysUserRole,PageEntity page) {
        Integer objectscount = getSysUserRoleCountByObj(sysUserRole);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysUserRoleIdListPage(sysUserRole,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
