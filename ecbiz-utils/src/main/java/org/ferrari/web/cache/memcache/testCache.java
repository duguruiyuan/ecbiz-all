package org.ferrari.web.cache.memcache;

public class testCache {
	public static void testTimeout(){
		ICache cache = CacheFactory.getCache();
		cache.set("timeout","test time out",5);//5秒
		System.out.println("before sleep:"+cache.get("timeout"));
		try {
			Thread.sleep(6*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after sleep:"+cache.get("timeout"));
		
	}
	public static void testReset(){
		ICache cache = CacheFactory.getCache();
		//cache.set("test","test reset");
		System.out.println("-------"+cache.get("test"));
		try {
			while(true){
				Thread.sleep(6*1000);
				Object obj = cache.get("test");
				if(obj==null){
					cache.reset();
					System.out.println("reset memcached.");
				}else{
					System.out.println("result:"+obj);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete(){
		ICache cache = CacheFactory.getCache();
		cache.set("key_test",",&#fasd");
		System.out.println("before delete: "+cache.get("key_test"));
		cache.delete("key_test");
		System.out.println("after delete: "+cache.get("key_test"));
	}
	
	public static void increase(){
		ICache cache = CacheFactory.getCache();
		cache.setCounter("increase2",80);
		System.out.println("init value:"+cache.getCounter("increase2"));
		long value = cache.incr("increase2",1);
		System.out.println("add 1 value:"+cache.getCounter("increase2"));
		System.out.println("value:"+value);
	}
	/*
	 * memcache bug:使用减法计数器之后，当当前值减少一位数（由100变为99）的时候，getCounter方法调用会出错，变为-1
	 */
	public static void decrease(){
		ICache cache = CacheFactory.getCache();
//		cache.initCounter("decrease",50);
//		cache.delete("decrease");
		System.out.println("init value:"+cache.getCounter("decrease"));
		long value = cache.decr("decrease",1);
//		long value = cache.incr("decrease",1);
		System.out.println("plus 1 value:"+cache.getCounter("decrease"));
		System.out.println("value:"+value);
	}
	
	public static void testTopicCount(){
		ICache cache = CacheFactory.getCache();
		long c = cache.getCounter("test_1603113");
		System.out.println("current value:"+c);
		if(c<0){
			c=23;
			cache.setCounter("test_1603113",c);
		}
		int count=-1;
	//	if(c<20){
	//		count=1;
	//	}
		if(count<0){
			cache.setCounter("test_1603113",c+count);
		}else{
			cache.incr("test_1603113",count);
		}
	}
	
	public static void main(String[] args){
//		testTopicCount();
		ICache cache = CacheFactory.getCache();
//		System.out.println(cache.get("key_test"));
//		System.out.println("before delete "+cache.get("k_test"));
//		System.out.println(cache.delete("increase2"));
//		System.out.println(cache.delete("decrease"));
//		System.out.println("after delete "+cache.get("key_test"));
//		testReset();
//		increase();
//		cache.incr("increase2",1);
//		long value = cache.getCounter("increase2");
//		System.out.println(value);
//		cache.initCounter("increase2",1000);
//		System.out.println("before:"+cache.getCounter("increase2"));
//		cache.decr("increase2",1);
//		System.out.println("after:"+cache.getCounter("increase2"));
//		cache.decr("increase2",1);
//		System.out.println("after:"+cache.getCounter("increase2"));
//		decrease();
		cache.setCounter("count0", 1017);
//		cache.reset();
//		System.out.println(cache.getCounter("count0"));
//		cache.decr("count0", 17);
//		System.out.println(cache.getCounter("count0"));
		System.out.println(cache.decr("count0", 18));
	}
}
