package com.mlj.ecbiz.model.product;

import java.io.Serializable;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductCategory implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
	private List<ProductCategory> childCategoryList;
    private Long id;
    private String catName;
    private Long parentId;
    private Long sortOrder=1L;
    private Long isShow=0L;
    private String typeId;
    private String depthPath;
    
}
