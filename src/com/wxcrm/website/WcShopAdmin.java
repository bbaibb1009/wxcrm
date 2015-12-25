package com.wxcrm.website;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcShopAdmin entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_shop_admin", catalog = "wxcrm")
public class WcShopAdmin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wsaId;
	private String wsaUsername;
	private String wsaPwd;
	private String wsaName;
	private String wsaSex;
	private Integer wsaRegistor;
	private String wsaRegistdate;
	private String wsaLogindate;
	private String wsaStatus;
	private String wsaPwdMd5;
	
	private String currentPage;
	private String pageSize;
	private String[] wsaIds;
	private String[] roleIds2;
	private String[] roleIds;
	private String menuIds;
	
	private String wsaUsername_Q;
	private String wsaName_Q;
	// Constructors

	/** default constructor */
	public WcShopAdmin() {
	}

	/** minimal constructor */
	public WcShopAdmin(String wsaUsername) {
		this.wsaUsername = wsaUsername;
	}

	/** full constructor */
	public WcShopAdmin(String wsaUsername, String wsaPwd, String wsaName,
			String wsaSex, Integer wsaRegistor, String wsaRegistdate,
			String wsaLogindate, String wsaStatus) {
		this.wsaUsername = wsaUsername;
		this.wsaPwd = wsaPwd;
		this.wsaName = wsaName;
		this.wsaSex = wsaSex;
		this.wsaRegistor = wsaRegistor;
		this.wsaRegistdate = wsaRegistdate;
		this.wsaLogindate = wsaLogindate;
		this.wsaStatus = wsaStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WSA_ID", unique = true, nullable = false)
	public Integer getWsaId() {
		return this.wsaId;
	}

	public void setWsaId(Integer wsaId) {
		this.wsaId = wsaId;
	}

	@Column(name = "WSA_USERNAME", nullable = false, length = 20)
	public String getWsaUsername() {
		return this.wsaUsername;
	}

	public void setWsaUsername(String wsaUsername) {
		this.wsaUsername = wsaUsername;
	}

	@Column(name = "WSA_PWD", length = 80)
	public String getWsaPwd() {
		return this.wsaPwd;
	}

	public void setWsaPwd(String wsaPwd) {
		this.wsaPwd = wsaPwd;
	}

	@Column(name = "WSA_NAME", length = 200)
	public String getWsaName() {
		return this.wsaName;
	}

	public void setWsaName(String wsaName) {
		this.wsaName = wsaName;
	}

	@Column(name = "WSA_SEX", length = 1)
	public String getWsaSex() {
		return this.wsaSex;
	}

	public void setWsaSex(String wsaSex) {
		this.wsaSex = wsaSex;
	}

	@Column(name = "WSA_REGISTOR")
	public Integer getWsaRegistor() {
		return this.wsaRegistor;
	}

	public void setWsaRegistor(Integer wsaRegistor) {
		this.wsaRegistor = wsaRegistor;
	}

	@Column(name = "WSA_REGISTDATE", length = 19)
	public String getWsaRegistdate() {
		return this.wsaRegistdate;
	}

	public void setWsaRegistdate(String wsaRegistdate) {
		this.wsaRegistdate = wsaRegistdate;
	}

	@Column(name = "WSA_LOGINDATE", length = 19)
	public String getWsaLogindate() {
		return this.wsaLogindate;
	}

	public void setWsaLogindate(String wsaLogindate) {
		this.wsaLogindate = wsaLogindate;
	}

	@Column(name = "WSA_STATUS", length = 20)
	public String getWsaStatus() {
		return this.wsaStatus;
	}

	public void setWsaStatus(String wsaStatus) {
		this.wsaStatus = wsaStatus;
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
	public String[] getWsaIds() {
		return wsaIds;
	}

	public void setWsaIds(String[] wsaIds) {
		this.wsaIds = wsaIds;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Transient
	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	@Transient
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	@Transient
	public String getWsaPwdMd5() {
		return wsaPwdMd5;
	}

	public void setWsaPwdMd5(String wsaPwdMd5) {
		this.wsaPwdMd5 = wsaPwdMd5;
	}
	@Transient
	public String[] getRoleIds2() {
		return roleIds2;
	}

	public void setRoleIds2(String[] roleIds2) {
		this.roleIds2 = roleIds2;
	}
	@Transient
	public String getWsaUsername_Q() {
		return wsaUsername_Q;
	}

	public void setWsaUsername_Q(String wsaUsername_Q) {
		this.wsaUsername_Q = wsaUsername_Q;
	}
	@Transient
	public String getWsaName_Q() {
		return wsaName_Q;
	}

	public void setWsaName_Q(String wsaName_Q) {
		this.wsaName_Q = wsaName_Q;
	}

	
	
	
}