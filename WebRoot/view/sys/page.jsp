<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<table cellpadding="0" cellspacing="0" border="1" width="100%">
<c:choose>
	<c:when test="${pageResult == null || pageResult.totalRows == 0}">
	<tr><td>没有满足条件的数据</td></tr>
	</c:when>
	
	<c:otherwise>
	<tr>
		<c:choose>
			<c:when test="${pageResult.currentPage == 1}">
			<td width="10%">首页</td>
			<td width="10%">上一页</td>
			</c:when>
			
			<c:otherwise>
			<td width="10%"><a href="javascript:goPage(1);">首页</a></td>
			<td width="10%"><a href="javascript:goPage(${pageResult.previousPage });">上一页</a></td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${pageResult.currentPage == pageResult.totalPages}">
			<td width="10%">下一页</td>
			<td width="10%">尾页</td>
			</c:when>
			
			<c:otherwise>
			<td width="10%"><a href="javascript:goPage(${pageResult.nextPage });">下一页</a></td>
			<td width="10%"><a href="javascript:goPage(${pageResult.totalPages });">尾页</a></td>
			</c:otherwise>
		</c:choose>
		
		<td width="60%">
		第&nbsp;<input type="text" id="currentPage" name="currentPage" value="${pageResult.currentPage }" size="3"/>&nbsp;页，
		共&nbsp;${pageResult.totalPages }&nbsp;页，
		每页显示&nbsp;<input type="text" id="pageSize" name="pageSize" value="${pageResult.pageSize }" size="3"/>&nbsp;条数据，
		共&nbsp;${pageResult.totalRows }&nbsp;条数据&nbsp;&nbsp;
		<a href="javascript:goPage(document.getElementById('currentPage').value);">刷新</a>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
</table>

