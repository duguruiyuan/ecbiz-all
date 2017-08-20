package  com.mlj.ecbiz.controller.trade;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ferrari.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.chexun.base.framework.core.entity.PageEntity;
import com.mlj.ecbiz.model.trade.TradeInfo;
import com.mlj.ecbiz.model.trade.TradeOrders;
import com.mlj.ecbiz.service.trade.TradeInfoService;
import com.mlj.ecbiz.service.trade.TradeOrdersService;

@Controller
@RequestMapping("/trade")
public class TradeInfoController{

	private static final Logger logger = Logger.getLogger(TradeInfoController.class);

	@Autowired
	private TradeInfoService tradeInfoService;
	@Autowired
	private TradeOrdersService tradeOrdersService;

	// 路径
	private String toList = "/trade/tradeList.httl";// 列表页
	private String toAdd = "/trade/tradeAdd.httl";// 添加页面
	private String toEdit = "/trade/trade_edit.httl";// 修改页

	@RequestMapping("/list")
	public ModelAndView listAll(HttpServletRequest request, HttpServletResponse response, TradeInfo query, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView(toList);
		try {
			page.setPageSize(2);
			page.setPageSize(10);
			if (query == null) {
				query = new TradeInfo();
			}
			List<TradeInfo> list = tradeInfoService.getTradeInfoPage(query, page);
			modelAndView.addObject("query", query);
			modelAndView.addObject("tradeInfoList", list);
			modelAndView.addObject("page",page);
		} catch (Exception e) {
			logger.error("TradeInfoController.listAll", e);
			//return new ModelAndView(setExceptionRequestAdmin(request, e));
		}

		return modelAndView;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(toAdd);
		try {
		} catch (Exception e) {
			logger.error("TradeInfoController.toAdd", e);
		}
		return modelAndView;
	}
	@ResponseBody
	@RequestMapping(value="/addsave",method=RequestMethod.POST)
	public String save(TradeInfo trade,TradeOrders order,String payTimes, HttpServletRequest request) {
		Long ret = -1L;
		trade.setCreated(new Date());
		trade.setPayTime(DateUtils.StringToDate(payTimes));
		trade.setStatus(1L);
		Long num=tradeInfoService.addTradeInfo(trade);
		tradeOrdersService.addTradeOrders(order);
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
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView toEdit(Long id,HttpServletRequest request) {
		ModelAndView modelAndView =new ModelAndView(toEdit);
		try {
			TradeInfo tradeInfo = tradeInfoService.getTradeInfoById(id);
			modelAndView.addObject(tradeInfo);
		} catch (Exception e) {
			logger.error("TradeInfoController.toEdit", e);
		}
		return modelAndView;
	}

	

	@RequestMapping("/delete")
	public RedirectView delete(String ids, HttpServletRequest request, TradeInfo query, @ModelAttribute("page") PageEntity page,RedirectAttributes attr) {
		RedirectView rv = new RedirectView("/manage/trade/tradeinfo/list");
		String[] idArray = ids.split(",");
		TradeInfo tradeInfo = new TradeInfo();
		try {// 软删除状态设置为2
			for (String id : idArray) {
				if (!"".equals(id)) {
					tradeInfo.setTid(Long.valueOf(id));
					//tradeInfo.setStatus(TradeInfo.DELETE_STATUS);
					this.tradeInfoService.updateTradeInfoByObj(tradeInfo);
				}
			}
			//attr.addAttribute("query", query);
			//attr.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("TradeInfoController.delete", e);
		}
		return rv;
	}
}
