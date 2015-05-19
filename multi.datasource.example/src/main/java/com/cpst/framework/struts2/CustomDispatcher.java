package com.cpst.framework.struts2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.BeanSelectionProvider;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;

public class CustomDispatcher extends Dispatcher {

	protected final Log logger = LogFactory.getLog(getClass());
	private ClassLoader beanClassLoaderCopy = ClassUtils.getDefaultClassLoader();
	private String moduleActionMapping;
	private static final String MODULEACTIONMAPPING = "moduleActionMapping";
	/* 必须与父类的DEFAULT_CONFIGURATION_PATHS的值保持一致 */
	private static final String DEFAULT_CONFIGURATION_PATHS = "struts-default.xml,struts-plugin.xml,struts.xml";
	
	private ServletContext servletContextCopy;
    
	private Map<String, XmlConfigurationProvider>  xmlConfigurationProviders = new HashMap<String, XmlConfigurationProvider>();
	private Map<String, StrutsXmlConfigurationProvider>  strutsXmlConfigurationProviders = new HashMap<String, StrutsXmlConfigurationProvider>();
	
    public CustomDispatcher(ServletContext servletContext, Map<String, String> initParams) {
    	super(servletContext, initParams);
    	this.servletContextCopy = servletContext;
    	setModuleMappingResource();
    }
    
    private void setModuleMappingResource() {
    	String s = servletContextCopy.getInitParameter(MODULEACTIONMAPPING);
    	if(null != s)
    		this.moduleActionMapping = s;
    	else{
    		this.moduleActionMapping = null;
    		logger.info("You don't set module action mapping configs, you should use 'org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter' instead.");
    	}
    	
    }
    
    private void initStruts2DefaultConfig() {
    	String configPaths = DEFAULT_CONFIGURATION_PATHS;
        String[] files = configPaths.split("\\s*[,]\\s*");
        for (String file : files) {
            if (file.endsWith(".xml")) {
                if ("xwork.xml".equals(file)) {
                	getConfigurationManager().addContainerProvider(createXmlConfigurationProvider(file, false));
                } else {
                	getConfigurationManager().addContainerProvider(createStrutsXmlConfigurationProvider(file, false, servletContextCopy));
                }
            } else {
                throw new IllegalArgumentException("Invalid configuration file name");
            }
        }
    }
    
	public void preInit() {	//完成模块化配置文件的加载, 例如web.xml 中配置的 classpath*:modules/**/struts-conf/*.xml 
    	if (this.getConfigurationManager() == null) {
    		this.setConfigurationManager( createConfigurationManager(BeanSelectionProvider.DEFAULT_BEAN_NAME) );
    	}
    	
    	initStruts2DefaultConfig();
		ApplicationContext springContext = (ApplicationContext)servletContextCopy.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if(null != springContext)
			beanClassLoaderCopy = springContext.getClassLoader();
    	//load module struts configurations use spring
		PathMatchingResourcePatternResolver actionConfigResolver = new PathMatchingResourcePatternResolver(beanClassLoaderCopy);
		if (this.moduleActionMapping != null) {
			String[] actionConfigs = StringUtils.tokenizeToStringArray(this.moduleActionMapping, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			if(null == actionConfigs)
				return;
			for (int i = 0; i < actionConfigs.length; i++) {
				String mappingResources = actionConfigs[i].trim();
				try {
					Resource[] resource = actionConfigResolver.getResources(mappingResources);
					for (int k = 0; k < resource.length; k++) 
						getConfigurationManager().addContainerProvider(createStrutsXmlConfigurationProvider(resource[k].getFile().getAbsolutePath(), false, servletContextCopy));
				} catch (IOException e) {
//					logger.error("Load module struts2 action mapping error.");
					throw new IllegalArgumentException("Load module struts2 action mapping error.",e);
				}

			}
		}
    }

    protected XmlConfigurationProvider createXmlConfigurationProvider(String filename, boolean errorIfMissing) {
    	XmlConfigurationProvider t = xmlConfigurationProviders.get(filename);
    	if(null != t)
    		return t;
    	t = new XmlConfigurationProvider(filename, errorIfMissing);
    	xmlConfigurationProviders.put(filename, t);
    	return t;
    }

    protected XmlConfigurationProvider createStrutsXmlConfigurationProvider(String filename, boolean errorIfMissing, ServletContext ctx) {
    	StrutsXmlConfigurationProvider t = strutsXmlConfigurationProviders.get(filename);
    	if(null != t)
    		return t;
    	t = new StrutsXmlConfigurationProvider(filename, errorIfMissing, ctx);
    	strutsXmlConfigurationProviders.put(filename, t);
    	return t;
    }
    
    @Override
	public void init() {
    	 preInit();
    	 super.init();
    	 this.xmlConfigurationProviders.clear();
    	 this.xmlConfigurationProviders = null;
    	 this.strutsXmlConfigurationProviders.clear();
    	 this.strutsXmlConfigurationProviders = null;
    }
    
}
