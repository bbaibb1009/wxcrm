package com.wxcrm.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxcrm.common.IMemcachedService;
import com.wxcrm.util.SessionUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.util.ThreeDes;

@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@RequestMapping("/adminLogin")
	public String adminLogin(@ModelAttribute("command") WcAdmin admin, 
			HttpServletResponse response, HttpSession session, Model model, 
			@CookieValue(value = SysConstant.ADMIN_USERNAME, required = false) String usernameCookie, 
			@CookieValue(value = SysConstant.ADMIN_PWD_MD5, required = false) String pwdMd5Cookie,
			@CookieValue(value = SysConstant.ADMIN_PWD_3DES, required = false) String pwd3desCookie,
			@CookieValue(value = SysConstant.LOGIN_REDIRECT_URI, required = false) String rediUriCookie)
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
				return "/sys/adminLogin";
			}
			// 页面登陆
			else 
			{
				admin.setWadPwdMd5(DigestUtils.md5DigestAsHex(admin.getWadPwd().getBytes()));
			}
		}
		
		if( adminService.chkUsernameUnique(admin.getWadUsername()) )
		{
			model.addAttribute("usernameError", "用户名不存在");
			return "/sys/adminLogin";
		}
		WcAdmin adminResult = adminService.adminLogin(admin); 
		if( adminResult == null )
		{
			model.addAttribute("alertMsg", "密码错误");
			return "/sys/adminLogin";
		}
		else
		{			
			adminResult.setWadPwdMd5(adminResult.getWadPwd());
			adminResult.setWadPwd(admin.getWadPwd());
			if( admin.isRemember() )
			{
				Cookie cookieUsername = new Cookie(SysConstant.ADMIN_USERNAME, adminResult.getWadUsername());
				cookieUsername.setMaxAge(SysConstant.COOKIE_AGE);
				cookieUsername.setPath("/");
				response.addCookie(cookieUsername);
				Cookie cookiePwdMd5 = new Cookie(SysConstant.ADMIN_PWD_MD5, adminResult.getWadPwdMd5());
				cookiePwdMd5.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwdMd5.setPath("/");
				response.addCookie(cookiePwdMd5);
				Cookie cookiePwd3des = new Cookie(SysConstant.ADMIN_PWD_3DES, ThreeDes.encode(adminResult.getWadPwd()));
				cookiePwd3des.setMaxAge(SysConstant.COOKIE_AGE);
				cookiePwd3des.setPath("/");
				response.addCookie(cookiePwd3des);
			}
			List<Map<String, Object>> menuList1 = adminService.queryAdminMenus(adminResult.getWadId(), "1");
			List<Map<String, Object>> menuList2 = adminService.queryAdminMenus(adminResult.getWadId(), "2");
			List<Map<String, Object>> menuList3 = adminService.queryAdminMenus(adminResult.getWadId(), "3");
			session.setAttribute(SysConstant.ADMIN_INFO, adminResult);//session存放当前的管理员信息
			session.setAttribute(SysConstant.ADMIN_MENU_ID1, menuList1.get(0).get("WME_ID").toString());
			session.setAttribute(SysConstant.ADMIN_MENU_ID2, -1);
			session.setAttribute(SysConstant.ADMIN_MENU_ID3, -1);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL1, menuList1);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL2, menuList2);
			session.setAttribute(SysConstant.ADMIN_MENUS_LEVEL3, menuList3);
			//清空前台相关的登陆信息
			SessionUtil.removeMySiteSession(session);
			adminService.updLoginTime(adminResult.getWadId());
			if( rediUriCookie == null )
			{
				return "redirect:/admin/adminLoginSuccess";
			}
			else 
			{
				Cookie cookieUri = new Cookie(SysConstant.LOGIN_REDIRECT_URI, null);
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
	
	
	@RequestMapping("/toMsg")
	public String toMsg()
	{
		return "/common/msg";
	}
	
	
	
	
	
	@RequestMapping(value = "/queryAdmin")
	public String queryAdmin(@ModelAttribute("command") WcAdmin admin,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, adminService.queryAdmin(admin));
		return "/sys/queryAdmin";
	}
	
	@RequestMapping(value ="/toAddAdmin", method = RequestMethod.GET)
	public String toAddAdmin(@ModelAttribute("command") WcAdmin admin, Model model)
	{
		model.addAttribute("roleList", roleService.queryRoleForAdminAdd());
		return "/sys/addAdmin";
	}
	
	
	
	@RequestMapping(value ="/addAdmin", method = RequestMethod.POST)
	public String addAdmin(WcAdmin admin, HttpServletRequest request, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
//		if( ! chkAdmin(admin) )
//		{
//			return null;
//		}
		adminService.addAdmin(admin);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "管理员添加成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/admin/queryAdmin"));
		return "redirect:/admin/toMsg";
	}

	@RequestMapping("/chkUsernameUnique/{adminId}/{username}")
	@ResponseBody
	public Map<String, Object> chkUsernameUnique(@PathVariable Integer adminId, @PathVariable String username 	) throws UnsupportedEncodingException
	{
	
		String name = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", adminService.chkUsernameUnique(adminId, username));
		map.put("name", name);
		return map;
	}
	
	@RequestMapping("/adminLoginSuccess")
	public String adminLoginSuccess(Model model, HttpSession session)
	{
		WcAdmin admin = (WcAdmin) session.getAttribute(SysConstant.ADMIN_INFO);
//		List<Map<String, Object>> dept2List = adminService.queryDept2Info(admin.getWadId());
//		if( dept2List.size() > 0 )
//		{
//			List<Map<String, Object>> dept2AdminList = adminService.queryDept2AdminInfo(admin.getAdminId());
//			model.addAttribute("dept2List", dept2List);
//			model.addAttribute("dept2AdminList", dept2AdminList);
//		}
//		List<Map<String, Object>> tongzhiList = tongzhiService.queryTongzhi();
		//model.addAttribute("tongzhiList", tongzhiList);
		return "/sys/adminLoginSuccess";
	}
	
	@RequestMapping("/goUrl/{menuId1}/{menuId2}/{menuId3}/{url}")
	public String goUrl(@PathVariable Integer menuId1, @PathVariable Integer menuId2, 
			@PathVariable Integer menuId3, @PathVariable String url, HttpSession session)
	{
		session.setAttribute(SysConstant.ADMIN_MENU_ID1, menuId1);
		session.setAttribute(SysConstant.ADMIN_MENU_ID2, menuId2);
		session.setAttribute(SysConstant.ADMIN_MENU_ID3, menuId3);
		
		return "redirect:" + url.replace("|", "/");
	}
	
	@RequestMapping(value = "/toUpdAdmin" ,method = RequestMethod.POST)
	public String toUpdAdmin(WcAdmin admin_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcAdmin admin = adminService.getAdminById(admin_Q.getWadId());
		StringUtil.copyProperties(admin_Q, admin);
		//admin.setDelFlagOld(admin.getDelFlag());
		model.addAttribute("roleList0", roleService.queryRoleForAdminUpd0(admin_Q.getWadId()));
		model.addAttribute("roleList1", roleService.queryRoleForAdminUpd1(admin_Q.getWadId()));
		//model.addAttribute("officeList", dicconfigservice.queryDcfByType("OFFICE"));
		model.addAttribute("command", admin);
		
		return "/sys/updAdmin";
	} 
	
	@RequestMapping(value = "/updAdmin", method = RequestMethod.POST)
	public String updAdmin(WcAdmin admin, HttpServletRequest request, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
//		if( ! chkAdmin(admin) )
//		{
//			return null;
//		}
		adminService.updAdmin(admin);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "管理员修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/admin/queryAdmin", admin));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping("/getAdminNameList")
	@ResponseBody
	public List<Map<String, Object>> getAdminNameList() 
	{
		return memcachedservice.getAdminNameAll();
	}
	
	@RequestMapping("/adminLogout")
	public void adminLogout(HttpServletResponse response, HttpSession session) throws IOException
	{
		session.invalidate();
		PrintWriter writer = response.getWriter();
		writer.write("<script type='text/javascript'>window.close();</script>");
		writer.close();
	}
	
}
