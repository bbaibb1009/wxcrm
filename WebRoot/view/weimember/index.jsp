<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/weimember/style.css?v=${applicationScope.sysStartUpTime}">
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/weimember/css/example.css?v=${applicationScope.sysStartUpTime}">
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/json2.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/wx_js_config.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript">
		var path = "${pageContext.request.contextPath}";
		var alertMsg = "${alertMsg}";
		
		////////////////////////////////////////////////////////////
		var appId 	  	= '${appId}';
		var timestamp 	= '${timestamp}';
		var nonceStr  	= '${nonceStr}';
		var signature 	= '${signature}';
		var shareTitle 	= '${shareTitle}';
		var shareLink 	= '${shareLink}';
		alert("shareLink:"+shareLink);
		var shareImgUrl = '${shareImgUrl}';
		////////////////////////////////////////////////////////////
		var wxConfOption = 
		{
    		debug: false, 			// 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    		appId: appId, 			// 必填，公众号的唯一标识
    		timestamp: timestamp, 	// 必填，生成签名的时间戳
    		nonceStr: nonceStr, 	// 必填，生成签名的随机串
    		signature: signature,	// 必填，签名，见附录1
    		jsApiList: jsApiList 	// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		};
		
		$(document).ready(function(){
			if( alertMsg.length>0)
			{
				alert(alertMsg);
			}
			if (typeof WeixinJSBridge == "undefined")
			{
			    if( document.addEventListener )
			    {
			        document.addEventListener('WeixinJSBridgeReady',onBridgeReady,false);
			    }
			    else if (document.attachEvent)
			    {
			        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			    }
			}
			else
			{
			    onBridgeReady();
			}
			wx.config(wxConfOption);
			wx.showOptionMenu();
		});
		
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
	    	var shareInfoTimeline = 
			{
		    	title: 	shareTitle, 	// 分享标题
		    	link: 	shareLink, 		// 分享链接
		    	imgUrl: shareImgUrl, 	// 分享图标
		    	success: function () { 
		       	 	// 用户确认分享后执行的回调函数
		       	 	//alert("本供求成功分享至朋友圈!");
			    },
				cancel: function () { 
				    // 用户取消分享后执行的回调函数
				}
			};
	    	fenxiang(shareInfoTimeline);//默认加载分享至朋友圈的链接
		});
		function fenxiang(shareInfoTimeline)
		{
		    wx.onMenuShareTimeline(shareInfoTimeline);
		}
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
		
	</script>
  </head>
  <body>
  	<h1>
  		<a href="javascript:goSubmit('${pageContext.request.contextPath}/weixine/toIntroEnter/');">
  		<img src="${pageContext.request.contextPath}/view/weixine/images/logo1.png" alt=""/>
  		</a>
  	</h1>
	<div class="wrap">
		<f:form action="${pageContext.request.contextPath}/weimember/toMemberIndex/${comamnd.wmbId}" class="dan-form" onsubmit="return false;">
			<p><strong>首页</strong></p>
		  	<p>appId:${appId}</p>
			<p>timestamp:${timestamp}</p>
			<p>nonceStr:${nonceStr}</p>
			<p>signature:${signature}</p>
			<p>shareTitle:${shareTitle}</p>
			<p>shareLink:${shareLink}</p>
			<p>shareImgUrl:${shareImgUrl}</p>
			<p>jsApiTicketStr:${jsApiTicketStr}</p>
		 </div>
   		</f:form>
	   	<footer>
		<p>&copy; 微信CRM</p>
		</footer>
  </body>
</html>
