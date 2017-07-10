 /**     
   * @Create: Jul 11, 2009 12:09:53 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.ColorsStatus;

/**  
 * @Create: Jul 11, 2009 12:09:53 PM     
 * @Description: TODO
 * @see: org.ferrari.utils.constant
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class ColorKeys extends CodeType{
	private static final long serialVersionUID = 9131687169458489700L;
	public final static ColorKeys type = new ColorKeys();

	public final static Code DEFAULT  = new Code(type, ColorsStatus.DEFALUT, "颜色");
	public final static Code BLACK  = new Code(type, ColorsStatus.BLACK, "黑色");
	public final static Code ARGENTINE = new Code(type,ColorsStatus.ARGENTINE,"银色");
	public final static Code WHITE = new Code(type,ColorsStatus.WHITE,"白色");
	public final static Code GRAY = new Code(type,ColorsStatus.GRAY,"灰色");
	public final static Code RED = new Code(type,ColorsStatus.RED,"红色");
	public final static Code YELLOW = new Code(type,ColorsStatus.YELLOW,"黄色");
	public final static Code BLUE = new Code(type,ColorsStatus.BLUE,"蓝色");
	public final static Code GREEN = new Code(type,ColorsStatus.GREEN,"绿色");
	public final static Code CYAN = new Code(type,ColorsStatus.CYAN,"青色");
	public final static Code ORANGE = new Code(type,ColorsStatus.ORANGE,"橙色");
	public final static Code AMETHYST = new Code(type,ColorsStatus.AMETHYST,"紫色");
	public final static Code GOLD = new Code(type,ColorsStatus.GOLD,"金色");
	public final static Code OTHRE = new Code(type,ColorsStatus.OTHER,"其它");
}
