<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>回复消息查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
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
			alert(alertMsg);
		}
	});
	</script>
  </head>
  <base target='_self'>
  <body>
  	<div class="wrapper">
  	<div class="content">

  	<f:form action="${pageContext.request.contextPath}/weixinmsg/queryDefaultMsg" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:10%;">回复类型:</td>
    		<td style="width:20%;text-align:left;">
	    		<f:select path="wmgReplyType_Q">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">-关键字回复-</f:option>
	    			<f:option value="2">-默认回复-</f:option>
	    			<f:option value="3">-关注回复-</f:option>
	    		</f:select>
    		</td>
    		<td style="width:10%;">消息类型:</td>
    		<td style="width:20%;text-align:left;">
	    		<f:select path="wmgMsgType_Q">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">图文信息</f:option>
	    			<f:option value="2">文字信息</f:option>
	    			<f:option value="3">图片信息</f:option>
	    			<f:option value="4">语音信息</f:option>
	    			<f:option value="5">视频信息</f:option>
	    			<f:option value="6">音乐信息</f:option>
	    		</f:select>
    		</td>
    		<td style="width:10%;">加密类型:</td>
    		<td style="width:20%;text-align:left;">
	    		<f:select path="wmgAesType_Q">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">加密</f:option>
	    			<f:option value="2">明文</f:option>
	    			<f:option value="3">兼容</f:option>
	    		</f:select>
    		</td>
    		<td style="width:10%;">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    </table>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="3%"></th>
    		<th width="85%">回复内容</th>
    		<th width="4%">回复类型</th>
    		<th width="4%">消息类型</th>
    		<th width="4%">加密类型</th>
    	</tr>
    	<c:forEach var="weiMsg" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wmgIds"  value="${weiMsg.WMG_ID}"/></td>
	    		<td>${weiMsg.WMG_DESC}</td>
	    		<td>${weiMsg.WMG_REPLY_TYPE}</td>
	    		<td>${weiMsg.WMG_MSG_TYPE}</td>
	    		<td>${weiMsg.WMG_AES_TYPE}</td>
	    	</tr>
    	</c:forEach>
    	
    </table>
    <jsp:include page="/view/sys/page.jsp"></jsp:include>
    
   	<div>
	    <input type="button" value="添加" onclick="javascript:addNewEnter();"/>
	    <input type="button" value="关闭" onclick="javascript:window.close();"/>
	</div>

    </f:form>
    </div>
    </div>
  </body>
</html>
