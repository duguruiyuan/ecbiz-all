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
 * @Description: 走势图显示类型
 * @see: org.ferrari.domain
 * @modify by: kevin
 * @time: 2009-2-5
 */
public class ChartType extends CodeType {
	private static final long serialVersionUID = -8024868171400678814L;
	public final static ChartType type = new ChartType();
	public final static Code LINE    = new Code(type, "1", "线形图");
	public final static Code COLUMN    = new Code(type, "2", "柱形图");
	public final static Code PIE   = new Code(type, "3", "饼形图");
}
