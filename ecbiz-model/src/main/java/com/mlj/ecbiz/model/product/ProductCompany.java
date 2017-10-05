package com.mlj.ecbiz.model.product;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductCompany implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long id;
    private String logo;
    private String brand;
    private String name;
    private String english;
    private String url;
    private String tel;
    private String description;
    private Long isshow;
}
