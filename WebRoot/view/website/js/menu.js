function getParentMenu(level) {
	var pmenu = document.getElementById("pmenu");
	if (level == "1") {
		pmenu.style.display = "none";
	} else {
		loadMenuTree(path + "/shopmenu/getShopParentMenuTree/" + level + "/-1");
		pmenu.style.display = "";
	}
}

function addMenuSubmit() {
	var myform = document.forms[0];
	var zTree;
	var zNodes;
	if (myform.wsmName.value.trim().length == 0) {
		alert("������˵�����");
		myform.wsmName.focus();
		return;
	}
	if (myform.wsmLevel.value != "1") {
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zNodes = zTree.getCheckedNodes(true);
		if (zNodes.length == 0) {
			alert("��ѡ���ϼ��˵�");
			return;
		}
	}
	if (myform.wsmOrder.value.trim().length == 0) {
		alert("������˵�˳��");
		myform.wsmOrder.focus();
		return;
	}
	if (!myform.wsmOrder.value.trim().isNumber()) {
		alert("��������ȷ�Ĳ˵�˳��");
		myform.wsmOrder.focus();
		return;
	}
	if (myform.wsmDesc.value.trim().length > 200) {
		alert("��ע���������200��");
		myform.wsmDesc.focus();
		return;
	}

	myform.wsmName.value = myform.wsmName.value.trim();
	myform.wsmUrl.value = myform.wsmUrl.value.trim();
	myform.wsmOrder.value = myform.wsmOrder.value.trim();
	myform.wsmDesc.value = myform.wsmDesc.value.trim();
	if (myform.wsmLevel.value == "1") {
		myform.wsmParentId.value = 0;
	} else {
		myform.wsmParentId.value = zNodes[0].id;
	}

	myform.submit();
}




function loadMenuTree(url) {
	$.post(url, function(result) {
				var zNodes = result;
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
}