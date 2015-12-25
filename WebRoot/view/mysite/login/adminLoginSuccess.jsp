<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
	<title>微信CRM管理后台</title>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/mysite/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	</script>
  </head>
  <body>
	<div class="wrapper">
		<jsp:include page="/view/mysite/login/menuFrame.jsp"></jsp:include>
		<div class="admin_right" id="adminRight">
			<jsp:include page="/view/mysite/login/adminIndex.jsp"></jsp:include>
		</div>
		<jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>