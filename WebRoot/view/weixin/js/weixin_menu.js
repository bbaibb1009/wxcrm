function addSubButton() {
	var $this = arguments[0];
	var index = arguments[1];
	var trThis = $($this).parent().parent();

	var tr = $("<tr></tr>");
	var input1 = $("<input type='text' />").attr("name", "subbtn" + index);
	var input2 = $("<input type='text' />").attr("name", "subbtn" + index);
	var td = $("<td></td>").append(input1).append(input2).append()

}

function needSubbtn() {
	var $this = arguments[0];
	var index = arguments[1];
	if (typeof($($this).attr("checked")) != "undefined") {
		enableInput($this, index);
	} else {
		disableInput($this, index);
	}

}

function enableInput() {
	var chkBox = arguments[0];
	var index = arguments[1];
	$(chkBox).parent().children("input[type='text'],select")
			.removeAttr("disabled");
}

function disableInput() {
	var chkBox = arguments[0];
	var index = arguments[1];
	$(chkBox).parent().children("input[type='text'],select").attr("disabled",
			"disabled");
}

function showSubBtns() {
	var $this = arguments[0];
	var val = $($this).val();
	var childrenTr = $($this).parent().parent().next().next()
			.nextUntil('.trNotSubBtn');
	if (val == '') {
		$(childrenTr).show();
	} else {
		$(childrenTr).hide();
		$(childrenTr).children().children("input[type='checkbox']")
				.removeAttr("checked");
		$(childrenTr).children().children("input[type='text'],select").attr(
				"disabled", "disabled");
	}

}

function getMenuJson() {
	/*
	 * { "button": [ { "type": "view", "name": "ʯ��ͨ", "url": "", "sub_button": [
	 * {"type": "view","name": "����","url":
	 * "http://info.oilchem.net/lzsht/index.html","sub_button": []}, {"type":
	 * "view","name": "����","url":
	 * "http://mp.weixin.qq.com/s?__biz=MzA3MDU3OTgzMA==&mid=201343703&idx=1&sn=6f9d66051dbf12b91de5291774fa30f4#rd","sub_button":[]} ] }, {
	 * "type": "view", "name": "����", "url":
	 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2ba0ce17184b93b7&redirect_uri=http%3A%2F%2Finfo.oilchem.net%2Fweixin%2FtoZhongjianYeMian&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect",
	 * "sub_button":[] }, { "type": "view", "name": "��Ѷͨ", "url":
	 * "http://img.oilchem.net/2014/lzdxt/", "sub_button":[] } ] }
	 */

	//
	var menu = {};
	var needBtn = $("input[name^='checkBtn']:checked");
	if ($(needBtn).length > 0) {
		var button = [];
		$(needBtn).each(function(index) {
			var chkBtn = $(this);
			var chkIndex = $(this).val();
			var complexBtn = {};
			var complexType = $("select[name='selType_" + chkIndex + "']")
					.val();
			var complexName = $("input[name='iptName_" + chkIndex + "']").val();
			complexBtn.name = complexName;
			if (complexType == '') {
				var checkSubChks = $("input[name='checkSubChk_" + chkIndex
						+ "']:checked");
				if (checkSubChks.length > 0) {
					complexBtn.url = '';
					var sub_button = [];
					$(checkSubChks).each(function(index) {
								var $this = $(checkSubChks)[index];
								var subbtn = {};
								var name = $($this).next().val();
								var type = $($this).next().next().val();
								var key = $($this).next().next().next().val();
								var url = $($this).next().next().next().next()
										.val();
								var subbtn_subbtn = [];
								subbtn.name = name;
								subbtn.type = type;
								if (type == 'view') {
									subbtn.url = url;
								} else if (type == 'click') {
									subbtn.key = key;
								}
								subbtn.sub_button = [];
								sub_button.push(subbtn);
							});
					complexBtn.sub_button = sub_button;
				} else {
					alert('�����ӽڵ�İ�ť�鲻��Ϊ��');
					button = [];
					return;
				}
			} else if (complexType == 'view') {
				complexBtn.type = complexType;
				complexBtn.url = $("input[name='iptUrl_" + chkIndex + "']")
						.val();
				complexBtn.sub_button = [];
			} else if (complexType == 'click') {
				complexBtn.type = complexType;
				complexBtn.key = $("input[name='iptKey_" + chkIndex + "']")
						.val();
				complexBtn.sub_button = [];
			} else {
				complexBtn.type = complexType;
				complexBtn.sub_button = [];
			}
			button.push(complexBtn);

		});
		menu.button = button;
		// alert(JSON.stringify(menu));
		return JSON.stringify(menu);
	} else {
		alert("δ�����κΰ�ť");
		return "";
	}

	//
}

