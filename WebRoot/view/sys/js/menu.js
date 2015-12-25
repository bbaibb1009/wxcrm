function getParentMenu(level) {
	var pmenu = document.getElementById("pmenu");
	if (level == "1") {
		pmenu.style.display = "none";
	} else {
		loadMenuTree(path + "/menu/getParentMenuTree/" + level + "/-1");
		pmenu.style.display = "";
	}
}

function addMenuSubmit() {
	var myform = document.forms[0];
	var zTree;
	var zNodes;
	if (myform.wmeName.value.trim().length == 0) {
		alert("请输入菜单名称");
		myform.wmeName.focus();
		return;
	}
	if (myform.wmeLevel.value != "1") {
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zNodes = zTree.getCheckedNodes(true);
		if (zNodes.length == 0) {
			alert("请选择上级菜单");
			return;
		}
	}
	if (myform.wmeOrder.value.trim().length == 0) {
		alert("请输入菜单顺序");
		myform.wmeOrder.focus();
		return;
	}
	if (!myform.wmeOrder.value.trim().isNumber()) {
		alert("请输入正确的菜单顺序");
		myform.wmeOrder.focus();
		return;
	}
	if (myform.wmeDesc.value.trim().length > 200) {
		alert("备注超长，最多200字");
		myform.wmeDesc.focus();
		return;
	}

	myform.wmeName.value = myform.wmeName.value.trim();
	myform.wmeUrl.value = myform.wmeUrl.value.trim();
	myform.wmeOrder.value = myform.wmeOrder.value.trim();
	myform.wmeDesc.value = myform.wmeDesc.value.trim();
	if (myform.wmeLevel.value == "1") {
		myform.wmeParentId.value = 0;
	} else {
		myform.wmeParentId.value = zNodes[0].id;
	}

	myform.submit();
}

function loadMenuTree(url) {
	$.post(url, function(result) {
				var zNodes = result;
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			});
}