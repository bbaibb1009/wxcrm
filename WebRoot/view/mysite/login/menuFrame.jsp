<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
		<div class="top">
			<span class="logo">&nbsp;</span><span style="width:40px;">&nbsp;</span><b>商家管理中心</b>&nbsp;&nbsp;&nbsp;&nbsp;
			您好，<a href="${pageContext.request.contextPath}/admin/showMyInfo/0">${adminInfoCust.wadName}</a>
			<a href="${pageContext.request.contextPath}/mysite/adminLoginSuccess">首页</a>&nbsp;&nbsp;&bull;&nbsp;
			<a href="${pageContext.request.contextPath}/document/use/index.html" target="_blank">使用说明</a>&nbsp;&nbsp;&bull;&nbsp;
			<a href="${pageContext.request.contextPath}/mysite/adminLogout">退出</a>
		</div>
		
		<div class="nav" id="adminTop">
			<c:forEach var="menu1" items="${adminMenusLevel1Cust}">
			<c:set var="menu1Class" value=""></c:set>
			<c:if test="${menu1.WME_ID == adminMenuId1Cust}">
			<c:set var="menu1Class" value="class='menu1'"></c:set>
			</c:if>
			<a href="#" onclick="showAdminMenu2(${menu1.WME_ID }, this);return false;" ${menu1Class}>${menu1.WME_NAME }</a>
			</c:forEach>
		</div>

		<div class="admin_left" id="adminLeft">
			<c:forEach var="menu1" items="${adminMenusLevel1Cust}">
			<c:set var="menu1Display" value="style='display: none;'"></c:set>
			<c:if test="${menu1.WME_ID == adminMenuId1Cust}">
			<c:set var="menu1Display" value=""></c:set>
			</c:if>
			<ul class="menu" id="${menu1.WME_ID }" ${menu1Display }>
				<c:forEach var="menu2" items="${adminMenusLevel2Cust}">
				<c:if test="${menu2.WME_PARENT_ID == menu1.WME_ID}">
				<li>&raquo;&nbsp;<a href="javascript: showAdminMenu3(${menu1.WME_ID }, ${menu2.WME_ID });">${menu2.WME_NAME }</a>
					<c:set var="menu2Display" value="style='display: none;'"></c:set>
					<c:if test="${menu2.WME_ID == adminMenuId2Cust}">
					<c:set var="menu2Display" value=""></c:set>
					</c:if>
					<ul id="${menu2.WME_ID }" ${menu2Display }>
						<c:forEach var="menu3" items="${adminMenusLevel3Cust}">
						<c:if test="${menu3.WME_PARENT_ID == menu2.WME_ID}">
						<c:set var="menu3Class" value=""></c:set>
						<c:if test="${menu3.WME_ID == adminMenuId3Cust}">
						<c:set var="menu3Class" value="class='menu3'"></c:set>
						</c:if>
						<li>&rsaquo;&nbsp;<a href="javascript: goUrl(${menu3.WME_ID }, '${menu3.WME_URL }');" ${menu3Class }>${menu3.WME_NAME }</a></li>
						</c:if>
						</c:forEach>
					</ul>
				</li>
				</c:if>
				</c:forEach>
			</ul>
			</c:forEach>
		</div>
		
		<div class="admin_center" id="adminCenter" onclick="showLeftMenu();">
			<img id="ar_l" src="${pageContext.request.contextPath}/view/image/ar_l.gif" border="0" style="display:none;"/>
			<img id="ar_r" src="${pageContext.request.contextPath}/view/image/ar_r.gif" border="0"/>
		</div>
