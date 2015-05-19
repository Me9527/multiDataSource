package com.cpst.framework.base;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO extends HibernateDaoSupport {
//	protected final Log logger = LogFactory.getLog(getClass());

	protected static final BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
	
	@SuppressWarnings("rawtypes")
	public List findByPage(final String hql, final int offset,final int pageSize) {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				return session.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
			}
		});

		return list;
	}

	@SuppressWarnings("rawtypes")
	public List findByFieldPage(final String hql, final List<Object> values, final int offset,final int pageSize) {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				Query queryObject = session.createQuery(hql);
				queryObject.setFirstResult(offset);
				queryObject.setMaxResults(pageSize);
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
