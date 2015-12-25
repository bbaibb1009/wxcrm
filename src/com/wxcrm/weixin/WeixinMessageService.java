package com.wxcrm.weixin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oilchem.weixin.Constant;
import com.oilchem.weixin.message.response.LzWeiBaseMsgResp;
import com.oilchem.weixin.message.response.LzWeiTextMsgResp;
import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiArticleMessage;
import com.wxcrm.weixin.pojo.WeixinUser;

@Service
@Transactional
public class WeixinMessageService implements IWeixinMessageService {

	@Autowired 
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Autowired 
	private IWeixinService weixinservice;
	
	@Autowired
	private IWeiMemberService weimemberservice;
	
	@Autowired 
	private IWeixinUserService weixinUserService;
	
	
	private static Logger log = Logger.getLogger(WeixinMessageService.class);
	


	public String processRequest_Jar(HttpServletRequest request,String token,String encodingAESKey,String appId)throws IOException 
	{
		String respMessage 		= null;  
        String encrypt_type 	= (String)request.getParameter("encrypt_type")==null? "":(String)request.getParameter("encrypt_type");
    	String msg_signature 	= (String)request.getParameter("msg_signature")==null?"":(String)request.getParameter("msg_signature");
    	String timestamp 		= (String)request.getParameter("timestamp")==null? "":(String)request.getParameter("timestamp");
    	String nonce 			= (String)request.getParameter("nonce")==null? "":(String)request.getParameter("nonce");
        // 默认返回的文本消息内容  
        String respContent = "请求处理异常，请稍候尝试！";  
        InputStream inputStream = request.getInputStream();
        // xml请求解析  
        Map<String, String> requestMap = new HashMap<String,String>();
        //按照加密方式的不同进行消息的预处理
        if(encrypt_type.equals("aes"))
        {
        	log.error("当前是安全模式!");
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlAes(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);
            log.error(requestMap);
        }
        else
        {
        	log.error("当前是兼容模式或明文模式!");
            requestMap = com.oilchem.weixin.message.MessageUtil.parseXmlRaw(inputStream, encrypt_type, msg_signature, timestamp, nonce, token, encodingAESKey, appId);  
            log.error(requestMap);
        }
        // 不同的消息内容返回不同的响应
        LzWeiBaseMsgResp respMsg = fenleiReq_Jar(requestMap,appId);  
        // 再根据加密方式的不同进行消息的相应前封装
        respMessage = com.oilchem.weixin.message.MessageUtil.baseMessageToXml(respMsg,encrypt_type,token,encodingAESKey,appId,msg_signature,timestamp,nonce);
        return respMessage; 
	}
    
