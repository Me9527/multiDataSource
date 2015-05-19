package com.cpst.framework.struts2;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;


public class StrutsPrepareAndExecuteFilterUsePathPatternMatchingConfigFile extends StrutsPrepareAndExecuteFilter {

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
    	CustomInitOperations init = new CustomInitOperations();
        try {
            FilterHostConfig config = new FilterHostConfig(filterConfig);
            init.initLogging(config);
            Dispatcher dispatcher = init.initDispatcher(config);
            init.initStaticContentLoader(config, dispatcher);

            prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);
            execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);
			this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);

            postInit(dispatcher, filterConfig);
        } finally {
            init.cleanup();
        }

    }
}
