package com.cpst.framework.spring;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class PrototypeLoadBalanceDataSource extends AbstractRoutingDataSource {
	private Lock lock = new ReentrantLock();
	private int counter = 0;
	private int dataSourceNumber = 3;
	
	@Override
	protected Object determineCurrentLookupKey() {
		lock.lock();
		try {
//			counter++;
//			int lookupKey = counter % getDataSourceNumber();
			return  "A";
		} finally {
			lock.unlock();
		}
	}
}
