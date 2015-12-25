package com.wxcrm.common.dao;

import java.io.Serializable;
import java.util.List;

public interface IHibernateDao 
{
	public Serializable add(Object entity);
	
	public void delete(Object entity);
	
	public void update(Object entity);
	
	public int bulkUpdate(String hql);
	
	public int bulkUpdate(String hql, Object[] args);
	
	public <T> T get(Class<T> entityClass, Serializable id);
	
	public void flush();
	
	public void clear();
	
	public void evict(Object entity);
	
	public <T> List<T> query(String hql);
	
	public <T> List<T> query(String hql, Object[] args);
	
	public <T> List<T> query(String hql, int index, int max);
	
	public <T> List<T> query(final String hql, final Object[] args, final int index, final int max);

}