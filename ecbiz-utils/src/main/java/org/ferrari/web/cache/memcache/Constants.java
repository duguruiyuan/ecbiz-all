package org.ferrari.web.cache.memcache;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.ferrari.web.cache.memcache.utils.ItemBean;
import org.ferrari.web.cache.memcache.utils.XmlParser;


public class Constants {
	private static Logger logger = Logger.getLogger(Constants.class);
	public static int cache_InitConn = 10;
	public static int cache_MinConn = 5;
	public static int cache_MaxConn = 100;
	public static int cache_MaintSleep = 0;
	public static int cache_SocketTo = 3000;
	public static int socketConnectTO = 1000 * 10; // 10 seconds to block on initial   
	public static String[] servers = null;

	private static String config = "/cache-co nfig.xml";
	private static boolean bflag = false;

	public static void load() {
		if (bflag == true) {
			return;
		}
		try {
			XmlParser xmlParser = new XmlParser(new String[] {"servers", "InitConn","MinConn","MaxConn","MaintSleep","SocketTo"});
			InputStream is = Constants.class.getResourceAsStream(config);
			String content = xmlParser.readUrlContent(is, "utf-8");
			ArrayList<?> list = xmlParser.parse(content);
			for (int i = 0; i < list.size(); i++) {
				ItemBean vr = (ItemBean) list.get(i);
				cache_InitConn = parseInt(vr.getItem("InitConn"), 10);
				cache_MinConn = parseInt(vr.getItem("MinConn"), 5);
				cache_MaxConn = parseInt(vr.getItem("MaxConn"), 100);
				cache_MaintSleep = parseInt(vr.getItem("MaintSleep"), 0);
				cache_SocketTo = parseInt(vr.getItem("SocketTo"), 3000);
				String slist = vr.getItem("servers");
				servers = slist.split(",");
			}
			bflag = true;
		} catch (IOException e) {
			logger.error("cache start failed:" + e.getMessage());
		}
	}

	public static void reset() {
		bflag = false;
	}

	public static void main(String args[]) {
		Constants.load();
	}

	public static int parseInt(String str, int defaultValue) {
		if (str == null)
			return defaultValue;
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return defaultValue;
	}
}
