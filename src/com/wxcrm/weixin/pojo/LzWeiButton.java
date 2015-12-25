package com.wxcrm.weixin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LzWeiButton entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_BUTTON")
public class LzWeiButton implements java.io.Serializable {

	// Fields

	private Integer wbtId;
	private String  wbtAppId;
	private String  wbtJson;
	private String  wbtUrl;
	private String  wbtKey;
	private Integer wbtLevel;
	private Integer wbtParent;
	private String  wbtStatus;

	// Constructors

	/** default constructor */
	public LzWeiButton() {
	}

	/** minimal constructor */
	public LzWeiButton(Integer wbtId) {
		this.wbtId = wbtId;
	}

	/** full constructor */
	public LzWeiButton(Integer wbtId, String wbtAppId, String wbtJson,
			String wbtUrl, String wbtKey, Integer wbtLevel, Integer wbtParent,
			String wbtStatus) {
		this.wbtId = wbtId;
		this.wbtAppId = wbtAppId;
		this.wbtJson = wbtJson;
		this.wbtUrl = wbtUrl;
		this.wbtKey = wbtKey;
		this.wbtLevel = wbtLevel;
		this.wbtParent = wbtParent;
		this.wbtStatus = wbtStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WBT_ID", unique = true, nullable = false)
	public Integer getWbtId() {
		return this.wbtId;
	}

	public void setWbtId(Integer wbtId) {
		this.wbtId = wbtId;
	}

	@Column(name = "WBT_APP_ID", length = 200)
	public String getWbtAppId() {
		return this.wbtAppId;
	}

	public void setWbtAppId(String wbtAppId) {
		this.wbtAppId = wbtAppId;
	}

	@Column(name = "WBT_JSON")
	public String getWbtJson() {
		return this.wbtJson;
	}

	public void setWbtJson(String wbtJson) {
		this.wbtJson = wbtJson;
	}

	@Column(name = "WBT_URL", length = 200)
	public String getWbtUrl() {
		return this.wbtUrl;
	}

	public void setWbtUrl(String wbtUrl) {
		this.wbtUrl = wbtUrl;
	}

	@Column(name = "WBT_KEY", length = 200)
	public String getWbtKey() {
		return this.wbtKey;
	}

	public void setWbtKey(String wbtKey) {
		this.wbtKey = wbtKey;
	}

	@Column(name = "WBT_LEVEL")
	public Integer getWbtLevel() {
		return this.wbtLevel;
	}

	public void setWbtLevel(Integer wbtLevel) {
		this.wbtLevel = wbtLevel;
	}

	@Column(name = "WBT_PARENT")
	public Integer getWbtParent() {
		return this.wbtParent;
	}

	public void setWbtParent(Integer wbtParent) {
		this.wbtParent = wbtParent;
	}

	@Column(name = "WBT_STATUS", length = 20)
	public String getWbtStatus() {
		return this.wbtStatus;
	}

	public void setWbtStatus(String wbtStatus) {
		this.wbtStatus = wbtStatus;
	}

}