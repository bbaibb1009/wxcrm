package com.wxcrm.weixin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oilchem.weixin.message.response.LzWeiBaseMsgResp;
import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiArticleMessage;

public interface IWeixinMessageService {
	//public String processRequest(HttpServletRequest request,String token,String encodingAESKey,String appId,String type) throws Exception ;
	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId) throws IOException;
	public LzWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId);
	public Page queryLzWeiMsg(LzWeiMessage msg);
	public void addLzWeiMsg(LzWeiMessage msg);
	public LzWeiMessage getLzWeiMessageById(Integer id);
	public List<Map<String,Object>> queryKeywordListByWei(Integer wecId);
	public void updLzWeiMsg(LzWeiMessage msg);
    public void	addLzWeiArticleMsg(LzWeiArticleMessage article);
    public Map<String,Object> getWeiArticleMap(Integer id);
	
	
}
