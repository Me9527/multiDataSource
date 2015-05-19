package com.cpst.framework.base.exception;

//NestedCheckedException
public class DAOLayerException extends BaseUnCheckedException {

	private static final long serialVersionUID = 2631490367837964951L;

	public DAOLayerException(String msg) {
		super(msg);
	}

	public DAOLayerException(String message, Throwable cause) {
		super(message, cause);
	}
}
