package com.wxcrm.weixin;

import javax.persistence.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LzWeiEnterCust entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_ENTER_CUST")
public class LzWeiEnterCust implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wetId;
	private String  wetOpenId;
	private String  wetCustenterName;
	private String  wetMobile;
	private String  wetName;
	private Integer wetCueId;
	private Integer wetCuuId;
	private String  wetStatus;
	private String  wetDesc;
	private Integer wetRegistor;
	private String  wetRegistdate;
	private String  wetType;
	
	private Integer wetGqId;
	private String  wetAppId;
	private Integer wetCueId_Q;
	private String  wetCustenterName_Q;
	private String  wetOpenId_Q;
	private String  wetMobile_Q;
	private String  wetName_Q;
	private String  wetType_Q;
	private String[] wetIds;
	
	private String currentPage;
	private String pageSize;

	private String cuuUsername;
	private String cuuPassword;
	
	
	
	// Constructors

	/** default constructor */
	public LzWeiEnterCust() {
	}

	/** minimal constructor */
	public LzWeiEnterCust(Integer wetId) {
		this.wetId = wetId;
	}

	/** full constructor */
	public LzWeiEnterCust(Integer wetId, String wetOpenId,
			String wetCustenterName, String wetMobile, String wetName,
			Integer wetCueId, Integer wetCuuId, String wetStatus,
			String wetDesc, Integer wetRegistor, String wetRegistdate) {
		this.wetId = wetId;
		this.wetOpenId = wetOpenId;
		this.wetCustenterName = wetCustenterName;
		this.wetMobile = wetMobile;
		this.wetName = wetName;
		this.wetCueId = wetCueId;
		this.wetCuuId = wetCuuId;
		this.wetStatus = wetStatus;
		this.wetDesc = wetDesc;
		this.wetRegistor = wetRegistor;
		this.wetRegistdate = wetRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WET_ID", unique = true, nullable = false)
	public Integer getWetId() {
		return this.wetId;
	}

	public void setWetId(Integer wetId) {
		this.wetId = wetId;
	}

	@Column(name = "WET_OPEN_ID", length = 200)
	public String getWetOpenId() {
		return this.wetOpenId;
	}

	public void setWetOpenId(String wetOpenId) {
		this.wetOpenId = wetOpenId;
	}

	@Column(name = "WET_CUSTENTER_NAME", length = 200)
	public String getWetCustenterName() {
		return this.wetCustenterName;
	}

	public void setWetCustenterName(String wetCustenterName) {
		this.wetCustenterName = wetCustenterName;
	}

	@Column(name = "WET_MOBILE", length = 20)
	public String getWetMobile() {
		return this.wetMobile;
	}

	public void setWetMobile(String wetMobile) {
		this.wetMobile = wetMobile;
	}

	@Column(name = "WET_NAME", length = 40)
	public String getWetName() {
		return this.wetName;
	}

	public void setWetName(String wetName) {
		this.wetName = wetName;
	}

	@Column(name = "WET_CUE_ID")
	public Integer getWetCueId() {
		return this.wetCueId;
	}

	public void setWetCueId(Integer wetCueId) {
		this.wetCueId = wetCueId;
	}

	@Column(name = "WET_CUU_ID")
	public Integer getWetCuuId() {
		return this.wetCuuId;
	}

	public void setWetCuuId(Integer wetCuuId) {
		this.wetCuuId = wetCuuId;
	}

	@Column(name = "WET_STATUS", length = 20)
	public String getWetStatus() {
		return this.wetStatus;
	}

	public void setWetStatus(String wetStatus) {
		this.wetStatus = wetStatus;
	}

	@Column(name = "WET_DESC", length = 200)
	public String getWetDesc() {
		return this.wetDesc;
	}

	public void setWetDesc(String wetDesc) {
		this.wetDesc = wetDesc;
	}

	@Column(name = "WET_REGISTOR")
	public Integer getWetRegistor() {
		return this.wetRegistor;
	}

	public void setWetRegistor(Integer wetRegistor) {
		this.wetRegistor = wetRegistor;
	}

	@Column(name = "WET_REGISTDATE", length = 23)
	public String getWetRegistdate() {
		return this.wetRegistdate;
	}

	public void setWetRegistdate(String wetRegistdate) {
		this.wetRegistdate = wetRegistdate;
	}
	@Column(name = "WET_TYPE", length = 1)
	public String getWetType() {
		return wetType;
	}

	public void setWetType(String wetType) {
		this.wetType = wetType;
	}

	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@Transient
	public Integer getWetGqId() {
		return wetGqId;
	}

	public void setWetGqId(Integer wetGqId) {
		this.wetGqId = wetGqId;
	}
	
	@Transient
	public String getWetAppId() {
		return wetAppId;
	}

	public void setWetAppId(String wetAppId) {
		this.wetAppId = wetAppId;
	}
	
	@Transient
	public Integer getWetCueId_Q() {
		return wetCueId_Q;
	}

	public void setWetCueId_Q(Integer wetCueId_Q) {
		this.wetCueId_Q = wetCueId_Q;
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
	public String[] getWetIds() {
		return wetIds;
	}

	public void setWetIds(String[] wetIds) {
		this.wetIds = wetIds;
	}
	@Transient
	public String getWetCustenterName_Q() {
		return wetCustenterName_Q;
	}

	public void setWetCustenterName_Q(String wetCustenterName_Q) {
		this.wetCustenterName_Q = wetCustenterName_Q;
	}
	
	@Transient
	public String getWetOpenId_Q() 
	{
		return wetOpenId_Q;
	}

	public void setWetOpenId_Q(String wetOpenId_Q) 
	{
		this.wetOpenId_Q = wetOpenId_Q;
	}
	
	@Transient
	public String getWetMobile_Q() 
	{
		return wetMobile_Q;
	}

	public void setWetMobile_Q(String wetMobile_Q) 
	{
		this.wetMobile_Q = wetMobile_Q;
	}
	
	@Transient
	public String getWetName_Q() 
	{
		return wetName_Q;
	}

	public void setWetName_Q(String wetName_Q) 
	{
		this.wetName_Q = wetName_Q;
	}
	@Transient
	public String getCuuUsername() {
		return cuuUsername;
	}

	public void setCuuUsername(String cuuUsername) {
		this.cuuUsername = cuuUsername;
	}
	@Transient
	public String getCuuPassword() {
		return cuuPassword;
	}

	public void setCuuPassword(String cuuPassword) {
		this.cuuPassword = cuuPassword;
	}
	@Transient
	public String getWetType_Q() {
		return wetType_Q;
	}

	public void setWetType_Q(String wetType_Q) {
		this.wetType_Q = wetType_Q;
	}
	
	

}