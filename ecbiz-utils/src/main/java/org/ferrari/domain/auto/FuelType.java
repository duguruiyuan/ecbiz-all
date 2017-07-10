 /**     
   * @Create: Jul 11, 2009 4:07:48 PM     
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
 * @Create: Jul 11, 2009 4:07:48 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: 林宝玉 
 * @time: Jul 11, 2009 4:07:48 PM
 */
public class FuelType extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	public final static FuelType type = new FuelType();
	public final static Code ONE  = new Code(type, "燃料类型", "燃料类型");
	public final static Code TWO  = new Code(type, "柴油", "柴油");
	public final static Code THREE  = new Code(type, "汽油", "汽油");
}
