package com.wxcrm.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiMessage entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_MESSAGE")
public class LzWeiMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wmgId;
	private String  wmgAppId;
	private String 	wmgContent;
	private String 	wmgContentXml;
	private String 	wmgReplyType;
	private String 	wmgMsgType;
	private String 	wmgAesType;
	private String 	wmgStatus;
	private String 	wmgDesc;
	private Integer wmgRegistor;
	private String 	wmgRegistdate;
	
	private String 	currentPage;
	private String 	pageSize;
	private String[] wmgIds;
	
	private String 	wmgReplyType_Q;
	private String 	wmgMsgType_Q;
	private String 	wmgAesType_Q;

	// Constructors
	

	/** default constructor */
	public LzWeiMessage() {
	}

	/** minimal constructor */
	public LzWeiMessage(Integer wmgId) {
		this.wmgId = wmgId;
	}

	/** full constructor */
	public LzWeiMessage(Integer wmgId, String wmgContent, String wmgReplyType,
			String wmgMsgType, String wmgAesType, String wmgStatus,
			String wmgDesc, Integer wmgRegistor, String wmgRegistdate) {
		this.wmgId = wmgId;
		this.wmgContent = wmgContent;
		this.wmgReplyType = wmgReplyType;
		this.wmgMsgType = wmgMsgType;
		this.wmgAesType = wmgAesType;
		this.wmgStatus = wmgStatus;
		this.wmgDesc = wmgDesc;
		this.wmgRegistor = wmgRegistor;
		this.wmgRegistdate = wmgRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WMG_ID", unique = true, nullable = false)
	public Integer getWmgId() {
		return this.wmgId;
	}

	public void setWmgId(Integer wmgId) {
		this.wmgId = wmgId;
	}

	@Column(name = "WMG_CONTENT", length = 800)
	public String getWmgContent() {
		return this.wmgContent;
	}

	public void setWmgContent(String wmgContent) {
		this.wmgContent = wmgContent;
	}
	@Column(name = "WMG_CONTENT_XML", length = 2000)
	public String getWmgContentXml() {
		return wmgContentXml;
	}

	public void setWmgContentXml(String wmgContentXml) {
		this.wmgContentXml = wmgContentXml;
	}
	
	@Column(name = "WMG_REPLY_TYPE", length = 1)
	public String getWmgReplyType() {
		return this.wmgReplyType;
	}

	public void setWmgReplyType(String wmgReplyType) {
		this.wmgReplyType = wmgReplyType;
	}

	@Column(name = "WMG_MSG_TYPE", length = 1)
	public String getWmgMsgType() {
		return this.wmgMsgType;
	}

	public void setWmgMsgType(String wmgMsgType) {
		this.wmgMsgType = wmgMsgType;
	}

	@Column(name = "WMG_AES_TYPE", length = 1)
	public String getWmgAesType() {
		return this.wmgAesType;
	}

	public void setWmgAesType(String wmgAesType) {
		this.wmgAesType = wmgAesType;
	}

	@Column(name = "WMG_STATUS", length = 20)
	public String getWmgStatus() {
		return this.wmgStatus;
	}

	public void setWmgStatus(String wmgStatus) {
		this.wmgStatus = wmgStatus;
	}

	@Column(name = "WMG_DESC", length = 200)
	public String getWmgDesc() {
		return this.wmgDesc;
	}

	public void setWmgDesc(String wmgDesc) {
		this.wmgDesc = wmgDesc;
	}

	@Column(name = "WMG_REGISTOR")
	public Integer getWmgRegistor() {
		return this.wmgRegistor;
	}

	public void setWmgRegistor(Integer wmgRegistor) {
		this.wmgRegistor = wmgRegistor;
	}

	@Column(name = "WMG_REGISTDATE", length = 23)
	public String getWmgRegistdate() {
		return this.wmgRegistdate;
	}

	public void setWmgRegistdate(String wmgRegistdate) {
		this.wmgRegistdate = wmgRegistdate;
	}
	
	@Column(name = "WMG_APP_ID", length = 200)
	public String getWmgAppId() {
		return wmgAppId;
	}

	public void setWmgAppId(String wmgAppId) {
		this.wmgAppId = wmgAppId;
	}
	
	@Transient
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String[] getWmgIds() {
		return wmgIds;
	}

	public void setWmgIds(String[] wmgIds) {
		this.wmgIds = wmgIds;
	}
	
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Transient
	public String getWmgReplyType_Q() {
		return wmgReplyType_Q;
	}

	public void setWmgReplyType_Q(String wmgReplyType_Q) {
		this.wmgReplyType_Q = wmgReplyType_Q;
	}

	@Transient
	public String getWmgMsgType_Q() {
		return wmgMsgType_Q;
	}

	public void setWmgMsgType_Q(String wmgMsgType_Q) {
		this.wmgMsgType_Q = wmgMsgType_Q;
	}

	@Transient
	public String getWmgAesType_Q() {
		return wmgAesType_Q;
	}

	public void setWmgAesType_Q(String wmgAesType_Q) {
		this.wmgAesType_Q = wmgAesType_Q;
	}

	
}