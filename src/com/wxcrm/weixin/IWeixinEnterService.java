package com.wxcrm.weixin;

import java.util.List;
import java.util.Map;

import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.Page;

public interface IWeixinEnterService 
{
	public List<Map<String,Object>> queryGqEnterList(Integer enterId);
	public void addWeiEnterCust(LzWeiEnterCust enterCust);
	public void updWeiEnterCust(LzWeiEnterCust enterCust);
	public LzWeiEnterCust getWeiEnterCustByOpenId(String openId);
	public LzWeiEnterCust getWeiEnterCustById(Integer wetId);
	public void addWeiEnterOrder(LzWeiEnterOrder order);

	public void delWeiEnterCust(String [] wetIds);
	public Page queryWeiEnterOrder(LzWeiEnterOrder order);
	public void delWeiEnterOrder(String[] weoIds);
	//public LzCustomerUser checkWebUserAccount(String username,String password);
	//public String bindAdminByCust(String appId,String openid,LzCustomerUser custuser,LzAdmin admin);
	//public  List<Map<String,Object>> queryGqByWeiAdmin(Integer cuuId);
	public void delGqByWeiAdmin(Integer gqId);
	public List<Map<String,Object>> queryWeiEnterOrderList(LzWeiEnterOrder order);
}
