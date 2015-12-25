package com.wxcrm.sys;

import java.util.List;
import java.util.Map;

import com.wxcrm.util.Page;

public interface IMenuService {

	public Page queryMenu(WcMenu menu);
	public List<Map<String, Object>> queryMenuToCache();
	public void addMenu(WcMenu menu);
	public void delMenu(String [] menuIds);

	public WcMenu getMenuById(Integer menuId);

	public void updMenu(WcMenu menu);
	
	public List<Map<String, Object>> queryShopMenuToCache();
	
}
