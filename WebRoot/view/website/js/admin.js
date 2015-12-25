var resetPwdFlag = 0;
function resetPwd(obj) {
	var pwdTr = document.getElementById("pwdTr");
	var pwd2Tr = document.getElementById("pwd2Tr");
	if (resetPwdFlag == 0) {
		obj.innerHTML = "取消";
		pwdTr.style.display = "";
		pwd2Tr.style.display = "";
		resetPwdFlag = 1;
	} else {
		obj.innerHTML = "重设";
		pwdTr.style.display = "none";
		pwd2Tr.style.display = "none";
		resetPwdFlag = 0;
	}
	document.getElementById("wsaPwd").value = "";
	document.getElementById("wsaPwd2").value = "";
}

function addAdminSubmit(typ) {
	var myform = document.forms[0];
	if (myform.wsaUsername.value.trim().length == 0) {
		alert("请输入用户名");
		myform.wsaUsername.focus();
		return;
	}
	if (!isUsername(myform.wsaUsername.value.trim())) {
		// 只能是字母上
		alert("请输入正确的用户名");
		myform.wsaUsername.focus();
		return;
	}
	if ((typ == "add") || (typ == "upd" && resetPwdFlag == 1)) {
		if (myform.wsaPwd.value.trim().length == 0) {
			alert("请输入密码");
			myform.wsaPwd.focus();
			return;
		}
		if (myform.wsaPwd2.value.trim().length == 0) {
			alert("请输入密码确认");
			myform.wsaPwd2.focus();
			return;
		}
		if (myform.wsaPwd.value.trim() != myform.wsaPwd2.value.trim()) {
			alert("两次密码不相同");
			myform.wsaPwd2.focus();
			return;
		}
	}
	if (myform.wsaName.value.trim().length == 0) {
		alert("请输入姓名");
		myform.wsaName.focus();
		return;
	}

	// var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	// var zNodes = zTree.getCheckedNodes(true);
	// zNodes.length == 0 &&
	if (myform.roleIds.length == 0) {
		alert("请设置权限");
		return;
	}

	chkUsernameUnique();
}

function chkUsernameUnique() {
	var wsaUsername = document.getElementById("wsaUsername");
	$.post(	path + "/shopadmin/chkUsernameUnique/" + adminIdStr + "/"
					+ wsaUsername.value.trim(), function(result) {
				if (!result.username) {
					alert("此用户名已存在");
					username.focus();
					return;
				}
				addAdminSubmitDo();
			});
}

function addAdminSubmitDo() {
	var myform = document.forms[0];
	// var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	// var zNodes = zTree.getCheckedNodes(true);

	myform.wsaUsername.value = myform.wsaUsername.value.trim();
	myform.wsaPwd.value = myform.wsaPwd.value.trim();
	myform.wsaName.value = myform.wsaName.value.trim();
	// myform.areaCode.value = myform.areaCode.value.trim();
	// myform.phone.value = myform.phone.value.trim();
	// myform.phoneFen.value = myform.phoneFen.value.trim();
	// myform.mobile.value = myform.mobile.value.trim();
	// myform.qq.value = myform.qq.value.trim();
	// myform.email.value = myform.email.value.trim();
	// myform.address.value = myform.address.value.trim();
	// myform.salaryNo.value = myform.salaryNo.value.trim();

	for (var i = 0; i < myform.roleIds.length; i++) {
		myform.roleIds[i].selected = true;
	}

	var menuIdStr = "";
	// for( var i = 0; i < zNodes.length; i++ )
	// {
	// menuIdStr += "," + zNodes[i].id;
	// }
	if (menuIdStr.length > 0) {
		menuIdStr = menuIdStr.substring(1, menuIdStr.length);
	}
	myform.menuIds.value = menuIdStr;

	// if( myform.deptId.value.length == 0 )
	// {
	// myform.deptId.value = "-1";
	// }
	//	
	// if( myform.khXuSel.value == "-3" )
	// {
	// myform.khXu.value = myform.khXu.value.trim();
	// }
	// else
	// {
	// myform.khXu.value = myform.khXuSel.value;
	// }
	//	
	// if( myform.khXinSel.value == "-3" )
	// {
	// myform.khXin.value = myform.khXin.value.trim();
	// }
	// else
	// {
	// myform.khXin.value = myform.khXinSel.value;
	// }
	//	
	// if( myform.khNotSel.value == "-3" )
	// {
	// myform.khNot.value = myform.khNot.value.trim();
	// }
	// else
	// {
	// myform.khNot.value = myform.khNotSel.value;
	// }

	myform.submit();
}

// 更改角色
function changeRole(roleIdFrom, roleIdTo, adminId) {
	for (var i = 0; i < roleIdFrom.length; i++) {
		if (roleIdFrom[i].selected) {
			var newOpt = document.createElement("option");
			newOpt.value = roleIdFrom[i].value;
			newOpt.innerHTML = roleIdFrom[i].innerHTML;
			roleIdTo.appendChild(newOpt);
			roleIdFrom.removeChild(roleIdFrom[i]);
			i--;
		}
	}

	// loadMenuTreeForAdmin(adminId);
}