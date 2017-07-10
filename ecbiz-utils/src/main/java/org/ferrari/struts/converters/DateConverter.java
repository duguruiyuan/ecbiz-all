package org.ferrari.struts.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.converters.CommonConverter;
import org.ferrari.ConstantKeys;

public class DateConverter extends CommonConverter {

	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value, String addin) {
		if (value == null || value instanceof Date)
			return (Date) value;
		else if (value instanceof String && "".equals(value))
			return null;
		try {
			if (addin == null) addin = ConstantKeys.DEFAULT_DATE_PATTERN;
			return new SimpleDateFormat((String)addin).parse(value.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			return (Date) null;
		}
	}

}
