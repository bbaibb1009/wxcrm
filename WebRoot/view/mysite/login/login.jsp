<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户登录</title>
		<LINK href="${pageContext.request.contextPath}/view/mysite/login/images/Default.css" type=text/css rel=stylesheet>
		<LINK href="${pageContext.request.contextPath}/view/mysite/login/images/xtree.css" type=text/css rel=stylesheet>
		<LINK href="${pageContext.request.contextPath}/view/mysite/login/images/User_Login.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
	    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/view/mysite/login/js/login.js?v=${applicationScope.sysStartUpTime}"></script>
	    <script type="text/javascript">
			var path = "${pageContext.request.contextPath}";
		</script>
	</head>
	<BODY id=userlogin_body>
	<f:form action="${pageContext.request.contextPath}/mysite/login" onsubmit="return false;">
	<DIV>
	</DIV>
	<DIV id=user_login>
	<DL>
  		<DD id=user_top>
  		<UL>
    		<LI class=user_top_l></LI>
    		<LI class=user_top_c></LI>
    		<LI class=user_top_r></LI>
    	</UL>
  		<DD id=user_main>
	  	<UL>
	    	<LI class=user_main_l></LI>
	    	<LI class=user_main_c>
		    	<DIV class=user_main_box>
			    	<UL>
				      	<LI class=user_main_text>用户名： </LI>
				      	<LI class=user_main_input>
				      		<f:input cssClass="TxtUserNameCssClass" path="wadUsername"  /> 
				      	</LI>
			      	</UL>
	   				<UL>
	      				<LI class=user_main_text>密 码： </LI>
					   	<LI class=user_main_input>
					      	<f:password cssClass="TxtPasswordCssClass" path="wadPwd" /> 
					    </LI>
	      			</UL>
	    			<UL>
	      				<LI class=user_main_text>记住密码</LI>
	      				<LI class=user_main_input>
					      	<f:checkbox path="remember"/>
					    </LI>
	        		</UL>
	        	</DIV>
        	</LI>
    		<LI class=user_main_r>
    			<INPUT class=IbtnEnterCssClass id=IbtnEnter style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
    			onclick='javascript:login();' 
    			type=image src="${pageContext.request.contextPath}/view/mysite/login/images/user_botton.gif" name=IbtnEnter> 
    		</LI>
    	</UL>
  		<DD id=user_bottom>
  			<UL>
    			<LI class=user_bottom_l></LI>
    			<LI class=user_bottom_c></LI>
    			<LI class=user_bottom_r></LI>
    		</UL>
    	</DD>
    </DL>
   </DIV>
   <SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN>
   <SPAN id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN>
   <SPAN id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
	<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>



</f:form></BODY>
</html>