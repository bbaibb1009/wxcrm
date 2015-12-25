<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript"  src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <script type="text/javascript" 	src="${pageContext.request.contextPath}/view/js/wx_js_config.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" 	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
		var path = "${pageContext.request.contextPath}";
		var alertMsg 	= "${alertMsg}";
		////////////////////////////////////////////////////////////
		var appId 	  	= '${appId}';
		var timestamp 	= '${timestamp}';
		var nonceStr  	= '${nonceStr}';
		var signature 	= '${signature}';
		
		////////////////////////////////////////////////////////////
		var wxConfOption = 
		{
    		debug: true, 			// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    		appId: appId, 			// 必填，公众号的唯一标识
    		timestamp: timestamp, 	// 必填，生成签名的时间戳
    		nonceStr: nonceStr, 	// 必填，生成签名的随机串
    		signature: signature,	// 必填，签名，见附录1
    		jsApiList: jsApiList 	// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		};
		
		wx.ready(function(){
	    	/*
	    		config信息验证后会执行ready方法，
	    		所有接口调用都必须在config接口获得结果之后，
	    		config是一个客户端的异步操作，
	    		所以如果需要在页面加载时就调用相关接口，
	    		则须把相关接口放在ready函数中调用来确保正确执行。
	    		对于用户触发时才调用的接口，
	    		则可以直接调用，不需要放在ready函数中。
	    	*/ 
		});
		wx.error(function(res){
	    	/*
	    		config信息验证失败会执行error函数，
	    		如签名过期导致验证失败，
	    		具体错误信息可以打开config的debug模式查看，
	    		也可以在返回的res参数中查看，
	    		对于SPA可以在这里更新签名。
	    	*/ 
		});
		
		function onBridgeReady()
		{
 			WeixinJSBridge.call('showOptionMenu');
		}

		//上传图片 提交
		function addUploadImgSubmit()
		{ 
			wx.uploadImage({
			    localId: $("#image1").attr("src") , // 需要上传的图片的本地ID，由chooseImage接口获得
			    isShowProgressTips: 1, // 默认为1，显示进度提示
			    success: function (res) {
			        var serverId = res.serverId; // 返回图片的服务器端ID
			        $("#serverId").val(serverId);
			        docment.forms[0].submit();

			    }
			});
		}
		//选择相册的图片就能
	
		function chooseImg()
		{
			wx.chooseImage({
	    		success: function (res) 
	    		{
	        		var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        		alert(localIds.length);
	        		alert(localIds);
	        		$("#image1").attr("src",localIds);
	    		}
		});
		}
		
		
		$(document).ready(function(){
			if( alertMsg.length > 0 )
			{
				alert(alertMsg);
			}
			wx.config(wxConfOption);
		});
	</script>
<title>上传图片</title>
</head>
<body>
	<f:form action="${pageContext.request.contextPath}/test/uploadImg/${appId}"  onsubmit="return false;" method="post" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	<tr>
    		<td width="70%"><input type="button" onclick="javascript:chooseImg();" value="选择" style="width:200px;height:200px;"/></td>
    	</tr>
    	<tr>
    		<td width="70%"><img id="image1" src=""/></td>
    	</tr>
    	<tr>
    		<td width="70%"><input name="serverId" id="serverId" value=""/></td>
    	</tr>
    	<tr>
    		<td>
    			<input type="button" id="btnAdd" value="上传" onclick="javascript:addUploadImgSubmit();"/>&nbsp;&nbsp;
    			<input type="button" id="btnBack" value="返回" onclick="window.location.href = '${pageContext.request.contextPath}/account/queryAccountIn';"/>
    		</td>
    	</tr>
    	</table>
	</f:form>
</body>
</html>