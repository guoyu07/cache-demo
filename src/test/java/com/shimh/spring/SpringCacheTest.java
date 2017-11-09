package com.shimh.spring;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shimh.spring.config.SpringCacheConfig;
import com.shimh.spring.model.User;
import com.shimh.spring.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCacheConfig.class)
public class SpringCacheTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test1(){
		
		User user  = new User("1","a",20);
		userService.addUser(user);
		
		User cacheUser = userService.getUserByIdCache1("1");
		
		System.out.println("--------");
		
		userService.removeUserById("1");
		
		User getUser = userService.getUserByIdCache1("1");
		
		//--------
		//call getUserById id = 1
	}
	
	/*
	 * 代码获取代理类，内部方法调动
	 */
	@Test
	public void test2(){
		System.out.println(userService == userService.aopContextTest());
	}
	
	/*
	 * 多个CacheManager测试
	 */
	@Test
	public void test3(){
		User user  = new User("2","b",20);
		userService.addUser(user);
		
		System.out.println("放入 cache1 cache2 中--------");
		
		userService.getUserByIdCache1("2");
		
		userService.getUserByIdCache2("2");
		
		System.out.println(" 结束  get--------");
		
		userService.removeAllCache1();
		
		System.out.println("cache1 清空  cache2不处理--------");
		
		userService.getUserByIdCache1("2");
		
		userService.getUserByIdCache2("2");
		
	}
}
