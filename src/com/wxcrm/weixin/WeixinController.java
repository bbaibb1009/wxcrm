package com.wxcrm.weixin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxcrm.common.IMemcachedService;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.website.IWebsiteService;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.pojo.LzWeiAccesstoken;
import com.wxcrm.weixin.pojo.LzWeiJsapiTicket;

@Controller
@RequestMapping("/weixin")
public class WeixinController 
{
	@Autowired
	private IWeixinService weixinservice;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
//	@Autowired
//	private IWebUserService webuserservice;
	
	@Autowired
	private IWebsiteService websiteservice;
	
	@RequestMapping("/queryErrorCode")
	public String queryErrorCode(@ModelAttribute("command") LzWeiErrorcode code,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryErrorCode(code));
		return "/weixin/queryErrorCode";
	}
	
	@RequestMapping("/addErrorCode")
	public String addErrorCode(@ModelAttribute("command") LzWeiErrorcode code, RedirectAttributes redirectattributes)
	{
		weixinservice.initErrorCode();
		return "redirect:/weixin/queryErrorCode";
	}

	@RequestMapping("/queryAccessToken")
	public String queryAccessToken(@ModelAttribute("command") LzWeiAccesstoken token,Model model) throws Exception
	{
		List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
		String watWecId_Q = token.getWatWecId_Q();
		Integer	wecId = 0;
		if(watWecId_Q==null||watWecId_Q.length()==0)
		{
			wecId = list.get(0).getWecId();
		}
		else
		{
			wecId = Integer.parseInt(watWecId_Q);
		}
		LzWeiAccesstoken tokenCur = weixinservice.getCurrentAccessToken(wecId);
		if(tokenCur!=null)
		{
			model.addAttribute(SysConstant.ACCESSTOKEN_EXPIRE, DateUtil.parseString(DateUtil.parseDate(tokenCur.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
			model.addAttribute(SysConstant.ACCESS_TOKEN, tokenCur.getWatToken());
		}
		
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryAccessToken(token));
		model.addAttribute("watApp",list);
		return "/weixin/queryAccessToken";
	}
	
	@RequestMapping("/queryAccessToken/{wecId}")
	public String queryAccessToken1(@ModelAttribute("command") LzWeiAccesstoken token,@PathVariable Integer wecId,Model model) throws Exception
	{
		List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
		token.setWatWecId_Q(Integer.toString(wecId));
		LzWeiAccesstoken tokenCur = weixinservice.getCurrentAccessToken(wecId);
		if(tokenCur!=null)
		{
			model.addAttribute(SysConstant.ACCESSTOKEN_EXPIRE, DateUtil.parseString(DateUtil.parseDate(tokenCur.getWatExpiresIn(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
			model.addAttribute(SysConstant.ACCESS_TOKEN, tokenCur.getWatToken());
		}
		
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryAccessToken(token));
		model.addAttribute("watApp",list);
		return "/weixin/queryAccessToken";
	}
	
	
	@RequestMapping("/queryWatcher")
	public String queryWatcher(@ModelAttribute("command") LzWeiWatcher watcher,Model model) throws Exception
	{
		List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWatcher(watcher));
		model.addAttribute("watApp", list);
		return "/weixin/queryWatcher";
	}
	
	@RequestMapping("/queryWatcherByEnter/{wecId}")
	public String queryWatcherByEnter(@ModelAttribute("command") LzWeiWatcher watcher,@PathVariable Integer wecId,Model model)
	{
		watcher.setWacWecId_Q(Integer.toString(wecId));
		List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWatcher(watcher));
		model.addAttribute("watApp", list);
		return "/weixin/queryWatcher";
	}

		
	@RequestMapping("/toSendWeixin/{wacId}")
	public String toSendWeixin(@ModelAttribute("command") LzWeiWatcher watcher ,@PathVariable Integer wacId,Model model)
	{
		watcher = weixinservice.getWatcher(wacId); 
		model.addAttribute("command", watcher);
		return "/weixin/sendWeixin";
	}
	
	@RequestMapping("/sendWeixin")
	public String toSendWeixin(@ModelAttribute("command") LzWeiWatcher watcher ,Model model)
	{
		String msg = watcher.getMsg();
		String openId = watcher.getWacOpenid();
		Integer wecId = watcher.getWacWecId();
		weixinservice.sendMsg(openId,msg,wecId);
		model.addAttribute("alertMsg", "发送消息成功!");
		return "/weixin/sendWeixin";
	}

	@RequestMapping("/setAccessToken")
	public String setAccessToken(@ModelAttribute("command") LzWeiAccesstoken accesstoken ,RedirectAttributes redirectattributes)
	{
		try
		{
			Integer watWedId = Integer.parseInt(accesstoken.getWatWecId_Q());
			weixinservice.updAccessToken(watWedId);
			redirectattributes.addFlashAttribute("command", accesstoken);
			redirectattributes.addFlashAttribute("alertMsg", "手动更新AccessToken成功!");
			return "redirect:/weixin/queryAccessToken";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
//	
	@RequestMapping("/updateWatcherOnline")
	public String updateWatcherOnline(@ModelAttribute("command") LzWeiWatcher watcher ,HttpSession session,RedirectAttributes redirectattributes) throws Exception
	{
		Integer num = 0;
		Integer total = 0;
		String nextOpenId = "";
		Integer wecId = Integer.parseInt(watcher.getWacWecId_Q());
		LzWeiEnter enter = weixinservice.getWeiEnterById(wecId);
		do
		{
			nextOpenId = (String)session.getAttribute("curNextOpenId")==null?"":(String)session.getAttribute("curNextOpenId");
			num = weixinservice.updWeixinWatcher(nextOpenId,session,wecId,enter.getWecAppId());
			total+=num;
			System.out.println("第"+(total/(num==0?1:num))+"次后:"+total+"条");
		}
		while(num>0);
		session.removeAttribute("curNextOpenId");
		redirectattributes.addFlashAttribute("alertMsg", "更新完毕!总用户数："+total+"人");
		return "redirect:/weixin/queryWatcher";
	}
	
	
	/**
	 * 查询所有正在管理的所有微信公众号
	 * */
	@RequestMapping("/queryWeixinEnter")
	public String queryWeixinEnter(@ModelAttribute("command") LzWeiEnter weiEnter,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryWeixinEnter(weiEnter));
		return "/weixin/queryWeixinEnter";
	}
	
	
	
	
	@RequestMapping(value = "/queryWeixinEnterBySite/{wcsId}")
	public String queryWeixinEnterByE(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable Integer wcsId,Model model)
	{
		weiEnter.setWecEnterId(wcsId);//商家ID
		model.addAttribute(SysConstant.PAGE_RESULT,  weixinservice.queryWeixinEnter(weiEnter));
		return "/weixin/queryWeixinEnterBySite";
	}
	
	
	/**
	 * 跳转添加微信公众号
	 * */
	@RequestMapping("/toAddWeiEnter")
	public String toAddWeiEnter(@ModelAttribute("command") LzWeiEnter weiEnter,Model model)
	{
		return "/weixin/addWeiEnter";
	}
	
	/**
	 * 添加微信公众号保存
	 * */
	@RequestMapping("/addWeiEnter")
	public String addWeiEnter(@ModelAttribute("command") LzWeiEnter weiEnter,HttpSession session, RedirectAttributes redirectAttributes)
	{
		//查重
		if(weixinservice.checkAppExsit(weiEnter))
		{
			redirectAttributes.addFlashAttribute("alertMsg", "当前应用同类型下已经存在不能重复添加!");
			return "redirect:/weixin/queryWeixinEnter";
		}
		if(weiEnter.getWecCusType().equals("1"))
		{
			weiEnter.setWecEnterId(0);
		}
		weiEnter.setWecStatus("1000");
		
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		weiEnter.setWecRegistor(admin.getWadId());
		weixinservice.addWeiEnter(weiEnter);
		redirectAttributes.addFlashAttribute("alertMsg", "添加应用成功!");
		return "redirect:/weixin/queryWeixinEnter";
	}
	
	/**
	 * 删除微信公众号
	 **/
	@RequestMapping("/delWeiEnter")
	public String delWeiEnter(@ModelAttribute("command") LzWeiEnter weiEnter,HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		String[] wecIds = weiEnter.getWecIds();
		weixinservice.deleteWeiEnter(wecIds);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnter"));
		return "redirect:/admin/toMsg";
	}
	
	/**
	 * 查找站点
	 * */
	@RequestMapping("/queryWebSite")
	public String queryWebSite(@ModelAttribute("command") WcWebsite website,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weixinservice.queryWebSite(website));
		return "/weixin/queryWebSite";
	}
	
	/**
	 * 跳转至修改微信账号资料
	 * */
	@RequestMapping(value = "/toUpdWeiEnter", method = RequestMethod.POST)
	public String toUpdWeiEnter(@ModelAttribute("command")LzWeiEnter weiEnter_Q, Model model)
	    throws IllegalArgumentException, IllegalAccessException
	{
		LzWeiEnter weiEnter = weixinservice.getWeiEnterById(weiEnter_Q.getWecId());
		StringUtil.copyProperties(weiEnter_Q, weiEnter);
//		LzCustomerEnterprise enter = webuserservice.getCustEnteById(weiEnter.getWecEnterId()); 
//		if(enter!=null)
//		{
//			weiEnter.setWecEnterName_Q(enter.getCueName());
//		}
//		else
//		{
//			weiEnter.setWecEnterName_Q("内部");
//		}
		model.addAttribute("command", weiEnter);
		return "/weixin/updWeiEnter";
	} 
	
	
	@RequestMapping(value = "/updWeiEnter", method = RequestMethod.POST)
	public String updWeiEnter(@ModelAttribute("command")LzWeiEnter weiEnter,HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException
	{
		String[] keyWrds = request.getParameterValues("keyWrd");
		String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		if(weiEnter.getWecCusType().equals("1"))
		{
			weiEnter.setWecEnterId(0);
		}

		weixinservice.updWeiEnter(weiEnter);
		//if(keyWrdMsgIds!=null&&keyWrdMsgIds.length>0)
		//{
			weixinservice.addKeyWordsByEnter(weiEnter,keyWrds,keyWrdMsgIds, admin);
		//}
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnter"));
		return "redirect:/admin/toMsg";
	} 
	
	
	
	@RequestMapping(value = "/toAddWeiEnterByE/{enterId}")
	public String toAddWeiEnterByE(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable Integer enterId,Model model)
	{
		WcWebsite webSite = websiteservice.getWebSiteById(enterId);
		weiEnter.setWecEnterName_Q(webSite.getWcsWebSiteName());
		weiEnter.setWecEnterId(enterId);
		weiEnter.setWecCusType("2");
		model.addAttribute("command",weiEnter);
		return "/weixin/addWeiEnterByE";
	}
	
	/**
	 * 添加微信公众号保存
	 * */
	@RequestMapping("/addWeiEnterByE/{enterId}")
	public String addWeiEnterByE(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable Integer enterId,HttpSession session,HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		//查重
		if(weixinservice.checkAppExsit(weiEnter))
		{
			redirectAttributes.addFlashAttribute("alertMsg", "当前应用同类型下已经存在不能重复添加!");
			return "redirect:/weixin/queryWeixinEnterBySite/"+enterId;
		}
		String[] keyWrds = request.getParameterValues("keyWrd");
		String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
		if(weiEnter.getWecCusType().equals("1"))
		{
			weiEnter.setWecEnterId(0);
		}
		weiEnter.setWecStatus("1000");
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		weiEnter.setWecRegistor(admin.getWadId());
		weixinservice.addWeiEnter(weiEnter);
		weixinservice.addKeyWordsByEnter(weiEnter,keyWrds,keyWrdMsgIds, admin);
		redirectAttributes.addFlashAttribute("alertMsg", "添加应用成功!");
		return "redirect:/weixin/queryWeixinEnterBySite/"+enterId;
	}
	
	@RequestMapping("/delWeiEnterByE/{enterId}")
	public String delWeiEnter(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable Integer enterId,HttpServletRequest request, RedirectAttributes redirectAttributes)
	{
		String[] wecIds = weiEnter.getWecIds();
		weixinservice.deleteWeiEnter(wecIds);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnterBySite/"+enterId));
		return "redirect:/admin/toMsg";
	}
	
	
	/**
	 * 跳转至修改微信账号资料
	 * */
	@RequestMapping(value = "/toUpdWeiEnterByE", method = RequestMethod.POST)
	public String toUpdWeiEnterByE(@ModelAttribute("command")LzWeiEnter weiEnter_Q, Model model)
	    throws IllegalArgumentException, IllegalAccessException
	{
		LzWeiEnter weiEnter = weixinservice.getWeiEnterById(weiEnter_Q.getWecId());
		StringUtil.copyProperties(weiEnter_Q,  weiEnter);
		WcWebsite website = websiteservice.getWebSiteById(weiEnter_Q.getWecEnterId()); 
		if(weiEnter!=null)
		{
			weiEnter.setWecEnterName_Q(website.getWcsWebSiteName());
		}
		else
		{
			weiEnter.setWecEnterName_Q("内部");
		}
		model.addAttribute("command", weiEnter);
		return "/weixin/updWeiEnterByE";
	} 
	
	@RequestMapping(value = "/updWeiEnterByE/{enterId}", method = RequestMethod.POST)
	public String updWeiEnterByE(@ModelAttribute("command")LzWeiEnter weiEnter,@PathVariable Integer enterId,HttpServletRequest request,HttpSession session, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException
	{
		String[] keyWrds = request.getParameterValues("keyWrd");
		String[] keyWrdMsgIds = request.getParameterValues("keyWrdmsgId");
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		if(weiEnter.getWecCusType().equals("1"))
		{
			weiEnter.setWecEnterId(0);
		}
		weixinservice.updWeiEnter(weiEnter);
		weixinservice.addKeyWordsByEnter(weiEnter,keyWrds,keyWrdMsgIds, admin);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/weixin/queryWeixinEnterBySite/"+enterId));
		return "redirect:/admin/toMsg";
	} 
	
	@RequestMapping("/queryJsApiTicket")
	public String queryJsApiTicket(@ModelAttribute("command") LzWeiJsapiTicket ticket,Model model) throws Exception
	{
		List<LzWeiEnter> list = weixinservice.queryWeixinEnterList();
		String wjtWecId_Q = ticket.getWatWecId_Q();
		Integer	wecId = 0;
		if(wjtWecId_Q==null||wjtWecId_Q.length()==0)
		{
			wecId = list.get(0).getWecId();
		}
		else
		{
			wecId = Integer.parseInt(wjtWecId_Q);
		}
		LzWeiJsapiTicket ticketCur = weixinservice.getCurrentTikcet(wecId);
		if(ticketCur!=null)
		{
			model.addAttribute(SysConstant.JS_API_TICKET_EXPIRE, DateUtil.parseString(DateUtil.parseDate(ticketCur.getWjtExpiresIn(), "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
			model.addAttribute(SysConstant.JS_API_TICKET, ticketCur.getWjtJsapiTicket());
		}
		
		model.addAttribute(SysConstant.PAGE_RESULT, weixinservice.queryJsApiTicket(ticket));
		model.addAttribute("watApp",list);
		return "/weixin/queryJsApiTicket";
	}
	
	@RequestMapping("/setJsApiTicket")
	public String setJsApiTicket(@ModelAttribute("command") LzWeiJsapiTicket ticket ,RedirectAttributes redirectattributes)
	{
		try
		{
			Integer watWedId = Integer.parseInt(ticket.getWatWecId_Q());
			weixinservice.updJsApiTicket(watWedId);
			redirectattributes.addFlashAttribute("command", ticket);
			redirectattributes.addFlashAttribute("alertMsg", "手动更新JsApiTicket成功!");
			return "redirect:/weixin/queryJsApiTicket";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
