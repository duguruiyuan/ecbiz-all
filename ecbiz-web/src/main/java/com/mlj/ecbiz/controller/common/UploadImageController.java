package  com.mlj.ecbiz.controller.common;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ferrari.utils.upload.CallName;
import org.ferrari.utils.upload.DirectoryFactory;
import org.ferrari.utils.upload.FtpBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mlj.ecbiz.model.product.PhotoDetail;
import com.mlj.ecbiz.service.product.PhotoDetailService;

@Controller
@RequestMapping("/common")
public class UploadImageController {

	private static final Logger logger = Logger.getLogger(UploadImageController.class);
	@Autowired
	private PhotoDetailService photoDetailService;
	private String toImageUpload = "/common/uploadImage.httl";// 修改页
	
	
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestParam(value="imageUrl",required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		
		String albumId = request.getParameter("albumId")==null?"0":request.getParameter("albumId");
		String picType  = request.getParameter("picType")==null?"0":request.getParameter("picType");
		String parentId = request.getParameter("parentId")==null?"0":request.getParameter("parentId");
		String fileName = file.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf('.'));
		try {
			PhotoDetail photo = new PhotoDetail();
			if (CallName.CAR.getName().equals(picType)) {
				photo.setPicType(DirectoryFactory.ReadDirectory(CallName.CAR));
			} else if (CallName.CAROTHER.getName().equals(picType)) {
				photo.setPicType(DirectoryFactory.ReadDirectory(CallName.CAROTHER));
			} else if (CallName.SEXMODEL.getName().equals(picType)) {
				photo.setPicType(DirectoryFactory.ReadDirectory(CallName.SEXMODEL));
			} else if (CallName.LOGO.getName().equals(picType)) {
				photo.setPicType(DirectoryFactory.ReadDirectory(CallName.LOGO));
			} else if (CallName.NEWS.getName().equals(picType)) {
				photo.setPicType(DirectoryFactory.ReadDirectory(CallName.NEWS));
			}
			photo.setAlbumId(Long.valueOf(albumId));
			photo.setCreateTime(new Date());
			//photo.setParentId(Long.parseLong(parentId));
			photo.setPicType(picType);
			photo.setFileType(fileType);
			photo.setState(1L);
			BufferedImage bi = javax.imageio.ImageIO.read(file.getInputStream());
			int imgWidth = bi.getWidth();
			int imgHeight = bi.getHeight();
			photo.setWidth(Long.valueOf(imgWidth));
			photo.setHeight(Long.valueOf(imgHeight));
			if (imgWidth != 0 && imgHeight != 0) {
				if (Double.valueOf(imgWidth) / Double.valueOf(imgHeight) > (1.5)) {
					photo.setPicstate(0L); // 宽版的图片
				} else {
					photo.setPicstate(1L);// 竖版的图片
				}
			}
			photoDetailService.addPhotoDetail(photo);
			FtpBean newFtp = new FtpBean(null);
			if(CallName.CAR.getName().equals(picType)){
				newFtp = new FtpBean(CallName.CAR);
			}else if (CallName.CAROTHER.getName().equals(picType)){
				newFtp = new FtpBean(CallName.CAROTHER);
			}else if (CallName.LOGO.getName().equals(picType)){
				newFtp = new FtpBean(CallName.LOGO);
			}else if (CallName.SEXMODEL.getName().equals(picType)){
				newFtp = new FtpBean(CallName.SEXMODEL);
			}else if (CallName.NEWS.getName().equals(picType)) {
				newFtp = new FtpBean(CallName.NEWS);
			}
			
			String webPath = "statics/upload/images/"+new Date().getYear()+"/"+new Date().getMonth()+new Date().getDay()+"/"; 
			String fileRealPath = request.getSession().getServletContext().getRealPath("/")+"statics\\upload\\";
		    
		    //判断文件夹是否存在  
		    File targerFile = new File(fileRealPath);                     
		    //判断是否存在目录  
		    if(!targerFile.exists()) {  
		       targerFile.mkdirs();  
		    }   
			String newfilename=UUID.randomUUID()+fileType;
			//新文件
			File newfile=new File(fileRealPath+newfilename);
			FileCopyUtils.copy(file.getBytes(), newfile);
			if (fileRealPath == null) {
				photoDetailService.deletePhotoDetailById(photo.getId());
			} else {
				String newPicType = "";
				if(DirectoryFactory.ReadDirectory(CallName.CAR).equals(picType)){
					newPicType="car_120_80";
				}else if(DirectoryFactory.ReadDirectory(CallName.CAROTHER).equals(picType)){
					newPicType="carother_120_0";
				}else if(DirectoryFactory.ReadDirectory(CallName.SEXMODEL).equals(picType)){
					newPicType="sexmodel_100_150";
				}else if(DirectoryFactory.ReadDirectory(CallName.LOGO).equals(picType)){
					newPicType="logo_50_50";
				}
				response.setCharacterEncoding("GBK");
		        response.setContentType("text/html;charset=GBK;");
		        PrintWriter out=null;
		        out = response.getWriter();
				out.print("<script>window.document.domain='chexun.com';window.parent.notice_id('http://ecbiz.chexun.com/"+webPath+newfilename+"','"+photo.getId()+"');</script>");
				//return fileRealPath+newfilename;
			}
		} catch (Exception e) {
			logger.error("SysUserController.toAdd", e);
		}
		
		return null;
	}
	
	
	
}
