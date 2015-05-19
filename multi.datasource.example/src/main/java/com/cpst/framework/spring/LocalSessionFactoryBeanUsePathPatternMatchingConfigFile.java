package com.cpst.framework.spring;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class LocalSessionFactoryBeanUsePathPatternMatchingConfigFile extends LocalSessionFactoryBean {

	protected final Log logger = LogFactory.getLog(getClass());
	private ClassLoader beanClassLoaderCopy = ClassUtils.getDefaultClassLoader();
	private String moduleMappingResource;

	
	public void setModuleMappingResource(String moduleMappingResource) {
		this.moduleMappingResource = moduleMappingResource;
	}

	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		super.setBeanClassLoader(beanClassLoader);
		beanClassLoaderCopy = beanClassLoader;
	}
	
	protected void postProcessMappings(Configuration config) throws HibernateException {
		PathMatchingResourcePatternResolver hbmConfigResolver = new PathMatchingResourcePatternResolver(beanClassLoaderCopy);
		if (this.moduleMappingResource != null) {
			String[] moduleMappingResources = StringUtils.tokenizeToStringArray(this.moduleMappingResource, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			if(null == moduleMappingResources)
				return;
			for (int i = 0; i < moduleMappingResources.length; i++) {
				String mappingResources = moduleMappingResources[i].trim();
				try {
					Resource[] resource = hbmConfigResolver.getResources(mappingResources);
					for (int k = 0; k < resource.length; k++) 
						config.addInputStream(resource[k].getInputStream());
				} catch (IOException e) {
					logger.error("Load module Hibernate mapping resource error.");
					throw new HibernateException(e);
				}

			}
		}
	}
	
}
