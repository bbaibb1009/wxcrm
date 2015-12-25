<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>测试添加</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/My97DatePicker/WdatePicker.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/view/test/js/test.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
		</script>
	</head>
	<body>
		<div class="wrapper">
		  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
		  	<div class="admin_right" id="adminRight">
		  		<div class="content">
			  	<f:form action="${pageContext.request.contextPath}/test/addTest" onsubmit="return false;">
			    <table cellpadding="0" cellspacing="0" border="1" width="100%" >
			    	<tr>
			    		<td width="10%" ><font color="red"><b>*</b></font>用户名：</td>
			    		<td width="75%" style="text-align:left;"><f:input path="wcsUsername" maxlength="40" cssStyle="width:200px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>字段1：VARCHAR</td>
			    		<td style="text-align:left;">
			    		 	<f:input path="wcsColumn1"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>字段2：TEXT</td>
			    		<td style="text-align:left;"><f:input path="wcsColumn2" cssStyle="width:80px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>字段3：DOUBLE</td>
			    		<td style="text-align:left;"><f:input path="wcsColumn3" cssStyle="width:80px;"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>字段4：DECIMAL</td>
			    		<td style="text-align:left;"><f:input path="wcsColumn4" /></td>
			    	</tr>
			    	<tr>
			    		<td>字段5：FLOAT</td>
			    		<td style="text-align:left;"><f:input path="wcsColumn5" /></td>
			    	</tr>
			    	<tr>
			    		<td>字段6：DATETIME</td>
			    		<td style="text-align:left;">
			    			<f:input onfocus="WdatePicker({readOnly:true})" cssClass="Wdate" cssStyle="cursor: pointer;" path="wcsColumn6"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td>字段7：DATE</td>
			    		<td style="text-align:left;">
			    			<f:input onfocus="WdatePicker({readOnly:true})" cssClass="Wdate" cssStyle="cursor: pointer;" path="wcsColumn7"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td colspan="2"  style="text-align:left;">
			    			<input type="button" value="保存" onclick="javascript:addTestSubmit();"/>&nbsp;&nbsp;
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