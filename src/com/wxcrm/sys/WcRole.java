package com.wxcrm.sys;

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
 * WcRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_role", catalog = "wxcrm")
public class WcRole implements java.io.Serializable {

	// Fields

	private Integer wroRoleId;
	private String wroRoleName;
	private String wroRoleDesc;
	private Integer wroRegistor;
	private String wroRegistDate;
	
	private String[] wroRoleIds;
	private String currentPage;
	private String pageSize;
	private String menuIds;
	private String adminIds;
	
	private String roleName_Q;

	// Constructors

	/** default constructor */
	public WcRole() {
	}

	/** minimal constructor */
	public WcRole(String wroRoleName) {
		this.wroRoleName = wroRoleName;
	}

	/** full constructor */
	public WcRole(String wroRoleName, String wroRoleDesc, Integer wroRegistor,
			String wroRegistDate) {
		this.wroRoleName = wroRoleName;
		this.wroRoleDesc = wroRoleDesc;
		this.wroRegistor = wroRegistor;
		this.wroRegistDate = wroRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WRO_ROLE_ID", unique = true, nullable = false)
	public Integer getWroRoleId() {
		return this.wroRoleId;
	}

	public void setWroRoleId(Integer wroRoleId) {
		this.wroRoleId = wroRoleId;
	}

	@Column(name = "WRO_ROLE_NAME", nullable = false, length = 100)
	public String getWroRoleName() {
		return this.wroRoleName;
	}

	public void setWroRoleName(String wroRoleName) {
		this.wroRoleName = wroRoleName;
	}

	@Column(name = "WRO_ROLE_DESC", length = 200)
	public String getWroRoleDesc() {
		return this.wroRoleDesc;
	}

	public void setWroRoleDesc(String wroRoleDesc) {
		this.wroRoleDesc = wroRoleDesc;
	}

	@Column(name = "WRO_REGISTOR")
	public Integer getWroRegistor() {
		return this.wroRegistor;
	}

	public void setWroRegistor(Integer wroRegistor) {
		this.wroRegistor = wroRegistor;
	}


	@Column(name = "WRO_REGIST_DATE", length = 19)
	public String getWroRegistDate() {
		return this.wroRegistDate;
	}

	public void setWroRegistDate(String wroRegistDate) {
		this.wroRegistDate = wroRegistDate;
	}
	@Transient
	public String[] getWroRoleIds() {
		return wroRoleIds;
	}

	public void setWroRoleIds(String[] wroRoleIds) {
		this.wroRoleIds = wroRoleIds;
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
	@Transient
	public String getRoleName_Q() {
		return roleName_Q;
	}

	public void setRoleName_Q(String roleName_Q) {
		this.roleName_Q = roleName_Q;
	}

	
	
}