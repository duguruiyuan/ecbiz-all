package org.ferrari.utils.image;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import magick.CompositeOperator;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.ferrari.utils.upload.DirectoryFactory;

import com.chexun.common.utils.upload.FtpReturnBean;

public class JmagickImageUtils {
	static byte WATER_MARKS_RIGHT[] = new byte[1024 * 10];
	static byte WATER_MARKS_LEFT[] = new byte[1024 * 10];

	static{
          //不能漏掉这个，不然jmagick.jar的路径找不到
          System.setProperty("jmagick.systemclassloader","no");
          
          InputStream input_water_right = ImageWaterMark.class.getResourceAsStream("chexun_logo.png");
	
          InputStream input_water_left = ImageWaterMark.class.getResourceAsStream("chexun_black.gif");
          
          try{
        	  input_water_left.read(WATER_MARKS_LEFT);
        	  input_water_left.close();
        	  
        	  input_water_right.read(WATER_MARKS_RIGHT);
        	  input_water_right.close();
          }catch(Exception ex){
        	  ex.printStackTrace();
          }

    }
	
        /**
		 *  * 压缩图片，不变形
		 *  *
		 * @param filePath
		 *            源文件路径
		 *  *
		 * @param toPath
		 *            缩略图路径
		 *  *
		 * @param width
		 *            设定宽
		 *  *
		 * @param height
		 *            设定长
		 * 
		 */

      public static void changeSize(String filePath, String toPath, int width,
			int height) throws MagickException {

		ImageInfo info = null;

		MagickImage image = null;

		Dimension imageDim = null;

		MagickImage scaled = null;

		try {

			info = new ImageInfo(filePath);

			image = new MagickImage(info);

			imageDim = image.getDimension();

			// 图片尺寸的大小处理，如果长宽都小于规定大小，则返回，如果有一个大于规定大小，则等比例缩放

			int srcH = imageDim.width;

			int srcW = imageDim.height;

			if (srcH <= height && srcW <= width) {

				return;

			}

			int tmpH = width;

			int tmpW = height;

			// 在长度和宽度都做了限制，不能超过设定值

			while (srcH > height || srcW > width) {

				if (srcW > width) {

					tmpH = srcH * width / srcW;

					srcH = tmpH;

					srcW = width;

				}

				if (srcH > height) {

					tmpW = srcW * height / srcH;

					srcW = tmpW;

					srcH = height;

				}

			}

			scaled = image.scaleImage(srcW, srcH);// 小图片文件的大小.

			scaled.setFileName(toPath);

			scaled.writeImage(info);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (scaled != null) {
				scaled.destroyImages();
			}

		}

	 }
    
      /**

    　　* 水印(图片logo)

    　　* @param filePath  源文件路径

    　　* @param toImg     修改图路径

    　　* @param logoPath  logo图路径

    　　* @throws MagickException

    　　*/

