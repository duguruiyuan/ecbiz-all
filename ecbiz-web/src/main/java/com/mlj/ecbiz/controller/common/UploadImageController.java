package  com.mlj.ecbiz.controller.common;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
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
			
			Calendar now = Calendar.getInstance();
			
			String webPath = "images/"+ frontCompWithZore(now.get(Calendar.YEAR),2)+"/"+frontCompWithZore((now.get(Calendar.MONTH) + 1),2)+frontCompWithZore(now.get(Calendar.DAY_OF_MONTH),2)+"/"; 
			String fileRealPath = request.getSession().getServletContext().getRealPath("/")+webPath;
		    
		    //判断文件夹是否存在  
		    File targerFile = new File(fileRealPath);                     
		    //判断是否存在目录  
		    if(!targerFile.exists()) {  
		       targerFile.mkdirs();  
		    }   
			String newfilename=UUID.randomUUID()+fileType;
			photo.setFileMd5(String.valueOf(UUID.randomUUID()));
			
			//新文件
			File newfile=new File(fileRealPath+"logo_"+newfilename);
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
				photo.setPicType(newPicType);
				photo.setPicRemark("http://ecbiz.chexun.com/"+webPath+"logo_"+newfilename);
				photoDetailService.addPhotoDetail(photo);
				response.setCharacterEncoding("GBK");
		        response.setContentType("text/html;charset=GBK;");
		        PrintWriter out=null;
		        out = response.getWriter();
				out.print("<script>window.document.domain='chexun.com';window.parent.notice_id('http://ecbiz.chexun.com/"+webPath+"logo_"+newfilename+"','"+photo.getId()+"');</script>");
				//return fileRealPath+newfilename;
			}
		} catch (Exception e) {
			logger.error("SysUserController.toAdd", e);
		}
		
		return null;
	}
    /** 
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回 
     * @param sourceDate 
     * @param formatLength 
     * @return 重组后的数据 
     */  
    public static String frontCompWithZore(int sourceDate,int formatLength)  
    {  
     /* 
      * 0 指前面补充零 
      * formatLength 字符总长度为 formatLength 
      * d 代表为正数。 
      */  
     String newString = String.format("%0"+formatLength+"d", sourceDate);  
     return  newString;  
    }  
	
	
}
