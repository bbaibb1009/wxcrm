<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>登录 - 山东隆众问答平台</title>
    
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/global.css?v=${applicationScope.sysStartUpTime}">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/login.css?v=${applicationScope.sysStartUpTime}">
    
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript">
    function usernameFocus(obj)
    {
    	if( obj.value.trim() == "用户名(请使用后台登录账号)" )
    	{
    		obj.value = "";
    	}
    }
    
    function usernameBlur(obj)
    {
    	if( obj.value.trim() == "" )
    	{
    		obj.value = "用户名(请使用后台登录账号)";
    	}
    }
    
    function pwdFocus()
    {
    	var pwdLabel = document.getElementById("pwdLabel");
    	var pwd = document.getElementById("wadPwd");
    	pwdLabel.style.display = "none";
    	pwd.style.display = "";
    	pwd.focus();
    }
    
    function pwdBlur()
    {
    	var pwdLabel = document.getElementById("pwdLabel");
    	var pwd = document.getElementById("wadPwd");
    	if( pwd.value.length == 0 )
    	{
    		pwdLabel.style.display = "";
    		pwd.style.display = "none";
    	}
    }
    
    function adminLoginSubmit()
    {
    	var username = document.getElementById("wadUsername");
    	var pwd = document.getElementById("wadPwd");
    	if( username.value.trim() == "用户名(请使用后台登录账号)" || username.value.trim() == "" )
    	{
    		alert("请输入用户名");
    		return;
    	}
    	if( pwd.value.length == 0 )
    	{
    		alert("请输入密码");
    		return;
    	}
    	username.value = username.value.trim();
    	document.forms[0].submit();
    }
    
    var alertMsg = "${alertMsg}";
    var usernameError = "${usernameError}";
	$(document).ready(function(){
		var username = document.getElementById("wadUsername");
		if( username != null && username.value.trim().length == 0 )
		{
			username.value = "用户名(请使用后台登录账号)";
		}
		
		if( alertMsg.length > 0 )
		{
			alert(alertMsg);
		}
		if( usernameError.length > 0 && window.confirm(usernameError) )
		{
			document.forms[0].action = "${pageContext.request.contextPath}/admin/toRegisterAdmin";
			document.forms[0].submit();
		}
	});
	
	/*
	$(document).keypress(function(eve){
			if(eve.which==13)
			{
					adminLoginSubmit();
			}
	});
	*/
    </script>
  </head>
  
  <body>
  	<div id="wrapper">
		<div class="login_bd">
		    <h1>
		      <img src="${pageContext.request.contextPath}/view/css/frame/images/logo.png"/>
		      <p class="i_cl_04">欢迎您访问隆众石化网后台！</p>
		    </h1>
		    <p class="login_state i_white" id="login_explain">
		    石油和化工产业资源整合者<br />
		    专注石油和化工产业链服务<br />
		    五大服务：资讯、咨询、电商、会议、公共服务平台<br />
		    石化产业信息服务商
		    </p>
		    <!--登陆模块_s-->
	    	<div class="login_mod" id="user_login">
	        	<h3 class="login_title i_white"><span class="login_c i_small i_line"></span>登录
	        	<a href="${pageContext.request.contextPath}/admin/toRegisterAdmin" style="color:white;">注册</a>
	        	</h3>
	        	<f:form action="${pageContext.request.contextPath}/admin/adminLogin">
	            <ul class="loging login_bg_02">
  
	            	<li class="i_lcol_01 i_white_bg">
		            	<label class="login_lb i_small un i_left"></label>
						<f:input path="wadUsername" cssClass="login_txt" onfocus="usernameFocus(this);" onblur="usernameBlur(this);" maxlength="20" autocomplete="off"/>					            	
	            	</li>
	                <li class="i_lcol_01 i_white_bg">
		                <label class="login_lb i_small pw i_left"></label>
		                <input type="text" value="密码(默认密码abc123)" class="login_txt" id="pwdLabel" onfocus="pwdFocus();" />
		                <f:password path="wadPwd" cssClass="login_txt" maxlength="20" onblur="pwdBlur();" autocomplete="off"/>
	                </li>
	                <li id="global_err" class="i_hide"><p class="i_global_err i_small i_err_bg"><em class="e"></em><span id="glo_Err"></span></p></li>
	                <li>
	                	<a href="javascript:adminLoginSubmit();" class="login_sub i_cl_06 i_fb_14 i_right" >登录</a>
	                	<a class="i_cl_02" href="${pageContext.request.contextPath}/admin/toForgetPwd">忘记密码</a>
	                </li>

	               
	            </ul>
	        	</f:form>
	            <div class="login_entrance login_bg_01">
				</div>
	        </div>
		    
		    <!--透明层-->  
		    <div class="login_alp i_alpha_login i_black_bg"></div>
	  	</div>
	</div>
	
	<div class="l_footer">Copyright &copy; 隆众石化网, All Rights Reserved.<br>
	Powered By <a href="http://www.oilchem.net" target="blank">隆众石化网</a></div>
	
  </body>
</html>
