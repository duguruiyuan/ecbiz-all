package com.mlj.ecbiz.dao.impl.permission;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.dao.permission.SysRolePermissionDao;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysRolePermission;
/**
 *
 * SysRolePermission
 * User:
 * Date: 2017-10-03
 */
 @Repository("sysRolePermissionDao")
public class SysRolePermissionDaoImpl extends GenericDaoImpl implements SysRolePermissionDao{

    public Long addSysRolePermission(SysRolePermission sysRolePermission) {
        return this.insert("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.createSysRolePermission",sysRolePermission);
    }
	public Long insertSysRolePermission(SysRolePermission sysRolePermission){
		return this.insert("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.insertSysRolePermission",sysRolePermission);
	}
    public Long deleteSysRolePermissionById(Long id){
        return this.delete("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.deleteSysRolePermissionById",id);
    }
    public Long deleteSysRolePermissionByObj(SysRolePermission sysRolePermission){
        return this.delete("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.deleteSysRolePermissionByObj",sysRolePermission);
    }
    
	public Long deleteSysRolePermissionByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.deleteSysRolePermissionByIdList",ids);
	}
    public Long updateSysRolePermission(SysRolePermission sysRolePermission) {
        return this.update("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.updateSysRolePermission",sysRolePermission);
    }
    
    public Long updateSysRolePermissionByObj(SysRolePermission sysRolePermission){
    	return this.update("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.updateSysRolePermissionByObj",sysRolePermission);
    }
    
    public Long updateSysRolePermissionByObjAndConditions(SysRolePermission s,SysRolePermission c){
    	Map<String,SysRolePermission> map = new HashMap<String,SysRolePermission>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.updateSysRolePermissionByObjAndConditions",map);
    }
	public void batchUpdateSysRolePermission(List<SysRolePermission> records){
		this.update("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.batchUpdateSysRolePermission",records);
	}
    public SysRolePermission getSysRolePermissionById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionById",id);
    }
    
    public SysRolePermission getSysRolePermissionByObj(SysRolePermission sysRolePermission) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionByObj",sysRolePermission);
    }

    
    public List<SysRolePermission> getSysRolePermissionListByObj(SysRolePermission sysRolePermission){
        return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionListByObj",sysRolePermission);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysRolePermission> getSysRolePermissionListPage(SysRolePermission sysRolePermission,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysRolePermission);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysRolePermissionCountByObj(SysRolePermission sysRolePermission){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionCountByObj", sysRolePermission);
    }
    
    public List<SysRolePermission> getSysRolePermissionPage(SysRolePermission sysRolePermission,PageEntity page) {
        Integer objectscount = getSysRolePermissionCountByObj(sysRolePermission);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysRolePermissionListPage(sysRolePermission,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysRolePermissionIdByObj(SysRolePermission sysRolePermission) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionIdByObj",sysRolePermission);
    }

    public List<Long> getSysRolePermissionIdList(SysRolePermission sysRolePermission) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionIdList",sysRolePermission);
    }
    
    public List<Long> getSysRolePermissionIdListByObj(SysRolePermission sysRolePermission){
        return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionIdListByObj",sysRolePermission);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysRolePermissionIdListPage(SysRolePermission sysRolePermission,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysRolePermission);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getSysRolePermissionIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysRolePermissionIdPage(SysRolePermission sysRolePermission,PageEntity page) {
        Integer objectscount = getSysRolePermissionCountByObj(sysRolePermission);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysRolePermissionIdListPage(sysRolePermission,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
	public List<?> getPermissionByRoleIds(String roleIds) {
		return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getPermissionByRoleIds",roleIds);
	}
	public List<?> getRolePermissionByRoleIds(List<SysRole> list) {
		return this.selectList("com.mlj.ecbiz.model.permission.SysRolePermissionMapper.getRolePermissionByRoleIds",list);
	}
}
