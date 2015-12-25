<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>管理员查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/admin.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var alertMsg = "${alertMsg}";
	$(document).ready(function(){
		if( alertMsg.length>0)
		{
			alert(alertMsg);
		}
	});
	</script>
  </head>
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/admin/queryAdmin" onsubmit="return false;">
  	<f:hidden path="wadId"/>
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/admin/toAddAdmin';"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%">编号</th>
    		<th width="8%">用户名</th>
    		<th width="3%">姓名</th>
    		<th width="8%">性别</th>
    		<th width="5%">录入人</th>
    		<th width="5%">录入时间</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="admin" items="${pageResult.resultList}">
	    	<tr>
	    		<td>${admin.WAD_ID}</td>
	    		<td>${admin.WAD_USERNAME}</td>
	    		<td>${admin.WAD_NAME}</td>
	    		<td>${admin.WAD_SEX}</td>
	    		<td>${admin.WAD_REGISTOR}</td>
	    		<td><fmt:formatDate value="${admin.WAD_REGISTDATE}" type="date"/></td>
	    		<td>
	    			<a href="javascript:toUpd('wadId', ${admin.WAD_ID}, '${pageContext.request.contextPath}/admin/toUpdAdmin')">修改</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
    <jsp:include page="/view/sys/page.jsp"></jsp:include>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
