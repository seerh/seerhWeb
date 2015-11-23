package com.seerh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seerh.dao.RoleMapper;
import com.seerh.entity.Role;
import com.seerh.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRolesByUserId(Long userId) {
		return roleMapper.getRolesByUserId(userId);
	}

}
