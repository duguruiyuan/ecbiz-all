package org.ferrari.web.cache.memcache.utils;

import java.util.HashMap;

public class ItemBean {
	private HashMap<String, String> map = new HashMap<String, String>();
	
	public void setItem(String name, String value){
		map.put(name, value);
	}
	
	public String getItem(String name){
		return (String)map.get(name);
	}
}
