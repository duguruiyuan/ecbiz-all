 /**     
   * @Create: Jul 11, 2009 2:16:58 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.VanStatus;

 /**  
 * @Create: Jul 11, 2009 2:16:58 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class VanKeys extends CodeType{
	private static final long serialVersionUID = 5369490817066324266L;
	public final static VanKeys type = new VanKeys();
	public final static Code DEFAULT  = new Code(type, VanStatus.DEFAULT, "厢式");
	public final static Code VAN_THREE  = new Code(type, VanStatus.VAN_THREE, "三厢");
	public final static Code VAN_TWO  = new Code(type, VanStatus.VAN_TWO, "两厢");

}
