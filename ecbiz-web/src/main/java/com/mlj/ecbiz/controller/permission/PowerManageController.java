package  com.mlj.ecbiz.controller.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.ferrari.exception.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.alibaba.fastjson.JSONArray;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.controller.common.BaseController;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysPermission;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.service.permission.SysMenuService;
import com.mlj.ecbiz.service.permission.SysOperationService;
import com.mlj.ecbiz.service.permission.SysPermissionService;
import com.mlj.ecbiz.service.permission.SysResourceService;
import com.mlj.ecbiz.service.permission.SysRolePermissionService;
import com.mlj.ecbiz.service.permission.SysRoleService;
import com.mlj.ecbiz.service.permission.SysUserService;
@Controller
@RequestMapping("/power")
public class PowerManageController  extends BaseController{

	private static final Logger logger = Logger.getLogger(PowerManageController.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysResourceService sysResourceService;
	
	@Autowired
	private SysOperationService sysOperationService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysPermissionService sysPermissionService;
	@Autowired
	private SysRolePermissionService sysRolePermissionService;
	@Autowired
	private SysMenuService sysMenuService;
	
	// 路径
	private String toList = "/permission/powermanage.httl";// 列表页
	private String toAdd = "/permission/sysUserAdd.httl";// 添加页面
	private String toEdit = "/permission/powermanage.httl";// 修改页
	private String toRolePermissionList = "/permission/rolePermissionList.httl";// 分配权限
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView =new ModelAndView(toList);
		return modelAndView;
	}
	@RequestMapping("/rolePermissionList")
	public ModelAndView rolePermissionList(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView =new ModelAndView(toRolePermissionList);
		String roleId = request.getParameter("roleId");
		modelAndView.addObject("roleId", roleId);
		return modelAndView;
	}
	@RequestMapping("/loadRolePermissionTree")
	public ModelAndView loadRolePermissionTree(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		try{
			String roleId = request.getParameter("roleId");
			HttpSession session = request.getSession();
			SysUser sysUser=new SysUser();
			sysUser.setName("master");
			session.setAttribute("user", sysUser);
			SysUser systemUser = (SysUser)session.getAttribute("user");
			//用户所有权限
			Map<SysResource,List<SysOperation>> allResourceOperationMap = null;
			//单个角色的权限
			Map<SysResource,List<SysOperation>> singleResourceOperationMap;
			
			if(systemUser.getName().equals("master")){
				allResourceOperationMap = sysMenuService.getAllPermission();
			}else{
				List<SysRole> roleList =(List<SysRole>) session.getAttribute("roleList");
//				allResourceOperationMap = getSysRoleService().getRolePermissionIsNotAdmin(roleList);
			}
			
			singleResourceOperationMap = sysMenuService.getPermissionByRoleId(roleId);			
			response.setHeader("Cache-Control", "no-store");
			response.setDateHeader("Expires", 0);
			response.setContentType("text/xml; charset=UTF-8");
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = document.createElement("tree");
			
			if(singleResourceOperationMap==null){
				singleResourceOperationMap = new HashMap<SysResource,List<SysOperation>>();
			}
			root = sysMenuService.recursiveRolePermission(allResourceOperationMap,singleResourceOperationMap,root,0L,document);					
			
			
			document.appendChild(root);
			DOMSource doms = new DOMSource(document);
			StreamResult sr = new StreamResult(response.getOutputStream());
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", "UTF-8");
			t.transform(doms, sr);
		}catch(Exception ex){
			throw new ActionException(ex.getMessage());
		}
		return null;
	}

	@RequestMapping("/loadResourceTree")
	public ModelAndView loadResourceTree(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		
		try {
			List<SysUser> list = sysUserService.getSysUserPage(query,page);
			HttpSession session = request.getSession();
			SysUser sysUser=new SysUser();
			sysUser.setName("master");
			session.setAttribute("user", sysUser);
			SysUser systemUser = (SysUser)session.getAttribute("user");
			String roleId = request.getParameter("roleId")==null?"1":request.getParameter("roleId");
			Map<SysResource,List<SysOperation>> resourceOperationMap;
			
			if(!systemUser.getName().equals("master")){
				List<SysRole> roleList =(List<SysRole>) session.getAttribute("roleList");
				resourceOperationMap = sysMenuService.getRolePermissionIsNotAdmin(roleList);
			}else{
				resourceOperationMap = sysMenuService.getAllPermission();
			}
			Set<SysResource> resourceSet = resourceOperationMap.keySet();
			if(resourceSet.size()>0){
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setContentType("text/xml; charset=UTF-8");
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = document.createElement("tree");
				List<SysResource> resourceList = new ArrayList<SysResource>();
				resourceList.addAll(resourceSet);
				root = sysMenuService.recursiveResource(resourceList,root,Long.valueOf(0),document);							
				document.appendChild(root);
				DOMSource doms = new DOMSource(document);
				StreamResult sr = new StreamResult(response.getOutputStream());
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				t.setOutputProperty("encoding", "UTF-8");
				t.transform(doms, sr);
			}
		} catch (Exception e) {
			logger.error("SysUserController.listAll", e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public JSONArray toEdit(Long id,HttpServletRequest request) {
		JSONArray json = new JSONArray();
		try {
			SysResource sysResource = sysResourceService.getSysResourceById(id);
			json.add(sysResource);
		} catch (Exception e) {
			logger.error("SysUserController.toEdit", e);
		}
		return json;
	}
	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(SysResource sysResource, HttpServletRequest request) {
		Long ret = -1L;
		sysResource.setCreateTime(new Date());
		Long num=sysResourceService.addSysResource(sysResource);
		SysPermission sysPermission=new SysPermission();
		sysPermission.setResourceId(sysResource.getId());
		sysPermissionService.addSysPermission(sysPermission);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}finally{
			addSysLogs("菜单新增操作", "/power/list",request);
		}
		return String.valueOf(ret);
	}
	@ResponseBody
	@RequestMapping(value="/editsave",method=RequestMethod.POST)
	public String editsave(SysResource sysResource, HttpServletRequest request) {
		Long ret = -1L;
		sysResource.setCreateTime(new Date());
		Long num=sysResourceService.updateSysResource(sysResource);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}finally{
			addSysLogs("菜单修改操作", "/power/list",request);
		}
		return String.valueOf(ret);
	}
	@ResponseBody
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public String del(Long id, HttpServletRequest request) {
		Long ret = -1L;
		SysPermission sysPermission=new SysPermission();
		sysPermission.setResourceId(id);
		sysPermissionService.deleteSysPermissionByObj(sysPermission);
		Long num=sysResourceService.deleteSysResourceById(id);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductInfoController.save", e);
			ret = -1L;
		}finally{
			addSysLogs("菜单删除操作", "/power/list",request);
		}
		return String.valueOf(ret);
	}
	
}
