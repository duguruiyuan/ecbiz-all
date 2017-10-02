package  com.mlj.ecbiz.controller.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

//import com.chexun.partner.constant.CoreConstant;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.permission.SysRole;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.model.permission.SysUserRole;
import com.mlj.ecbiz.service.permission.SysRoleService;
import com.mlj.ecbiz.service.permission.SysUserRoleService;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysUserService;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {

	private static final Logger logger = Logger.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	// 路径
	private String toList = "/permission/sysUserList.httl";// 列表页
	private String toAdd = "/permission/sysUserAdd.httl";// 添加页面
	private String toEdit = "/permission/sysUserEdit.httl";// 修改页
	private String toPopedom = "/permission/popedom.httl";// 修改页
	
	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			page.setPageSize(2);
			page.setPageSize(10);
			if (query == null) {
				query = new SysUser();
			}
			List<SysUser> list = sysUserService.getSysUserPage(query,page);
			List<SysUser> sysUserlist =new ArrayList<SysUser>();
			for(SysUser sysUser:list){
				SysUserRole sysUserRole=new SysUserRole();
				sysUserRole.setUId(sysUser.getUid());
				List<SysUserRole> sysUserRoleList=sysUserRoleService.getSysUserRoleListByObj(sysUserRole);
				String rnames="";
				for(SysUserRole userRole:sysUserRoleList){
					SysRole sysRole=sysRoleService.getSysRoleById(userRole.getRId());
					if(null!=sysRole){
						rnames=rnames+sysRole.getRName()+",";
					}
				}
				sysUser.setRName(rnames);
				sysUserlist.add(sysUser);
			}
			modelAndView.addObject("query", query);
			modelAndView.addObject("sysUserList", sysUserlist);
			modelAndView.addObject("page", page);
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
	
	@RequestMapping(value="/popedom",method=RequestMethod.GET)
	public ModelAndView toPopedom(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toPopedom);
		try {
			SysRole sysRole=new SysRole();
			List<SysRole>srList=new ArrayList<SysRole>();
			List<SysRole> sysRoleList=sysRoleService.findALLRole(sysRole);
			SysUserRole sysUserRole=new SysUserRole();
			sysUserRole.setUId(id);
			List<SysUserRole> sysUserRoleList=sysUserRoleService.getSysUserRoleListByObj(sysUserRole);
			for(SysUserRole sysurole:sysUserRoleList){
				srList.add(sysRoleService.getSysRoleById(sysurole.getRId()));
				sysRoleList.remove(sysRoleService.getSysRoleById(sysurole.getRId()));
			}
			modelAndView.addObject("roleList",srList);
			modelAndView.addObject("sysRoleList",sysRoleList);
			modelAndView.addObject("uId", id);
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return modelAndView;
	}
	@RequestMapping(value="/savepopedom",method=RequestMethod.POST)
	public ModelAndView savePopedom(Long[] popedomIds,Long uId,HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		try {
			SysUserRole sysUserRole=new SysUserRole();
			for(Long p:popedomIds){
				SysRole sysRole=sysRoleService.getSysRoleById(p);
				sysUserRole.setRId(p);
				sysUserRole.setUId(uId);
				sysUserRole.setRName(sysRole.getRName());
				SysUserRole sur=sysUserRoleService.getSysUserRoleByObj(sysUserRole);
				if(null!=sur){
					sysUserRoleService.updateSysUserRole(sysUserRole);
				}else{
					sysUserRoleService.insertSysUserRole(sysUserRole);
				}
			}
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return this.listAll(request, response, query, page);
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
	@ResponseBody
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public String editsave(SysUser sysUser,String payTimes, HttpServletRequest request) {
		Long ret = -1L;
		sysUser.setCreated(new Date());
		Long num=sysUserService.updateSysUser(sysUser);
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
