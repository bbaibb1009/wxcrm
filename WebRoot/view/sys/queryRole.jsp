<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>��ɫ��ѯ</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/sys/js/role.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	
	</script>
  </head>
  
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  
  
  	<f:form action="${pageContext.request.contextPath}/role/queryRole" onsubmit="return false;">
  	<f:hidden path="wroRoleId"/>
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="30%">��ɫ���ƣ�</td>
    		<td width="30%"><f:input path="roleName_Q"/></td>
    		<td width="40%">
    			<input type="button" value="��ѯ" onclick="querySubmit()"/>&nbsp;&nbsp;
    			<input type="button" value="����" onclick="queryRoleReset()"/>
    		</td>
    	</tr>
    </table>
    
    <br/>
  	<input type="button" value="���" onclick="window.location.href = '${pageContext.request.contextPath}/role/toAddRole';"/>
  	<input type="button" value="ɾ��" onclick="delChk('wroRoleIds', '${pageContext.request.contextPath}/role/delRole');"/>
  	
    <table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<th width="5%"><input type="checkbox" onclick="chkall(this.checked, 'wroRoleIds');"/></th>
    		<th width="20%">��ɫ����</th>
    		<th width="55%">��ע</th>
    		<th width="20%">����</th>
    	</tr>
    	<c:forEach var="role" items="${pageResult.resultList}">
    	<tr>
    		<td><input type="checkbox" name="wroRoleIds" value="${role.WRO_ROLE_ID }" /></td>
    		<td>${role.WRO_ROLE_NAME }</td>
    		<td>${role.WRO_ROLE_DESC }</td>
    		<td>
    			<a href="javascript:showRole(${role.WRO_ROLE_ID });">�鿴</a>
    			<a href="javascript:toUpd('wroRoleId', ${role.WRO_ROLE_ID}, '${pageContext.request.contextPath}/role/toUpdRole')">�޸�</a>
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
