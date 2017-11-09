package com.shimh.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class SpringDataJedisConfig {
	
	
	public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
		
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		stringRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		
		return stringRedisTemplate;
	}
	
	
	
	
	public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
		
		JedisConnectionFactory factory = new JedisConnectionFactory(jedisPoolConfig);
		
		factory.setHostName("localhost");
		
		return factory;
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
	
	
	
	
	
	
	
	
	
}
