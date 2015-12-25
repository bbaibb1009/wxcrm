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
		alert("��ȡ�û�΢����Ȩ���ִ���!");
		retValMyDialog('');
	}
	if ($("#msg").val().trim().length == 0) {
		alert("��Ϣ���ݲ���Ϊ��!");
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
	// ����Ƿ�Ϊ��
	if ($("#wecAppName").val().length == 0) {
		alert("Ӧ�����Ʋ���Ϊ��!");
		$("#wecAppName").focus();
		return;
	}
	if ($("#wecAppId").val().length == 0) {
		alert("appId����Ϊ��!");
		$("#wecAppId").focus();
		return;
	}
	if ($("#wecAppSecret").val().length == 0) {
		alert("AppSecret����Ϊ��!");
		$("#wecAppSecret").focus();
		return;
	}
	if ($("#wecRederectUrl").val().length == 0) {
		alert("�ص���ַ����Ϊ��!");
		$("#wecRederectUrl").focus();
		return;
	}
	if ($("#wecToken").val().length == 0) {
		alert("���Ʋ���Ϊ��!");
		$("#wecToken").focus();
		return;
	}
	if ($("#wecEncodingAesKey").val().length == 0) {
		alert("EncodingAESKey����Ϊ��!");
		$("#wecEncodingAesKey").focus();
		return;
	}
	if ($("#wecAesType").val().length == 0) {
		alert("��Ϣ�ӽ��ܷ�ʽ!");
		$("#wecAesType").focus();
		return;
	}
	if ($("#wecAppType").val().length == 0) {
		alert("Ӧ�����Ͳ���Ϊ��!");
		$("#wecAppType").focus();
		return;
	}
	if ($("#wecCusType").val().length == 0) {
		alert("�ͻ����Ͳ���Ϊ��!");
		$("#wecCusType").focus();
		return;
	}
	if ($("#wecAccountType").val().length == 0) {
		alert("�˻����Ͳ���Ϊ��!");
		$("#wecAccountType").focus();
		return;
	}
	if ($("#wecCusType").val() == '2' && $("#wecEnterId").val().length == 0) {
		alert("���ÿͻ���ҵ����Ϊ��!");
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
			alert("��ѡ��һ��վ��!");
			return;
		} else if (selCount > 1) {
			alert("ֻ��ѡһ��վ��!");
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
			alert("��ѡ�����󶨵���Ϣģ��!");
			return;
		} else if (selCount > 1) {
			alert("���ܶ�ѡ!");
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
			alert("��ѡ�����󶨵���Ϣģ��!");
			return;
		} else if (selCount > 1) {
			alert("���ܶ�ѡ!");
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
	// �жϵ�ǰ��ѡ��ֵ ѡ������ʾ
	$(trId).show();
	$(trId).children().find("input").removeAttr("disabled");
	// ����ѡ��ֵ������Ӧ����
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
		alert("�������Ͳ���Ϊ�գ�");
		$("#wmgAesType").focus();
		return;
	}
	if ($("#wmgMsgType").val().length == 0) {
		alert("��Ϣ���Ͳ���Ϊ��!");
		$("#wmgMsgType").focus();
		return;
	}
	if ($("#wmgReplyType").val().length == 0) {
		alert("�ظ����Ͳ���Ϊ��!");
		$("#wmgReplyType").focus();
		return;
	}
	if ($("#wmgMsgType").val() == '2' && $("#wmgContent").val().length == 0) {
		alert("��Ϣ����Ϊ����ʱ���ظ����ݲ���Ϊ��!");
		$("#wmgContent").focus();
		return;
	}
	myForm.submit();
}

function selKeyWordsMsg() {
	var action = arguments[0];
	var returnVal = showMyDialog(action, window, 700, 500);
	if (returnVal) {
		var id = returnVal.split("^")[1].split("_")[0];// ��ϢID
		var content = returnVal.split("^")[1].split("_")[1];// ��Ϣԭ��
		// �ȼ�������Ϣ�Ƿ��Ѿ�����
		if ($("#keyWrdmsgId" + id).length == 0) {
			var tr = $("<tr></tr>");
			var iptKeyWrd = $("<input type='text'/>").attr("name", "keyWrd")
					.attr("id", "keyWrdId" + id);
			var td1 = $("<td></td>").append(iptKeyWrd);
			var hidMsgId = $("<input type='hidden' />").attr("name",
					"keyWrdmsgId").attr("id", "keyWrdmsgId" + id).val(id);
			var td2 = $("<td></td>").html(content).append(hidMsgId);
			var td3 = $("<td></td>");
			var btn = $("<input type='button' value='ɾ��'>").bind('click',
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
			alert("��ѡ�����󶨵���Ϣģ��!");
			return;
		} else if (selCount > 1) {
			alert("���ܶ�ѡ!");
			return;
		} else {
			retValMyDialog(strCheck);
		}
	}
}

function removeOldKeyWords() {
	// ��ɾ��(�Ӿ��ϵ�ɾ��)
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
				var td2 = $("<td></td>").html(obj1["WAM_DESCRIPTION"]);//����
				var td3 = $("<td></td>").html(obj1["WAM_PICURL"]);//ͼƬ����
				var td4 = $("<td></td>").html(obj1["WAM_URL"]);//��ת����
				$(tr).append(td1).append(td2).append(td3).append(td4);
				$(".newsMsgTh").after(tr);
			},
			error:function(){
				alert("����");
			}
		});
	}
}
function addNewsMsgSubmit()
{
	var myForm = document.forms[0];
	myForm.submit();
}