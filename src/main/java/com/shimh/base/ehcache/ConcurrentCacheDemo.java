package com.shimh.base.ehcache;

import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/*
 * Ehcache为我们提供了针对于缓存元素Key的Read（读）、Write（写）锁
 */
public class ConcurrentCacheDemo {

	public static void main(String[] args) throws Exception {
		
		
		CacheManager cacheManager = CacheManager.create();
		
		Cache cache = cacheManager.getCache("cache1");
		
	    String key  = "name";
		cache.put(new Element(key,"1"));
		
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				cache.acquireWriteLockOnKey(key);
				
				cache.put(new Element(key,"2"));
				
				try {
					TimeUnit.MILLISECONDS.sleep(6000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					System.out.println("thread" + cache.get(key)); 
					cache.releaseWriteLockOnKey(key);
				}
				
			}
		}).start();
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		System.out.println(cache.get(key));  
		
		
	}
}
