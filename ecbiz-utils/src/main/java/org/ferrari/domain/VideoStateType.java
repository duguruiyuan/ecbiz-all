package org.ferrari.domain;
import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
public class VideoStateType extends CodeType {
	private static final long serialVersionUID = -3444959180957116288L;
	public final static VideoStateType type = new VideoStateType();
	public final static Code YES  = new Code(type, "1", "正常");
	public final static Code NO  = new Code(type,"0","锁定");
	

}
