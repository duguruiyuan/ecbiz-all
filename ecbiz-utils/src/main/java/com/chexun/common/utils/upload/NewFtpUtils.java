package com.chexun.common.utils.upload;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;
import org.ferrari.utils.Digital;
import org.ferrari.utils.EncryptMd5;
import org.ferrari.utils.image.ThumImageUtil;
import org.ferrari.utils.upload.DirectoryFactory;
import org.ferrari.utils.upload.FtpBean;
import org.ferrari.utils.upload.FtpClientFactory;
import org.ferrari.utils.upload.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chexun.picinfo.bean.PhotoDetail;
import com.enterprisedt.net.ftp.FTPFile;
import com.enterprisedt.net.ftp.FileTransferClient;

public class NewFtpUtils {
	public static final int BUFFER_SIZE = 16 * 1024;

	public static boolean isRightFomat(FormFile picture) {
		if (picture != null && picture.getFileSize() > 0) {
			Logger.getLogger(NewFtpUtils.class).debug(
					"图片mime:" + picture.getContentType());
			List<String> type = Arrays.asList("image/bmp", "image/png","image/gif", "image/jpg", "image/jpeg", "image/pjpeg","application/x-shockwave-flash",
					"flv-application/octet-stream");
			if (!type.contains(picture.getContentType().toLowerCase())) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}
	}
	public static String upload(CommonsMultipartFile picture, PhotoDetail photoDetailBean , FtpBean ftpBean) {
//		if (picture == null || photoDetailBean.getId() == null) {
//			return null;
//		}
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("/images/");
		int j = 0;
		String imageName = null;
		String height = null;
		String width = null;
//		if (ftpBean.getWidth() != null && !"".equals(ftpBean.getWidth())) {
//			return uploadsize(picture,photoDetailBean, ftpBean);
//		}
		FileTransferClient ftp = FtpClientFactory.getFtpClient();
//		List<UploadParam> uploadParams = Rules.getImageSize(ftpBean.getCallName());
		//组织上传路径的目录结构
		List<String> commurl = new ArrayList<String>();
		
		Date createTime = photoDetailBean.getCreateTime();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
		String curDate = time.format(createTime);
		//二层结构--年
		String year =curDate.substring(0, 4);
		commurl.add(year);
		//三层结构--月日
		String monthDay = curDate.substring(4,curDate.length());
		commurl.add(monthDay);
		//四层结构--每个文件夹只存500个picId
		int idMath = (int) Math.floor(photoDetailBean.getId()/500);
		commurl.add(String.valueOf(idMath));
		
		String oldname = picture.getOriginalFilename();
		String picIdMD5 = new EncryptMd5().getMD5ofStr(photoDetailBean.getId().toString());
		String md5Name = picIdMD5 + oldname.substring(oldname.lastIndexOf('.'));
		String callName= null;
		if(null !=ftpBean.getCallName() && !ftpBean.getCallName().equals("")){
			callName = DirectoryFactory.ReadDirectory(ftpBean.getCallName());
		}else{
			callName = "other";
		}
		String sizeName = null;
		String lastDir =null;
		try {
			ftp.connect();
			for (String dname : commurl) {
				if (j == 0) {
					sBuffer.append(dname + "/");
				}
				String[] names = ftp.directoryNameList(dname, false);
				if (!(names.length > 0)) {
					ftp.createDirectory(dname);
				}
				ftp.changeDirectory(dname);
				lastDir=dname;
			}
			

			FTPFile[] names = ftp.directoryList();
			if (!(names.length > 0)) {
				ftp.changeToParentDirectory();
				ftp.deleteDirectory(lastDir);
			}
			
			ftp.disconnect();

			String fatherString = sBuffer.toString();
			return imageName + "," +fatherString.substring(7,fatherString.length());

		} catch (Exception e) {
			e.printStackTrace();
//			throw new UtilsException(e);
			return null;
		}
		finally {
			try {
				if (ftp.isConnected())
					ftp.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
//				throw new UtilsException("关闭ftp连接失败", e);
				return null;
			}
		}
	}
	
	

