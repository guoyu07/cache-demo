package com.shimh.base.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

public class CacheDemo {
	public static void main(String[] args) {
		//test1();
		test4();
	}
	
	
	/*
	 */
	private static void test1(){
	   //内存中保存的Element的最大数量  
	   int maxEntriesLocalHeap = 10000;  
	   CacheConfiguration cacheConfiguration = new CacheConfiguration("cache2", maxEntriesLocalHeap);  
	   cacheConfiguration.overflowToOffHeap(false);  
	   Cache cache = new Cache(cacheConfiguration);  
	   //使用默认配置创建CacheManager  
	   CacheManager cacheManager = CacheManager.create();  
	   //只有添加到CacheManager中的Cache才是有用的  
	   cacheManager.addCache(cache);  
	   cache.put(new Element("a", "1"));  
	   System.out.println(cache.get("a"));  
	   
	   cacheManager.shutdown();
	}
	/*
	 */
	private static void test2(){
		CacheManager cacheManager = CacheManager.create(); 
		Cache cache3 = new Cache("cache3", 1000, true, true, 0, 0);
		cacheManager.addCache(cache3);
		
		cache3.put(new Element("a", "1"));  
	    System.out.println(cache3.get("a"));  
	   
	    cacheManager.shutdown();
	}
	
	
	private static void test3(){
		CacheManager cacheManager = CacheManager.create();  
	   //以默认配置添加一个名叫cacheName的Cache。  
	   cacheManager.addCache("cache4");  
	   Cache cache4 = cacheManager.getCache("cache4");  
	   Element ele = new Element("a", "1");  
	   cache4.put(ele);  
	   System.out.println(cache4.get("a"));  
	   
	   cacheManager.shutdown();
	}
	/*
	 * cache 更新
	 */
	private static void test4(){
		CacheManager cacheManager = CacheManager.create();  
	   //以默认配置添加一个名叫cacheName的Cache。  
	   cacheManager.addCache("cache4");  
	   Cache cache4 = cacheManager.getCache("cache4");  
	   cache4.put(new Element("a", "1"));  
	   
	   //替换元素的时候只有Cache中已经存在对应key的元素时才会替换，否则不操作。  
	   //cache4.replace(new Element("a", "3"));
	   
	   cache4.put(new Element("a", "2"));
	   System.out.println(cache4.get("a"));  
	   
	   cacheManager.shutdown();
	   
	}
	
	private static void test5(){
		CacheManager cacheManager = CacheManager.create();  
		   //以默认配置添加一个名叫cacheName的Cache。  
		   cacheManager.addCache("cache4");  
		   Cache cache4 = cacheManager.getCache("cache4");  
		   cache4.put(new Element("a", "1"));  
		   cache4.remove("a");
		   System.out.println(cache4.get("a"));  
		   
		   cacheManager.shutdown();
	}
}
