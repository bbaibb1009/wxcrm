function addErrorCode(action) {
	var myForm = document.forms[0];
	myForm.action = action;
	myForm.submit();
}

function showSendWeixin() {
	var action = arguments[0];
	var retVal = showMyDialog(action, window, 500, 400);
	var myForm = document.forms[0];
	if (retVal) {
		alert(retVal);
		myForm.submit();
	}
}

function sendMsg() {
	var myForm = document.forms[0];
	if ($("#wacOpenid").val().length == 0) {
		alert("获取用户微信授权出现错误!");
		retValMyDialog('');
	}
	if ($("#msg").val().trim().length == 0) {
		alert("消息内容不能为空!");
		$("#msg").focus();
		return;
	}
	myForm.submit();
}

function getAccessToken() {
	var myForm = document.forms[0];
	myForm.action = path + "/weixin/setAccessToken";
	myForm.submit();
}
function getJsApiTicket() {
	var myForm = document.forms[0];
	myForm.action = path + "/weixin/setJsApiTicket";
	myForm.submit();
}

function addWeiEnterSubmit() {
	// 检查是否为空
	if ($("#wecAppName").val().length == 0) {
		alert("应用名称不能为空!");
		$("#wecAppName").focus();
		return;
	}
	if ($("#wecAppId").val().length == 0) {
		alert("appId不能为空!");
		$("#wecAppId").focus();
		return;
	}
	if ($("#wecAppSecret").val().length == 0) {
		alert("AppSecret不能为空!");
		$("#wecAppSecret").focus();
		return;
	}
	if ($("#wecRederectUrl").val().length == 0) {
		alert("回调地址不能为空!");
		$("#wecRederectUrl").focus();
		return;
	}
	if ($("#wecToken").val().length == 0) {
		alert("令牌不能为空!");
		$("#wecToken").focus();
		return;
	}
	if ($("#wecEncodingAesKey").val().length == 0) {
		alert("EncodingAESKey不能为空!");
		$("#wecEncodingAesKey").focus();
		return;
	}
	if ($("#wecAesType").val().length == 0) {
		alert("消息加解密方式!");
		$("#wecAesType").focus();
		return;
	}
	if ($("#wecAppType").val().length == 0) {
		alert("应用类型不能为空!");
		$("#wecAppType").focus();
		return;
	}
	if ($("#wecCusType").val().length == 0) {
		alert("客户类型不能为空!");
		$("#wecCusType").focus();
		return;
	}
	if ($("#wecAccountType").val().length == 0) {
		alert("账户类型不能为空!");
		$("#wecAccountType").focus();
		return;
	}
	if ($("#wecCusType").val() == '2' && $("#wecEnterId").val().length == 0) {
		alert("商用客户企业不能为空!");
		$("#wecEnterName_Q").focus();
		return;
	}
	var myForm = document.forms[0];
	myForm.submit();

}

function showWebSite() {
	var $this = arguments[0];
	var action = path + "/weixin/queryWebSite";
	var retVal = showMyDialog(action, window, 800, 500);
	if (retVal) {
		var returnVal = retVal.split('^')[1];
		var enterId = returnVal.split('_')[0];
		var enterName = returnVal.split('_')[1];
		$("#wecEnterId").val(enterId);
		$("#wecEnterName_Q").val(enterName);
	}
}

function addNewWebsite() {
	var chkboxAry = $("input[name='wcsIds']:checked");
	var strCheck = "";
	if (chkboxAry.length > 0) {
		var selCount = 0;
		for (var i = 0; i < chkboxAry.length; i++) {
			if (chkboxAry[i].checked) {
				selCount++;
				strCheck = strCheck + "^" + chkboxAry[i].value;

			}
		}
		if (selCount == 0) {
			alert("请选择一个站点!");
			return;
		} else if (selCount > 1) {
			alert("只能选一个站点!");
			return;
		} else {
			retValMyDialog(strCheck);
		}
	}
}

function updWatcherOnline(action) {
	var myForm = document.forms[0];
	myForm.action = action;
	myForm.submit();
	myForm.action = path + "/weixin/queryWatcher";
}

function selDefaultMsg() {
	var action = arguments[0];
	var returnVal = showMyDialog(action, window, 700, 400);
	if (returnVal) {
		var id = returnVal.split("^")[1].split("_")[0];
		var content = returnVal.split("^")[1].split("_")[1];
		$("#wecDefaultMsg").val(id);
		$("#wecDefaultMsgDesc").val(content);

	}
}

function addDefaultMsg() {
	var chkboxAry = $("input[name='wmgIds']:checked");
	var strCheck = "";
	if (chkboxAry.length > 0) {
		var selCount = 0;
		for (var i = 0; i < chkboxAry.length; i++) {
			if (chkboxAry[i].checked) {
				selCount++;
				strCheck = strCheck + "^" + chkboxAry[i].value;
			}
		}
		if (selCount == 0) {
			alert("请选择您绑定的消息模板!");
			return;
		} else if (selCount > 1) {
			alert("不能多选!");
			return;
		} else {
			retValMyDialog(strCheck);
		}
	}
}

function selSubScirbeMsg() {
	var action = arguments[0];
	var returnVal = showMyDialog(action, window, 700, 400);
	if (returnVal) {
		var id = returnVal.split("^")[1].split("_")[0];
		var content = returnVal.split("^")[1].split("_")[1];
		$("#wecSubscribeMsg").val(id);
		$("#wecSubscribeMsgDesc").val(content);
	}
}

