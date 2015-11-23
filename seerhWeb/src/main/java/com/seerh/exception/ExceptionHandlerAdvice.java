package com.seerh.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	/**
	 * 权限异常处理
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler({UnauthorizedException.class})
	public String unauthorized(Exception exception,WebRequest request) {
		exception.printStackTrace();
		return "redirect:/mydemo/unauthorized";
	}
	
}
