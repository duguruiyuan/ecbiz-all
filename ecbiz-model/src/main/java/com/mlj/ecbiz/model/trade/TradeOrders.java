package com.mlj.ecbiz.model.trade;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TradeOrders implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long oid;
    private Long tid;
    private Long productsn;
    private String title;
    private String price;
    private Long num;
    private String sellerNick;
}
