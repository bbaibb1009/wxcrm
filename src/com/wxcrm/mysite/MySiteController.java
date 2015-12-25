package com.wxcrm.mysite;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxcrm.common.IMemcachedService;
import com.wxcrm.goods.IGoodsService;
import com.wxcrm.goods.WcGoods;
import com.wxcrm.goods.WcGoodsFenlei;
import com.wxcrm.goods.WcGoodsIn;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.SessionUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.util.ThreeDes;
import com.wxcrm.website.IWebsiteService;
import com.wxcrm.website.WcWebsite;
import com.wxcrm.weixin.IWeixinService;
import com.wxcrm.weixin.LzWeiEnter;

@Controller
@RequestMapping("/mysite")
public class MySiteController 
{
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IWeiMemberService weimemberservice;
	
	@Autowired
	private IWeixinService weixinservice;
	
	@Autowired
	private IWebsiteService websiteservice;
	
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping("/login")
	public String adminLogin(@ModelAttribute("command") WcAdmin admin, 
			HttpServletResponse response, HttpSession session, Model model, 
			@CookieValue(value = SysConstant.ADMIN_USERNAME_WX, required = false) String usernameCookie, 
			@CookieValue(value = SysConstant.ADMIN_PWD_MD5_WX, required = false) String pwdMd5Cookie,
			@CookieValue(value = SysConstant.ADMIN_PWD_3DES_WX, required = false) String pwd3desCookie,
			@CookieValue(value = SysConstant.LOGIN_REDIRECT_URI_WX, required = false) String rediUriCookie)
	{
		// cookie登陆
		if( usernameCookie != null && pwdMd5Cookie != null && pwd3desCookie != null )
		{
			admin.setWadUsername(usernameCookie);
			admin.setWadPwdMd5(pwdMd5Cookie);
			admin.setWadPwd(ThreeDes.decode(pwd3desCookie));
		}
		else 
		{
			// 跳转登陆页面
			if( admin.getWadUsername() == null )
			{
				return "/mysite/login/login";
			}
			// 页面登陆
			else 
			{
				admin.setWadPwdMd5(DigestUtils.md5DigestAsHex(admin.getWadPwd().getBytes()));
			}
		}
		
		if(adminService.chkUsernameUnique(admin.getWadUsername()))
		{
			model.addAttribute("usernameError", "用户名不存在，是否注册？");
			return "/mysite/login/login";
		}
		WcAdmin adminResult = adminService.adminLogin(admin); 
		if( adminResult == null )
		{
			model.addAttribute("alertMsg", "密码错误");
			return "/mysite/login/login";
		}
		else
		{
			adminResult.setWadPwdMd5(adminResult.getWadPwd());
			adminResult.setWadPwd(admin.getWadPwd());
			if(admin.isRemember())
			{
				Cookie cookieUsername = new Cookie(SysConstant.ADMIN_USERNAME_WX, adminResult.getWadUsername());
				cookieUsername.setMaxAge(SysConstant.COOKIE_AGE);
				cookieUsername.setPath("/");
				response.addCookie(cookieUsername);
				Cookie cookiePwdMd5 = new Cookie(SysConstant.ADMIN_PWD_MD5_WX, adminResult.getWadPwdMd5());
				cookiePwdMd5.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwdMd5.setPath("/");
				response.addCookie(cookiePwdMd5);
				Cookie cookiePwd3des = new Cookie(SysConstant.ADMIN_PWD_3DES_WX, ThreeDes.encode(adminResult.getWadPwd()));
				cookiePwd3des.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwd3des.setPath("/");
				response.addCookie(cookiePwd3des);
			}
			List<Map<String, Object>> menuList1 = adminService.queryAdminMenus(adminResult.getWadId(), "1");
			List<Map<String, Object>> menuList2 = adminService.queryAdminMenus(adminResult.getWadId(), "2");
			List<Map<String, Object>> menuList3 = adminService.queryAdminMenus(adminResult.getWadId(), "3");
			session.setAttribute(SysConstant.ADMIN_MENU_ID1_WX, menuList1.get(0).get("WME_ID").toString());
			session.setAttribute(SysConstant.ADMIN_MENU_ID2_WX, -1);
			session.setAttribute(SysConstant.ADMIN_MENU_ID3_WX, -1);
			session.setAttribute(SysConstant.ADMIN_INFO_WX, adminResult);
			
			WcWebsite webSite = websiteservice.queryMySiteByAdmin(adminResult.getWadId());
			session.setAttribute(SysConstant.WEBSITE_INFO, webSite);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL1_WX, menuList1);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL2_WX, menuList2);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL3_WX, menuList3);
			SessionUtil.removeAdminSession(session);
			adminService.updLoginTime(adminResult.getWadId());
			if( rediUriCookie == null )
			{
				return "redirect:/mysite/adminLoginSuccess";
			}
			else 
			{
				Cookie cookieUri = new Cookie(SysConstant.LOGIN_REDIRECT_URI_WX, null);
				cookieUri.setMaxAge(0);
				cookieUri.setPath("/");
				response.addCookie(cookieUri);
				return "redirect:" + rediUriCookie;
			}
		}
	}
	
	private boolean chkAdminInfo(WcAdmin admin)
	{
		boolean result = true;
		
		return result;
	}

	@RequestMapping("/adminLoginSuccess")
	public String adminLoginSuccess(Model model, HttpSession session)
	{
		WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO_WX);
		//查出当前管理员对应的商家是什么
		WcWebsite mysite = websiteservice.queryMySiteByAdmin(admin.getWadId());
		model.addAttribute("mysite", mysite);
		
		return "/mysite/login/adminLoginSuccess";
	}
	
	
	@RequestMapping("/queryMyMember")
	public String queryMyMember(@ModelAttribute("command") LzWeiMember member,HttpSession session,Model model)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO_WX);
		LzWeiEnter wec = weixinservice.getWeiEnterByAdminId(admin.getWadId());
		if(wec!=null)
		{
			member.setWmbWecId(wec.getWecId());
		}
		model.addAttribute(SysConstant.PAGE_RESULT, weimemberservice.queryWeiMember(member));
		return "/mysite/member/queryMember";
	}
	
	
	/**
	 * 商品分类管理
	 * */
	@RequestMapping(value = "/queryGoodsFenlei")
	public String queryGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,HttpSession session,Model model)
	{
		WcWebsite website = (WcWebsite)session.getAttribute(SysConstant.WEBSITE_INFO);
		feilei.setWgfWcsId(website.getWcsId());
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsFeilei(feilei));
		return "/goods/queryGoodsFenlei";
	}
	
	@RequestMapping(value = "/toAddGoodsFenlei/{wcsId}")
	public String toAddGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,@PathVariable Integer wcsId,Model model)
	{
		feilei.setWgfWcsId(wcsId);
		return "/goods/addGoodsFenlei";
	}

	@RequestMapping(value = "/addGoodsFenlei")
	public String addGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei fenlei,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO_WX);
		fenlei.setWgfRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		fenlei.setWgfStatus("1000");
		fenlei.setWgfRegistor(admin.getWadId());
		goodsService.addGoodsFenlei(fenlei);
		redirectAttribute.addFlashAttribute("alertMsg", "添加商品分类成功!");
		return "redirect:/mysite/queryGoodsFenlei";
	}
	
	@RequestMapping(value = "/toUpdGoodsFenlei" ,method = RequestMethod.POST)
	public String toUpdGoodsFenlei(WcGoodsFenlei Fenlei_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoodsFenlei fenlei = goodsService.getFeileiById(Fenlei_Q.getWgfId());
		StringUtil.copyProperties(Fenlei_Q, fenlei);
		model.addAttribute("command", fenlei);
		return "/goods/updGoodsFenlei";
	} 
	
	@RequestMapping("/updGoodsFenlei")
	public String updGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei Fenlei,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{

		goodsService.updGoodsFenlei(Fenlei);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "站点修改成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoodsFenlei", Fenlei));
		return "redirect:/admin/toMsg";
	}
	

	@RequestMapping(value = "/delGoodsFenlei/{wcsId}")
	public String delGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei fenlei,@PathVariable Integer wcsId,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		fenlei.setWgfWcsId(wcsId);
		goodsService.delGoodsFenlei(fenlei);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品分类删除成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoodsFenlei", fenlei));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/queryGoods/{wfgId}")
	public String queryGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wfgId,Model model)
	{
		goods.setWgsWgfId(wfgId);
		WcGoodsFenlei fenlei = goodsService.getFeileiById(wfgId);
		model.addAttribute("fenlei", fenlei);
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoods(goods));
		return "/goods/queryGoods";
	}
	
	@RequestMapping(value = "/toAddGoods/{wgfId}")
	public String toAddGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wgfId,Model model)
	{
		WcGoodsFenlei fenlei = goodsService.getFeileiById(wgfId);
		goods.setWgsWgfId(wgfId);
		goods.setWgsWcsId(fenlei.getWgfWcsId());
		goods.setWgfName(fenlei.getWgfName());
		return "/goods/addGoods";
	}
	
	@RequestMapping(value = "/addGoods")
	public String addGoodsFenlei(@ModelAttribute("command") WcGoods goods,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO_WX);
		goods.setWgsKucun(0.0);
		goods.setWgsRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goods.setWgsStatus("1000");
		goods.setWgsRegistor(admin.getWadId());
		goodsService.addGoods(goods);
		redirectAttribute.addFlashAttribute("alertMsg", "添加商品成功!");
		return "redirect:/mysite/queryGoods/"+goods.getWgsWgfId();
	}
	
	
	@RequestMapping(value = "/toUpdGoods" ,method = RequestMethod.POST)
	public String toUpdGoods( WcGoods goods_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsFenlei fenlei = goodsService.getFeileiById(goods.getWgsWgfId());
		StringUtil.copyProperties(goods_Q, goods);
		model.addAttribute("fenlei", fenlei);
		model.addAttribute("command", goods);
		return "/goods/updGoods";
	} 
	
	@RequestMapping(value="/updGoods",method = RequestMethod.POST)
	public String updGoods(@ModelAttribute("command") WcGoods goods,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		goodsService.updGoods(goods);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品修改成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoods/"+goods.getWgsWgfId(), goods));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/delGoods/{wgfId}")
	public String delGoods(@ModelAttribute("command") WcGoods goods,@PathVariable Integer wgfId,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{
		goods.setWgsWgfId(wgfId);
		goodsService.delGoods(goods);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "商品删除成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/mysite/queryGoods/"+goods.getWgsWgfId(), goods));
		return "redirect:/admin/toMsg";
	}
	
	
	@RequestMapping(value = "/toInGoods" ,method = RequestMethod.POST)
	public String toInGoods(WcGoods goods_Q,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoods goods = goodsService.getGoodsById(goods_Q.getWgsId());
		WcGoodsIn goodsIn = new WcGoodsIn();
		goodsIn.setWgiInTime(DateUtil.parseString(new Date(), "yyyy-MM-dd"));
		goodsIn.setWgiWgsId(goods.getWgsId());
		goodsIn.setWgiWcsId(goods.getWgsWcsId());
		model.addAttribute("goods", goods);
		model.addAttribute("command", goodsIn);
		return "/goods/addInGoods";
	} 
	
	@RequestMapping(value = "/addInGoods")
	public String addInGoods(@ModelAttribute("command") WcGoodsIn goodsIn,HttpSession session,RedirectAttributes redirectAttribute)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO_WX);
		goodsIn.setWgiInAdmin(admin.getWadId());
		goodsIn.setWgiSurplusNum(goodsIn.getWgiInNum());
		goodsIn.setWgiStatus("1000");
		goodsIn.setWgiRegistor(admin.getWadId());
		goodsIn.setWgiRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		goodsService.addGoodsIn(goodsIn);
		redirectAttribute.addFlashAttribute("alertMsg", "入库成功!");
		WcGoods goods = goodsService.getGoodsById(goodsIn.getWgiWgsId());
		return "redirect:/mysite/queryGoods/"+goods.getWgsWgfId();
	}
	
	
}
