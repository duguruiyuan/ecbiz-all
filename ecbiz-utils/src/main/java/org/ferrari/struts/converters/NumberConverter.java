package org.ferrari.struts.converters;

import org.apache.commons.beanutils.converters.CommonConverter;

public class NumberConverter extends CommonConverter {

/*	private Converter integerConverter = ConvertUtils.lookup(Integer.class);
	private Converter longConverter = ConvertUtils.lookup(Long.class);
	private Converter shortConverter = ConvertUtils.lookup(Short.class);
	private Converter byteConverter = ConvertUtils.lookup(Byte.class);
	private Converter doubleConverter = ConvertUtils.lookup(Double.class);
	private Converter floatConverter = ConvertUtils.lookup(Float.class);
*/
	@SuppressWarnings("unchecked")
	public Object convert(Class type, Object value, String addin) {
		if (value == null || value.equals("")) return null;
		if (type.equals(Integer.class)) {
			try {
				return new Integer(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else if (type.equals(Long.class)) {
			try {
				return new Long(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else if (type.equals(Short.class)) {
			try {
				return new Short(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else if (type.equals(Byte.class)) {
			try {
				return new Byte(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else if (type.equals(Double.class)) {
			try {
				return new Double(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else if (type.equals(Float.class)) {
			try {
				return new Float(value.toString());
			} catch(Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
