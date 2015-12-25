<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>角色权限</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/zTree/zTreeStyle.css?v=${applicationScope.sysStartUpTime}">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/menu.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	$(document).ready(function(){
		loadMenuTree("${pageContext.request.contextPath}/menu/getMenuTreeForRole/1/${roleId}");
	});
	</script>
  </head>
  
  <body>
  		<ul id="treeDemo" class="ztree"></ul>
  </body>
</html>
