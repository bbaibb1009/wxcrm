package com.wxcrm.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static void removeAdminSession(HttpSession session)
	{
		session.removeAttribute(SysConstant.ADMIN_INFO);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID1);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID2);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID3);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL1);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL2);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL3);
	}
	
	public static void removeMySiteSession(HttpSession session)
	{
		session.removeAttribute(SysConstant.ADMIN_INFO_WX);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID1_WX);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID2_WX);
		session.removeAttribute(SysConstant.ADMIN_MENU_ID3_WX);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL1_WX);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL2_WX);
		session.removeAttribute(SysConstant.ADMIN_MENUS_LEVEL3_WX);
	}
}
