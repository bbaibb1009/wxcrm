<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>站点查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/website.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<f:form action="${pageContext.request.contextPath}/website/queryWebsite" onsubmit="return false;">
  	<f:hidden path="wcsId"/>
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/website/toAddWebsite';"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%">编号</th>
    		<th width="8%">站点名</th>
    		<th width="8%">微信名称</th>
    		<th width="8%">商家类型</th>
    		<th width="8%">APP名称</th>
			
    		<th width="5%">所属管理员</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="test" items="${pageResult.resultList}">
	    	<tr>
	    		<td>${test.WCS_ID}</td>
	    		<td>${test.WCS_WEbSITE_NAME}</td>
	    		<td>${test.WCS_APP_NAME}</td>
	    		<td>
	    			<c:if test="${test.WCS_TYPE=='1'}">产品类</c:if>
	    			<c:if test="${test.WCS_TYPE=='2'}">服务类</c:if>
	    		</td>
	    		<td>${test.WCS_APP_ID}</td>
	    		<td>${test.WSA_NAME}</td>
	    		<td>
	    			<a href="javascript:toUpd('wcsId', ${test.WCS_ID}, '${pageContext.request.contextPath}/website/toUpdWebSite')">修改</a>
	    			<a href="${pageContext.request.contextPath}/weixin/queryWeixinEnterBySite/${test.WCS_ID}">微信</a>
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
