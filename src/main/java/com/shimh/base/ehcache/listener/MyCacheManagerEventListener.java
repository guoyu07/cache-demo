package com.shimh.base.ehcache.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

/*
 * CacheManagerEventListener可以监听的事件有CacheManager添加和移除Cache。
 */
public class MyCacheManagerEventListener implements CacheManagerEventListener{

	   private final CacheManager cacheManager;  
	    
	   public MyCacheManagerEventListener(CacheManager cacheManager) {  
	      this.cacheManager = cacheManager;  
	   }  
	    
	   @Override  
	   public void init() throws CacheException {  
	      System.out.println("init.....");  
	   }  
	   
	   @Override  
	   public Status getStatus() {  
	      System.out.println("getStatus.....");  
	      return Status.STATUS_ALIVE;  
	   }  
	   
	   @Override  
	   public void dispose() throws CacheException {  
	      System.out.println("dispose......");  
	   }  
	   
	   @Override  
	   public void notifyCacheAdded(String cacheName) {  
	      System.out.println("cacheAdded......." + cacheName);  
	      System.out.println(cacheManager.getCache(cacheName));  
	   }  
	   
	   @Override  
	   public void notifyCacheRemoved(String cacheName) {  
	      System.out.println("cacheRemoved......" + cacheName);  
	   } 

}
