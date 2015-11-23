package com.seerh.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seerh.entity.User;
import com.seerh.service.UserService;
  
@Controller  
public class UserController {  
	
	@Autowired
	private UserService userService;
	
	/*@RequiresRoles(value={"admin", "user"}, logical=Logical.OR)
	@RequiresPermissions("store:view") */
    @RequestMapping(value="/mydemo/getUserInfo") 
    public String getUserInfo(HttpServletRequest request){  
        //User currentUser = (User)request.getSession().getAttribute("currentUser");
		//"记住我"功能，因为没有登录，此时session中没有用户信息，需要从SecurityUtils中获取Subject
		Subject subject = SecurityUtils.getSubject();
		String userName = subject.getPrincipal().toString();
		User currentUser = userService.getUserByUserName(userName);
		//将用户信息放入session
		subject.getSession().setAttribute("currentUser", currentUser);
		//从session中获取session
		currentUser = (User)request.getSession().getAttribute("currentUser");
        System.out.println("当前登录的用户为[" + currentUser.getNickName() + "]");  
        request.setAttribute("currUser", currentUser.getNickName());  
        return "/user/info";  
    }  
    
    @RequiresPermissions("store:view")
    @RequestMapping(value="/admin/userList")  
    public String userList(HttpServletRequest request){  
        return "/admin/listUser";  
    }  
    
    @ResponseBody
    @RequestMapping(value="/test")  
    public String test(HttpServletRequest request, String name){  
        return name;  
    } 
    
}  
