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
			  	<f:form action="${pageContext.request.contextPath}/weixin/addWeiEnter" onsubmit="return false;">
			    <table cellpadding="0" cellspacing="0" border="1" width="100%" >
			    	<tr>
			    		<td width="10%" ><font color="red"><b>*</b></font>应用名称:</td>
			    		<td width="75%" style="text-align:left;"><f:input path="wecAppName"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>APPID：</td>
			    		<td style="text-align:left;"><f:input path="wecAppId"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"><b>*</b></font>AppSecret：</td>
			    		<td style="text-align:left;"><f:password path="wecAppSecret"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>URL(服务器回调地址)：</td>
			    		<td style="text-align:left;"><f:input path="wecRederectUrl"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>Token(令牌)：</td>
			    		<td style="text-align:left;"><f:input path="wecToken"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>EncodingAESKey：</td>
			    		<td style="text-align:left;"><f:input path="wecEncodingAesKey"/></td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>消息加解密方式：</td>
			    		<td style="text-align:left;">
				    		<f:select path="wecAesType" >
				    			<f:option value="">-请选择-</f:option>
				    			<f:option value="0">明文</f:option>
				    			<f:option value="1">加密</f:option>
				    			<f:option value="2">兼容</f:option>
		    				</f:select>
			    		</td>
			    	</tr>
					<tr>
			    		<td><font color="red"></font>应用类型：</td>
			    		<td style="text-align:left;">
				    		<f:select path="wecAppType" >
				    			<f:option value="">-请选择-</f:option>
				    			<f:option value="1">微信</f:option>
				    			<f:option value="2">微博</f:option>
				    		</f:select>
			    		</td>
			    	</tr>	
			    	<tr>
			    		<td><font color="red"></font>账户类型：</td>
			    		<td style="text-align:left;">
				    		<f:select path="wecAccountType" >
				    			<f:option value="">-请选择-</f:option>
				    			<f:option value="1">订阅号</f:option>
				    			<f:option value="2">服务号</f:option>
				    			<f:option value="3">企业号</f:option>
				    		</f:select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>客户类型：</td>
			    		<td style="text-align:left;">
				    		<f:select path="wecCusType" >
				    			<f:option value="">-请选择-</f:option>
				    			<f:option value="1">内部应用</f:option>
				    			<f:option value="2">商用</f:option>
				    		</f:select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>所属站点：</td>
			    		<td style="text-align:left;">
			    			<f:input path="wecEnterName_Q"/><f:hidden path="wecEnterId"/><input type="button" value="查找站点" onclick="javascript:showWebSite(this);"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td><font color="red"></font>备注：</td>
			    		<td style="text-align:left;"><f:textarea path="wecDesc"/></td>
			    	</tr>
			    	<tr>
			    		<td colspan="2"  style="text-align:left;">
			    			<input type="button" value="保存" onclick="javascript:addWeiEnterSubmit();"/>&nbsp;&nbsp;
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