<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<table cellpadding="0" cellspacing="0" border="1" width="100%">
<c:choose>
	<c:when test="${pageResult == null || pageResult.totalRows == 0}">
	<tr><td>û����������������</td></tr>
	</c:when>
	
	<c:otherwise>
	<tr>
		<c:choose>
			<c:when test="${pageResult.currentPage == 1}">
			<td width="10%">��ҳ</td>
			<td width="10%">��һҳ</td>
			</c:when>
			
			<c:otherwise>
			<td width="10%"><a href="javascript:goPage(1);">��ҳ</a></td>
			<td width="10%"><a href="javascript:goPage(${pageResult.previousPage });">��һҳ</a></td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${pageResult.currentPage == pageResult.totalPages}">
			<td width="10%">��һҳ</td>
			<td width="10%">βҳ</td>
			</c:when>
			
			<c:otherwise>
			<td width="10%"><a href="javascript:goPage(${pageResult.nextPage });">��һҳ</a></td>
			<td width="10%"><a href="javascript:goPage(${pageResult.totalPages });">βҳ</a></td>
			</c:otherwise>
		</c:choose>
		
		<td width="60%">
		��&nbsp;<input type="text" id="currentPage" name="currentPage" value="${pageResult.currentPage }" size="3"/>&nbsp;ҳ��
		��&nbsp;${pageResult.totalPages }&nbsp;ҳ��
		ÿҳ��ʾ&nbsp;<input type="text" id="pageSize" name="pageSize" value="${pageResult.pageSize }" size="3"/>&nbsp;�����ݣ�
		��&nbsp;${pageResult.totalRows }&nbsp;������&nbsp;&nbsp;
		<a href="javascript:goPage(document.getElementById('currentPage').value);">ˢ��</a>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
</table>

