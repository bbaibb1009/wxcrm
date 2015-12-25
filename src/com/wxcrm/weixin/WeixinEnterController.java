package com.wxcrm.weixin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oilchem.weixin.OAuth2.authorizeUtil;
import com.oilchem.weixin.mp.aes.AesException;
import com.wxcrm.member.IWeiMemberService;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.SysConstant;

@Controller
@RequestMapping("/weixine")
public class WeixinEnterController 
{
	@Autowired
	private IWeixinService weixinservice;
	
//	@Autowired
//	private IWebUserService webuserservice;
	
	@Autowired
	private IWeixinEnterService weixinEnterService;
	
	@Autowired 
	private IWeiMemberService weimemberservice;

//	@Autowired
//	private IProductService productservice;
//	
//	@Autowired
//	private IAddressService addressService;
	
	private static Logger log = Logger.getLogger(WeixinEnterController.class);
	
	///////////////////////////////////////////////////////////以下是通过按钮跳转的链接///////////////////////////////////////////////////////////////////////////////
	/**
	 * 跳转至企业简介的页面
	 * */
	@RequestMapping("/toIntroEnter/{appId}")
	public String toIntroEnterMenu(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
	{
		String code   = (String)request.getParameter("code")  == null? "" : (String)request.getParameter("code");
		String openid = weiEnter.getWecCustOpenId_Q();
		weiEnter = weixinservice.getWeiEnterByAppId(appId);
		if(code!=null&&code.length()>0)
		{
			//菜单点击:存在code，则是内部登陆的，查出用户的OpenId
			openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(),weiEnter.getWecAppSecret(),code);
		}
		else
		{
			if(openid!=null&&openid.length()>0)
			{
				//非菜单点击，且不是表单提交：外部链接点进去的，适合于分享后第三者点击页面的处理
			}
			else
			{
				//非菜单点击，但是表单提交：从页面本身的链接点击进入的
			}
		}
		weiEnter.setWecCustOpenId_Q(openid);
		//model.addAttribute("custEnter", webuserservice.getCustEnteById(weiEnter.getWecEnterId()));
		model.addAttribute("command",weiEnter);
		return "/weixine/introEnter";

	}
	
	/**
	 * 跳转至联系我们的页面
	 * */
	@RequestMapping("/toContactUs/{appId}")
	public String toContactUsMenu(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
	{
		String code  = (String)request.getParameter("code") ==null?"":(String)request.getParameter("code");
		String openid = weiEnter.getWecCustOpenId_Q();
		weiEnter = weixinservice.getWeiEnterByAppId(appId);
		if(code!=null&&code.length()>0)
		{
			//菜单点击:存在code，则是内部登陆的，查出用户的OpenId
			openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(),weiEnter.getWecAppSecret(),code);
		}
		else
		{
			if(openid!=null&&openid.length()>0)
			{
				//非菜单点击，且不是表单提交：外部链接点进去的，适合于分享后第三者点击页面的处理
			}
			else
			{
				//非菜单点击，但是表单提交：从页面本身的链接点击进入的
			}
		}
		weiEnter.setWecCustOpenId_Q(openid);
		//查出该企业的所有供求
		//model.addAttribute("custEnter",  	webuserservice.getCustEnteById(weiEnter.getWecEnterId()));
		model.addAttribute("command",		weiEnter);
		return "/weixine/contactUs";
	}
	
