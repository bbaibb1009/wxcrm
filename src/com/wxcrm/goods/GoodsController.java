package com.wxcrm.goods;

import java.util.Date;

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

import com.wxcrm.sys.IAdminService;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;

@Controller
@RequestMapping("/goods")
public class GoodsController 
{
	@Autowired
	private IGoodsService goodsService;
	
	@Autowired
	private IAdminService adminservice;
	
	@RequestMapping(value = "/queryGoodsFenlei/{wcsId}")
	public String queryGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,@PathVariable Integer wcsId,Model model)
	{
		feilei.setWgfWcsId(wcsId);
		model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoodsFeilei(feilei));
		return "/goods/queryGoodsFenlei";
	}
	
	@RequestMapping(value = "/toAddGoodsFenlei")
	public String toAddGoodsFenlei(@ModelAttribute("command") WcGoodsFenlei feilei,Model model)
	{
		return "/goods/addGoodsFeilei";
	}

	@RequestMapping(value = "/addGoodsFenlei")
	public String addWebsite(@ModelAttribute("command") WcGoodsFenlei fenlei,HttpSession session, RedirectAttributes redirectAttribute)
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO_WX);
		fenlei.setWgfRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		fenlei.setWgfStatus("1000");
		fenlei.setWgfRegistor(admin.getWadId());
		goodsService.addGoodsFenlei(fenlei);
		redirectAttribute.addFlashAttribute("alertMsg", "添加商品分类成功!");
		return "redirect:/goods/queryWebsite";
	}
	
	@RequestMapping(value = "/toUpdGoodsFenlei" ,method = RequestMethod.POST)
	public String toUpdWebSite(WcGoodsFenlei Fenlei_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		WcGoodsFenlei fenlei = goodsService.getFeileiById(Fenlei_Q.getWgfId());
		StringUtil.copyProperties(Fenlei_Q, fenlei);
		model.addAttribute("command", fenlei);
		return "/goods/updGoodsFenlei";
	} 
	
	@RequestMapping("/updGoodsFenlei")
	public String updWebSite(@ModelAttribute("command") WcGoodsFenlei Fenlei,HttpServletRequest request,RedirectAttributes redirectAttribute) throws IllegalArgumentException, IllegalAccessException
	{

		goodsService.updGoodsFenlei(Fenlei);
		redirectAttribute.addFlashAttribute("msgCode", "2");
		redirectAttribute.addFlashAttribute("alertMsg", "站点修改成功");
		redirectAttribute.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/website/queryWebsite", Fenlei));
		return "redirect:/admin/toMsg";
	}
	
	@RequestMapping(value = "/queryGoods")
	public String queryGoods(@ModelAttribute("command") WcGoods goods,Model model)
	{
		//model.addAttribute(SysConstant.PAGE_RESULT, goodsService.queryGoods(goods));
		return "/goods/queryGoods";
	}
	
	
	
	
}
