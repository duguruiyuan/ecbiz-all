package com.mlj.ecbiz.service.permission;

import java.util.List;
import com.mlj.ecbiz.model.permission.SysLogs;
import com.chexun.base.framework.core.entity.PageEntity; 
/**
 * SysLogs管理接口
 * User: 
 * Date: 2017-10-08
 */
public interface SysLogsService {

    /**
     * 添加SysLogs
     * @param sysLogs 要添加的SysLogs
     * @return id
     */
    public Long addSysLogs(SysLogs sysLogs);
	public Long insertSysLogs(SysLogs sysLogs);
    /**
     * 根据id删除一个SysLogs
     * @param id 要删除的id
     */
    public Long deleteSysLogsById(Long id);
	public Long deleteSysLogsByObj(SysLogs sysLogs);
    public Long deleteSysLogsByIdList(List<Long > ids);
    /**
     * 修改SysLogs
     * @param sysLogs 要修改的SysLogs
     */
    public Long updateSysLogs(SysLogs sysLogs);
    public Long updateSysLogsByObj(SysLogs sysLogs);
    public Long updateSysLogsByObjAndConditions(SysLogs s,SysLogs c);
	public void batchUpdateSysLogs(List<SysLogs> records);
    /**
     * 根据id获取单个SysLogs对象
     * @param id 要查询的id
     * @return SysLogs
     */
    public SysLogs getSysLogsById(Long id);
	public SysLogs getSysLogsByObj(SysLogs sysLogs);
    /**
     * 根据条件获取SysLogs列表
     * @param sysLogs 查询条件
     * @return List<SysLogs>
     */
    public List<SysLogs> getSysLogsListByObj(SysLogs sysLogs);
    public List<SysLogs> getSysLogsListPage(SysLogs sysLogs,Integer offset,Integer limit);
    public Integer getSysLogsCountByObj(SysLogs sysLogs);
    public List<SysLogs> getSysLogsPage(SysLogs sysLogs,PageEntity page);
    
}