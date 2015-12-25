package com.wxcrm.weixin;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * LzWeiEnter entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LZ_WEI_ENTER")
public class LzWeiEnter implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer wecId;
	private String 	wecAppName;
	private String 	wecAppId;
	private String 	wecAppSecret;
	private String 	wecRederectUrl;
	private String 	wecToken;
	private String 	wecEncodingAesKey;
	private String 	wecAesType;
	private String 	wecAppType;
	private String 	wecCusType;
	private String 	wecAccountType;
	private Integer wecEnterId;
	private String 	wecStatus;
	private String 	wecDesc;
	private Integer wecRegistor;
	private String 	wecRegistdate;
	private Integer wecDefaultMsg;
	private Integer wecSubscribeMsg;
	private String 	wecDefaultMsgDesc;
	private String 	wecSubscribeMsgDesc;
	
	
	private String 	currentPage;
	private String 	pageSize;
	private String[] wecIds;
	
	private List<Map<String,Object>> listKeyWords;
	
	private String wecAppName_Q;	//应用名称
	private String wecAppId_Q;		//appId
	private String wecAesType_Q;	//消息加密方式
	private String wecAppType_Q;	//应用类型
	private String wecCusType_Q; 	//客户类型 
	private String wecAccountType_Q;//账户类型
	private String wecEnterName_Q;	//企业名称
	
	private String wecCustOpenId_Q;	//openId
	private String wetOpenId_Q;
	
	

	// Constructors

	/** default constructor */
	public LzWeiEnter() {
	}

	/** minimal constructor */
	public LzWeiEnter(Integer wecId, String wecAppId, String wecAppSecret) {
		this.wecId = wecId;
		this.wecAppId = wecAppId;
		this.wecAppSecret = wecAppSecret;
	}

	/** full constructor */
	public LzWeiEnter(
			Integer wecId, 
			String wecAppName, 
			String wecAppId,
			String wecAppSecret, 
			String wecRederectUrl, 
			String wecToken,
			String wecEncodingAesKey, 
			String wecAesType, String wecAppType,
			String wecCusType, String wecAccountType, Integer wecEnterId,
			String wecStatus, String wecDesc, Integer wecRegistor,
			String wecRegistdate) {
		this.wecId = wecId;
		this.wecAppName = wecAppName;
		this.wecAppId = wecAppId;
		this.wecAppSecret = wecAppSecret;
		this.wecRederectUrl = wecRederectUrl;
		this.wecToken = wecToken;
		this.wecEncodingAesKey = wecEncodingAesKey;
		this.wecAesType = wecAesType;
		this.wecAppType = wecAppType;
		this.wecCusType = wecCusType;
		this.wecAccountType = wecAccountType;
		this.wecEnterId = wecEnterId;
		this.wecStatus = wecStatus;
		this.wecDesc = wecDesc;
		this.wecRegistor = wecRegistor;
		this.wecRegistdate = wecRegistdate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WEC_ID", unique = true, nullable = false)
	public Integer getWecId() {
		return this.wecId;
	}

	public void setWecId(Integer wecId) {
		this.wecId = wecId;
	}

	@Column(name = "WEC_APP_NAME", length = 100)
	public String getWecAppName() {
		return this.wecAppName;
	}

	public void setWecAppName(String wecAppName) {
		this.wecAppName = wecAppName;
	}

	@Column(name = "WEC_APP_ID", nullable = false, length = 80)
	public String getWecAppId() {
		return this.wecAppId;
	}

	public void setWecAppId(String wecAppId) {
		this.wecAppId = wecAppId;
	}

	@Column(name = "WEC_APP_SECRET", nullable = false, length = 100)
	public String getWecAppSecret() {
		return this.wecAppSecret;
	}

	public void setWecAppSecret(String wecAppSecret) {
		this.wecAppSecret = wecAppSecret;
	}

	@Column(name = "WEC_REDERECT_URL", length = 100)
	public String getWecRederectUrl() {
		return this.wecRederectUrl;
	}

	public void setWecRederectUrl(String wecRederectUrl) {
		this.wecRederectUrl = wecRederectUrl;
	}

	@Column(name = "WEC_TOKEN", length = 200)
	public String getWecToken() {
		return this.wecToken;
	}

	public void setWecToken(String wecToken) {
		this.wecToken = wecToken;
	}

	@Column(name = "WEC_ENCODING_AES_KEY", length = 200)
	public String getWecEncodingAesKey() {
		return this.wecEncodingAesKey;
	}

	public void setWecEncodingAesKey(String wecEncodingAesKey) {
		this.wecEncodingAesKey = wecEncodingAesKey;
	}

	@Column(name = "WEC_AES_TYPE", length = 1)
	public String getWecAesType() {
		return this.wecAesType;
	}

	public void setWecAesType(String wecAesType) {
		this.wecAesType = wecAesType;
	}

	@Column(name = "WEC_APP_TYPE", length = 1)
	public String getWecAppType() {
		return this.wecAppType;
	}

	public void setWecAppType(String wecAppType) {
		this.wecAppType = wecAppType;
	}

	@Column(name = "WEC_CUS_TYPE", length = 1)
	public String getWecCusType() {
		return this.wecCusType;
	}

	public void setWecCusType(String wecCusType) {
		this.wecCusType = wecCusType;
	}

	@Column(name = "WEC_ACCOUNT_TYPE", length = 1)
	public String getWecAccountType() {
		return this.wecAccountType;
	}

	public void setWecAccountType(String wecAccountType) {
		this.wecAccountType = wecAccountType;
	}

	@Column(name = "WEC_ENTER_ID")
	public Integer getWecEnterId() {
		return this.wecEnterId;
	}

	public void setWecEnterId(Integer wecEnterId) {
		this.wecEnterId = wecEnterId;
	}

	@Column(name = "WEC_STATUS", length = 20)
	public String getWecStatus() {
		return this.wecStatus;
	}

	public void setWecStatus(String wecStatus) {
		this.wecStatus = wecStatus;
	}

	@Column(name = "WEC_DESC", length = 200)
	public String getWecDesc() {
		return this.wecDesc;
	}

	public void setWecDesc(String wecDesc) {
		this.wecDesc = wecDesc;
	}

	@Column(name = "WEC_REGISTOR")
	public Integer getWecRegistor() {
		return this.wecRegistor;
	}

	public void setWecRegistor(Integer wecRegistor) {
		this.wecRegistor = wecRegistor;
	}

	@Column(name = "WEC_REGISTDATE", length = 23)
	public String getWecRegistdate() {
		return this.wecRegistdate;
	}

	public void setWecRegistdate(String wecRegistdate) {
		this.wecRegistdate = wecRegistdate;
	}
	@Column(name = "WEC_DEFAULT_MSG")
	public Integer getWecDefaultMsg() {
		return wecDefaultMsg;
	}

	public void setWecDefaultMsg(Integer wecDefaultMsg) {
		this.wecDefaultMsg = wecDefaultMsg;
	}
	@Column(name = "WEC_SUBSCRIBE_MSG")
	public Integer getWecSubscribeMsg() {
		return wecSubscribeMsg;
	}

	public void setWecSubscribeMsg(Integer wecSubscribeMsg) {
		this.wecSubscribeMsg = wecSubscribeMsg;
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
	public String[] getWecIds() {
		return wecIds;
	}

	public void setWecIds(String[] wecIds) {
		this.wecIds = wecIds;
	}
	@Transient
	public String getWecAppName_Q() {
		return wecAppName_Q;
	}

	public void setWecAppName_Q(String wecAppName_Q) {
		this.wecAppName_Q = wecAppName_Q;
	}
	@Transient
	public String getWecAppId_Q() {
		return wecAppId_Q;
	}

	public void setWecAppId_Q(String wecAppId_Q) {
		this.wecAppId_Q = wecAppId_Q;
	}
	@Transient
	public String getWecAesType_Q() {
		return wecAesType_Q;
	}

	public void setWecAesType_Q(String wecAesType_Q) {
		this.wecAesType_Q = wecAesType_Q;
	}
	@Transient
	public String getWecAppType_Q() {
		return wecAppType_Q;
	}

	public void setWecAppType_Q(String wecAppType_Q) {
		this.wecAppType_Q = wecAppType_Q;
	}
	@Transient
	public String getWecCusType_Q() {
		return wecCusType_Q;
	}

	public void setWecCusType_Q(String wecCusType_Q) {
		this.wecCusType_Q = wecCusType_Q;
	}
	@Transient
	public String getWecAccountType_Q() {
		return wecAccountType_Q;
	}

	public void setWecAccountType_Q(String wecAccountType_Q) {
		this.wecAccountType_Q = wecAccountType_Q;
	}
	@Transient
	public String getWecEnterName_Q() {
		return wecEnterName_Q;
	}

	public void setWecEnterName_Q(String wecEnterName_Q) {
		this.wecEnterName_Q = wecEnterName_Q;
	}
	
	@Transient
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	@Transient
	public String getWecCustOpenId_Q() {
		return wecCustOpenId_Q;
	}

	public void setWecCustOpenId_Q(String wecCustOpenId_Q) {
		this.wecCustOpenId_Q = wecCustOpenId_Q;
	}
	@Transient
	public String getWecDefaultMsgDesc() {
		return wecDefaultMsgDesc;
	}

	public void setWecDefaultMsgDesc(String wecDefaultMsgDesc) {
		this.wecDefaultMsgDesc = wecDefaultMsgDesc;
	}
	
	@Transient
	public String getWecSubscribeMsgDesc() {
		return wecSubscribeMsgDesc;
	}

	public void setWecSubscribeMsgDesc(String wecSubscribeMsgDesc) {
		this.wecSubscribeMsgDesc = wecSubscribeMsgDesc;
	}
	
	@Transient
	public List<Map<String, Object>> getListKeyWords() {
		return listKeyWords;
	}

	public void setListKeyWords(List<Map<String, Object>> listKeyWords) {
		this.listKeyWords = listKeyWords;
	}
	
	@Transient
	public String getWetOpenId_Q() {
		return wetOpenId_Q;
	}

	public void setWetOpenId_Q(String wetOpenId_Q) {
		this.wetOpenId_Q = wetOpenId_Q;
	}

	

	
	
}