package org.ferrari.utils.upload;

/**
 * 
   * @create.date: Mar 10, 2012 5:11:10 PM     
   * @see: org.ferrari.utils.upload
   * @author: aaron.han
   * @modify.by: aaron.han
   * @modify.date: Mar 10, 2012 5:11:10 PM
 */
public enum CallName {

	/** 车型实拍图和车展车型图片 **/
	CAR {public String getName(){return "1";}},
	
	/** 车型官图图解及活动 和 人物事件类图片**/
	CAROTHER {public String getName(){return "2";}},
    
	/** 车模**/
	SEXMODEL{public String getName(){return "3";}},
    
	/** LOGO**/
	LOGO {public String getName(){return "4";}},
	
	/** 新闻**/
	NEWS {public String getName(){return "5";}},
	
	/** 口碑图片**/
	REPUTATION {public String getName(){return "7";}},
	/** 全景看车flash**/
	CAR360 {public String getName(){return "11";}},
	/** 用品图片**/
	PRODUCTION {public String getName(){return "15";}},
	
	/**汽车天气图片**/
	WEATHER {public String getName(){return "21";}}
	;

	public abstract String getName();
	
}
