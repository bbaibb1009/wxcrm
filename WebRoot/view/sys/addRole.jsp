<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>角色添加</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/zTree/zTreeStyle.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/menu.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/role.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "ps", "N" : "ps" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	$(document).ready(function(){
		loadMenuTree("${pageContext.request.contextPath}/menu/getMenuTreeForRole/0/-1");
		//sloadDeptTree("${pageContext.request.contextPath}/dept/getDeptTreeForRole/-1", "treeDemo2", setting);
	});
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  
  
  	<f:form action="${pageContext.request.contextPath}/role/addRole" onsubmit="return false;">
  	<f:hidden path="menuIds"/>
  	<f:hidden path="adminIds"/>
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="30%"><font color="red"><b>*</b></font>角色名称：</td>
    		<td width="70%"><f:input path="wroRoleName" maxlength="50"/></td>
    	</tr>
    	<tr>
    		<td><font color="red"><b>*</b></font>角色权限：</td>
    		<td><ul id="treeDemo" class="ztree"></ul></td>
    	</tr>
    	<tr>
    		<td>管理员：</td>
    		<td><ul id="treeDemo2" class="ztree"></ul></td>
    	</tr>
    	<tr>
    		<td>备注：</td>
    		<td><f:textarea path="wroRoleDesc" cols="50" rows="5"/></td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" value="添加" onclick="addRoleSubmit();"/>&nbsp;&nbsp;
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
