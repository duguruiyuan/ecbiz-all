package  com.mlj.ecbiz.controller.permission;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysRolePermission;
import com.mlj.ecbiz.service.permission.SysPermissionService;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysRolePermissionService;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
//import com.chexun.partner.constant.CoreConstant;

@Controller
@RequestMapping("/sysrolepermission")
public class SysRolePermissionController {

	private static final Logger logger = Logger.getLogger(SysRolePermissionController.class);

	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@Autowired
	private SysPermissionService sysPermissionService;
	// 路径
	private String toList = "/permission/sysrolepermission_list.httl";// 列表页
	private String toAdd = "/permission/sysrolepermission_add.httl";// 添加页面
	private String toEdit = "/permission/sysrolepermission_edit.httl";// 修改页

	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(HttpServletRequest request) {
		Long ret = -1L;
		Set<Long> permissionIdSet = new HashSet<Long>();
		Set<Long> resourceIdSet = new HashSet<Long>();
		Set<String> parentIdSet = new HashSet<String>();
		String permissionIds=request.getParameter("permissionIds");
		String pids[]=permissionIds.split("#");
		String roleId=request.getParameter("roleId");
		if(null!=pids&&pids.length>0){
			SysRolePermission sysRolePermission=new SysRolePermission();
			sysRolePermission.setRoleId(Long.valueOf(roleId));
			sysRolePermissionService.deleteSysRolePermissionByObj(sysRolePermission);
			for(String permission:pids){
				String pid[]=permission.split(",");
				if(pid.length==2){
					SysPermission sysPermission=new SysPermission();
					sysPermission.setResourceId(Long.valueOf(pid[0]));
					sysPermission.setOperationId(Long.valueOf(pid[1]));
					Long permissionId=sysPermissionService.getSysPermissionListByObj(sysPermission).get(0).getId();
					
					SysRolePermission sysRolePer=new SysRolePermission();
					sysRolePer.setRoleId(Long.valueOf(roleId));
					sysRolePer.setPermissionId(permissionId);
					sysRolePer.setCreateTime(new Date());
					sysRolePermissionService.addSysRolePermission(sysRolePer);
				}
			}
		}
		try {
			ret = 1L;
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
}
