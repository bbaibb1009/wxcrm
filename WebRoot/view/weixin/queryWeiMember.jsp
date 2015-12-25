<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>企业公众号-会员查询</title>
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

  	<f:form action="${pageContext.request.contextPath}/weixine/queryWeiMemberByApp/${command.wmbWecId_Q}" onsubmit="return false;">
  	<f:hidden path="wmbId"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:8%;">会员姓名:</td>  <td style="text-align:left;"><f:input path="wmbName_Q"/></td>
    		<td style="width:8%;">openid:</td>  <td style="text-align:left;"><f:input path="wmbOpenid_Q"/></td>
    	</tr>
    	<tr>
    		<td style="width:8%;">会员类型:</td><td style="text-align:left;">
    		<f:select path="wmbType_Q" onchange="querySubmit();">
    			<f:option value="">-请选择-</f:option>
    			<f:option value="1">关注者</f:option>
    			<f:option value="2">会员</f:option>	
    		</f:select>
    		</td>
    		<td style="width:8%;">手机:</td>		<td style="text-align:left;"><f:input path="wmbMobule_Q"/></td>
    	</tr>
    	<tr>
    		<td colspan="4">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    </table>
  	<input type="button" value="删除" onclick="delChk('wmbIds','${pageContext.request.contextPath}/weixine/delWeiMember/${command.wmbWecId_Q}');"/>
  	
  	
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
	   		<th width="1%"></th>
    		<th width="5%">微信名称</th>
    		<th width="10%">商家名称</th>
    		<th width="3%">卡号</th>
    		<th width="2%">openid</th>
    		<th width="2%">会员类型</th>
    		<th width="2%">姓名</th>
    		<th width="2%">手机号码</th>
    		<th width="2%">状态</th>
    		<th width="2%">备注</th>
    		<th width="2%">录入人</th>
    		<th width="2%">录入时间</th>
    		<th width="5%">操作</th>
    	</tr>
    	<c:forEach var="weiMember" items="${pageResult.resultList}">
	    	<tr>
	    		<td>
	    			<input type="checkbox" name="wmbIds" value="'${weiMember.WMB_ID}'"/>
	    		</td>
	    		<td>${weiMember.WEC_APP_NAME}</td>
	    		<td>${weiMember.WCS_WEBSITE_NAME}</td>
	    		<td>${weiMember.WMB_CARD_ID}</td>
	    		<td>${weiMember.WMB_OPENID}</td>
	    		<td>
	    		<c:if test="${weiMember.WMB_TYPE=='1'}">关注者</c:if>
	    		<c:if test="${weiMember.WMB_TYPE=='2'}">会员</c:if>
	    		</td>
	    		<td>${weiMember.WMB_NAME}</td>
	    		<td>${weiMember.WMB_MOBULE}</td>
	    		<td>
	    		<c:if test="${weiMember.WMB_STATUS=='1000'}">可用</c:if>
	    		<c:if test="${weiMember.WMB_STATUS=='2000'}">不可用</c:if>
	    		</td>
	    		<td>${weiMember.WMB_DESC}</td>
	    		<td>${weiMember.WAD_NAME}</td>
	    		
	    		<td><fmt:formatDate value="${weiMember.WMB_REGISTDATE}" /></td>
	    		<td></td>
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
