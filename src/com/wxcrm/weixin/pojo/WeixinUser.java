package com.wxcrm.weixin.pojo;

import net.sf.json.JSONObject;

public class WeixinUser {

	
	/***
	 * 
	 * 
	 * {
		"sex":1,
		"subscribe":1,
		"remark":"",
		"nickname":"黄巧克力",
		"province":"山东",
		"openid":"oFVvzsk82VGxsVDrT8YhWzwaTDXE",
		"language":"zh_CN",
		"headimgurl":"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJKcAuNafuwWicoYCLjhQ6pRGnwWDRrppaHMLiaxibIicgnuozDVd0u3Iia7ra6ufEygiawZavwUvNHu06w/0",
		"subscribe_time":1429077215,
		"country":"中国",
		"city":"淄博"
		}
	 * 
	 * */
	private String sex  ;//1,
	private String subscribe ;//1,
	private String remark ;//"",
	private String nickname ;//"黄巧克力",
	private String province ;//"山东",
	private String openid ;//"oFVvzsk82VGxsVDrT8YhWzwaTDXE",
	private String language ;//"zh_CN",
	private String headimgurl ;//"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJKcAuNafuwWicoYCLjhQ6pRGnwWDRrppaHMLiaxibIicgnuozDVd0u3Iia7ra6ufEygiawZavwUvNHu06w/0",
	private String subscribe_time ;//1429077215,
	private String country ;//"中国",
	private String city ;//"淄博"\
	
	public WeixinUser(JSONObject obj)
	{
		this.sex = obj.getString("sex");//1,
		this.subscribe= obj.getString("subscribe");//1,
		this.remark= obj.getString("remark");//"",
		this.nickname= obj.getString("nickname");//"黄巧克力",
		this.province= obj.getString("province");//"山东",
		this.openid= obj.getString("openid");//"oFVvzsk82VGxsVDrT8YhWzwaTDXE",
		this.language= obj.getString("language");//"zh_CN",
		this.headimgurl= obj.getString("headimgurl");//"http://wx.qlogo.cn/mmopen/PiajxSqBRaEJKcAuNafuwWicoYCLjhQ6pRGnwWDRrppaHMLiaxibIicgnuozDVd0u3Iia7ra6ufEygiawZavwUvNHu06w/0",
		this.subscribe_time= obj.getString("subscribe_time");//1429077215,
		this.country= obj.getString("country");//"中国",
		this.city= obj.getString("city");//"淄博"\
		
		
	}
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribeTime) {
		subscribe_time = subscribeTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	 
}
