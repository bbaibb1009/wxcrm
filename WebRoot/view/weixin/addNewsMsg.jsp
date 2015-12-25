<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>单条图文消息添加</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
			var alertMsg = '${alertMsg}';
			if(alertMsg!=null&&alertMsg.length > 0)
			{
				retValMyDialog(alertMsg);
			}
		</script>
	</head>
	<body>
		<div class="wrapper">
		  		<div class="content">
			  	<f:form action="${pageContext.request.contextPath}/weixinmsg/addNewsMsg" onsubmit="return false;">
			    <table cellpadding="0" cellspacing="0" border="1" width="100%" >
			    	<tr>
			    		<td width="10%"><font color="red">*</font>标题：</td><td style="text-align:left;"><f:input path="wamTitle"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>描述：</td>
			    		<td style="text-align:left;">
			    			<f:input path="wamDescription"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>PICURL：</td>
			    		<td style="text-align:left;">
			    			<f:input path="wamPicurl" />
			    		</td>
			    	</tr>
			    	<tr id="type_2" >
			    		<td width="10%">
			    			<font color="red"><b>*</b></font>URL:
			    		</td>
			    		<td width="75%" style="text-align:left;">
			    			<f:input path="wamUrl"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>备注：</td>
			    		<td style="text-align:left;"><f:input path="wamDesc"/></td>
			    	</tr>
			    	<tr>
			    		<td colspan="2"  style="text-align:left;">
			    			<input type="button" value="保存" onclick="javascript:addNewsMsgSubmit();"/>&nbsp;&nbsp;
			    			<input type="button" value="关闭" onclick="javascript:window.close();"/>
			    		</td>
			    	</tr>
 			    </table>
			    </f:form>
		    	</div>
		    </div>
	</body>
</html>