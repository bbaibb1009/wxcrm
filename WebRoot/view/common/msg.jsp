<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
  	<title>提示信息</title>
  
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var msgCode = "${msgCode}";
	if( msgCode == "1" )
	{
		sysTimeout();
	}
	else if( msgCode == "3" )
	{
		alert("您的IP地址受限制，请联系管理员");
		window.close();
	}
	
	$(document).ready(function(){
		if( msgCode == "2" )
		{
			$("#formHiddSpan").html('${formHidden}');
			alert("${alertMsg}");
			document.forms[0].submit();
		} 
	});
	</script>
  </head>
  
  <body>
  	<c:if test="${msgCode == 2}">
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	
  	
  	<span id="formHiddSpan"></span>
  	
  	
  	</div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
    </c:if>
  </body>
</html>
