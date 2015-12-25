<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>发送消息</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
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
					retValMyDialog(alertMsg);
				}
			});
		</script>
	</head>
	<base target='_self'>
	<body>
		<f:form action="${pageContext.request.contextPath}/weixin/sendWeixin" onsubmit="return false;">
		<f:hidden path="wacOpenid" />
		<f:hidden path="wacWecId" />
		<table cellpadding="0" cellspacing="0" border="1" width="100%">
			<tr><td style="width:30%;text-align:right;">头像:</td><td><img src="${command.wacHeadImgUrl}" style="width:40px;height:40px;" /></td></tr>
			<tr><td style="text-align:right;">用户名:</td><td>${username}</td></tr>
			<tr><td style="text-align:right;">联系人:</td><td>${contractsname}</td></tr>
			<tr><td style="text-align:right;">手机号:</td><td>${mobile}</td></tr>
			<tr><td style="text-align:right;">企业:</td><td>${entername}</td></tr>
			<tr>
				<td style="text-align:right;">消息:</td>
				<td>
					<f:textarea path="msg" rows="5" cols="50"></f:textarea>
				</td>
			</tr>
			<tr><td colspan=2><input type="button" value="发送" onclick="javascript:sendMsg();"/></td></tr>
		</table>
		</f:form>
	</body>
</html>