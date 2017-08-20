package  com.mlj.ecbiz.controller.product;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.product.ProductInfo;
import com.mlj.ecbiz.service.product.ProductInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/productinfo")
public class ProductInfoController {

	private static final Logger logger = Logger.getLogger(ProductInfoController.class);
	@Autowired
	private ProductInfoService productInfoService;
	// 路径
	private String toList = "/product/productList.httl";// 列表页
	private String toAdd = "/product/productAdd.httl";// 添加页面
	private String toEdit = "/product/productEdit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, ProductInfo query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			page.setPageSize(2);
			if (query == null) {
				query = new ProductInfo();
			}
			List<ProductInfo> list = productInfoService.getProductInfoPage(query, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("productInfoList", list);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("ProductInfoController.listAll", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("ProductInfoController.toAdd", e);
		}
		return modelAndView;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			ProductInfo productInfo = productInfoService.getProductInfoById(id);
			modelAndView.addObject(productInfo);
		} catch (Exception e) {
			logger.error("ProductInfoController.toEdit", e);
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(ProductInfo productInfo, HttpServletRequest request) {
		Long ret = -1L;
		Long num=productInfoService.addProductInfo(productInfo);
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
	@RequestMapping(value="/editsave")
	public String editsave(HttpServletRequest request,ProductInfo productInfo) {
		Long ret = -1L;
		Long num=productInfoService.updateProductInfo(productInfo);
		try {
			if(num>0){
				ret = num;
			}
		}catch (Exception e){
			logger.error("ProductInfoController.editsave", e);
			ret = -1L;
		}
		return String.valueOf(ret);
	}
	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, ProductInfo query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/product/productinfo/list");
		String[] idArray = ids.split(",");
		ProductInfo productInfo = new ProductInfo();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					productInfo.setId(Long.valueOf(id));
					//productInfo.setStatus(ProductInfo.DELETE_STATUS);
					this.productInfoService.updateProductInfoByObj(productInfo);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("ProductInfoController.delete", e);
		}
		return rv;
	}
}
