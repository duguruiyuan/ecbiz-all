package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.StatusKeys;
/**
 * 
   * @create.date: 2009-5-20 上午10:01:35     
   * @comment: <p>status</p>
   * @see: org.ferrari.domain
   * @author: kevin
   * @verson: 1.0
 */
public class AllStatusKeys extends CodeType{
	private static final long serialVersionUID = -964475481337497622L;
	public final static AllStatusKeys type = new AllStatusKeys();
	public final static Code YES  = new Code(type, StatusKeys.YES, "正常");
	public final static Code NUAUDITED  = new Code(type, StatusKeys.NUAUDITED, "未审核");
	public final static Code NOTPASS  = new Code(type, StatusKeys.NOTPASS, "审核未通过");
	public final static Code LOCKED  = new Code(type, StatusKeys.LOCKED, "锁定");
	public final static Code CASLOCKED  = new Code(type, StatusKeys.CASLOCKED, "锁定");
}
