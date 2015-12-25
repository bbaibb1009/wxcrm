<%@ page language="java" contentType="text/html; charset=utf-8"%>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin.js?v=${applicationScope.sysStartUpTime}"></script>
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
  <base target='_self'>
  <body>
  	<div class="wrapper">
  	<div class="content">

  	<f:form action="${pageContext.request.contextPath}/weixin/queryWebSite" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:20%;">站点名称:</td>
    		<td style="width:70%;text-align:left;">
	    		<f:input path="wcsWebSiteName"/>
    		</td>
    		<td style="width:10%;">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    </table>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="1%"></th>
    		<th width="4%">站点名称</th>
    		<th width="2%">管理员</th>
    	</tr>
    	<c:forEach var="custEnter" items="${pageResult.resultList}">
	    	<tr>
	    		<td>
	    			<input type="checkbox" name="wcsIds"  value="${custEnter.WCS_ID}_${custEnter.WCS_WEBSITE_NAME}"/>
	    		</td>
	    		<td>${custEnter.WCS_WEBSITE_NAME}</td>
	    		<td>${custEnter.WCS_ADMIN_ID}</td>
	    	</tr>
    	</c:forEach>
    	
    </table>
    <jsp:include page="/view/sys/page.jsp"></jsp:include>
    
   	<div>
	    <input type="button" value="添加" onclick="javascript:addNewWebsite();"/>
	    <input type="button" value="关闭" onclick="javascript:window.close();"/>
	</div>

    </f:form>
    </div>
    </div>
  </body>
</html>
