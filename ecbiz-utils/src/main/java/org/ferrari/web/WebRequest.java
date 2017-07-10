package org.ferrari.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.ferrari.ConstantKeys;
import org.ferrari.utils.StringUtil;
import org.ferrari.utils.security.ByteUtils;
import org.ferrari.utils.security.Enumerator;
import org.ferrari.utils.security.SecurityUtils;
/**
   * 
   * @Create: Dec 5, 2008 4:25:37 PM     
   * @Description: get方式提交时url参数编码类
   * @see: com.jmyz.page.utils
   * @modify by: kevin
   * @time: Dec 5, 2008 4:25:37 PM
 */
public class WebRequest extends HttpServletRequestWrapper {

	private String encoding = null;
	private HashMap<String, String[]> parameters = null;
	private HashMap<String, String[]> searchKeys = new HashMap<String, String[]>();
	private boolean parseFlag = false;

	public WebRequest(HttpServletRequest request) {
		super(request);
		decodeParameters();
		setRequest(request);
	}

	public String getParameter(String name) {
		parseParameter();
		Object value = parameters.get(name);
		if (value == null)
			return null;
		if (value instanceof String[])
			return ((String[]) value)[0];
		if (value instanceof String)
			return (String) value;
		else
			return value.toString();
	}

	public Map<String, String[]> getParameterMap() {
		parseParameter();
		return parameters;
	}

	public Enumeration<?> getParameterNames() {
		parseParameter();
		return new Enumerator(parameters.keySet());
	}

	public String[] getParameterValues(String name) {
		parseParameter();
		Object value = parameters.get(name);
        if (value == null)
			return null;
		if (value instanceof String[])
			return (String[]) value;
		if (value instanceof String) {
			return new String[]{(String) value};
		} else {
			return new String[]{value.toString()};
		}
	}

	public Object getAttribute(String name) {
		if (ConstantKeys.PARAMETER_KEY.equals(name))
			return encodeParameters();
		else
			return getRequest().getAttribute(name);
	}

	@SuppressWarnings("unchecked")
	private void parseParameter() {
		if (parseFlag) return;
		HashMap<String, String[]> temp = copyMap(searchKeys);
		temp.putAll(getRequest().getParameterMap());
		temp.remove(ConstantKeys.PARAMETER_KEY);
		this.parameters = temp;
		parseFlag = true;
	}

	public void setRequest(ServletRequest request) {
		super.setRequest(request);
		parseFlag = false;
	}

    private static void putMapEntry(HashMap<String, String[]> map, String name, String value) {
		String newValues[] = null;
		String oldValues[] = map.get(name);
		if (oldValues == null) {
			newValues = new String[1];
			newValues[0] = value;
		} else {
			newValues = new String[oldValues.length + 1];
			System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
			newValues[oldValues.length] = value;
		}
		map.put(name, newValues);
	}

    private HashMap<String, String[]> copyMap(Map<String, String[]> orig) {
		if (orig == null)
			return new HashMap<String, String[]>();
		HashMap<String, String[]> dest = new HashMap<String, String[]>();
		String key;
		for (Iterator<String> keys = orig.keySet().iterator(); keys.hasNext(); dest.put(key, orig.get(key)))
			key = keys.next();
		return dest;
	}

	private void decodeParameters() {
		String parameter = getRequest().getParameter(ConstantKeys.PARAMETER_KEY);
		if (parameter != null && !"".equals(parameter)) {
			parameter = parameter.replace(' ', '+');//edit by kevin at 2009-12-12
			parameter = parameter.replaceAll("!", "+");
			parameter = this.argumentFilter(parameter);//过滤加密后重复的参数 add at 2010-12-27
			String[] paramerers = parameter.split("-");
			for (int i = 0; i < paramerers.length; i++) {
				if (paramerers[i].length() == 0) continue;
				String[] temp = ByteUtils.toString(SecurityUtils.base64Decode(paramerers[i]), getCharacterEncoding()).split("=", 2);
				putMapEntry(searchKeys, temp[0], temp[1]);
			}
		}
	}

