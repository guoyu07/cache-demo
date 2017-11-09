package com.shimh.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shimh.spring.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static List<User> users = new ArrayList<User>();
	
	@Cacheable(cacheNames="cache1")
	@Override
	public User getUserByIdCache1(String id) {
		
		System.out.println("call getUserByIdCache1 id = " + id);
		for(User u:users){
			if(u.getId().equals(id)){
				return u;
			}
		}
		return null;
	}

	@Cacheable(cacheNames="cache1",condition = "#name.length() < 4")
	@Override
	public User getUserByIdAndName(String id, String name) {
		System.out.println("call getUserByIdAndName id = " + id + " name = " + name);
		
		for(User u:users){
			if(u.getId().equals(id) && u.getName().equals(name)){
				return u;
			}
		}
		return null;
	}
	
	@CachePut(cacheNames = {"cache1","cache2"},key = "#user.getId()")
	@Override
	public void addUser(User user) {
		users.add(user);
		
	}
	/*
	 * allEntries	是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存	例如：
	 * beforeInvocation	是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
	 */
	
	@CacheEvict(cacheNames = "cache1",allEntries = false,beforeInvocation = false)
	@Override
	public void removeUserById(String id) {

		for(User u:users){
			if(u.getId().equals(id)){
				users.remove(u);
				break;
			}
		}
		
	}

	@Override
	public UserService aopContextTest() {
		UserService proxy = (UserService) AopContext.currentProxy();
		return proxy;
		
	}
	
	@Cacheable(cacheNames="cache2")
	@Override
	public User getUserByIdCache2(String id) {
		System.out.println("call getUserByIdCache2 id = " + id);
		for(User u:users){
			if(u.getId().equals(id)){
				return u;
			}
		}
		return null;
	}
	
	@CacheEvict(cacheNames = "cache1",allEntries = true,beforeInvocation = false)
	@Override
	public void removeAllCache1() {
		// TODO Auto-generated method stub
		
	}
	@CacheEvict(cacheNames = "cache2",allEntries = true,beforeInvocation = false)
	@Override
	public void removeAllCache2() {
		// TODO Auto-generated method stub
		
	}
}
