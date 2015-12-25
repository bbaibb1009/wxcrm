
// 分页查询提交
function querySubmit() {
	if (!chkPagePara()) {
		return;
	}
	var currentPage = document.getElementById("currentPage");
	if (currentPage != null) {
		currentPage.value = 1;
	}
	document.forms[0].submit();
}

// 分页跳转，默认提交页面上的第一个表单
function goPage(currentPage) {
	if (!chkPagePara()) {
		return;
	}
	document.getElementById("currentPage").value = currentPage;
	document.forms[0].submit();
}

// 验证分页参数
function chkPagePara() {
	var currentPage = document.getElementById("currentPage");
	var pageSize = document.getElementById("pageSize");
	if (currentPage != null && !currentPage.value.isNumber()) {
		alert("请输入正确的页码");
		currentPage.focus();

		return false;
	}
	if (pageSize != null && !pageSize.value.isNumber()) {
		alert("请输入正确的分页显示条数");
		pageSize.focus();

		return false;
	}

	return true;
}

// 全选
function selectAll(chkName) {
	var chkboxAry = document.getElementsByName(chkName);
	if (chkboxAry.length > 0) {
		for (var i = 0; i < chkboxAry.length; i++) {
			if (!chkboxAry[i].disabled && !chkboxAry[i].checked) {
				chkboxAry[i].checked = true;
			}
		}
	}
}

// 反选
function selectFan(chkName) {
	var chkboxAry = document.getElementsByName(chkName);
	if (chkboxAry.length > 0) {
		for (var i = 0; i < chkboxAry.length; i++) {
			if (!chkboxAry[i].disabled) {
				if (chkboxAry[i].checked) {
					chkboxAry[i].checked = false;
				} else {
					chkboxAry[i].checked = true;
				}
			}
		}
	}
}

function chkall(chked, chkName) {
	var chkboxAry = document.getElementsByName(chkName);
	if (chkboxAry.length > 0) {
		for (var i = 0; i < chkboxAry.length; i++) {
			if (!chkboxAry[i].disabled) {
				chkboxAry[i].checked = chked;
			}
		}
	}
}

// 删除操作复选框判断
function delChk(chkName, action) {
	var chkboxAry = document.getElementsByName(chkName);
	if (chkboxAry.length > 0) {
		var selCount = 0;
		for (var i = 0; i < chkboxAry.length; i++) {
			if (chkboxAry[i].checked) {
				selCount++;
			}
		}
		if (selCount == 0) {
			alert("请选择您要删除的数据");
		} else {
			if (chkPagePara()
					&& window.confirm("您确定要删除这" + selCount + "条数据吗？此操作不可恢复")) {
				document.forms[0].action = action;
				document.forms[0].submit();
			}
		}
	}
}

function toUpd(idName, idValue, action) {
	if (chkPagePara()) {
		document.getElementById(idName).value = idValue;
		document.forms[0].action = action;
		document.forms[0].submit();
	}
}

function toDel(idName, idValue, action) {
	if (chkPagePara() && window.confirm("您确定要删除这1条数据吗？此操作不可恢复")) {
		document.getElementById(idName).value = idValue;
		document.forms[0].action = action;
		document.forms[0].submit();
	}
}

function toReturn(action) {
	document.forms[0].action = action;
	document.forms[0].submit();
}