	public static void initLogoImg(String filePath, String toImg,
			String logoPath) throws MagickException {

		ImageInfo info = new ImageInfo();

		MagickImage fImage = null;

		MagickImage sImage = null;

		MagickImage fLogo = null;

		MagickImage sLogo = null;

		Dimension imageDim = null;

		Dimension logoDim = null;

		try {

			fImage = new MagickImage(new ImageInfo(filePath));

			imageDim = fImage.getDimension();

			int width = imageDim.width;

			int height = imageDim.height;

			if (width > 660) {

				height = 660 * height / width;

				width = 660;

			}

			sImage = fImage.scaleImage(width, height);

			fLogo = new MagickImage(new ImageInfo(logoPath));

			logoDim = fLogo.getDimension();

			int lw = width / 8;

			int lh = logoDim.height * lw / logoDim.width;

			sLogo = fLogo.scaleImage(lw, lh);

			sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
					width - (lw + lh / 10), height - (lh + lh / 10));

			sImage.setFileName(toImg);

			sImage.writeImage(info);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (sImage != null) {

				sImage.destroyImages();

			}

		}

	}
	
	/**

	　　* 切图

	　　* @param imgPath 源图路径

	　　* @param toPath  修改图路径

	　　* @param w  宽度

	　　* @param h 高度

	　　* @param x 左上角的 X 坐标

	　　* @param y 左上角的 Y 坐标

	　　* @throws MagickException

	　　*/

	public static void cutImg(String imgPath, String toPath, int w, int h,
			int x, int y) throws MagickException {

		ImageInfo infoS = null;

		MagickImage image = null;

		MagickImage cropped = null;

		Rectangle rect = null;

		try {

			infoS = new ImageInfo(imgPath);
			infoS.setQuality(90);
			
			image = new MagickImage(infoS);

			rect = new Rectangle(x, y, w, h);

			cropped = image.cropImage(rect);

			cropped.setFileName(toPath);

			cropped.writeImage(infoS);

		}catch (Exception ex) {
			throw new MagickException(ex.getMessage());
		}finally {

			if (cropped != null) {

				cropped.destroyImages();

			}

		}

	}
	
	public static void cutImage(byte[] srcBytes, BufferedOutputStream out, int width,
			int height, boolean force)  {
		
		MagickImage cropped = null;

		try {
			ImageInfo infoS = new ImageInfo();
			MagickImage image = new MagickImage(infoS, srcBytes);
			
//			image.profileImage("IPTC", null);
//			image.profileImage("ICC", null);
			
			infoS.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(imageDim.width, imageDim.height, width,
						height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(imageDim.width, imageDim.height, width,
						height);
			}
			
			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;
			
			if(finalWidth<500){
				image.profileImage("*", null);
			}
			 
//			image.setImageAttribute("EXIF:Model", null);
//			image.setImageAttribute("EXIF:Software", null);
//			image.setImageAttribute("EXIF:DateTime", null);
//			image.setImageAttribute("EXIF:DateTimeOriginal", null);
//			image.setImageAttribute("EXIF:DateTimeDigitized", null);
			
			cropped = image.cropImage(area);
			cropped = cropped.scaleImage(finalWidth, finalHeight);
			
			out.write(cropped.imageToBlob(infoS));
			out.close();
			
			image.destroyImages();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}	
	}
	
	public static byte[] cutImage(byte[] srcBytes, int width,
			int height, boolean force)  {
		MagickImage cropped = null;
		byte[] result=null;
		try{
			ImageInfo infoS = new ImageInfo();
			
			MagickImage image = new MagickImage(infoS, srcBytes);
//			image.profileImage("*", null);
			infoS.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(imageDim.width, imageDim.height, width,
						height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(imageDim.width, imageDim.height, width,
						height);
			}
			
			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;
			
			if(finalWidth<500){
				image.profileImage("*", null);
			}
			
			cropped = image.cropImage(area);
			cropped = cropped.scaleImage(finalWidth, finalHeight);
			result = cropped.imageToBlob(infoS);
			
			image.destroyImages();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
		return result;	
	}
	public static byte[] cutImageTest(byte[] srcBytes,BufferedOutputStream out, Rectangle rec,int imageWidth, int imageHeight)  {
		MagickImage cropped = null;
		byte[] result=null;
		try{
			ImageInfo infoS = new ImageInfo();
			MagickImage image = new MagickImage(infoS, srcBytes);
			infoS.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			Rectangle area = null;
			double width = imageDim.getWidth();
			double height = imageDim.getHeight();
			int left = rec.x;
		       int top = rec.y;
		       if (width > imageWidth && height > imageHeight) {
		           if (width / imageWidth > height / imageHeight) {
		        	   left = (int)(left*(height/imageHeight));
		        	   top = (int)(top*(height/imageHeight));
		        	   rec.width = (int)(rec.width*(height/imageHeight));
		        	   rec.height = (int)(rec.height*(height/imageHeight));
		           }
		           else {
		        	   left = (int)(left*(width/imageWidth));
		        	   top = (int)(top*(width/imageWidth));
		        	   rec.width = (int)(rec.width*(width/imageWidth));
		        	   rec.height = (int)(rec.height*(width/imageWidth));
		           }
		       }else if(width < imageWidth && height < imageHeight){
		    	   if (imageWidth/width > imageHeight/ height) {
		        	   left = (int)(left*(height/imageHeight));
		        	   top = (int)(top*(height/imageHeight));
		        	   rec.width = (int)(rec.width*(height/imageHeight));
		        	   rec.height = (int)(rec.height*(height/imageHeight));
		           }
		           else {
		        	   left = (int)(left*(width/imageWidth));
		        	   top = (int)(top*(width/imageWidth));
		        	   rec.width = (int)(rec.width*(width/imageWidth));
		        	   rec.height = (int)(rec.height*(width/imageWidth));
		           }
		       }
		     area = new Rectangle((int)left,(int)top,rec.width,rec.height);
			
			cropped = image.cropImage(area);
			cropped = cropped.scaleImage(rec.width, rec.height);
			out.write(cropped.imageToBlob(infoS));
			out.close();
			image.destroyImages();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
		return result;	
	}
	public static FtpReturnBean cutImage2(byte[] srcBytes, BufferedOutputStream out, int width,
			int height, boolean force)  {
		
		FtpReturnBean returnBean = new FtpReturnBean();
		
		MagickImage cropped = null;

		try {
			ImageInfo infoS = new ImageInfo();
			MagickImage image = new MagickImage(infoS, srcBytes);
			
			infoS.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(imageDim.width, imageDim.height, width,
						height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(imageDim.width, imageDim.height, width,
						height);
			}
			
			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;
			
			if(finalWidth<500){
				image.profileImage("*", null);
			}
			
			if((float)imageDim.width/imageDim.height>=1){
				returnBean.setImageResolution("H");
			}else{
				returnBean.setImageResolution("V");
			}
			
			if(judge(imageDim.width,imageDim.height,finalWidth,finalHeight)){
				cropped = image.cropImage(area);
				cropped = cropped.scaleImage(finalWidth, finalHeight);
				
				out.write(cropped.imageToBlob(infoS));
				out.close();
				
				image.destroyImages();
				
				returnBean.setImageStandard("1");
			}else{
				out.write(srcBytes);
				out.close();
				returnBean.setImageStandard("0");
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
		return returnBean;
	}
	
	public static FtpReturnBean cutImage2(byte[] srcBytes, int width,
			int height, boolean force)  {
		
		FtpReturnBean returnBean = new FtpReturnBean();
		MagickImage cropped = null;
		byte[] result=null;
		try{
			ImageInfo infoS = new ImageInfo();
			
			MagickImage image = new MagickImage(infoS, srcBytes);
			infoS.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			Rectangle area = null;

			if (force) {
				area = getMaxCutSize(imageDim.width, imageDim.height, width,
						height);
			} else {
				// 计算缩放尺寸
				area = getMaxZoomSize(imageDim.width, imageDim.height, width,
						height);
			}
			
			int finalWidth = force ? width : area.width;
			int finalHeight = force ? height : area.height;
			
			if(finalWidth<500){
				image.profileImage("*", null);
			}
			
			if((float)imageDim.width/imageDim.height>=1){
				returnBean.setImageResolution("H");
			}else{
				returnBean.setImageResolution("V");
			}
			
			if(judge(imageDim.width,imageDim.height,finalWidth,finalHeight)){
				cropped = image.cropImage(area);
				cropped = cropped.scaleImage(finalWidth, finalHeight);
				result = cropped.imageToBlob(infoS);
			
				image.destroyImages();
				
				returnBean.setImageStandard("1");
			}else{
				result = srcBytes;
				returnBean.setImageStandard("0");
			}
			
			returnBean.setImageByte(result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
		return returnBean;	
	}
	
	public static int getWidth(byte[] srcBytes){
		try{
			ImageInfo infoS = new ImageInfo();
		
			MagickImage image = new MagickImage(infoS, srcBytes);
		
			Dimension imageDim = image.getDimension();
			
			return imageDim.width;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int getHeight(byte[] srcBytes){
		try{
			ImageInfo infoS = new ImageInfo();
		
			MagickImage image = new MagickImage(infoS, srcBytes);
		
			Dimension imageDim = image.getDimension();
			
			return imageDim.height;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static byte[] pressImage(byte[] src_image){
		
		
		MagickImage image = null;
		byte[] result=null;
		try{
			int x = 5, y = 5;
			ImageInfo info = new ImageInfo();
			
			
			image = new MagickImage(info, src_image);
//			image.profileImage("*", null);
			info.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			
			//MagickImage logoLeft = new MagickImage(new ImageInfo(),WATER_MARKS_LEFT);
			
			MagickImage logoRight = new MagickImage(new ImageInfo(),WATER_MARKS_RIGHT);
			Dimension logoRightDim = logoRight.getDimension();
			
			
			//image.compositeImage(CompositeOperator.AtopCompositeOp, logoLeft, x, y);
			image.compositeImage(CompositeOperator.AtopCompositeOp, logoRight, imageDim.width-logoRightDim.width-x, imageDim.height-logoRightDim.height-y);
			
			result = image.imageToBlob(info);
			
			//logoLeft.destroyImages();
			logoRight.destroyImages();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (image != null) {
				image.destroyImages();
			}
		}
		return result;
	}
	
	public static void pressImage(byte[] src_image, OutputStream out){
		
		MagickImage image = null;
		
		try{
			int x = 5, y = 5;
			
			ImageInfo info = new ImageInfo();
			image = new MagickImage(info, src_image);
//			image.profileImage("*", null);	
			info.setQuality(90);
			
			Dimension imageDim = image.getDimension();
			
			MagickImage logoLeft = new MagickImage(new ImageInfo(),WATER_MARKS_LEFT);
			
			MagickImage logoRight = new MagickImage(new ImageInfo(),WATER_MARKS_RIGHT);
			
			Dimension logoRightDim = logoRight.getDimension();
			
			
			image.compositeImage(CompositeOperator.AtopCompositeOp, logoLeft, x, y);
			image.compositeImage(CompositeOperator.AtopCompositeOp, logoRight, imageDim.width-logoRightDim.width-x, imageDim.height-logoRightDim.height-y);
			
			out.write(image.imageToBlob(info));
			out.close();
			
			logoLeft.destroyImages();
			logoRight.destroyImages();
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (image != null) {
				image.destroyImages();
			}
		}	
	}

	private static Rectangle getMaxCutSize(int w1, int h1, int w2, int h2) {
		int x, y, w3, h3;
		if ((float) w1 / w2 >= (float) h1 / h2) {
			h3 = h1; // 高度设为原高度
			w3 = h1 * w2 / h2; // 宽度按比率计算
		} else {
			w3 = w1; // 宽度设为原宽度
			h3 = w1 * h2 / w2; // 高度按比较计算
		}
		x = (w1 - w3) / 2;
		y = (h1 - h3) / 2;
		return new Rectangle(x > 0 ? x : 0, y > 0 ? y : 0, w3, h3);
	}
	
	private static Rectangle getMaxZoomSize(int w1, int h1, int w2, int h2) {
		int w3, h3;
		if (w1 <= w2 && h1 <= h2) {
			w3 = w1;
			h3 = h1;
		} else if ((float) w1 / w2 >= (float) h1 / h2) {
			w3 = w2;
			h3 = w2 * h1 / w1;
		} else {
			h3 = h2;
			w3 = h2 * w1 / h1;
		}
		return new Rectangle(w3, h3);
	}
	
	private static boolean judge(int w1, int h1, int w2, int h2){
		boolean flag = true;
		String standFlag = DirectoryFactory.get("image_standard_flag");
		if("1".equals(standFlag)){
			String stdWidth = "";
			String stdHeight = "";
			if((float)w1/h1>=1){
				if((float)w2/h2>=1){
					stdWidth = DirectoryFactory.get("image_standard_h_width");
					stdHeight = DirectoryFactory.get("image_standard_h_height");
				}else{
					flag = false;
					return flag;
				}
			}else{
				if((float)w2/h2<1){
					stdWidth = DirectoryFactory.get("image_standard_v_width");
					stdHeight = DirectoryFactory.get("image_standard_v_height");
				}else{
					flag = false;
					return flag;
				}
			}
			int width = Integer.parseInt(stdWidth);
			int height = Integer.parseInt(stdHeight);
			
			if(w1>=w2&&h1>=h2){
				flag = true;
			}else{
				if(w2>=width&&h2>=height){
					flag = false;
				}else{
					flag = true;
				}
			}
		}
		return flag;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
//			JmagickImageUtils.cutImg("D://src.jpg", "D://8.jpg", 1024, 768, 0, 0);
//			InputStream in = new FileInputStream("D://src.jpg");
//			
//			byte[] bytes = new byte[10243 * 1024];
//			in.read(bytes);
//			in.close();
//			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("D://33.jpg"));
//			
//			JmagickImageUtils.cutImage(bytes, out, 1024, 768, true);
//			DwindlePic dwind = new DwindlePic();
//			dwind.cutImage(in, out, 800, 600, true);
			
			String src = "D:\\jw.jpg";
			InputStream in = new FileInputStream(new File(src));
			byte[] src_image = new byte[1024 * 1024];
			in.read(src_image);
			in.close();
			
			JmagickImageUtils.pressImage(src_image, out);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
