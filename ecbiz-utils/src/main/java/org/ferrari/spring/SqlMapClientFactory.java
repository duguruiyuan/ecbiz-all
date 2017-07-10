package org.ferrari.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.web.context.support.ServletContextResource;

public class SqlMapClientFactory extends SqlMapClientFactoryBean {

	public void setConfigLocation(Resource configLocation) {
		ServletContextResource resource = (ServletContextResource) configLocation;
		StringTokenizer path = new StringTokenizer(resource.getPath(),", \t\n\r");
		StringBuffer sqlmap = new StringBuffer();
		sqlmap.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>").append("\n");
		sqlmap.append("<!DOCTYPE sqlMapConfig PUBLIC \"-//ibatis.apache.org//DTD SQL Map Config 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-config-2.dtd\">").append("\n");
		sqlmap.append("<sqlMapConfig>").append("\n");
		while(path.hasMoreTokens()) {
			try {
				ServletContextResource source = new ServletContextResource(resource.getServletContext(), path.nextToken());
				InputStream stream = source.getInputStream();
				int c = 0;
				StringBuffer buffer = new StringBuffer();
				while((c = stream.read()) != -1)
					buffer.append((char)c);
				sqlmap.append(buffer.toString().replaceAll("[\\s\\S]*<sqlMapConfig>.*[\r\n]*", "").replaceAll(".*</sqlMapConfig>[\\s\\S]*", ""));
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sqlmap.append("</sqlMapConfig>").append("\n");
		Resource resouce = new ByteArrayResource(sqlmap.toString().getBytes());
		super.setConfigLocation(resouce);
	}

}
