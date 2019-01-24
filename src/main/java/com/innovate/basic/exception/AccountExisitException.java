package com.innovate.basic.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 自定义Exception,判断当前用户是否已经登录状态了
 * @author IvanHsu
 * @2018年3月31日 下午3:25:32
 */
public class AccountExisitException extends AccountException {
	private static final long serialVersionUID = 1791406294011753831L;

	public AccountExisitException() {
	}

	public AccountExisitException(String message) {
		super(message);
	}

	public AccountExisitException(Throwable cause) {
		super(cause);
	}

	public AccountExisitException(String message, Throwable cause) {
		super(message, cause);
	}
}
