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
import com.mlj.ecbiz.model.trade.TradeInfo;
import com.mlj.ecbiz.model.trade.TradeOrders;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysUserService;

import org.apache.log4j.Logger;
import org.ferrari.utils.DateUtils;
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
@RequestMapping("/power")
public class PowerManageController {

	private static final Logger logger = Logger.getLogger(PowerManageController.class);

	@Autowired
	private SysUserService sysUserService;

	// 路径
	private String toList = "/permission/powermanage.httl";// 列表页
	private String toAdd = "/permission/sysUserAdd.httl";// 添加页面
	private String toEdit = "/permission/sysuser_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			List<SysUser> list = sysUserService.getSysUserPage(query,page);
			
			modelAndView.addObject("sysUserList", list);
		} catch (Exception e) {
			logger.error("SysUserController.listAll", e);
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
	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(SysUser sysUser,String payTimes, HttpServletRequest request) {
		Long ret = -1L;
		sysUser.setCreated(new Date());
		Long num=sysUserService.addSysUser(sysUser);
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
	public RedirectView delete(String ids, HttpServletRequest request, SysUser query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/permission/sysuser/list");
		String[] idArray = ids.split(",");
		SysUser sysUser = new SysUser();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					sysUser.setUid(Long.valueOf(id));
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
