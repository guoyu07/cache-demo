package com.shimh.extend.easycache.showcase;

import java.lang.reflect.Proxy;

import com.shimh.extend.easycache.CacheInterceptor;
import com.shimh.extend.easycache.ProxyFactory;

public class Test {
	public static void main(String[] args) {
		
		UserService u = ProxyFactory.getProxy(UserService.class, new UserServiceImpl());
		
		u.getUserById("1");
		u.getUserById("1");
		u.remove();
		u.getUserById("1");
		
	}
}
