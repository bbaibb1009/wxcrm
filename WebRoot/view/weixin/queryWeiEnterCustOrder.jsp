<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<html>
  <head>
    <title>企业订单查询</title>
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

  	<f:form action="${pageContext.request.contextPath}/weixine/queryWeiEnterCustOrder/${command.weoEnterId_Q}" onsubmit="return false;">
  	<f:hidden path="weoId"/>
  	<f:hidden path="weoWetId_Q"/>
  	<f:hidden path="weoEnterId_Q"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td style="width:8%;">企业名称：</td> <td style="text-align:left;"><f:input path="weoCustenterName_Q"/></td>
    		<td style="width:8%;">联系人姓名:</td><td style="text-align:left;"><f:input path="weoName_Q"/></td>
    		<td style="width:8%;">手机:</td>		<td style="text-align:left;"><f:input path="weoMobile_Q"/></td>
    		<td style="width:8%;">产品:</td>		<td style="text-align:left;"><f:input path="weoProName_Q"/></td>
    	</tr>
    	<tr>
    		<td colspan="8">
    			<input type="button" value="查询" onclick="querySubmit()"/>
    		</td>
    	</tr>
    	
    </table>
  	<input type="button" value="删除" onclick="delChk('weoIds','${pageContext.request.contextPath}/weixine/delWeiEnterOrder');"/>
  	<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="1%"></th>
    		<th width="3%">产品</th>
    		<th width="2%">规格</th>
    		<th width="2%">数量</th>
    		<th width="2%">价格</th>
    		<th width="2%">企业名称</th>
    		<th width="2%">联系人</th>
    		<th width="2%">手机</th>
    		<th width="5%">创建时间</th>
    		<th width="8%">操作</th>
    	</tr>
    	<c:forEach var="weiEnterOrder" items="${pageResult.resultList}">
	    	<tr>
	    		<td><input type="checkbox" name="weoIds" value="'${weiEnterOrder.WEO_ID}'"/></td>
	    		<td>${weiEnterOrder.pro_cn_name}</td>
	    		<td>${weiEnterOrder.gq_cycle}</td>
	    		<td>${weiEnterOrder.gq_num}</td>
	    		<td>
	    			<c:if test="${weiEnterOrder.gq_price==null}">面议</c:if>
	    			<c:if test="${weiEnterOrder.gq_price!=null}">${weiEnterOrder.gq_price}</c:if>
	    		</td>
	    		<td>${weiEnterOrder.WET_CUSTENTER_NAME}</td>
	    		<td>${weiEnterOrder.WET_NAME}</td>
	    		<td>${weiEnterOrder.WET_MOBILE}</td>
	    		<td><fmt:formatDate value="${weiEnterOrder.WEO_REGISTDATE}" type="both"/></td>
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
