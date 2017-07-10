package com.chexun.picinfo.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode(callSuper = false)
public class PhotoDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;//图片编号
	private Integer albumId;//相册编号
	private Date createTime;//上传时间
	private Integer parentId; //所属相册父类
	private Integer clickNum;//图片点击量
	private String picType;//图片分类
	private Integer scenePicType;//实拍图分类
	private Integer scenePicTypeSort;//实拍图各分类排列顺序
	private Integer picSort;//图片排列顺序
	private String picRemark;//图注
	private String fileType;//文件类型
	private String fileMd5;//文件MD5值
	private Integer state;//状态
	private Integer seriesId;//车系
	
	private Double width;//图片宽度
	private Double height;//图片长度
	private Integer picState;//0为 宽版    1为长版
	
	
	
	
	
	
}
