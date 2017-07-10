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
import com.mlj.ecbiz.model.permission.SysUser;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysUserService;

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
@RequestMapping("/manage/permission/sysuser")
public class SysUserController {

	private static final Logger logger = Logger.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;

	// 路径
	private String toList = "/permission/sysuser_list.httl";// 列表页
	private String toAdd = "/permission/sysuser_add.httl";// 添加页面
	private String toEdit = "/permission/sysuser_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
//			this.setPage(page);
//			this.getPage().setPageSize(10);
			if (query == null) {
				query = new SysUser();
			}
//			List<SysUser> list = sysUserService.getSysUserPage(query, this.getPage());
//			modelAndView.addObject("query", query);
//			modelAndView.addObject("sysUserList", list);
//			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("SysUserController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysUserController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SysUser sysUser = sysUserService.getSysUserById(id);
			modelAndView.addObject(sysUser);
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(SysUser sysUser, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(sysUser.getId())){
				//sysUser.setMtime(new Date());
				//if (seuser != null) {
					//sysUser.setMuser(String.valueOf(seuser.getId()));
				//}
				//sysUserService.updateSysUserByObj(sysUser);
			//}else{
				//sysUser.setCtime(new Date());
				//sysUser.setMtime(new Date());
				//if (seuser != null) {
					//sysUser.setCuser(String.valueOf(seuser.getId()));
					//sysUser.setMuser(String.valueOf(seuser.getId()));
				//}
				sysUserService.addSysUser(sysUser);
			//}
		} catch (Exception e) {
			logger.error("SysUserController.edit", e);
		}
		return new RedirectView("/manage/permission/sysuser/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SysUser query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sysuser/list");
		String[] idArray = ids.split(",");
		SysUser sysUser = new SysUser();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysUser.setId(Long.valueOf(id));
					//sysUser.setStatus(SysUser.DELETE_STATUS);
					this.sysUserService.updateSysUserByObj(sysUser);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SysUserController.delete", e);
		}
		return rv;
	}
}
