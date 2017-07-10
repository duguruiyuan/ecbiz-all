package com.mlj.ecbiz.model.product;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductBrand implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private String brandName;
    private String englishName;
    private String thumbLogo;
    private String brandLogo;
    private String brandDesc;
    private String aliasName;
    private String manufacturer;
    private String officialUrl;
    private String telephone;
    private String content;
    private Long sortOrder;
    private Long isShow;
    private String english;
}
