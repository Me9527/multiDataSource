package com.cpst.framework.base;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cpst.framework.base.util.IExportUtil;

public class BaseService {
	protected final Log logger = LogFactory.getLog(getClass());
	protected static final BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
	
	protected IExportUtil exportUtil;

	public IExportUtil getExportUtil() {
		return exportUtil;
	}

	public void setExportUtil(IExportUtil exportUtil) {
		this.exportUtil = exportUtil;
	}
}
