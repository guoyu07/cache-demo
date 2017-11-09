package com.shimh.spring.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shimh.base.redis.JedisTemplate;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
public class MyJedisConfig {
	
	
	@Bean
	public JedisTemplate jedisTemplate(JedisPool jedisPool){
		JedisTemplate jedisTemplate = new JedisTemplate(jedisPool);
		return jedisTemplate;
	}
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大实例总数 
        jedisPoolConfig.setMaxTotal(150); 
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。 
        jedisPoolConfig.setMaxIdle(30); 
        jedisPoolConfig.setMinIdle(10); 
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException； 
        jedisPoolConfig.setMaxWaitMillis(3 * 1000); 
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；  
        jedisPoolConfig.setTestOnBorrow(true); 
        // 在还会给pool时，是否提前进行validate操作  
        jedisPoolConfig.setTestOnReturn(true); 
        jedisPoolConfig.setTestWhileIdle(true); 
        jedisPoolConfig.setMinEvictableIdleTimeMillis(500); 
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1000); 
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(1000); 
        jedisPoolConfig.setNumTestsPerEvictionRun(100); 
        return jedisPoolConfig;
	}
	
	/*
	 * 单机的  即部署 一个 redis
	 */
	@Bean
	public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig){
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost");
		return jedisPool;
	}
	
	/*
	 * 分片 即 部署多个redis，相互独立，采用一致性哈希算法，将不同的key 储存到不同的redis。 数据的扩容采
	 */
	
	@Bean
	public ShardedJedisPool sharedJedisPool(JedisPoolConfig jedisPoolConfig){
		
		List<JedisShardInfo> shards = new ArrayList<>();
		
		shards.add(new JedisShardInfo("localhost",6379,"redis1"));
		shards.add(new JedisShardInfo("localhost",6380,"redis2"));
		shards.add(new JedisShardInfo("localhost",6381,"redis3"));
		
		ShardedJedisPool shared= new ShardedJedisPool(jedisPoolConfig, shards);
		
		return shared;
	}
	
	/*
	 * redis的哨兵模式，监控 master-slave，master节点down机，自动切换。
	 */
	@Bean
	public JedisSentinelPool jedisSentinelPool(JedisPoolConfig jedisPoolConfig){
		
		Set<String> sentinels = new HashSet<String>();  
	    sentinels.add("172.18.18.207:26379");  
		JedisSentinelPool sentinel = new JedisSentinelPool("master", sentinels, jedisPoolConfig);
		return sentinel;
	}
	

	
	
}
