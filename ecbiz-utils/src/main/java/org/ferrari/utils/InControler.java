package org.ferrari.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;
import org.apache.struts.util.RequestUtils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InControler extends RequestProcessor {

	@SuppressWarnings("unchecked")
	@Override
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping) throws IOException {		
		// Acquire the Action instance we will be using (if there is one)
        String className = mapping.getType();
        if (log.isDebugEnabled()) {
            log.debug(" Looking for Action instance for class " + className);
        }
        Action instance;

        synchronized (actions) {
            // Return any existing Action instance of this class
            instance = (Action) actions.get(className);
            if (instance != null) {
                if (log.isTraceEnabled()) {
                    log.trace("  Returning existing Action instance");
                }
                return (instance);
            }
            // Create and return a new Action instance
            if (log.isTraceEnabled()) {
                log.trace("  Creating new Action instance");
            }
            try {
                instance = (Action) RequestUtils.applicationInstance(className);
                this.jnjectSpringBean(instance);//实施注入
                // Maybe we should propagate this exception
                // instead of returning null.
            } catch (Exception e) {
                log.error(getInternal().getMessage("actionCreate",
                        mapping.getPath()), e);

                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    getInternal().getMessage("actionCreate", mapping.getPath()));

                return (null);
            }
            actions.put(className, instance);
        }
        if (instance.getServlet() == null) {
            instance.setServlet(this.servlet);
        }
        return (instance);
	}
	/**
	 * 实施对@Resource注解的解析,该注解实现了Spring bean的注入,@Resource.name()用于指定bean的名称,如果没有name()属性,
	 * 默认使用Field名或属性名作为bean的名称
	 * @param action
	 */
	@SuppressWarnings("unchecked")
	private void jnjectSpringBean(Action action) {
		WebApplicationContext 	wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		Class<Action> actionClass = (Class<Action>) action.getClass();
		try {
			//实现对Filed的注入
			Field[] fileds = actionClass.getDeclaredFields();
			for(Field field : fileds){
				if(field.isAnnotationPresent(Resource.class)){//判断Field上是否存在@Resource注解
					field.setAccessible(true);//设置字段允许访问,一般用在字段访问属性是private的情况
					Resource resource = field.getAnnotation(Resource.class);//获取注解信息,相当于获取XML的配置信息
					if(resource.name()==null || "".equals(resource.name().trim())){
						//如果没有设置@Resource.name()属性值,默认使用字段的名称作为bean的名称	
						field.set(action, wac.getBean(field.getName()));//实施注入
					}else{
						field.set(action, wac.getBean(resource.name()));//实施注入
					}
				}
			}
			//实现对属性的setter方法进行注入
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(actionClass).getPropertyDescriptors();
			for(PropertyDescriptor propertydes : propertyDescriptors){
				Method setterMethod = propertydes.getWriteMethod();
				if(setterMethod!=null && setterMethod.isAnnotationPresent(Resource.class)){
					setterMethod.setAccessible(true);
					Resource resource = setterMethod.getAnnotation(Resource.class);//获取注解信息
					if(resource.name()==null || "".equals(resource.name().trim())){
						//如果没有设置@Resource.name()属性值,默认使用属性的名称作为bean的名称	
						setterMethod.invoke(action, wac.getBean(propertydes.getName()));//实施注入
					}else{
						setterMethod.invoke(action, wac.getBean(resource.name()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
