<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>商品查询</title>
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
  	<f:form action="${pageContext.request.contextPath}/mysite/queryGoods/${command.wgsWcsId}" onsubmit="return false;">
  	<f:hidden path="wgsId"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="30%">当前分类:</td><td>${fenlei.wgfName}</td>
    	</tr>
    </table>
    <input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/mysite/toAddGoods/${fenlei.wgfId}';"/>
    <input type="button" value="删除" onclick="delChk('wgsIds', '${pageContext.request.contextPath}/mysite/delGoods/${fenlei.wgfId}');"/>
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"><input type="checkbox" onclick="chkall(this.checked, 'wgsIds');"/></th>
    		<th width="16%">商品名称</th>
    		<th width="8%">商品分类</th>
    		<th width="4%">标准销售价</th>
    		<th width="4%">零售价</th>
    		<th width="4%">库存量</th>
    		<th width="3%">状态</th>
    		<th width="4%">录入人</th>
    		<th width="5%">录入时间</th>
    		<th width="8%">操作</th>
    	</tr>
    	<c:forEach var="goods" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wgsIds" value="${goods.WGS_ID}" /></td>
	    		<td>${goods.WGS_NAME}</td>
	    		<td>${goods.WGF_NAME}</td>
	    		<td>${goods.WGS_BZ_PRICE}</td>
	    		<td>${goods.WGS_LS_PRICE}</td>
	    		<td>${goods.WGS_KUCUN}</td>
	    		<td>
	    		<c:if test="${goods.WGS_STATUS==1000}">可用</c:if>
	    		<c:if test="${goods.WGS_STATUS==2000}">不可用</c:if>
	    		</td>
	    		<td>${goods.WGS_REGISTOR}</td>
	    		<td><fmt:formatDate value="${goods.WGS_REGISTDATE}" type="date"/></td>
	    		<td>
	    			<a href="javascript:toUpd('wgsId', ${goods.WGS_ID}, '${pageContext.request.contextPath}/mysite/toUpdGoods')">修改</a>
	    			<a href="javascript:toUpd('wgsId', ${goods.WGS_ID}, '${pageContext.request.contextPath}/mysite/toInGoods')">入库</a>
	    			<a href="javascript:toUpd('wgsId', ${goods.WGS_ID}, '${pageContext.request.contextPath}/mysite/toOutGoods')">出库</a>
	    			<a href="javascript:toUpd('wgsId', ${goods.WGS_ID}, '${pageContext.request.contextPath}/mysite/toBadGoods')">坏账</a>
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
