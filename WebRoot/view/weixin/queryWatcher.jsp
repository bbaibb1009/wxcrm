<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>关注者查询</title>
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

  	<f:form action="${pageContext.request.contextPath}/weixin/queryWatcher" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:8%;">微信账号：</td>
    		<td style="text-align:left;">
    			<f:select path="wacWecId_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<c:forEach items="${watApp}" var="app">
	    			<f:option value="${app.wecId}">${app.wecAppName}</f:option>
	    			</c:forEach>
	    		</f:select>
	    	</td>
    		<td style="width:8%;">绑定状态:</td>
    		<td style="text-align:left;">
	    		<f:select path="wacBindStatus_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">已绑定</f:option>
	    			<f:option value="0">未绑定</f:option>
	    		</f:select>
    		</td>
    		<td style="width:8%;">手机:</td>
    		<td style="text-align:left;">
	    		<f:input path="wacMobile_Q"/>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:8%;">用户名:</td>
    		<td style="text-align:left;">
	    		<f:input path="wacUserName_Q"/>
    		</td>
    		<td style="width:8%;">联系人:</td>
    		<td style="text-align:left;">
	    		<f:input path="wacContract_Q"/>
    		</td>
    		<td style="width:8%;">企业名称:</td>
    		<td style="text-align:left;">
	    		<f:input path="wacEnterPrise_Q"/>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:8%;">昵称:</td>
    		<td style="text-align:left;">
	    		<f:input path="wacNickName_Q"/>
    		</td>
    		<td style="width:8%;">状态:</td>
    		<td style="text-align:left;">
	    		<f:select path="wacStatus_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">可用</f:option>
	    			<f:option value="0">不可用</f:option>
	    		</f:select>
    		</td>
    		<td style="width:8%;">关注状态：</td>
    		<td style="text-align:left;">
    			<f:select path="wacWatchStatus_Q" onchange="querySubmit()">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">已关注</f:option>
	    			<f:option value="0">未关注</f:option>
	    		</f:select>
	    	</td>
	    </tr>
	    <tr>
    		<td colspan="6" style="text-align:left;"> 
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    </table>
  	<div>当前应用总关注用户数:${command.sumTotal}人</div>
  	<c:if  test="${command.wacWecId_Q != null && command.wacWecId_Q != ''}">
  		<input type="button" value="在线更新关注用户的状态" onclick="javascript:updWatcherOnline('${pageContext.request.contextPath}/weixin/updateWatcherOnline');"/>
  	</c:if>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%">头像</th>
    		<th width="8%">昵称</th>
    		<th width="3%">性别</th>
    		<th width="8%">openid</th>
    		<th width="5%">最后关注时间</th>
    		<th width="5%">关注状态</th>
    		<th width="5%">账户状态</th>
    		<th width="5%">对应用户名</th>
    		<th width="5%">对应联系人</th>
    		<th width="5%">手机</th>
    		<th width="10%">对应企业</th>
    		<th width="4%">操作</th>
    	</tr>
    	<c:forEach var="watcher" items="${pageResult.resultList}">
	    	<tr>
	    		<td><img style="width:40px;height:40px;" src="${watcher.WAC_HEAD_IMG_URL}"/></td>
	    		<td>${watcher.WAC_NICK_NAME}</td>
	    		<td>
	    			<c:if test="${watcher.WAC_SEX==0}">不详</c:if>
	    			<c:if test="${watcher.WAC_SEX==1}">男</c:if>
	    			<c:if test="${watcher.WAC_SEX==2}">女</c:if>
	    		</td>
	    		
	    		<td>${watcher.WAC_OPENID}</td>
	    		<td><fmt:formatDate value="${watcher.WAC_SUBSCRIBE_TIME}" /></td>
	    		<td>
	    			<c:if test="${watcher.WAC_SUBSCRIBE==0}">取消关注</c:if>
	    			<c:if test="${watcher.WAC_SUBSCRIBE==1}">已关注</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${watcher.OLG_ID==0}">未绑定账户</c:if>
	    			<c:if test="${watcher.OLG_ID>0}">已绑定账户</c:if>
	    		</td>
	    		<td>${watcher.CUU_USERNAME}</td>
	    		<td>${watcher.CUC_NAME}</td>
	    		<td>${watcher.CUC_MOBILE}</td>
	    		<td>${watcher.CUE_NAME}</td>
	    		<td>
	    			<a href="javascript:showSendWeixin('${pageContext.request.contextPath}/weixin/toSendWeixin/${watcher.WAC_ID}');">发消息</a>
	    			<a href="${pageContext.request.contextPath}/weixin/updateSingleWatcher/${watcher.WAC_ID}">更新</a>
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
