<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>测试页面</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
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
  	<f:form action="${pageContext.request.contextPath}/weixinmsg/testLzWeiMsg" onsubmit="return false;">
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	
    	<tr>
    		<td style="width:8%;">appId:</td>  
    		<td style="text-align:left;">
    			<input name="Appid" id="Appid" value="wx699aebd2bc63a9fb"/> 
    			
    		</td>
    	</tr>
    	<tr>
    		<td style="width:8%;">FromUserName:</td>  
    		<td style="text-align:left;">
    			<input name="FromUserName" id="FromUserName" value="oGp7ls_iVQGuBChhVYCjOSQkW6L4"/>
    		</td>
    	</tr>
    	<tr>	
    		<td style="width:8%;">ToUserName:</td>  
    		<td style="text-align:left;">
    			<input name="ToUserName" id="ToUserName" value="gh_7ac5c03c2cbb"/>
    		</td>
    	</tr>
    	<tr>
    		<td style="width:8%;">MsgType:</td>
    		<td style="text-align:left;">
    			<select name="MsgType" id="MsgType">
    				<option value="text">文本</option>
    				<option value="image">图片</option>
    				<option value="link">链接</option>
    				<option value="location">地理位置</option>
    				<option value="voice">音频</option>
    				<option value="event">推送</option>
    			</select>
    		</td>
    	</tr>
    	<tr>	
    		<td style="width:8%;">Content:</td>	
    		<td style="text-align:left;">
    			<input name="Content" id="Content"/>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			${xml}
    		</td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<input type="button" value="提交" onclick="querySubmit()"/>
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
