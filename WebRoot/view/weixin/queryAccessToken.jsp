<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>accessToken查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<f:form action="${pageContext.request.contextPath}/weixin/queryAccessToken" onsubmit="return false;">
  	<div><h1>当前可用AccessToken:<span style="color:red;">${accessToken}</span></h1></div>
  	<div><h1>过期时间:<span style="color:red;">${accessTokenExpire}</span></h1></div>
  	<div><input type="button" value="手动获取AccessToken" onclick="javascript:getAccessToken();"/></div>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
  	<tr> 
  		<td style="width:10%">所属应用:</td>
  		<td style="width:50%">
  			<f:select path="watWecId_Q" onchange="javascript:querySubmit();">
  				<c:forEach items="${watApp}" var="app">
  					<f:option value="${app.wecId}">${app.wecAppName}</f:option>
  				</c:forEach>
  			</f:select>
  		</td>
  		<td style="width:40%">
  			<input type="button" value="查询" onclick="querySubmit();"/>
  		</td>
  	</tr>
  	</table>
  	
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="2%">ID</th>
    		<th width="8%">应用名</th>
    		<th width="57%">AccessToken</th>
    		<th width="13%">过期时间</th>
    		<th width="13%">创建时间</th>
    		<th width="5%">状态</th>
    	</tr>
    	<c:forEach var="accessToken" items="${pageResult.resultList}">
    	<tr>
			<td>${accessToken.WAT_ID}</td>
			<td>${accessToken.WEC_APP_NAME}</td>
			<td>${accessToken.WAT_TOKEN}</td>
    		<td>${accessToken.WAT_EXPIRES_IN}</td>
    		<td>${accessToken.WAT_CREAT_TIME}</td>
    		<td>
    		<c:if test="${accessToken.WAT_STATUS==1}">可用</c:if>
    		<c:if test="${accessToken.WAT_STATUS==0}">不可用</c:if>
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
