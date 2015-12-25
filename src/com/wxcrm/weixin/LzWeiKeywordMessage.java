package com.wxcrm.weixin;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LzWeiKeywordMessage entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_KEYWORD_MESSAGE")
public class LzWeiKeywordMessage implements java.io.Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private Integer wkgId;
	private Integer wkgWecId;
	private String  wkgAppId;
	private String  wkgKeywords;
	private Integer wkgWmgId;
	private String  wkgStatus;
	private String  wkgDesc;
	private Integer wkgRegistor;
	private String  wkgRegistdate;

	// Constructors

	/** default constructor */
	public LzWeiKeywordMessage() {
	}

	/** minimal constructor */
	public LzWeiKeywordMessage(Integer wkgId) {
		this.wkgId = wkgId;
	}

	/** full constructor */
	public LzWeiKeywordMessage(Integer wkgId, Integer wkgWecId,
			String wkgAppId, String wkgKeywords, Integer wkgWmgId,
			String wkgStatus, String wkgDesc, Integer wkgRegistor,
			String wkgRegistdate) {
		this.wkgId = wkgId;
		this.wkgWecId = wkgWecId;
		this.wkgAppId = wkgAppId;
		this.wkgKeywords = wkgKeywords;
		this.wkgWmgId = wkgWmgId;
		this.wkgStatus = wkgStatus;
		this.wkgDesc = wkgDesc;
		this.wkgRegistor = wkgRegistor;
		this.wkgRegistdate = wkgRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WKG_ID", unique = true, nullable = false)
	public Integer getWkgId() {
		return this.wkgId;
	}

	public void setWkgId(Integer wkgId) {
		this.wkgId = wkgId;
	}

	@Column(name = "WKG_WEC_ID")
	public Integer getWkgWecId() {
		return this.wkgWecId;
	}

	public void setWkgWecId(Integer wkgWecId) {
		this.wkgWecId = wkgWecId;
	}

	@Column(name = "WKG_APP_ID", length = 200)
	public String getWkgAppId() {
		return this.wkgAppId;
	}

	public void setWkgAppId(String wkgAppId) {
		this.wkgAppId = wkgAppId;
	}

	@Column(name = "WKG_KEYWORDS", length = 200)
	public String getWkgKeywords() {
		return this.wkgKeywords;
	}

	public void setWkgKeywords(String wkgKeywords) {
		this.wkgKeywords = wkgKeywords;
	}

	@Column(name = "WKG_WMG_ID")
	public Integer getWkgWmgId() {
		return this.wkgWmgId;
	}

	public void setWkgWmgId(Integer wkgWmgId) {
		this.wkgWmgId = wkgWmgId;
	}

	@Column(name = "WKG_STATUS", length = 20)
	public String getWkgStatus() {
		return this.wkgStatus;
	}

	public void setWkgStatus(String wkgStatus) {
		this.wkgStatus = wkgStatus;
	}

	@Column(name = "WKG_DESC", length = 200)
	public String getWkgDesc() {
		return this.wkgDesc;
	}

	public void setWkgDesc(String wkgDesc) {
		this.wkgDesc = wkgDesc;
	}

	@Column(name = "WKG_REGISTOR")
	public Integer getWkgRegistor() {
		return this.wkgRegistor;
	}

	public void setWkgRegistor(Integer wkgRegistor) {
		this.wkgRegistor = wkgRegistor;
	}

	@Column(name = "WKG_REGISTDATE", length = 23)
	public String getWkgRegistdate() {
		return this.wkgRegistdate;
	}

	public void setWkgRegistdate(String wkgRegistdate) {
		this.wkgRegistdate = wkgRegistdate;
	}

}