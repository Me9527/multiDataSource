package com.cpst.postal.settlement.md02.dao;

import java.io.Serializable;
import java.util.List;

public interface IMd02CommonDAO {
	
	void addData(Object data);
	
	void update(Object data);
	
	void delById(Class<?> clazz, Serializable id);
	
	List<?> find(String queryString, Object... values);
	
	List<?> findByFieldAll(final String hql, final List<Object> values);
	
	Long findTotal(final String hql, List<Object> values);
	
	List<?> findByPage(final String hql, final Integer offset, final Integer pageSize, List<Object> values);
	
	int bulkUpdate(String queryString, Object... values);
	
	@SuppressWarnings("rawtypes")
	Object load(Class entityClass, Serializable id);
	
}
