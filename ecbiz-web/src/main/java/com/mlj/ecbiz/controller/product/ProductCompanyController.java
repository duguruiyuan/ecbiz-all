package  com.mlj.ecbiz.controller.product;

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
import com.mlj.ecbiz.model.product.ProductBrand;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.mlj.ecbiz.model.product.ProductCompany;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.product.ProductCompanyService;

@Controller
@RequestMapping("/productcompany")
public class ProductCompanyController{

	private static final Logger logger = Logger.getLogger(ProductCompanyController.class);

	@Autowired
	private ProductCompanyService productCompanyService;

	// 路径
	private String toList = "/product/companyList.httl";// 列表页
	private String toAdd = "/product/companyAdd.httl";// 添加页面
	private String toEdit = "/product/productcompany_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductCompany query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			if (query == null) {
				query = new ProductCompany();
			}
			List<ProductCompany> list = productCompanyService.getProductCompanyPage(query, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("productCompanyList", list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("ProductCompanyController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/addsave")
	public String addsave(ProductCompany productCompany,HttpServletRequest request,ProductBrand productBrand) {
		Long ret = -1L;
		Long num=productCompanyService.addProductCompany(productCompany);
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
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("ProductCompanyController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			ProductCompany productCompany = productCompanyService.getProductCompanyById(id);
			modelAndView.addObject(productCompany);
		} catch (Exception e) {
			logger.error("ProductCompanyController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(ProductCompany productCompany, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(productCompany.getId())){
				//productCompany.setMtime(new Date());
				//if (seuser != null) {
					//productCompany.setMuser(String.valueOf(seuser.getId()));
				//}
				//productCompanyService.updateProductCompanyByObj(productCompany);
			//}else{
				//productCompany.setCtime(new Date());
				//productCompany.setMtime(new Date());
				//if (seuser != null) {
					//productCompany.setCuser(String.valueOf(seuser.getId()));
					//productCompany.setMuser(String.valueOf(seuser.getId()));
				//}
				productCompanyService.addProductCompany(productCompany);
			//}
		} catch (Exception e) {
			logger.error("ProductCompanyController.edit", e);
		}
		return new RedirectView("/manage/product/productcompany/list");
		
	}

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ProductCompany query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/product/productcompany/list");
		String[] idArray = ids.split(",");
		ProductCompany productCompany = new ProductCompany();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					productCompany.setId(Long.valueOf(id));
					//productCompany.setStatus(ProductCompany.DELETE_STATUS);
					this.productCompanyService.updateProductCompanyByObj(productCompany);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("ProductCompanyController.delete", e);
		}
		return rv;
	}
}
