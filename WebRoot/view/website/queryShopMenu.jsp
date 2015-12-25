<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商铺菜单查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" charset="UTF-8"  src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/view/website/js/menu.js?v=${applicationScope.sysStartUpTime}"></script>
	
	<script type="text/javascript" charset="UTF-8">
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
  	<f:form action="${pageContext.request.contextPath}/shopmenu/queryShopMenu" onsubmit="return false;">
  	<f:hidden path="wsmId"/>
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/shopmenu/toAddShopMenu';"/>
  	<input type="button" value="删除" onclick="delChk('menuIds', '${pageContext.request.contextPath}/shopmenu/delShopMenu');"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'menuIds');"/></th>
    		<th width="8%">菜单名称</th>
    		<th width="3%">菜单URL</th>
    		<th width="8%">菜单级别</th>
    		<th width="5%">菜单顺序</th>
    		<th width="5%">上级菜单</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="menu" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="menuIds" value="${menu.WME_ID}"/></td>
	    		<td>${menu.WSM_NAME}</td>
	    		<td>${menu.WSM_URL}</td>
	    		<td>${menu.WSM_LEVEL}级菜单</td>
	    		<td>${menu.WSM_ORDER}</td>
	    		<td>${menu.parent_menu}</td>
	    		<td>
	    			<a href="javascript:toUpd('wsmId', ${menu.WSM_ID}, '${pageContext.request.contextPath}/shopmenu/toUpdShopMenu')">修改</a>
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
