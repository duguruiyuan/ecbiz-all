package com.mlj.ecbiz.dao.impl.permission;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.mlj.ecbiz.model.permission.SysLogs;
import com.mlj.ecbiz.dao.permission.SysLogsDao;
import org.springframework.stereotype.Repository;
import com.chexun.base.framework.core.dao.impl.common.GenericDaoImpl;
import com.chexun.base.common.util.BeanMapConvertor;
import com.chexun.base.framework.core.entity.PageEntity;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
/**
 *
 * SysLogs
 * User:
 * Date: 2017-10-08
 */
 @Repository("sysLogsDao")
public class SysLogsDaoImpl extends GenericDaoImpl implements SysLogsDao{

    public Long addSysLogs(SysLogs sysLogs) {
        return this.insert("com.mlj.ecbiz.model.permission.SysLogsMapper.createSysLogs",sysLogs);
    }
	public Long insertSysLogs(SysLogs sysLogs){
		return this.insert("com.mlj.ecbiz.model.permission.SysLogsMapper.insertSysLogs",sysLogs);
	}
    public Long deleteSysLogsById(Long id){
        return this.delete("com.mlj.ecbiz.model.permission.SysLogsMapper.deleteSysLogsById",id);
    }
    public Long deleteSysLogsByObj(SysLogs sysLogs){
        return this.delete("com.mlj.ecbiz.model.permission.SysLogsMapper.deleteSysLogsByObj",sysLogs);
    }
    
	public Long deleteSysLogsByIdList(List<Long > ids){
		return this.delete("com.mlj.ecbiz.model.permission.SysLogsMapper.deleteSysLogsByIdList",ids);
	}
    public Long updateSysLogs(SysLogs sysLogs) {
        return this.update("com.mlj.ecbiz.model.permission.SysLogsMapper.updateSysLogs",sysLogs);
    }
    
    public Long updateSysLogsByObj(SysLogs sysLogs){
    	return this.update("com.mlj.ecbiz.model.permission.SysLogsMapper.updateSysLogsByObj",sysLogs);
    }
    
    public Long updateSysLogsByObjAndConditions(SysLogs s,SysLogs c){
    	Map<String,SysLogs> map = new HashMap<String,SysLogs>();
    	map.put("s",s);
    	map.put("c",c);
    	return this.update("com.mlj.ecbiz.model.permission.SysLogsMapper.updateSysLogsByObjAndConditions",map);
    }
	public void batchUpdateSysLogs(List<SysLogs> records){
		this.update("com.mlj.ecbiz.model.permission.SysLogsMapper.batchUpdateSysLogs",records);
	}
    public SysLogs getSysLogsById(Long id) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsById",id);
    }
    
    public SysLogs getSysLogsByObj(SysLogs sysLogs) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsByObj",sysLogs);
    }

    
    public List<SysLogs> getSysLogsListByObj(SysLogs sysLogs){
        return this.selectList("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsListByObj",sysLogs);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<SysLogs> getSysLogsListPage(SysLogs sysLogs,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysLogs);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public Integer getSysLogsCountByObj(SysLogs sysLogs){
    	return this.selectOne("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsCountByObj", sysLogs);
    }
    
    public List<SysLogs> getSysLogsPage(SysLogs sysLogs,PageEntity page) {
        Integer objectscount = getSysLogsCountByObj(sysLogs);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysLogsListPage(sysLogs,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
    
    
    
     /**
    *以下为缓存查询用
    */
    public Long getSysLogsIdByObj(SysLogs sysLogs) {
        return this.selectOne("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsIdByObj",sysLogs);
    }

    public List<Long> getSysLogsIdList(SysLogs sysLogs) {
        return this.selectList("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsIdList",sysLogs);
    }
    
    public List<Long> getSysLogsIdListByObj(SysLogs sysLogs){
        return this.selectList("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsIdListByObj",sysLogs);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Long> getSysLogsIdListPage(SysLogs sysLogs,Integer offset,Integer limit){
    	try {
			Map map = BeanMapConvertor.convertBean(sysLogs);
			map.put("offset",offset);
    		map.put("limit",limit);
        	return this.selectList("com.mlj.ecbiz.model.permission.SysLogsMapper.getSysLogsIdListByMap",map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
    }
    public List<Long> getSysLogsIdPage(SysLogs sysLogs,PageEntity page) {
        Integer objectscount = getSysLogsCountByObj(sysLogs);
        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return getSysLogsIdListPage(sysLogs,(page.getCurrentPage() - 1) * page.getPageSize(),page.getPageSize());
        }
    }
}
