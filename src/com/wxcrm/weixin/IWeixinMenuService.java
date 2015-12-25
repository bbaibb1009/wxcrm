package com.wxcrm.weixin;

import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiMenu;

public interface IWeixinMenuService {
	
	public Page queryMenu(LzWeiMenu menu);
	public int addMenu(LzWeiMenu menu,String accessToken);
	public LzWeiMenu getMenuById(Integer wmuId);
	public LzWeiMenu getMenuByAppId(String appId);

}
