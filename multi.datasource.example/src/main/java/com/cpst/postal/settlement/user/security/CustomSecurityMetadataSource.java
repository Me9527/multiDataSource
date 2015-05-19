package com.cpst.postal.settlement.user.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.cpst.postal.settlement.user.dao.ICommonDAO;
import com.cpst.postal.settlement.user.model.BResourcePermission;

public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	private ICommonDAO commonDAO;
    private String rolePrefix = "perm_";
    private List<String> excludeUrls;
    
    public void afterPropertiesSet() throws java.lang.Exception{
//    	+ " m order by m.sequence"
		String hql = "from " + BResourcePermission.class.getSimpleName() ; 
		@SuppressWarnings("unchecked")
		List<BResourcePermission> rs = (List<BResourcePermission>) commonDAO.find(hql);
		if(null == rs)
			return;
		
		for(BResourcePermission perm: rs){
			if(perm.getRType() == 10){	//==10 表示url形式的资源权限,而不是业务层的(service层)
				SecurityConfig configAttribute = new SecurityConfig(rolePrefix+perm.getSeqId());
				Collection<ConfigAttribute> c = new HashSet<ConfigAttribute>();
				c.add(configAttribute);
				AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(perm.getResources());
				requestMap.put(requestMatcher, c);
			}

		}
    }
    
    public CustomSecurityMetadataSource() {
    	Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
    	this.requestMap = requestMap;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
//        for(String s: excludeUrls){
//        	
//        }
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

    
}