	public static String uploadsize(FormFile picture,PhotoDetail photoDetailBean ,FtpBean ftpBean) {

		String imageName = null;
		int j = 0;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("/images/");
		FileTransferClient ftp = FtpClientFactory.getFtpClient();
		//组织上传路径的目录结构
		List<String> commurl = new ArrayList<String>();
		Date createTime = photoDetailBean.getCreateTime();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
		String curDate = time.format(createTime);
		//二层结构--年
		String year =curDate.substring(0, 4);
		commurl.add(year);
		//三层结构--月日
		String monthDay = curDate.substring(4,curDate.length());
		commurl.add(monthDay);
		//四层结构--每个文件夹只存500个picId
		int idMath = (int) Math.floor(photoDetailBean.getId()/500);
		commurl.add(String.valueOf(idMath));
		
		String oldname = picture.getFileName();
		String picIdMD5 = new EncryptMd5().getMD5ofStr(photoDetailBean.getId().toString());
		String md5Name = picIdMD5 + oldname.substring(oldname.lastIndexOf('.'));
		String callName= null;
		if(null !=ftpBean.getCallName() && !ftpBean.getCallName().equals("")){
			callName = DirectoryFactory.ReadDirectory(ftpBean.getCallName());
		}else{
			callName = "other";
		}
		String sizeName = "default";
		try {
			ftp.connect();
			for (String dname : commurl) {
				if (j == 0) {
					sBuffer.append(dname + "/");
				}
				String[] names = ftp.directoryNameList(dname, false);
				if (!(names.length > 0)) {
					ftp.createDirectory(dname);
				}
				ftp.changeDirectory(dname);
			}
			String height = ftpBean.getHeight();
			String width = ftpBean.getWidth();		
			
			imageName = callName +"_"+ sizeName +"_"+ md5Name;			

			byte[] bytes = picture.getFileData();
			OutputStream fileOutstream = ftp.uploadStream(imageName);

			if (Digital.ONE.equals(ftpBean.getAddPicture())) {
				byte[] temps = ThumImageUtil.cutImage(bytes, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
				ThumImageUtil.pressImage(temps, fileOutstream, imageName.substring(imageName.lastIndexOf(".") + 1));
			}else{
				BufferedOutputStream bufferout = new BufferedOutputStream(
						fileOutstream);
				ThumImageUtil.cutImage(bytes, bufferout, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
				bufferout.close();
			}
			ftp.changeDirectory("/");
			ftp.disconnect();

			String fatherString = sBuffer.toString();
			return imageName + "," +fatherString.substring(7,fatherString.length());

		} catch (Exception e) {
			e.printStackTrace();
//			throw new UtilsException(e);
			return null;

		}

		finally {

			try {
				if (ftp.isConnected())
					ftp.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
//				throw new UtilsException("关闭ftp连接失败", e);
				return null;
			}
		}
	}
	public static String uploadWithoutSize(FormFile picture,PhotoDetail photoDetailBean ,FtpBean ftpBean) {
		
		String imageName = null;
		int j = 0;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("/images/");
		FileTransferClient ftp = FtpClientFactory.getFtpClient();
		//组织上传路径的目录结构
		List<String> commurl = new ArrayList<String>();
		Date createTime = photoDetailBean.getCreateTime();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
		String curDate = time.format(createTime);
		//二层结构--年
		String year =curDate.substring(0, 4);
		commurl.add(year);
		//三层结构--月日
		String monthDay = curDate.substring(4,curDate.length());
		commurl.add(monthDay);
		//四层结构--每个文件夹只存500个picId
		int idMath = (int) Math.floor(photoDetailBean.getId()/500);
		commurl.add(String.valueOf(idMath));
		
		String oldname = picture.getFileName();
		String picIdMD5 = new EncryptMd5().getMD5ofStr(photoDetailBean.getId().toString());
		String md5Name = picIdMD5 + oldname.substring(oldname.lastIndexOf('.'));
		String callName= null;
		if(null !=ftpBean.getCallName() && !ftpBean.getCallName().equals("")){
			callName = DirectoryFactory.ReadDirectory(ftpBean.getCallName());
		}else{
			callName = "other";
		}
		String sizeName = "default";
		try {
			ftp.connect();
			for (String dname : commurl) {
				if (j == 0) {
					sBuffer.append(dname + "/");
				}
				String[] names = ftp.directoryNameList(dname, false);
				if (!(names.length > 0)) {
					ftp.createDirectory(dname);
				}
				ftp.changeDirectory(dname);
			}
			String height = ftpBean.getHeight();
			String width = ftpBean.getWidth();		
			
			imageName = callName +"_"+ sizeName +"_"+ md5Name;			
			
			byte[] bytes = picture.getFileData();
			OutputStream fileOutstream = ftp.uploadStream(imageName);
			ByteArrayInputStream in = new ByteArrayInputStream(bytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
			BufferedOutputStream bufferout = new BufferedOutputStream(
					fileOutstream);
			ImageIO.write(image, imageName.substring(imageName.lastIndexOf(".") + 1), bufferout);
			bufferout.close();
			
//			if (Digital.ONE.equals(ftpBean.getAddPicture())) {
//				byte[] temps = ThumImageUtil.cutImage(bytes, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
//				ThumImageUtil.pressImage(temps, fileOutstream, imageName.substring(imageName.lastIndexOf(".") + 1));
//			}else{
//				BufferedOutputStream bufferout = new BufferedOutputStream(
//						fileOutstream);
//				ThumImageUtil.cutImage(bytes, bufferout, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
//				bufferout.close();
//			}
			ftp.changeDirectory("/");
			ftp.disconnect();
			
			String fatherString = sBuffer.toString();
			return imageName + "," +fatherString.substring(7,fatherString.length());
			
		} catch (Exception e) {
			e.printStackTrace();
//			throw new UtilsException(e);
			return null;
			
		}
		
		finally {
			
			try {
				if (ftp.isConnected())
					ftp.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
//				throw new UtilsException("关闭ftp连接失败", e);
				return null;
			}
		}
		
	}
	//this for FileItems upload
public static String uploadWithoutSizeItems(FileItem picture,PhotoDetail photoDetailBean ,FtpBean ftpBean) {
		
		String imageName = null;
		int j = 0;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("/images/");
		FileTransferClient ftp = FtpClientFactory.getFtpClient();
		//组织上传路径的目录结构
		List<String> commurl = new ArrayList<String>();
		Date createTime = photoDetailBean.getCreateTime();
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
		String curDate = time.format(createTime);
		//二层结构--年
		String year =curDate.substring(0, 4);
		commurl.add(year);
		//三层结构--月日
		String monthDay = curDate.substring(4,curDate.length());
		commurl.add(monthDay);
		//四层结构--每个文件夹只存500个picId
		int idMath = (int) Math.floor(photoDetailBean.getId()/500);
		commurl.add(String.valueOf(idMath));
		
		String oldname = picture.getName();
		String picIdMD5 = new EncryptMd5().getMD5ofStr(photoDetailBean.getId().toString());
		String md5Name = picIdMD5 + oldname.substring(oldname.lastIndexOf('.'));
		String callName= null;
		if(null !=ftpBean.getCallName() && !ftpBean.getCallName().equals("")){
			callName = DirectoryFactory.ReadDirectory(ftpBean.getCallName());
		}else{
			callName = "other";
		}
		String sizeName = "default";
		try {
			ftp.connect();
			for (String dname : commurl) {
				if (j == 0) {
					sBuffer.append(dname + "/");
				}
				String[] names = ftp.directoryNameList(dname, false);
				if (!(names.length > 0)) {
					ftp.createDirectory(dname);
				}
				ftp.changeDirectory(dname);
			}
			String height = ftpBean.getHeight();
			String width = ftpBean.getWidth();		
			
			imageName = callName +"_"+ sizeName +"_"+ md5Name;			
			
			byte[] bytes = picture.get();
			OutputStream fileOutstream = ftp.uploadStream(imageName);
			ByteArrayInputStream in = new ByteArrayInputStream(bytes); // 将b作为输入流；
			BufferedImage image = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
			BufferedOutputStream bufferout = new BufferedOutputStream(
					fileOutstream);
			ImageIO.write(image, imageName.substring(imageName.lastIndexOf(".") + 1), bufferout);
			bufferout.close();
			
//			if (Digital.ONE.equals(ftpBean.getAddPicture())) {
//				byte[] temps = ThumImageUtil.cutImage(bytes, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
//				ThumImageUtil.pressImage(temps, fileOutstream, imageName.substring(imageName.lastIndexOf(".") + 1));
//			}else{
//				BufferedOutputStream bufferout = new BufferedOutputStream(
//						fileOutstream);
//				ThumImageUtil.cutImage(bytes, bufferout, Integer.parseInt(width), Integer.parseInt(height), true, imageName.substring(imageName.lastIndexOf(".") + 1));
//				bufferout.close();
//			}
			ftp.changeDirectory("/");
			ftp.disconnect();
			
			String fatherString = sBuffer.toString();
			return imageName + "," +fatherString.substring(7,fatherString.length());
			
		} catch (Exception e) {
			e.printStackTrace();
//			throw new UtilsException(e);
			return null;
			
		}
		
		finally {
			
			try {
				if (ftp.isConnected())
					ftp.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
//				throw new UtilsException("关闭ftp连接失败", e);
				return null;
			}
		}
		
	}
	
	
	public static void main(String args[]){
		String picIdMD5 = new EncryptMd5().getMD5ofStr("528");
		System.out.println("picIdMD5______________"+picIdMD5);
	}
	
//	public static String uploadVideo(FormFile formFile, Video video) {
//		if (formFile == null || video.getId() == null) {
//			return null;
//		}
//		StringBuffer sBuffer = new StringBuffer();
//		sBuffer.append("/video/");
//		int j = 0;
//		String newVideoName = "";
//
//		FileTransferClient ftp = FtpClientFactory.getVideoFtpClient();
//		//组织上传路径的目录结构
//		List<String> commurl = new ArrayList<String>();
//		
//		Date createTime = video.getCreateTime();
//		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
//		String curDate = time.format(createTime);
//		//二层结构--年
//		String year =curDate.substring(0, 4);
//		commurl.add(year);
//		//三层结构--月日
//		String monthDay = curDate.substring(4,curDate.length());
//		commurl.add(monthDay);
//		//四层结构--每个文件夹只存50个videoId
//		int idMath = (int) Math.floor(video.getId()/50);
//		commurl.add(String.valueOf(idMath));
//		
//		String oldname = formFile.getFileName();
//		newVideoName = video.getId().toString() + oldname.substring(oldname.lastIndexOf('.'));
//		String lastDir =null;
//		try {
//			ftp.connect();
//			for (String dname : commurl) {
//				if (j == 0) {
//					sBuffer.append(dname + "/");
//				}
//				String[] names = ftp.directoryNameList(dname, false);
//				if (!(names.length > 0)) {
//					ftp.createDirectory(dname);
//				}
//				ftp.changeDirectory(dname);
//				lastDir=dname;
//			}
//			InputStream inputStream = formFile.getInputStream();
//			OutputStream fileOutstream = ftp.uploadStream(newVideoName);
//			byte[] buffer = new byte[10240];
//			int readlen = -1;
//			while ((readlen = inputStream.read(buffer, 0, 1024)) != -1) {
//				fileOutstream.write(buffer, 0, readlen);
//			}
//			fileOutstream.close();
//			inputStream.close();					
//	
//			FTPFile[] names = ftp.directoryList();
//			if (!(names.length > 0)) {
//				ftp.changeToParentDirectory();
//				ftp.deleteDirectory(lastDir);
//			}
//			
//			ftp.disconnect();
//
//			String fatherString = sBuffer.toString();
//			return fatherString.substring(7,fatherString.length())+"/"+newVideoName;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			try {
//				if (ftp.isConnected())
//					ftp.disconnect();
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//	}
//	
//	
//	public static String uploadFlash(FormFile formFile, Car360pic car360picBean) {
//		if (formFile == null || car360picBean.getId() == null) {
//			return null;
//		}
//		StringBuffer sBuffer = new StringBuffer();
//		sBuffer.append("/flash/");
//		int j = 0;
//		String flashName = "";
//
//		FileTransferClient ftp = FtpClientFactory.getFlashFtpClient();
//		//组织上传路径的目录结构
//		List<String> commurl = new ArrayList<String>();
//		
//		Date createTime = car360picBean.getCreatTime();
//		SimpleDateFormat time=new SimpleDateFormat("yyyyMMdd"); 
//		String curDate = time.format(createTime);
//		//二层结构--年
//		String year =curDate.substring(0, 4);
//		commurl.add(year);
//		//三层结构--月日
//		String monthDay = curDate.substring(4,curDate.length());
//		commurl.add(monthDay);
//		//四层结构--每个文件夹只存50个videoId
//		int idMath = (int) Math.floor(car360picBean.getId()/50);
//		commurl.add(String.valueOf(idMath));
//		
//		String oldname = formFile.getFileName();
//		flashName = car360picBean.getId().toString() + oldname.substring(oldname.lastIndexOf('.'));
//		String lastDir =null;
//		try {
//			ftp.connect();
//			for (String dname : commurl) {
//				if (j == 0) {
//					sBuffer.append(dname + "/");
//				}
//				String[] names = ftp.directoryNameList(dname, false);
//				if (!(names.length > 0)) {
//					ftp.createDirectory(dname);
//				}
//				ftp.changeDirectory(dname);
//				lastDir=dname;
//			}
//			InputStream inputStream = formFile.getInputStream();
//			OutputStream fileOutstream = ftp.uploadStream(flashName);
//			byte[] buffer = new byte[10240];
//			int readlen = -1;
//			while ((readlen = inputStream.read(buffer, 0, 1024)) != -1) {
//				fileOutstream.write(buffer, 0, readlen);
//			}
//			fileOutstream.close();
//			inputStream.close();					
//	
//			FTPFile[] names = ftp.directoryList();
//			if (!(names.length > 0)) {
//				ftp.changeToParentDirectory();
//				ftp.deleteDirectory(lastDir);
//			}
//			
//			ftp.disconnect();
//
//			String fatherString = sBuffer.toString();
//			return fatherString.substring(7,fatherString.length())+"/"+flashName;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally {
//			try {
//				if (ftp.isConnected())
//					ftp.disconnect();
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//		}
//	}
}
