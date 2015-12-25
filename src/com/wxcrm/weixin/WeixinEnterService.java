package com.wxcrm.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.member.LzWeiMember;
import com.wxcrm.util.Page;

@Service
@Transactional
public class WeixinEnterService  implements IWeixinEnterService {
	
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

//	@Autowired 
//	private IWebUserService webuserservice;
	
	@Autowired
	private IWeixinService weixinservice;
	
	

	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryGqEnterList(Integer enterId) {
		// TODO 根据企业编号获取该企业的供求列表
		List<Object> paraList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder
		(
			  " select a.gq_id,a.gq_type,a.gq_title,b.pro_cn_name,		" 
			+ " b.pro_unit,a.gq_num,a.gq_unit,a.gq_cycle,a.gq_price, 	" 
			+ " convert(varchar(10), a.gq_yxsj, 20) gq_yxsj, a.gq_desc, "
			+ " convert(varchar(19), a.create_time,20) create_time,		" 
			+ " convert(varchar(10),a.create_time,20) create_time2,		"
			+ " case when a.gq_price < 100000 then cast(a.gq_price as varchar)                                       "
			+ "      when a.gq_price >= 100000 then cast(cast(a.gq_price/10000 as numeric(11,2)) as varchar) + '万'  "
			+ "      else '面议' end gq_price2                                                                        "

		);
		StringBuilder sqlCnt = new StringBuilder(" select count(*) ");
		StringBuilder sqlConf = new StringBuilder
		(
			" from lz_ente_gq a                                                                                   	"
			+ " left join lz_product b on a.pro_id = b.pro_id                                                       "
			+ " where a.gq_xia ='1' and a.gq_sh = '1' and a.cue_id = ?                                                                "
		);
		paraList.add(enterId);
		sql.append(sqlConf.toString());
		sqlCnt.append(sqlConf.toString());
		sql.append(" order by a.create_time desc ");
		return jdbcDao.queryForList(sql.toString(),paraList.toArray());
	}
	
	public LzWeiEnterCust getWeiEnterCustByOpenId(String openId) {
		// TODO 根据Openid获取客户的客户信息
		String sql = 
			" select " +
			" a.WET_ID," +
			" a.WET_OPEN_ID," +
			" a.WET_CUSTENTER_NAME," +
			" a.WET_MOBILE," +
			" a.WET_NAME," +
			" a.WET_TYPE," +
			" a.WET_CUE_ID," +
			" a.WET_CUU_ID," +
			" a.WET_STATUS," +
			" a.WET_DESC " +
			" from LZ_WEI_ENTER_CUST a where a.WET_OPEN_ID = ?";
		List<Map<String,Object>> lst = jdbcDao.queryForList(sql, new Object[]{openId});
		if(lst.size()>0)
		{
			LzWeiEnterCust enterCust = new LzWeiEnterCust();
			Map<String,Object> map = lst.get(0);
			enterCust.setWetId((Integer)map.get("WET_ID"));
			enterCust.setWetOpenId((String)map.get("WET_OPEN_ID"));
			enterCust.setWetCustenterName((String)map.get("WET_CUSTENTER_NAME"));
			enterCust.setWetMobile((String)map.get("WET_MOBILE"));
			enterCust.setWetName((String)map.get("WET_NAME"));
			enterCust.setWetType((String)map.get("WET_TYPE"));
			enterCust.setWetCueId((Integer)map.get("WET_CUE_ID"));
			enterCust.setWetCuuId((Integer)map.get("WET_CUU_ID"));
			enterCust.setWetStatus((String)map.get("WET_STATUS"));
			enterCust.setWetDesc((String)map.get("WET_DESC"));
			return enterCust;
		}
		else
		{
			return null;
		}
		
	}

	public void addWeiEnterCust(LzWeiEnterCust enterCust) {
		// TODO 根据用户填写的企业信息 姓名 手机 openid 添加客户的客户 再添加订单 
		hibernateDao.add(enterCust);
	}

	public void updWeiEnterCust(LzWeiEnterCust enterCust)
	{
		hibernateDao.update(enterCust);
	}

	public void addWeiEnterOrder(LzWeiEnterOrder order) {
		// TODO Auto-generated method stub
		hibernateDao.add(order);
	}

	

