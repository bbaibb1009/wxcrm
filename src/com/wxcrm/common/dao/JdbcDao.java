package com.wxcrm.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.wxcrm.util.Page;



@Repository
public class JdbcDao implements IJdbcDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int add(String sql)
	{
		return add(sql, null);
	}
	
	public int add(String sql, Object[] args)
	{
		return jdbcTemplate.update(sql, args);
	}
	
	public int delete(String sql)
	{
		return delete(sql, null);
	}
	
	public int delete(String sql, Object[] args)
	{
		return jdbcTemplate.update(sql, args);
	}
	
	public int update(String sql)
	{
		return update(sql, null);
	}
	
	public int update(String sql, Object[] args)
	{
		return jdbcTemplate.update(sql, args);
	}
	
	public int[] batchUpdate(String[] sql)
	{
		return jdbcTemplate.batchUpdate(sql);
	}
	
	public int[] batchUpdate(String sql, List<Object[]> batchArgs)
	{
		return jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	
	public int queryForInt(String sql)
	{
		return queryForInt(sql, null);
	}
	
	public int queryForInt(String sql, Object[] args)
	{
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}
	
	public long queryForLong(String sql)
	{
		return queryForLong(sql, null);
	}
	
	public long queryForLong(String sql, Object[] args)
	{
		return jdbcTemplate.queryForObject(sql, args, Long.class);
	}
	
	public float queryForFloat(String sql)
	{
		return queryForFloat(sql, null);
	}
	
	public float queryForFloat(String sql, Object[] args)
	{
		return jdbcTemplate.queryForObject(sql, args, Float.class);
	}
	
	public double queryForDouble(String sql)
	{
		return queryForDouble(sql, null);
	}
	
	public double queryForDouble(String sql, Object[] args)
	{
		return jdbcTemplate.queryForObject(sql, args, Double.class);
	}
	
	public String queryForString(String sql)
	{
		return queryForString(sql, null);
	}
	
	public String queryForString(String sql, Object[] args)
	{
		return jdbcTemplate.queryForObject(sql, args, String.class);
	}
	
	public Map<String, Object> queryForMap(String sql)
	{
		return queryForMap(sql, null);
	}
	
	public Map<String, Object> queryForMap(String sql, Object[] args)
	{
		return jdbcTemplate.queryForMap(sql, args);
	}
	
	public List<Map<String, Object>> queryForList(String sql)
	{
		return queryForList(sql, null);
	}
	
	public List<Map<String, Object>> queryForList(String sql, Object[] args)
	{
		return jdbcTemplate.queryForList(sql, args);
	}
	
	public <T> T queryForObject(String sql, Class<T> requiredType)
	{
		return queryForObject(sql, null, requiredType);
	}
	
	public <T> T queryForObject(String sql, Object[] args, Class<T> requiredType)
	{
		return jdbcTemplate.queryForObject(sql, args, requiredType);
	}
	
	public void queryForPage(final Page page)
	{
		page.setTotalRows(queryForInt(page.getSqlCnt(), page.getQueryPara()));
		
		List<Map<String, Object>> resultList = 
		jdbcTemplate.execute(new MyPreparedStatementCreator(page.getSql()), 
			new PreparedStatementCallback<List<Map<String, Object>>>()
			{
				public List<Map<String, Object>> doInPreparedStatement(PreparedStatement ps) throws SQLException
				{
					ArgumentPreparedStatementSetter pss = new ArgumentPreparedStatementSetter(page.getQueryPara());
					ResultSet rs = null;
					try {
						pss.setValues(ps);
						ps.setMaxRows(page.getMaxRows());
						rs = ps.executeQuery();
						ResultSet rsToUse = rs;
						if (jdbcTemplate.getNativeJdbcExtractor() != null) {
							rsToUse = jdbcTemplate.getNativeJdbcExtractor().getNativeResultSet(rs);
						}
						if( page.getPageIndex() > 0 )
						{
							rsToUse.absolute(page.getPageIndex());
						}
						
						return new RowMapperResultSetExtractor<Map<String, Object>>(new ColumnMapRowMapper())
							.extractData(rsToUse);
					}
					finally {
						JdbcUtils.closeResultSet(rs);
						pss.cleanupParameters();
					}
				}
			}
		);
		
		page.setResultList(resultList);
	}
	
	private static class MyPreparedStatementCreator implements PreparedStatementCreator, SqlProvider {

		private final String sql;

		public MyPreparedStatementCreator(String sql) {
			this.sql = sql;
		}

		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			return con.prepareStatement(this.sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}

		public String getSql() {
			return this.sql;
		}
	}
}
