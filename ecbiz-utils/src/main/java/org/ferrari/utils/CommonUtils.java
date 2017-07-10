package org.ferrari.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.ferrari.exception.UtilsException;
import org.ferrari.web.filter.EncodingFilter;

public class CommonUtils {
	private static String[] weekArray = new String[]{"日","一","二","三","四","五","六"};
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-8-3 ( 上午10:18:14 )
	 * @author: kevin
	 * @param request
	 * @return
	 * @see: <h1>org.ferrari.utils.CommonUtils.getDomain</h1>
	 */
	public static String getDomain(HttpServletRequest request) {
		return request.getServerName().replaceAll("^[^\\.]*.", "");
	}
	/**
	 * set operation for list1 and list2, that is list1 intersect list2
	 * @param list1
	 * @param list2
	 * @return
	 * <pre>
	 * String[] a1=new String[]{"A","C","D","H","Z"};
	 * String[] a2=new String[]{"A","B","D","X"};
	 * List list1=a1.asList();
	 * List list2=a2.asList();
	 * List result = CollectionUtil.intersect(list1, list2);
	 * result is:C,H,Z
	 * </pre>
	 */
	public static List<Object> intersect(final List<Object> list1, final List<Object> list2) {
		if (list1 == null || list1.size() == 0 || list2 == null || list2.size() == 0) { //any array is null or its length is equal to zero, then return null
			return null;
		}
		int length1 = list1.size();
		int length2 = list2.size();
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < length1; i++) {
			map.put((Object)list1.get(i), null);
		}
		for (int i = 0; i < length2; i++) {
			if (map.containsKey(list2.get(i))) {
				list.add(list2.get(i));
			}
		}
		return list;
	}
	/**
	 * list1 subtracts list2 
	 * @param list1
	 * @param list2
	 * @return
	 * <pre>
	 * String[] a1=new String[]{"A","C","D","H","Z"};
	 * String[] a2=new String[]{"A","B","D","X"};
	 * List list1=a1.asList();
	 * List list2=a2.asList();
	 * List result = CollectionUtil.subtract(list1, list2);
	 * result is:C,H,Z
	 * </pre>
	 */
	public static List<Object> subtract(final List<Object> list1, final List<Object> list2) {
		final ArrayList<Object> result = new ArrayList<Object>(list1);
		final Iterator<Object> iterator = list2.iterator();
		while (iterator.hasNext()) {
			result.remove(iterator.next());
		}
		return result;
	}
	/**
	 * 
	 * @comment: <p>check string is number</p>  
	 * @create.date: 2009-5-12 ( 下午03:34:53 )
	 * @author: kevin
	 * @param str
	 * @return
	 * @see: <h1>org.ferrari.utils.CommonUtils.isNumber</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static boolean isNumber(String str) {
		int begin = 0;
		if (str == null || str.trim().equals("")) {
			return false;
		}
		str = str.trim();
		if (str.startsWith("+") || str.startsWith("-")) {
			if (str.length() == 1) {
				// "+" "-"
				return false;
			}
			begin = 1;
		}
		for (int i = begin; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @comment: <p>get object from spring context by spring inject name</p>  
	 * @create.date: 2009-5-12 ( 下午03:35:23 )
	 * @author: kevin
	 * @param name
	 * @return
	 * @see: <h1>org.ferrari.utils.CommonUtils.getBean</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static Object getBean(String name){
		try{
			return EncodingFilter.getBean(name);
		}catch(UtilsException e){
			throw new UtilsException("get spring context failure! reason by: " + e);
		}
	}
	/**
	 * 
	 * @comment: <p>property copy</p>  
	 * @create.date: 2009-5-14 ( 下午07:04:10 )
	 * @author: kevin
	 * @param src
	 * @param dest
	 * @see: <h1>org.ferrari.utils.CommonUtils.copyProperties</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static void copyProperties(Object src, Object dest) {
		try {
			PropertyUtils.copyProperties(dest, src);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @comment: <p>过滤重复元素</p>  
	 * @create.date: 2009-6-4 ( 下午03:35:06 )
	 * @author: kevin
	 * @param list
	 * @return
	 * @see: <h1>org.ferrari.utils.CommonUtils.filterList</h1>
	 */
	public static List<?> filterList(List<?> list){
		for (int k = 0; k < list.size() - 1; k++) {
			for (int j = list.size() - 1; j > k; j--) {
				if (list.get(j).equals(list.get(k))) {
					list.remove(j);
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: 2009-9-15 ( 上午09:54:00 )
	 * @author: kevin
	 * @param date
	 * @return
	 * @see: <h1>org.ferrari.utils.CommonUtils.formatWeek</h1>
	 */
	public static String formatWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		return "星期" + weekArray[i-1];
	}
	
	/**
	 * cut source string to max length
	 * @author: kevin
	 * @param source
	 * @param maxlen
	 * @return
	 */
	public static String cutString(String source, int maxlen) {
		if (source == null || maxlen <= 0) return "";
		int maxBytes = maxlen * 2 - 3;
		int bytes = 0; int index = -1; //boolean flag = false;
		for(int i = 0; i < source.length(); i++) {
			bytes += (source.charAt(i) >= 0xFF)?2:1;
			if (index == -1 && maxBytes > 0 && bytes >= maxBytes ) {
				index = i;
				//flag = maxBytes - bytes == 0;
			}
		}
		if (bytes <= maxBytes+3) return source;
		return source.substring(0, index+1);
	}

	/**
	 * get string bytes count
	 * @param source
	 * @return
	 */
	public static int getBytes(String source) {
		if (source == null) return 0;
		int size = source.length();
		for(int i = 0; i < source.length(); size += (source.charAt(i++) >= 255)?1:0);
		return size;
	}
	
	public static void main(String[] args) {
		System.out.println(cutString("中华aa人民共和国", 5));
	}
}
