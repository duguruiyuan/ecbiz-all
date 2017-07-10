 /**     
   * @Create: Jul 11, 2009 2:32:44 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.GearboxStatus;

 /**  
 * @Create: Jul 11, 2009 2:32:44 PM     
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class GearboxKeys extends CodeType {
	private static final long serialVersionUID = -7228074316476770025L;
	public final static GearboxKeys type = new GearboxKeys();
	public final static Code DEFAULT  = new Code(type, GearboxStatus.DEFAULT, "变速箱");
	public final static Code MANUAL  = new Code(type, GearboxStatus.MANUAL, "手动");
	public final static Code AUTOMATIC  = new Code(type, GearboxStatus.AUTOMATIC, "自动");
	public final static Code TIPTRONIC  = new Code(type, GearboxStatus.TIPTRONIC, "手自一体");
	public final static Code SPEED  = new Code(type, GearboxStatus.SPEED, "无级变速");
}
