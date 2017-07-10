 /**     
   * @Create: Jul 11, 2009 3:51:24 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.TravelKilometerStatus;

 /**  
 * @Create: Jul 11, 2009 3:51:24 PM     
 * @Description: 行驶公里
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class TravelKilometerKeys extends CodeType {
	private static final long serialVersionUID = -1115259355141424147L;
	public static final TravelKilometerKeys type = new TravelKilometerKeys();
	
	/** 默认 */
	public final static Code TravelKilometer  = new Code(type, TravelKilometerStatus.DEFALUT, "行驶公里");
	/** 1万以内 */
	public final static Code TravelKilometer1  = new Code(type, TravelKilometerStatus.TravelKilometer1, "1万以内");
	/** 1-3万 */
	public final static Code TravelKilometer2  = new Code(type, TravelKilometerStatus.TravelKilometer2, "1-3万");
	/** 3-6万 */
	public final static Code TravelKilometer3  = new Code(type, TravelKilometerStatus.TravelKilometer3, "3-6万");
	/** 6-10万 */
	public final static Code TravelKilometer4  = new Code(type, TravelKilometerStatus.TravelKilometer4, "6-10万");
	/** 10-20万 */
	public final static Code TravelKilometer5  = new Code(type, TravelKilometerStatus.TravelKilometer5, "10-20万");
	/** 20万以上 */
	public final static Code TravelKilometer6  = new Code(type, TravelKilometerStatus.TravelKilometer6, "20万以上");
	
}
