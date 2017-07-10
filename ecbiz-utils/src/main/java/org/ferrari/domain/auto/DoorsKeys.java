 /**     
   * @Create: Jul 11, 2009 2:38:06 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.DoorsStatus;

 /**  
 * @Create: Jul 11, 2009 2:38:06 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class DoorsKeys extends CodeType {
	private static final long serialVersionUID = 4940045215630490407L;
	
	public static final DoorsKeys type = new DoorsKeys();
	public final static Code DEFAULT  = new Code(type, DoorsStatus.DEFUALT, "门数");
	public final static Code DOOR_TWO  = new Code(type, DoorsStatus.DOOR_TWO, "2门");
	public final static Code DOOR_THREE  = new Code(type, DoorsStatus.DOOR_THREE, "3门");
	public final static Code DOOR_FOUR  = new Code(type, DoorsStatus.DOOR_FOUR, "4门");
	public final static Code DOOR_FIVE  = new Code(type, DoorsStatus.DOOR_FIVE, "5门");

}
