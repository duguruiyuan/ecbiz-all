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
public class CodeStatus extends CodeType{
	private static final long serialVersionUID = -2695184391465964304L;
	public final static CodeStatus type = new CodeStatus();
	public final static Code YES  = new Code(type, StatusKeys.YES, "正常");
	public final static Code NO  = new Code(type, StatusKeys.LOCKED, "锁定");
}
