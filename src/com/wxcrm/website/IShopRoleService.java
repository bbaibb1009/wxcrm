package com.wxcrm.website;

import java.util.List;

import com.wxcrm.util.Page;

public interface IShopRoleService {

	public List<WcShopRole> queryShopRoleForAdminAdd();
	
	public Page queryShopRole(WcShopRole role);
	
	public void addShopRole(WcShopRole role);
	
	public WcShopRole getShopRoleById(Integer roleId);
	
	public void delShopRole(String[] roleIds);
	
	public void updShopRole(WcShopRole role);
	 
	public List<String> queryShopRoleMenusById(Integer roleId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd1(Integer adminId);
	
	public List<WcShopRole> queryShopRoleForAdminUpd0(Integer adminId);
}
