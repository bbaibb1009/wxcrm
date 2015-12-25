package com.wxcrm.weixin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.pojo.LzWeiMenu;


@Controller
@RequestMapping("/weixinmenu")
public class WeixinMenuController {

	@Autowired
	private IWeixinMenuService weixinMenuService;
	
	@Autowired
	private IWeixinService weixinservice;
	
	@Autowired
	private IWeixinEnterService weixinenterservice;

	
	private static Logger log = Logger.getLogger(WeixinMenuController.class);

	
	@RequestMapping(value = "/queryMenu")
	public String queryMenu(@ModelAttribute("command") LzWeiMenu menu,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT, weixinMenuService.queryMenu(menu));
		return "/weixin/queryMenu";
	}
	
	@RequestMapping(value = "/toAddMenu")
	public String toAddMenu(@ModelAttribute("command") LzWeiMenu menu,Model model)
	{
		return "/weixin/addMenu";
	}
	
	
	@RequestMapping(value = "/addMenu")
	public String addMenu(@ModelAttribute("command") LzWeiMenu menu, RedirectAttributes redirectattributes) throws Exception
	{
		String  appId_curent = menu.getWmuAppId();
		String  appSecret_current = menu.getWmuAppSecret();
		LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId_curent);
		String 	jsonMenu = menu.getWmuJson();
		String accessTokenStr = weixinservice.getCurrentAccessTokenStr(enter.getWecId());  
	    if (null != accessTokenStr) {  
	        // ���ýӿڴ����˵�  
	        // int result = WeixinUtil.createMenu(getMenu(),at.getWatToken());  
	        int result = weixinMenuService.addMenu(menu,accessTokenStr); 	
	        // �жϲ˵��������  
	        if (0 == result)  
	        {
	        	log.info("�˵������ɹ���");
	        	redirectattributes.addFlashAttribute("alertMsg", "�˵������ɹ���");	
	        }
	        else
	        {	
	            log.info("�˵�����ʧ�ܣ������룺" + result);
	            redirectattributes.addFlashAttribute("alertMsg", "�˵�����ʧ�ܣ������룺" + result);
	        }
	    }
	    return "redirect:/weixinmenu/queryMenu";
	}
	
	
	
	@RequestMapping(value = "/addMenuByEnter")
	public String addMenuByEnter(@ModelAttribute("command") LzWeiMenu menu, RedirectAttributes redirectattributes) throws Exception
	{
		String  appId_curent = menu.getWmuAppId();
		String  appSecret_current = menu.getWmuAppSecret();
		LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId_curent);
		String 	jsonMenu = menu.getWmuJson();
		String accessTokenStr = weixinservice.getCurrentAccessTokenStr(enter.getWecId());  
	    if (null != accessTokenStr) {  
	        // ���ýӿڴ����˵�  
	        // int result = WeixinUtil.createMenu(getMenu(),at.getWatToken());  
	        int result = weixinMenuService.addMenu(menu,accessTokenStr); 	
	        // �жϲ˵��������  
	        if (0 == result)  
	        {
	        	log.info("�˵������ɹ���");
	        	redirectattributes.addFlashAttribute("alertMsg", "�˵������ɹ���");	
	        }
	        else
	        {	
	            log.info("�˵�����ʧ�ܣ������룺" + result);
	            redirectattributes.addFlashAttribute("alertMsg", "�˵�����ʧ�ܣ������룺" + result);
	        }
	    }
	    return "redirect:/weixin/queryWeixinEnter";
		
	}
	
	
	@RequestMapping(value = "/toUpdMenu" ,method = RequestMethod.POST)
	public String toUpdAdmin(LzWeiMenu menu_Q, Model model) throws IllegalArgumentException, IllegalAccessException
	{
		LzWeiMenu menu = weixinMenuService.getMenuById(menu_Q.getWmuId());
		StringUtil.copyProperties(menu_Q, menu);
		model.addAttribute("command", menu);
		return "/weixin/updMenu";
	} 
	
	@RequestMapping("/toUpdWeiXinMenu/{wecId}")
	public String toUpdWeiXinMenu(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable Integer wecId,Model model) 
	{
		weiEnter = weixinservice.getWeiEnterById(wecId);
		LzWeiMenu menu = weixinMenuService.getMenuByAppId(weiEnter.getWecAppId());
		
		if(menu!=null)
		{
			menu.setWmuWecId(wecId);
			menu.setWmuAppSecret(weiEnter.getWecAppSecret());
			model.addAttribute("weiEnter", weiEnter);
			model.addAttribute("command", menu);
			return "/weixin/updMenuByWeiEnter";
		}
		else
		{
			menu = new LzWeiMenu();
			menu.setWmuAppId(weiEnter.getWecAppId());
			menu.setWmuWecId(wecId);
			model.addAttribute("weiEnter", weiEnter);
			model.addAttribute("command", menu);
			return "/weixin/addMenuByWeiEnter";
		}
	}
	
	
//	/** 
//     * ��װ�˵����� 
//     * @return 
//     */  
//    private  Menu getMenu() 
//    {  
//        ViewButton btn11 = new ViewButton();  
//        btn11.setName("����ʯ��ͨ");  
//        btn11.setType("view");  
//        btn11.setUrl("http://info.oilchem.net/lzsht/index.html"); 
//  
//        ViewButton btn12 = new ViewButton();  
//        btn12.setName("����");  
//        btn12.setType("view");  
//        btn12.setUrl("http://mp.weixin.qq.com/s?__biz=MzA3MDU3OTgzMA==&mid=201343703&idx=1&sn=6f9d66051dbf12b91de5291774fa30f4#rd");  
//
//        ComplexButton mainBtn1 = new ComplexButton();  
//        mainBtn1.setName("ʯ��ͨ");  
//        mainBtn1.setSub_button(new Button[] {btn11, btn12});  
//  
//        ViewButton btn13 = new ViewButton();  
//        btn13.setName("����");  
//        btn13.setType("view");  
//        btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2ba0ce17184b93b7&redirect_uri=http%3A%2F%2Finfo.oilchem.net%2Fweixin%2FtoZhongjianYeMian&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");  
//  
//        ViewButton btn14 = new ViewButton();  
//        btn14.setName("��Ѷͨ");  
//        btn14.setType("view");  
//        btn14.setUrl("http://img.oilchem.net/2014/lzdxt/"); 
//         
//        Menu menu = new Menu();  
//        menu.setButton(new Button[] { mainBtn1, btn13, btn14});  
//        return menu;  
//    }
	
	
}
