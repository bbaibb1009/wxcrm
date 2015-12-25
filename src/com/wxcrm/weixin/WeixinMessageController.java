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
	 * �����ǡ����Թ��ںš�����Ϣ����
	 ***/
	@RequestMapping(value = "/getServeEchoTest/{appId}", method = RequestMethod.GET)
	public String getServeEchoTestGet(@PathVariable String appId,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws AesException, IOException
	{
		try
		{
			LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
		    String token = enter.getWecToken();//pudding
			String signature 	= request.getParameter("signature");// ΢�ż���ǩ��
	        String echostr 		= request.getParameter("echostr");	// ����ַ���
	        String timestamp 	= request.getParameter("timestamp");// ʱ���
	        String nonce 		= request.getParameter("nonce");	// �����
	        String encrypt = "";
	        String[] str = {token,timestamp,nonce};
	        Arrays.sort(str); // �ֵ�������
	        // SHA1����
	        String digest = SHA1.getSHA1(token,timestamp,nonce,encrypt);
	        // ȷ����������΢��
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
        //�ȸ���appId���������΢���˺�
        LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
        String token_test = enter.getWecToken();//pudding
        String encodingAESKey_test = enter.getWecEncodingAesKey();//
        String appId_test = enter.getWecAppId();
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩   
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        // ���ú���ҵ���������Ϣ��������Ϣ   
        String respMessage = weixinmsgservice.processRequest_Jar(request,token_test,encodingAESKey_test,appId_test); 
        // ��Ӧ��Ϣ   
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();
        return null;
	}
	
	/**
	 * ��ѯĬ�ϻظ���Ϣ
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
	 * �ظ���Ϣ��ѯ
	 * */
	@RequestMapping(value = "/queryLzWeiMsg")
	public String queryLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		model.addAttribute(SysConstant.PAGE_RESULT,weixinmsgservice.queryLzWeiMsg(msg));
		return "/weixin/queryLzWeiMsg";
	}
	
	/**
	 * ��ת����Ϣ���
	 * */
	@RequestMapping(value = "/toAddLzWeiMsg")
	public String toAddLzWeiMsg(@ModelAttribute("command") LzWeiMessage msg,Model model)
	{
		return "/weixin/addLzWeiMsg";
	}
	
	/**
	 * ��Ϣ��ӱ���
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
		//Ҫ�ȸ�����Ϣ���ͺͼ������� ������Ϣ��XML��ʽ
		if(msgType.equals("1"))
		{
			//ͼ����Ϣ�ȱ�����Ϣ 
			//�ٱ���ͼ�Ĺ�ϵ

		}
		else if(msgType.equals("2"))
		{
			//������Ϣֱ�ӱ���
			weixinmsgservice.addLzWeiMsg(msg);
		}
		return "redirect:/weixinmsg/queryLzWeiMsg";
	}
	
	/**
	 * ��ת����Ϣ�޸�
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
		redirectAttributes.addFlashAttribute("alertMsg", "�ظ���Ϣ�޸ĳɹ�");
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
