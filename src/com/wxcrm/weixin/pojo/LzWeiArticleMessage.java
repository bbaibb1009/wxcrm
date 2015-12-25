package com.wxcrm.weixin.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiArticleMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "lz_wei_article_message", catalog = "wxcrm")
public class LzWeiArticleMessage implements java.io.Serializable {

	/**
	 * 
	 */
	
	// Fields
	private Integer wamId;
	private String wamTitle;
	private String wamDescription;
	private String wamPicurl;
	private String wamUrl;
	private String wamStatus;
	private String wamDesc;
	private Integer wamRegistor;
	private String wamRegistdate;
	private static final long serialVersionUID = 1L;
	// Constructors

	/** default constructor */
	public LzWeiArticleMessage() {
	}

	/** full constructor */
	public LzWeiArticleMessage(String wamTitle, String wamDescription,
			String wamPicurl, String wamUrl, String wamStatus, String wamDesc,
			Integer wamRegistor, String wamRegistdate) {
		this.wamTitle = wamTitle;
		this.wamDescription = wamDescription;
		this.wamPicurl = wamPicurl;
		this.wamUrl = wamUrl;
		this.wamStatus = wamStatus;
		this.wamDesc = wamDesc;
		this.wamRegistor = wamRegistor;
		this.wamRegistdate = wamRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WAM_ID", unique = true, nullable = false)
	public Integer getWamId() {
		return this.wamId;
	}

	public void setWamId(Integer wamId) {
		this.wamId = wamId;
	}

	@Column(name = "WAM_TITLE", length = 200)
	public String getWamTitle() {
		return this.wamTitle;
	}

	public void setWamTitle(String wamTitle) {
		this.wamTitle = wamTitle;
	}

	@Column(name = "WAM_DESCRIPTION", length = 200)
	public String getWamDescription() {
		return this.wamDescription;
	}

	public void setWamDescription(String wamDescription) {
		this.wamDescription = wamDescription;
	}

	@Column(name = "WAM_PICURL", length = 200)
	public String getWamPicurl() {
		return this.wamPicurl;
	}

	public void setWamPicurl(String wamPicurl) {
		this.wamPicurl = wamPicurl;
	}

	@Column(name = "WAM_URL", length = 200)
	public String getWamUrl() {
		return this.wamUrl;
	}

	public void setWamUrl(String wamUrl) {
		this.wamUrl = wamUrl;
	}

	@Column(name = "WAM_STATUS", length = 20)
	public String getWamStatus() {
		return this.wamStatus;
	}

	public void setWamStatus(String wamStatus) {
		this.wamStatus = wamStatus;
	}

	@Column(name = "WAM_DESC", length = 200)
	public String getWamDesc() {
		return this.wamDesc;
	}

	public void setWamDesc(String wamDesc) {
		this.wamDesc = wamDesc;
	}

	@Column(name = "WAM_REGISTOR")
	public Integer getWamRegistor() {
		return this.wamRegistor;
	}

	public void setWamRegistor(Integer wamRegistor) {
		this.wamRegistor = wamRegistor;
	}

	@Column(name = "WAM_REGISTDATE", length = 19)
	public String getWamRegistdate() {
		return this.wamRegistdate;
	}

	public void setWamRegistdate(String wamRegistdate) {
		this.wamRegistdate = wamRegistdate;
	}
	
	@Transient
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}