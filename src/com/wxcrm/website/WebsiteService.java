package com.wxcrm.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.util.Page;
@Service
@Transactional
public class WebsiteService implements IWebsiteService {

	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;
	
	public Page queryWebsite(WcWebsite webSite) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(
			"	select  " +
			"WCS_ID,			WCS_WEBSITE_NAME,	" +
			"		WCS_APP_NAME,			WCS_APP_ID,			WCS_APP_SECRET, " +
			"WCS_REDIRECT_URL,			WCS_TOKEN, " +
			"WCS_ADMIN_ID,		WCS_TYPE,	WCS_REGISTOR, " +
			"WCS_REGISTDATE " +
			"	from WC_WEBSITE a where 1=1 "
		);
		StringBuilder sqlCnt = new StringBuilder
		(
			"  	select count(*) from WC_WEBSITE a where 1=1 " 
		);
		List<Object> paraList = new ArrayList<Object>(){};
		Page page = new Page(sql.toString(),sqlCnt.toString(),webSite.getCurrentPage(),	webSite.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public void addWebsite(WcWebsite webSite) {
		// TODO Auto-generated method stub
		hibernateDao.add(webSite);
	}

	public WcWebsite getWebSiteById(Integer id) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcWebsite.class, id);
	}

	public void updWebSite(WcWebsite webSite) {
		// TODO Auto-generated method stub
		hibernateDao.update(webSite);
	}

	public WcWebsite queryMySiteByAdmin(Integer wadId) {
		// TODO Auto-generated method stub
		String sql = "select WCS_ID  from WC_WEBSITE a  where a.WCS_ADMIN_ID = ?  LIMIT 1";
		WcWebsite website = null;
		List<Map<String,Object>> list =  jdbcDao.queryForList(sql,new Object[]{wadId});
		if(list.size()>0)
		{
			Integer wcsId = (Integer)list.get(0).get("WCS_ID");
			website = this.getWebSiteById(wcsId);
		}
		return website;

	}

	public List<WcWebsite> getWebSiteByAdminId(Integer wadId) {
		// TODO Auto-generated method stub
		String hql = " from WcWebsite website where website.wcsAdminId  = ?";
		return hibernateDao.query(hql, new Object[]{wadId});
	}
	
	

}
