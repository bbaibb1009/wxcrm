package com.wxcrm.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiAccesstoken entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_ACCESSTOKEN")
public class LzWeiAccesstoken implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer watId;
	private String  watToken;
	private String  watExpiresIn;
	private String  watStatus; //;
	private Integer watWecId;
	private String  watAppid;
	
	
	
	private String currentPage;
	private String pageSize;

	private String watWecId_Q;
	
	// Constructors

	

	/** default constructor */
	public LzWeiAccesstoken() {
	}

	/** minimal constructor */
	public LzWeiAccesstoken(Integer watId) {
		this.watId = watId;
	}

	/** full constructor */
	public LzWeiAccesstoken(Integer watId, String watToken, String watExpiresIn) {
		this.watId = watId;
		this.watToken = watToken;
		this.watExpiresIn = watExpiresIn;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WAT_ID", unique = true, nullable = false)
	public Integer getWatId() {
		return this.watId;
	}

	public void setWatId(Integer watId) {
		this.watId = watId;
	}

	@Column(name = "WAT_TOKEN", length = 200)
	public String getWatToken() {
		return this.watToken;
	}

	public void setWatToken(String watToken) {
		this.watToken = watToken;
	}

	@Column(name = "WAT_EXPIRES_IN", length = 23)
	public String getWatExpiresIn() {
		return this.watExpiresIn;
	}

	public void setWatExpiresIn(String watExpiresIn) {
		this.watExpiresIn = watExpiresIn;
	}
	
	@Column(name = "WAT_STATUS", length = 1)
	public String getWatStatus() {
		return watStatus;
	}

	public void setWatStatus(String watStatus) {
		this.watStatus = watStatus;
	}
	
	@Column(name = "WAT_WEC_ID")
	public Integer getWatWecId() {
		return this.watWecId;
	}

	public void setWatWecId(Integer watWecId) {
		this.watWecId = watWecId;
	}
	
	
	@Column(name = "WAT_APPID", length = 200)
	public String getWatAppid() {
		return watAppid;
	}

	public void setWatAppid(String watAppid) {
		this.watAppid = watAppid;
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

	public void setPageSize(String pageSize) 
	{
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
	public static long getSerialVersionUID() 
	{
		return serialVersionUID;
	}

	
	
	

}