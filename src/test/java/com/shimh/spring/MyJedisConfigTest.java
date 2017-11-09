package com.shimh.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shimh.base.redis.JedisTemplate;
import com.shimh.spring.config.MyJedisConfig;
import com.shimh.spring.config.RootConfig;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyJedisConfig.class)
public class MyJedisConfigTest {
	
	@Autowired
	private JedisTemplate jedisTemplate;
	
	@Test
	public void test1(){
		jedisTemplate.set("test1", "aaaa");
        System.out.println(jedisTemplate.get("test1"));
	}
	
	
}