	private String encodeParameters() {
		if (encoding != null) return encoding;
		StringBuffer buffer = new StringBuffer();
		Enumeration<?> names = getParameterNames();
		Set<String> set = new HashSet<String>();
		while(names.hasMoreElements()) {
			String name = (String) names.nextElement();
			name = name.replace("?", "").replace("/", "");
			if(set.contains(name)) continue;
			set.add(name);
			String[] values = getParameterValues(name);
			if (ConstantKeys.PAGINATION_CURR_PAGE.equals(name)||values == null) continue;
			for(int i=0; i<values.length; i++) {
				byte[] temp = ByteUtils.toBytes(name + "=" + values[i], getCharacterEncoding());
				buffer.append("-").append(SecurityUtils.base64Encode(temp));
			}
		}
		encoding = buffer.toString().replaceAll("\\+", "!");
		//过滤加密后重复的参数 add at 2010-12-27
		if(encoding != null && !"".equals(encoding)) encoding = this.argumentFilter(encoding);
		set = null;
		return encoding;
	}
	/**
	 * 过滤加密后重复的参数
	 * @param para
	 * @date 2010-12-27
	 * @return
	 */
	public String argumentFilter(String para){
		StringBuffer buff = null;
		String args = null;
		if(para != null && !"".equals(para)){
			String[] arrs = StringUtil.splitStringToArray(para, "-");
			Object[] objectArrays = StringUtil.filterArrayRepeatElement(arrs);
			String[] stringArrays = new String[objectArrays.length];
			for (int i = 0; i < objectArrays.length; i++) {
				if(objectArrays[i] == null) continue;
				stringArrays[i] = objectArrays[i].toString();
			}
			buff = StringUtil.arrayToString(stringArrays, "-");
			args = "-" + buff.toString();
		}
		return args;
	}
	public static void main(String[] args){
//		StringBuffer buffer = new StringBuffer();
//		byte[] temp = ByteUtils.toBytes("parameter is" + "=" + "奥迪", "UTF-8");
//		byte[] temp1 = ByteUtils.toBytes("parameter is" + "=" + "宝马", "UTF-8");
//		buffer.append("-").append(SecurityUtils.base64Encode(temp));
//		buffer.append("-").append(SecurityUtils.base64Encode(temp1));
//		System.out.println("编码后 = "+buffer.toString());
//		
//		String parameter = "-YnJhbmRJZD0w".replaceAll("!", "+");
//		String[] paramerers = parameter.split("-");
//		for (int i = 0; i < paramerers.length; i++) {
//			String[] tempDe = ByteUtils.toString(SecurityUtils.base64Decode(paramerers[i]), "UTF-8").split("=", 2);
//			for(int k=0; k<tempDe.length; k++){
//				if(tempDe.length == 0)continue;
//				System.out.println("解码后 = "+tempDe[k]);
//			}
//		}
//		StringBuffer buffer = new StringBuffer();
//		String p = "argkeys=&pagekeys=1";
//		byte[] temp = ByteUtils.toBytes(p, "UTF-8");
//		buffer.append("-").append(SecurityUtils.base64Encode(temp));
//		System.out.println("编码后 = "+buffer.toString());
//		
//		String parameter = buffer.toString().replaceAll("!", "+");
//		String[] paramerers = parameter.split("-");
//		for (int i = 0; i < paramerers.length; i++) {
//			byte[] codeBytes = SecurityUtils.base64Decode(paramerers[i]);
//			String code = ByteUtils.toString(codeBytes, "UTF-8");
//			String[] temps = code.split("=", 2);
//			for(int k=0; k<temps.length; k++){
//				if(temps.length == 0 || temps[i].equals(""))continue;
//				System.out.println("解码后 = "+temps[k]);
//			}
//		}
//		String s = "-cHJpY2V0eXBlPTE=-b3JpZ2luPTA=-b3JpZ2luPTA=-cHJpY2V0eXBlPTE=-Y2FydHlwZT0w-Y2FydHlwZT0w-RGVsaXZlcnlBcGFjaXR5PTA=-RGVsaXZlcnlBcGFjaXR5PTA=-U3BlZWRCb3g9MA==-U3BlZWRCb3g9MA==";
//		String[] arrs = StringUtil.splitStringToArray(s, "-");
//		Object[] t = StringUtil.filterArrayRepeatElement(arrs);
//		String[] x = new String[t.length];
//		for(int i=0;i<t.length;i++){
//			x[i]= t[i].toString();
//		}
//		StringBuffer temp = StringUtil.arrayToString(x, "-");
//		System.out.println(temp.toString());
//		Set set = new HashSet();
//		set.add("kevin");
//		System.out.println(set.contains("kevin1"));
	}
}
