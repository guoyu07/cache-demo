package com.shimh.extend.easycache.showcase;

import com.shimh.extend.easycache.annotation.CacheEvict;
import com.shimh.extend.easycache.annotation.Cacheable;

public interface UserService {
	
	
	public String getUserById(String id);
	
	
	public void remove();
}
