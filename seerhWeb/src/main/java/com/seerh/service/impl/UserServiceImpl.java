package com.seerh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seerh.dao.UserMapper;
import com.seerh.entity.User;
import com.seerh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(readOnly=true)
	public User getUserByUserName(String userName) {
		return userMapper.getUserByUserName(userName);
	}

}
