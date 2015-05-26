package com.cpst.postal.settlement.md02.services;

import java.util.Map;

import com.cpst.framework.base.Page;


public interface IMd02Service {

	public void addData(Object data);
	
	public void update(Object data);
	
	public void delByIds(Class<?> clazz, Integer seqIds[]);
	
	public Page getPage(Class<?> clazz, Map<String, Object> params);
	
	public Page  findByFieldsToPage(Class<?> clazz,Map<String, ?> fields);

}
