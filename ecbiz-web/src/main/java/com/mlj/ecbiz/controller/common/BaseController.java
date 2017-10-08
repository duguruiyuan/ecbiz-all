package com.mlj.ecbiz.controller.common;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.mlj.ecbiz.model.permission.SysLogs;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.service.permission.SysLogsService;

public class BaseController {
	@Autowired
	private SysLogsService sysLogsService;
	public Long addSysLogs(String describe,String operUrl,HttpServletRequest request){
		HttpSession session = request.getSession();
		SysLogs sysLogs=new SysLogs();
		sysLogs.setCreated(new Date());
		sysLogs.setDescribe(describe);
		sysLogs.setIp(request.getRemoteAddr());
		SysUser systemUser = (SysUser)session.getAttribute("user");
		sysLogs.setUserid(systemUser.getUid());
		sysLogs.setUsername(systemUser.getName());
		sysLogs.setOperUrl(operUrl);
		return sysLogsService.addSysLogs(sysLogs);
	}
}
