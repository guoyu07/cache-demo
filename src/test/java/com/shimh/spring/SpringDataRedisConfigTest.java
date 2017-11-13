package com.shimh.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shimh.spring.config.SpringDataRedisConfig;
import com.shimh.spring.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataRedisConfig.class)
public class SpringDataRedisConfigTest {
	
	@Autowired
	private RedisTemplate<String,User> redisTemplate;
	
	//@Autowired
	//private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test1(){
		
		User user  = new User("1","a",20);
		
		redisTemplate.opsForValue().set(user.getId(), user);
		
		User cache = redisTemplate.opsForValue().get(user.getId());
		
		System.out.println(cache);
		
	}
	
	@Test
	public void testOpsForList(){
		
		User user1  = new User("1","a",20);
		User user2  = new User("2","b",20);
		
		redisTemplate.opsForList().leftPush("users", user1);
		redisTemplate.opsForList().leftPush("users", user2);
		
		System.out.println(redisTemplate.opsForList().range("users", 0, -1));
		
	}
	
	@Test
	public void testBound(){
		
		User user1  = new User("1","a",20);
		User user2  = new User("2","b",20);
		
		BoundListOperations<String, User> listOp = redisTemplate.boundListOps("users");
		listOp.leftPush(user1);
		listOp.leftPush(user2);
		
		System.out.println(redisTemplate.opsForList().range("users", 0, -1));
		
	}
	
	
}
