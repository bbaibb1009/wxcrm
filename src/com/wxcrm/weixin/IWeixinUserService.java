package com.wxcrm.weixin;

import com.wxcrm.weixin.pojo.WeixinUser;

public interface IWeixinUserService {

	public WeixinUser getUserInfo(String openId,String appId);
	
	
}
