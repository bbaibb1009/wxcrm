package com.wxcrm.website;

import java.util.List;
import java.util.Map;

import com.wxcrm.util.Page;

public interface IShopMenuService {

	public Page queryShopMenu(WcShopMenu menu);
	public void addShopMenu(WcShopMenu menu);
	public void delShopMenu(String [] menuIds);

	public WcShopMenu getShopMenuById(Integer menuId);

	public void updShopMenu(WcShopMenu menu);
	
	public List<Map<String, Object>> queryShopMenuToCache();
	
}
