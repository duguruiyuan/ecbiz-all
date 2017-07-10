package  com.mlj.ecbiz.controller.product;

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
import com.mlj.ecbiz.model.product.ProductType;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.product.ProductTypeService;

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
@RequestMapping("/manage/product/producttype")
public class ProductTypeController {

	private static final Logger logger = Logger.getLogger(ProductTypeController.class);

	@Autowired
	private ProductTypeService productTypeService;

	// 路径
	private String toList = "/product/producttype_list.httl";// 列表页
	private String toAdd = "/product/producttype_add.httl";// 添加页面
	private String toEdit = "/product/producttype_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductType query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
//			this.setPage(page);
//			this.getPage().setPageSize(10);
//			if (query == null) {
//				query = new ProductType();
//			}
//			List<ProductType> list = productTypeService.getProductTypePage(query, this.getPage());
//			modelAndView.addObject("query", query);
//			modelAndView.addObject("productTypeList", list);
//			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			logger.error("ProductTypeController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("ProductTypeController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			ProductType productType = productTypeService.getProductTypeById(id);
			modelAndView.addObject(productType);
		} catch (Exception e) {
			logger.error("ProductTypeController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(ProductType productType, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(productType.getId())){
				//productType.setMtime(new Date());
				//if (seuser != null) {
					//productType.setMuser(String.valueOf(seuser.getId()));
				//}
				//productTypeService.updateProductTypeByObj(productType);
			//}else{
				//productType.setCtime(new Date());
				//productType.setMtime(new Date());
				//if (seuser != null) {
					//productType.setCuser(String.valueOf(seuser.getId()));
					//productType.setMuser(String.valueOf(seuser.getId()));
				//}
				productTypeService.addProductType(productType);
			//}
		} catch (Exception e) {
			logger.error("ProductTypeController.edit", e);
		}
		return new RedirectView("/manage/product/producttype/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ProductType query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/product/producttype/list");
		String[] idArray = ids.split(",");
		ProductType productType = new ProductType();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					productType.setId(Long.valueOf(id));
					//productType.setStatus(ProductType.DELETE_STATUS);
					this.productTypeService.updateProductTypeByObj(productType);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("ProductTypeController.delete", e);
		}
		return rv;
	}
}
