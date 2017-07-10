package org.ferrari.utils.upload;

import org.ferrari.utils.Digital;
import org.ferrari.utils.LetterConvert;

/**
 * 
   * @create.date: May 20, 2009 3:55:13 PM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 20, 2009 3:55:13 PM
 */
public class FtpBean {
//品牌名	
private String ftpBrand;
//系列名
private String ftpSeries;
//型号名
private String ftpModel;
//相册组合名
private String ftpPhotos;
//用户类型
private CallName callName; 
//自定宽度
private String width;
//自定高度
private String height;
//图片原宽度
private Integer oldWidth;
//图片原高度
private Integer oldHeight;
//是否加水印
private String addPicture=Digital.ZERO;
//视频分类名
private String videoType;



public FtpBean(String ftpPhotos, CallName callName) {
	
	this.ftpPhotos = LetterConvert.getValues(ftpPhotos);
	this.callName = callName;
}
public String getVideoType() {
	return videoType;
}
public void setVideoType(String videoType) {
	this.videoType = videoType;
}

public FtpBean(String ftpBrand, String ftpSeries, String ftpModel,
		CallName callName, String width, String height, String addPicture) {
	
	this.ftpBrand = LetterConvert.getValues(ftpBrand);
	this.ftpSeries =LetterConvert.getValues(ftpSeries);
	this.ftpModel = LetterConvert.getFirstValue(ftpModel);
	this.callName = callName;
	this.width = width;
	this.height = height;
	this.addPicture = addPicture;
}

public String getAddPicture() {
	return addPicture;
}
public void setAddPicture(String addPicture) {
	this.addPicture = addPicture;
}
public String getWidth() {
	return width;
}
public void setWidth(String width) {
	this.width = width;
}
public String getHeight() {
	return height;
}
public void setHeight(String height) {
	this.height = height;
}

public CallName getCallName() {
	return callName;
}
public void setCallName(CallName callName) {
	this.callName = callName;
}


public FtpBean(CallName callName) {
	
	this.callName = callName;
}
public FtpBean(String ftpBrand, String ftpSeries, String ftpModel,
		 CallName callName) {

	this.ftpBrand = LetterConvert.getValues(ftpBrand);
	this.ftpSeries =LetterConvert.getValues(ftpSeries);
	this.ftpModel = LetterConvert.getFirstValue(ftpModel);
	
	this.callName = callName;
}
public String getFtpPhotos() {
	return ftpPhotos;
}
public void setFtpPhotos(String ftpPhotos) {
	this.ftpPhotos = ftpPhotos;
}
public String getFtpBrand() {
	return ftpBrand;
}
public void setFtpBrand(String ftpBrand) {
	this.ftpBrand = ftpBrand;
}
public String getFtpSeries() {
	return ftpSeries;
}
public void setFtpSeries(String ftpSeries) {
	this.ftpSeries = ftpSeries;
}
public String getFtpModel() {
	return ftpModel;
}
public void setFtpModel(String ftpModel) {
	this.ftpModel = ftpModel;
}
public Integer getOldWidth() {
	return oldWidth;
}
public void setOldWidth(Integer oldWidth) {
	this.oldWidth = oldWidth;
}
public Integer getOldHeight() {
	return oldHeight;
}
public void setOldHeight(Integer oldHeight) {
	this.oldHeight = oldHeight;
}



}
