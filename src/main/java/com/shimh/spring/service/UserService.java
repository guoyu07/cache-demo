package com.shimh.spring.service;

import com.shimh.spring.model.User;

public interface UserService {
	
	
	User getUserByIdCache1(String id);
	User getUserByIdCache2(String id);
	
	void addUser(User user);
	
	void removeAllCache1();
	void removeAllCache2();
	
	void removeUserById(String id);
	
	public User getUserByIdAndName(String id, String name);
	
	public UserService aopContextTest();

}
