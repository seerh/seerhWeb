package com.seerh.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

public class MyUserFilter extends UserFilter {

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;  
		if ("XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) {
			Subject subject = SecurityUtils.getSubject();
	        Session session = subject.getSession();
	        String casUrl = httpRequest.getHeader("casUrl");
	        SavedRequest savedRequest = new SavedRequest("GET", httpRequest.getQueryString(), casUrl);
	        session.setAttribute("shiroSavedRequest", savedRequest);
			WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else 
        	saveRequestAndRedirectToLogin(request, response);
        return false;
    }
	
}
