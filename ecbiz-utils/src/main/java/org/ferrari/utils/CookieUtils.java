package org.ferrari.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	/**
	 * lookup cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie find(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;
		for (int i = 0; i < cookies.length; i++)
			if (cookies[i].getName().equals(name))
				return cookies[i];
		return null;
	}

}
