package org.ferrari.utils.upload;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class SessionListener implements ServletContextListener,
HttpSessionListener
{

	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	
	public void sessionCreated(HttpSessionEvent arg0) {
	
		
		System.out.println("HttpSession被创建了");
		long start = System.currentTimeMillis();
		System.out.println("当前现在时间是"+start);
	//	FtpClientFactory.init();
		System.out.println("结束时间是"+(System.currentTimeMillis()-start));
		
	}


	public void sessionDestroyed(HttpSessionEvent arg0) {
	
	//	WebUtils.removeUser("userid");
		
		System.out.println("HttpSession被销毁了");
		
		
	}
  
}
