package org.ferrari.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.ferrari.exception.UtilsException;

/**
 * 
 * @Create: 2009-2-5 上午10:45:28
 * @Description: TODO
 * @see: org.ferrari.utils
 * @modify by: 喻新容
 * @time: 2009-2-5 上午10:45:28
 */
public class DateUtils {
	/**
	 * 
	 * @description: 将字符串转换为时间
	 * @param mytime
	 * @return
	 * @author: 喻新容
	 * @date: 2009-2-5
	 */
	public static Date StringToDate(String mytime) {
		Date date = null;
		if (mytime != null && !"".equals(mytime.trim())) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = new Date(format.parse(mytime).getTime());
			} catch (Exception e) {
				throw new UtilsException("时间从字符串到date异常",e);
		
			
			}
		}
		return date;
	}

	/**
	 * 
	 * @description: 将字符串转换为时间
	 * @param mytime
	 * @return
	 * @author: 喻新容
	 * @date: 2009-2-5
	 */
	public static Date StringToDateMMSS(String mytime) {
		Date date = null;
		if (mytime != null && !"".equals(mytime.trim())) {
			if (mytime.trim().length() < 11) {
				mytime = mytime + " 0:00:00";
			}
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = new Date(format.parse(mytime).getTime());
			} catch (Exception e) {
				
				throw new UtilsException("时间从字符串到date异常",e);
				
			}
		}
		return date;
	}
	
	public static String StringToDateMMSS2(String mytime) {
		
		if (mytime != null && !"".equals(mytime.trim())) {
			if (mytime.trim().length() < 11) {
				mytime = mytime + " 00:00:00";
			}
			String result = "to_date('";
			result = result+mytime+"','yyyy-mm-dd hh24:mi:ss')";
			return result;
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @description: 将字符串转换为时间
	 * @param mytime
	 * @return
	 * @author: jackie
	 * @date: 2009-9-1
	 */
	public static Date StringToMMSS(String mytime,String secondtime) {
		Date date = null;
		if (mytime != null && !"".equals(mytime.trim())) {
			mytime = mytime +" "+ secondtime;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = new Date(format.parse(mytime).getTime());
			} catch (Exception e) {
				
				throw new UtilsException("时间从字符串到date异常",e);
				
			}
		}
		return date;
	}

	/**
	 * 
	 * @description:将Date类型时间格式转换为String型
	 * @param dateStr
	 * @return
	 * @throws Exception
	 * @author: 喻新容
	 * @date: 2009-2-5
	 */

	public static String Date2StringMMSS(Date dateStr) throws Exception {
		if (dateStr == null) {
			throw new UtilsException("时间转字符串异常" + dateStr + "|"
					+ "yyyy-MM-dd HH:mm:ss");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String time = df.format(dateStr);
			return time;
		} catch (Exception ex) {
			
			throw new UtilsException("时间转字符串异常",ex);
			
		}
	}
	
	

	public static String getInfoDate(Date dateStr) throws Exception {
		if (dateStr == null) {
			throw new UtilsException("时间转字符串异常" + dateStr + "|"
					+ "yyyy-MM-dd HH:mm:ss");
		}
		SimpleDateFormat df = new SimpleDateFormat("HH");
		try {
			String time = df.format(dateStr);
		Integer	time2 = Integer.parseInt(time);
		if(time2>=0&&time2<6)
		{
		return "凌晨好";
		}
		
		   if(time2>=6&&time2<12)
			{
			return "早上好";
			}
			if(time2>=12&&time2<14)
			{
			return "中午好";
			}
			
			if(time2>=12&&time2<14)
			{
			return "中午好";
			}
			
			if(time2>=14&&time2<18)
			{
			return "下午好";
			}
			if(time2>=18&&time2<19)
			{
			return "傍晚好";
			}
			
			if(time2>=19&&time2<24)
			{
			return "晚上好";
			}
			
			return "您好";
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @description: 将Date类型时间格式转换为String型
	 * @param dateStr
	 * @return
	 * @throws Exception
	 * @author: 喻新容
	 * @date: 2009-2-5
	 */
	public static String Date2String(Date dateStr) throws Exception {
		if (dateStr == null) {
			throw new UtilsException("时间转字符串异常" + dateStr + "|" + "yyyy-MM-dd");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String time = df.format(dateStr);
			return time;
		} catch (Exception ex) {
			throw new UtilsException("时间转字符串异常",ex);
		}
	}

	/**
	 * 
	 * @description: get year part of date
	 * @param date
	 * @return year
	 * @author: kevin
	 * @date: 2009-2-5
	 */
	public static int getYearPart(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @description: get month part of date
	 * @param date
	 * @return month
	 * @author: kevin
	 * @date: 2009-2-5
	 */
	public static int getMonthPart(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * @description: get day part of date
	 * @param date
	 * @return day
	 * @author: kevin
	 * @date: 2009-2-5
	 */
	public static int getDayPart(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static String getYmdhms(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
	
		String  year =  Integer.toString(c.get(Calendar.YEAR));
		String  month =  Integer.toString(c.get(Calendar.MONTH) + 1);
		String   dd= Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String    hour=Integer.toString(c.get(Calendar.HOUR_OF_DAY));  
        String   minute=Integer.toString(c.get(Calendar.MINUTE));  
        String   second=Integer.toString(c.get(Calendar.SECOND));
        int k = new Random().nextInt();
        int j = Math.abs(k % 40) + 60; 
        return  year+month+dd+hour+minute+second+j;
	}
	public static String getOneMonthDate(Date date,Integer monthCount) {
		Calendar c = new GregorianCalendar();
		String  month =null;
		c.setTime(date);
		String  year =  Integer.toString(c.get(Calendar.YEAR));
		Integer mm = c.get(Calendar.MONTH) + 1+monthCount;
		if(mm<10){
		month =  "0"+Integer.toString(c.get(Calendar.MONTH) + 1+monthCount);
		}
		else {
			 month =  Integer.toString(c.get(Calendar.MONTH) + 1+monthCount);
		}
		
		String   dd= Integer.toString(c.get(Calendar.DAY_OF_MONTH));
//        String    hour=Integer.toString(c.get(Calendar.HOUR));  
//        String   minute=Integer.toString(c.get(Calendar.MINUTE));  
//        String   second=Integer.toString(c.get(Calendar.SECOND));   
		return  year+"-"+month+"-"+dd;
	}
	
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: Jun 6, 2009 ( 2:23:43 PM )
	 * @author: yuxinrong
	 * @param date
	 * @return
	 * @see: <h1>org.ferrari.utils.DateUtils.getMdhms</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static String getMdhms(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		
		String  month =  Integer.toString(c.get(Calendar.MONTH) + 1);
		String   dd= Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String    hour=Integer.toString(c.get(Calendar.HOUR));  
        String   minute=Integer.toString(c.get(Calendar.MINUTE));  
        String   second=Integer.toString(c.get(Calendar.SECOND));   
		return  month+dd+hour+minute+second;
	}
	public static String getTimeRandom(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		
	 
        String   minute=Integer.toString(c.get(Calendar.MINUTE));  
        String   second=Integer.toString(c.get(Calendar.SECOND));   
		return  minute+second;
	}
	/**
	 * 
	 * @description: format date by YYYY-MM-DD
	 * @param date
	 * @param pattern
	 * @return string
	 * @author: kevin
	 * @date: 2009-2-5
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 
	 * @description: parse date for YYYY-MM-DD
	 * @param date
	 * @param pattern
	 * @return date
	 * @author: kevin
	 * @date: 2009-2-5
	 */
	public static Date parseDate(String date, String pattern) {
		if (date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (Exception e) {
			throw new UtilsException("时间转字符串异常",e);
		}
	}

	/*
	 * syh
	 */
	public static String getDateNow4Cn() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	/*
	 * syh
	 */
	public static String getWeekDay() {
		Calendar calendarr = new GregorianCalendar();
		int weekDay = calendarr.get(Calendar.DAY_OF_WEEK);
		switch (weekDay) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";
		}
		return null;
	}
	
	/**
	 * 
	 *@description: 把毫秒转换到YY-MM-DD
	 *@param str
	 *@return
	 *@author: 李建迎
	 *@date: 2009-2-21
	 */
	@SuppressWarnings("deprecation")
	public static String parTimeMillis(String str){
		java.sql.Date date = null;
		try{
			if(str != null && !"".equals(str)){
				date = new java.sql.Date(Long.parseLong(str)*1000);
			}
			
			//return format:toLocaleString 2009-2-20 16:33:49
			//return format:toGMTString 20 Feb 2009 08:33:49 GMT
			if(date.getMonth() > 9){
				return date.toLocaleString().substring(0, 10);
			}else{
				return date.toLocaleString().substring(0, 9);
			}
		}catch(Exception e){
			throw new UtilsException("在把毫秒转换成当前时间的进程中出错了！",e);
			
		}
	}
	/**
	 * 
	 *@description: TODO
	 *@param str
	 *@return
	 *@author: 喻新容
	 *@date: 2009-4-3
	 */
	@SuppressWarnings("deprecation")
	public static String parTimeHHSS(String str){
		java.sql.Date date = null;
		try{
			if(str != null && !"".equals(str)){
				date = new java.sql.Date(Long.parseLong(str)*1000);
			}
			
			return date.toLocaleString();
			
		}catch(Exception e){
			
			throw new UtilsException("在把毫秒转换成当前时间的进程中出错了！",e);
		
		}
	}
	
	
	
	public static String getHMS(long apart){
		
		long h = (apart)/(60*60*1000);
		long myu=0;
		long hyu =0;
		long m =0;
		long s=0;
		if(h<=0){
			m = (apart)/(60*1000);
			if(m>0){
				myu = (apart)%(60*1000);
				if(myu>0){
					s = myu/(1000);
				}
			}else{
				s = (apart)/(1000);
			}
		}else{
			hyu = (apart)%(60*60*1000);
			if(hyu>0){
				m = hyu/(60*1000);
				if(m>0){
					myu = (apart)%(60*1000);
					if(myu>0){
						s = myu/(1000);
					}
				}else{
					s = (apart)/(1000);
				}
			}
		
	}
		return h+"小时"+m+"分钟"+s+"秒";
	}
	
	
	public static String getMonthPartStr(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int i = c.get(Calendar.MONTH) + 1;
		String str = "";
		if(i<10){
			str = "0"+String.valueOf(i);
		}else{
			str = String.valueOf(i);
		}
		return str;
	}
	
	public static String getDayPartStr(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_MONTH);
		String str = "";
		if(i<10){
			str = "0"+String.valueOf(i);
		}else{
			str = String.valueOf(i);
		}
		return str;
	}
	
	
	/*
	 * 返回当前时间 前几天的时间
	 * @param days  天数
	 * @param format 返回时间的格式
	 */

	public static String getTimeFromNow(int days,String format) {
	   
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(new Date());
	   long timethis = calendar.getTimeInMillis();
	   
	   long differ = days * (1000 * 60 * 60 * 24L);
	   
	   long timestart = timethis-differ;
	   
	   Date date = new Date(timestart);
       
	   SimpleDateFormat df = new SimpleDateFormat(format);
	   
	   return df.format(date);
	
	}
	
	//用户输入一个时间，得到当前时间   判断两个时间的时间差(天为单位)

	public static int Difference(String date,String format) {
	   Date dt = null;
	   try {
		   dt = new SimpleDateFormat(format).parse(date);
		   
		   Calendar calendar = Calendar.getInstance();
		   calendar.setTime(new Date());
		   long timethis = calendar.getTimeInMillis();
		   calendar.setTime(dt);
		   long timeend = calendar.getTimeInMillis();
		   //将时间转化为天 返回一个int型负数
		   long theday = (timethis - timeend) / (1000 * 60 * 60 * 24L);
		   
		   return (int)theday;
	   } catch (Exception e) {
	      e.printStackTrace();
	      return 0;
	   }
	   
	}

	// 指定时间日期的比较,前者比后者小返回<0，大返回>0,相等返回=0
	public static int getCompare(String dateStr1, String dateStr2) {
		return dateStr1.compareTo(dateStr2);
	}
	   
	public static void main(String [] aa)
	{
		try{
		System.out.println(Difference("2011-06-21 16:02:27","yyyy-MM-dd HH:mm:ss"));
	}catch(Exception ex){
		
	}
	}
	
	
}
