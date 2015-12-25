package com.wxcrm.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.util.Page;
import com.wxcrm.util.StringUtil;
@Service
@Transactional
public class AdminService implements IAdminService {

	

	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	@Transactional(readOnly = true)
	public Page queryAdmin(WcAdmin admin) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
			"	select WAD_ID,"+
			"	WAD_USERNAME,"+
			"	WAD_PWD,"+
			"	WAD_NAME,"+
			"	WAD_SEX,"+
			"	WAD_REGISTOR,"+
			"	WAD_REGISTDATE" +
			"	from WC_ADMIN a "
		);
		StringBuilder sqlCnt = new StringBuilder(
			"	select count(*) "+
			"	from WC_ADMIN a "
		
		);
		
		List<Object> paraList =  new ArrayList<Object>();
		Page page = new Page(sql.toString(),sqlCnt.toString(),admin.getCurrentPage(),admin.getPageSize());
		jdbcDao.queryForPage(page);
		return page;
	}

	@Transactional(readOnly = true)
	public boolean chkUsernameUnique(Integer adminId, String username)
	{
		int cnt = jdbcDao.queryForInt("select count(*) from WC_ADMIN where WAD_ID != ? and WAD_USERNAME = ?", 
				new Object[]{adminId, username});
		if( cnt == 0 )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	@Transactional(readOnly = true)
	public boolean chkUsernameUnique(String username)
	{
		int cnt = jdbcDao.queryForInt("select count(*) from WC_ADMIN where WAD_USERNAME = ?", new Object[]{username});
		if( cnt == 0 )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public void addAdmin(WcAdmin admin) 
	{
		admin.setWadPwd(DigestUtils.md5DigestAsHex(admin.getWadPwd().getBytes()));
//		if( admin.getBirthday() != null && admin.getBirthday().length() == 0 )
//		{
//			admin.setBirthday(null);
//		}
//		if( admin.getInCompanyTime() != null && admin.getInCompanyTime().length() == 0 )
//		{
//			admin.setInCompanyTime(null);
//		}
//		if( admin.getOtherTime() != null && admin.getOtherTime().length() == 0 )
//		{
//			admin.setOtherTime(null);
//		}
//		if( admin.getDelFlag().equals("2") || admin.getDelFlag().equals("3") )
//		{
//			admin.setAreaCode(null);
//			admin.setPhone(null);
//			admin.setPhoneFen(null);
//		}
		hibernateDao.add(admin);
		addAdminOther(admin);
		
		// 更新缓存
		hibernateDao.flush();
//		if( admin.getDeptId() != null && admin.getDeptId() > 0 )
//		{
//			memcachedService.setDeptAll();
//		}
//		memcachedService.setAdminNameAll();
//		
//		String txlSync = dicconfigservice.getDivValueByKey("TXL_SYNC");
//		if( txlSync.equals("1") )
//		{
//			txlService.addUser(admin);
//		}
	}
	
	private void addAdminOther(WcAdmin admin)
	{
		String adminId = String.valueOf(admin.getWadId());
		String[] roleIds = admin.getRoleIds();
		if( roleIds != null && roleIds.length > 0 )
		{
			jdbcDao.batchUpdate("insert into WC_ADMIN_ROLE (WAR_ADMIN_ID, WAR_ROLE_ID) values (?, ?)", StringUtil.getObjAryList(adminId, roleIds)); 
		}
		if( admin.getMenuIds() != null && admin.getMenuIds().length() > 0 )
		{
			String[] menuIds = admin.getMenuIds().split(",");
			jdbcDao.batchUpdate("insert into WC_ADMIN_MENU (WAM_ADMIN_ID, WAM_MENU_ID) values (?, ?)", StringUtil.getObjAryList(adminId, menuIds)); 
		}
	}
	
	@Transactional(readOnly = true)
	public WcAdmin adminLogin(WcAdmin admin)
	{
		WcAdmin result = null;
		List<WcAdmin> list = hibernateDao.query(
			"from WcAdmin where wadUsername = ? and wadPwd = ?", 
			new Object[]{admin.getWadUsername(), admin.getWadPwdMd5()});
		if( list.size() > 0 )
		{
			result = list.get(0);
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryAdminMenus(Integer adminId, String menuLevel)
	{
		String sql =
			  " select distinct c.WME_ID, c.WME_NAME, c.WME_URL, "
			+ " c.WME_LEVEL, c.WME_ORDER, c.WME_PARENT_ID             "
			+ " from WC_ADMIN_ROLE a                                "
			+ " join WC_ROLE_MENU b on a.WAR_ROLE_ID = b.WRM_ROLE_ID        "
			+ " join WC_MENU c on b.WRM_MENU_ID = c.WME_ID             "
			+ " where a.WAR_ADMIN_ID = ?                                "
			+ " and c.WME_LEVEL = ?                                "
			+ " union                                               "
			+ " select e.WME_ID, e.WME_NAME, e.WME_URL,          "
			+ " e.WME_LEVEL, e.WME_ORDER, e.WME_PARENT_ID             "
			+ " from WC_ADMIN_MENU d                                "
			+ " join WC_MENU e on d.WAM_MENU_ID = e.WME_ID             "
			+ " where d.WAM_ADMIN_ID = ?                                "
			+ " and e.WME_LEVEL = ?                                "
			+ " order by WME_ORDER asc                             ";
		List<Map<String, Object>>  list1 = jdbcDao.queryForList(sql, new Object[]{adminId, menuLevel, adminId, menuLevel});
		return list1;
	}
	
	public void updLoginTime(Integer adminId)
	{
		Date dateNow = new Date();
		jdbcDao.update("update WC_ADMIN set WAD_LOGINDATE = ? where WAD_ID = ?",new Object[]{dateNow,adminId});
	}

	public WcAdmin getAdminById(Integer id) {
		// TODO Auto-generated method stub
		WcAdmin admin =  hibernateDao.get(WcAdmin.class, id);
		admin.setWadPwdMd5(admin.getWadPwd());
		return admin;
	}
	
	public void updAdmin(WcAdmin admin) throws JsonParseException, JsonMappingException, JsonGenerationException, IOException
	{
		if( admin.getWadPwd().length() == 0 )
		{
			admin.setWadPwd(admin.getWadPwdMd5());
		}
		else
		{
			admin.setWadPwd(DigestUtils.md5DigestAsHex(admin.getWadPwd().getBytes()));
		}
		
		
		hibernateDao.update(admin);
		Object[] obj = new Object[]{admin.getWadId()};
		jdbcDao.delete("delete from WC_ADMIN_ROLE where WAR_ADMIN_ID = ?", obj);
		jdbcDao.delete("delete from WC_ADMIN_MENU where WAM_ADMIN_ID = ?", obj);
		
		addAdminOther(admin);
		
		// 更新缓存 管理员可能由有部门修改为无部门，此时也需更新缓存
		hibernateDao.flush();

		
	
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryAdminNameToCache()
	{
		String sql = 
			  " select WAD_ID data, WAD_NAME value "
			+ " from WC_ADMIN "
			+ " where 1=1"
			+ " order by WAD_NAME asc ";
		
		return jdbcDao.queryForList(sql);
	}
	
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryShopAdminNameToCache()
	{
		String sql = 
			  " select WSA_ID data, WSA_NAME value "
			+ " from WC_SHOP_ADMIN "
			+ " where 1=1"
			+ " order by WSA_NAME asc ";
		
		return jdbcDao.queryForList(sql);
	}
	
}
