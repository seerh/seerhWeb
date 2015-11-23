package com.seerh.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.seerh.entity.Permission;
import com.seerh.entity.Role;
import com.seerh.entity.User;
import com.seerh.service.PermissionService;
import com.seerh.service.RoleService;
import com.seerh.service.UserService;

public class MyRealm extends AuthorizingRealm {

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

	/**
	 * 登陆
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.getUserByUserName(token.getUsername());
		if (null != user) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(
					user.getUserName(), user.getPassword(), user.getNickName());
			this.setSession("currentUser", user);
			return authcInfo;
		} else 
			return null;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
