<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<style type="text/css">
	
	ul {
		padding:5px 2px 5px 2px;
	}
	li {
		display:inline;
		padding-right:5px;
		font-size:12pt;
	}
	li span {
		color:#00e;
		font-weight:bold;
		font-size:12pt;
	}
	h3
	{
		background-color:#eee;
		padding:5px 2px 8px 2px;
	}
	
	table{
		
		width:100%;
	}
	th{
		font-size:1em;
	
	}
	.infoDiv{
		width:100%;
	
	}
</style>
	<div class="content">
			微信CRM客户端
		<table cellpadding="0" cellspacing="0" border="1" width="100%">
    	 <tr><th width="3%">商家名称:</th><td>${mysite.wcsWebSiteName}</td></tr>
    	 <tr><th width="8%">微信名称:</th><td>${mysite.wcsAppName}</td></tr>
    	 <tr><th width="3%">查看会员:</th><td><a href="${pageContext.request.contextPath}/goods/queryGoodsFenlei/${mysite.wcsId}">点击</a></td></tr>
    	 <tr><th width="8%">查看商品分类:</th><td><a href="${pageContext.request.contextPath}/mysite/queryGoodsFenlei/${mysite.wcsId}">点击</a></td></tr>
    	 <tr><th width="5%">查看所有商品:</th><td><a href="${pageContext.request.contextPath}/mysite/queryGoods/${mysite.wcsId}">点击</a></td></tr>
	   	</table>
	</div>
