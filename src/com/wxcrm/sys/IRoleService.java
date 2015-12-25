package com.wxcrm.sys;

import java.util.List;

import com.wxcrm.util.Page;

public interface IRoleService {

	public List<WcRole> queryRoleForAdminAdd();
	
	public Page queryRole(WcRole role);
	
	public void addRole(WcRole role);
	
	public WcRole getRoleById(Integer roleId);
	
	public void delRole(String[] roleIds);
	
	public void updRole(WcRole role);
	 
	public List<String> queryRoleMenusById(Integer roleId);
	
	public List<WcRole> queryRoleForAdminUpd1(Integer adminId);
	
	public List<WcRole> queryRoleForAdminUpd0(Integer adminId);
}
