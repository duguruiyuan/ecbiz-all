package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
/**
   * 
   * @Create: Jan 8, 2009 8:55:10 AM     
   * @Description: 星期的选项设置
   * @see: org.ferrari.domain
   * @modify by: kevin
   * @time: Jan 8, 2009 8:55:10 AM
 */
public class WEEK extends CodeType {
	private static final long serialVersionUID = 303757070919353434L;
	public final static WEEK type = new WEEK();
	public final static Code SELECT    = new Code(type, "", "请选择...");
	public final static Code MONDAY    = new Code(type, "MON", "星期一");
	public final static Code TUESDAY   = new Code(type, "TUE", "星期二");
	public final static Code WEDNESDAY = new Code(type, "WED", "星期三");
	public final static Code THURSDAY  = new Code(type, "THU", "星期四");
	public final static Code FRIDAY    = new Code(type, "FRI", "星期五");
	public final static Code SATURDAY  = new Code(type, "SAT", "星期六");
	public final static Code SUNDAY    = new Code(type, "SUN", "星期日");

}
