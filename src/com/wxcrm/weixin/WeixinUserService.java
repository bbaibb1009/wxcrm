package com.wxcrm.weixin;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oilchem.weixin.user.UserUtil;
import com.wxcrm.weixin.pojo.WeixinUser;
@Service
@Transactional
public class WeixinUserService implements IWeixinUserService {

	@Autowired
	private IWeixinService weixinservice;
	
	public WeixinUser getUserInfo(String openId, String appId)  {
		// TODO 获取用户基本信息
		try
		{
			String accessToken = weixinservice.getCurtAccTokenStrByApp(appId);
			JSONObject obj =  UserUtil.getUserInfo(accessToken, openId);
			if(obj!=null)
			{
				return new WeixinUser(obj);
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			return null;
		}
		
		
	}

	
	
}
