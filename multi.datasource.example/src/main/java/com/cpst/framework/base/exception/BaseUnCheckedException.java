package com.cpst.framework.base.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 所有runtime异常的基类
 *
 * @author todd
 * @version 1.2 , 2008/5/6
 * @since JDK1.5
 */


public class BaseUnCheckedException extends NestedRuntimeException {
	private static final long serialVersionUID = -8995060188398373784L;

	/**
	 * 构造方法
	 *
	 * @param msg
	 * 			出现异常后提示信息
	 */
	public BaseUnCheckedException(String msg) {
		super(msg);
	}

	/**
	 * 构造方法
	 *
	 * @param msg
	 * 			出现异常后提示信息
	 * @param ex
	 * 			抛出的异常
	 */
	public BaseUnCheckedException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
