<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>会员查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/mysite/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
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
  	<jsp:include page="/view/mysite/login/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/mysite/queryMyMember" onsubmit="return false;">
  	<f:hidden path="wmbId"/>
  	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/website/toAddWebsite';"/>
 	<input type="button" value="删除" onclick="window.location.href='${pageContext.request.contextPath}/website/toAddWebsite';"/>
 	
 	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%">编号</th>
    		<th width="8%">微信ID</th>
    		<th width="3%">卡号</th>
    		<th width="8%">openid</th>
    		<th width="5%">会员类型</th>
    		<th width="4%">姓名</th>
    		
    		<th width="8%">手机号码</th>
    		<th width="3%">状态</th>
    		<th width="8%">备注</th>
    		<th width="5%">录入时间</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="member" items="${pageResult.resultList}">
	    	<tr>
	    		<td>${member.WMB_ID}</td>
	    		<td>${member.WMB_WEC_ID}</td>
	    		<td>${member.WMB_CARD_ID}</td>
	    		<td>${member.WMB_OPENID}</td>
	    		<td>${member.WMB_TYPE}</td>
	    		<td>${member.WMB_NAME}</td>
	    		<td>${member.WMB_MOBULE}</td>
	    		<td>${member.WMB_STATUS}</td>
	    		<td>${member.WMB_DESC}</td>
	    		<td>${member.WMB_REGISTDATE}</td>
	    		<td>
	    			<a href="javascript:toUpd('wcsId', ${member.WMB_ID}, '${pageContext.request.contextPath}/website/')">修改</a>
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
