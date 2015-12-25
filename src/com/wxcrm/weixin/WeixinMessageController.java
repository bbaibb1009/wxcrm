package com.wxcrm.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oilchem.weixin.mp.aes.AesException;
import com.oilchem.weixin.mp.aes.SHA1;
import com.wxcrm.sys.WcAdmin;
import com.wxcrm.util.DateUtil;
import com.wxcrm.util.StringUtil;
import com.wxcrm.util.SysConstant;
import com.wxcrm.weixin.pojo.LzWeiArticleMessage;


@Controller
@RequestMapping("/weixinmsg")
public class WeixinMessageController {

	@Autowired
	private IWeixinMessageService weixinmsgservice;
	
	@Autowired
	private IWeixinService weixinservice;


	private static Logger log = Logger.getLogger(WeixinMessageController.class);
	


	/**
	 * 以下是“测试公众号”的消息处理
	 ***/
	@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.GET)
	public String getServeEchoTestGet(@PathVariable String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws AesException, IOException
	{
		try
		{
			LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
		    String token = enter.getWecToken();//pudding
			String signature 	= request.getParameter("signature");// 微信加密签名
	        String echostr 		= request.getParameter("echostr");	// 随机字符串
	        String timestamp 	= request.getParameter("timestamp");// 时间戳
	        String nonce 		= request.getParameter("nonce");	// 随机数
	        String encrypt = "";
	        String[] str = {token,timestamp,nonce};
	        Arrays.sort(str); // 字典序排序
	        // SHA1加密
	        String digest = SHA1.getSHA1(token,timestamp,nonce,encrypt);
	        // 确认请求来至微信
	        if(digest.equals(signature)) 
	        {
	            response.getWriter().print(echostr);
	        }
	        else
	        {
	        }
		}
		catch(AesException ae)
		{
			ae.printStackTrace();
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		finally
		{
			return null;
		}
	}
	
	@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.POST)
	public String getServeEchoTestPost(@PathVariable String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException
	{
        //先根据appId查出所属的微信账号
        LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
        String token_test = enter.getWecToken();//pudding
        String encodingAESKey_test = enter.getWecEncodingAesKey();//
        String appId_test = enter.getWecAppId();
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）   
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        // 调用核心业务类接收消息、处理消息   
        String respMessage = weixinmsgservice.processRequest_Jar(request,token_test,encodingAESKey_test,appId_test); 
        // 响应消息   
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();
        return null;
	}
	
	/**
	 * 查询默认回复消息
	 ***/
	@RequestMapping(value = "/queryDefaultMsg")
	public String queryDefaultMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		msg.setWmgReplyType_Q("2");
		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
		return "/weixin/showDefaultMsg";
	}
	
	
	@RequestMapping(value = "/querySubscribeMsg")
	public String querySubscribeMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		msg.setWmgReplyType_Q("3");
		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
		return "/weixin/showSubscribeMsg";
	}
	
	
	@RequestMapping(value = "/queryKeyWordsMsg")
	public String queryKeyWordsMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		msg.setWmgReplyType_Q("1");
		model.addAttribute(SysConstant.PAGE_RESULT, weixinmsgservice.queryLzWeiMsg(msg));
		return "/weixin/showKeyWordsMsg";
	}
	/**
	 * 回复消息查询
	 * */
	@RequestMapping(value = "/queryLzWeiMsg")
	public String queryLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weixinmsgservice.queryLzWeiMsg(msg));
		return "/weixin/queryLzWeiMsg";
	}
	
	/**
	 * 跳转至消息添加
	 * */
	@RequestMapping(value = "/toAddLzWeiMsg")
	public String toAddLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		return "/weixin/addLzWeiMsg";
	}
	
	/**
	 * 消息添加保存
	 * */
	@RequestMapping(value = "/addLzWeiMsg")
	public String addLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,HttpSession session, RedirectAttributes redirectAttribute)
	{
		String content = msg.getWmgContent()==null?"":msg.getWmgContent();
		String msgType = msg.getWmgMsgType()==null?"":msg.getWmgMsgType();
		String aesType = msg.getWmgAesType()==null?"":msg.getWmgAesType();
		WcAdmin admin  = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		msg.setWmgStatus("1000");
		msg.setWmgRegistor(admin.getWadId());
		msg.setWmgRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//要先根据消息类型和加密类型 处理消息的XML格式
		if(msgType.equals("1"))
		{
			//图文消息先保存消息 
			//再保存图文关系

		}
		else if(msgType.equals("2"))
		{
			//文字消息直接保存
			weixinmsgservice.addLzWeiMsg(msg);
		}
		return "redirect:/weixinmsg/queryLzWeiMsg";
	}
	
	/**
	 * 跳转到消息修改
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * 
	 * */
	
	@RequestMapping(value = "/toUpdLzWeiMsg")
	public String toUpdLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg_Q,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		LzWeiMessage msg = weixinmsgservice.getLzWeiMessageById(msg_Q.getWmgId());
		StringUtil.copyProperties(msg_Q, msg);
		model.addAttribute("command", msg);	
		return "/weixin/updLzWeiMsg";
	}
	
	@RequestMapping(value ="/updLzWeiMsg", method = RequestMethod.POST )
	public String updLzWeiMsg(LzWeiMessage msg, HttpServletRequest request,RedirectAttributes redirectAttributes)
	    throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, JsonGenerationException, IOException 
	{
		weixinmsgservice.updLzWeiMsg(msg);
		redirectAttributes.addFlashAttribute("msgCode", "2");
		redirectAttributes.addFlashAttribute("alertMsg", "回复消息修改成功");
		redirectAttributes.addFlashAttribute("formHidden", StringUtil.formPost(request.getContextPath() + "/weixinmsg/queryLzWeiMsg", msg));
		return "redirect:/admin/toMsg";
	}
	
	
	@RequestMapping(value = "/toAddNewsMsg")
	public String toAddNewsMsg(@ModelAttribute("command") LzWeiArticleMessage article,Model model) throws IllegalArgumentException, IllegalAccessException
	{
		model.addAttribute("command", article);	
		return "/weixin/addNewsMsg";
	}
	
	
	@RequestMapping(value = "/addNewsMsg")
	public String addNewsMsg(@ModelAttribute("command") LzWeiArticleMessage article,Model model,HttpSession session) throws IllegalArgumentException, IllegalAccessException
	{
		WcAdmin admin = (WcAdmin)session.getAttribute(SysConstant.ADMIN_INFO);
		article.setWamRegistor(admin.getWadId());
		article.setWamRegistdate(DateUtil.parseString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		article.setWamStatus("1000");
		weixinmsgservice.addLzWeiArticleMsg(article);
		model.addAttribute("alertMsg", article.getWamId());
		return "/weixin/addNewsMsg";
	}
	
	
	@RequestMapping("/getArticleJson/{id}")
	@ResponseBody
	public Map<String,Object> getArticleJson(@PathVariable Integer id) throws IllegalArgumentException, IllegalAccessException
	{
		Map<String,Object> map = weixinmsgservice.getWeiArticleMap(id);
		return map;
	}
	
	public String getTextMsgXMLByType(String content,String aesType)
	{
		
		return "";
	}
	
	
	
	public static void main(String [] args) throws ClientProtocolException, IOException
	{
		
	}
	
	
}
