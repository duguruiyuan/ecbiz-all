 /**     
   * @Create: Jul 14, 2009 2:24:44 PM     
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
 * @Create: Jul 14, 2009 2:24:44 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 14, 2009
 */
public class UsedTimeSortKeys extends CodeType {
	private static final long serialVersionUID = 8693062463711376287L;
	public final static UsedTimeSortKeys type = new UsedTimeSortKeys();
	public final static Code TIME_DESC  = new Code(type,"desc","由晚到早");
	public final static Code TIME_ASC  = new Code(type,"asc","由早到晚");

}
