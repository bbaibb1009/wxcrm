package com.wxcrm.util;

import javax.servlet.http.HttpServletRequest;

public class SysConstant 
{
	// 保存在request中的分页查询结果的访问变量
	public static final String PAGE_RESULT = "pageResult";
	
	//public static final String BLACKTEL_ALL = "BlacktelAll";
	
	// 保存在memcached中的所有菜单列表
	public static final String MENU_ALL = "menuAll";
	
	// 保存在memcached中的所有微信商家菜单列表
	public static final String SHOP_MENU_ALL = "shopmenuAll";
	
	
//	// 保存在memcached中的所有部门列表
//	public static final String DEPT_ALL = "deptAll";
	
//	// 保存在memcached中的所有产品名称列表
//	public static final String PRO_NAME_ALL = "proNameAll";
	
//	// 保存在memcached中的所有产品数据组列表
//	public static final String PRODUCT_TEMPLATE_ALL = "productTemplateAll";
//	
	// 保存在memcached中的所有管理员姓名列表
	public static final String ADMIN_NAME_ALL = "wxcrmadminNameAll";
	
	
	// 保存在memcached中的所有微信商家管理员姓名列表
	public static final String SHOP_ADMIN_NAME_ALL = "wxshopadminNameAll";
	
//	// 保存在memcached中的所有部门名称列表
//	public static final String DEPT_NAME_ALL = "deptNameAll";
//	
//	// 保存在memcached中的一级部门名称列表
//	public static final String DEPT_NAME1_ALL = "deptName1All";
	
//	// 保存在memcached中的二级部门名称列表
//	public static final String DEPT_NAME2_ALL = "deptName2All";
	
//	// 保存在memcached中的邮件属性列表
//	public static final String MAIL_CONFIG_ALL = "mailConfigAll";
//	
//	// 保存在memcached中的产品数据表列表
//	public static final String PRODUCT_DATATAB_ALL = "productDatatabAll";
	
//	// 保存在memcached中的产品数据单元列表
//	public static final String PRODUCT_UNIT_ALL = "productUnitAll";
//	
//	// 保存在memcached中的装置企业名称列表
//	public static final String COMP_NAME_ALL = "compNameAll";
	
//	// 保存在memcached中的装置类型名称列表
//	public static final String TYPE_NAME_ALL = "typeNameAll";
	
	// 保存在memcached中的IP白名单列表
	public static final String IPY_ALL = "wxcrmipyAll";
	
//	// 保存在memcached中的套餐单元名称列表
//	public static final String PACKUNIT_ALL = "packUnitAll";
	
	// 保存在session中的管理员信息
	public static final String ADMIN_INFO = "adminInfo";
	
	// 保存在session中的管理员拥有的一级菜单
	public static final String ADMIN_MENUS_LEVEL1 = "adminMenusLevel1";
	
	// 保存在session中的管理员拥有的二级菜单
	public static final String ADMIN_MENUS_LEVEL2 = "adminMenusLevel2";
	
	// 保存在session中的管理员拥有的三级菜单
	public static final String ADMIN_MENUS_LEVEL3 = "adminMenusLevel3";
	
	// 保存在session中的当前选中的一级菜单id
	public static final String ADMIN_MENU_ID1 = "adminMenuId1";
	
	// 保存在session中的当前选中的二级菜单id
	public static final String ADMIN_MENU_ID2 = "adminMenuId2";
	
	// 保存在session中的当前选中的三级菜单id
	public static final String ADMIN_MENU_ID3 = "adminMenuId3";
	
	// cookie过期时间
	public static final int COOKIE_AGE = 30 * 24 * 60 * 60;
	
	// 保存在cookie中的管理员用户名
	public static final String ADMIN_USERNAME = "wxcrmAdminUsername";
	
	// 保存在cookie中的管理员密码md5
	public static final String ADMIN_PWD_MD5 = "wxcrmAdminPwdmd5";
	
	// 保存在cookie中的管理员密码3des
	public static final String ADMIN_PWD_3DES = "wxcrmAdminPwd3des";
	
	
	
	// 保存在cookie中的管理员登录直接跳转的uri
	public static final String LOGIN_REDIRECT_URI = "wxcrmloginRedirectUri";
	
	
	
	// 保存在线程局部变量中的request对象
	public static final ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new ThreadLocal<HttpServletRequest>();
	
	// 系统启动的时间戳
	public static final String SYS_STARTUP = "wxcrmsysStartUpTime";
	
//	// 保存在memcached中信息单元名称
//	public static final String INFO_UNIT_ALL = "infoUnitAll";
//	
//	// 保存在memcached中信息单元名称
//	public static final String NEWS_TEMPLATE_ALL = "newsTemplateAll";
//	
//	// 邮件通知
//	public static final String NOTICE_MAIL = "0";
//	
//	// spark通知
//	public static final String NOTICE_SPARK = "1";
	
//	// 微信通知
//	public static final String NOTICE_WEIXIN = "2";
	
