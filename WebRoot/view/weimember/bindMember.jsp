<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>会员绑定</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/weimember/style.css?v=${applicationScope.sysStartUpTime}">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/weimember/css/example.css?v=${applicationScope.sysStartUpTime}">
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/json2.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weimember/js/weimember.js?v=${applicationScope.sysStartUpTime}"></script>

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
  	<h1>
  		<a href="javascript:goSubmit('${pageContext.request.contextPath}/weixine/toIntroEnter/');">
  		<img src="${pageContext.request.contextPath}/view/weixine/images/logo1.png" alt=""/>
  		</a>
  	</h1>
	<div class="wrap">
		<f:form action="${pageContext.request.contextPath}/weimember/bindMember" class="dan-form" onsubmit="return false;">
			<p><strong>内部绑定</strong></p>
		  	<p class="username">
		  		<f:hidden path="wmbId"/>
		  		<f:hidden path="wWmbName"/>
		    	wmbid:${command.wmbId}
		  	</p>
		  	<p class="phone">
		    	openid:${command.wmbOpenid}
		  	</p>
		  	<p class="submit-btn">
		    	<input type="button" value="点击获取会员卡" onclick="javascript:bindMember();"/>
		  	</p>
		 </div>
   		</f:form>
	   	<footer>
		<p>&copy; 微信CRM</p>
		</footer>
  </body>
</html>
