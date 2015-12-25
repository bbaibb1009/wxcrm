<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>企业公众号查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">

  	<f:form action="${pageContext.request.contextPath}/weixin/queryWeixinEnter" onsubmit="return false;">
  	<f:hidden path="wecId"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:8%;">应用类型：</td>
    		<td style="text-align:left;">
    			<f:select path="wecAppType_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">微信</f:option>
	    			<f:option value="2">微博</f:option>
	    		</f:select>
	    	</td>
    		<td style="width:8%;">消息加密方式:</td>
    		<td style="text-align:left;">
	    		<f:select path="wecAesType_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="0">明文</f:option>
	    			<f:option value="1">加密</f:option>
	    			<f:option value="2">兼容</f:option>
	    		</f:select>
    		</td>
    		<td style="width:8%;">客户类型:</td>
    		<td style="text-align:left;">
	    		<f:select path="wecCusType_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">内部应用</f:option>
	    			<f:option value="2">商用</f:option>
	    		</f:select>
    		</td>
    		<td style="width:8%;">账户类型:</td>
    		<td style="text-align:left;">
	    		<f:select path="wecAccountType_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">订阅号</f:option>
	    			<f:option value="2">服务号</f:option>
	    			<f:option value="3">企业号</f:option>
	    		</f:select>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:8%;">应用名称:</td>
    		<td style="text-align:left;">
	    		<f:input path="wecAppName_Q"/>
    		</td>
    		<td style="width:8%;">appId:</td>
    		<td style="text-align:left;">
	    		<f:input path="wecAppId_Q"/>
    		</td>
    		<td style="width:8%;">站点名称:</td>
    		<td style="text-align:left;">
	    		<f:input path="wecEnterName_Q"/>
    		</td>
    		<td colspan="2">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    	
    </table>
  	<input type="button" value="添加" onclick="window.location.href = '${pageContext.request.contextPath}/weixin/toAddWeiEnter';"/>
  	<input type="button" value="删除" onclick="delChk('wecIds','${pageContext.request.contextPath}/weixin/delWeiEnter');"/>
  	<input type="button" value="测试" onclick="window.location.href = '${pageContext.request.contextPath}/weixinmsg/toTestLzWeiMsg';"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="1%"></th>
    		<th width="3%">名称</th>
    		<th width="2%">AppId</th>
    		<th width="12%">回调URL</th>
    		<th width="2%">加密</th>
    		<th width="2%">应用</th>
    		<th width="2%">客户</th>
    		<th width="2%">账户</th>
    		<th width="8%">企业</th>
    		<th width="8%">操作</th>
    	</tr>
    	<c:forEach var="weiEnter" items="${pageResult.resultList}">
	    	<tr>
	    		<td>
	    			<input type="checkbox" name="wecIds" value="'${weiEnter.WEC_ID}'"/>
	    		</td>
	    		<td>${weiEnter.WEC_APP_NAME}</td>
	    		<td>${weiEnter.WEC_APP_ID}</td>
	    		<td>${weiEnter.WEC_REDERECT_URL}</td>
	    		<td>
	    			<c:if test="${weiEnter.WEC_AES_TYPE==0}">明文</c:if>
	    			<c:if test="${weiEnter.WEC_AES_TYPE==1}">加密</c:if>
	    			<c:if test="${weiEnter.WEC_AES_TYPE==2}">兼容</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${weiEnter.WEC_APP_TYPE==1}">微信</c:if>
	    			<c:if test="${weiEnter.WEC_APP_TYPE==2}">微博</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${weiEnter.WEC_CUS_TYPE==1}">内部</c:if>
	    			<c:if test="${weiEnter.WEC_CUS_TYPE==2}">商用</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${weiEnter.WEC_ACCOUNT_TYPE==1}">订阅号</c:if>
	    			<c:if test="${weiEnter.WEC_ACCOUNT_TYPE==2}">服务号</c:if>
	    			<c:if test="${weiEnter.WEC_ACCOUNT_TYPE==3}">企业号</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${weiEnter.WEC_CUS_TYPE==2}">${weiEnter.WCS_WEBSITE_NAME}</c:if>
	    			<c:if test="${weiEnter.WEC_CUS_TYPE==1}">内部</c:if>
	    		</td>
	    		<td>
	    			<a href="javascript:toUpd('wecId', '${weiEnter.WEC_ID}','${pageContext.request.contextPath}/weixin/toUpdWeiEnter')">修改</a>|
	    			<a href="${pageContext.request.contextPath}/weixin/queryWatcherByEnter/${weiEnter.WEC_ID}">关注者</a>|
	    			<a href="${pageContext.request.contextPath}/weixinmenu/toUpdWeiXinMenu/${weiEnter.WEC_ID}">菜单</a>|
	    			<a href="${pageContext.request.contextPath}/weixin/queryAccessToken/${weiEnter.WEC_ID}">accessToken</a>
	    			<c:if test="${weiEnter.WEC_CUS_TYPE==2}">
	    			|<a href="${pageContext.request.contextPath}/weixine/queryWeiMemberByApp/${weiEnter.WEC_ENTER_ID}">会员查询</a>|
	    			<a href="${pageContext.request.contextPath}/weixine/queryWeiEnterAdminByApp/${weiEnter.WEC_ENTER_ID}">管理员</a>|
	    			<a href="${pageContext.request.contextPath}/weixine/queryWeiEnterCustOrder/${weiEnter.WEC_ENTER_ID}">订单</a>
	    			</c:if>
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
