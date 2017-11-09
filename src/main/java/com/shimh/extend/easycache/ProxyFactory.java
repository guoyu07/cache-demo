package com.shimh.extend.easycache;

import java.lang.reflect.Proxy;

public class ProxyFactory {
	
	public static <T> T getProxy(Class<T> clazz,Object obj){
		
		return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new CacheInterceptor(obj));
		
	}
	
	
}
