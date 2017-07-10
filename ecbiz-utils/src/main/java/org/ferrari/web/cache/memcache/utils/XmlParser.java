package org.ferrari.web.cache.memcache.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * @author max xml格式如下： 解析两层结构或者由多个类似的两层结构组成的3层结构。 <ANY NAME allnum="300">
 *         <record> ... </record> ... </ANY NAME>
 */
public class XmlParser {
	private static final Logger logger = Logger.getLogger(XmlParser.class);
	private String RECORD = "config";
	private String[] ITEMS = null;
	public int allnum = 0;// 总共有多少结果

	/**
	 * Creates a new instance of XmlParser
	 */
	public XmlParser(String[] items) {
		if (items != null && items.length > 0) {
			this.ITEMS = items;
		}
	}

	public XmlParser(String[] items, String record) {
		if (items != null && items.length > 0) {
			this.ITEMS = items;
		}
		this.RECORD = record;
	}

	public int getAllnum() {
		return allnum;
	}

	public void setItems(String[] items) {
		if (items != null && items.length > 0)
			this.ITEMS = items;
	}

	public ArrayList<ItemBean> parse(String content) {
		ArrayList<ItemBean> resultList = new ArrayList<ItemBean>();
		if (this.ITEMS == null || content == null)
			return resultList;
		int start = content.indexOf("allnum=");// 得到allnum的值
		try {
			if (start == -1) {
				allnum = 0;
			} else {
				try {
					allnum = Integer.parseInt(content.substring(start + 8, content.indexOf("'", start + 8)));
				} catch (Exception e) {
					logger.error("parse xml error:", e);
					e.printStackTrace();
				}
				if (allnum == 0) {
					if (content.indexOf("\"", start + 8) >= start + 8)
						allnum = Integer.parseInt(content.substring(start + 8, content.indexOf("\"", start + 8)));
				}
			}
		} catch (Exception e) {
			logger.error("parse xml error:", e);
			e.printStackTrace();
		}
		String[] records = parseTag(content, RECORD);
		for (int i = 0; i < records.length; i++) {// 逐条解析查询结果
			ItemBean bean = new ItemBean();
			String[] items;
			for (int j = 0; j < ITEMS.length; j++) {
				items = parseTag(records[i], ITEMS[j]);
				if ((items != null) && (items.length > 0)) {
					bean.setItem(ITEMS[j], items[0]);
				}
			}
			resultList.add(bean);
		}
		return resultList;
	}

	/**
	 * 解析标签TAG内部的内容
	 */
	public static String[] parseTag(String content, String tag) {
		final String _TAG_START = "<" + tag + ">";
		final String _TAG_END = "</" + tag + ">";
		ArrayList<String> results = new ArrayList<String>();
		int start = content.indexOf(_TAG_START);
		int end = 0;
		while (start >= 0) {
			end = content.indexOf(_TAG_END, start);
			if (end < start) break;
			String item = content.substring(start + _TAG_START.length(), end);
			start = content.indexOf(_TAG_START, end);
			results.add(item);
		}
		return (String[]) results.toArray(new String[0]);
	}

	public String escapeCharsToHtml(String str) {
		String ret = str;
		if (ret == null) return "";
		ret = ret.replaceAll("&amp;", "&");
		ret = ret.replaceAll("&gt;", ">");
		ret = ret.replaceAll("&lt;", "<");
		return ret;
	}
	/**
	 * 读取一个指定的流的内容并返回
	 * @param is InputStream对象
	 * @throws IOException
	 * @return String
	 */
	public String readUrlContent(java.io.InputStream is, String encoding) throws IOException {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(is, encoding));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
				result.append("\n");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result.toString();
	}

	/**
	 * 读取一个指定的Url的内容并返回
	 * @param url String 指定的url,如 http://www.1233.net
	 * @throws IOException
	 * @return String
	 */
	public String readUrlContent(String url, String encoding)
			throws IOException {
		URL verisign = new URL(url);
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(verisign.openStream(), encoding));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				result.append(inputLine);
				result.append("\n");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result.toString();
	}

	public static void main(String[] args) {
		String[] items = { "qid", "title", "path", "answer", "answernums" };
		XmlParser vxp = new XmlParser(items);
		try {
			String content = vxp.readUrlContent("", "utf-8");
			ArrayList<?> list = vxp.parse(content);
			for (int i = 0; i < list.size(); i++) {
				ItemBean vr = (ItemBean) list.get(i);
				for (int j = 0; j < items.length; j++) {
					System.out.println(vr.getItem(items[j]));
				}
			}
			System.out.println(vxp.allnum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
