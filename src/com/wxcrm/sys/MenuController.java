package com.wxcrm.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	public IMenuService menuservice;
	
	@Autowired
	public IRoleService roleService;
	
	

	@RequestMapping(value = "/queryMenu")
	public String queryMenu(@ModelAttribute("command") WcMenu menu,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, menuservice.queryMenu(menu));
		return "/sys/queryMenu";
	}
	
	@RequestMapping(value = "/toAddMenu", method = RequestMethod.GET)
	public String toAddMenu(@ModelAttribute("command") WcMenu menu)
	{
		return "/sys/addMenu";
	}
	
	@RequestMapping(value = "/addMenu", method = RequestMethod.POST)
	public String addMenu(WcMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException
	{
		if( ! chkMenu(menu) )
		{
			return null;
		}
		menuservice.addMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单添加成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/menu/queryMenu"));
		return "redirect:/admin/toMsg";
	}
	
	private boolean chkMenu(WcMenu menu)
	{
		if( menu.getWmeName()== null || menu.getWmeName().trim().length() == 0 ||
			menu.getWmeLevel()== null || menu.getWmeLevel().trim().length() == 0 ||
			menu.getWmeOrder()== null )
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	/**
	 * @author wanglei
	 * created on Sep 12, 2013 10:45:20 AM
	 * @param menuLevel 二级菜单的上级菜单为一级菜单，三级菜单的上级菜单为二级菜单
	 * @param parentId menu_id为parentId的菜单为选中状态
	 * @return
	 */
	@RequestMapping("/getParentMenuTree/{menuLevel}/{parentId}")
	@ResponseBody
	public List<Map<String, Object>> getParentMenuTree(@PathVariable String menuLevel, @PathVariable String parentId)
	{
		List<Map<String, Object>> menuList = menuservice.queryMenuToCache();
		for( Map<String, Object> map : menuList )
		{
			if( parentId != null && map.get("id").toString().equals(parentId) )
			{
				map.put("checked", true);
			}	
			
			if( map.get("menuLevel").toString().equals("1") )
			{
				if( menuLevel.equals("3") )
				{	
					map.put("nocheck", true);
				}
			}
			else if( map.get("menuLevel").toString().equals("2") )
			{
				if( menuLevel.equals("2") )
				{	
					map.put("nocheck", true);
				}
			}
			else 
			{
				map.put("nocheck", true);
			}
		}
		
		return menuList;
	}
		
	@RequestMapping("/getMenuTreeForRole/{disabled}/{roleId}")
	@ResponseBody
	public List<Map<String, Object>> getMenuTreeForRole(@PathVariable String disabled, @PathVariable Integer roleId)
	{
		List<String> checkedList = null;
		if( roleId != -1 )
		{
			checkedList = roleService.queryRoleMenusById(roleId);
		}
		
		return getMenuTree(disabled, null, checkedList);
	}
	
	/**
	 * @author wanglei
	 * created on Sep 13, 2013 10:29:32 AM
	 * @param disabled 若是0则无操作，若是1则全部置灰，若是2则disabledList中的置灰
	 * @param disabledList 置灰的菜单id列表
	 * @param checkedList 默认选中的菜单id列表
	 * @return
	 */
	private List<Map<String, Object>> getMenuTree(String disabled, 
		List<String> disabledList, List<String> checkedList)
	{
		
		
		//List<Map<String, Object>> menuList = memcachedService.getMenuAll();
		List<Map<String, Object>> menuList = menuservice.queryMenuToCache();
		
		for( Map<String, Object> map : menuList )
		{
			if( disabled.equals("1") )
			{
				map.put("chkDisabled", true);
			}
			else if( disabled.equals("2") && disabledList != null && 
					disabledList.contains(map.get("id").toString()) ) 
			{
				map.put("chkDisabled", true);
			}
			
			if( checkedList != null && checkedList.contains(map.get("id").toString()) )
			{
				map.put("checked", true);
			}
		}
		
		return menuList;
	}
	
	
	@RequestMapping(value = "/delMenu", method = RequestMethod.POST)
	public String delMenu(WcMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException
	{
		if( menu.getMenuIds() == null || menu.getMenuIds().length == 0 )
		{
			return null;
		}
		menuservice.delMenu(menu.getMenuIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/menu/queryMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/toUpdMenu", method = RequestMethod.POST)
	public String toUpdMenu(WcMenu menu_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcMenu menu = menuservice.getMenuById(menu_Q.getWmeId());
		StringUtil.copyProperties(menu_Q, menu);
		model.addAttribute("command", menu);
		return "/sys/updMenu";
	}
	
	@RequestMapping(value = "/updMenu", method = RequestMethod.POST)
	public String updMenu(WcMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException 
	{
		if( ! chkMenu(menu) )
		{
			return null;
		}
		menuservice.updMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "菜单修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/menu/queryMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
}
