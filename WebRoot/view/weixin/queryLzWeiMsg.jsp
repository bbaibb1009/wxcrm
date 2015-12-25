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
  	<f:form action="${pageContext.request.contextPath}/weixinmsg/queryLzWeiMsg" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:10%;">回复类型:</td>
    		<td style="width:20%;text-align:left;">
	    		<f:select path="wmgReplyType_Q">
	    			<f:option value="">-请选择-</f:option>
	    			<f:option value="1">关键字回复</f:option>
	    			<f:option value="2">默认回复</f:option>
	    			<f:option value="3">关注回复</f:option>
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
    <input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/weixinmsg/toAddLzWeiMsg';"/>
  	<input type="button" value="删除" onclick="delChk('wmgIds','${pageContext.request.contextPath}/weixinmsg/delWeiMsg');"/>
  	<f:hidden path="wmgId"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="2%"></th>
    		<th width="30%">回复内容</th>
    		<th width="4%">回复类型</th>
    		<th width="4%">消息类型</th>
    		<th width="4%">加密类型</th>
    		<th width="4%">状态</th>
    		<th width="8%">备注</th>
    		<th width="5%">操作</th>
    	</tr>
    	<c:forEach var="msg" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="wmgIds"  value="${msg.WMG_ID}"/></td>
	    		<td>${msg.WMG_CONTENT}</td>
	    		<td>
	    			<c:if test="${msg.WMG_REPLY_TYPE==1}">关键字回复</c:if>
	    			<c:if test="${msg.WMG_REPLY_TYPE==2}">默认回复</c:if>
	    			<c:if test="${msg.WMG_REPLY_TYPE==3}">关注回复</c:if>
	    		</td>
	    		<td>
		    		<c:if test="${msg.WMG_MSG_TYPE==1}">图文信息</c:if>
		    		<c:if test="${msg.WMG_MSG_TYPE==2}">文字信息</c:if>
		    		<c:if test="${msg.WMG_MSG_TYPE==3}">图片信息</c:if>
		    		<c:if test="${msg.WMG_MSG_TYPE==4}">语音信息</c:if>
		    		<c:if test="${msg.WMG_MSG_TYPE==5}">视频信息</c:if>
		    		<c:if test="${msg.WMG_MSG_TYPE==6}">音乐信息</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${msg.WMG_AES_TYPE==1}">加密</c:if>
		    		<c:if test="${msg.WMG_AES_TYPE==2}">明文</c:if>
		    		<c:if test="${msg.WMG_AES_TYPE==3}">兼容</c:if>
	    		</td>
	    		<td>
	    			<c:if test="${msg.WMG_STATUS==1000}">可用</c:if>
		    		<c:if test="${msg.WMG_STATUS==2000}">不可用</c:if>
	    		</td>
	    		<td>${msg.WMG_DESC}</td>
	    		<td>
	    			<a href="javascript:void(0);" onclick="javascript:toUpd('wmgId',${msg.WMG_ID},'${pageContext.request.contextPath}/weixinmsg/toUpdLzWeiMsg')">修改</a>
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
