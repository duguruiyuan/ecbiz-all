 /**     
   * @Create: Jul 14, 2009 2:20:14 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

 /**  
 * @Create: Jul 14, 2009 2:20:14 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 14, 2009
 */
public class UsedPriceSortKeys extends CodeType {
	private static final long serialVersionUID = -6776895248550672884L;
	public final static UsedPriceSortKeys type = new UsedPriceSortKeys();
	public final static Code PRICE_DESC  = new Code(type,"desc","由高到低");
	public final static Code PRICE_ASC  = new Code(type,"asc","由低到高");
}
