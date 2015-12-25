<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>菜单修改</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/zTree/zTreeStyle.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/menu.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	
	var setting = {
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  
  
  	<f:form action="${pageContext.request.contextPath}/menu/updMenu" onsubmit="return false;">
  	<f:hidden path="wmeParentId"/>
  	<f:hidden path="wmeId"/>
  	
	<f:hidden path="wmeLevel"/>
  	<f:hidden path="wmeParentId"/>
  	<f:hidden path="wmeLevel_Q"/>
  	<f:hidden path="wmeName_Q"/>
  	<f:hidden path="currentPage"/>
  	<f:hidden path="pageSize"/>
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="30%"><font color="red"><b>*</b></font>菜单名称：</td>
    		<td width="70%"><f:input path="wmeName" maxlength="25"/></td>
    	</tr>
    	<tr>
    		<td>菜单URL：</td>
    		<td><f:input path="wmeUrl" maxlength="100"/></td>
    	</tr>
    	<tr>
    		<td><font color="red"><b>*</b></font>菜单级别：</td>
    		<td>
    			<c:if test="${command.wmeLevel == '1'}">
    			一级菜单
    			</c:if>
    			<c:if test="${command.wmeLevel == '2'}">
    			二级菜单
    			</c:if>
    			<c:if test="${command.wmeLevel == '3'}">
    			三级菜单
    			</c:if>
    		</td>
    	</tr>
    	<c:if test="${command.wmeLevel != '1'}">
    	<tr>
    		<td><font color="red"><b>*</b></font>上级菜单：</td>
    		<td><ul id="treeDemo" class="ztree"></ul></td>
    	</tr>
    	<script type="text/javascript">
    	loadMenuTree("${pageContext.request.contextPath}/menu/getParentMenuTree/${command.wmeLevel}/${command.wmeParentId}");
    	</script>
    	</c:if>
    	<tr>
    		<td><font color="red"><b>*</b></font>菜单顺序：</td>
    		<td><f:input path="wmeOrder" maxlength="3"/></td>
    	</tr>
    	<tr>
    		<td>备注：</td>
    		<td><f:textarea path="wmeDesc" cols="50" rows="5"/></td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" value="添加" onclick="addMenuSubmit();"/>&nbsp;&nbsp;
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
