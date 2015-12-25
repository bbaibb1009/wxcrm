package com.wxcrm.sys;

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
public class MenuService implements IMenuService {
	@Autowired
	private IJdbcDao jdbcDao;
	
	@Autowired
	private IHibernateDao hibernateDao;

	public Page queryMenu(WcMenu menu) {
		// TODO Auto-generated method stub
		String menuName_Q = menu.getWmeName_Q();
		String menuLevel_Q = menu.getWmeLevel_Q();
		List<Object> paraList = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder(
			  " select a.WME_ID,a.WME_NAME,a.WME_URL,a.WME_LEVEL,a.WME_ORDER,a.WME_DESC,a.WME_PARENT_ID,a.WME_REGISTOR,a.WME_REGIST_DATE, b.WME_NAME parent_menu "
			+ " from WC_MENU a "
			+ " left join WC_MENU b on a.WME_PARENT_ID = b.WME_ID "
			+ " where 1 = 1 "
		);
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) from WC_MENU a where 1 = 1 "
		);
		if( menuName_Q != null && menuName_Q.length() > 0 )
		{
			sql.append(" and a.WME_NAME like ? ");
			sqlCnt.append(" and a.WME_NAME like ? ");
			paraList.add("%" + menuName_Q + "%");
		}
		if( menuLevel_Q != null && menuLevel_Q.length() > 0 )
		{
			sql.append(" and a.WME_LEVEL = ? ");
			sqlCnt.append(" and a.WME_LEVEL = ? ");
			paraList.add(menuLevel_Q);
		}
		sql.append(" order by a.WME_ID desc ");
		
		Page page = new Page(sql.toString(), sqlCnt.toString(), 
				menu.getCurrentPage(), menu.getPageSize(),
				paraList.toArray());
		jdbcDao.queryForPage(page);
		
		return page;
		
	}
	
	public void addMenu(WcMenu menu)
	{
		hibernateDao.add(menu);
		hibernateDao.flush();
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> queryMenuToCache()
	{
		String sql = 
			  " select " +
			  " WME_ID id,WME_NAME name,WME_LEVEL menuLevel,WME_PARENT_ID pId, "
			+ " case when WME_LEVEL = '1' then 'true' "
			+ "      when WME_LEVEL = '2' then 'true' "
			+ "      else 'false' end isParent,        "
			+ " case when WME_LEVEL = '1' then 'true' "
			+ "      else 'false' end 'open'           "
			+ " from WC_MENU "
			+ " order by WME_LEVEL asc, WME_ORDER asc ";
		return jdbcDao.queryForList(sql);
	}

	
	public void delMenu(String[] menuIds) {
		// TODO Auto-generated method stub
		String menuIdsStr = StringUtils.arrayToCommaDelimitedString(menuIds);
		jdbcDao.delete("delete from WC_MENU where WME_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_ROLE_MENU where WRM_MENU_ID in (" + menuIdsStr + ")");
		jdbcDao.delete("delete from WC_ADMIN_MENU where WAM_MENU_ID in (" + menuIdsStr + ")");
	}

	public WcMenu getMenuById(Integer menuId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcMenu.class, menuId);
	}
	
	public void updMenu(WcMenu menu)
	{
		hibernateDao.update(menu);
		hibernateDao.flush();
		

	}

	public List<Map<String, Object>> queryShopMenuToCache() {
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
	
	
	
}
