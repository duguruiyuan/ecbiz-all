 /**     
   * @Create: Jul 11, 2009 5:11:33 PM     
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
 * @Create: Jul 11, 2009 5:11:33 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: 林宝玉 
 * @time: Jul 11, 2009 5:11:33 PM
 */
public class BrakingMode extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	public final static BrakingMode type = new BrakingMode();
	public final static Code DEFAULT  = new Code(type, "制动方式", "制动方式");
	public final static Code ONE  = new Code(type, "前盘后毂", "前盘后毂");
	public final static Code TWO  = new Code(type, "四轮毂刹", "四轮毂刹");
	public final static Code THREE  = new Code(type, "四轮碟刹", "四轮碟刹");
}
