package com.mlj.ecbiz.model.common;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

public class UserOffKeys  extends CodeType{
	private static final long serialVersionUID = -3564595605231540951L;
	public final static UserOffKeys type = new UserOffKeys();
	public final static Code NORMAL  = new Code(type, "1", "正常");
	public final static Code LOCKED  = new Code(type, "0", "已注销");
	public final static Code IllEGAL = new Code(type, "2", "锁定"); 
}
