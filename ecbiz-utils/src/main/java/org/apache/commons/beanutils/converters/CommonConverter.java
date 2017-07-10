package org.apache.commons.beanutils.converters;

import org.apache.commons.beanutils.Converter;

public abstract class CommonConverter implements Converter {

	@SuppressWarnings("unchecked")
	public final Object convert(Class type, Object value) {
		if (value != null && value instanceof CommonDataType) {
			CommonDataType temp = (CommonDataType) value;
			return convert(type, temp.getValue(), temp.getAddin());
		} else {
			return value;
		}
	}
	
	@SuppressWarnings("unchecked")
	public abstract Object convert(Class type, Object value, String addin);

}
