package com.shimh.base.ehcache;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class Demo1 {
	public static void main(String[] args) throws Exception {
		
		
		//CacheManager cacheManager = CacheManager.create();
		//CacheManager cacheManager = CacheManager.newInstance();
		CacheManager cacheManager = new CacheManager();
		
		//getCache(cacheManager);
		addCache(cacheManager);
		
		cacheManager.shutdown();
	}
	
	
	
	private static void getCache(CacheManager cacheManager) throws Exception{
		System.out.println(Arrays.toString(cacheManager.getCacheNames()));
		Cache cache1 = cacheManager.getCache("cache1");
		
		Element el1 = cache1.get("a");
		System.out.println(el1);
		
		Element el2 = new Element("a", "1");
		cache1.put(el2);
		
		Element el3 = cache1.get("a");
		System.out.println(el3.getObjectValue());
		
		//超时
		TimeUnit.MILLISECONDS.sleep(13000);
		
		Element el4 = cache1.get("a");
		System.out.println(el4);
	}
	
	private static void addCache(CacheManager cacheManager) throws Exception{
		System.out.println(Arrays.toString(cacheManager.getCacheNames()));
		
		cacheManager.addCache("cache2");
		
		Cache cache3 = new Cache("cache3", 1000, true, true, 0, 0);
		cacheManager.addCache(cache3);
		
		System.out.println(Arrays.toString(cacheManager.getCacheNames()));
	}
	
}
