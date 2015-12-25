package com.wxcrm.util;

import java.util.List;
import java.util.Map;

public class Page 
{
	private static final int PAGE_SIZE = 10;
	
	// 当前页
	private int currentPage;
	
	// 上一页
	private int previousPage;
	
	// 下一页
	private int nextPage;
	
	// 总页数
	private int totalPages;
	
	// 总行数
	private int totalRows;
	
	// 每页显示的行数
	private int pageSize;
	
	// 分页截取的行索引
	private int pageIndex;
	
	// 读取的最大条数
	private int maxRows;
	
	// 执行的sql
	private String sql;
	
	private String sqlCnt;
	
	// 占位符参数值
	private Object[] queryPara;
	
	// 返回的结果集
	private List<Map<String, Object>> resultList;

	public Page(String sql, String sqlCnt, 
				String currentPage, String pageSize, 
				Object[] queryPara) 
	{
		this.sql = sql;
		this.sqlCnt = sqlCnt;
		this.queryPara = queryPara;
		this.currentPage = (currentPage == null || currentPage.length() == 0 || 
				Integer.parseInt(currentPage) == 0) ? 1 : Integer.parseInt(currentPage);
		this.pageSize = (pageSize == null || pageSize.length() == 0 ||
				Integer.parseInt(pageSize) == 0) ? PAGE_SIZE : Integer.parseInt(pageSize);
	}
	
	public Page(String sql, String sqlCnt, String currentPage, String pageSize) 
	{
		this(sql, sqlCnt, currentPage, pageSize, null);
	}

	public Page() 
	{
		
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		if( this.totalRows > 0 )
		{
			this.totalPages = (this.totalRows % this.pageSize == 0 ? 
					(this.totalRows / this.pageSize) : (this.totalRows / this.pageSize + 1));
			this.currentPage = this.currentPage > this.totalPages ? this.totalPages : this.currentPage;
			this.previousPage = this.currentPage - 1;
			this.nextPage = this.currentPage + 1;
			this.pageIndex = (this.currentPage - 1) * this.pageSize;
			this.maxRows = this.currentPage * this.pageSize;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Map<String, Object>> getResultList() {
		return resultList;
	}

	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public Object[] getQueryPara() {
		return queryPara;
	}

	public void setQueryPara(Object[] queryPara) {
		this.queryPara = queryPara;
	}

	public String getSqlCnt() {
		return sqlCnt;
	}

	public void setSqlCnt(String sqlCnt) {
		this.sqlCnt = sqlCnt;
	}
}