	public void delWeiEnterCust(String[] wetIds) {
		// TODO 删除微信企业客户
		String wetIdStr = StringUtils.arrayToCommaDelimitedString(wetIds);
		//删除企业客户表数据
		String sql = " delete from LZ_WEI_ENTER_CUST where WET_ID in ( "+wetIdStr+")";
		//删除订单客户表数据
		String sql1 = " delete from LZ_WEI_ENTER_ORDER where WEO_WET_ID in ("+wetIdStr+")";
		jdbcDao.delete(sql);
		jdbcDao.delete(sql1);
		
	}

	public Page queryWeiEnterOrder(LzWeiEnterOrder order) {
		// TODO Auto-generated method stub
		List<Object> paraList 	   = new ArrayList<Object>();
		Integer weoEnterId_Q 	   = order.getWeoEnterId_Q();   
		Integer wetId_Q 		   = order.getWeoWetId_Q();
		String  weoCustenterName_Q = order.getWeoCustenterName_Q();
		String  weoProName_Q       = order.getWeoProName_Q();
		String  weoMobile_Q        = order.getWeoMobile_Q();
		String  weoName_Q          = order.getWeoName_Q();
		
		StringBuilder sql = new StringBuilder(
			" select " +
			" a.WEO_ID," +
			" a.WEO_GQ_ID," +
			" a.WEO_WET_ID," +
			" a.WEO_STATUS," +
			" a.WEO_DESC," +
			" a.WEO_REGISTOR," +
			" a.WEO_REGISTDATE," +
			" d.pro_cn_name , " +
			" b.gq_cycle, " +
			" b.gq_price, " +
			" b.gq_num, " +
			" c.WET_CUSTENTER_NAME, " +
			" c.WET_MOBILE," +
			" c.WET_NAME  " +
			" from LZ_WEI_ENTER_ORDER a " +
			" left join lz_ente_gq b on b.gq_id = a.WEO_GQ_ID " +
			" left join lz_product d on d.pro_id = b.pro_id  " +
			" left join LZ_WEI_ENTER_CUST c on c.WET_ID = WEO_WET_ID " +
			" where 1=1 ");
		StringBuilder sqlCnt = new StringBuilder(
			" select " +
			" count(*) " +
			" from LZ_WEI_ENTER_ORDER a " +
			" left join lz_ente_gq b on b.gq_id = a.WEO_GQ_ID " +
			" left join lz_product d on d.pro_id = b.pro_id  " +
			" left join LZ_WEI_ENTER_CUST c on c.WET_ID = WEO_WET_ID " +
			" where 1=1  "
		);
		if(weoEnterId_Q!=null&&weoEnterId_Q>0)
		{
			sql.append(" and b.cue_id = ? ");
			sqlCnt.append(" and b.cue_id = ? ");
			paraList.add(weoEnterId_Q);
			
		}
		
		if(wetId_Q!=null&&wetId_Q>0)
		{
			sql.append(" and a.WEO_WET_ID = ? ");
			sqlCnt.append(" and a.WEO_WET_ID = ? ");
			paraList.add(wetId_Q);
		}
		
		if(weoCustenterName_Q!=null&&weoCustenterName_Q.length()>0)
		{
			sql.append(" and c.WET_CUSTENTER_NAME like ? ");
			sqlCnt.append(" and c.WET_CUSTENTER_NAME like ? ");
			paraList.add("%"+weoCustenterName_Q+"%");
			
		}
		if(weoProName_Q!=null&&weoProName_Q.length()>0)
		{
			sql.append(" and d.pro_cn_name like ? ");
			sqlCnt.append(" and d.pro_cn_name like ? ");
			paraList.add("%"+weoProName_Q+"%");
		}
		if(weoMobile_Q!=null&&weoMobile_Q.length()>0)
		{
			sql.append(" and c.WET_MOBILE = ? ");
			sqlCnt.append(" and c.WET_MOBILE = ? ");
			paraList.add(weoMobile_Q);
		}
		if(weoName_Q!=null&&weoName_Q.length()>0)
		{
			sql.append(" and c.WET_NAME like ? ");
			sqlCnt.append(" and c.WET_NAME like ? ");
			paraList.add("%"+weoName_Q+"%");
		}
		sql.append(" order by a.WEO_REGISTDATE desc ");
		Page page = new Page(sql.toString(),sqlCnt.toString(),order.getCurrentPage(),order.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public LzWeiEnterCust getWeiEnterCustById(Integer wetId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(LzWeiEnterCust.class, wetId);
	}

	public void delWeiEnterOrder(String[] weoIds) {
		// TODO Auto-generated method stub
		String strWeoid = StringUtils.arrayToCommaDelimitedString(weoIds);
		String sql = "delete from LZ_WEI_ENTER_ORDER where WEO_ID in ("+strWeoid+")";
		jdbcDao.delete(sql);
	}

//	public LzCustomerUser checkWebUserAccount(String username, String password) {
//		// TODO Auto-generated method stub
//		String hql  = " from LzCustomerUser user where user.cuuUsername = ? and user.cuuPassword = ? ";
//		List<LzCustomerUser> list = hibernateDao.query(hql, new Object[]{username,DigestUtils.md5DigestAsHex(password.getBytes())});
//		if(list.size()>0)
//		{
//			return list.get(0);
//		}
//		else
//		{
//			return null;
//		}
//		
//	}
//
//	public String bindAdminByCust(String appId,String openid, LzCustomerUser custuser,LzAdmin admin) {
//		// TODO 根据客户账号绑定微信的账户管理员
//		//1 检查app对应企业 是否和用户所属企业对应
//
//		LzEnterMainuserConfig emc = webuserservice.getEnterMainUserConfById(custuser.getCuuEmcId());
//		Integer enterId = emc.getEmcEnterId();
//		LzWeiEnter enter = weixinservice.getWeiEnterByAppId(appId);
//		if(enterId.compareTo(enter.getWecEnterId())==0)
//		{
//			LzCustomerEnterprise enterCust = webuserservice.getCustEnteById(enterId);
//			//根据他的openId 判断该用户是否已经存在 如果存在就把他转成 内部客户 订单状态全部置为 3000 内部订单
//			LzWeiEnterCust weicust = getWeiEnterCustByOpenId(openid);
//			LzCustomerContacts cuc = webuserservice.getCustContById(custuser.getCuuContactsId());
//			if(weicust!=null)
//			{
//				weicust.setWetAppId(appId);
//				weicust.setWetOpenId(openid);
//				weicust.setWetCueId(enterId);
//				weicust.setWetCustenterName(enterCust.getCueName());
//				weicust.setWetMobile(cuc.getCucMobile());
//				weicust.setWetName(cuc.getCucName());
//				weicust.setWetType("2");
//				weicust.setWetCuuId(custuser.getCuuId());
//				weicust.setWetStatus("1000");
//				//weicust.setWetRegistor(admin==null?1:admin.getAdminId());
//				//weicust.setWetRegistdate(DateUtil.parseString(new Date(),"yyyy-MM-dd"));
//				hibernateDao.update(weicust);
//				//原账户的订单该状态
//				this.updateOrderStatusByCust(weicust.getWetId(),"3000");
//				return "2";//老客户改出来的
//			}
//			else
//			{
//				weicust = new LzWeiEnterCust();
//				weicust.setWetAppId(appId);
//				weicust.setWetOpenId(openid);
//				weicust.setWetCueId(enterId);
//				weicust.setWetCustenterName(enterCust.getCueName());
//				weicust.setWetMobile(cuc.getCucMobile());
//				weicust.setWetName(cuc.getCucName());
//				weicust.setWetType("2");
//				weicust.setWetCuuId(custuser.getCuuId());
//				weicust.setWetStatus("1000");
//				weicust.setWetRegistor(admin==null?1:admin.getAdminId());
//				weicust.setWetRegistdate(DateUtil.parseString(new Date(),"yyyy-MM-dd"));
//				hibernateDao.add(weicust);
//				return "3";//新的管理员添加
//			}
//		}
//		else
//		{
//			return "1";//当前账户的所属企业和微信号绑定企业不一致
//		}
//		
//	}
	
	public void updateOrderStatusByCust(Integer wetId,String status)
	{
		String sql = "update LZ_WEI_ENTER_ORDER set WEO_STATUS = ? where WEO_WET_ID = ?";
		jdbcDao.update(sql, new Object[]{status,wetId});
	}

//	public List<Map<String,Object>> queryGqByWeiAdmin(Integer cuuId) {
//		// TODO 查询本企业的所有供求
//		LzCustomerUser 	cuu = webuserservice.getCustUserById(cuuId);
//		LzEnterMainuserConfig emc = webuserservice.getEnterMainUserConfById(cuu.getCuuEmcId());
//		StringBuilder sql = new StringBuilder
//		( 
//			" select distinct														" +
//			" d.gq_id,   d.gq_type, d.gq_title, d.gq_unit,d.gq_num,d.create_time,	" +
//			" d.gq_price,d.gq_cycle,d.gq_price, d.gq_xia,d.gq_from,d.gq_sh,			" +
//			" convert(varchar(16),d.create_time,20) as createtime,e.pro_cn_name 	" +
//			" from lz_ente_gq d 													" + 
//			" join LZ_ENTER_MAINUSER_CONFIG b 	on d.cue_id = b.EMC_ENTER_ID  		" +
//			" join LZ_CUSTOMER_USER c 			on b.EMC_ID = c.CUU_EMC_ID  		" +
//			" left join lz_product e 			on d.pro_id = e.pro_id   			" +
//			" where c.CUU_USERNAME = ? and d.cuc_id = ? 							"  
//			//" where d.cue_id = ? 													"  //这是查全企业的
//		);
//		sql.append(" order by d.create_time desc,d.gq_sh desc, d.gq_xia desc		");
//		LzCustomerContacts 	cuc = webuserservice.getCustContById(cuu.getCuuContactsId());
//		return jdbcDao.queryForList(sql.toString(),new Object[]{cuu.getCuuUsername(),cuc.getCucId()});
//	}

	public void delGqByWeiAdmin(Integer gqId) {
		// TODO Auto-generated method stub
		String sql = "delete from lz_ente_gq where gq_id = ?";
		jdbcDao.delete(sql, new Object[]{gqId});
	}

	public List<Map<String, Object>> queryWeiEnterOrderList(
			LzWeiEnterOrder order) {
		// TODO Auto-generated method stub
		List<Object> paraList 	   = new ArrayList<Object>();
		Integer weoEnterId_Q 	   = order.getWeoEnterId_Q();   
		Integer wetId_Q 		   = order.getWeoWetId_Q();
		String  weoCustenterName_Q = order.getWeoCustenterName_Q();
		String  weoProName_Q       = order.getWeoProName_Q();
		String  weoMobile_Q        = order.getWeoMobile_Q();
		String  weoName_Q          = order.getWeoName_Q();
		Integer weoGqId_Q		   = order.getWeoGqId_Q();
		StringBuilder sql = new StringBuilder(
			" select " +
			" a.WEO_ID," +
			" a.WEO_GQ_ID," +
			" a.WEO_WET_ID," +
			" a.WEO_STATUS," +
			" a.WEO_DESC," +
			" a.WEO_REGISTOR," +
			" a.WEO_REGISTDATE," +
			" d.pro_cn_name , " +
			" b.gq_title, " +
			" b.gq_price, " +
			" b.gq_num, " +
			" c.WET_CUSTENTER_NAME, " +
			" c.WET_MOBILE," +
			" c.WET_NAME  " +
			" from LZ_WEI_ENTER_ORDER a " +
			" left join lz_ente_gq b on b.gq_id = a.WEO_GQ_ID " +
			" left join lz_product d on d.pro_id = b.pro_id  " +
			" left join LZ_WEI_ENTER_CUST c on c.WET_ID = WEO_WET_ID " +
			" where 1=1 ");
		
		if(weoGqId_Q!=null&&weoGqId_Q>0)
		{
			sql.append(" and a.WEO_GQ_ID = ? ");
			paraList.add(weoGqId_Q);
		}
		if(weoEnterId_Q!=null&&weoEnterId_Q>0)
		{
			sql.append(" and b.cue_id = ? ");
			paraList.add(weoEnterId_Q);
		}
		
		if(wetId_Q!=null&&wetId_Q>0)
		{
			sql.append(" and a.WEO_WET_ID = ? ");
			paraList.add(wetId_Q);
		}
		
		if(weoCustenterName_Q!=null&&weoCustenterName_Q.length()>0)
		{
			sql.append(" and c.WET_CUSTENTER_NAME like ? ");
			paraList.add("%"+weoCustenterName_Q+"%");
		}
		if(weoProName_Q!=null&&weoProName_Q.length()>0)
		{
			sql.append(" and d.pro_cn_name like ? ");
			paraList.add("%"+weoProName_Q+"%");
		}
		if(weoMobile_Q!=null&&weoMobile_Q.length()>0)
		{
			sql.append(" and c.WET_MOBILE = ? ");
			paraList.add(weoMobile_Q);
		}
		if(weoName_Q!=null&&weoName_Q.length()>0)
		{
			sql.append(" and c.WET_NAME like ? ");
			paraList.add("%"+weoName_Q+"%");
		}
		sql.append(" order by a.WEO_REGISTDATE desc ");
		return jdbcDao.queryForList(sql.toString(),paraList.toArray());
	}
	
	
	
	
}
