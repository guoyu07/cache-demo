package com.shimh.spring.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@ComponentScan(basePackages = "com.shimh.spring.service")
@EnableCaching
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringCacheConfig {
	
	
	/*
	 * 当我们调用cacheManager.getCache(cacheName) 时，会先从第一个cacheManager中查找有没有cacheName的cache，
	 * 如果没有接着查找第二个，如果最后找不到，因为fallbackToNoOpCache=true，那么将返回一个NOP的Cache否则返回null。
	 */
	@Primary
	@Bean
	public CompositeCacheManager cacheManager(){
		CompositeCacheManager manager = new CompositeCacheManager();
		
		List<CacheManager> cs = new ArrayList<CacheManager>();
		cs.add(ehCacheCacheManager());
		cs.add(concurrentMapCacheManager());
		
		manager.setCacheManagers(cs);
		
		manager.setFallbackToNoOpCache(true);
		
		
		return manager;
	}
	
	
	@Bean
	public EhCacheCacheManager ehCacheCacheManager(){
		
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
		ehCacheCacheManager.setCacheManager(factoryBean.getObject());
		
		//transactionAware表示是否事务环绕的，如果true，则如果事务回滚，缓存也回滚，默认false。
		//ehCacheCacheManager.setTransactionAware(true);
		return ehCacheCacheManager;
	}
	
	@Bean
	public ConcurrentMapCacheManager concurrentMapCacheManager(){
		ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
		concurrentMapCacheManager.setCacheNames(Arrays.asList("cache2"));
		
		return concurrentMapCacheManager;
	}
	
/*	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		return RedisCacheManager.create(connectionFactory);
	}*/


}
