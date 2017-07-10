 /**     
   * @Create: 2009-5-25 下午04:37:04     
   * @Description: TODO
   * @Company: 车马驿站
   * @author: jackie
   * @Copyright: (c) 2008 by jackie.  
   * @version: 1.0   
   */ 
package com.chexun.common.bean;

import java.io.Serializable;
import java.util.Date;

import org.ferrari.domain.UploadParaType;
import org.ferrari.utils.Code;

 /**  
 * @Create: 2009-5-25 下午04:37:04     
 * @Description: TODO
 * @see: com.jmyz.common.bean
 * @modify by: jackie
 * @time: 2009-5-25
 */
public class UploadParam implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	//参数名称
	private String name;
	//参数值
	private String value;
	//1.资讯2.相册3.车型库4.品牌logo5.二手车信息发布6.商城7.视频封面8. 资讯FckEdit9.店内公告FckEdit
	private String type;
	
	private Code typeText = UploadParaType.type.getDefaultValue();
	//参数描述
	private String description;
	//添加时间
	private Date createTime;
	//是否加水印,0不加，1加
	private String addpic;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the addpic
	 */
	public String getAddpic() {
		return addpic;
	}
	/**
	 * @param addpic the addpic to set
	 */
	public void setAddpic(String addpic) {
		this.addpic = addpic;
	}
	/**
	 * @return the typeText
	 */
	public Code getTypeText() {
		typeText.setValue(type);
		return typeText;
	}
	/**
	 * @param typeText the typeText to set
	 */
	public void setTypeText(Code typeText) {
		this.typeText = typeText;
	}
	
}