	public static final String ADMIN_INFO_COOKIE = "wxcrmadminInfoCookie";
	
//	//保存在memcached中的资讯套餐名称
//	public static final String PACK_INFO_NAME_ALL = "packInfoNameAll";
//	
//	//保存在memcached中的资讯套餐
//	public static final String PACK_INFO_ALL = "packInfoAll";
//	
//	//保存在memcached中的短信套餐名称
//	public static final String PACK_SMS_NAME_ALL = "packSmsNameAll";
//	
//	//保存在memcached中的短信套餐
//	public static final String PACK_SMS_ALL = "packSmsAll";
//	
//	//保存在memcached中的短信基础套餐
//	public static final String PACK_LEAF_SMS_NAME_ALL = "packLeafSmsAll";
//	
//	//保存在memcached中的资讯基础套餐
//	public static final String PACK_LEAF_INFO_NAME_ALL = "packLeafInfoAll";
//	
//	//保存在memcached中的短讯通套餐
//	public static final String PACK_DXT_ALL = "packDxtAll";
//	
//	//保存在memcached中的短讯通名称
//	public static final String PACK_DXT_NAME_ALL = "packDxtNameAll";
//	
//	//保存在memcached中的短讯通名称
//	public static final String PACK_LEAF_DXT_NAME_ALL = "packLeafDxtAll";
//	
//	//保存在memcached中的会议树信息
//	public static final String PACK_MEETING_ALL = "packMeetingAll";
//	
//	public static final String PACK_MEETING_NAME_ALL = "packMeetingNameAll";
//	
//	public static final String PACK_LEAF_MEETING_NAME_ALL = "packLeafMeetingAll";
//	
//	public static final String PACK_WEI_ALL = "packWeiAll";
//	
//	public static final String PACK_WEI_NAME_ALL = "packWeiNameAll";
//	
//	public static final String PACK_LEAF_WEI_NAME_ALL = "packLeafWeiAll";
//	
//	public static final String PACK_PRT_ALL = "packPrtAll";
//	
//	public static final String PACK_PRT_NAME_ALL = "packPrtNameAll";
//	
//	public static final String PACK_LEAF_PRT_NAME_ALL = "packLeafPrtAll";
//	
//	public static final String PACK_ADV_ALL = "packAdvAll";
//	
//	public static final String PACK_ADV_NAME_ALL = "packAdvNameAll";
//	
//	public static final String PACK_LEAF_ADV_NAME_ALL = "packLeafAdvAll";
	
	public static final String ACCESS_TOKEN = "wxcrmaccessToken";
	
	public static final String ACCESSTOKEN_EXPIRE = "wxcrmaccessTokenExpire";
	
	public static final String JS_API_TICKET = "wxcrmjsApiTikcet";
	
	public static final String JS_API_TICKET_EXPIRE = "wxcrmjsApiTikcetExpire";
	
	//第三方登录方式-微信
	public static final String OTHERLOG_WEIXIN = "3";
	
	/***************************************以下是客户系统中需要用到的变量******************************************/
	// 保存在cookie中的管理员用户名
	public static final String ADMIN_USERNAME_WX = "wxcrmAdminUsernameCust";
	
	// 保存在cookie中的管理员密码md5
	public static final String ADMIN_PWD_MD5_WX = "wxcrmAdminPwdmd5Cust";
	
	// 保存在cookie中的管理员密码md5
	public static final String ADMIN_PWD_3DES_WX = "wxcrmAdminPwd3desCust";
	
	// 保存在cookie中的管理员登录直接跳转的uri
	public static final String LOGIN_REDIRECT_URI_WX = "wxcrmloginRedirectUriCust";
	
	// 保存在session中的管理员信息
	public static final String ADMIN_INFO_WX = "adminInfoCust";
	
	public static final String ADMIN_INFO_COOKIE_WX = "wxcrmadminInfoCookieCust";
	
	// 保存在session中的管理员拥有的一级菜单
	public static final String ADMIN_MENUS_LEVEL1_WX = "adminMenusLevel1Cust";
	
	// 保存在session中的管理员拥有的二级菜单
	public static final String ADMIN_MENUS_LEVEL2_WX = "adminMenusLevel2Cust";
	
	// 保存在session中的管理员拥有的三级菜单
	public static final String ADMIN_MENUS_LEVEL3_WX = "adminMenusLevel3Cust";
	
	// 保存在session中的当前选中的一级菜单id
	public static final String ADMIN_MENU_ID1_WX = "adminMenuId1Cust";
	
	// 保存在session中的当前选中的二级菜单id
	public static final String ADMIN_MENU_ID2_WX = "adminMenuId2Cust";
	
	// 保存在session中的当前选中的三级菜单id
	public static final String ADMIN_MENU_ID3_WX = "adminMenuId3Cust";
	
	//登陆的商家信息
	public static final String WEBSITE_INFO_LIST = "webSiteInfoList";
	
	//登陆的商家信息
	public static final String WEBSITE_INFO = "webSiteInfo";
	
}







