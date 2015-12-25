package com.wxcrm.util;

import java.util.List;
import java.util.Map;

public class Page 
{
	private static final int PAGE_SIZE = 10;
	
	// ��ǰҳ
	private int currentPage;
	
	// ��һҳ
	private int previousPage;
	
	// ��һҳ
	private int nextPage;
	
	// ��ҳ��
	private int totalPages;
	
	// ������
	private int totalRows;
	
	// ÿҳ��ʾ������
	private int pageSize;
	
	// ��ҳ��ȡ��������
	private int pageIndex;
	
	// ��ȡ���������
	private int maxRows;
	
	// ִ�е�sql
	private String sql;
	
	private String sqlCnt;
	
	// ռλ������ֵ
	private Object[] queryPara;
	
	// ���صĽ����
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
