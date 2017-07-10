package org.ferrari.utils.upload;
import com.enterprisedt.net.ftp.FileTransferClient;
/**
 * 
   * @create.date: May 11, 2009 5:29:40 PM     
   * @comment: <p>TODO</p>
   * @see: com.jmyz.upload.util
   * @author: yuxinrong
   * @modify.by: yuxinrong
   * @modify.date: May 11, 2009 5:29:40 PM
 */
public class SetEncodingFileTransferClient extends FileTransferClient{
	/**
	 * 设置连接时的字符集, 默认值是US-ASCII.
	 * @param controlEncoding 字符集名, 如GB2312等
	 */
    public synchronized void setControlEncoding(String controlEncoding) {
        super.masterContext.setControlEncoding(controlEncoding);
        
   }
}
