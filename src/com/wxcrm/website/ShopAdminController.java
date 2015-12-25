package com.wxcrm.website;

import java.io.IOException;
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
@RequestMapping("/shopadmin")
public class ShopAdminController 
{
	@Autowired
	private IShopAdminService adminShopService;
	
	@Autowired
	private IShopRoleService roleShopService;
	
	@Autowired 
	private IMemcachedService memcachedservice;
	
	@RequestMapping("/toMsg")
	public String toMsg()
	{
		return "/common/msg";
	}

	
	@RequestMapping(value = "/queryShopAdmin")
	public String queryShopAdmin(@ModelAttribute("command") WcShopAdmin admin,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, adminShopService.queryShopAdmin(admin));
		return "/website/queryShopAdmin";
	}
	
	@RequestMapping(value ="/toAddShopAdmin", method = RequestMethod.GET)
	public String toAddShopAdmin(@ModelAttribute("command") WcShopAdmin admin, Model model)
	{
		model.addAttribute("roleList", roleShopService.queryShopRoleForAdminAdd());
		return "/website/addShopAdmin";
	}
	
	
	
	@RequestMapping(value ="/addShopAdmin", method = RequestMethod.POST)
	public String addShopAdmin(WcShopAdmin admin, HttpServletRequest request, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		adminShopService.addShopAdmin(admin);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "管理员添加成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shopadmin/queryShopAdmin"));
		return "redirect:/admin/toMsg";
	}

	@RequestMapping("/chkUsernameUnique/{adminId}/{username}")
	@ResponseBody
	public Map<String, Object> chkUsernameUnique(@PathVariable Integer adminId, @PathVariable String username 	) throws UnsupportedEncodingException
	{
	
		String name = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", adminShopService.chkShopUsernameUnique(adminId, username));
		map.put("name", name);
		return map;
	}
	
	@RequestMapping(value = "/toUpdShopAdmin" ,method = RequestMethod.POST)
	public String toUpdAdmin(WcShopAdmin admin_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopAdmin admin = adminShopService.getShopAdminById(admin_Q.getWsaId());
		StringUtil.copyProperties(admin_Q, admin);
		model.addAttribute("roleList0", roleShopService.queryShopRoleForAdminUpd0(admin_Q.getWsaId()));
		model.addAttribute("roleList1", roleShopService.queryShopRoleForAdminUpd1(admin_Q.getWsaId()));
		model.addAttribute("command", admin);
		return "/website/updShopAdmin";
	} 
	
	@RequestMapping(value = "/updShopAdmin", method = RequestMethod.POST)
	public String updAdmin(WcShopAdmin admin, HttpServletRequest request, RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		adminShopService.updShopAdmin(admin);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "管理员修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shopadmin/queryShopAdmin", admin));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping("/getShopAdminNameList")
	@ResponseBody
	public List<Map<String, Object>> getShopAdminNameAll() 
	{
		return memcachedservice.getShopAdminNameAll();
	}
	
}
