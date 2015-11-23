package com.seerh.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.seerh.entity.Permission;
import com.seerh.entity.Role;
import com.seerh.entity.User;
import com.seerh.service.PermissionService;
import com.seerh.service.RoleService;
import com.seerh.service.UserService;

public class MyCasRealm extends CasRealm {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		User user = userService.getUserByUserName(currentUsername);
		List<String> roleList = new ArrayList<>();
		List<String> permissionList = new ArrayList<>();
		if(user != null) {
			List<Role> roles = roleService.getRolesByUserId(user.getId());
			for(Role role : roles) {
				roleList.add(role.getCode());
				List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getId());
				for(Permission permission : permissions) {
					permissionList.add(permission.getCode());
				}
			}
		} else 
			throw new AuthorizationException(); 
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        simpleAuthorInfo.addRoles(roleList);  
        simpleAuthorInfo.addStringPermissions(permissionList); 
		return simpleAuthorInfo;
	}

}
