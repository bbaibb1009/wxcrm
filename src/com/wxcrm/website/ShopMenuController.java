package com.wxcrm.website;

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
@RequestMapping("/shopmenu")
public class ShopMenuController {

	@Autowired
	public IShopMenuService menuservice;
	
	@Autowired
	public IShopRoleService roleService;
	
	

	@RequestMapping(value = "/queryShopMenu")
	public String queryShopMenu(@ModelAttribute("command") WcShopMenu menu,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, menuservice.queryShopMenu(menu));
		return "/website/queryShopMenu";
	}
	
	@RequestMapping(value = "/toAddShopMenu", method = RequestMethod.GET)
	public String toAddShopMenu(@ModelAttribute("command") WcShopMenu menu)
	{
		return "/website/addShopMenu";
	}
	
	@RequestMapping(value = "/addShopMenu", method = RequestMethod.POST)
	public String addMenu(WcShopMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException
	{
		if( ! chkShopMenu(menu) )
		{
			return null;
		}
		menuservice.addShopMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "商铺菜单添加成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formGet(request.getContextPath() + "/shopmenu/queryShopMenu"));
		return "redirect:/admin/toMsg";
	}
	
	private boolean chkShopMenu(WcShopMenu menu)
	{
		if( menu.getWsmName()== null || menu.getWsmName().trim().length() == 0 ||
			menu.getWsmLevel()== null || menu.getWsmLevel().trim().length() == 0 ||
			menu.getWsmOrder()== null )
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
	@RequestMapping("/getShopParentMenuTree/{menuLevel}/{parentId}")
	@ResponseBody
	public List<Map<String, Object>> getShopParentMenuTree(@PathVariable String menuLevel, @PathVariable String parentId)
	{
		List<Map<String, Object>> menuList = menuservice.queryShopMenuToCache();
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
		
	@RequestMapping("/getShopMenuTreeForRole/{disabled}/{roleId}")
	@ResponseBody
	public List<Map<String, Object>> getShopMenuTreeForRole(@PathVariable String disabled, @PathVariable Integer roleId)
	{
		List<String> checkedList = null;
		if( roleId != -1 )
		{
			checkedList = roleService.queryShopRoleMenusById(roleId);
		}
		
		return getShopMenuTree(disabled, null, checkedList);
	}
	
	/**
	 * @author wanglei
	 * created on Sep 13, 2013 10:29:32 AM
	 * @param disabled 若是0则无操作，若是1则全部置灰，若是2则disabledList中的置灰
	 * @param disabledList 置灰的菜单id列表
	 * @param checkedList 默认选中的菜单id列表
	 * @return
	 */
	private List<Map<String, Object>> getShopMenuTree(String disabled, 
		List<String> disabledList, List<String> checkedList)
	{
		List<Map<String, Object>> menuList = menuservice.queryShopMenuToCache();
		
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
	
	
	@RequestMapping(value = "/delShopMenu", method = RequestMethod.POST)
	public String delShopMenu(WcShopMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException
	{
		if( menu.getMenuIds() == null || menu.getMenuIds().length == 0 )
		{
			return null;
		}
		menuservice.delShopMenu(menu.getMenuIds());
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "商铺菜单删除成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shopmenu/queryShopMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/toUpdShopMenu", method = RequestMethod.POST)
	public String toUpdShopMenu(WcShopMenu menu_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcShopMenu menu = menuservice.getShopMenuById(menu_Q.getWsmId());
		StringUtil.copyProperties(menu_Q, menu);
		model.addAttribute("command", menu);
		return "/website/updShopMenu";
	}
	
	@RequestMapping(value = "/updShopMenu", method = RequestMethod.POST)
	public String updShopMenu(WcShopMenu menu, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws IllegalArgumentException, IllegalAccessException 
	{
		if( ! chkShopMenu(menu) )
		{
			return null;
		}
		menuservice.updShopMenu(menu);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "商铺菜单修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/shopmenu/queryShopMenu", menu));
		return "redirect:/admin/toMsg";
	}
	
}
