package  com.mlj.ecbiz.controller.product;
import java.util.Collection;
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
import com.mlj.ecbiz.model.product.ProductCategory;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.product.ProductCategoryService;

@Controller
@RequestMapping("/productcategory")
public class ProductCategoryController {
	private static final Logger logger = Logger.getLogger(ProductCategoryController.class);
	@Autowired
	private ProductCategoryService productCategoryService;
	// 路径                                                                                        
	private String toList = "/product/categoryList.httl";// 列表页
	private String toAdd = "/product/categoryAdd.httl";// 添加页面
	private String toEdit = "/product/categoryEdit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductCategory query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			List<ProductCategory> list = productCategoryService.getProductCategoryListByObj(null);
			modelAndView.addObject("categoryList", list);
		} catch (Exception e) {
			logger.error("ProductCategoryController.listAll", e);
		}
		return modelAndView;
	}
	@ResponseBody 
	@RequestMapping(value="/catlist",method=RequestMethod.POST)
	public JSONArray catlist(String parentId) {
		JSONArray json = new JSONArray();
		try {
			ProductCategory productCategory=productCategoryService.findCategoryListByCategory();
			json.add(productCategory);
		} catch (Exception e) {
			logger.error("PhoneRecordsController.toAdd", e);
		}
		return json;
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/addsave")
	public String addsave(HttpServletRequest request,ProductCategory proCat) {
		Long ret = -1L;
		Long num=productCategoryService.addProductCategory(proCat);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductCategoryController.toAdd", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
			modelAndView.addObject(productCategory);
		} catch (Exception e) {
			logger.error("ProductCategoryController.toEdit", e);
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/editsave")
	public String editsave(HttpServletRequest request,ProductCategory proCat) {
		Long ret = -1L;
		Long num=productCategoryService.updateProductCategory(proCat);
		try {
			if(num>0){
				ret = num;
			}
		} catch (Exception e) {
			logger.error("ProductCategoryController.toAdd", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	@ResponseBody 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public JSONArray del(Long id) {
		JSONArray json = new JSONArray();
		try {
			Long num=productCategoryService.deleteProductCategoryById(id);
			json.add(num);
		} catch (Exception e) {
			logger.error("PhoneRecordsController.toAdd", e);
		}
		return json;
	}
	

//	@RequestMapping(value="/save",method=RequestMethod.POST)
//	public RedirectView save(ProductCategory productCategory, HttpServletRequest request) {
//		try {
//			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
//			//if(StringUtils.isNotEmpty(productCategory.getId())){
//				//productCategory.setMtime(new Date());
//				//if (seuser != null) {
//					//productCategory.setMuser(String.valueOf(seuser.getId()));
//				//}
//				//productCategoryService.updateProductCategoryByObj(productCategory);
//			//}else{
//				//productCategory.setCtime(new Date());
//				//productCategory.setMtime(new Date());
//				//if (seuser != null) {
//					//productCategory.setCuser(String.valueOf(seuser.getId()));
//					//productCategory.setMuser(String.valueOf(seuser.getId()));
//				//}
//				productCategoryService.addProductCategory(productCategory);
//			//}
//		} catch (Exception e) {
//			logger.error("ProductCategoryController.edit", e);
//		}
//		return new RedirectView("/manage/product/productcategory/list");
//		
//	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ProductCategory query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/product/productcategory/list");
		String[] idArray = ids.split(",");
		ProductCategory productCategory = new ProductCategory();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					productCategory.setId(Long.valueOf(id));
					//productCategory.setStatus(ProductCategory.DELETE_STATUS);
					this.productCategoryService.updateProductCategoryByObj(productCategory);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("ProductCategoryController.delete", e);
		}
		return rv;
	}
}
