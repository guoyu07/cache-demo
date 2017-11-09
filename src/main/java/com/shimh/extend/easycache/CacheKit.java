package com.shimh.extend.easycache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheKit {
	
    private static CacheManager cacheManager;
    private static final Logger log = LoggerFactory.getLogger(CacheKit.class);

    private static Cache getOrAddCache(String cacheName) {

        CacheManager cacheManager = getManager();
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            CacheManager var2 = cacheManager;
            synchronized (cacheManager) {
                cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    log.debug("Could not find cache config [" + cacheName + "], using default.");
                    cacheManager.addCacheIfAbsent(cacheName);
                    cache = cacheManager.getCache(cacheName);
                    log.debug("Cache [" + cacheName + "] started.");
                }
            }
        }
        return cache;
    }


    private static CacheManager getManager() {
        if (cacheManager == null) {
            cacheManager = CacheManager.create();
        }
        return cacheManager;
    }


    public static void put(String cacheName, Object key, Object value) {
        getOrAddCache(cacheName).put(new Element(key, value));
    }

    public static Object get(String cacheName, Object key) {
        Element element = getOrAddCache(cacheName).get(key);
        return element != null ? element.getObjectValue() : null;
    }
    public static void put(String cacheName, Object[] key, Object value) {
    	for(Object k:key){
    		getOrAddCache(cacheName).put(new Element(k, value));
    	}
    }
    
    public static Object get(String cacheName, Object[] key) {
    	for(Object k:key){
    		Object value = get(cacheName,k);
    		if(null != value){
    			return value;
    		}
    	}
    	return null;
    }

    public static void remove(String cacheName, Object key) {
        getOrAddCache(cacheName).remove(key);
    }
    
    public static void remove(String cacheName, Object key[]) {
    	for(Object k:key){
    		getOrAddCache(cacheName).remove(k);
    	}
    }
	
}
