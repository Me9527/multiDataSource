package com.cpst.framework.base;

public class ReqContextHolder {
	private static ThreadLocal<CommParamRequestContext> requestContext = new ThreadLocal<CommParamRequestContext>();

	public static void setContext(CommParamRequestContext context) {
		requestContext.set(context);
	}
	
	public static CommParamRequestContext getContext() {
		return requestContext.get();
	}
	
	public static void cleanContext() {
		requestContext.remove();
	}
}
