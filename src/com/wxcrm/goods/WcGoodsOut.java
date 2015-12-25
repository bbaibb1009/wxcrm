package com.wxcrm.goods;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * WcGoodsOut entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_goods_out", catalog = "wxcrm")
public class WcGoodsOut implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wgoId;
	private String wgoName;
	private Integer wgoWcsId;
	private String wgoType;
	private Double wgoOutNum;
	private Double wgoOutPrice;
	private Integer wgoOutAdmin;
	private String wgoOutTime;
	private String wgoDesc;
	private String wgoStatus;
	private Integer wgoRegistor;
	private String wgoRegistdate;
	
	private Integer wgoWmbId;
	
	private String currentPage;
	private String pageSize;
	private String[] wgoIds;

	// Constructors

	/** default constructor */
	public WcGoodsOut() {
	}

	/** minimal constructor */
	public WcGoodsOut(String wgoName, Integer wgoWcsId) {
		this.wgoName = wgoName;
		this.wgoWcsId = wgoWcsId;
	}

	/** full constructor */
	public WcGoodsOut(String wgoName, Integer wgoWcsId, String wgoType,
			Double wgoOutNum, Double wgoOutPrice, Integer wgoOutAdmin,
			String wgoOutTime, String wgoDesc, String wgoStatus,
			Integer wgoRegistor, String wgoRegistdate) {
		this.wgoName = wgoName;
		this.wgoWcsId = wgoWcsId;
		this.wgoType = wgoType;
		this.wgoOutNum = wgoOutNum;
		this.wgoOutPrice = wgoOutPrice;
		this.wgoOutAdmin = wgoOutAdmin;
		this.wgoOutTime = wgoOutTime;
		this.wgoDesc = wgoDesc;
		this.wgoStatus = wgoStatus;
		this.wgoRegistor = wgoRegistor;
		this.wgoRegistdate = wgoRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WGO_ID", unique = true, nullable = false)
	public Integer getWgoId() {
		return this.wgoId;
	}

	public void setWgoId(Integer wgoId) {
		this.wgoId = wgoId;
	}

	@Column(name = "WGO_NAME", nullable = false, length = 200)
	public String getWgoName() {
		return this.wgoName;
	}

	public void setWgoName(String wgoName) {
		this.wgoName = wgoName;
	}

	@Column(name = "WGO_WCS_ID", nullable = false)
	public Integer getWgoWcsId() {
		return this.wgoWcsId;
	}

	public void setWgoWcsId(Integer wgoWcsId) {
		this.wgoWcsId = wgoWcsId;
	}

	@Column(name = "WGO_TYPE", length = 1)
	public String getWgoType() {
		return this.wgoType;
	}

	public void setWgoType(String wgoType) {
		this.wgoType = wgoType;
	}

	@Column(name = "WGO_OUT_NUM", precision = 11)
	public Double getWgoOutNum() {
		return this.wgoOutNum;
	}

	public void setWgoOutNum(Double wgoOutNum) {
		this.wgoOutNum = wgoOutNum;
	}

	@Column(name = "WGO_OUT_PRICE", precision = 11)
	public Double getWgoOutPrice() {
		return this.wgoOutPrice;
	}

	public void setWgoOutPrice(Double wgoOutPrice) {
		this.wgoOutPrice = wgoOutPrice;
	}

	@Column(name = "WGO_OUT_ADMIN")
	public Integer getWgoOutAdmin() {
		return this.wgoOutAdmin;
	}

	public void setWgoOutAdmin(Integer wgoOutAdmin) {
		this.wgoOutAdmin = wgoOutAdmin;
	}

	@Column(name = "WGO_OUT_TIME", length = 19)
	public String getWgoOutTime() {
		return this.wgoOutTime;
	}

	public void setWgoOutTime(String wgoOutTime) {
		this.wgoOutTime = wgoOutTime;
	}

	@Column(name = "WGO_DESC", length = 200)
	public String getWgoDesc() {
		return this.wgoDesc;
	}

	public void setWgoDesc(String wgoDesc) {
		this.wgoDesc = wgoDesc;
	}

	@Column(name = "WGO_STATUS", length = 20)
	public String getWgoStatus() {
		return this.wgoStatus;
	}

	public void setWgoStatus(String wgoStatus) {
		this.wgoStatus = wgoStatus;
	}

	@Column(name = "WGO_REGISTOR")
	public Integer getWgoRegistor() {
		return this.wgoRegistor;
	}

	public void setWgoRegistor(Integer wgoRegistor) {
		this.wgoRegistor = wgoRegistor;
	}

	@Column(name = "WGO_REGISTDATE", length = 19)
	public String getWgoRegistdate() {
		return this.wgoRegistdate;
	}

	public void setWgoRegistdate(String wgoRegistdate) {
		this.wgoRegistdate = wgoRegistdate;
	}
	
	 
	
	@Column(name = "WGO_WMB_ID")
	public Integer getWgoWmbId() {
		return wgoWmbId;
	}

	public void setWgoWmbId(Integer wgoWmbId) {
		this.wgoWmbId = wgoWmbId;
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
	public String[] getWgoIds() {
		return wgoIds;
	}

	public void setWgoIds(String[] wgoIds) {
		this.wgoIds = wgoIds;
	}
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	

}