<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>商铺管理员修改</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/zTree/zTreeStyle.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/menu.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/website/js/admin.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.core-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/zTree/jquery.ztree.excheck-3.5.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/My97DatePicker/WdatePicker.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery.autocomplete.min.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var adminIdStr = "${command.wsaId}";
	
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
		loadMenuTreeForAdmin(${command.wsaId});
	});
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  
   	<f:form action="${pageContext.request.contextPath}/shopadmin/updShopAdmin" onsubmit="return false;">
  	<f:hidden path="wsaId"/>
  	<f:hidden path="wsaPwdMd5"/>
  	<f:hidden path="wsaLogindate"/>
  	<f:hidden path="menuIds"/>
  	<f:hidden path="wsaUsername_Q"/>
  	<f:hidden path="wsaName_Q"/>
  	<f:hidden path="currentPage"/>
  	<f:hidden path="pageSize"/>
  	
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="25%"><font color="red"><b>*</b></font>用户名：</td>
    		<td width="75%"><f:input path="wsaUsername" readonly="true"/></td>
    	</tr>
    	<tr>
    		<td>重设密码</td>
    		<td><a href="#" onclick="resetPwd(this);return false;">重设</a></td>
    	</tr>
    	<tr id="pwdTr" style="display: none;">
    		<td><font color="red"><b>*</b></font>密码：</td>
    		<td><f:password path="wsaPwd" maxlength="20"/></td>
    	</tr>
    	<tr id="pwd2Tr" style="display: none;">
    		<td><font color="red"><b>*</b></font>密码确认：</td>
    		<td><input type="password" id="wsaPwd2" name="wsaPwd2"/></td>
    	</tr>
    	<tr>
    		<td><font color="red"><b>*</b></font>姓名：</td>
    		<td><f:input path="wsaName" maxlength="10"/></td>
    	</tr>
    	<tr>
    		<td><font color="red"><b>*</b></font>性别：</td>
	    	<td>
	   			<f:select path="wsaSex">
	   				<f:option value="1">男</f:option>
	   				<f:option value="0">女</f:option>
	   			</f:select>
	   		</td>
   		</tr>
    	<tr>
			<td>角色设置：</td>
			<td>
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td align="center">未有角色</td>
					<td>&nbsp;</td>
					<td align="center">已有角色</td>
				</tr>
				<tr>
					<td align="center">
						<f:select path="roleIds2" multiple="true" size="20" cssStyle="width: 250px;">
							<f:options items="${roleList0}" itemValue="wsrRoleId" itemLabel="wsrRoleName"/>
						</f:select>
					</td>
					<td align="center">
						<img src="${pageContext.request.contextPath}/view/image/goright.gif" onclick="changeRole(document.getElementById('roleIds2'), document.getElementById('roleIds'), ${command.wsaId});" title="添加角色" style="cursor: pointer;"/>
						<br/><br/>
						<img src="${pageContext.request.contextPath}/view/image/goleft.gif" onclick="changeRole(document.getElementById('roleIds'), document.getElementById('roleIds2'), ${command.wsaId});" title="删除角色" style="cursor: pointer;"/>
					</td>
					<td align="center">
						<f:select path="roleIds" multiple="true" size="20" cssStyle="width: 250px;">
							<f:options items="${roleList1}" itemValue="wsrRoleId" itemLabel="wsrRoleName"/>
						</f:select>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><font color="red"><b>*</b></font>管理员权限：</td>
			<td><ul id="treeDemo" class="ztree"></ul></td>
		</tr>
    	<tr>
    		<td colspan="2">
    			<input type="button" value="修改" onclick="addAdminSubmit('upd');"/>&nbsp;&nbsp;
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