	/**
	 * 跳转至商机的页面
	 * */
	@RequestMapping("/queryBusiOpport/{appId}")
	public String queryBusiOpportMenu(@ModelAttribute("command") LzWeiEnter weiEnter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
	{
		String code  = (String)request.getParameter("code")==null?"" :(String)request.getParameter("code");
		String openidTest = (String)request.getParameter("openidTest");
		String openid = weiEnter.getWecCustOpenId_Q();
		weiEnter = weixinservice.getWeiEnterByAppId(appId);
		if(code!=null&&code.length()>0)
		{
			//菜单点击:存在code，则是内部登陆的，查出用户的OpenId
			openid = authorizeUtil.getOpenId(weiEnter.getWecAppId(),weiEnter.getWecAppSecret(),code);
		}
		else
		{
			if(openid!=null&&openid.length()>0)
			{
				//非菜单点击，且不是表单提交：外部链接点进去的，适合于分享后第三者点击页面的处理
			}
			else
			{
				//非菜单点击，但是表单提交：从页面本身的链接点击进入的
			}
		}
		//测试用
		if(openidTest!=null)
		{
			openid = openidTest;
		}
		weiEnter.setWecCustOpenId_Q(openid);
		weiEnter.setWetOpenId_Q(openid);//设这个值是为了方便在下单的时候能够通过表单提交获取openid
		//要根据用户的openId 查询当前用户是不是当前微信账号的内部管理员 如果是管理员就不跳转到订单页面
		LzWeiEnterCust cust = weixinEnterService.getWeiEnterCustByOpenId(openid);
		//LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(weiEnter.getWecEnterId());
		if(cust!=null&&cust.getWetType().equals("2"))
		{
			//如果是内部管理员 进入管理界面
			model.addAttribute("command", 	cust);
			//model.addAttribute("custEnter", custEnter);
			//model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryGqByWeiAdmin(cust.getWetCuuId()));
			return "/weixine/adminIndex";
		}
		else
		{
			//如果是客户，查出该企业的所有供求
			model.addAttribute("command",	weiEnter);
			//model.addAttribute("custEnter",	custEnter);
			model.addAttribute("list",		weixinEnterService.queryGqEnterList(weiEnter.getWecEnterId()));
			return "/weixine/busiOpport";
		}
	}
	
	/**
	 * 后台查询商家信息
	 **/
	@RequestMapping("/queryWeiMemberByApp/{wetCueId}")
	public String queryWeiEnterCustByApp(@ModelAttribute("command") LzWeiMember member,@PathVariable Integer wetCueId,Model model)
	{
		member.setWmbWecId_Q(wetCueId);
		model.addAttribute(SysConstant.PAGE_RESULT, weimemberservice.queryWeiMember(member));
		return "/weixin/queryWeiMember";
	}
	
	/**
	 * 后台删除客户信息
	 * */
	@RequestMapping("/delWeiMember/{wmbWecId}")
	public String delWeiMember(@ModelAttribute("command") LzWeiMember member,@PathVariable Integer wmbWecId,RedirectAttributes redirectAttribute)
	{
		String[] wmbIds = member.getWmbIds();
		weimemberservice.delWeiMember(wmbIds);
		redirectAttribute.addFlashAttribute("alertMsg", "删除会员成功！");
		return "redirect:/weixine/queryWeiMemberByApp/"+wmbWecId;
	}
	
	
	
//	
//	/**
//	 * 跳转至下单
//	 * */
//	@RequestMapping("/toOrder/{gqId}/{appId}/{enterId}")
//	public String toOrder(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable Integer gqId,@PathVariable String appId,@PathVariable String enterId,HttpSession session,Model model)
//	{
//		//LzEnteGq gq 		= webuserservice.getGqById(gqId);
//		gq.setProName_Q(gq.getProName());
//		//LzProduct product 	= productservice.getProductById(gq.getProId());
//		String openid 	= (String)enterCust.getWetOpenId_Q();
//		log.error("openid:"+openid);
//		if(openid!=null&&openid.length()>0)
//		{
//			//非菜单点击，但是表单提交：从页面本身的链接点击进入的
//			log.error("表单提交：从页面本身的链接点击进入的");
//			enterCust = weixinEnterService.getWeiEnterCustByOpenId(openid);
//			if(enterCust == null)
//			{
//				enterCust=new LzWeiEnterCust();
//			}
//			enterCust.setWetOpenId(openid);
//		}
//		else
//		{
//			//非菜单点击，且不是表单提交：外部链接点进去的，适合于分享后第三者点击页面的处理
//			log.error("不是表单提交：外部链接点进去的，适合于分享后第三者点击页面的处理");
//			enterCust.setWetOpenId(null);
//		}
//		enterCust.setWetCueId(Integer.parseInt(enterId));
//		enterCust.setWetType("1");
//		enterCust.setWetGqId(gqId);
//		enterCust.setWetAppId(appId);
//		model.addAttribute("command", enterCust);
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(Integer.parseInt(enterId));
//		model.addAttribute("custEnter", custEnter);
//		model.addAttribute("gq", gq);
//		return "/weixine/order";
//	}
//	
//	/**
//	 * 订单保存
//	 * */
//	@RequestMapping("/addOrder")
//	public String createOrder(@ModelAttribute("command") LzWeiEnterCust enterCust,HttpSession session ,RedirectAttributes redirectAttribute)
//	{
//		//先判断是从哪个链接进来的
//		//String sessionIn = (String)session.getAttribute(SysConstant.SESSION_IN_WEIENTER);
//		//log.error("当前的session标识是:"+sessionIn);
//		String msg = "";
//		//if(sessionIn!=null&&sessionIn.equals("1"))
//		//{
//			//log.error("openid:"+enterCust.getWetOpenId());
//			msg = "下单完成!";
//			Integer wetId = enterCust.getWetId();
//			if(wetId==null)
//			{
//				weixinEnterService.addWeiEnterCust(enterCust);
//			}
//			else
//			{
//				weixinEnterService.updWeiEnterCust(enterCust);
//			}
////		}
////		else
////		{
////			msg = "下单完成!建议您先关注本公众号，方便获取更多商机!";
////			enterCust.setWetOpenId("0");
////			weixinEnterService.addWeiEnterCust(enterCust);
////		}
//		if(enterCust.getWetOpenId()==null||enterCust.getWetOpenId().length()==0)
//		{
//			msg = "下单完成!建议您先关注本公众号，方便获取更多商机!";
//		}
//		LzWeiEnterOrder order = new LzWeiEnterOrder();
//		order.setWeoGqId(enterCust.getWetGqId());
//		order.setWeoRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		order.setWeoRegistor(1);
//		order.setWeoWetId(enterCust.getWetId());
//		order.setWeoDesc("");
//		order.setWeoStatus("1000");
//		weixinEnterService.addWeiEnterOrder(order);
//		redirectAttribute.addFlashAttribute("alertMsg", msg);
//		LzWeiEnter weiEnter = new LzWeiEnter();
//		weiEnter.setWecCustOpenId_Q(enterCust.getWetOpenId());
//		redirectAttribute.addFlashAttribute("command", weiEnter);
//		return "redirect:/weixine/queryBusiOpport/"+enterCust.getWetAppId();
//	}
//	
//	
//	/**
//	 * 客户的微信供求平台管理员
//	 * */
//	@RequestMapping("/toBindWeiEnterAdmin/{appId}")
//	public String toBindWeiEnterAdmin(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable String appId,HttpServletRequest request,Model model)
//	{
//		String code  = (String)request.getParameter("code")==null?"" :(String)request.getParameter("code");
//		String state = (String)request.getParameter("state")==null?"":(String)request.getParameter("state");
//		String openid = "";
//		LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
//		if(code!=null&&code.length()>0)
//		{
//			//查出用户的OpenId
//			openid = authorizeUtil.getOpenId(enter.getWecAppId(),enter.getWecAppSecret(),code);
//		}
//		//根据openid和appid判断当前微信是不是已经是管理员，如果不是就跳转到登陆页面
//		enterCust = weixinEnterService.getWeiEnterCustByOpenId(openid);
//		LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(enter.getWecEnterId());
//		//如果是 就直接进入管理页面
//		if(enterCust!=null&&enterCust.getWetType().equals("2"))
//		{
//			model.addAttribute("command", enterCust);
//			model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryGqByWeiAdmin(enterCust.getWetCuuId()));
//			//model.addAttribute("cueName", enter1.getCueName());
//			model.addAttribute("custEnter", enter1);
//			return "/weixine/adminIndex";
//		}
//		else
//		{
//			enterCust = new LzWeiEnterCust();
//			enterCust.setWetOpenId(openid);
//			enterCust.setWetOpenId_Q(openid);
//			model.addAttribute("openId",  openid);
//			model.addAttribute("command", enterCust);
//			//model.addAttribute("cueName", enter1.getCueName());
//			model.addAttribute("custEnter", enter1);
//			return "/weixine/bindAdmin";
//		}
//	}
//	
//	@RequestMapping("/bindWeiEnterAdmin/{appId}")
//	public String bindWeiEnterAdmin(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable String appId,HttpSession session,HttpServletRequest request,Model model)
//	{
//		LzAdmin admin = (LzAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
//		LzCustomerUser custuser = weixinEnterService.checkWebUserAccount(enterCust.getCuuUsername(),enterCust.getCuuPassword());
//		String openid = enterCust.getWetOpenId();
//		enterCust.setWetAppId(appId);
//		String msg = "";
//		if(custuser!=null)
//		{
//			LzEnterMainuserConfig emc = webuserservice.getEnterMainUserConfById(custuser.getCuuEmcId());
//			LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(emc.getEmcEnterId());
//			//做绑定 
//			String code = weixinEnterService.bindAdminByCust(appId,openid,custuser,admin);
//			if(code.equals("1"))
//			{
//				msg = " 当前账户的所属企业和微信号绑定企业不一致，管理员添加失败! ";
//				model.addAttribute("alertMsg",msg);
//				model.addAttribute("openId",  openid);
//				model.addAttribute("command", enterCust);
//				LzWeiEnter enter= weixinservice.getWeiEnterByAppId(appId);
//				enter1 = webuserservice.getCustEnteById(enter.getWecEnterId());
//				//model.addAttribute("cueName", enter1.getCueName());
//				model.addAttribute("custEnter", enter1);
//				return 	"/weixine/bindAdmin";		//跳转回登陆页面
//			}
//			else if(code.equals("2"))
//			{
//				msg = " 新的管理员升级成功! ";
//				model.addAttribute("alertMsg",msg);
//				enterCust.setWetCuuId(custuser.getCuuId());
//				model.addAttribute("command", enterCust);
//				//model.addAttribute("cueName", enter1.getCueName());
//				model.addAttribute("custEnter", enter1);
//				model.addAttribute(SysConstant.PAGE_RESULT, weixinEnterService.queryGqByWeiAdmin(custuser.getCuuId()));
//				return 	"/weixine/adminIndex";		//跳转管理页面
//			}
//			else if(code.equals("3"))
//			{
//				msg = " 新的管理员添加成功! ";
//				model.addAttribute("alertMsg",msg);
//				enterCust.setWetCuuId(custuser.getCuuId());
//				model.addAttribute("command", enterCust);
//				//model.addAttribute("cueName", enter1.getCueName());
//				model.addAttribute("custEnter", enter1);
//				model.addAttribute(SysConstant.PAGE_RESULT, weixinEnterService.queryGqByWeiAdmin(custuser.getCuuId()));
//				return 	"/weixine/adminIndex";		//跳转管理页面
//			}
//			else
//			{
//				msg = " 未知错误! ";
//				model.addAttribute("alertMsg",msg);
//				model.addAttribute("openId",  openid);
//				model.addAttribute("command", enterCust);
//				//model.addAttribute("cueName", enter1.getCueName());
//				model.addAttribute("custEnter", enter1);
//				return 	"/weixine/bindAdmin";//登陆页面
//			}
//		}
//		else
//		{
//			msg =	"用户名密码错误!";
//			model.addAttribute("alertMsg",msg);
//			//model.addAttribute("openId",  openid);
//			model.addAttribute("command", enterCust);
//			LzWeiEnter enter= weixinservice.getWeiEnterByAppId(appId);
//			LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(enter.getWecEnterId());
//			//model.addAttribute("cueName", enter1.getCueName());
//			model.addAttribute("custEnter", enter1);
//			return 	"/weixine/bindAdmin";
//			//跳转回登陆页面
//		}
//	}
//
//	@RequestMapping("/queryGqByWeiAdmin/{cuuId}/{cueId}")
//	public String queryGqByWeiAdmin(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable Integer cuuId,@PathVariable Integer cueId,Model model)
//	{
//		model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryGqByWeiAdmin(cuuId));
//		//LzWeiEnter custEnter = weixinservice.getWeiEnterByAppId(enterCust.getWetAppId());
//		LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(cueId);
//		//model.addAttribute("cueName", enter1.getCueName());
//		//model.addAttribute("custEnter", custEnter);
//		enterCust.setWetCueId(cueId);
//		enterCust.setWetCuuId(cuuId);
//		model.addAttribute("custEnter", enter1);
//		return "/weixine/adminIndex";
//	}
//
//	
//	
//	@RequestMapping("/toAddGq/{cuuId}")
//	public String toAddGq(@ModelAttribute("command") LzEnteGq gq,@PathVariable Integer cuuId,Model model)
//	{
//		LzCustomerUser user = webuserservice.getCustUserById(cuuId); 
//		LzCustomerContacts cuc = webuserservice.getCustContById(user.getCuuContactsId());
//		LzEnterMainuserConfig emc 	= webuserservice.getEnterMainUserConfById(cuc.getCucEmcId());
//		gq.setCucId(user.getCuuContactsId());
//		gq.setCueId(emc.getEmcEnterId());
//		gq.setGqCycle("1");
//		model.addAttribute("ocAreaList",addressService.queryAreas1());
//		model.addAttribute("cuuId", cuuId);
//		LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(emc.getEmcEnterId());
//		//model.addAttribute("cueName", enter1.getCueName());
//		model.addAttribute("custEnter", enter1);
//		return "/weixine/addGq";
//	}
//	
//	@RequestMapping("/addGq/{cuuId}")
//	public String insertGq(@ModelAttribute("command") LzEnteGq gq,HttpSession session,@PathVariable Integer cuuId,RedirectAttributes redirectAttribute)
//	{
//		LzCustomerEnterprise enter  = webuserservice.getCustEnteById(gq.getCueId());
//		LzCustomerContacts cuc		= webuserservice.getCustContById(gq.getCucId());
//		LzEnterMainuserConfig emc 	= webuserservice.getEnterMainUserConfById(cuc.getCucEmcId());
//		
//		Integer proId = gq.getProId();
//		if(proId==null||proId<=0)
//		{
//			redirectAttribute.addFlashAttribute("alertMsg", "产品不能为空");
//			return "redirect:/weixine/queryGqByWeiAdmin/"+cuuId+"/"+gq.getCueId();
//		}
//		else
//		{
//			Integer gqProvince = gq.getGqProvince();
//			if(gq.getGqProvince()==null&&(gq.getGqProvinces()==null||gq.getGqProvinces().length==0))
//			{	gqProvince = 1;	}
//			else
//			{	gqProvince = 0;	}
//			gq.setGqProvince(gqProvince);		//1：全国 0:省份
//			gq.setCueId(emc.getEmcEnterId());	//企业ID
//			gq.setCucId(cuc.getCucId());		//联系人ID
//			gq.setAdminId(1);	//到正式的时候直接删掉
//			gq.setUpdAdminId(1);
//			gq.setGqFrom("3");	//客户微信端添加
//			gq.setGqCycle("2");	//时间周期
//			gq.setGqSh("0");
//			gq.setGqXia("1");	//默认上架
//			gq.setRefreshTime(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			webuserservice.addEnteGq3(gq);
//			redirectAttribute.addFlashAttribute("alertMsg", "供求发布成功");
//			return "redirect:/weixine/queryGqByWeiAdmin/"+cuuId+"/"+gq.getCueId();
//		}
//	}
//	
//	@RequestMapping("/delWeiAdminGq/{gqId}/{cuuId}")
//	public String removeWeiAdminGq(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable Integer gqId,@PathVariable Integer cuuId,RedirectAttributes redirectAttribute)
//	{
//		weixinEnterService.delGqByWeiAdmin(gqId);	
//		redirectAttribute.addFlashAttribute("alertMsg", "删除供求成功!");
//		enterCust.setWetCuuId(cuuId);
//		redirectAttribute.addFlashAttribute("command", enterCust);
//		return "redirect:/weixine/queryGqByWeiAdmin/"+cuuId+"/"+enterCust.getWetCueId();
//	}
//	
//	
//	@RequestMapping("/toUpdWeiAdminGq/{gqId}/{cuuId}")
//	public String toUpdGq(@ModelAttribute("command") LzEnteGq enterGq,@PathVariable Integer gqId,@PathVariable Integer cuuId,Model model)
//	{
//		enterGq = webuserservice.getGqById(gqId);
//		model.addAttribute("command", enterGq);
//		model.addAttribute("provinces", addressService.queryProvinces()); 
//		List<OcAreas> areaList = addressService.queryAreas1();
//		model.addAttribute("ocAreaList", areaList);
//		model.addAttribute("gqProvinces", webuserservice.queryGqProvince(enterGq.getGqId()));
//		model.addAttribute("provNames", webuserservice.queryGqProvinceSel(enterGq));
//		model.addAttribute("cuuId", cuuId);
//		LzCustomerUser custuser = webuserservice.getCustUserById(cuuId);
//		LzEnterMainuserConfig emc = webuserservice.getEnterMainUserConfById(custuser.getCuuEmcId());
//		LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(emc.getEmcEnterId());
//		model.addAttribute("cueName", enter1.getCueName());
//		return "/weixine/updGq";
//	}
//	
//	@RequestMapping("/updGq/{cuuId}")
//	public String modifyGq12(@ModelAttribute("command") LzEnteGq enterGq,@PathVariable Integer cuuId,RedirectAttributes redirectAttributes)
//	{
//		Integer proId = enterGq.getProId();
//		if(proId==null||proId<=0)
//		{
//			redirectAttributes.addFlashAttribute("alertMsg", "产品不能为空!");
//			return "redirect:/weixine/queryGqByWeiAdmin/"+cuuId+"/"+enterGq.getCueId();
//		}
//		else
//		{
//			Integer gqProvince = enterGq.getGqProvince();
//			if(enterGq.getGqProvince()==null&&(enterGq.getGqProvinces()==null||enterGq.getGqProvinces().length==0))
//			{
//				gqProvince = 1;
//			}
//			else
//			{
//				gqProvince = 0;
//			}
//			enterGq.setGqProvince(gqProvince);
//			enterGq.setGqFrom("3");//客户微信端添加
//			enterGq.setGqSh("0");
//			webuserservice.updEnteGq3(enterGq);
//			redirectAttributes.addFlashAttribute("alertMsg", "供求修改完成!");
//			return "redirect:/weixine/queryGqByWeiAdmin/"+cuuId+"/"+enterGq.getCueId();
//		}
//	}
//	
//	@RequestMapping("/chkProIdByName/{proName}")
//	@ResponseBody
//	public Map<String, Object> chkProIdByName(@PathVariable String proName) throws UnsupportedEncodingException
//	{
//		Map<String, Object> map = new  HashMap<String, Object>();
//		proName= URLDecoder.decode(proName, "UTF-8");
//		LzProduct product =productservice.queryProductByName(proName);
//		if(product!=null)
//		{
//			map.put("proName", 	product.getProCnName());
//			map.put("proId", 	product.getProId());
//		}
//		else
//		{
//			map.put("proName", 	"");
//			map.put("proId", 	"");
//		}
//		return map;
//	}
//	
//	/**
//	 * 根据供求查询订单信息
//	 **/
//	@RequestMapping("/queryOrderByGq/{gqId}/{cuuId}")
//	public String queryOrderByGq(@ModelAttribute("command") LzWeiEnterOrder order,@PathVariable Integer gqId,@PathVariable Integer cuuId,Model model)
//	{
//		order.setWeoGqId_Q(gqId);
//		model.addAttribute("cuuId", cuuId);
//		model.addAttribute("gq", webuserservice.getGqById(gqId));
//		model.addAttribute("list",weixinEnterService.queryWeiEnterOrderList(order));
//		LzCustomerUser custuser = webuserservice.getCustUserById(cuuId);
//		LzEnterMainuserConfig emc = webuserservice.getEnterMainUserConfById(custuser.getCuuEmcId());
//		LzCustomerEnterprise enter1 = webuserservice.getCustEnteById(emc.getEmcEnterId());
//		model.addAttribute("cueName", enter1.getCueName());
//		return "/weixine/queryOrderByGq";
//	}
//	
//	/**
//	 * 解除绑定
//	 **/
//	@RequestMapping("/cancleBind/{appId}")
//	public String cancleBindAdmin(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable String appId,RedirectAttributes redirectAttributes)
//	{
//		enterCust = weixinEnterService.getWeiEnterCustById(enterCust.getWetId());
//		enterCust.setWetType("1");
//		enterCust.setWetCuuId(null);
//		weixinEnterService.updWeiEnterCust(enterCust);
//		redirectAttributes.addFlashAttribute("alertMsg", "解绑成功");
//		return "redirect:/weixine/toBindWeiEnterAdmin/"+appId;
//	}
//	
	
//	
//	@RequestMapping("/queryWeiEnterAdminByApp/{wetCueId}")
//	public String queryWeiEnterAdminByApp(@ModelAttribute("command") LzWeiEnterCust enterCust,@PathVariable Integer wetCueId,Model model)
//	{
//		enterCust.setWetCueId_Q(wetCueId);
//		enterCust.setWetType_Q("2");
//		model.addAttribute(SysConstant.PAGE_RESULT, weixinEnterService.queryWeiEnterCust(enterCust));
//		return "/weixin/queryWeiEnterAdmin";
//	}
//	

//	
//	@RequestMapping("/queryOrderByWet/{wetId}")
//	public String queryOrderByWet(@ModelAttribute("command") LzWeiEnterOrder order,@PathVariable Integer wetId,Model model)
//	{
//		LzWeiEnterCust enter = weixinEnterService.getWeiEnterCustById(wetId);
//		order.setWeoWetId_Q(wetId);
//		order.setWeoEnterId_Q(enter.getWetCueId());
//		model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryWeiEnterOrder(order));
//		return "/weixin/queryWeiEnterCustOrder";
//	}
//
//	@RequestMapping("/queryWeiEnterCustOrder/{wecEnterId}")
//	public String queryWeiEnterCustOrder(@ModelAttribute("command") LzWeiEnterOrder order,@PathVariable Integer wecEnterId,Model model)
//	{
//		order.setWeoEnterId_Q(wecEnterId);
//		model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryWeiEnterOrder(order));
//		return "/weixin/queryWeiEnterCustOrder";
//	}
//
//	@RequestMapping("/delWeiEnterOrder")
//	public String delWeiEnterOrder(@ModelAttribute("command") LzWeiEnterOrder order,RedirectAttributes redirectAttribute)
//	{
//		String [] weoIds = order.getWeoIds();
//		weixinEnterService.delWeiEnterOrder(weoIds);
//		redirectAttribute.addFlashAttribute("alertMsg","删除订单成功!");
//		return "redirect:/weixine/queryWeiEnterCustOrder/"+order.getWeoEnterId_Q();
//	}
//	
//	/**
//	 * 跳转至企业简介的页面-微博
//	 * */
//	@RequestMapping("/toIntroEnterWeiBo/{enterId}")
//	public String toIntroEnterWeiBo(@ModelAttribute("command") LzWeiEnter enter,@PathVariable Integer enterId,HttpServletRequest request,Model model)
//	{
//		//取一下企业简介 
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(enterId);
//		model.addAttribute("custEnter",  custEnter);
//		model.addAttribute("command",	 enter);
//		return "/weixine/introEnterWeibo";
//	}
//	
//	/**
//	 * 跳转至企业的联系方式-微博
//	 * */
//	@RequestMapping("/toContactUsWeiBo/{enterId}")
//	public String toContactUsWeiBo(@ModelAttribute("command") LzWeiEnter enter,@PathVariable Integer enterId,HttpServletRequest request,Model model)
//	{
//		//取一下企业联系方式
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(enterId);
//		model.addAttribute("custEnter", custEnter);
//		model.addAttribute("command",enter);
//		return "/weixine/contactUsWeibo";
//	}
//	
//	/**
//	 * 跳转至商机的页面-微博
//	 * */
//	@RequestMapping("/queryBusiOpportWeiBo/{enterId}")
//	public String queryBusiOpportWeibo(@ModelAttribute("command") LzWeiEnter enter,@PathVariable Integer enterId,HttpServletRequest request,Model model)
//	{
//		//取一下企业联系方式
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(enterId);
//		model.addAttribute("custEnter", custEnter);
//		model.addAttribute("command",enter);
//		//查出该企业的所有供求
//		model.addAttribute("list",weixinEnterService.queryGqEnterList(enterId));
//		return "/weixine/busiOpportWeibo";
//	}
//	
//	/**
//	 * 跳转至商机的页面-微博
//	 * @throws Exception 
//	 * */
//	@RequestMapping("/toTestJsApi/{appId}")
//	public String toTestJsApi(@ModelAttribute("command") LzWeiEnter enter,@PathVariable String appId,HttpServletRequest request,Model model) throws Exception
//	{
//		//取一下企业联系方式
//		enter = weixinservice.getWeiEnterByAppId(appId);
//		LzWeiJsapiTicket jsApiTicket = weixinservice.getCurrentTikcet(enter.getWecId());
//		String jsApiTicketStr = jsApiTicket.getWjtJsapiTicket(); 
//		String timestamp = Long.toString(new Date().getTime()/1000);
//		String nonceStr  = "abc123";
//		String url = "http://gl.oilchem.net/oilchem/weixine/toTestJsApi/"+appId;
//		String string1 = getString1(jsApiTicketStr,nonceStr,timestamp,url);
//		String signature =  com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
//		model.addAttribute("command",enter);
//		model.addAttribute("timestamp",timestamp);
//		model.addAttribute("nonceStr",nonceStr);
//		model.addAttribute("signature",signature);
//		model.addAttribute("jsApiTicketStr",jsApiTicketStr);
//		return "/weixine/testJsSDK";
//	}
//	
//	public String getString1(String ticket,String noncestr,String timestamp,String url)
//	{
//		String string1 = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
//		return string1.replace("TICKET", ticket).replace("NONCESTR", noncestr).replace("TIMESTAMP", timestamp).replace("URL", url);
//	}
	
	public static void main(String [] args)
	{
		try {
			String appId = "wx699aebd2bc63a9fb";
			System.out.println("appId:"+appId);
			String jsApiTicketStr = "kPAVMQm0WKdsodEfj5UKJPrtId4aDLMnZLjOQRTvIr9rgVJqZ2gw0DK-OuFiV5U44ISqBpAeQrA1ARGPTsfMgz0lGGnYspS4MYp91G42mMI"; 
			System.out.println("jsApiTicketStr:"+jsApiTicketStr);
			String timestamp = Long.toString(new Date().getTime());
			System.out.println("timestamp:"+timestamp);
			String nonceStr  = "abc123";
			System.out.println("nonceStr:"+nonceStr);
			String url = "http://gl.oilchem.net/oilchem/weixine/toTestJsApi/"+appId;
			System.out.println("url:"+url);
			String string1 = "jsapi_ticket=TICKET&noncestr=NONCESTR&timestamp=TIMESTAMP&url=URL";
			string1=string1.replace("TICKET", jsApiTicketStr).replace("NONCESTR", nonceStr).replace("TIMESTAMP", timestamp).replace("URL", url);
			System.out.println("string1:"+string1);
			String signature =  com.oilchem.weixin.mp.aes.SHA1.getJsSignatureSHA1(string1);
			System.out.println("signature:"+signature);
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * 跳转至企业简介的页面
//	 * */
//	@RequestMapping("/toIntroEnter/{appId}")
//	public String toIntroEnter(@ModelAttribute("command") LzWeiEnter enter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
//	{
//		String code   = (String)request.getParameter("code")  == null? "" : (String)request.getParameter("code");
//		//String state  = (String)request.getParameter("state") == null? "" : (String)request.getParameter("state");
//		String openid = enter.getWecCustOpenId_Q();
//		enter = weixinservice.getWeiEnterByAppId(appId);
//		if(code!=null&&code.length()>0)
//		{
//			//查出用户的OpenId
//			openid = authorizeUtil.getOpenId(enter.getWecAppId(),enter.getWecAppSecret(),code);
//		}
//		/**
//		 * 测试内部账号时候做的复制
//		 * */
//		if(openid==null||openid.length()==0)
//		{
//			openid="0";
//		}
//		enter.setWecCustOpenId_Q(openid);
//		Integer entreId = enter.getWecEnterId();
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(entreId);
//		//取一下企业简介 
//		model.addAttribute("openid", openid);
//		model.addAttribute("appid", appId);
//		model.addAttribute("custEnter", custEnter);
//		model.addAttribute("command",enter);
//		return "/weixine/introEnter";
//	}
//	
//	/**
//	 * 跳转至联系我们的页面
//	 * */
//	@RequestMapping("/toContactUs/{appId}")
//	public String toContactUs(@ModelAttribute("command") LzWeiEnter enter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
//	{
//		String code  = (String)request.getParameter("code") ==null?"":(String)request.getParameter("code");
//		String state = (String)request.getParameter("state")==null?"":(String)request.getParameter("state");
//		String openid = enter.getWecCustOpenId_Q();
//		enter = weixinservice.getWeiEnterByAppId(appId);
//		if(code!=null&&code.length()>0)
//		{
//			//查出用户的OpenId
//			openid = authorizeUtil.getOpenId(enter.getWecAppId(),enter.getWecAppSecret(),code);
//		}
//		if(openid==null||openid.length()==0)
//		{
//			openid="0";
//		}
//		enter.setWecCustOpenId_Q(openid);
//		Integer entreId = enter.getWecEnterId();
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(entreId);
//		//查出该企业的所有供求
//		model.addAttribute("custEnter",  	custEnter);
//		model.addAttribute("openid", 		openid);
//		model.addAttribute("appid", 		appId);
//		model.addAttribute("command",		enter);
//		return "/weixine/contactUs";
//	}
//	
//	/**
//	 * 跳转至商机的页面
//	 * */
//	@RequestMapping("/queryBusiOpport/{appId}")
//	public String queryBusiOpport(@ModelAttribute("command") LzWeiEnter enter,@PathVariable String appId,HttpServletRequest request,HttpSession session,Model model)
//	{
//		String code  = (String)request.getParameter("code")==null?"" :(String)request.getParameter("code");
//		String state = (String)request.getParameter("state")==null?"":(String)request.getParameter("state");
//		String openid = enter.getWecCustOpenId_Q();
//		enter = weixinservice.getWeiEnterByAppId(appId);
//		if(code!=null&&code.length()>0)
//		{
//			//查出用户的OpenId
//			openid = authorizeUtil.getOpenId(enter.getWecAppId(),enter.getWecAppSecret(),code);
//		}
//		if(openid==null||openid.length()==0)
//		{
//			openid="0";
//		}
//		String sessionIn = (String)session.getAttribute(SysConstant.SESSION_IN_WEIENTER);
//		if(sessionIn!=null&&sessionIn.equals("1"))
//		{
//			enter.setWecCustOpenId_Q(openid);
//		}
//		else
//		{
//			enter.setWecCustOpenId_Q("0");
//		}
//		
//		Integer entreId = enter.getWecEnterId();
//		//要根据用户的openId 查询当前用户是不是当前微信账号的内部管理员 如果是管理员就不跳转到订单页面
//		LzWeiEnterCust cust = weixinEnterService.getWeiEnterCustByOpenId(openid);
//		LzCustomerEnterprise custEnter = webuserservice.getCustEnteById(entreId);
//		model.addAttribute("openid", openid);
//		model.addAttribute("appid", appId);
//		model.addAttribute("enterId", entreId);
//		model.addAttribute("command",enter);
//		if(cust!=null&&cust.getWetType().equals("2"))
//		{
//			model.addAttribute("command", cust);
//			model.addAttribute("cueName", custEnter.getCueName());
//			model.addAttribute(SysConstant.PAGE_RESULT,weixinEnterService.queryGqByWeiAdmin(cust.getWetCuuId()));
//			return "/weixine/adminIndex";
//		}
//		else
//		{
//			//查出该企业的所有供求
//			model.addAttribute("custEnter",  	custEnter);
//			model.addAttribute("list",weixinEnterService.queryGqEnterList(entreId));
//			return "/weixine/busiOpport";
//		}
//	}
	
	
	
}
