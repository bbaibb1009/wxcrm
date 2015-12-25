<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>企业公众号-客户查询</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixine/js/weixine.js?v=${applicationScope.sysStartUpTime}"></script>
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

  	<f:form action="${pageContext.request.contextPath}/weixine/queryWeiEnterAdminByApp/${command.wetCueId_Q}" onsubmit="return false;">
  	<f:hidden path="wetCueId_Q"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:8%;">企业名称:</td>  <td style="text-align:left;"><f:input path="wetCustenterName_Q"/></td>
    		<td style="width:8%;">openid:</td>  <td style="text-align:left;"><f:input path="wetOpenId_Q"/></td>
    	</tr>
    	<tr>
    		<td style="width:8%;">联系人姓名:</td><td style="text-align:left;"><f:input path="wetName_Q"/></td>
    		<td style="width:8%;">手机:</td>		<td style="text-align:left;"><f:input path="wetMobile_Q"/></td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    </table>
  	<!-- <input type="button" value="添加" onclick="window.location.href = '${pageContext.request.contextPath}/weixine/toAddWeiEnter';"/> -->
  	<input type="button" value="删除" onclick="delChk('wetIds','${pageContext.request.contextPath}/weixine/delWeiEnterCust/${command.wetCueId_Q}');"/>
  	
  	
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="1%"></th>
    		<th width="5%">openId</th>
    		<th width="10%">企业名称</th>
    		<th width="3%">手机</th>
    		<th width="2%">姓名</th>
    		<th width="2%">类型</th>
    		<th width="5%">操作</th>
    	</tr>
    	<c:forEach var="weiEnterCust" items="${pageResult.resultList}">
	    	<tr>
	    		<td>
	    			<input type="checkbox" name="wetIds" value="'${weiEnterCust.WET_ID}'"/>
	    		</td>
	    		<td>${weiEnterCust.WET_OPEN_ID}</td>
	    		<td>${weiEnterCust.WET_CUSTENTER_NAME}</td>
	    		<td>${weiEnterCust.WET_MOBILE}</td>
	    		<td>${weiEnterCust.WET_NAME}</td>
	    		<td>
	    		<c:if test="${weiEnterCust.WET_TYPE=='1'}">客户</c:if>
	    		<c:if test="${weiEnterCust.WET_TYPE=='2'}">管理员</c:if>
	    		</td>
	    		<td>
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
