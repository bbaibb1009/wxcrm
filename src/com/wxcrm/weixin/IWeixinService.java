package com.wxcrm.weixin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.Page;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.pojo.LzWeiAccesstoken;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;

public interface IWeixinService {
	public void initErrorCode();
	public Page queryErrorCode(LzWeiErrorcode code);
	public Page queryAccessToken(LzWeiAccesstoken token);
	//public List<Map<String,Object>> queryWatcherList(LzWeiWatcher watcher) throws Exception;
	public Page queryWatcher(LzWeiWatcher watcher);
	public String updAccessToken(Integer wecId) throws Exception;
	public Integer updWeixinWatcher(String next_OpenId,HttpSession session,Integer wecId,String appId);
	public void sendMsg(String openId ,String msg,Integer wecId);
	public LzWeiWatcher getWatcher(Integer wacId);
//	public boolean testAccessToken(String accessToken);
	public Date getExpireDate();
	public String getNextOpenId();
	public Page queryWeixinEnter(LzWeiEnter weiEnter);
	public boolean checkAppExsit(LzWeiEnter weiEnter);
	public void addWeiEnter(LzWeiEnter weiEnter);
	public void deleteWeiEnter(String[] wecIds);
	public Page queryWebSite(WcWebsite website);
	public LzWeiEnter getWeiEnterById(Integer weiEnterId);
	public void updWeiEnter(LzWeiEnter weiEnter);
	public List<LzWeiEnter> queryWeixinEnterList();
	public List<Map<String,Object>> queryWeiEnterList();
	public String getCurrentAccessTokenStr(Integer wecId) throws Exception;
	public String getCurtAccTokenStrByApp(String appId) throws Exception;
	public LzWeiAccesstoken getCurrentAccessToken(Integer wecId) throws Exception;
	
	public LzWeiEnter getWeiEnterByAppId(String appId);
	public void addKeyWordsByEnter(LzWeiEnter weiEnter,String[] keyWrds,String[] keyWrdMsgIds,WcAdmin admin);
	public LzWeiWatcher getWatcherByOpenId(String openidStr,Integer wecId);
	//public Map<String,Object> querUserInfo(String openId,Integer wecId) throws Exception;
	public void addWatcher(LzWeiWatcher watcher);
	public void updWatcher(LzWeiWatcher watcher);
	
	public String updJsApiTicket(Integer wecId) throws Exception;
	
	public LzWeiJsapiTicket getCurrentTikcet(Integer wecId) throws Exception;
	public Page queryJsApiTicket(LzWeiJsapiTicket tikcet) ;
	
	public LzWeiEnter getWeiEnterByAdminId(Integer wadId);

}
