package com.wxcrm.website;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wxcrm.util.Page;

public interface IShopAdminService {

	public Page queryShopAdmin(WcShopAdmin admin);
	public void addShopAdmin(WcShopAdmin admin);
	public boolean chkShopUsernameUnique(Integer adminId, String username);
	public boolean chkShopUsernameUnique(String username);
	public List<Map<String, Object>> queryShopAdminMenus(Integer adminId, String menuLevel);
	public void updShopLoginTime(Integer adminId);
	public WcShopAdmin getShopAdminById(Integer id);
	public void updShopAdmin(WcShopAdmin admin) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException;
	
	public List<Map<String, Object>> queryShopAdminNameToCache();
	
	
}
