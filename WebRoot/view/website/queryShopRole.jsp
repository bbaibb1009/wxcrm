<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>商家角色查询</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/role.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  
  
  	<f:form action="${pageContext.request.contextPath}/shoprole/queryShopRole" onsubmit="return false;">
  	<f:hidden path="wsrRoleId"/>
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="30%">角色名称：</td>
    		<td width="30%"><f:input path="roleName_Q"/></td>
    		<td width="40%">
    			<input type="button" value="查询" onclick="querySubmit()"/>&nbsp;&nbsp;
    			<input type="button" value="重置" onclick="queryRoleReset()"/>
    		</td>
    	</tr>
    </table>
    
    <br/>
  	<input type="button" value="添加" onclick="window.location.href = '${pageContext.request.contextPath}/shoprole/toAddShopRole';"/>
  	<input type="button" value="删除" onclick="delChk('wroRoleIds', '${pageContext.request.contextPath}/shoprole/delRole');"/>
  	
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="5%"><input type="checkbox" onclick="chkall(this.checked, 'wsrRoleIds');"/></th>
    		<th width="20%">角色名称</th>
    		<th width="55%">备注</th>
    		<th width="20%">操作</th>
    	</tr>
    	<c:forEach var="role" items="${pageResult.resultList}">
    	<tr>
    		<td><input type="checkbox" name="wroRoleIds" value="${role.WSR_ROLE_ID }" /></td>
    		<td>${role.WSR_ROLE_NAME }</td>
    		<td>${role.WSR_ROLE_DESC }</td>
    		<td>
    			<a href="javascript:showRole(${role.WSR_ROLE_ID });">查看</a>
    			<a href="javascript:toUpd('wsrRoleId', ${role.WSR_ROLE_ID}, '${pageContext.request.contextPath}/shoprole/toUpdShopRole')">修改</a>
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
