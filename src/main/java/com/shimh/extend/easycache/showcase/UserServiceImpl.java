package com.shimh.extend.easycache.showcase;

import com.shimh.extend.easycache.annotation.CacheEvict;
import com.shimh.extend.easycache.annotation.Cacheable;

public class UserServiceImpl implements UserService {
	
	@Cacheable(cacheName="aa",keys = {"getUserById"})
	public String getUserById(String id){
		  System.out.println("返回user");
		return id + "user";
	}
	
	@CacheEvict(cacheName="aa",keys = {"getUserById"})
	public void remove(){
		
	}
}
