function addGoodsFenlei() {
	var myForm = document.forms[0];
	myForm.submit();
}
function addGoods() {
	var myForm = document.forms[0];
	if ($("#wgsName").val() == null || $("#wgsName").val().length == 0) {
		alert("商品名称不能为空!");
		return;
	}
	if ($("#wgsBzPrice").val() == null || $("#wgsBzPrice").val().length == 0) {
		alert("标准零售价不能为空!");
		return;
	}
	if ($("#wgsLsPrice").val() == null || $("#wgsLsPrice").val().length == 0) {
		alert("零售价不能为空!");
		return;
	}
	myForm.submit();
}

function addInGoods() {
	var myForm = document.forms[0];
	if ($("#wgiInTime").val() == null || $("#wgiInTime").val().length == 0) {
		alert("入库时间不能为空!");
		return;
	}
	if ($("#wgiInNum").val() == null || !$("#wgiInNum").val().isFloat()
			|| parseInt($("#wgiInNum").val()) <= 0) {
		alert("入库数量不能小于0!");
		return;
	}
	if ($("#wgiInPrice").val() == null || !$("#wgiInPrice").val().isFloat()) {
		alert("进价填写错误!");
		return;
	}
	myForm.submit();
}
