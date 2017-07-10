package org.ferrari.struts.plugins;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.ferrari.struts.converters.DateConverter;

public class ConvertPlugIn implements PlugIn {

	private Logger logger = Logger.getLogger("ConvertPlugIn");

	public void destroy() {
		logger.log(Level.INFO, "deregister converters.");
		ConvertUtils.deregister(Date.class);
		ConvertUtils.deregister(String.class);
		ConvertUtils.deregister(Integer.class);
		ConvertUtils.deregister(Long.class);
		ConvertUtils.deregister(Double.class);
		ConvertUtils.deregister(Float.class);
		ConvertUtils.deregister(Short.class);
		ConvertUtils.deregister(Byte.class);
	}

	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		logger.log(Level.INFO, "register converters.");
		ConvertUtils.register(new DateConverter(), Date.class);
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new ByteConverter(null), Byte.class);
	}

}
