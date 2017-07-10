package org.ferrari.domain;

import java.util.List;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

public class NewsSubjectPKType extends CodeType {

	private static final long serialVersionUID = -3444959180957116288L;
	public final static NewsSubjectPKType type = new NewsSubjectPKType();
	
	public final static Code PKmap1  = new Code(type, "1", "车坛巡阅使");
	public final static Code PKmap2  = new Code(type, "2", "车型初体验");
	public final static Code PKmap3  = new Code(type, "3", "配置评估区");
	public final static Code PKmap4  = new Code(type, "4", "性能比擂台");
	public final static Code PKmap5  = new Code(type, "5", "费用计算器");
	public final static Code PKmap6  = new Code(type, "6", "最终角逐");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(NewsSubjectPKType.PKmap1.getName());
		System.out.println(NewsSubjectPKType.PKmap2.getValue());
		
		List<Code> list = NewsSubjectPKType.type.getCodeList();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getName());
		}
	}

}
