package com.wxcrm.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_MENU")
public class LzWeiMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wmuId;
	private Integer wmuWecId;
	private String 	wmuAppId;


	private String 	wmuJson;
	private String 	wmuDesc;
	private String 	wmuStatus;
	private String 	wmuAppSecret;
	private String 	currentPage;
	private String 	pageSize;
	
	

	// Constructors

	/** default constructor */
	public LzWeiMenu() {
	}

	/** minimal constructor */
	public LzWeiMenu(Integer wmuId) {
		this.wmuId = wmuId;
	}

	/** full constructor */
	public LzWeiMenu(Integer wmuId, String wmuAppId, String wmuJson,
			String wmuStatus) {
		this.wmuId = wmuId;
		this.wmuAppId = wmuAppId;
		this.wmuJson = wmuJson;
		this.wmuStatus = wmuStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WMU_ID", unique = true, nullable = false)
	public Integer getWmuId() {
		return this.wmuId;
	}

	public void setWmuId(Integer wmuId) {
		this.wmuId = wmuId;
	}

	
	
	
	@Column(name = "WMU_APP_ID", length = 200)
	public String getWmuAppId() {
		return this.wmuAppId;
	}

	public void setWmuAppId(String wmuAppId) {
		this.wmuAppId = wmuAppId;
	}

	@Column(name = "WMU_JSON")
	public String getWmuJson() {
		return this.wmuJson;
	}

	public void setWmuJson(String wmuJson) {
		this.wmuJson = wmuJson;
	}


	@Column(name = "WMU_DESC", length = 200)
	public String getWmuDesc() {
		return wmuDesc;
	}
	public void setWmuDesc(String wmuDesc) {
		this.wmuDesc = wmuDesc;
	}
	
	
	@Column(name = "WMU_STATUS", length = 1)
	public String getWmuStatus() {
		return this.wmuStatus;
	}

	public void setWmuStatus(String wmuStatus) {
		this.wmuStatus = wmuStatus;
	}
	
	@Column(name = "WMU_WEC_ID")
	public Integer getWmuWecId() {
		return wmuWecId;
	}

	public void setWmuWecId(Integer wmuWecId) {
		this.wmuWecId = wmuWecId;
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
	public String getWmuAppSecret() {
		return wmuAppSecret;
	}

	public void setWmuAppSecret(String wmuAppSecret) {
		this.wmuAppSecret = wmuAppSecret;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	

}