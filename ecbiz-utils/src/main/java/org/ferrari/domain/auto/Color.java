 /**     
   * @Create: Jul 11, 2009 4:35:34 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: 林宝玉 
   * @Copyright: (c) 2007 by 林宝玉.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

 /**  
 * @Create: Jul 11, 2009 4:35:34 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: 林宝玉 
 * @time: Jul 11, 2009 4:35:34 PM
 */
public class Color extends CodeType {
	private static final long serialVersionUID = 9131687169458489700L;
	public final static Color type = new Color();
	public final static Code DEFAULT  = new Code(type, "颜色", "颜色");
	public final static Code BLACK  = new Code(type, "黑色", "黑色");
	public final static Code ARGENTINE = new Code(type,"银色","银色");
	public final static Code WHITE = new Code(type,"白色","白色");
	public final static Code GRAY = new Code(type,"灰色","灰色");
	public final static Code RED = new Code(type,"红色","红色");
	public final static Code YELLOW = new Code(type,"黄色","黄色");
	public final static Code BLUE = new Code(type,"蓝色","蓝色");
	public final static Code GREEN = new Code(type,"绿色","绿色");
	public final static Code CYAN = new Code(type,"青色","青色");
	public final static Code ORANGE = new Code(type,"橙色","橙色");
	public final static Code AMETHYST = new Code(type,"紫色","紫色");
	public final static Code GOLD = new Code(type,"金色","金色");
	public final static Code DEFIND = new Code(type,"自定义","自定义");

}
