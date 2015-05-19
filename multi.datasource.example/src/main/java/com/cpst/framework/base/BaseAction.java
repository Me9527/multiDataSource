package com.cpst.framework.base;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class BaseAction {

	protected final Log logger = LogFactory.getLog(getClass());
	protected static final BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
    
	protected String methodName; 

	private Long seqIds[];
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Long[] getSeqIds() {
		return seqIds;
	}

	public void setSeqIds(Long[] seqIds) {
		this.seqIds = seqIds;
	}

}