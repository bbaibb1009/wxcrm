package com.wxcrm.util;

import javax.servlet.http.HttpServletRequest;

public class SysConstant 
{
	// ������request�еķ�ҳ��ѯ����ķ��ʱ���
	public static final String PAGE_RESULT = "pageResult";
	
	//public static final String BLACKTEL_ALL = "BlacktelAll";
	
	// ������memcached�е����в˵��б�
	public static final String MENU_ALL = "menuAll";
	
	// ������memcached�е�����΢���̼Ҳ˵��б�
	public static final String SHOP_MENU_ALL = "shopmenuAll";
	
	
//	// ������memcached�е����в����б�
//	public static final String DEPT_ALL = "deptAll";
	
//	// ������memcached�е����в�Ʒ�����б�
//	public static final String PRO_NAME_ALL = "proNameAll";
	
//	// ������memcached�е����в�Ʒ�������б�
//	public static final String PRODUCT_TEMPLATE_ALL = "productTemplateAll";
//	
	// ������memcached�е����й���Ա�����б�
	public static final String ADMIN_NAME_ALL = "wxcrmadminNameAll";
	
	
	// ������memcached�е�����΢���̼ҹ���Ա�����б�
	public static final String SHOP_ADMIN_NAME_ALL = "wxshopadminNameAll";
	
//	// ������memcached�е����в��������б�
//	public static final String DEPT_NAME_ALL = "deptNameAll";
//	
//	// ������memcached�е�һ�����������б�
//	public static final String DEPT_NAME1_ALL = "deptName1All";
	
//	// ������memcached�еĶ������������б�
//	public static final String DEPT_NAME2_ALL = "deptName2All";
	
//	// ������memcached�е��ʼ������б�
//	public static final String MAIL_CONFIG_ALL = "mailConfigAll";
//	
//	// ������memcached�еĲ�Ʒ���ݱ��б�
//	public static final String PRODUCT_DATATAB_ALL = "productDatatabAll";
	
//	// ������memcached�еĲ�Ʒ���ݵ�Ԫ�б�
//	public static final String PRODUCT_UNIT_ALL = "productUnitAll";
//	
//	// ������memcached�е�װ����ҵ�����б�
//	public static final String COMP_NAME_ALL = "compNameAll";
	
//	// ������memcached�е�װ�����������б�
//	public static final String TYPE_NAME_ALL = "typeNameAll";
	
	// ������memcached�е�IP�������б�
	public static final String IPY_ALL = "wxcrmipyAll";
	
//	// ������memcached�е��ײ͵�Ԫ�����б�
//	public static final String PACKUNIT_ALL = "packUnitAll";
	
	// ������session�еĹ���Ա��Ϣ
	public static final String ADMIN_INFO = "adminInfo";
	
	// ������session�еĹ���Աӵ�е�һ���˵�
	public static final String ADMIN_MENUS_LEVEL1 = "adminMenusLevel1";
	
	// ������session�еĹ���Աӵ�еĶ����˵�
	public static final String ADMIN_MENUS_LEVEL2 = "adminMenusLevel2";
	
	// ������session�еĹ���Աӵ�е������˵�
	public static final String ADMIN_MENUS_LEVEL3 = "adminMenusLevel3";
	
	// ������session�еĵ�ǰѡ�е�һ���˵�id
	public static final String ADMIN_MENU_ID1 = "adminMenuId1";
	
	// ������session�еĵ�ǰѡ�еĶ����˵�id
	public static final String ADMIN_MENU_ID2 = "adminMenuId2";
	
	// ������session�еĵ�ǰѡ�е������˵�id
	public static final String ADMIN_MENU_ID3 = "adminMenuId3";
	
	// cookie����ʱ��
	public static final int COOKIE_AGE = 30 * 24 * 60 * 60;
	
	// ������cookie�еĹ���Ա�û���
	public static final String ADMIN_USERNAME = "wxcrmAdminUsername";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_MD5 = "wxcrmAdminPwdmd5";
	
	// ������cookie�еĹ���Ա����3des
	public static final String ADMIN_PWD_3DES = "wxcrmAdminPwd3des";
	
	
	
	// ������cookie�еĹ���Ա��¼ֱ����ת��uri
	public static final String LOGIN_REDIRECT_URI = "wxcrmloginRedirectUri";
	
	
	
	// �������ֲ߳̾������е�request����
	public static final ThreadLocal<HttpServletRequest> REQUEST_LOCAL = new ThreadLocal<HttpServletRequest>();
	
