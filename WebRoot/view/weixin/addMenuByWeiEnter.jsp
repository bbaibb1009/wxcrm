<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
  <head>
    <title>菜单添加</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/frame/images/admin.css?v=${applicationScope.sysStartUpTime}"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/view/css/jquery.autocomplete.css?v=${applicationScope.sysStartUpTime}"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/jquery-1.8.2.min.js?v=${applicationScope.sysStartUpTime}"></script>
    <jsp:include page="/view/common/includeFiles.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/js/basic.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/tabList.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/js/json2.js?v=${applicationScope.sysStartUpTime}"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/weixin/js/weixin_menu.js?v=${applicationScope.sysStartUpTime}"></script>
	
	<script type="text/javascript">
	var path = "${pageContext.request.contextPath}";
	var alertMsg = "${alertMsg}";
	$(document).ready(function(){
		if( alertMsg.length>0)
		{
			alert(alertMsg);
		}
		$(".trSubBtn").hide();
		$("select[name^='selType']").bind("change",function(){
			showSubBtns($(this));
		});
	});
	</script>
  </head>
  <body>
  	<div class="wrapper">
  	<jsp:include page="/view/sys/menuFrame.jsp"></jsp:include>
  	<div class="admin_right" id="adminRight">
  	<div class="content">
  	<f:form action="${pageContext.request.contextPath}/weixinmenu/addMenuByEnter" onsubmit="return false;">
  		<f:hidden path="wmuWecId"/>
  		<table cellpadding="0" cellspacing="0" border="1" width="100%">
	    	<tr class ="trNotSubBtn"><th width="15%">APPID</th>	<td width="85%" style="text-align:left;"><f:input path="wmuAppId" /></td></tr>
	    	<tr class ="trNotSubBtn"><th width="15%">appSecret</th>	<td width="85%" style="text-align:left;"><f:password path="wmuAppSecret" /></td></tr>
	    	<tr class ="trNotSubBtn"><th width="15%">JSON串</th>	<td width="85%" style="text-align:left;"><f:input path="wmuJson" /></td></tr>
	    	<tr class ="trNotSubBtn"><th width="15%">备注</th>	<td width="85%" style="text-align:left;"><f:input path="wmuDesc" /></td></tr>
	    	<tr class ="trNotSubBtn">
	    		<td width="15%" colspan=2 style="text-align:left;">
	    			<input type="checkbox" name="checkBtn_1" id="checkBtn_1" value="1" onclick="javascript:needBtn(this,1);"/>按钮1_左
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">名称</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptName_1"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">类型</th>
	    		<td width="85%" style="text-align:left;">
	    		<select name="selType_1" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">key</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptKey_1"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn" >
	    		<th width="15%">url</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptUrl_1"  value="" /></td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮1</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_1" id="checkSubChk_1_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_1"  id="iptSubName_1_1" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_1" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮2</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox"  name="checkSubChk_1"  id="checkSubChk_1_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_1" id="iptSubName_1_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_2" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮3</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_1" id="checkSubChk_1_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_3" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮4</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_1" id="checkSubbtn_1_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_4" value="" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮5</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_1" id="checkSubbtn_1_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_1" id="iptSubName_1_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_1" id="selSubType_1_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_1" id="subIptKey_1_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_1" id="subIptUrl_1_5" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<td width="15%" colspan=2 style="text-align:left;">
	    			<input type="checkbox" name="checkBtn_2" id="checkBtn_2" value="2"  onclick="javascript:needBtn(this,2);"/>按钮2_中
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">名称</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptName_2"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">类型</th>
	    		<td width="85%" style="text-align:left;">
	    		<select name="selType_2" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">key</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptKey_2"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn" >
	    		<th width="15%">url</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptUrl_2"  value="" /></td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮1</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_2" id="checkSubChk_2_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_2"  id="iptSubName_2_1" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_1" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮2</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox"  name="checkSubChk_2"  id="checkSubChk_2_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_2" id="iptSubName_2_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_2" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮3</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_2" id="checkSubChk_2_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_3" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮4</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_2" id="checkSubbtn_2_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_4" value="" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮5</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_2" id="checkSubbtn_2_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_2" id="iptSubName_2_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_2" id="selSubType_2_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_2" id="subIptKey_2_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_2" id="subIptUrl_2_5" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<td width="15%" colspan=2 style="text-align:left;">
	    			<input type="checkbox" name="checkBtn_3" id="checkBtn_3" value="3"  onclick="javascript:needBtn(this,3);"/>按钮3_右
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">名称</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptName_3"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">类型</th>
	    		<td width="85%" style="text-align:left;">
	    		<select name="selType_3" >
	    			<option value="view">跳转URL</option>
	    			<option value="click">点击推事件</option>
	    			<option value="">含子节点</option>
	    		</select>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn">
	    		<th width="15%">key</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptKey_3"  value="" /></td>
	    	</tr>
	    	<tr class ="trNotSubBtn" >
	    		<th width="15%">url</th>
	    		<td width="85%" style="text-align:left;"><input type="text" name="iptUrl_3"  value="" /></td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮1</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_3" id="checkSubChk_3_1" onclick="javascript:needSubbtn(this,1);" />
	    			名称:<input type="text" name="iptSubName_3"  id="iptSubName_3_1" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_1" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_1" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_1" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮2</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox"  name="checkSubChk_3"  id="checkSubChk_3_2" onclick="javascript:needSubbtn(this,2);"/>
	    			名称:
	    			<input type="text" name="iptSubName_3" id="iptSubName_3_2" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_2" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_2" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_2" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮3</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubChk_3" id="checkSubChk_3_3" onclick="javascript:needSubbtn(this,3);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_3" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_3" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_3" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_3" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮4</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_3" id="checkSubbtn_3_4" onclick="javascript:needSubbtn(this,4);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_4" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_4" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_4" value="" disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_4" value="" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trSubBtn">
	    		<th width="15%">小按钮5</th>
	    		<td width="85%" style="text-align:left;">
	    			<input type="checkbox" name="checkSubbtn_3" id="checkSubbtn_3_5" onclick="javascript:needSubbtn(this,5);"/>
	    			名称:<input type="text" name="iptSubName_3" id="iptSubName_3_5" value="" disabled="disabled"/> 
	    			类型:
	    			<select name="selSubType_3" id="selSubType_3_5" disabled="disabled">
	    				<option value="">-请选择-</option>
	    				<option value="view">跳转URL</option>
	    				<option value="click">点击推事件</option>
	    			</select>
	    			key:<input type="text" name="subIptKey_3" id="subIptKey_3_5" value=""  disabled="disabled"/>
	    			url:<input type="text" name="subIptUrl_3" id="subIptUrl_3_5" value=""  disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr class ="trNotSubBtn"><td colspan=2><input type="button" value="保存" onclick="javascript:addMenu()"/></td></tr>
    	</table>
    	
    </f:form>
    </div>
    </div>
    <jsp:include page="/view/sys/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
