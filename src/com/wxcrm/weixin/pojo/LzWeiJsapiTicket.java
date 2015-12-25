package com.wxcrm.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiJsapiTicket entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_JSAPI_TICKET")
public class LzWeiJsapiTicket implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wjtId;
	private Integer wjtWecId;
	private String wjtAppid;
	private String wjtJsapiTicket;
	private String wjtExpiresIn;
	private String wjtCreatTime;
	private String wjtStatus;
	
	private String currentPage;
	private String pageSize;

	private String watWecId_Q;

	// Constructors

	/** default constructor */
	public LzWeiJsapiTicket() {
	}

	/** minimal constructor */
	public LzWeiJsapiTicket(Integer wjtId) {
		this.wjtId = wjtId;
	}

	/** full constructor */
	public LzWeiJsapiTicket(Integer wjtId, Integer wjtWecId, String wjtAppid,
			String wjtJsapiTicket, String wjtExpiresIn, String wjtCreatTime,
			String wjtStatus) {
		this.wjtId = wjtId;
		this.wjtWecId = wjtWecId;
		this.wjtAppid = wjtAppid;
		this.wjtJsapiTicket = wjtJsapiTicket;
		this.wjtExpiresIn = wjtExpiresIn;
		this.wjtCreatTime = wjtCreatTime;
		this.wjtStatus = wjtStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WJT_ID", unique = true, nullable = false)
	public Integer getWjtId() {
		return this.wjtId;
	}

	public void setWjtId(Integer wjtId) {
		this.wjtId = wjtId;
	}

	@Column(name = "WJT_WEC_ID")
	public Integer getWjtWecId() {
		return this.wjtWecId;
	}

	public void setWjtWecId(Integer wjtWecId) {
		this.wjtWecId = wjtWecId;
	}

	@Column(name = "WJT_APPID", length = 80)
	public String getWjtAppid() {
		return this.wjtAppid;
	}

	public void setWjtAppid(String wjtAppid) {
		this.wjtAppid = wjtAppid;
	}

	@Column(name = "WJT_JSAPI_TICKET", length = 200)
	public String getWjtJsapiTicket() {
		return this.wjtJsapiTicket;
	}

	public void setWjtJsapiTicket(String wjtJsapiTicket) {
		this.wjtJsapiTicket = wjtJsapiTicket;
	}

	@Column(name = "WJT_EXPIRES_IN", length = 23)
	public String getWjtExpiresIn() {
		return this.wjtExpiresIn;
	}

	public void setWjtExpiresIn(String wjtExpiresIn) {
		this.wjtExpiresIn = wjtExpiresIn;
	}

	@Column(name = "WJT_CREAT_TIME", length = 23)
	public String getWjtCreatTime() {
		return this.wjtCreatTime;
	}

	public void setWjtCreatTime(String wjtCreatTime) {
		this.wjtCreatTime = wjtCreatTime;
	}

	@Column(name = "WJT_STATUS", length = 1)
	public String getWjtStatus() {
		return this.wjtStatus;
	}

	public void setWjtStatus(String wjtStatus) {
		this.wjtStatus = wjtStatus;
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
	public String getWatWecId_Q() {
		return watWecId_Q;
	}

	public void setWatWecId_Q(String watWecId_Q) {
		this.watWecId_Q = watWecId_Q;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	

}