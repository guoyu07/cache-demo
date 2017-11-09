package com.shimh.base.ehcache.listener;

import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EventListenerTest {
	public static void main(String[] args) {
		//cacheManagerEventListenerTest();
		cacheEventListenerTest();
	}
	
	private static void cacheManagerEventListenerTest(){
		CacheManager cacheManager = CacheManager.create();
		
		cacheManager.addCache("cache2");  
		cacheManager.removeCache("cache2"); 
		
	}
	
	private static void cacheEventListenerTest(){
		CacheManager cacheManager = CacheManager.create();
		
		Cache cache1 = cacheManager.getCache("cache1");
		cache1.put(new Element("a","1"));
		
		
	}
}
