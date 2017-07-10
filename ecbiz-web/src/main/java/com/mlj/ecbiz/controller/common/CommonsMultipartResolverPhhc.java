package com.mlj.ecbiz.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class CommonsMultipartResolverPhhc extends CommonsMultipartResolver {
	
    public boolean isMultipart(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url!=null && url.contains("ueditorDispatch.do")) {
            return false;
        } else {
            return super.isMultipart(request);
        }
    }
}
