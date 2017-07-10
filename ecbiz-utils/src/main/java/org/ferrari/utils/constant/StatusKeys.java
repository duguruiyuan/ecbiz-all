 /**     
   * @create.date: 2009-5-5 下午12:22:12
   * @author: kevin 
   * @company: 车马驿站
   * @see:org.ferrari.utils 
   */ 
package org.ferrari.utils.constant;

 /**  
 * @create.date: 2009-5-5 下午12:22:12     
 * @comment: <p>TODO</p>
 * @see: org.ferrari.utils
 * @author: kevin
 * @verson: 1.0
 */
public class StatusKeys {
	/**正常状态*/
	public static final String YES = "1";
	/**等待审核状态*/
	public static final String NUAUDITED = "2";
	/**审核未通过*/
	public static final String NOTPASS = "3";
	/**单条锁定*/
	public static final String LOCKED = "4";
	/**级联锁定*/
	public static final String CASLOCKED = "5";
	/**删除*/
	public static final String DELETE = "6";
	/**白金等待审核*/
	public static final String WHITEEWAIT  = "7";
	/**白金审核不通过*/
	public static final String CHECKEKOUT = "8";
	
	/**拆车状态*/
	public static final String DISASSEMBLY_HIDDEN = "0";
	public static final String DISASSEMBLY_YES = "1";
	public static final String DISASSEMBLY_NOTICE = "2";
}
