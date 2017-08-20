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
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysUser;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysRoleService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController {

	private static final Logger logger = Logger.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;

	// 路径
	private String toList = "/permission/sysRoleList.httl";// 列表页
	private String toAdd = "/permission/sysRoleAdd.httl";// 添加页面
	private String toEdit = "/permission/roleEdit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysRole query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			page.setPageSize(15);
			if (query == null) {
				query = new SysRole();
			}
			List<SysRole> list = sysRoleService.getSysRolePage(query,page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("sysRoleList", list);
			modelAndView.addObject("page",page);
		} catch (Exception e) {
			logger.error("SysRoleController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysRoleController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			SysRole sysRole = sysRoleService.getSysRoleById(id);
			modelAndView.addObject(sysRole);
		} catch (Exception e) {
			logger.error("SysRoleController.toEdit", e);
		}
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(SysRole sysRole , HttpServletRequest request) {
		Long ret = -1L;
		sysRole.setCreated(new Date());
		Long num=sysRoleService.addSysRole(sysRole);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, SysRole query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sysrole/list");
		String[] idArray = ids.split(",");
		SysRole sysRole = new SysRole();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysRole.setRId(Long.valueOf(id));
					//sysRole.setStatus(SysRole.DELETE_STATUS);
					this.sysRoleService.updateSysRoleByObj(sysRole);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("SysRoleController.delete", e);
		}
		return rv;
	}
}
