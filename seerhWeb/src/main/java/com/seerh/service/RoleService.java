package com.seerh.service;

import java.util.List;

import com.seerh.entity.Role;

public interface RoleService {
	
	List<Role> getRolesByUserId(Long userId);

}
