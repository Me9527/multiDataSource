package com.cpst.framework.spring;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class MultiDataSourceRoute implements MethodBeforeAdvice {
	private final Log logger = LogFactory.getLog(getClass());
	
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		logger.info("method = " + method.getName());
	}
	
}
