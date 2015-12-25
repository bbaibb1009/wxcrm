package com.wxcrm.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDao implements IHibernateDao
{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Serializable add(Object entity)
	{
		return hibernateTemplate.save(entity);
	}
	
	public void delete(Object entity)
	{
		hibernateTemplate.delete(entity);
	}
	
	public void update(Object entity)
	{
		hibernateTemplate.update(entity);
	}
	
	public int bulkUpdate(String hql)
	{
		return hibernateTemplate.bulkUpdate(hql);
	}
	
	public int bulkUpdate(String hql, Object[] args)
	{
		return hibernateTemplate.bulkUpdate(hql, args);
	}
	
	public <T> T get(Class<T> entityClass, Serializable id)
	{
		return hibernateTemplate.get(entityClass, id);
	}
	
	public void flush()
	{
		hibernateTemplate.flush();
	}
	
	public void clear()
	{
		hibernateTemplate.clear();
	}
	
	public void evict(Object entity)
	{
		hibernateTemplate.evict(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String hql)
	{
		return hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String hql, Object[] args)
	{
		return hibernateTemplate.find(hql, args);
	}
	
	public <T> List<T> query(String hql, int index, int max)
	{
		return query(hql, null, index, max);
	}
	
	public <T> List<T> query(final String hql, final Object[] args, final int index, final int max)
	{
		return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(hql);
//				prepareQuery(queryObject); 源码执行了此方法
				if (args != null) {
					for (int i = 0; i < args.length; i++) {
						queryObject.setParameter(i, args[i]);
					}
				}
				queryObject.setFirstResult(index);
				queryObject.setMaxResults(max);
				
				return queryObject.list();
			}
		});
	}
}
