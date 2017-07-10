package com.mlj.ecbiz.model.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
   * @create.date: Mar 22, 2012 12:24:46 PM     
   * @see: com.system.utils
   * @author: aaron.han
   * @modify.by: aaron.han
   * @modify.date: Mar 22, 2012 12:24:46 PM
 */

public class Constants {
	//----------------------------------------------图片库整体分类--parentId-----------------------------------------------start
	/**
	 * 图片库分类-----车型图片
	 */
	public static final String CHEXING_PIC = "1";
	/**
	 * 图片库分类-----人物事件图片
	 */
	public static final String RENWU_PIC = "2";
	/**
	 * 图片库分类-----车展图片
	 */
	public static final String CHEZHAN_PIC = "3";
	/**
	 * 图片库分类-----新闻图片
	 */
	public static final String NEWS_PIC = "4";
	/**
	 * 图片库分类-----拆车坊图片
	 */
	public static final String CHAICHE_PIC = "5";
	/**
	 * 图片库分类-----白底图
	 */
	public static final String WHITE_PIC = "6";
	/**
	 * 图片库分类-----用品图
	 */
	public static final String PRODUCTION_PIC = "15";
	/**
	 * 图片库分类-----其他图片
	 */
	public static final String OTHER_PIC = "9";
	//-----------------------------------------------图片库整体分类--parentId------------------------------------------------end

	
	//----------------------------------------图片上传所属类别--picType-----------------------------------------------------start
	/** 车型实拍图和车展车型图片 **/
	public static final String CAR= "1";
	/** 车型官图图解及活动 和 人物事件类图片**/
	public static final String CAROTHER = "2";
	/** 车模**/
	public static final String SEXMODEL = "3";
	/** LOGO**/
	public static final String LOGO = "4";
	/** 新闻**/
	public static final String NEWS = "5";
	//----------------------------------------图片上传所属类别--picType--------------------------------------------------------end

	
	
	//----------------------------------------相册分类--albumType-----------------------------------------------------start
	/**
	 * 图片库分类----车型图片---实拍图类别
	 */
	public static final Integer SHI_PAI = 1;
	/**
	 * 图片库分类----车展图片分类---汽车类别
	 */
	public static final Integer CAR_CHEZHAN = 1;
	/**
	 * 图片库分类----车展图片分类---模特类别
	 */
	public static final Integer MODEL_CHEZHAN = 2;
	/**
	 * 图片库分类----人物事件类图片分类---人物类别
	 */
	public static final Integer TYPE_PERSON = 1;
	/**
	 * 图片库分类----人物事件类图片分类---事件类别
	 */
	public static final Integer TYPE_EVENT = 2;
	//----------------------------------------相册分类--albumType--------------------------------------------------------end

	
	
	/**
	 * 后台数据管理日志记录，值可修改，项目发布前可选择控制台显示
	 */
	public static final String MANAGER = "manager";
	/**
	 * 后台CMS日志记录
	 */
	public static final String CMS = "cms";
	/**
	 * 通过SSO登录
	 */
	public static final String LOGIN = "http://reg.chexun.com/logon.aspx?gourl=http://m.tool.chexun.com/system/login.do";
	//public static final String LOGIN = "http://reg.chexun.com/logon.aspx?gourl=http://m.chexun.com/system/login.do";
	/**
	 * 通过SSO注销
	 */
	public static final String LOGOUT = "http://reg.chexun.com/logout.aspx?gourl=http://m.tool.chexun.com/system/login.do";
	//public static final String LOGOUT = "http://reg.chexun.com/Logout.aspx?gourl=http://m.chexun.com/system/login.do";
	/**
	 * SSO验证
	 */
	public static final String SSO_CHECK = "http://reg.chexun.com/check.aspx";
	
	
	/**
	 * 乐视视频上传回调方法返回状态参数
	 * 等待上传----0
	 * 上传中----1
	 * 上传完毕----2
	 * 转码中-----3
	 * 处理完成----4
	 * 转码失败----5
	 * 审核失败----6
	 * 视频暂停----7
	 */
	public static final String UPLOAD_OVER = "UPLOAD_OVER";//上传完毕----2
	public static final String DEAL_FAILED = "DEAL_FAILED";//转码失败----5
	public static final String CHECK_FAILED = "CHECK_FAILED";//审核失败----6
	public static final String OK = "OK";//处理成功，可以正常播放----4
	public static final String PAUSE = "PAUSE";//视频暂停-----7
	

	public static final int uploadSizeLimit =512000;
	
	
	
	
	
	
	
	
	//Date和String互相转换
	public static String dateToString(Date date) {  
		String stringDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date); 

		return stringDate;  
	}  
	public static Date stringToDate(String str) {  
		Date dateString = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
			dateString  = format.parse(str);
		
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return dateString; 
	}
	
}
