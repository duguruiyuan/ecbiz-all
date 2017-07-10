package org.ferrari.web.cache.memcache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class CacheManager implements ICache {
	private Logger logger = Logger.getLogger(CacheManager.class);
	private static CacheManager instance = null;// Cache实例
	private MemCachedClient mcc = null;// cache客户端
	private SockIOPool pool = null;// socket池
	private List<String> keyList = new ArrayList<String>();// 用来存所有查询的key，在每天更新索引后根据这些key把cache删除。

	private CacheManager() {
		init();// 初始化缓存
	}
	public synchronized static CacheManager getInstance() {
		if (instance == null) {
			instance = new CacheManager();
		}
		return instance;
	}
	private void init() {
		Constants.load();
		logger.info("init cache start!");
		pool = SockIOPool.getInstance();
		pool.setServers(Constants.servers); // 服务器列表
		pool.setFailover(true);
		pool.setInitConn(Constants.cache_InitConn); // 初始连接数
		pool.setMinConn(Constants.cache_MinConn);// 最小连接数
		pool.setMaxConn(Constants.cache_MaxConn);// 最大连接数
		pool.setMaintSleep(Constants.cache_MaintSleep); // 设置主线程的睡眠时间
		pool.setNagle(false); // TCP参数,对于socket创建的算法
		pool.setSocketTO(Constants.cache_SocketTo); // 连接建立 后 对超时的控制
		pool.setSocketConnectTO(Constants.socketConnectTO); // 连接建立 时 对超时的控制
		pool.setAliveCheck(true);// 心跳检查，确定服务器的运行状态
		pool.initialize();
		mcc = new MemCachedClient();
//		mcc.setCompressEnable(false);
//		mcc.setCompressThreshold(64 * 1024);
		logger.info("init cache end!");
	}

	/**
	 * <h1>销毁socket io池</h1>
	 * @author kevin
	 */
	public void destory() {
		if (pool != null) {
			pool.shutDown();
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		if (null != mcc) {
			return mcc.keyExists(key)?(T)mcc.get(key):null;
		} else {
			return null;
		}
	}
	public Map<?, ?> getMulti(String[] keys) {
		if (null != mcc) {
			return mcc.getMulti(keys);
		} else {
			return null;
		}
	}
	public boolean set(String key, Object value) {
		boolean bRet = false;
		if (null != mcc) {
			bRet = mcc.set(key, value);
			keyList.add(key);
		}
		return bRet;
	}
	public boolean set(String key, Object value, int expiretime) {
		boolean bRet = false;
		if (null != mcc) {
			if (expiretime < 0) {
				expiretime = 0;
			}
			bRet = mcc.set(key, value, new Date(expiretime * 1000));
		}
		return bRet;
	}
	public boolean replace(String key, Object value){
		boolean bflag = false;
		if(null != mcc){
			bflag = mcc.replace(key, value);
		}
		return bflag;
	}
	public boolean replace(String key, Object value, int expiretime){
		boolean bflag = false;
		if(null != mcc){
			bflag = mcc.replace(key, value, new Date(expiretime * 1000));
		}
		return bflag;
	}
	public boolean delete(String key) {
		boolean bRet = false;
		if (null != mcc) {
			bRet = mcc.delete(key);
		}
		return bRet;
	}
	public synchronized void delete() {
		List<String> tempList = new ArrayList<String>();
		for (int i = 0; i < keyList.size(); i++) {
			delete(keyList.get(i));
			tempList.add(keyList.get(i));
		}
		for (int i = 0; i < tempList.size(); i++) {
			keyList.remove(tempList.get(i));
		}
	}
	public long incr(String key, long value) {
		long lRet = -1;
		if (null != mcc) {
			lRet = mcc.addOrIncr(key, value);
		}
		return lRet;
	}
	//该方法中MEMCACHE存在BUG,当做减法运算时,
	//高位数过度到低位数的时候其值都为-1,
	//如100-1=-1,100为三位数,减1以后为2位数
	public long decr(String key, long value) {
		long def = -1;
		if (null != mcc) {
			//获得被减之前的基数count
			long count = this.getCounter(key);
			def = mcc.addOrDecr(key, value);
			if(count != -1){
				//如果被减以后的基数为-1,证明memcache出现BUG,
				//因为无论如何做减法,该值都不应该为-1.
				if(this.getCounter(key) == -1){
					//纠正BUG,使其值正确,重新设置计数器
					this.setCounter(key, count - value);
				}
			}
		}
		return def;
	}
	public long getCounter(String key) {
		long lRet = -1;
		if (null != mcc) {
			lRet = mcc.getCounter(key);
		}
		return lRet;
	}
	public boolean setCounter(String key, long value) {
		boolean bRet = false;
		if (null != mcc) {
			bRet = mcc.storeCounter(key, value);
		}
		return bRet;
	}
	/**
	 * 清空缓存
	 */
	public boolean flushAll() {
		return mcc.flushAll();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> status() {
		return mcc.stats();
	}
	
	public boolean reset() {
		boolean bRet = false;
		instance.destory();
		Constants.reset();
		instance.init();
		bRet = true;
		return bRet;
	}
	public boolean keyExists(String key) {
		return mcc.keyExists(key);
	}

}
