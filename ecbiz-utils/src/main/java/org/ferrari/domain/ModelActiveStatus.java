 /**     
   * @create.date: 2009-6-15 上午11:31:17
   * @author: kevin 
   * @company: 车马驿站
   * @see:org.ferrari.domain 
   */ 
package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.ModelActiveKeys;

 /**  
 * @create.date: 2009-6-15 上午11:31:17     
 * @comment: <p>车型活动状态</p>
 * @see: org.ferrari.domain
 * @author: kevin
 * @verson: 1.0
 */
public class ModelActiveStatus extends CodeType{
	private static final long serialVersionUID = -1620590318723133583L;
	public final static ModelActiveStatus type = new ModelActiveStatus();
	public final static Code YES  = new Code(type, ModelActiveKeys.YES, "已上市");
	public final static Code NO  = new Code(type, ModelActiveKeys.NO, "未上市");
	public final static Code STOP  = new Code(type, ModelActiveKeys.STOP, "已停产");
}
