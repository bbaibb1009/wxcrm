<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>站点添加</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/My97DatePicker/WdatePicker.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/website.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/adminName.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
			$(document).ready(function(){
				getAdminNameList("adminName");
			});
			function selAdminName(suggestion)
			{
				document.getElementById("wcsAdminId").value = suggestion.data;
				document.getElementById("adminName").value = suggestion.value;
			}
		</script>
	</head>
	<body>
		<div class="wrapper">
		  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
		  	<div class="admin_right" id="adminRight">
		  		<div class="content">
			  	<f:form action="${pageContext.request.contextPath}/website/addWebsite" onsubmit="return false;">
			    <table cellpadding="0" cellspacing="0" border="1" width="100%" >
			    	<tr>
			    		<td width="10%" ><font color="red"><b>*</b></font>站点名称：</td>
			    		<td width="75%" style="text-align:left;"><f:input path="wcsWebSiteName" maxlength="40" cssStyle="width:200px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>应用名称</td>
			    		<td style="text-align:left;">
			    		 	<f:input path="wcsAppName"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>appID</td>
			    		<td style="text-align:left;"><f:input path="wcsAppId" cssStyle="width:80px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>商家类型</td>
			    		<td style="text-align:left;"><f:select path="wcsType" cssStyle="width:80px;">
			    		<f:option value="">请选择</f:option>
			    		<f:option value="1">产品类</f:option>
			    		<f:option value="2">服务类</f:option>
			    		</f:select></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>AppSecret</td>
			    		<td style="text-align:left;"><f:input path="wcsAppSecret" cssStyle="width:80px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>回调地址:</td>
			    		<td style="text-align:left;"><f:input path="wcsRedirectUrl" /></td>
			    	</tr>
			    	<tr>
			    		<td>token:</td>
			    		<td style="text-align:left;"><f:input path="wcsToken" /></td>
			    	</tr>
			    	<tr>
			    		<td>登陆随机码:</td>
			    		<td style="text-align:left;"><f:input path="wcsRandomNum" /></td>
			    	</tr>
			    	
			    	<tr>
			    		<td>所属管理员:</td>
			    		<td style="text-align:left;">
			    			<input type="text" name="adminName"  id="adminName" />
			    			<f:hidden path="wcsAdminId" />
			    		</td>
			    	</tr>
			    	<tr>
			    		<td colspan="2"  style="text-align:left;">
			    			<input type="button" value="保存" onclick="javascript:addWebsiteSubmit();"/>&nbsp;&nbsp;
			    			<input type="button" value="返回" onclick="window.location.href = '${pageContext.request.contextPath}/packadv/queryAdvPack';"/>
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