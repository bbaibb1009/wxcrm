package com.wxcrm.website;

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
 * WcShopRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_shop_role", catalog = "wxcrm")
public class WcShopRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wsrRoleId;
	private String wsrRoleName;
	private String wsrRoleDesc;
	private Integer wsrRegistor;
	private String wsrRegistDate;
	private String roleName_Q;
	
	private String currentPage;
	private String pageSize;
	private String[] wsrRoleIds;
	
	private String menuIds;
	private String adminIds;

	// Constructors

	/** default constructor */
	public WcShopRole() {
	}

	/** minimal constructor */
	public WcShopRole(String wsrRoleName) {
		this.wsrRoleName = wsrRoleName;
	}

	/** full constructor */
	public WcShopRole(String wsrRoleName, String wsrRoleDesc,
			Integer wsrRegistor, String wsrRegistDate) {
		this.wsrRoleName = wsrRoleName;
		this.wsrRoleDesc = wsrRoleDesc;
		this.wsrRegistor = wsrRegistor;
		this.wsrRegistDate = wsrRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WSR_ROLE_ID", unique = true, nullable = false)
	public Integer getWsrRoleId() {
		return this.wsrRoleId;
	}

	public void setWsrRoleId(Integer wsrRoleId) {
		this.wsrRoleId = wsrRoleId;
	}

	@Column(name = "WSR_ROLE_NAME", nullable = false, length = 100)
	public String getWsrRoleName() {
		return this.wsrRoleName;
	}

	public void setWsrRoleName(String wsrRoleName) {
		this.wsrRoleName = wsrRoleName;
	}

	@Column(name = "WSR_ROLE_DESC", length = 200)
	public String getWsrRoleDesc() {
		return this.wsrRoleDesc;
	}

	public void setWsrRoleDesc(String wsrRoleDesc) {
		this.wsrRoleDesc = wsrRoleDesc;
	}

	@Column(name = "WSR_REGISTOR")
	public Integer getWsrRegistor() {
		return this.wsrRegistor;
	}

	public void setWsrRegistor(Integer wsrRegistor) {
		this.wsrRegistor = wsrRegistor;
	}

	@Column(name = "WSR_REGIST_DATE", length = 19)
	public String getWsrRegistDate() {
		return this.wsrRegistDate;
	}

	public void setWsrRegistDate(String wsrRegistDate) {
		this.wsrRegistDate = wsrRegistDate;
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
	public String[] getWsrRoleIds() {
		return wsrRoleIds;
	}

	public void setWsrRoleIds(String[] wsrRoleIds) {
		this.wsrRoleIds = wsrRoleIds;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@Transient
	public String getRoleName_Q() {
		return roleName_Q;
	}

	public void setRoleName_Q(String roleName_Q) {
		this.roleName_Q = roleName_Q;
	}
	@Transient
	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	@Transient
	public String getAdminIds() {
		return adminIds;
	}

	public void setAdminIds(String adminIds) {
		this.adminIds = adminIds;
	}
	
	

}