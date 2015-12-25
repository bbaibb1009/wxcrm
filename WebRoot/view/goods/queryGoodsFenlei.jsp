<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品分类查询</title>
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
  	<f:form action="${pageContext.request.contextPath}/mysite/queryGoodsFenlei/${command.wgfWcsId}" onsubmit="return false;">
  	<f:hidden path="wgfId"/>
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/mysite/toAddGoodsFenlei/${command.wgfWcsId}';"/>
 	<input type="button" value="删除" onclick="delChk('wgfIds', '${pageContext.request.contextPath}/mysite/delGoodsFenlei/${command.wgfWcsId}');"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'wgfIds');"/></th>
    		<th width="8%">分类名称</th>
    		<th width="3%">状态</th>
    		<th width="3%">录入人</th>
    		<th width="3%">录入时间</th>
    		<th width="14%">操作</th>
    	</tr>
    	<c:forEach var="goodsfeilei" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wgfIds" value="${goodsfeilei.WGF_ID}" /></td>
	    		<td>${goodsfeilei.WGF_NAME}</td>
	    		<td>
	    		<c:if test="${goodsfeilei.WGF_STATUS==1000}">可用</c:if>
	    		<c:if test="${goodsfeilei.WGF_STATUS==2000}">不可用</c:if>
	    		</td>
	    		<td>${goodsfeilei.WGF_REGISTOR}</td>
	    		<td><fmt:formatDate value="${goodsfeilei.WGF_REGISTDATE}" type="date"/></td>
	    		<td>
	    			<a href="${pageContext.request.contextPath}/mysite/queryGoods/${goodsfeilei.WGF_ID}">查看所有商品</a>
	    			<a href="javascript:toUpd('wgfId', ${goodsfeilei.WGF_ID}, '${pageContext.request.contextPath}/mysite/toUpdGoodsFenlei')">修改</a>
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
