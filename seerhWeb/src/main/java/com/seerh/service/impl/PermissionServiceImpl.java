package com.seerh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seerh.dao.PermissionMapper;
import com.seerh.entity.Permission;
import com.seerh.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> getPermissionsByRoleId(Long roleId) {
		return permissionMapper.getPermissionsByRoleId(roleId);
	}

}
