package org.ferrari.struts.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.CommonConverter;
import org.ferrari.ConstantKeys;

public class StringConverter extends CommonConverter {

	private Converter default_converter = ConvertUtils.lookup(String.class);
	
	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value, String addin) {
		//if addin is null, use default converter
		if (addin == null) {
			return  default_converter.convert(type, value);
		}
		
		if (value == null) {
            return ((String) null);
		} else if (value instanceof java.util.Date) {
			if (addin == null) addin = ConstantKeys.DEFAULT_DATE_PATTERN;
			return new SimpleDateFormat((String)addin).format(value);
		} else if (value instanceof Number) {
			NumberFormat format = NumberFormat.getNumberInstance();
			if (addin == null) addin = ConstantKeys.DEFAULT_NUMBER_PATTERN;
        	((DecimalFormat) format).applyPattern(addin);
        	return format.format(value);
        } else {
            return (value.toString());
        }
	}

}
