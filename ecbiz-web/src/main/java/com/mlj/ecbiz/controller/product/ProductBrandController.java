package  com.mlj.ecbiz.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONArray;
//import com.chexun.partner.constant.CoreConstant;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.product.PhotoDetail;
//import com.chexun.partner.web.back.controllers.system.SysBaseController;
import com.mlj.ecbiz.model.product.ProductBrand;
import com.mlj.ecbiz.service.product.PhotoDetailService;
//import com.chexun.partner.model.system.SysUser;
import com.mlj.ecbiz.service.product.ProductBrandService;

@Controller
@RequestMapping("/productbrand")
public class ProductBrandController {

	private static final Logger logger = Logger.getLogger(ProductBrandController.class);

	@Autowired
	private ProductBrandService productBrandService;
	@Autowired
	private PhotoDetailService photoDetailService;

	// 路径
	private String toList = "/product/brandList.httl";// 列表页
	private String toAdd = "/product/brandAdd.httl";// 添加页面
	private String toEdit = "/product/brandEdit.httl";// 修改页
	private String toUploadImage = "/common/uploadImage.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductBrand query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			String letter=request.getParameter("letter");
			String seachType= request.getParameter("seachType");
			String keyword=request.getParameter("keyword");
			Map map=new HashMap();
			map.put("letter", letter);
			map.put("seachType", seachType);
			map.put("keyword",keyword);
//			if(!"".equals(letter)){
//				map.put("letter", letter);
//			}
			page.setPageSize(2);
			List<ProductBrand> list = productBrandService.getProductBrandPage(map, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("productBrandList", list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("ProductBrandController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}
	@RequestMapping(value="/uploadImage",method=RequestMethod.GET)
	public ModelAndView toUploadImage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toUploadImage);
		//modelAndView.addObject("actionUrl","");
		return modelAndView;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("ProductBrandController.toAdd", e);
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/addsave")
	public String addsave(HttpServletRequest request,ProductBrand productBrand) {
		Long ret = -1L;
		Long num=productBrandService.addProductBrand(productBrand);
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
			ProductBrand productBrand = productBrandService.getProductBrandById(id);
			PhotoDetail photoDetail=photoDetailService.getPhotoDetailById(Long.valueOf(productBrand.getBrandLogo()));
			modelAndView.addObject(productBrand);
			modelAndView.addObject(photoDetail);
		} catch (Exception e) {
			logger.error("ProductBrandController.toEdit", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public RedirectView save(ProductBrand productBrand, HttpServletRequest request) {
		try {
			//SysUser seuser = (SysUser) this.getSessionAttribute(request, CoreConstant.SYS_USER_SESSION_NAME);
			//if(StringUtils.isNotEmpty(productBrand.getId())){
				//productBrand.setMtime(new Date());
				//if (seuser != null) {
					//productBrand.setMuser(String.valueOf(seuser.getId()));
				//}
				//productBrandService.updateProductBrandByObj(productBrand);
			//}else{
				//productBrand.setCtime(new Date());
				//productBrand.setMtime(new Date());
				//if (seuser != null) {
					//productBrand.setCuser(String.valueOf(seuser.getId()));
					//productBrand.setMuser(String.valueOf(seuser.getId()));
				//}
				productBrandService.addProductBrand(productBrand);
			//}
		} catch (Exception e) {
			logger.error("ProductBrandController.edit", e);
		}
		return new RedirectView("/manage/product/productbrand/list");
		
	}

	@ResponseBody 
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public JSONArray del(Long id) {
		JSONArray json = new JSONArray();
		try {
			Long num=productBrandService.deleteProductBrandById(id);
			json.add(num);
		} catch (Exception e) {
			logger.error("PhoneRecordsController.toAdd", e);
		}
		return json;
	}
}
