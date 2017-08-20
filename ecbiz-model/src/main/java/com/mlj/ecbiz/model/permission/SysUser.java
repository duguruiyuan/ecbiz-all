package com.mlj.ecbiz.model.permission;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long uid;
    private String uname;
    private String password;
    private String name;
    private String organization;
    private String rId;
    private String rName;
    private String operator;
    private String tel;
    private String status;
    private java.util.Date created;
    private String note;
}
