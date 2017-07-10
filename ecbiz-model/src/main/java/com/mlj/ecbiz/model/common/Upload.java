package com.mlj.ecbiz.model.common;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.struts.upload.FormFile;
@Data
@EqualsAndHashCode(callSuper = false)
public class Upload  implements Serializable{
	private static final long serialVersionUID = 1L;
	private FormFile imageUrl;
	private String addPicture;
	private Integer waterMask;
	private String imageLink;
}
