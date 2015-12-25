function getAdminNameList(adminName) {
	$.post(path + "/shopadmin/getShopAdminNameList", function(result) {
				$("#" + adminName).autocomplete({
							lookup : result,
							onSelect : function(suggestion) {
								selAdminName(suggestion);
							}
						});
			});
}

function getAdminNameList2(adminName) {
	$.post(path + "/shopadmin/getShopAdminNameList", function(result) {
				$("#" + adminName).autocomplete({
							lookup : result,
							onSelect : function(suggestion) {
								selAdminName2(suggestion, adminName);
							}
						});
			});
}