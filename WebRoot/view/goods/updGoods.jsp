<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品修改</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/mysite/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/goods/js/goods.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<jsp:include page="/view/mysite/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/mysite/updGoods" onsubmit="return false;">
  	<f:hidden path="wgsId"/>
  	<f:hidden path="wgsWcsId"/>
  	<f:hidden path="wgsStatus"/>
  	<f:hidden path="wgsRegistor"/>
  	<f:hidden path="wgsRegistdate"/>
  	<f:hidden path="wgsKucun"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
 		<tr><th width="8%">所属商品分类</th><td>${fenlei.wgfName}<f:hidden path="wgsWgfId"/></td></tr>
    	<tr><th width="8%">名称</th><td><f:input path="wgsName"/></td></tr>
    	<tr><th width="8%">标准销售价</th><td><f:input path="wgsBzPrice"/></td></tr>
    	<tr><th width="8%">零售价</th><td><f:input path="wgsLsPrice"/></td></tr>
    	
   		<tr><td colspan=2><input type="button" value="修改" onclick="javascript:addGoods();"/></td></tr>
    </table>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
