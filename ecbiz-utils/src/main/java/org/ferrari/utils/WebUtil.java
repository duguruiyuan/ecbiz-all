package org.ferrari.utils;


public class WebUtil {
	/**
	 * 
	 * @param 
	 * 截取字符工具类
	 *   String s 需截取字符串
	 * @param 
	 *   int n 字节数 （1字符=2字节）
	 * @return 
	 *   String
	 */
	// 截取字符串（避免截取半个汉字） s：所截取字符串 n：需截取字节数（1字符=2字节）
	// edit by Bob.Z
	public static String getSubString(String s, int n) {

		if (s == null) {
			return s;
		}
		s = s.trim();
		if (s.getBytes().length < n) {
			return s;
		} else {
			int lastIndex = 0;
			int temp = 0;
			int length = s.length();
			for (int i = 0; i < length; i++) {
				String tempString = s.substring(i, i + 1);
				temp = tempString.getBytes().length + temp;
				if (Character.isUpperCase(tempString.charAt(0))
						&& !tempString.equals("I")) {
					temp = temp + 1;
				}
				if (temp >= n) {
					if (temp == n) {
						lastIndex = i + 1;
					} else {
						lastIndex = i;
					}
					break;
				}
			}
			return s.substring(0, lastIndex);
		}

	}

}
