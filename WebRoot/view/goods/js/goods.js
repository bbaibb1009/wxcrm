function addGoodsFenlei() {
	var myForm = document.forms[0];
	myForm.submit();
}
function addGoods() {
	var myForm = document.forms[0];
	if ($("#wgsName").val() == null || $("#wgsName").val().length == 0) {
		alert("��Ʒ���Ʋ���Ϊ��!");
		return;
	}
	if ($("#wgsBzPrice").val() == null || $("#wgsBzPrice").val().length == 0) {
		alert("��׼���ۼ۲���Ϊ��!");
		return;
	}
	if ($("#wgsLsPrice").val() == null || $("#wgsLsPrice").val().length == 0) {
		alert("���ۼ۲���Ϊ��!");
		return;
	}
	myForm.submit();
}

function addInGoods() {
	var myForm = document.forms[0];
	if ($("#wgiInTime").val() == null || $("#wgiInTime").val().length == 0) {
		alert("���ʱ�䲻��Ϊ��!");
		return;
	}
	if ($("#wgiInNum").val() == null || !$("#wgiInNum").val().isFloat()
			|| parseInt($("#wgiInNum").val()) <= 0) {
		alert("�����������С��0!");
		return;
	}
	if ($("#wgiInPrice").val() == null || !$("#wgiInPrice").val().isFloat()) {
		alert("������д����!");
		return;
	}
	myForm.submit();
}
