package com.mlj.ecbiz.service.impl.permission;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mlj.ecbiz.model.permission.SysLogs;
import com.mlj.ecbiz.dao.permission.SysLogsDao;
import com.mlj.ecbiz.service.permission.SysLogsService;
import com.chexun.base.framework.core.entity.PageEntity;
import com.chexun.base.cache.QueryProvider;
/**
 * SysLogs管理接口
 * User: 
 * Date: 2017-10-08
 */
@Service("sysLogsService")
public class SysLogsServiceImpl implements SysLogsService{

 	@Autowired
    private SysLogsDao sysLogsDao;
    /**
     * 添加SysLogs
     * @param sysLogs 要添加的SysLogs
     * @return id
     */
    public Long addSysLogs(SysLogs sysLogs){
    	Long res = sysLogsDao.addSysLogs(sysLogs);
    	return res;
    }
	public Long insertSysLogs(SysLogs sysLogs){
		Long res = sysLogsDao.insertSysLogs(sysLogs);
		
    	return res;
	}
    /**
     * 根据id删除一个SysLogs
     * @param id 要删除的id
     */
    public Long deleteSysLogsById(Long id){
    	return sysLogsDao.deleteSysLogsById(id);
    }
	public Long deleteSysLogsByObj(SysLogs sysLogs){
        return sysLogsDao.deleteSysLogsByObj(sysLogs);
    }
    public Long deleteSysLogsByIdList(List<Long > ids){
    	
    	return sysLogsDao.deleteSysLogsByIdList(ids);
    }
    /**
     * 修改SysLogs
     * @param sysLogs 要修改的SysLogs
     */
    public Long updateSysLogs(SysLogs sysLogs){
     	return sysLogsDao.updateSysLogs(sysLogs);
    }
    
    public Long updateSysLogsByObj(SysLogs sysLogs){
    	return sysLogsDao.updateSysLogsByObj(sysLogs);
    }
    
    public Long updateSysLogsByObjAndConditions(SysLogs s,SysLogs c){
    	return sysLogsDao.updateSysLogsByObjAndConditions(s,c);
    }
	public void batchUpdateSysLogs(List<SysLogs> records){
		sysLogsDao.batchUpdateSysLogs(records);
	}
    /**
     * 根据id获取单个SysLogs对象
     * @param id 要查询的id
     * @return SysLogs
     */
    
    public Integer getSysLogsCountByObj(SysLogs sysLogs){
    	return sysLogsDao.getSysLogsCountByObj(sysLogs);
    }
    


    public SysLogs getSysLogsById(Long id){
    	return sysLogsDao.getSysLogsById( id);
    }
    
     public SysLogs getSysLogsByObj(SysLogs sysLogs) {
        return sysLogsDao.getSysLogsByObj(sysLogs);
    }


    
    public List<SysLogs> getSysLogsListByObj(SysLogs sysLogs){
        return sysLogsDao.getSysLogsListByObj(sysLogs);
    }
    public List<SysLogs> getSysLogsListPage(SysLogs sysLogs,Integer offset,Integer limit){
        return sysLogsDao.getSysLogsListPage(sysLogs,offset,limit);
    }
    
    public List<SysLogs> getSysLogsPage(SysLogs sysLogs,PageEntity page) {
        return sysLogsDao.getSysLogsPage(sysLogs,page);
    }
}