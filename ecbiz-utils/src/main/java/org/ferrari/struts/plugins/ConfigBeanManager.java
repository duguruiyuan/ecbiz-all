package org.ferrari.struts.plugins;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.springframework.web.context.WebApplicationContext;

public class ConfigBeanManager implements PlugIn {

	private static WebApplicationContext wacontext;

	public static Object getBean(String name) {
		return wacontext.getBean(name);
	}

	public void destroy() {
		wacontext = null;
	}

	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		ServletContext context = servlet.getServletContext();
		wacontext = (WebApplicationContext) context.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

}
