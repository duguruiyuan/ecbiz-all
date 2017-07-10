package org.ferrari.domain.auto;

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
public class UsedAgeKeys extends CodeType{
	private static final long serialVersionUID = -3444959180957116288L;
	public final static UsedAgeKeys type = new UsedAgeKeys();
	public final static Code DEFAULT  = new Code(type, "使用时间", "使用时间");
	public final static Code ONE  = new Code(type, "1年以下", "1年以下");
	public final static Code TWO  = new Code(type, "1-2年", "1-2年");
	public final static Code THREE  = new Code(type, "2-3年", "2-3年");
	public final static Code FOUR  = new Code(type, "3-4年", "3-4年");
	public final static Code FIVE  = new Code(type, "4-5年", "4-5年");
	public final static Code SIX  = new Code(type, "5-7年", "5-7年");
	public final static Code SEVEN  = new Code(type, "7-10年", "7-10年");
	public final static Code EIGHT  = new Code(type, "10年以上", "10年以上");
    
}

