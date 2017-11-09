package com.shimh.extend.easycache;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shimh.extend.easycache.annotation.CacheEvict;
import com.shimh.extend.easycache.annotation.Cacheable;

public class CacheInterceptor implements InvocationHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CacheInterceptor.class);
	
	private Object target;
	
	public CacheInterceptor(Object target){
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//Annotation[] annotations = method.getAnnotations(); 获取的是接口的方法信息 不是具体实现类
		
		//得到实现类的方法
		Method targetMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
		Annotation[] annotations = targetMethod.getAnnotations();
		
		if(annotations.length > 0){
			for(Annotation a:annotations){
				if(Cacheable.class == a.annotationType()){
					if(Void.class == method.getReturnType())
						return method.invoke(target, args);
					
					Cacheable cacheable = (Cacheable) a;
					String cacheName = cacheable.cacheName();
					String[] keys = cacheable.keys();
					Object value = CacheKit.get(cacheName, keys);
					
					if(null == value){
						Object obj = method.invoke(target, args);
						CacheKit.put(cacheName, keys, obj);
						log.info("cache " + cacheName + "== put value " + obj);
						return obj;
					}else{
						log.info("cache " + cacheName + "== get value " + value);
						return value;
						
					}
				}else if(CacheEvict.class == a.annotationType()){
					CacheEvict cacheEvict = (CacheEvict) a;
					String cacheName = cacheEvict.cacheName();
					String[] keys = cacheEvict.keys();
					
					log.info("cache " + cacheName + "== remove keys " + keys);
					
					CacheKit.remove(cacheName, keys);
					
					return method.invoke(target, args);
				}
			}
			
		}else{
			return  method.invoke(target, args);
		}
		
		return null;
	}
	
}
