package com.cpst.framework.base.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class ReadPool implements InitializingBean, DisposableBean {
	private final Log logger = LogFactory.getLog(getClass());
	private ThreadPoolExecutor readPool;
	
	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private int queueSize;
	
	private TimeUnit unit = TimeUnit.SECONDS;
	private BlockingQueue<Runnable> workQueue;

	// private RejectedExecutionHandler handler;

	public void afterPropertiesSet() throws java.lang.Exception {
		logger.info("corePoolSize = " + corePoolSize);
		logger.info("maximumPoolSize = " + maximumPoolSize);
		logger.info("keepAliveTime = " + keepAliveTime);
		logger.info("queueSize = " + queueSize);
		createPool();
	}

    @Override  
    public void destroy() throws Exception {  
    	readPool.shutdown();
    }  
    
	public void createPool() {
		workQueue = new LinkedBlockingQueue<Runnable>(queueSize);
		readPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, unit, workQueue);
	}

	public ThreadPoolExecutor getReadPool() {
		return readPool;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		this.workQueue = workQueue;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

}
