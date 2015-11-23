package com.seerh.service;

import java.util.List;

import com.seerh.entity.Permission;

public interface PermissionService {
	
	List<Permission> getPermissionsByRoleId(Long roleId);

}
