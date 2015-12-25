package com.wxcrm.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiWatcher entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_WATCHER")
public class LzWeiWatcher implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wacId;
	private String 	wacOpenid;
	private Integer wacSubscribe;
	private String 	wacNickName;
	private String 	wacSex;
	private String 	wacLanguage;
	private String 	wacCity;
	private String 	wacProvince;
	private String 	wacCountry;
	private String 	wacHeadImgUrl;
	private String 	wacSubscribeTime;
	private String 	wacStatus;
	private Integer wacWecId;
	private String  wacAppid;
	private String  currentPage;
	private String  pageSize;
	
	private String wacWatchStatus_Q;
	private String wacBindStatus_Q;
	private String wacMobile_Q;
	private String wacUserName_Q;
	private String wacContract_Q;
	private String wacEnterPrise_Q;
	private String wacNickName_Q;
	private String wacStatus_Q;
	private String wacWecId_Q;
	private String msg;
	
	
	private String sumTotal;
	
	// Constructors

	/** default constructor */
	public LzWeiWatcher() {
	}

	/** minimal constructor */
	public LzWeiWatcher(Integer wacId) {
		this.wacId = wacId;
	}

	/** full constructor */
	public LzWeiWatcher(Integer wacId, String wacOpenid, Integer wacSubscribe,
			String wacNickName, String wacSex, String wacLanguage,
			String wacCity, String wacProvince, String wacCountry,
			String wacHeadImgUrl, String wacSubscribeTime, String wacStatus) {
		this.wacId = wacId;
		this.wacOpenid = wacOpenid;
		this.wacSubscribe = wacSubscribe;
		this.wacNickName = wacNickName;
		this.wacSex = wacSex;
		this.wacLanguage = wacLanguage;
		this.wacCity = wacCity;
		this.wacProvince = wacProvince;
		this.wacCountry = wacCountry;
		this.wacHeadImgUrl = wacHeadImgUrl;
		this.wacSubscribeTime = wacSubscribeTime;
		this.wacStatus = wacStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WAC_ID", unique = true, nullable = false)
	public Integer getWacId() {
		return this.wacId;
	}

	public void setWacId(Integer wacId) {
		this.wacId = wacId;
	}

	@Column(name = "WAC_OPENID", length = 200)
	public String getWacOpenid() {
		return this.wacOpenid;
	}

	public void setWacOpenid(String wacOpenid) {
		this.wacOpenid = wacOpenid;
	}

	@Column(name = "WAC_SUBSCRIBE")
	public Integer getWacSubscribe() {
		return this.wacSubscribe;
	}

	public void setWacSubscribe(Integer wacSubscribe) {
		this.wacSubscribe = wacSubscribe;
	}

	@Column(name = "WAC_NICK_NAME", length = 80)
	public String getWacNickName() {
		return this.wacNickName;
	}

	public void setWacNickName(String wacNickName) {
		this.wacNickName = wacNickName;
	}

	@Column(name = "WAC_SEX", length = 1)
	public String getWacSex() {
		return this.wacSex;
	}

	public void setWacSex(String wacSex) {
		this.wacSex = wacSex;
	}

	@Column(name = "WAC_LANGUAGE", length = 20)
	public String getWacLanguage() {
		return this.wacLanguage;
	}

	public void setWacLanguage(String wacLanguage) {
		this.wacLanguage = wacLanguage;
	}

	@Column(name = "WAC_CITY", length = 100)
	public String getWacCity() {
		return this.wacCity;
	}

	public void setWacCity(String wacCity) {
		this.wacCity = wacCity;
	}

	@Column(name = "WAC_PROVINCE", length = 100)
	public String getWacProvince() {
		return this.wacProvince;
	}

	public void setWacProvince(String wacProvince) {
		this.wacProvince = wacProvince;
	}

	@Column(name = "WAC_COUNTRY", length = 100)
	public String getWacCountry() {
		return this.wacCountry;
	}

	public void setWacCountry(String wacCountry) {
		this.wacCountry = wacCountry;
	}

	@Column(name = "WAC_HEAD_IMG_URL", length = 200)
	public String getWacHeadImgUrl() {
		return this.wacHeadImgUrl;
	}

	public void setWacHeadImgUrl(String wacHeadImgUrl) {
		this.wacHeadImgUrl = wacHeadImgUrl;
	}

	@Column(name = "WAC_SUBSCRIBE_TIME", length = 23)
	public String getWacSubscribeTime() {
		return this.wacSubscribeTime;
	}

	public void setWacSubscribeTime(String wacSubscribeTime) {
		this.wacSubscribeTime = wacSubscribeTime;
	}

	@Column(name = "WAC_STATUS", length = 1)
	public String getWacStatus() {
		return this.wacStatus;
	}

	public void setWacStatus(String wacStatus) {
		this.wacStatus = wacStatus;
	}
	
	@Column(name = "WAC_WEC_ID")
	public Integer getWacWecId() {
		return wacWecId;
	}

	public void setWacWecId(Integer wacWecId) {
		this.wacWecId = wacWecId;
	}
	@Column(name = "WAC_APPID", length = 200)
	public String getWacAppid() {
		return wacAppid;
	}

	public void setWacAppid(String wacAppid) {
		this.wacAppid = wacAppid;
	}

	@Transient
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	@Transient
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Transient
	public String getWacWatchStatus_Q() {
		return wacWatchStatus_Q;
	}

	public void setWacWatchStatus_Q(String wacWatchStatus_Q) {
		this.wacWatchStatus_Q = wacWatchStatus_Q;
	}

	@Transient
	public String getWacBindStatus_Q() {
		return wacBindStatus_Q;
	}

	public void setWacBindStatus_Q(String wacBindStatus_Q) {
		this.wacBindStatus_Q = wacBindStatus_Q;
	}
	@Transient
	public String getWacMobile_Q() {
		return wacMobile_Q;
	}

	public void setWacMobile_Q(String wacMobile_Q) {
		this.wacMobile_Q = wacMobile_Q;
	}
	@Transient
	public String getWacUserName_Q() {
		return wacUserName_Q;
	}

	public void setWacUserName_Q(String wacUserName_Q) {
		this.wacUserName_Q = wacUserName_Q;
	}
	
	@Transient
	public String getWacContract_Q() {
		return wacContract_Q;
	}

	public void setWacContract_Q(String wacContract_Q) {
		this.wacContract_Q = wacContract_Q;
	}
	
	@Transient
	public String getWacEnterPrise_Q() {
		return wacEnterPrise_Q;
	}

	public void setWacEnterPrise_Q(String wacEnterPrise_Q) {
		this.wacEnterPrise_Q = wacEnterPrise_Q;
	}
	
	@Transient
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Transient
	public String getWacNickName_Q() {
		return wacNickName_Q;
	}

	public void setWacNickName_Q(String wacNickName_Q) {
		this.wacNickName_Q = wacNickName_Q;
	}
	
	@Transient
	public String getSumTotal() {
		return sumTotal;
	}

	public void setSumTotal(String sumTotal) {
		this.sumTotal = sumTotal;
	}
	
	@Transient
	public String getWacStatus_Q() {
		return wacStatus_Q;
	}

	public void setWacStatus_Q(String wacStatus_Q) {
		this.wacStatus_Q = wacStatus_Q;
	}
	
	@Transient
	public String getWacWecId_Q() {
		return wacWecId_Q;
	}

	public void setWacWecId_Q(String wacWecId_Q) {
		this.wacWecId_Q = wacWecId_Q;
	}

	
	
	
}