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
@RequestMapping("/paramConfig")
public class ParamConfigController {

	private static final Logger logger = Logger.getLogger(ParamConfigController.class);

	@Autowired
	private ProductTypeService productTypeService;

	// 路径
	private String toEdit = "/product/paramConfig.httl";// 修改页

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			//ProductType productType = productTypeService.getProductTypeById(id);
			//modelAndView.addObject(productType);
		} catch (Exception e) {
			logger.error("ParamConfigController.toEdit", e);
		}
		return modelAndView;
	}

}
