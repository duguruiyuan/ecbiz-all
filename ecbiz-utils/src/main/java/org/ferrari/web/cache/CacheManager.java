package org.ferrari.web.cache;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.ferrari.exception.UtilsException;
/**
 * 
   * @create.date: 2009-5-11 上午09:01:51     
   * @comment: <p>this is cache manager</p>
   * @see: org.ferrari.web.cache
   * @author: kevin
   * @verson: 1.0
 */
public class CacheManager {

	private static Hashtable<Object, Object> maps = new Hashtable<Object, Object>();

	public static void bind(String name, CacheEntry entry) {
		maps.put(name, entry);
	}

	public static void unbind(String name) {
		maps.remove(name);
	}

	public static CacheEntry lookup(String name) {
		return (CacheEntry) maps.get(name);
	}

	public static void refresh(String name) throws UtilsException {
		try {
			lookup(name).refresh();
		} catch (UtilsException e) {
			throw e;
		} catch (NullPointerException e) {
			throw new UtilsException("CacheEntry[" + name + "] is not found!");
		}
	}

	public static List<?> list() {
		ArrayList<Object> list = new ArrayList<Object>();
		Enumeration<?> e = maps.keys();
		while(e.hasMoreElements())
			list.add((String) e.nextElement());
		return list;
	}

}