	// ϵͳ������ʱ���
	public static final String SYS_STARTUP = "wxcrmsysStartUpTime";
	
//	// ������memcached����Ϣ��Ԫ����
//	public static final String INFO_UNIT_ALL = "infoUnitAll";
//	
//	// ������memcached����Ϣ��Ԫ����
//	public static final String NEWS_TEMPLATE_ALL = "newsTemplateAll";
//	
//	// �ʼ�֪ͨ
//	public static final String NOTICE_MAIL = "0";
//	
//	// spark֪ͨ
//	public static final String NOTICE_SPARK = "1";
	
//	// ΢��֪ͨ
//	public static final String NOTICE_WEIXIN = "2";
	
	public static final String ADMIN_INFO_COOKIE = "wxcrmadminInfoCookie";
	
//	//������memcached�е���Ѷ�ײ�����
//	public static final String PACK_INFO_NAME_ALL = "packInfoNameAll";
//	
//	//������memcached�е���Ѷ�ײ�
//	public static final String PACK_INFO_ALL = "packInfoAll";
//	
//	//������memcached�еĶ����ײ�����
//	public static final String PACK_SMS_NAME_ALL = "packSmsNameAll";
//	
//	//������memcached�еĶ����ײ�
//	public static final String PACK_SMS_ALL = "packSmsAll";
//	
//	//������memcached�еĶ��Ż����ײ�
//	public static final String PACK_LEAF_SMS_NAME_ALL = "packLeafSmsAll";
//	
//	//������memcached�е���Ѷ�����ײ�
//	public static final String PACK_LEAF_INFO_NAME_ALL = "packLeafInfoAll";
//	
//	//������memcached�еĶ�Ѷͨ�ײ�
//	public static final String PACK_DXT_ALL = "packDxtAll";
//	
//	//������memcached�еĶ�Ѷͨ����
//	public static final String PACK_DXT_NAME_ALL = "packDxtNameAll";
//	
//	//������memcached�еĶ�Ѷͨ����
//	public static final String PACK_LEAF_DXT_NAME_ALL = "packLeafDxtAll";
//	
//	//������memcached�еĻ�������Ϣ
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
	
	//��������¼��ʽ-΢��
	public static final String OTHERLOG_WEIXIN = "3";
	
	/***************************************�����ǿͻ�ϵͳ����Ҫ�õ��ı���******************************************/
	// ������cookie�еĹ���Ա�û���
	public static final String ADMIN_USERNAME_WX = "wxcrmAdminUsernameCust";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_MD5_WX = "wxcrmAdminPwdmd5Cust";
	
	// ������cookie�еĹ���Ա����md5
	public static final String ADMIN_PWD_3DES_WX = "wxcrmAdminPwd3desCust";
	
	// ������cookie�еĹ���Ա��¼ֱ����ת��uri
	public static final String LOGIN_REDIRECT_URI_WX = "wxcrmloginRedirectUriCust";
	
	// ������session�еĹ���Ա��Ϣ
	public static final String ADMIN_INFO_WX = "adminInfoCust";
	
	public static final String ADMIN_INFO_COOKIE_WX = "wxcrmadminInfoCookieCust";
	
	// ������session�еĹ���Աӵ�е�һ���˵�
	public static final String ADMIN_MENUS_LEVEL1_WX = "adminMenusLevel1Cust";
	
	// ������session�еĹ���Աӵ�еĶ����˵�
	public static final String ADMIN_MENUS_LEVEL2_WX = "adminMenusLevel2Cust";
	
	// ������session�еĹ���Աӵ�е������˵�
	public static final String ADMIN_MENUS_LEVEL3_WX = "adminMenusLevel3Cust";
	
	// ������session�еĵ�ǰѡ�е�һ���˵�id
	public static final String ADMIN_MENU_ID1_WX = "adminMenuId1Cust";
	
	// ������session�еĵ�ǰѡ�еĶ����˵�id
	public static final String ADMIN_MENU_ID2_WX = "adminMenuId2Cust";
	
	// ������session�еĵ�ǰѡ�е������˵�id
	public static final String ADMIN_MENU_ID3_WX = "adminMenuId3Cust";
	
	//��½���̼���Ϣ
	public static final String WEBSITE_INFO_LIST = "webSiteInfoList";
	
	//��½���̼���Ϣ
	public static final String WEBSITE_INFO = "webSiteInfo";
	
}







