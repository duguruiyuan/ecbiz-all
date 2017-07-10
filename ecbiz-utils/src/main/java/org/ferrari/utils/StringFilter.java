package org.ferrari.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**  
 * @create.date:Oct 29, 2009  
 * @comment: <p>TODO</p>
 * @see: com.jmyz.extend.car.web.utils
 * @author: tanghuiling
 * @verson: 1.0
 */
public class StringFilter {
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字        
		// String   regEx  =  "[^a-zA-Z0-9]";                      
		// 清除掉所有特殊字符   
		String regEx = "[`~!@#$%^&*()+=_|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}\"\"-【】《》‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
