 /**     
   * @Create: Jul 11, 2009 5:09:31 PM     
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
 * @Create: Jul 11, 2009 5:09:31 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: 林宝玉 
 * @time: Jul 11, 2009 5:09:31 PM
 */
public class IgnitionMode extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	public final static IgnitionMode type = new IgnitionMode();
	public final static Code DEFAULT  = new Code(type, "点火方式", "点火方式");
	public final static Code ONE  = new Code(type, "电喷式", "电喷式");
	public final static Code TWO  = new Code(type, "化油器", "化油器");
	public final static Code THREE  = new Code(type, "压燃式", "压燃式");
}
