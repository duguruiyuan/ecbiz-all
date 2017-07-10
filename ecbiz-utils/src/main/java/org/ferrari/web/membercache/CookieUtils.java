package org.ferrari.web.membercache;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
   * @create.date: Jul 13, 2009 3:26:50 PM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.web.membercache
   * @author: shellrong
   * @modify.by: shellrong
   * @modify.date: Jul 13, 2009 3:26:50 PM
 */
public class CookieUtils {
	  /**
     * 添加cookie
     * @param response
     * @param name cookie的名称
     * @param value cookie的值
     * @param maxAge cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge,String domain) {        
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath("/");
        if (maxAge>0) cookie.setMaxAge(maxAge);
        response.addHeader("P3P", "CP=\"IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA\"");
        response.addCookie(cookie);
    }
    
    /**
     * 获取cookie的值
     * @param request
     * @param name cookie的名称
     * @return
     */
   public static String getCookieByName(HttpServletRequest request, String name) {
    	Map<String, Cookie> cookieMap = CookieUtils.readCookieMap(request);
    	 String vaule = null;
      if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
         try {
        	 vaule = java.net.URLDecoder.decode(cookie.getValue(),"UTF-8");
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return vaule;
       }else{
          return null;
       }
  }
    
 protected static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                cookieMap.put(cookies[i].getName(), cookies[i]);
            }
        }
        return cookieMap;
    }
 
 public static void  deleteByNameLike(HttpServletRequest request,HttpServletResponse response,String nameLike,int maxAge,String domain){
	    Cookie[] cookies = request.getCookies();
	    if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
            	
                if((cookies[i].getName().indexOf(nameLike.trim()))>=0){
                 	
                	 addCookie(response,cookies[i].getName(),null, maxAge, domain);
                }
            }
        }
 }
 
 public static String getRequestURI(HttpServletRequest request){     
     return request.getRequestURI();
 }
 /**
  * 获取完整请求路径(含内容路径及请求参数)
  * @param request
  * @return
  */
 public static String getRequestURIWithParam(HttpServletRequest request){     
     return getRequestURI(request) + (request.getQueryString() == null ? "" : "?"+ request.getQueryString());
 }
}
