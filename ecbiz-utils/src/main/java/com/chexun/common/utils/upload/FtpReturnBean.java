package com.chexun.common.utils.upload;

public class FtpReturnBean {

	private String imagePath;
	private String imageResolution;
	private String imageStandard;
	private byte[] imageByte;
	
	
	public byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageResolution() {
		return imageResolution;
	}
	public void setImageResolution(String imageResolution) {
		this.imageResolution = imageResolution;
	}
	public String getImageStandard() {
		return imageStandard;
	}
	public void setImageStandard(String imageStandard) {
		this.imageStandard = imageStandard;
	}
	
	
}
