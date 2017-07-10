 /**     
   * @create.date: 2009-5-26 下午06:54:26
   * @author: kevin 
   * @company: 车马驿站
   * @see:org.ferrari.domain 
   */ 
package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

 /**  
 * @create.date: 2009-5-26 下午06:54:26     
 * @comment: <p>TODO</p>
 * @see: org.ferrari.domain
 * @author: kevin
 * @verson: 1.0
 */
public class UploadParaType extends CodeType{
	private static final long serialVersionUID = 8271224346395156535L;
	public final static UploadParaType type = new UploadParaType();
	public final static Code CAR  = new Code(type, "1", "车型实拍图和车展车型图片");
	public final static Code CAROTHER   = new Code(type, "2", "车型官图图解及活动和人物事件类图片");
	public final static Code SEXMODEL = new Code(type, "3", "车模");
	public final static Code LOGO  = new Code(type, "4", "LOGO");
	public final static Code NEWS  = new Code(type, "5", "新闻");
	public final static Code REPUTATION  = new Code(type, "7", "口碑图片");
	
}
