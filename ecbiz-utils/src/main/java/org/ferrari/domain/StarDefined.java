 /**     
   * @Create: 2009-2-5 下午01:17:44     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: kevin
   * @Copyright: (c) 2008 by kevin.  
   * @version: 1.0   
   */ 
package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

 /**  
 * @Create: 2009-2-5 下午01:17:44     
 * @Description: 星级定义图片显示
 * @see: org.ferrari.domain
 * @modify by: kevin
 * @time: 2009-2-5
 */
public class StarDefined extends CodeType {
	private static final long serialVersionUID = -6730497360142292115L;
	public final static StarDefined type = new StarDefined();
	public final static Code ONE = new Code(type, "/manage/images/xx_01.gif", "一星");
	public final static Code TWO = new Code(type, "/manage/images/xx_02.gif", "二星");
	public final static Code THREE = new Code(type, "/manage/images/xx_03.gif", "三星");
	public final static Code FOUR = new Code(type, "/manage/images/xx_04.gif", "四星");
	public final static Code FIVE = new Code(type, "/manage/images/xx_05.gif", "五星");
}
