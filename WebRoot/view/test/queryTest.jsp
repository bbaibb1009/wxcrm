<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>测试查询</title>
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
  	<f:form action="${pageContext.request.contextPath}/test/queryTest" onsubmit="return false;">
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/test/toAddTest';"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%">编号</th>
    		<th width="8%">用户名</th>
    		<th width="3%">字段1</th>
    		<th width="8%">字段2</th>
    		<th width="5%">字段3</th>
    		<th width="5%">字段4</th>
    		<th width="5%">字段5</th>
    		<th width="5%">字段6</th>
    		<th width="5%">字段7</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="test" items="${pageResult.resultList}">
	    	<tr>
	    		<td>${test.WCS_ID}</td>
	    		<td>${test.WCS_USERNAME}</td>
	    		<td>${test.WCS_COLUMN1}</td>
	    		<td>${test.WCS_COLUMN2}</td>
	    		<td>${test.WCS_COLUMN3}</td>
	    		<td>${test.WCS_COLUMN4}</td>
	    		<td>${test.WCS_COLUMN5}</td>
	    		<td>${test.WCS_COLUMN6}</td>
	    		<td>${test.WCS_COLUMN7}</td>
	    		<td>
	    			<a href="javascript:showSendWeixin('${pageContext.request.contextPath}/weixin/toSendWeixin/${watcher.WAC_ID}');">发消息</a>
	    			<a href="${pageContext.request.contextPath}/weixin/updateSingleWatcher/${watcher.WAC_ID}">更新</a>
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
