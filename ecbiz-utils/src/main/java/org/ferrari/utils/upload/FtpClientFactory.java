package org.ferrari.utils.upload;

import java.util.ResourceBundle;

import com.enterprisedt.net.ftp.FTPConnectMode;


/**
 * 
   * @create.date: May 11, 2009 5:29:23 PM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 11, 2009 5:29:23 PM
 */
public class FtpClientFactory {
	/**
	 * 
	 * @comment: <p>please description this method to do</p>  
	 * @create.date: May 11, 2009 ( 5:29:32 PM )
	 * @author: yuxinrong
	 * @param usertype
	 * @return
	 * @see: <h1>com.jmyz.upload.util.FtpClientFactory.getFtpClient</h1>
	 * @modified.by: <if there modified by others,then write author name>
	 * @modified.date: <modified date time>
	 */
	public static SetEncodingFileTransferClient getFtpClient() {
		SetEncodingFileTransferClient ftp = null;

		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
		String host = bundle.getString("host");
		String username = bundle.getString("username");
		String password = bundle.getString("password");

		try {
			ftp = new SetEncodingFileTransferClient();
			ftp.setRemoteHost(host);
			ftp.setUserName(username);
			ftp.setPassword(password);
			//TODO 本地和测试环境测试时，需要设置模式为PASV
			ftp.getAdvancedFTPSettings().setConnectMode(FTPConnectMode.PASV);
			ftp.setControlEncoding("GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp;
	}
	
	public static SetEncodingFileTransferClient getVideoFtpClient() {
		SetEncodingFileTransferClient ftp = null;
		
		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
		String host = bundle.getString("video_host");
		String username = bundle.getString("video_username");
		String password = bundle.getString("video_password");
		
		try {
			ftp = new SetEncodingFileTransferClient();
			ftp.setRemoteHost(host);
			ftp.setUserName(username);
			ftp.setPassword(password);
			ftp.setControlEncoding("GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp;
	}

	/**
	 * flash上传路径控制
	 * */
	public static SetEncodingFileTransferClient getFlashFtpClient() {
		SetEncodingFileTransferClient ftp = null;
		
		ResourceBundle bundle = ResourceBundle.getBundle("DirectoryRules");
		String host = bundle.getString("flash_host");
		String username = bundle.getString("flash_username");
		String password = bundle.getString("flash_password");
		
		try {
			ftp = new SetEncodingFileTransferClient();
			ftp.setRemoteHost(host);
			ftp.setUserName(username);
			ftp.setPassword(password);
			ftp.setControlEncoding("GB2312");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ftp;
	}
}
