package com.wxcrm.test;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oilchem.weixin.OAuth2.authorizeUtil;
import com.wxcrm.goods.IGoodsService;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.util.SysConstant;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.weixin.LzWeiEnter;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;

@Controller
@RequestMapping("/test")
public class TestController 
{
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IWeiMemberService weimemberservice;
	
	@Autowired
	private IWeixinService weixinservice;

	@Autowired
	private IGoodsService goodsService;

	@RequestMapping("/toUploadImg/{appId}")
	public String toUploadImg(@PathVariable String appId,Model model, HttpSession session,HttpServletRequest request) throws Exception
	{
		String code   = (String)request.getParameter("code")  == null? "" : (String)request.getParameter("code");
		String openid  ="";
		LzWeiEnter	enter 		= weixinservice.getWeiEnterByAppId(appId);
		if(code!=null&&code.length()>0)
		{
			openid = authorizeUtil.getOpenId(appId,enter.getWecAppSecret(),code);
		}
		if(openid != null && openid.length()>0)
		{
			 LzWeiMember weimember = weimemberservice.getWeiMemberByOpenId(openid,appId);//
		}
		LzWeiJsapiTicket jsApiTicket = weixinservice.getCurrentTikcet(enter.getWecId());
		String jsApiTicketStr 	= jsApiTicket.getWjtJsapiTicket(); 
		String timestamp 		= Long.toString(new Date().getTime()/1000);
		String nonceStr  		= "abc123";
		String url 				= "http://wx.ypweb.net/test/toUploadImg/"+appId;
		String string1 			= getString1(jsApiTicketStr,nonceStr,timestamp,url);
		String signature 		= com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
		model.addAttribute("command",enter);
		model.addAttribute("appId", appId);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", nonceStr);
		model.addAttribute("signature", signature);
		model.addAttribute("jsApiTicketStr",jsApiTicketStr);
		return "/test/uploadImg";
	}
	
	public String getString1(String ticket,String noncestr,String timestamp,String url)
	{
		String string1 = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
		return string1.replace("TICKET", ticket).replace("NONCESTR", noncestr).replace("TIMESTAMP", timestamp).replace("URL", url);
	}
	
	@RequestMapping("/uploadImg/{appId}")
	public String uploadImg(@PathVariable String appId,Model model, HttpSession session,HttpServletRequest request) throws Exception
	{
		
		return "/test/uploadImg";
	}

}
