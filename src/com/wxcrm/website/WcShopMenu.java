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
 * WcShopMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_shop_menu", catalog = "wxcrm")
public class WcShopMenu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wsmId;
	private String wsmName;
	private String wsmUrl;
	private String wsmLevel;
	private Integer wsmOrder;
	private String wsmDesc;
	private Integer wsmParentId;
	private Integer wsmRegistor;
	private String wsmRegistDate;
	
	private String currentPage;
	private String pageSize;
	private String[] wsmIds;
	
	private String wsmName_Q;
	private String wsmLevel_Q;
	private String[] menuIds;

	// Constructors

	/** default constructor */
	public WcShopMenu() {
	}

	/** minimal constructor */
	public WcShopMenu(String wsmName) {
		this.wsmName = wsmName;
	}

	/** full constructor */
	public WcShopMenu(String wsmName, String wsmUrl, String wsmLevel,
			Integer wsmOrder, String wsmDesc, Integer wsmParentId,
			Integer wsmRegistor, String wsmRegistDate) {
		this.wsmName = wsmName;
		this.wsmUrl = wsmUrl;
		this.wsmLevel = wsmLevel;
		this.wsmOrder = wsmOrder;
		this.wsmDesc = wsmDesc;
		this.wsmParentId = wsmParentId;
		this.wsmRegistor = wsmRegistor;
		this.wsmRegistDate = wsmRegistDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WSM_ID", unique = true, nullable = false)
	public Integer getWsmId() {
		return this.wsmId;
	}

	public void setWsmId(Integer wsmId) {
		this.wsmId = wsmId;
	}

	@Column(name = "WSM_NAME", nullable = false, length = 50)
	public String getWsmName() {
		return this.wsmName;
	}

	public void setWsmName(String wsmName) {
		this.wsmName = wsmName;
	}

	@Column(name = "WSM_URL", length = 200)
	public String getWsmUrl() {
		return this.wsmUrl;
	}

	public void setWsmUrl(String wsmUrl) {
		this.wsmUrl = wsmUrl;
	}

	@Column(name = "WSM_LEVEL", length = 1)
	public String getWsmLevel() {
		return this.wsmLevel;
	}

	public void setWsmLevel(String wsmLevel) {
		this.wsmLevel = wsmLevel;
	}

	@Column(name = "WSM_ORDER")
	public Integer getWsmOrder() {
		return this.wsmOrder;
	}

	public void setWsmOrder(Integer wsmOrder) {
		this.wsmOrder = wsmOrder;
	}

	@Column(name = "WSM_DESC", length = 400)
	public String getWsmDesc() {
		return this.wsmDesc;
	}

	public void setWsmDesc(String wsmDesc) {
		this.wsmDesc = wsmDesc;
	}

	@Column(name = "WSM_PARENT_ID")
	public Integer getWsmParentId() {
		return this.wsmParentId;
	}

	public void setWsmParentId(Integer wsmParentId) {
		this.wsmParentId = wsmParentId;
	}

	@Column(name = "WSM_REGISTOR")
	public Integer getWsmRegistor() {
		return this.wsmRegistor;
	}

	public void setWsmRegistor(Integer wsmRegistor) {
		this.wsmRegistor = wsmRegistor;
	}

	@Column(name = "WSM_REGIST_DATE", length = 19)
	public String getWsmRegistDate() {
		return this.wsmRegistDate;
	}

	public void setWsmRegistDate(String wsmRegistDate) {
		this.wsmRegistDate = wsmRegistDate;
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
	public String[] getWsmIds() {
		return wsmIds;
	}

	public void setWsmIds(String[] wsmIds) {
		this.wsmIds = wsmIds;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Transient
	public String getWsmName_Q() {
		return wsmName_Q;
	}

	public void setWsmName_Q(String wsmName_Q) {
		this.wsmName_Q = wsmName_Q;
	}
	@Transient
	public String getWsmLevel_Q() {
		return wsmLevel_Q;
	}

	public void setWsmLevel_Q(String wsmLevel_Q) {
		this.wsmLevel_Q = wsmLevel_Q;
	}
	@Transient
	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

	
	
}