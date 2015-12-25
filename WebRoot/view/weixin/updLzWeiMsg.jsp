<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>微信公众号添加</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
		</script>
	</head>
	<body>
		<div class="wrapper">
		  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
		  	<div class="admin_right" id="adminRight">
		  		<div class="content">
			  	<f:form action="${pageContext.request.contextPath}/weixinmsg/updLzWeiMsg" onsubmit="return false;">
			  	<f:hidden path="wmgId"/>
			  	<f:hidden path="wmgStatus"/>
				<f:hidden path="wmgRegistor"/>
				<f:hidden path="wmgRegistdate"/>
				<f:hidden path="currentPage"/>
				<f:hidden path="pageSize"/>
				<f:hidden path="wmgReplyType_Q"/>
				<f:hidden path="wmgMsgType_Q"/>
				<f:hidden path="wmgAesType_Q"/>
			    <table cellpadding="0" cellspacing="0" border="1" width="100%" >
			    	<tr>
			    		<td><font color="red">*</font>加密类型：</td>
			    		<td style="text-align:left;">
			    			<f:select path="wmgAesType">
			    				<f:option value="">-请选择-</f:option>
				    			<f:option value="1">加密</f:option>
				    			<f:option value="2">明文</f:option>
				    			<f:option value="3">兼容</f:option>
			    			</f:select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>回复类型：</td>
			    		<td style="text-align:left;">
			    			<f:select path="wmgReplyType">
			    				<f:option value="">-请选择-</f:option>
	    						<f:option value="1">关键字回复</f:option>
				    			<f:option value="2">默认回复</f:option>
				    			<f:option value="3">关注回复</f:option>
			    			</f:select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>消息类型：</td>
			    		<td style="text-align:left;">
			    			<f:select path="wmgMsgType" onchange="javascript:selMsgModel(this);">
			    				<f:option value="">-请选择-</f:option>
				    			<f:option value="1">图文信息</f:option>
				    			<f:option value="2">文字信息</f:option>
				    			<f:option value="3">图片信息</f:option>
				    			<f:option value="4">语音信息</f:option>
				    			<f:option value="5">视频信息</f:option>
				    			<f:option value="6">音乐信息</f:option>
			    			</f:select>
			    		</td>
			    	</tr>
			    	<tr id="type_1" >
			    		<td width="10%">
			    			<font color="red"><b>*</b></font>图文列表：
			    		</td>
			    		<td width="75%" style="text-align:left;">
			    			<table id="tbTuwen"  cellpadding="0" cellspacing="0" border="1" width="100%" >
			    				<tr>
			    					<th>图文消息名称</th><th>图文消息描述</th><th>图片链接</th><th>点击图文消息跳转链接</th>
			    				</tr>
			    			</table>
			    		</td>
			    	</tr>
			    	<tr id="type_2" style="display:none;">
			    		<td width="10%">
			    			<font color="red"><b>*</b></font>回复内容:
			    		</td>
			    		<td width="85%" style="text-align:left;">
			    			<f:textarea path="wmgContent"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>备注：</td>
			    		<td style="text-align:left;"><f:input path="wmgDesc"/></td>
			    	</tr>
			    	<tr>
			    		<td colspan="2"  style="text-align:left;">
			    			<input type="button" value="保存" onclick="javascript:addLzWeiMsgSubmit();"/>&nbsp;&nbsp;
			    			<input type="button" value="返回" onclick="history.back();"/>
			    		</td>
			    	</tr>
 			    </table>
			    </f:form>
		    	</div>
		    </div>
		    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
		    </div>
	</body>
</html>