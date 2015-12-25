package com.wxcrm.sys;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wxcrm.util.Page;

public interface IAdminService {

	public Page queryAdmin(WcAdmin admin);
	public void addAdmin(WcAdmin admin);
	public boolean chkUsernameUnique(Integer adminId, String username);
	public boolean chkUsernameUnique(String username);
	public WcAdmin adminLogin(WcAdmin admin);
	public List<Map<String, Object>> queryAdminMenus(Integer adminId, String menuLevel);
	public void updLoginTime(Integer adminId);
	public WcAdmin getAdminById(Integer id);
	public void updAdmin(WcAdmin admin) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public List<Map<String, Object>> queryAdminNameToCache();
	public List<Map<String, Object>> queryShopAdminNameToCache();
	
}
