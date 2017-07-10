package org.ferrari.service;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	protected String from;
	protected String to;
	protected String subject;
	protected String body;
	protected String username;
	protected String password;
	protected String filename;
	
	public SendMail(){}
	
	public void setProperty(String from,String to,String subject,String body){
		this.from=from;
		this.to=to;
		this.subject=subject;
		this.body=body;
	}
    
	public void setauth(String username,String password){
		this.username=username;
		this.password=password;
	}
	
	public void setfile(String filename){
		this.filename=filename;
	}
	
	public void mail(){
		try{
			//设置stmp邮件服务器
			Properties props=System.getProperties();
			props.put("mail.smtp.host","mail.jumayizhan.com");
			props.put("mail.smtp.auth","true");
			props.put("username", username);
			props.put("password", password);
			
			Session session=Session.getDefaultInstance(props);
			session.setDebug(true);
	
			//创建新邮件
			MimeMessage msg=new MimeMessage(session);
			//指定发信人
			msg.setFrom(new InternetAddress(from));
			//指定收件人，多人用逗号分隔
			InternetAddress [] tos=InternetAddress.parse(to);
			msg.setRecipients(Message.RecipientType.TO,tos);
			//标题
			msg.setSubject(subject);
			
			//发送时间
	       msg.setSentDate(new Date());
	       //内容
	       BodyPart mdp = new MimeBodyPart();
	       mdp.setContent(body, "text/html;charset=utf-8");
	       Multipart test=new MimeMultipart();
	       test.addBodyPart(mdp);
	      
	       
	       
	       
	       //判断是否有附件
	       if(filename!=null){
	    	   MimeBodyPart bodyPart2=new MimeBodyPart();
	    	   File f=new File(filename);
	    	   DataSource source=new FileDataSource(f);
	    	   bodyPart2.setDataHandler(new DataHandler(source));
	    	   bodyPart2.setFileName(f.getName());
	    	   
	    	  test.addBodyPart(bodyPart2);
	       }
	       msg.setContent(test);
	        //发送
	       msg.saveChanges();
	       Transport transport=session.getTransport("smtp");
	       transport.connect("mail.jumayizhan.com",username,password);
	       transport.sendMessage(msg,msg.getAllRecipients());
	       transport.close();
		}catch(Exception ex){
			//System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
