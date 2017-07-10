package org.ferrari.service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderService {

	private String smtpHost;
	private int smtpPort = 25;
	private String userName;
	private String password;
	private String encoding;
	
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	private InternetAddress[] covertAddress(String[] source) {
		InternetAddress[] address = new InternetAddress[source.length];
		try {
			for(int i=0; i<source.length; i++)
				address[i] = new InternetAddress(source[i]);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return address;
	}

	public void send(String from, String[] to, String subject, String message) {
		try {
			// Create a mail session
			java.util.Properties props = new java.util.Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.port", String.valueOf(smtpPort));
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
					}});
			
			// Construct the message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, covertAddress(to));
			msg.setSubject(subject, encoding);
			msg.setContent(message, "text/html;charset=" + encoding);
			msg.setSentDate(new java.util.Date());
			msg.saveChanges();
	        
	        //Send mail
	        Transport t = session.getTransport("smtp");
	        t.connect();
	        t.sendMessage(msg, msg.getAllRecipients());
	        t.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
