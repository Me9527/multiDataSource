package com.cpst.framework.struts2;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionProxyFactory;

public class CustomStrutsActionProxyFactory extends DefaultActionProxyFactory {

	//TODO comment
    public ActionProxy createActionProxy(String namespace, String actionName, String methodName, Map<String, Object> extraContext, boolean executeResult, boolean cleanupContext) {
        ActionInvocation inv = new CustomDefaultActionInvocation(extraContext, true);
        container.inject(inv);
        return createActionProxy(inv, namespace, actionName, methodName, executeResult, cleanupContext);
    }

}
