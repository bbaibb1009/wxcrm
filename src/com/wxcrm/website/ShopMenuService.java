package com.wxcrm.website;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.util.Page;

@Service
@Transactional
public class ShopMenuService implements IShopMenuService {
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

	public Page queryShopMenu(WcShopMenu menu) {
		// TODO Auto-generated method stub
		String menuName_Q = menu.getWsmName_Q();
		String menuLevel_Q = menu.getWsmLevel_Q();
		List<Object> paraList = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder(
			  " select a.WSM_ID,a.WSM_NAME,a.WSM_URL,a.WSM_LEVEL,a.WSM_ORDER,a.WSM_DESC,a.WSM_PARENT_ID,a.WSM_REGISTOR,a.WSM_REGIST_DATE, b.WSM_NAME parent_menu "
			+ " from WC_SHOP_MENU a "
			+ " left join WC_SHOP_MENU b on a.WSM_PARENT_ID = b.WSM_ID "
			+ " where 1 = 1 "
		);
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) from WC_SHOP_MENU a where 1 = 1 "
		);
		if( menuName_Q != null && menuName_Q.length() > 0 )
		{
			sql.append(" and a.WSM_NAME like ? ");
			sqlCnt.append(" and a.WSM_NAME like ? ");
			paraList.add("%" + menuName_Q + "%");
		}
		if( menuLevel_Q != null && menuLevel_Q.length() > 0 )
		{
			sql.append(" and a.WSM_LEVEL = ? ");
			sqlCnt.append(" and a.WSM_LEVEL = ? ");
			paraList.add(menuLevel_Q);
		}
		sql.append(" order by a.WSM_ID desc ");
		
		Page page = new Page(sql.toString(), sqlCnt.toString(), 
				menu.getCurrentPage(), menu.getPageSize(),
				paraList.toArray());
		jdbcDao.queryForPage(page);
		
		return page;
		
	}
	
	public void addShopMenu(WcShopMenu menu)
	{
		hibernateDao.add(menu);
		hibernateDao.flush();
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryShopMenuToCache()
	{
		String sql = 
			  " select " +
			  " WSM_ID id,WSM_NAME name,WSM_LEVEL menuLevel,WSM_PARENT_ID pId, "
			+ " case when WSM_LEVEL = '1' then 'true' "
			+ "      when WSM_LEVEL = '2' then 'true' "
			+ "      else 'false' end isParent,        "
			+ " case when WSM_LEVEL = '1' then 'true' "
			+ "      else 'false' end 'open'           "
			+ " from WC_SHOP_MENU "
			+ " order by WSM_LEVEL asc, WSM_ORDER asc ";
		return jdbcDao.queryForList(sql);
	}

	
	public void delShopMenu(String[] menuIds) {
		// TODO Auto-generated method stub
		String menuIdsStr = StringUtils.arrayToCommaDelimitedString(menuIds);
		jdbcDao.delete("delete from WC_SHOP_MENU where WSM_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ROLE_MENU where WSRM_MENU_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_SHOP_ADMIN_MENU where WSAM_MENU_ID in (" + menuIdsStr + ")");
	}

	public WcShopMenu getShopMenuById(Integer menuId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcShopMenu.class, menuId);
	}
	
	public void updShopMenu(WcShopMenu menu)
	{
		hibernateDao.update(menu);
		hibernateDao.flush();
		

	}


	
	
	
}
