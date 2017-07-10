package org.ferrari.domain;

import java.util.List;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;

public class ModelBitmapType extends CodeType {

	private static final long serialVersionUID = -3444959180957116288L;
	public final static ModelBitmapType type = new ModelBitmapType();
	
	public final static Code Bitmap1  = new Code(type, "1", "头图");
	public final static Code Bitmap2  = new Code(type, "2", "正前");
	public final static Code Bitmap3  = new Code(type, "3", "正后");
	public final static Code Bitmap4  = new Code(type, "4", "正侧");
	public final static Code Bitmap5  = new Code(type, "5", "中控全图");
	public final static Code Bitmap6  = new Code(type, "6", "驾驶位");
	public final static Code Bitmap7  = new Code(type, "7", "中控台");
	public final static Code Bitmap8  = new Code(type, "8", "副驾驶");
	public final static Code Bitmap9  = new Code(type, "9", "方向盘");
	public final static Code Bitmap10  = new Code(type, "10", "仪表盘");
	public final static Code Bitmap11  = new Code(type, "11", "档把");
	public final static Code Bitmap12  = new Code(type, "12", "驻车制动");
	public final static Code Bitmap13  = new Code(type, "13", "前排空间");
	public final static Code Bitmap14  = new Code(type, "14", "后排空间");
	public final static Code Bitmap15  = new Code(type, "15", "后备箱");
	public final static Code Bitmap16  = new Code(type, "16", "门窗控制");
	public final static Code Bitmap17  = new Code(type, "17", "发动机舱");
	public final static Code Bitmap18  = new Code(type, "18", "前轮");
	public final static Code Bitmap19  = new Code(type, "19", "备胎");
	public final static Code Bitmap20  = new Code(type, "20", "车钥匙");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(ModelBitmapType.Bitmap1.getName());
		System.out.println(ModelBitmapType.Bitmap2.getValue());
		
		List<Code> list = ModelBitmapType.type.getCodeList();
		for(int i=0; i<list.size(); i++){
			System.out.println(list.get(i).getValue());
		}
	}

}
