package org.ferrari.domain;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.RecommendKeys;

public class RecommCodeKeys extends CodeType{
	private static final long serialVersionUID = -8394597898430635315L;
	public final static RecommCodeKeys type = new RecommCodeKeys();
	public final static Code NO  = new Code(type, RecommendKeys.NO, "否");
	public final static Code YES  = new Code(type, RecommendKeys.YES, "是");
}
