package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

public class CommonLogCategory extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	
	public final static CommonLogCategory type = new CommonLogCategory();
	
	public final static Code News  = new Code(type, "1", "资讯");
	public final static Code Photo  = new Code(type,"2","图片");
	public final static Code Video  = new Code(type,"3","视频");
	public final static Code User  = new Code(type,"4","用户管理");

}
