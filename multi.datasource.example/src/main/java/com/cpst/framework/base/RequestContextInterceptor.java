package com.cpst.framework.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RequestContextInterceptor implements Interceptor {
	private static final long serialVersionUID = 2195370264611741644L;
	private final Log logger = LogFactory.getLog(getClass());
	
	
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ReqContextHolder.setContext(setCommnParam());
		
		String result = invocation.invoke();
		
		ReqContextHolder.cleanContext();
		
		return result;
	}

	private CommParamRequestContext setCommnParam() {
		
		HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		CommParamRequestContext context = new CommParamRequestContext();
		try{
			if(null != request.getParameter(Constants.CURR_PAGE))
				context.getPage().setCurrPage(Integer.parseInt(request.getParameter(Constants.CURR_PAGE)));
			if(null != request.getParameter(Constants.PAGE_SIZE))
				context.getPage().setPageSize(Integer.parseInt(request.getParameter(Constants.PAGE_SIZE)));
			context.getCommParam().put(Constants.SORT_ORDER, request.getParameter(Constants.SORT_ORDER));
			
			context.getCommParam().put(Constants.COLUMN_TITLE, request.getParameter(Constants.COLUMN_TITLE));
			context.getCommParam().put(Constants.COLUMN_FIELD_MAP, request.getParameter(Constants.COLUMN_FIELD_MAP));
			context.getCommParam().put(Constants.EXPORT_TYPE, request.getParameter(Constants.EXPORT_TYPE));
			context.getCommParam().put(Constants.EXPORT_FILE_NAME, request.getParameter(Constants.EXPORT_FILE_NAME));
			context.getCommParam().put(Constants.IN_OUT, request.getParameter(Constants.IN_OUT));
			
		}catch(Exception e){
			logger.error("Error in set CommParamRequestContext", e);
		}
		
		request.setAttribute(Constants.PAGE, context.getPage());
		request.setAttribute(Constants.SORT_ORDER, context.getCommParam().get(Constants.SORT_ORDER));
		
		return context;
	}
	
	public void init() {
		logger.info(this.getClass().getSimpleName() + " initial finished.");
	}

	public void destroy() {
		logger.info(this.getClass().getSimpleName() + " destroy finished.");
	}
}
