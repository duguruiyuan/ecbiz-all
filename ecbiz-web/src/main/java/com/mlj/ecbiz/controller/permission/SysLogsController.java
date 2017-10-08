package  com.mlj.ecbiz.controller.permission;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chexun.base.common.util.string.StringUtils;
import com.chexun.base.framework.core.controller.BaseController;
//import com.chexun.partner.constant.CoreConstant;
import com.chexun.base.framework.core.entity.PageEntity;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.mlj.ecbiz.model.permission.SysLogs;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysLogsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/syslogs")
public class SysLogsController {

	private static final Logger logger = Logger.getLogger(SysLogsController.class);

	@Autowired
	private SysLogsService sysLogsService;

	// 路径
	private String toList = "/permission/sysLogList.httl";// 列表页
	private String toAdd = "/permission/syslogs_add.httl";// 添加页面
	private String toEdit = "/permission/syslogs_edit.httl";// 修改页
	
	private String toClear = "/permission/sysLogClear.httl";// 清除日志

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysLogs query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			if (query == null) {
				query = new SysLogs();
			}
			List<SysLogs> list = sysLogsService.getSysLogsPage(query, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("sysLogsList", list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("SysLogsController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}
	@RequestMapping("/logClear")
	public ModelAndView logClear(HttpServletRequest request, HttpServletResponse response, SysLogs query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toClear);
		
		return modelAndView;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysLogsController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SysLogs sysLogs = sysLogsService.getSysLogsById(id);
			modelAndView.addObject(sysLogs);
		} catch (Exception e) {
			logger.error("SysLogsController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(SysLogs sysLogs, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(sysLogs.getId())){
				//sysLogs.setMtime(new Date());
				//if (seuser != null) {
					//sysLogs.setMuser(String.valueOf(seuser.getId()));
				//}
				//sysLogsService.updateSysLogsByObj(sysLogs);
			//}else{
				//sysLogs.setCtime(new Date());
				//sysLogs.setMtime(new Date());
				//if (seuser != null) {
					//sysLogs.setCuser(String.valueOf(seuser.getId()));
					//sysLogs.setMuser(String.valueOf(seuser.getId()));
				//}
				sysLogsService.addSysLogs(sysLogs);
			//}
		} catch (Exception e) {
			logger.error("SysLogsController.edit", e);
		}
		return new RedirectView("/manage/permission/syslogs/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SysLogs query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/syslogs/list");
		String[] idArray = ids.split(",");
		SysLogs sysLogs = new SysLogs();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysLogs.setId(Long.valueOf(id));
					//sysLogs.setStatus(SysLogs.DELETE_STATUS);
					this.sysLogsService.updateSysLogsByObj(sysLogs);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SysLogsController.delete", e);
		}
		return rv;
	}
}
