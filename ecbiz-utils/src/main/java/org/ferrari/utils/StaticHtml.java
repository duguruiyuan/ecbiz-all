package org.ferrari.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StaticHtml {
	/**
	 * 根据URL获取数据流
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getPageStream(String url) throws IOException {
		URL realUrl = null;
		URLConnection conn = null;
		StringBuffer temp = new StringBuffer();
		try {
			realUrl = new URL(url);
			conn = realUrl.openConnection();
			conn.connect();
			InputStream in = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String tempLine = rd.readLine();
			while (tempLine != null) {
				if ("".equals(tempLine.trim())) {
					tempLine = rd.readLine();
					continue;
				}
				temp.append(tempLine).append("\n");
				tempLine = rd.readLine();
			}
			rd.close();
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp.toString();
	}
	/**
	 * 生成文件
	 * @param html
	 * @param fileName
	 */
	public static void outHtmlFile(String html, String fileName) {
		File file = new File(ClassPathUtils.getWebRoot() + fileName);
		OutputStreamWriter outWriter;
		try {
			FileOutputStream outer = new FileOutputStream(file);
			outWriter = new OutputStreamWriter(outer, "UTF-8");
			outWriter.write(html, 0, html.length());
			outWriter.flush();
			outWriter.close();
			outer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
