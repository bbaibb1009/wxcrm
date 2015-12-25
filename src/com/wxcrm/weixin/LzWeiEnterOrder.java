package com.wxcrm.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiEnterOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_ENTER_ORDER")
public class LzWeiEnterOrder implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer weoId;
	private Integer weoGqId;
	private Integer weoWetId;
	private String  weoStatus;
	private String  weoDesc;
	private Integer weoRegistor;
	private String  weoRegistdate;
	
	private String  weoCustenterName_Q ;
	private String  weoProName_Q;
	private String  weoMobile_Q;
	private String  weoName_Q;
	private Integer weoGqId_Q;
	
	private String currentPage;
	private String pageSize;
	private String[] weoIds;
	
	private Integer weoWetId_Q;
	private Integer weoEnterId_Q;

	// Constructors

	/** default constructor */
	public LzWeiEnterOrder() {
	}

	/** minimal constructor */
	public LzWeiEnterOrder(Integer weoId) {
		this.weoId = weoId;
	}

	/** full constructor */
	public LzWeiEnterOrder(Integer weoId, Integer weoGqId, Integer weoWetId,
			String weoStatus, String weoDesc, Integer weoRegistor,
			String weoRegistdate) {
		this.weoId = weoId;
		this.weoGqId = weoGqId;
		this.weoWetId = weoWetId;
		this.weoStatus = weoStatus;
		this.weoDesc = weoDesc;
		this.weoRegistor = weoRegistor;
		this.weoRegistdate = weoRegistdate;
	}
	
	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WEO_ID", unique = true, nullable = false)
	public Integer getWeoId() {
		return this.weoId;
	}

	public void setWeoId(Integer weoId) {
		this.weoId = weoId;
	}

	@Column(name = "WEO_GQ_ID")
	public Integer getWeoGqId() {
		return this.weoGqId;
	}

	public void setWeoGqId(Integer weoGqId) {
		this.weoGqId = weoGqId;
	}

	@Column(name = "WEO_WET_ID")
	public Integer getWeoWetId() {
		return this.weoWetId;
	}

	public void setWeoWetId(Integer weoWetId) {
		this.weoWetId = weoWetId;
	}

	@Column(name = "WEO_STATUS", length = 20)
	public String getWeoStatus() {
		return this.weoStatus;
	}

	public void setWeoStatus(String weoStatus) {
		this.weoStatus = weoStatus;
	}

	@Column(name = "WEO_DESC", length = 200)
	public String getWeoDesc() {
		return this.weoDesc;
	}

	public void setWeoDesc(String weoDesc) {
		this.weoDesc = weoDesc;
	}
	
	
	

	@Column(name = "WEO_REGISTOR")
	public Integer getWeoRegistor() {
		return this.weoRegistor;
	}

	public void setWeoRegistor(Integer weoRegistor) {
		this.weoRegistor = weoRegistor;
	}

	@Column(name = "WEO_REGISTDATE", length = 23)
	public String getWeoRegistdate() {
		return this.weoRegistdate;
	}

	public void setWeoRegistdate(String weoRegistdate) {
		this.weoRegistdate = weoRegistdate;
	}
	
	@Transient
	public Integer getWeoWetId_Q() 
	{
		return weoWetId_Q;
	}

	public void setWeoWetId_Q(Integer weoWetId_Q) 
	{
		this.weoWetId_Q = weoWetId_Q;
	}
	
	@Transient
	public static long getSerialVersionUID() 
	{
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
	public String getWeoCustenterName_Q() {
		return weoCustenterName_Q;
	}

	public void setWeoCustenterName_Q(String weoCustenterName_Q) {
		this.weoCustenterName_Q = weoCustenterName_Q;
	}
	@Transient
	public String getWeoProName_Q() {
		return weoProName_Q;
	}

	public void setWeoProName_Q(String weoProName_Q) {
		this.weoProName_Q = weoProName_Q;
	}
	@Transient
	public String getWeoMobile_Q() {
		return weoMobile_Q;
	}

	public void setWeoMobile_Q(String weoMobile_Q) {
		this.weoMobile_Q = weoMobile_Q;
	}
	@Transient
	public String getWeoName_Q() {
		return weoName_Q;
	}

	public void setWeoName_Q(String weoName_Q) {
		this.weoName_Q = weoName_Q;
	}
	@Transient
	public String[] getWeoIds() {
		return weoIds;
	}

	public void setWeoIds(String[] weoIds) {
		this.weoIds = weoIds;
	}
	@Transient
	public Integer getWeoEnterId_Q() {
		return weoEnterId_Q;
	}

	public void setWeoEnterId_Q(Integer weoEnterId_Q) {
		this.weoEnterId_Q = weoEnterId_Q;
	}
	@Transient
	public Integer getWeoGqId_Q() {
		return weoGqId_Q;
	}

	public void setWeoGqId_Q(Integer weoGqId_Q) {
		this.weoGqId_Q = weoGqId_Q;
	}
	
	

}