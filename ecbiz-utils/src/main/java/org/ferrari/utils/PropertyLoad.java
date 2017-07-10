/**
 *
 */
package org.ferrari.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName PropertyUtil
 * @package 
 * @description
 * @author spencer
 * @Create Date: 2013-2-26 下午06:52:31
 */
public class PropertyLoad {

	// 实例map
	private static Map<String, PropertyLoad> instance = Collections.synchronizedMap(new HashMap<String, PropertyLoad>());

	protected String sourceUrl;
	private Properties properties = new Properties();
//	protected ResourceBundle resourceBundle;
//	private static Map<String, String> convert = Collections.synchronizedMap(new HashMap<String, String>());

	protected PropertyLoad(String sourceUrl) {
		this.sourceUrl = sourceUrl;
		load();
	}

	public static PropertyLoad getInstance(String sourceUrl) {
		synchronized (PropertyLoad.class) {
			PropertyLoad manager = (PropertyLoad) instance.get(sourceUrl);
			if (manager == null) {
				manager = new PropertyLoad(sourceUrl);
				instance.put(sourceUrl, manager);
			}
			return manager;
		}
	}

	private synchronized void load() {
		try {
			InputStream in = getClass().getResourceAsStream("/" + sourceUrl + ".properties");
			properties.load(in);
			//resourceBundle = ResourceBundle.getBundle(sourceUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("sourceUrl = " + sourceUrl + "file load error!", e);
		}
	}

//	public String getProperty(String key) {
//		return resourceBundle.getString(key);
//	}

	public List<String[]> readyConvert() {
		@SuppressWarnings("rawtypes")
		List<String[]> list = new ArrayList<String[]>();
//		Enumeration enu = resourceBundle.getKeys();
//		List<String[]> list = new ArrayList<String[]>();
//		while (enu.hasMoreElements()) {
//			String key = (String) enu.nextElement();
//			String value = resourceBundle.getString(key);
//			list.add(new String[]{key,value});
//		}
		Iterator it = properties.keySet().iterator();
		if(it != null){
			String key,value;
			while(it.hasNext()){
				key = (String) it.next();
				value = properties.getProperty(key);
				list.add(new String[]{key,value});
				System.out.println("key:" + key + ",value:" + value);
			}
		}
		return list;
	}
}
