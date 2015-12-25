package com.wxcrm.website;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * WcWebsite entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_website", catalog = "wxcrm")
public class WcWebsite implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wcsId;
	private String wcsWebSiteName;
	private String wcsAppName;
	private String wcsAppId;
	private String wcsAppSecret;
	private String wcsRedirectUrl;
	private String wcsToken;
	private Integer wcsAdminId;
	private Integer wcsRegistor;
	private String wcsRegistdate;
	private String wcsType;


	private String wcsRandomNum;
	
	
	private String currentPage;
	private String pageSize;
	private String[] wcsIds;
	// Constructors

	/** default constructor */
	public WcWebsite() {
	}

	/** minimal constructor */
	public WcWebsite(String wcsWebSiteName) {
		this.wcsWebSiteName = wcsWebSiteName;
	}

	/** full constructor */
	public WcWebsite(String wcsWebSiteName, String wcsAppName, String wcsAppId,
			String wcsAppSecret, String wcsRedirectUrl, String wcsToken,
			Integer wcsAdminId, Integer wcsRegistor, String wcsRegistdate) {
		this.wcsWebSiteName = wcsWebSiteName;
		this.wcsAppName = wcsAppName;
		this.wcsAppId = wcsAppId;
		this.wcsAppSecret = wcsAppSecret;
		this.wcsRedirectUrl = wcsRedirectUrl;
		this.wcsToken = wcsToken;
		this.wcsAdminId = wcsAdminId;
		this.wcsRegistor = wcsRegistor;
		this.wcsRegistdate = wcsRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WCS_ID", unique = true, nullable = false)
	public Integer getWcsId() {
		return this.wcsId;
	}

	public void setWcsId(Integer wcsId) {
		this.wcsId = wcsId;
	}

	@Column(name = "WCS_WEbSITE_NAME", nullable = false, length = 200)
	public String getWcsWebSiteName() {
		return this.wcsWebSiteName;
	}

	public void setWcsWebSiteName(String wcsWebSiteName) {
		this.wcsWebSiteName = wcsWebSiteName;
	}

	@Column(name = "WCS_APP_NAME", length = 200)
	public String getWcsAppName() {
		return this.wcsAppName;
	}

	public void setWcsAppName(String wcsAppName) {
		this.wcsAppName = wcsAppName;
	}

	@Column(name = "WCS_APP_ID", length = 100)
	public String getWcsAppId() {
		return this.wcsAppId;
	}

	public void setWcsAppId(String wcsAppId) {
		this.wcsAppId = wcsAppId;
	}

	@Column(name = "WCS_APP_SECRET", length = 100)
	public String getWcsAppSecret() {
		return this.wcsAppSecret;
	}

	public void setWcsAppSecret(String wcsAppSecret) {
		this.wcsAppSecret = wcsAppSecret;
	}

	@Column(name = "WCS_REDIRECT_URL", length = 200)
	public String getWcsRedirectUrl() {
		return this.wcsRedirectUrl;
	}

	public void setWcsRedirectUrl(String wcsRedirectUrl) {
		this.wcsRedirectUrl = wcsRedirectUrl;
	}

	@Column(name = "WCS_TOKEN", length = 80)
	public String getWcsToken() {
		return this.wcsToken;
	}

	public void setWcsToken(String wcsToken) {
		this.wcsToken = wcsToken;
	}

	@Column(name = "WCS_ADMIN_ID")
	public Integer getWcsAdminId() {
		return this.wcsAdminId;
	}

	public void setWcsAdminId(Integer wcsAdminId) {
		this.wcsAdminId = wcsAdminId;
	}

	@Column(name = "WCS_REGISTOR")
	public Integer getWcsRegistor() {
		return this.wcsRegistor;
	}

	public void setWcsRegistor(Integer wcsRegistor) {
		this.wcsRegistor = wcsRegistor;
	}

	@Column(name = "WCS_REGISTDATE", length = 19)
	public String getWcsRegistdate() {
		return this.wcsRegistdate;
	}
	
	public void setWcsRegistdate(String wcsRegistdate) {
		this.wcsRegistdate = wcsRegistdate;
	}
	@Transient
	public static long getSerialVersionUID() {
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
	public String[] getWcsIds() {
		return wcsIds;
	}

	public void setWcsIds(String[] wcsIds) {
		this.wcsIds = wcsIds;
	}
	@Column(name = "WCS_RANDOM_NUM", length = 20)
	public String getWcsRandomNum() {
		return wcsRandomNum;
	}

	public void setWcsRandomNum(String wcsRandomNum) {
		this.wcsRandomNum = wcsRandomNum;
	}
	@Column(name = "WCS_TYPE", length = 1)  
	public String getWcsType() {
		return wcsType;
	}

	public void setWcsType(String wcsType) {
		this.wcsType = wcsType;
	}

	
	
}