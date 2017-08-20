package com.mlj.ecbiz.model.trade;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TradeInfo implements Serializable{
	public static final int DEFAULT_STATUS = 0;// 默认状态
	public static final int FREEZE_STATUS = 1;// 冻结
	public static final int DELETE_STATUS = 2;// 伪删除
    private Long tid;
    private Long status;
    private java.util.Date created;
    private java.util.Date payTime;
    private java.util.Date consignTime;
    private Long payType;
    private java.math.BigDecimal postFee;
    private java.math.BigDecimal payment;
    private String buyerNick;
    private String receiverName;
    private String receiverState;
    private String receiverAddress;
    private Long receiverZip;
    private Long receiverMobile;
}
