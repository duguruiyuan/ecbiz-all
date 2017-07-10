package org.ferrari.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.ferrari.exception.UtilsException;

import org.ferrari.web.WebRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class EncodingFilter implements Filter {

	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	private static 	WebApplicationContext wac =null;
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null)
			request.setCharacterEncoding(encoding);
		request = new WebRequest((HttpServletRequest) request);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		if(wac==null)
		  wac  =  WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		
		this.encoding = filterConfig.getInitParameter("encoding");
	}
	
	public static Object getBean(String name){
		if(wac == null) return new UtilsException("spring webApplicationContext is null!");
		try{
			return wac.getBean(name);
		}catch(UtilsException e){
			throw new UtilsException("bean name is error, please checked spring config. name="+ name + " reason by: " + e);
		}
	}

}
