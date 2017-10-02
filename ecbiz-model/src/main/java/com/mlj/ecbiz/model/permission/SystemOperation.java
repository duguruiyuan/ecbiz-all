package com.mlj.ecbiz.model.permission;

import java.io.Serializable;
import java.util.Date;

import org.ferrari.utils.Code;

import com.mlj.ecbiz.model.common.UserOffKeys;

public class SystemOperation  implements Serializable {
	private static final long serialVersionUID = -1L;
	private Integer id;
	private String name;
	private String url;
	private String description;
	private Date createTime;
	private Code state = UserOffKeys.type.getDefaultValue();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Code getState() {
		return state;
	}
	public void setState(Code state) {
		this.state = state;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj)
			return true;
		if(!(obj instanceof SystemOperation))
			return false;
		SystemOperation resource = (SystemOperation)obj;
		if(resource.getId().equals(this.getId())&&resource.getName().equals(this.getName())){
			
			return true;
		}else{
			return false;
		}
		
	}
	public int hashCode() {
		// TODO Auto-generated method stub
		return 31*this.getId()+this.getName().hashCode();
	}
}
