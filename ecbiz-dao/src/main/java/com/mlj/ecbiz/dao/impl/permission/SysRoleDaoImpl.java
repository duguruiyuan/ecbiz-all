package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.dao.permission.SysRoleDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysRole
 * User:
 * Date: 2017-08-20
 */
 @Repository("sysRoleDao")
public class SysRoleDaoImpl extends GenericDaoImpl implements SysRoleDao{

    public Long addSysRole(SysRole sysRole) {
        return this.insert("com.mlj.ecbiz.model.permission.SysRoleMapper.createSysRole",sysRole);
    }
	public Long insertSysRole(SysRole sysRole){
		return this.insert("com.mlj.ecbiz.model.permission.SysRoleMapper.insertSysRole",sysRole);
	}
    public Long deleteSysRoleById(Long rId){
        return this.delete("com.mlj.ecbiz.model.permission.SysRoleMapper.deleteSysRoleById",rId);
    }
    public Long deleteSysRoleByObj(SysRole sysRole){
        return this.delete("com.mlj.ecbiz.model.permission.SysRoleMapper.deleteSysRoleByObj",sysRole);
    }
    
	public Long deleteSysRoleByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysRoleMapper.deleteSysRoleByIdList",ids);
	}
    public Long updateSysRole(SysRole sysRole) {
        return this.update("com.mlj.ecbiz.model.permission.SysRoleMapper.updateSysRole",sysRole);
    }
    
    public Long updateSysRoleByObj(SysRole sysRole){
    	return this.update("com.mlj.ecbiz.model.permission.SysRoleMapper.updateSysRoleByObj",sysRole);
    }
    
    public Long updateSysRoleByObjAndConditions(SysRole s,SysRole c){
    	Map<String,SysRole> map = new HashMap<String,SysRole>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysRoleMapper.updateSysRoleByObjAndConditions",map);
    }
	public void batchUpdateSysRole(List<SysRole> records){
		this.update("com.mlj.ecbiz.model.permission.SysRoleMapper.batchUpdateSysRole",records);
	}
    public SysRole getSysRoleById(Long rId) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleById",rId);
    }
    
    public SysRole getSysRoleByObj(SysRole sysRole) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleByObj",sysRole);
    }

    
    public List<SysRole> getSysRoleListByObj(SysRole sysRole){
        return this.selectList("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleListByObj",sysRole);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysRole> getSysRoleListPage(SysRole sysRole,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysRole);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysRoleCountByObj(SysRole sysRole){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleCountByObj", sysRole);
    }
    
    public List<SysRole> getSysRolePage(SysRole sysRole,PageEntity page) {
        Integer objectscount = getSysRoleCountByObj(sysRole);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysRoleListPage(sysRole,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysRoleIdByObj(SysRole sysRole) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleIdByObj",sysRole);
    }

    public List<Long> getSysRoleIdList(SysRole sysRole) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleIdList",sysRole);
    }
    
    public List<Long> getSysRoleIdListByObj(SysRole sysRole){
        return this.selectList("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleIdListByObj",sysRole);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysRoleIdListPage(SysRole sysRole,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysRole);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysRoleMapper.getSysRoleIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysRoleIdPage(SysRole sysRole,PageEntity page) {
        Integer objectscount = getSysRoleCountByObj(sysRole);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysRoleIdListPage(sysRole,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
