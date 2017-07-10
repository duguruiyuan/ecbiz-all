package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
/**
 * 
   * @create.date: 2009-5-20 上午10:01:35     
   * @comment: <p>gender</p>
   * @see: org.ferrari.domain
   * @author: kevin
   * @verson: 1.0
 */
public class Gender extends CodeType{
	private static final long serialVersionUID = -3444959180957116288L;
	public final static Gender type = new Gender();
	public final static Code MALE  = new Code(type, "M", "男");
	public final static Code FEMALE  = new Code(type, "F", "女");
}