	/** 
     * 对微信发过来的请求进行分类
     * @param request 
     * @return 
	 * @throws Exception 
     */
    public LzWeiBaseMsgResp fenleiReq_Jar(Map<String, String> requestMap,String appId) 
    {
    	//发送方帐号(open_id)  
        String fromUserName = requestMap.get("FromUserName")==null?"":requestMap.get("FromUserName");  
        //公众帐号  (app_Id)
        String toUserName 	= requestMap.get("ToUserName")==null?"":requestMap.get("ToUserName");  
        //消息类型  
        String msgType 		= requestMap.get("MsgType")==null?"":requestMap.get("MsgType");  
        //发过来的消息
        String content		= requestMap.get("Content")==null?"":requestMap.get("Content");
        
        String respContent 	= "";
        
        LzWeiBaseMsgResp 	respMessage = new LzWeiBaseMsgResp();
        respMessage.setToUserName(fromUserName);  
        respMessage.setFromUserName(toUserName);  
        respMessage.setCreateTime(new Date().getTime());  
        respMessage.setFuncFlag(0); 
        
        log.error("现在的信息类型是:"+msgType);
        // 文本消息  
        if(msgType.equals(Constant.REQ_MESSAGE_TYPE_TEXT)) 
        {  
        	log.error("我这是文本信息1");
            //在这里将发送过来的消息进行分类处理
        	//先根据appid 取出默认回复信息
        	respMessage = this.queryDefaultMsgByAppId(respMessage,content,appId);
        	respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_TEXT);
        }  
        // 图片消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_IMAGE)) 
        {  
        	log.error("我这是图片消息");
            respContent = "您发送的是图片消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_IMAGE);
        }  
        // 地理位置消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LOCATION)) 
        {  
        	log.error("我这是地理位置消息");
            respContent = "您发送的是地理位置消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LOCATION);
        }  
        // 链接消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_LINK)) 
        {  
            respContent = "您发送的是链接消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_LINK);
        }  
        // 音频消息  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_VOICE)) 
        {  
            respContent = "您发送的是音频消息！";  
            respMessage.setMsgType(Constant.REQ_MESSAGE_TYPE_VOICE);
        }  
        // 事件推送  
        else if (msgType.equals(Constant.REQ_MESSAGE_TYPE_EVENT)) 
        {  
            // 事件类型  
            String eventType = requestMap.get("Event");  
            // 订阅  
            if (eventType.equals(Constant.EVENT_TYPE_SUBSCRIBE)) 
            {  
            	//添加一条会员记录 只是关注者
            	LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
            	//先检查当前openid在该appid下有没有绑定
            	if(weimemberservice.checkOpenIdExsit(fromUserName,enter.getWecId()))
            	{
            		//回复老朋友！欢迎回来
            		respMessage = this.querySubscribeAgainMsgByAppId(respMessage);
            	}
            	else
            	{
            		WeixinUser user =  weixinUserService.getUserInfo(fromUserName, appId);
            		LzWeiMember member = new LzWeiMember(fromUserName,enter.getWecId(),enter.getWecEnterId(),user);
                	weimemberservice.addWeiMember(member);
                	//其次返回一个消息
                	respMessage = this.querySubscribeMsgByAppId(respMessage,appId);
            	}
            	log.error("5");
            	
            }  
            // 取消订阅  
            else if (eventType.equals(Constant.EVENT_TYPE_UNSUBSCRIBE)) 
            {  
                // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
            	// 取消关注后要将用户的状态改为已取消关注
            	this.updWatcherByUnSubscribe(respMessage,appId);
            	log.error("取消订阅");
            	respMessage.setMsgType(Constant.EVENT_TYPE_UNSUBSCRIBE);
            }  
            // 自定义菜单点击事件  
            else if (eventType.equals(Constant.EVENT_TYPE_CLICK)) {  
                // TODO 自定义菜单权没有开放，暂不处理该类消息 
            	respMessage.setMsgType(Constant.EVENT_TYPE_CLICK);
            }  
        }
        return respMessage;
    }
        
    public void updWatcherByUnSubscribe(LzWeiBaseMsgResp respMessage,String appId)
    {
    	//根据openId和appid找到相应的watcher
    	//置为取消关注
    	LzWeiEnter wec= weixinservice.getWeiEnterByAppId(appId);
    	String openId = respMessage.getToUserName();
    	LzWeiWatcher watcher = weixinservice.getWatcherByOpenId(openId,wec.getWecId());
    	watcher.setWacSubscribe(0);
    	weixinservice.updWatcher(watcher);
    }
    
    public LzWeiBaseMsgResp queryDefaultMsgByAppId(LzWeiBaseMsgResp respMessage,String content,String appId)
    {
    	log.info("获取回复文本消息");
    	LzWeiMessage msg = this.getKeyWordMsgByContent(content, appId);
    	//先判断content是不是关键字
    	if(msg!=null)
    	{
    		String msgType = msg.getWmgMsgType();
    		if(msgType.equals("2"))
    		{
    			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else
    		{
    			log.error("***********************318行：是关键字 但不是文本信息***********************");
    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		}
    	}
    	//不是关键字的就返回默认回复信息
    	else
    	{

        	String sql = 
        		" select b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT " +
        		" from LZ_WEI_ENTER a " +
        		" join LZ_WEI_MESSAGE b on a.WEC_DEFAULT_MSG = b.WMG_ID  " +
        		" where a.WEC_APP_ID = ? ";
        	List<Map<String,Object>> list = jdbcDao.queryForList(sql,new Object[]{appId});
        	if(list.size()>0)
        	{
        		Map<String,Object> map = list.get(0);
        		Integer wmgId = (Integer)map.get("WMG_ID");
        		msg = this.getLzWeiMessageById(wmgId);
        		String msgType = msg.getWmgMsgType() ;
        		if(msgType.equals("2"))
        		{
        			log.error("***********************338行***********************");
        			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
        			msgResp.setContent(msg.getWmgContent());
        			return msgResp;
        		}
        		else
        		{
        			log.error("***********************345行：不是返回文本信息***********************");
        			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        		}
        	}
        	else
        	{
        		log.error("***********************351行：查不出数据***********************");
        		log.error(" select b.WMG_ID, b.WMG_MSG_TYPE,b.WMG_CONTENT " +
                		" from LZ_WEI_ENTER a " +
                		" join LZ_WEI_MESSAGE b on a.WEC_DEFAULT_MSG = b.WMG_ID  " +
                		" where a.WEC_APP_ID = "+appId);
        		return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
        	}
    	}
    }
    
    
    public LzWeiBaseMsgResp  querySubscribeMsgByAppId(LzWeiBaseMsgResp respMessage,String appId)
    {
    	LzWeiMessage msg = this.getSubScribeMsgByApp(appId);
    	//先判断content是不是关键字
    	if(msg!=null)
    	{
    		String msgType = msg.getWmgMsgType();
    		if(msgType.equals("2"))
    		{
    			LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
    			msgResp.setContent(msg.getWmgContent());
    			return msgResp;
    		}
    		else
    		{
    			log.error("***********************318行：有关注回复 但不是文字消息***********************");
    			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		}
    	}
    	else
    	{
    		log.error("***********************387行：没有设置关注回复***********************");
			return com.oilchem.weixin.message.TextMsgUtil.getDefualtTextMsg(respMessage);
    		
    	}
    }
    
    
    public LzWeiBaseMsgResp  querySubscribeAgainMsgByAppId(LzWeiBaseMsgResp respMessage)
    {
    	LzWeiTextMsgResp msgResp = new LzWeiTextMsgResp(respMessage);
		msgResp.setContent("老朋友，欢迎回来!");
		return msgResp;
    }
    
    public Page queryLzWeiMsg(LzWeiMessage msg) {
		// TODO Auto-generated method stub
    	String wmgReplyType_Q = msg.getWmgReplyType_Q();
    	String wmgMsgType_Q = msg.getWmgMsgType_Q();
    	String wmgAesType_Q = msg.getWmgAesType_Q();
    	StringBuilder sql =new StringBuilder(
    		" select " +
    		" a.WMG_ID," +
    		" a.WMG_CONTENT," +
    		" a.WMG_REPLY_TYPE," +
    		" a.WMG_MSG_TYPE," +
    		" a.WMG_AES_TYPE," +
    		" a.WMG_STATUS," +
    		" a.WMG_DESC," +
    		" a.WMG_REGISTOR," +
    		" a.WMG_REGISTDATE " +
    		" from LZ_WEI_MESSAGE a " +
    		" where 1=1 "
    	);
    	StringBuilder sqlCnt = new StringBuilder(
    		" select count(*) from LZ_WEI_MESSAGE a " +
    		" where 1=1 "
    	);
    	List<Object> paraList = new ArrayList<Object>(){};
    	if(wmgReplyType_Q!=null && wmgReplyType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_REPLY_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_REPLY_TYPE = ? ");
    		paraList.add(wmgReplyType_Q);
    	}
    	if(wmgMsgType_Q!=null && wmgMsgType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_MSG_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_MSG_TYPE = ? ");
    		paraList.add(wmgMsgType_Q);
    	}
    	if(wmgAesType_Q!=null && wmgAesType_Q.length()>0)
    	{
    		sql.append(" and a.WMG_AES_TYPE =  ? ");
    		sqlCnt.append(" and a.WMG_AES_TYPE = ? ");
    		paraList.add(wmgAesType_Q);
    	}
    	Page page = new Page(sql.toString(),sqlCnt.toString(),msg.getCurrentPage(),msg.getPageSize(),paraList.toArray());
    	jdbcDao.queryForPage(page);
		return page;
	}

	public void addLzWeiMsg(LzWeiMessage msg) 
	{
		// TODO Auto-generated method stub
		hibernateDao.add(msg);
	}

	public LzWeiMessage getLzWeiMessageById(Integer id)
	{
		return hibernateDao.get(LzWeiMessage.class, id);
	}
	
	public LzWeiMessage getKeyWordMsgByContent(String content,String appId)
	{
		String sql = 
			" select a.WKG_WMG_ID from LZ_WEI_KEYWORD_MESSAGE a " +
			" join LZ_WEI_ENTER b on a.WKG_WEC_ID = b.WEC_ID  " +
			" where b.WEC_APP_ID = ? and a.WKG_KEYWORDS = ? ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId,content});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmgId = (Integer)map.get("WKG_WMG_ID");
			LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId); 
			return msgWei;
		}
		else
		{
			return null;
		}
	}
	
	public LzWeiMessage getSubScribeMsgByApp(String appId)
	{
		String sql = 
			" select a.WEC_SUBSCRIBE_MSG from LZ_WEI_ENTER a " +
			" where a.WEC_APP_ID = ?  ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			Integer wmgId = (Integer)map.get("WEC_SUBSCRIBE_MSG");
			LzWeiMessage msgWei = this.getLzWeiMessageById(wmgId); 
			return msgWei;
		}
		else
		{
			return null;
		}
	}
	

	public List<Map<String, Object>> queryKeywordListByWei(Integer wecId) {
		// TODO Auto-generated method stub
		String sql = 
			" select distinct  " +
			"  LEFT(a.kwd,LENGTH(a.kwd)-1) as kwd,b.WMG_CONTENT,b.WMG_ID ,a.WKG_WEC_ID,c.WKG_APP_ID " +
			"  from  " +
			"  (  " +
			" 		select bb.WMG_ID,cc.WKG_WEC_ID, " +
			"		(select group_concat(WKG_KEYWORDS) from LZ_WEI_KEYWORD_MESSAGE where WKG_WMG_ID = bb.WMG_ID ) as kwd from LZ_WEI_MESSAGE bb left join LZ_WEI_KEYWORD_MESSAGE cc on bb.WMG_ID = cc.WKG_WMG_ID group by bb.WMG_ID ,cc.WKG_WEC_ID " +
			" ) a  " +
			" left join LZ_WEI_MESSAGE b on a.WMG_ID = b.WMG_ID  " +
			" join LZ_WEI_KEYWORD_MESSAGE c on c.WKG_WEC_ID = a.WKG_WEC_ID  " +
			" where c.WKG_WEC_ID = ? " ;
		return jdbcDao.queryForList(sql, new Object[]{wecId});
	}

	public void updLzWeiMsg(LzWeiMessage msg) {
		// TODO Auto-generated method stub
		hibernateDao.update(msg);
	}
	
	public Map<String, Object> getWeiArticleMap(Integer id) {
		// TODO Auto-generated method stub
		String sql = new String(" select * from lz_wei_article_message a where a.wam_id = ? ");
		return jdbcDao.queryForMap(sql, new Object[]{id});
	}

	public void addLzWeiArticleMsg(LzWeiArticleMessage article) {
		// TODO Auto-generated method stub
		hibernateDao.add(article);
	}

	public static void main(String[] args)
	{
		Map<String,String> map = new HashMap<String,String>();
		map.put("fromUser", "葫芦娃");
		map.put("toUser", "葫芦娃");
		System.out.println(map);
		
	}
	
	
}