function addSubscribeMsg() {
	var chkboxAry = $("input[name='wmgIds']:checked");
	var strCheck = "";
	if (chkboxAry.length > 0) {
		var selCount = 0;
		for (var i = 0; i < chkboxAry.length; i++) {
			if (chkboxAry[i].checked) {
				selCount++;
				strCheck = strCheck + "^" + chkboxAry[i].value;
			}
		}
		if (selCount == 0) {
			alert("请选择您绑定的消息模板!");
			return;
		} else if (selCount > 1) {
			alert("不能多选!");
			return;
		} else {
			retValMyDialog(strCheck);
		}
	}
}

function selMsgModel() {
	var $this = arguments[0];
	var id = $($this).val();
	var trId = $("#type_" + id);
	// 判断当前的选择值 选择行显示
	$(trId).show();
	$(trId).children().find("input").removeAttr("disabled");
	// 根据选择值隐藏相应的行
	var trIdOther = $("tr[id^=type_][id!=type_" + id + "]");
	$(trIdOther).hide();
	$(trIdOther).each(function(index) {
				var trOther = $(trIdOther)[index];
				$(trOther).children().find("input")
						.attr("disabled", "disabled");
			});
}

function addLzWeiMsgSubmit() {
	var myForm = document.forms[0];
	if ($("#wmgAesType").val().length == 0) {
		alert("加密类型不能为空！");
		$("#wmgAesType").focus();
		return;
	}
	if ($("#wmgMsgType").val().length == 0) {
		alert("消息类型不能为空!");
		$("#wmgMsgType").focus();
		return;
	}
	if ($("#wmgReplyType").val().length == 0) {
		alert("回复类型不能为空!");
		$("#wmgReplyType").focus();
		return;
	}
	if ($("#wmgMsgType").val() == '2' && $("#wmgContent").val().length == 0) {
		alert("消息类型为文字时，回复内容不能为空!");
		$("#wmgContent").focus();
		return;
	}
	myForm.submit();
}

function selKeyWordsMsg() {
	var action = arguments[0];
	var returnVal = showMyDialog(action, window, 700, 500);
	if (returnVal) {
		var id = returnVal.split("^")[1].split("_")[0];// 消息ID
		var content = returnVal.split("^")[1].split("_")[1];// 消息原文
		// 先检查这个消息是否已经存在
		if ($("#keyWrdmsgId" + id).length == 0) {
			var tr = $("<tr></tr>");
			var iptKeyWrd = $("<input type='text'/>").attr("name", "keyWrd")
					.attr("id", "keyWrdId" + id);
			var td1 = $("<td></td>").append(iptKeyWrd);
			var hidMsgId = $("<input type='hidden' />").attr("name",
					"keyWrdmsgId").attr("id", "keyWrdmsgId" + id).val(id);
			var td2 = $("<td></td>").html(content).append(hidMsgId);
			var td3 = $("<td></td>");
			var btn = $("<input type='button' value='删除'>").bind('click',
					removeProTr);
			$(td3).append(btn);
			$(tr).append(td1).append(td2).append(td3);
			$(".trKwdTitle").after(tr);
		}

	}
}

function addKeyWordsMsg() {
	var chkboxAry = $("input[name='wmgIds']:checked");
	var strCheck = "";
	if (chkboxAry.length > 0) {
		var selCount = 0;
		for (var i = 0; i < chkboxAry.length; i++) {
			if (chkboxAry[i].checked) {
				selCount++;
				strCheck = strCheck + "^" + chkboxAry[i].value;
			}
		}
		if (selCount == 0) {
			alert("请选择您绑定的消息模板!");
			return;
		} else if (selCount > 1) {
			alert("不能多选!");
			return;
		} else {
			retValMyDialog(strCheck);
		}
	}
}

function removeOldKeyWords() {
	// 再删除(视觉上的删除)
	var $this = arguments[0];
	var td = $($this).parent();
	var tr = $(td).parent();
	$(tr).remove();
}

function removeProTr() {
	var td = $(this).parent();
	var tr = $(td).parent();
	$(tr).remove();
}


function addNewsMsg()
{
	var action = path+"/weixinmsg/toAddNewsMsg";
	var retVal = showMyDialog(action,window,500,400);
	if(retVal)
	{
		var obj = retVal;
		
		$.ajax({
			url:path+'/weixinmsg/getArticleJson/'+retVal,
			data:{},
			dataType:'json',
			type:'post',
			success:function(data){
				var obj1 = eval(data);
				var iptArticle = $("<input type='hidden' name='articleId'/>").val(obj1["WAM_ID"]);
				var tr = $("<tr></tr>");
				var td1 = $("<td></td>").html(obj1["WAM_TITLE"]).append(iptArticle);
				var td2 = $("<td></td>").html(obj1["WAM_DESCRIPTION"]);//描述
				var td3 = $("<td></td>").html(obj1["WAM_PICURL"]);//图片链接
				var td4 = $("<td></td>").html(obj1["WAM_URL"]);//跳转链接
				$(tr).append(td1).append(td2).append(td3).append(td4);
				$(".newsMsgTh").after(tr);
			},
			error:function(){
				alert("错误");
			}
		});
	}
}
function addNewsMsgSubmit()
{
	var myForm = document.forms[0];
	myForm.submit();
}