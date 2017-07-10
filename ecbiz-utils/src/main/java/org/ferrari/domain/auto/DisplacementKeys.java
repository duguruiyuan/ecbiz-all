 /**     
   * @Create: Jul 11, 2009 3:14:52 PM     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: zhaobingbing
   * @Copyright: (c) 2008 by zhaobingbing.  
   * @version: 1.0   
   */ 
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.DisplacementStatus;

 /**  
 * @Create: Jul 11, 2009 3:14:52 PM     
 * @Description: 排量
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class DisplacementKeys extends CodeType {
	private static final long serialVersionUID = -4534359980660060740L;
	public static final DisplacementKeys type = new DisplacementKeys();

	/** 默认 */
	public final static Code DISPLACEMENT  = new Code(type, DisplacementStatus.DEFAULT, "排量");
	/** 1.0以下 */
	public final static Code DISPLACEMENT1  = new Code(type, DisplacementStatus.DISPLACEMENT1, "1.0以下");
	/** 1.0--1.5 */
	public final static Code DISPLACEMENT2  = new Code(type, DisplacementStatus.DISPLACEMENT2, "1.0-1.5");
	/** 1.6--1.9 */
	public final static Code DISPLACEMENT3  = new Code(type, DisplacementStatus.DISPLACEMENT3, "1.6-1.9");
	/** 2.0--2.9 */
	public final static Code DISPLACEMENT4  = new Code(type, DisplacementStatus.DISPLACEMENT4, "2.0-2.9");
	/** 3.0--3.6 */
	public final static Code DISPLACEMENT5  = new Code(type, DisplacementStatus.DISPLACEMENT5, "3.0-3.6");
	/** 3.6以上 */
	public final static Code DISPLACEMENT6  = new Code(type, DisplacementStatus.DISPLACEMENT6, "3.6以上");
}
