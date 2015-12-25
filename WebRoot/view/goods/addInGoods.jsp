<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品入库</title>
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
  	<f:form action="${pageContext.request.contextPath}/mysite/addInGoods" onsubmit="return false;">
  	<f:hidden path="wgiWgsId"/>
  	<f:hidden path="wgiWcsId"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
 		<tr><th width="8%">入库商品</th><td>${goods.wgsName}</td></tr>
    	<tr><th width="8%">入库时间</th><td><f:input path="wgiInTime" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" cssClass="Wdate" cssStyle="cursor: pointer;"/></td></tr>
    	<tr><th width="8%">入库量</th><td><f:input path="wgiInNum"/></td></tr>
    	<tr><th width="8%">进价</th><td><f:input path="wgiInPrice"/></td></tr>
    	<tr><th width="8%">存放位置</th><td><f:textarea path="wgiLoc"/></td></tr>
    	<tr><th width="8%">备注</th><td><f:textarea path="wgiDesc"/></td></tr>
   		<tr><td colspan=2><input type="button" value="添加" onclick="javascript:addInGoods();"/></td></tr>
    </table>
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
