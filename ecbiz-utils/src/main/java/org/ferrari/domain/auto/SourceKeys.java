/**     
 * @Create: Jul 11, 2009 2:48:01 PM     
 * @Description: TODO
 * @Company: 车马驿站
 * @author: zhaobingbing
 * @Copyright: (c) 2008 by zhaobingbing.  
 * @version: 1.0   
 */
package org.ferrari.domain.auto;

import org.ferrari.utils.Code;
import org.ferrari.utils.CodeType;
import org.ferrari.utils.constant.auto.SourceStatus;

/**
 * @Create: Jul 11, 2009 2:48:01 PM
 * @Description: TODO
 * @see: org.ferrari.domain.auto
 * @modify by: zhaobingbing
 * @time: Jul 11, 2009
 */
public class SourceKeys extends CodeType {
	private static final long serialVersionUID = -2883882406098261285L;

	public static final SourceKeys type = new SourceKeys();

	public final static Code DEFALUT = new Code(type, SourceStatus.DEFALUT,
			"车辆来源");

	public final static Code DEALER = new Code(type, SourceStatus.DEALER, "经销商");

	public final static Code PERSONAL = new Code(type, SourceStatus.PERSONAL,
			"个人");

}
