package org.ferrari.web.membercache;

import java.util.ResourceBundle;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
/**
 * 
   * @create.date: Jul 13, 2009 2:38:37 PM     
   * @comment: <p>TODO</p>
   * @see: org.ferrari.web.membercache
   * @author: shellrong
   * @modify.by: shellrong
   * @modify.date: Jul 13, 2009 2:38:37 PM
 */
public class CacheUtils {
	
	private static final MemCachedClient mcc = new MemCachedClient();

	private CacheUtils() {
		
	}
   /**
    * 
    * @comment: <p>please description this method to do</p>  
    * @create.date: Jul 13, 2009 ( 2:38:53 PM )
    * @author: shellrong
    * @see: <h1>org.ferrari.web.membercache.CacheUtils.toConnCache</h1>
    * @modified.by: <if there modified by others,then write author name>
    * @modified.date: <modified date time>
    */
	public static void toConnCache() {
		ResourceBundle bundle = ResourceBundle.getBundle("membcache");
		String ips = bundle.getString("ips");
		String[] servers = { ips };
        Integer[] weights = { Integer.parseInt(bundle.getString("weights")) };
        // 创建一个实例对象SockIOPool
        SockIOPool pool = SockIOPool.getInstance();
        // set the servers and the weights
		// 设置Memcached Server
		pool.setServers(servers);
		pool.setWeights(weights);
        // set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		pool.setInitConn(Integer.parseInt(bundle.getString("InitConn")));
		pool.setMinConn(Integer.parseInt(bundle.getString("MinConn")));
		pool.setMaxConn(Integer.parseInt(bundle.getString("MaxConn")));
		pool.setMaxIdle(Integer.parseInt(bundle.getString("MaxIdle")));
        // set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(Integer.parseInt(bundle.getString("MaintSleep")));
        // Tcp的规则就是在发送一个包之前，本地机器会等待远程主机
		// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，
		// 以至这个包准备好了就发；
		pool.setNagle(Boolean.getBoolean(bundle.getString("setNagle")));
		// 连接建立后对超时的控制
		pool.setSocketTO(Integer.parseInt(bundle.getString("SocketTO")));
		// 连接建立时对超时的控制
		pool.setSocketConnectTO(Integer.parseInt(bundle.getString("SocketConnectTO")));
        // initialize the connection pool
		// 初始化一些值并与MemcachedServer段建立连接
		pool.initialize();
        // lets set some compression on for the client
		// compress anything larger than 64k
		CacheUtils.getCachedClient().setCompressEnable(
				Boolean.getBoolean(bundle.getString("CompressEnable")));
		CacheUtils.getCachedClient().setCompressThreshold(
				Integer.parseInt(bundle.getString("CompressThreshold")));

	}
    public static MemCachedClient getCachedClient() {
		return mcc;
	}
}
