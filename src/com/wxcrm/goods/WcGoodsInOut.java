package com.wxcrm.goods;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WcGoodsInOut entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wc_goods_in_out", catalog = "wxcrm")
public class WcGoodsInOut implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wioId;
	private Integer wioWgoId;
	private Integer wioWgiId;
	private Double wioNumber;
	private Integer wioRegistor;
	private String wioRegistdate;

	// Constructors

	/** default constructor */
	public WcGoodsInOut() {
	}

	/** minimal constructor */
	public WcGoodsInOut(Integer wioWgoId, Integer wioWgiId, Double wioNumber) {
		this.wioWgoId = wioWgoId;
		this.wioWgiId = wioWgiId;
		this.wioNumber = wioNumber;
	}

	/** full constructor */
	public WcGoodsInOut(Integer wioWgoId, Integer wioWgiId, Double wioNumber,
			Integer wioRegistor, String wioRegistdate) {
		this.wioWgoId = wioWgoId;
		this.wioWgiId = wioWgiId;
		this.wioNumber = wioNumber;
		this.wioRegistor = wioRegistor;
		this.wioRegistdate = wioRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WIO_ID", unique = true, nullable = false)
	public Integer getWioId() {
		return this.wioId;
	}

	public void setWioId(Integer wioId) {
		this.wioId = wioId;
	}

	@Column(name = "WIO_WGO_ID", nullable = false)
	public Integer getWioWgoId() {
		return this.wioWgoId;
	}

	public void setWioWgoId(Integer wioWgoId) {
		this.wioWgoId = wioWgoId;
	}

	@Column(name = "WIO_WGI_ID", nullable = false)
	public Integer getWioWgiId() {
		return this.wioWgiId;
	}

	public void setWioWgiId(Integer wioWgiId) {
		this.wioWgiId = wioWgiId;
	}

	@Column(name = "WIO_NUMBER", nullable = false, precision = 11)
	public Double getWioNumber() {
		return this.wioNumber;
	}

	public void setWioNumber(Double wioNumber) {
		this.wioNumber = wioNumber;
	}

	@Column(name = "WIO_REGISTOR")
	public Integer getWioRegistor() {
		return this.wioRegistor;
	}

	public void setWioRegistor(Integer wioRegistor) {
		this.wioRegistor = wioRegistor;
	}

	@Column(name = "WIO_REGISTDATE", length = 19)
	public String getWioRegistdate() {
		return this.wioRegistdate;
	}

	public void setWioRegistdate(String wioRegistdate) {
		this.wioRegistdate = wioRegistdate;
	}

}