function needBtn() {
	var $this = arguments[0];
	var index = arguments[1];
	if (typeof($($this).attr("checked")) == "undefined") {
		// δѡ�� ����������������� ȫ��disabled
		// �������� ���� url key disabled
		$("input[name='iptName_" + index + "']").attr("disabled", "disabled");
		$("select[name='selType_" + index + "']").attr("disabled", "disabled");
		$("input[name='iptKey_" + index + "']").attr("disabled", "disabled");
		$("input[name='iptUrl_" + index + "']").attr("disabled", "disabled");
		var childrenTr = $($this).parent().parent().next().next().next().next()
				.nextUntil('.trNotSubBtn');
		$(childrenTr).children().children("input[type='checkbox']")
				.removeAttr("checked");
		$(childrenTr).children().children("input[type='text'],select").attr(
				"disabled", "disabled");
	} else {
		$("input[name='iptName_" + index + "']").removeAttr("disabled");
		$("select[name='selType_" + index + "']").removeAttr("disabled");
		$("input[name='iptKey_" + index + "']").removeAttr("disabled");
		$("input[name='iptUrl_" + index + "']").removeAttr("disabled");
	}

}

function initSubButton() {
	var menuStr = arguments[0];
	var menuObj = eval(menuStr);
	var button = menuObj["button"];
	$(button).each(function(index) {
		var butIndex = button[index];
		var count = parseInt(index) + parseInt('1');
		var checkBtn = $("input[name='checkBtn_" + count + "']").attr(
				'checked', 'checked');
		needSubbtn(checkBtn, count);
		var iptName = $("input[name='iptName_" + count + "']");
		$(iptName).val(butIndex.name);
		var selType = $("select[name='selType_" + count + "']");
		$(selType).val(butIndex.type);
		showSubBtns(selType);
		initSubBtns(count, butIndex.sub_button);
		var iptKey = $("input[name='iptKey_" + count + "']");
		$(iptKey).val(butIndex.key);
		var iptUrl = $("input[name='iptUrl_" + count + "']");
		$(iptUrl).val(butIndex.url);
	});
}

function initSubBtns() {

	var parent_count = arguments[0];
	var sub_button = arguments[1];
	$(sub_button).each(function(index) {

		var subbtn = $(sub_button)[index];
		var count = parseInt(index) + parseInt('1');
		var name = subbtn.name;
		var type = typeof(subbtn.type) == 'undefined' ? '' : subbtn.type;
		var url = subbtn.url;
		var key = subbtn.key;
		$("#checkSubChk_" + parent_count + "_" + count).attr("checked",
				"checked").removeAttr("disabled");
		$("#iptSubName_" + parent_count + "_" + count).val(name)
				.removeAttr("disabled");
		$("#selSubType_" + parent_count + "_" + count).val(type)
				.removeAttr("disabled");
		$("#subIptUrl_" + parent_count + "_" + count).val(url)
				.removeAttr("disabled");
		$("#subIptKey_" + parent_count + "_" + count).val(key)
				.removeAttr("disabled");

	});
}

function addMenu() {
	// appId ����Ϊ��
	var appId = $("#wmuAppId").val();
	if (appId.length == 0) {
		alert("appId����Ϊ��!");
		return;
	}

	// secret����Ϊ��
	var secret = $("#wmuAppSecret").val();
	if (secret.length == 0) {
		alert("secret����Ϊ��!");
		return;
	}

	// json����ƴ ���ǲ���Ϊ��
	var json = getMenuJson();
	if (json.length > 0) {
		var myForm = document.forms[0];
		myForm.wmuJson.value = json;
		myForm.submit();
	} else {
		alert("��ť��Ϣ��ȫ�����±༭����!");
		return;
	}
}
