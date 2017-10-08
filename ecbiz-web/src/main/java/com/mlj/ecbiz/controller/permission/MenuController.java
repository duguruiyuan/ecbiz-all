package com.mlj.ecbiz.controller.permission;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.ferrari.exception.ActionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.controller.common.BaseController;
import com.mlj.ecbiz.model.permission.SysOperation;
import com.mlj.ecbiz.model.permission.SysResource;
import com.mlj.ecbiz.model.permission.SysRole;
import com.mlj.ecbiz.model.permission.SysUser;
import com.mlj.ecbiz.service.permission.SysMenuService;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("/loadMenuTree")
	public ModelAndView loadMenuTree(HttpServletRequest request, HttpServletResponse response, SysUser query, @ModelAttribute("page") PageEntity page) {
		try{
			HttpSession session = request.getSession();
			String roleId = request.getParameter("roleIds");
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
//				List<SysRole> roleList=new ArrayList<SysRole>();
//				SysRole sysRole=new SysRole();
//				sysRole.setRId(1L);
//				roleList.add(sysRole);
//				session.setAttribute("roleList", roleList);
				List<SysRole> roleList1 =(List<SysRole>) session.getAttribute("roleList");
				allResourceOperationMap = sysMenuService.getRolePermissionIsNotAdmin(roleList1);
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
}
