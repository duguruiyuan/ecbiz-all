package org.ferrari.web.cache.memcache;

import java.util.Map;

public interface ICache {
	/**
	 * 
	 * <h1>根据key值获取对象</h1>
	 * @param key
	 * @return
	 * @author kevin
	 */
	public <T> T get(String key);
	/**
	 * 
	 * <h1>根据键值数组获取对象</h1>
	 * @param keys
	 * @return map
	 * @author kevin
	 */
	public Map<?, ?> getMulti(String[] keys);
	/**
	 * 
	 * <h1>设置值到缓存中</h1>
	 * @param key 键
	 * @param value 值
	 * @return
	 * @author kevin
	 */
	public boolean set(String key, Object value);
	/**
	 * 
	 * <h1>设置值到缓存中</h1>
	 * @param key 键
	 * @param value 值
	 * @param expiretime 过期时间（秒）
	 * @return
	 * @author kevin
	 */
	public boolean set(String key, Object value, int expiretime);
	/**
	 * 
	 * <h1>根据键值替换对应的值</h1>
	 * @param key
	 * @param value
	 * @return
	 * @author kevin
	 */
	public boolean replace(String key, Object value);
	/**
	 * 
	 * <h1>根据键值替换对应的值</h1>
	 * @param key
	 * @param value
	 * @param expiretime 过期时间（秒）
	 * @return
	 * @author kevin
	 */
	public boolean replace(String key, Object value, int expiretime);
	/**
	 * 
	 * <h1>对一个键实现自减操作</h1>
	 * @param key
	 * @param value
	 * @return
	 * @author kevin
	 */
	public long decr(String key, long value);
	/**
	 * 
	 * <h1>对一个键实现自增操作</h1>
	 * @param key
	 * @param value
	 * @return
	 * @author kevin
	 */
	public long incr(String key, long value);
	/**
	 * 
	 * <h1>获取计数器</h1>
	 * @param key
	 * @return
	 * @author kevin
	 */
	public long getCounter(String key);
	/**
	 * 
	 * <h1>设置计数器</h1>
	 * @param key
	 * @param value
	 * @return
	 * @author kevin
	 */
	public boolean setCounter(String key, long value);
	/**
	 * 
	 * <h1>根据键值删除对象</h1>
	 * @param key
	 * @return
	 * @author kevin
	 */
	public boolean delete(String key);
	/**
	 * 
	 * <h1>重新初始化缓存</h1>
	 * @return
	 * @author kevin
	 */
	public boolean reset();
	/**
	 * 
	 * <h1>清空所有缓存</h1>
	 * @return
	 * @author kevin
	 */
	public boolean flushAll();
	/**
	 * 
	 * <h1>返回缓存状态</h1>
	 * @return
	 * @author kevin
	 */
	public Map<String, Map<String, String>> status();
	/**
	 * 
	 * <h1>清空key list中所有缓存对象</h1>
	 * @author kevin
	 */
	public void delete();
	
	public boolean keyExists(String key);
}
