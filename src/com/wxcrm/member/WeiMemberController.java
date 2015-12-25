package com.wxcrm.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oilchem.weixin.OAuth2.authorizeUtil;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.weixin.LzWeiEnter;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;

@Controller
@RequestMapping("/weimember")
public class WeiMemberController 
{
	@Autowired
	private IWeiMemberService weimemberService;
	
	@Autowired
	private IWeixinService weixinService;
	
	@RequestMapping(value = "/queryWeiMember")
	public String queryTest(@ModelAttribute("command") LzWeiMember weimember,HttpSession session, Model model)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		model.addAttribute(SysConstant.PAGE_RESULT, weimemberService.queryWeiMember(weimember));
		return "/weimember/queryWeiMember";
	}

	/**
	 * 关注弹出的跳转连接
	 * */
	@RequestMapping(value = "/toBindMember/{appId}")
	public String toBindMember(@ModelAttribute("command") LzWeiMember weimember,@PathVariable String appId ,HttpServletRequest request,Model model)
	{
		String code   = (String)request.getParameter("code")  == null? "" : (String)request.getParameter("code");
		String state  = (String)request.getParameter("state") == null? "" : (String)request.getParameter("state");
		String url = null;
		String openid = weimember.getWmbOpenid();
		LzWeiEnter enter = weixinService.getWeiEnterByAppId(appId);
		if(code!=null&&code.length()>0)
		{
			//查出用户的OpenId
			openid = authorizeUtil.getOpenId(enter.getWecAppId(),enter.getWecAppSecret(),code);
		}
		if(openid != null && openid.length()>0)
		{
			weimember = weimemberService.getWeiMemberByOpenId(openid,appId);//查出现有的会员信息
			if(weimember!=null)
			{
				//用户在点击关注的时候就已经添加了账号了
				url= "/weimember/index";
			}
			else
			{
				weimember.setWmbOpenid(openid);
				url= "/weimember/bindMember";
			}
		}
		model.addAttribute("command", weimember);
		return url;
	}
	
	@RequestMapping("/bindMember")
	public String bindMember(@ModelAttribute("command") LzWeiMember weimember ,Model model)
	{
		//顺序生成卡号
		weimember = weimemberService.getWeiMemberByid(weimember.getWmbId());
		//绑定之前先检查是否已经绑定
		if(weimember.getWmbCardId()!=null&&weimember.getWmbCardId().trim().length()>0)
		{
			//有卡号的不用绑定了
		}
		else
		{
			String wmbCardId= weimemberService.getMemberCardId();
			weimember.setWmbCardId(wmbCardId);
			weimember.setWmbType("2");//设置会员状态为“会员”
			weimemberService.updateWeiMember(weimember);
		}
		model.addAttribute("command", weimember);
		return "/weimember/index";
	}
	
	@RequestMapping("/toMemberIndex/{memberId}/{appId}")
	public String toMemberIndex(
			@ModelAttribute("command") LzWeiMember weimember,
			@PathVariable Integer memberId ,
			@PathVariable String appId ,
			HttpServletRequest request,Model model) throws Exception
	{
		String timestamp 		= Long.toString(new Date().getTime()/1000);
		String nonceStr  		= "abc123";
		String path				= request.getRequestURL().toString() ;
		
		weimember=weimemberService.getWeiMemberByid(memberId);
		LzWeiJsapiTicket jsApiTicket = weixinService.getCurrentTikcet(weimember.getWmbWecId());
//		if(path.indexOf("?")>=0)
//		{
//			path= path.substring(0, path.indexOf("?"));
//		}
		String rootPath 		= path.substring(0,path.indexOf("/weimember/toMemberIndex/"));
		String shareImgUrl 		= rootPath+"/view/weimember/images/logo1.png";
		String url 				= path;
		String jsApiTicketStr 	= "";
		String shareTitle 		= "分享测试";
		String shareLink 		= path;
		//String shareLink 		= "http://www.163.com";
		jsApiTicketStr   		= jsApiTicket.getWjtJsapiTicket(); 
		String string1 			= com.oilchem.weixin.mp.aes.SHA1.getJsSignatureString1(jsApiTicketStr, nonceStr, timestamp, url);
		String signature 		= com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
		
		String path1 = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
		System.out.println("basePath:"+basePath);

		model.addAttribute("command", weimember);
		model.addAttribute("appId", appId);
		model.addAttribute("timestamp", timestamp);
		model.addAttribute("nonceStr", nonceStr);
		model.addAttribute("signature", signature);
		model.addAttribute("shareTitle", shareTitle);
		model.addAttribute("shareLink", shareLink);
		model.addAttribute("shareImgUrl", shareImgUrl);
		model.addAttribute("jsApiTicketStr", jsApiTicketStr);
		
		return "/weimember/index";
	}
	
}
