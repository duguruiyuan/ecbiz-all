package com.mlj.ecbiz.model.product;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PhotoDetail implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private Long albumId;
    private java.util.Date createTime;
    private Long parentId;
    private Long clickNum;
    private Long scenePicType;
    private Long scenePicTypeSort;
    private Long picSort;
    private String picRemark;
    private String picType;
    private String fileType;
    private String fileMd5;
    private Long state;
    private Long width;
    private Long height;
    private Long picstate;
}
