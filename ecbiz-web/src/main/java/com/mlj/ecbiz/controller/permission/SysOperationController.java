package  com.mlj.ecbiz.controller.permission;

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



import com.alibaba.fastjson.JSONArray;
//import com.chexun.partner.constant.CoreConstant;
import com.chexun.base.framework.core.entity.PageEntity;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysResource;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.permission.SysOperationService;
import com.mlj.ecbiz.service.permission.SysPermissionService;

@Controller
@RequestMapping("/sysoperation")
public class SysOperationController {

	private static final Logger logger = Logger.getLogger(SysOperationController.class);

	@Autowired
	private SysOperationService sysOperationService;

	@Autowired
	private SysPermissionService sysPermissionService;
	// 路径
	private String toList = "/permission/sysOperationList.httl";// 列表页
	private String toAdd = "/permission/sysoperation_add.httl";// 添加页面
	private String toEdit = "/permission/sysoperation_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, SysOperation query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			String resourceId=request.getParameter("resourceId");
			SysPermission sysPermission=new SysPermission();
			sysPermission.setResourceId(Long.valueOf(resourceId));
			List<SysPermission> splist=sysPermissionService.getSysPermissionListByObj(sysPermission);
			Long spid[]=new Long[splist.size()];
			int i=0;
			for(SysPermission permission:splist)
			{   
				spid[i]=permission.getOperationId();
				i++;
			}
			if (query == null) {
				query = new SysOperation();
			}
			query.setSpid(spid);
			List<SysOperation> list = sysOperationService.getSysOperationPage(query, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("resourceId", resourceId);
			modelAndView.addObject("sysOperationList", list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("SysOperationController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("SysOperationController.toAdd", e);
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public JSONArray toEdit(Long id,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		try {
			SysOperation sysOperation = sysOperationService.getSysOperationById(id);
			json.add(sysOperation);
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return json;
	}
	

	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(SysOperation sysOperation, HttpServletRequest request) {
		Long ret = -1L;
		String resourceId=request.getParameter("resourceId");
		sysOperation.setCreateTime(new Date());
		sysOperation.setState('1');
		Long num=sysOperationService.addSysOperation(sysOperation);
		Long id=sysOperation.getId();
		SysPermission sysPermission=new SysPermission();
		sysPermission.setOperationId(id);
		sysPermission.setResourceId(Long.valueOf(resourceId));
		List<SysPermission> list=sysPermissionService.getSysPermissionListByObj(sysPermission);
		if(null!=list&&list.size()>0){
			
			sysPermissionService.updateSysPermission(sysPermission);
		}else{
			sysPermissionService.insertSysPermission(sysPermission);
		}
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
	public String editsave(SysOperation sysOperation, HttpServletRequest request) {
		Long ret = -1L;
		sysOperation.setCreateTime(new Date());
		Long num=sysOperationService.updateSysOperation(sysOperation);
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
	@RequestMapping("/delete")
	public String delete(Long id,Long resourceId, HttpServletRequest request, SysOperation query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		Long ret = -1L;
		Long num=sysOperationService.deleteSysOperationById(id);
		SysPermission sysPermission=new SysPermission();
		sysPermission.setOperationId(id);
		sysPermission.setResourceId(resourceId);
		sysPermissionService.deleteSysPermissionByObj(sysPermission);
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
}
