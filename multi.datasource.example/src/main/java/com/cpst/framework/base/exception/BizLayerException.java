package com.cpst.framework.base.exception;

public class BizLayerException extends BaseUnCheckedException {

	private static final long serialVersionUID = 2631490367837964951L;

	public BizLayerException(String msg) {
		super(msg);
	}

	public BizLayerException(String message, Throwable cause) {
		super(message, cause);
	}
}
