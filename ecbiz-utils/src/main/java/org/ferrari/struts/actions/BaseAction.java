package org.ferrari.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.ferrari.ConstantKeys;
import org.ferrari.exception.ActionException;
import org.ferrari.exception.UtilsException;
import org.ferrari.struts.forms.BaseForm;
import org.ferrari.utils.CommonUtils;
import org.ferrari.web.WebDialog;


public class BaseAction extends DispatchAction{
	private String args = null;
	private String pages = null;
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ActionException {
		String parameter = mapping.getParameter();
		BaseForm forms = null;
		if(form instanceof BaseForm){
			forms = (BaseForm)form;
			args = forms.getArgsKeys();
			pages = forms.getPagekeys();
		}
		if (parameter == null)
			throw new ActionException("parameter is null, check struct-config.xml");
		try {
			ActionForward forward = dispatchMethod(mapping, form, request, response, parameter);
			return forward;
		} catch (ActionException e) {
			forms = null;
			throw e;
		} catch (Exception e) {
			forms = null;
			throw new ActionException(e);
		}
	}
	
	public ActionForward forwardDialog(ActionMapping mapping,
			HttpServletRequest request, WebDialog dialog) {
		request.setAttribute(ConstantKeys.DIALOG_KEY, dialog);
		return mapping.findForward("dialog");
	}
	
	/**
	 * 用于查询后返回当前页的参数调用
	 * @param args
	 * @param pages
	 * @return
	 */
	public String argument(String args, String pages){
		StringBuffer buffer = new StringBuffer();
		buffer.append("?").append(ConstantKeys.PAGINATION_CURR_PAGE).append("=").append(pages);
		buffer.append("&").append(ConstantKeys.PARAMETER_KEY).append("=").append(args);
		return buffer.toString();
	}
	
	/**
	 * 用于查询后返回当前页的参数调用
	 * @param args
	 * @param pages
	 * @return
	 */
	
	public String argument(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("?").append(ConstantKeys.PAGINATION_CURR_PAGE).append("=").append(this.pages);
		buffer.append("&").append(ConstantKeys.PARAMETER_KEY).append("=").append(this.args);
		return buffer.toString();
	}
	/**
	 * 获取SPRING容器中实例
	 * @param name
	 * @return
	 * @throws UtilsException
	 */
	protected Object getBean(String name) throws UtilsException {
		Object bean = null;
		try {
			bean = CommonUtils.getBean(name);
			if (bean == null)
				throw new NullPointerException();
			return bean;
		} catch(Exception e) {
			throw new UtilsException("service or dao [" + name + "] bean not found!");
		}
	}
	/**
	 * log4j 方式记录日志
	 * @param log
	 * @param ex
	 * @param method
	 * @return
	 */
	protected boolean handleError(Logger log, Exception ex, String method){
		if(log == null || ex == null){
			return false;
		}
		log.error("[METHOD:" + method + "],[TYPE:"+ex.getClass()+"], [MESSAGE:" + ex.getMessage()+"]");
		return true;
	}
	/**
	 * common log 方式记录日志
	 * @param log
	 * @param ex
	 * @param clazz
	 * @return
	 */
	protected boolean handleError(Log log, Exception ex, Class<?> clazz){
		if(log == null || ex == null){
			return false;
		}
		log.error(clazz, ex);
		return true;
	}
}
