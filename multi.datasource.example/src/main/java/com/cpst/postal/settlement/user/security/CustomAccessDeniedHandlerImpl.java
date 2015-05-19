package com.cpst.postal.settlement.user.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandlerImpl implements AccessDeniedHandler {

    protected static final Log logger = LogFactory.getLog(CustomAccessDeniedHandlerImpl.class);

    private String errorPage;
    private String defaultErrorPage;
    
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
            if (errorPage != null) {
            	String s = request.getServletPath();	/* /jsp/zwcjrg/IEMF/main.jsp */
            	int flashCount = 0; int i=0;
            	for( ;i<s.length(); i++){
            		if(s.charAt(i) == '/')
            			flashCount++;
            		if(3 == flashCount)
            			break;
            	}
            	if(3 == flashCount)
            		s = s.substring(0, i+1) + errorPage;
            	else
            		s = defaultErrorPage;
  
                // Put exception into request scope (perhaps of use to a view)
                request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
                // Set the 403 status code.
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                // forward to error page.
                RequestDispatcher dispatcher = request.getRequestDispatcher(s);
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            }
        }
    }

    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage can not begin with '/'");
        }

        this.errorPage = errorPage;
    }

	public void setDefaultErrorPage(String defaultErrorPage) {
        if ((defaultErrorPage != null) && !defaultErrorPage.startsWith("/")) {
            throw new IllegalArgumentException("defaultErrorPage must begin with '/'");
        }
        
		this.defaultErrorPage = defaultErrorPage;
	}
    
    
}
