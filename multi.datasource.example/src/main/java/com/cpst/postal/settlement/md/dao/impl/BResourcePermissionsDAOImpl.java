package com.cpst.postal.settlement.md.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cpst.framework.base.BaseDAO;
import com.cpst.postal.settlement.user.dao.IBResourcePermissionsDAO;

public class BResourcePermissionsDAOImpl extends BaseDAO implements IBResourcePermissionsDAO {

	public void addData(Object data) {

		getHibernateTemplate().save(data);
	}

	public void update(Object data) {
		getHibernateTemplate().update(data);
	}

	public void delById(Class<?> clazz, Serializable id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(clazz, id));
	}

	public List<?> find(String queryString, Object... values) {
		return getHibernateTemplate().find(queryString, values);
	}

	public Object findBySeqId(Class<?> clazz, Long seqId) {
		return getHibernateTemplate().get(clazz, seqId);
	}

	public List<?> findByPage(final String hql, final Integer offset,
			final Integer pageSize, List<Object> values) {
		if(values.size() > 0)
			return super.findByFieldPage(hql, values, offset, pageSize);
		else
			return super.findByPage(hql, offset, pageSize);
	}

	@SuppressWarnings("unchecked")
	public Long findTotal(final String hql, List<Object> values) {
		List<Long> rs = null;
		if(values.size() > 0)
			rs = (List<Long>) getHibernateTemplate().find(hql, values.toArray());
		else
			rs = (List<Long>) getHibernateTemplate().find(hql);
		return rs.get(0);
	}

	@SuppressWarnings("rawtypes")
	public List<?> findByFieldAll(final String hql, final List<Object> values) {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query queryObject = session.createQuery(hql);
				if (values.size() > 0) {
					for (int i = 0; i < values.size(); i++) {
						queryObject.setParameter(i, values.get(i));
					}
				}
				return queryObject.list();
			}
		});
		return list;
	}
}
