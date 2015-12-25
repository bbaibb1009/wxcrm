package com.wxcrm.common.dao;

import java.util.List;
import java.util.Map;

import com.wxcrm.util.Page;




public interface IJdbcDao 
{
	public int add(String sql);
	
	public int add(String sql, Object[] args);
	
	public int delete(String sql);
	
	public int delete(String sql, Object[] args);
	
	public int update(String sql);
	
	public int update(String sql, Object[] args);
	
	public int[] batchUpdate(String[] sql);
	
	public int[] batchUpdate(String sql, List<Object[]> batchArgs);
	
	public int queryForInt(String sql);
	
	public int queryForInt(String sql, Object[] args);
	
	public long queryForLong(String sql);
	
	public long queryForLong(String sql, Object[] args);
	
	public float queryForFloat(String sql);
	
	public float queryForFloat(String sql, Object[] args);
	
	public double queryForDouble(String sql);
	
	public double queryForDouble(String sql, Object[] args);
	
	public String queryForString(String sql);
	
	public String queryForString(String sql, Object[] args);
	
	public Map<String, Object> queryForMap(String sql);
	
	public Map<String, Object> queryForMap(String sql, Object[] args);
	
	public List<Map<String, Object>> queryForList(String sql);
	
	public List<Map<String, Object>> queryForList(String sql, Object[] args);
	
	public <T> T queryForObject(String sql, Class<T> requiredType);
	
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType);
	
	public void queryForPage(final Page page);
}