package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.dao.permission.SysPermissionDao;

import org.springframework.stereotype.Repository;

import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysPermission
 * User:
 * Date: 2017-10-03
 */
 @Repository("sysPermissionDao")
public class SysPermissionDaoImpl extends GenericDaoImpl implements SysPermissionDao{

    public Long addSysPermission(SysPermission sysPermission) {
        return this.insert("com.mlj.ecbiz.model.permission.SysPermissionMapper.createSysPermission",sysPermission);
    }
	public Long insertSysPermission(SysPermission sysPermission){
		return this.insert("com.mlj.ecbiz.model.permission.SysPermissionMapper.insertSysPermission",sysPermission);
	}
    public Long deleteSysPermissionById(Long id){
        return this.delete("com.mlj.ecbiz.model.permission.SysPermissionMapper.deleteSysPermissionById",id);
    }
    public Long deleteSysPermissionByObj(SysPermission sysPermission){
        return this.delete("com.mlj.ecbiz.model.permission.SysPermissionMapper.deleteSysPermissionByObj",sysPermission);
    }
    
	public Long deleteSysPermissionByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysPermissionMapper.deleteSysPermissionByIdList",ids);
	}
    public Long updateSysPermission(SysPermission sysPermission) {
        return this.update("com.mlj.ecbiz.model.permission.SysPermissionMapper.updateSysPermission",sysPermission);
    }
    
    public Long updateSysPermissionByObj(SysPermission sysPermission){
    	return this.update("com.mlj.ecbiz.model.permission.SysPermissionMapper.updateSysPermissionByObj",sysPermission);
    }
    
    public Long updateSysPermissionByObjAndConditions(SysPermission s,SysPermission c){
    	Map<String,SysPermission> map = new HashMap<String,SysPermission>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysPermissionMapper.updateSysPermissionByObjAndConditions",map);
    }
	public void batchUpdateSysPermission(List<SysPermission> records){
		this.update("com.mlj.ecbiz.model.permission.SysPermissionMapper.batchUpdateSysPermission",records);
	}
    public SysPermission getSysPermissionById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionById",id);
    }
    
    public SysPermission getSysPermissionByObj(SysPermission sysPermission) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionByObj",sysPermission);
    }

    
    public List<SysPermission> getSysPermissionListByObj(SysPermission sysPermission){
        return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionListByObj",sysPermission);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysPermission> getSysPermissionListPage(SysPermission sysPermission,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysPermission);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysPermissionCountByObj(SysPermission sysPermission){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionCountByObj", sysPermission);
    }
    
    public List<SysPermission> getSysPermissionPage(SysPermission sysPermission,PageEntity page) {
        Integer objectscount = getSysPermissionCountByObj(sysPermission);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysPermissionListPage(sysPermission,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysPermissionIdByObj(SysPermission sysPermission) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionIdByObj",sysPermission);
    }

    public List<Long> getSysPermissionIdList(SysPermission sysPermission) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionIdList",sysPermission);
    }
    
    public List<Long> getSysPermissionIdListByObj(SysPermission sysPermission){
        return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionIdListByObj",sysPermission);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysPermissionIdListPage(SysPermission sysPermission,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysPermission);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getSysPermissionIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysPermissionIdPage(SysPermission sysPermission,PageEntity page) {
        Integer objectscount = getSysPermissionCountByObj(sysPermission);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysPermissionIdListPage(sysPermission,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
	@Override
	public List<?> getAllPermission() {
		// TODO Auto-generated method stub
		 return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getAllPermission","");
	}
	@Override
	public List<?> getAllPermissionByPids(List<SysRolePermission> list) {
		// TODO Auto-generated method stub
		 return this.selectList("com.mlj.ecbiz.model.permission.SysPermissionMapper.getAllPermissionByPids",list);
	}
}
