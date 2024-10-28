package com.gd.web.exception;


import com.gd.core.exception.BusinessException;

/**
 * 用户未登录异常
 */
public class UnauthorizedException extends BusinessException {

	public UnauthorizedException() {
		super("用户未登录");
	}

}
