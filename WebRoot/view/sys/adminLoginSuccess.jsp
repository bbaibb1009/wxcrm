<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
	<title>隆众石化网管理后台</title>
	
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	</script>
  </head>
  <body>
	<div class="wrapper">
		<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
		<div class="admin_right" id="adminRight">
			<!--<h1>欢迎访问 隆众石化网管理后台</h1>-->
			<jsp:include page="/view/sys/adminIndex.jsp"></jsp:include>
		</div>
		<jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>