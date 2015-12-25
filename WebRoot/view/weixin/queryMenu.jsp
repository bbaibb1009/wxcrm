<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>菜单查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin_menu.js?v=${applicationScope.sysStartUpTime}"></script>
	
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
  	<f:form action="${pageContext.request.contextPath}/weixinmenu/queryMenu" onsubmit="return false;">
  		<f:hidden path="wmuId"/>
  		<input type="button" value="创建" onclick=" window.location.href = '${pageContext.request.contextPath}/weixinmenu/toAddMenu'; " />
  		<table cellpadding="0" cellspacing="0" border="1" width="100%">
    		<tr>
	    		<th width="5%">菜单ID</th>
	    		<th width="10%">APPID</th>
	    		<th width="45%">JSON串</th>
	    		<th width="15%">备注</th>
	    		<th width="5%">状态</th>
	    		<th width="5%">创建时间</th>
	    		<th width="5%">编辑</th>
	    	</tr>
	    	<c:forEach var="menu" items="${pageResult.resultList}">
	    	<tr>
				<td>${menu.WMU_ID}</td>
				<td>${menu.WMU_APP_ID}</td>
	    		<td>${menu.WMU_JSON}</td>
	    		<td>${menu.WMU_DESC}</td>
	    		<td>${menu.WMU_STATUS}</td>
	    		<td>${menu.WMU_REGISTOR_DATE}</td>
	    		<td><a href="javascript:toUpd('wmuId','${menu.WMU_ID}','${pageContext.request.contextPath}/weixinmenu/toUpdMenu')">修改</a></td>
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
