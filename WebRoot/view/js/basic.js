
// js中删除字符串两边的空格
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
// 判断字符串是否是数字
String.prototype.isNumber = function() {
	return (this.search(/^\d+$/g) == 0);
}
// 判断字符串是否是浮点类型
String.prototype.isFloat = function() {
	var patrn = /^-?\d+\.{0,1}\d{0,}$/;
	if (!patrn.exec(this))
		return false;
	else
		return true;
}

function isUsername(str) {
	var patrn = /^\w+$/;

	return patrn.test(str);
}

function isEmail(str) {
	var patrn = /^([0-9a-z_\.-]+)@([0-9a-z\.-]+)\.([a-z]{2,6})$/;

	return patrn.test(str);
}

function isEnglish(str) {
	var patrn = /[A-Za-z]+$/;

	return patrn.test(str);
}

function isPhone(str) {
	var patrn = /^[+]{0,1}(\d){1,4}[ ]{0,1}([-]{0,1}((\d)|[ ]){1,12})+$/;

	return patrn.test(str);
}

function showAdminMenu2(menuId1, obj) {
	var adminTop = document.getElementById("adminTop")
			.getElementsByTagName("a");
	for (var i = 0; i < adminTop.length; i++) {
		adminTop[i].className = "";
	}
	obj.className = "menu1";

	var adminLeft = $("#adminLeft").children();
	for (var i = 0; i < adminLeft.length; i++) {
		adminLeft[i].style.display = "none";
	}

	var ulAry = document.getElementById(menuId1).getElementsByTagName("ul");
	for (var i = 0; i < ulAry.length; i++) {
		ulAry[i].style.display = "none";
	}

	document.getElementById(menuId1).style.display = "";

	if (!leftMenuShow) {
		showLeftMenu();
	}
}

function showAdminMenu3(menuId1, menuId2) {
	var ulAry = document.getElementById(menuId1).getElementsByTagName("ul");
	for (var i = 0; i < ulAry.length; i++) {
		ulAry[i].style.display = "none";
	}
	document.getElementById(menuId2).style.display = "";
}

function goUrl(adminMenuId3, url) {
	var adminMenuId1 = -1;
	var adminMenuId2 = -1;

	var adminLeft = $("#adminLeft").children();
	for (var i = 0; i < adminLeft.length; i++) {
		if (adminLeft[i].style.display == "") {
			adminMenuId1 = adminLeft[i].id;
		}
	}

	var ulAry = document.getElementById(adminMenuId1)
			.getElementsByTagName("ul");
	for (var i = 0; i < ulAry.length; i++) {
		if (ulAry[i].style.display == "") {
			adminMenuId2 = ulAry[i].id;
		}
	}

	window.location.href = path + "/admin/goUrl/" + adminMenuId1 + "/"
			+ adminMenuId2 + "/" + adminMenuId3 + "/" + url.replace(/\//g, "|");
}

function divAutoHeight() {
	var minHeight = document.documentElement.clientHeight - 125;
	var adminLeftHeight = $("#adminLeft").height();
	var adminRightHeight = $("#adminRight").height();
	var divHeight = adminLeftHeight > adminRightHeight
			? adminLeftHeight
			: adminRightHeight;
	divHeight = divHeight > minHeight ? divHeight : minHeight;
	$("#adminLeft").css("min-height", divHeight);
	$("#adminCenter").css("min-height", divHeight);
	$("#adminCenter").css("line-height", divHeight + "px");
	$("#adminRight").css("min-height", divHeight);
}

// 参数中包含.的，必须以/结尾
function specialEncode(str) {
	str = encodeURI(encodeURI(str));
	str = str.replace(/\#/g, "%2523");
	str = str.replace(/\;/g, "%253B");
	str = str.replace(/\?/g, "%253F");
	str = str.replace(/\+/g, "%252B");
	str = str.replace(/\//g, "%252F");

	return str;
}

function showMyDialog(sURL, obj, dWidth, dHeight) {
	if ($.browser.webkit && parseInt($.browser.version.split(".")[0]) > 36) {
		alert("您的浏览器不支持此功能，请使用别的浏览器");
		return;
	}
	sURL += "?timestamp=" + new Date().getTime();
	var dLeft = (document.documentElement.clientWidth - dWidth + 20) / 2;
	var dTop = (document.documentElement.clientHeight - dHeight + 155) / 2;

	var retVal = window.showModalDialog(sURL, obj, "dialogWidth:" + dWidth
					+ "px;dialogHeight:" + dHeight + "px;dialogLeft:" + dLeft
					+ "px;dialogTop:" + dTop + "px;");
	// google
	if ($.browser.webkit) {
		retVal = window.returnValue;
	}
	return retVal;
}

function retValMyDialog(obj) {
	// google
	if ($.browser.webkit) {
		window.opener.returnValue = obj;
	} else {
		window.returnValue = obj;
	}

	window.close();
}

function getParentWindow() {
	var parentWindow;
	// google
	if ($.browser.webkit) {
		parentWindow = window.opener;
	} else {
		parentWindow = window.dialogArguments;
	}

	return parentWindow;
}

function sysTimeout() {
	alert("系统超时，请重新登录");
	var parentWindow = getParentWindow();
	if (parentWindow == undefined) {
		parentWindow = window;
	} else {
		window.close();
	}
	parentWindow.close();
}

if (typeof jQuery != 'undefined') {
	$.ajaxSetup({
				complete : function(xhr, status) {
					if (xhr.status == 999) {
						sysTimeout();
					}
				}
			});
}

var leftMenuShow = true;

function showLeftMenu() {
	if (leftMenuShow) {
		$("#ar_l").show();
		$("#ar_r").hide();
		$("#adminLeft").hide();
		$("#adminRight").css("margin-left", "8px");
	} else {
		$("#ar_l").hide();
		$("#ar_r").show();
		$("#adminLeft").show();
		$("#adminRight").css("margin-left", "208px");
	}
	leftMenuShow = !leftMenuShow;
}
