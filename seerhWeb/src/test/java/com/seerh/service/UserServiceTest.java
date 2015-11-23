package com.seerh.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.seerh.entity.User;


public class UserServiceTest extends BaseTest{
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testGetUserByUserName(){
		User user = userService.getUserByUserName("admin");
		System.out.println(user.getNickName());
	}
	
}
