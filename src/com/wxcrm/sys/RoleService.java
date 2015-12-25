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
import com.wxcrm.util.StringUtil;


@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private IJdbcDao jdbcDao;

	@Autowired
	private IHibernateDao hibernateDao;


	@Transactional(readOnly = true)
	public List<WcRole> queryRoleForAdminAdd()
	{
		return hibernateDao.query("from WcRole order by wroRoleId desc");
	}


	public void addRole(WcRole role) {
		// TODO Auto-generated method stub
		hibernateDao.add(role);
		addRoleOther(role);
		
	}

	private void addRoleOther(WcRole role)
	{
		
		String roleId = String.valueOf(role.getWroRoleId());
		String[] menuIds = role.getMenuIds().split(",");
		jdbcDao.batchUpdate("insert into WC_ROLE_MENU (WRM_ROLE_ID, WRM_MENU_ID) values (?, ?)", StringUtil.getObjAryList(roleId, menuIds)); 
		if( role.getAdminIds().length() > 0 )
		{
			String[] adminIds = role.getAdminIds().split(",");
			jdbcDao.batchUpdate("insert into WC_ADMIN_ROLE (WAR_ADMIN_ID, WAR_ROLE_ID) values (?, ?)", StringUtil.getObjAryList(adminIds, roleId)); 
		}
	}
	
	
	
	public void delRole(String[] roleIds) {
		// TODO Auto-generated method stub
		String roleIdsStr = StringUtils.arrayToCommaDelimitedString(roleIds);
		jdbcDao.delete("delete from WC_ROLE where WRO_ROLE_ID in (" + roleIdsStr + ")");
		jdbcDao.delete("delete from WC_ROLE_MENU where WRM_ROLE_ID in (" + roleIdsStr + ")");
		jdbcDao.delete("delete from WC_ADMIN_ROLE where WAR_ROLE_ID in (" + roleIdsStr + ")");
	}


	public WcRole getRoleById(Integer roleId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(WcRole.class, roleId);
	}


	public Page queryRole(WcRole role) {
		// TODO Auto-generated method stub
		String roleName_Q = role.getRoleName_Q();
		List<Object> paraList = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder(
			  " select WRO_ROLE_ID, WRO_ROLE_NAME, WRO_ROLE_DESC "
			+ " from WC_ROLE "
			+ " where 1 = 1 "
		);
		StringBuilder sqlCnt = new StringBuilder(
			" select count(*) from WC_ROLE where 1 = 1 "
		);
		if( roleName_Q != null && roleName_Q.length() > 0 )
		{
			sql.append(" and WRO_ROLE_NAME like ? ");
			sqlCnt.append(" and WRO_ROLE_NAME like ? ");
			paraList.add("%" + roleName_Q + "%");
		}
		sql.append(" order by WRO_ROLE_ID desc ");
		
		Page page = new Page(sql.toString(), sqlCnt.toString(), 
				role.getCurrentPage(), role.getPageSize(),
				paraList.toArray());
		jdbcDao.queryForPage(page);
		
		return page;
	}


	public void updRole(WcRole role) {
		// TODO Auto-generated method stub
		hibernateDao.update(role);
		Object[] obj = new Object[]{role.getWroRoleId()};
		jdbcDao.delete("delete from WC_ROLE_MENU where WRM_ROLE_ID = ?", obj);
		// 没有设置部门的管理员不能删除，因为在部门树中没有这部分管理员
//		jdbcDao.delete(" delete a from WC_ADMIN_ROLE a where a.WAR_ROLE_ID = ? ",obj);
//		
//		jdbcDao.delete(
//			  " delete a from lz_admin_role a where a.role_id = ? "
//			+ " and exists ( "
//			+ " 	select 1 from lz_admin b "
//			+ " 	where a.admin_id = b.admin_id "
//			+ "		and b.dept_id > 0 "
//			+ " ) "
//			, obj);
		addRoleOther(role);
	}

	@Transactional(readOnly = true)
	public List<String> queryRoleMenusById(Integer roleId)
	{
		List<Map<String, Object>> menuList =
			jdbcDao.queryForList("select WRM_ROLE_ID, WRM_MENU_ID from WC_ROLE_MENU where WRM_ROLE_ID = ?", 
					new Object[]{roleId});
		
		return chgList(menuList, "WRM_MENU_ID");
	}
	
	
	private List<String> chgList(List<Map<String, Object>> list, String colName)
	{
		List<String> resultList = new ArrayList<String>();
		for( Map<String, Object> map : list )
		{
			resultList.add(map.get(colName).toString());
		}
		
		return resultList;
	}
	
	@Transactional(readOnly = true)
	public List<WcRole> queryRoleForAdminUpd1(Integer adminId)
	{
		String sql =
			" select b.WRO_ROLE_ID, b.WRO_ROLE_NAME "
			+ " from WC_ADMIN_ROLE a "
			+ " join WC_ROLE b on a.WAR_ROLE_ID = b.WRO_ROLE_ID "
			+ " where a.WAR_ADMIN_ID = ? "
			+ " order by b.WRO_ROLE_ID desc ";
		List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[]{adminId});
		return chgRoleList(list);
	}
	
	@Transactional(readOnly = true)
	public List<WcRole> queryRoleForAdminUpd0(Integer adminId)
	{
		String sql =
			  " select a.WRO_ROLE_ID, a.WRO_ROLE_NAME "
			+ " from WC_ROLE a "
			+ " where not exists ( "
			+ " select 1 from WC_ADMIN_ROLE b "
			+ " where a.WRO_ROLE_ID = b.WAR_ROLE_ID and b.WAR_ADMIN_ID = ? ) "
			+ " order by a.WRO_ROLE_ID desc ";
		List<Map<String, Object>> list = jdbcDao.queryForList(sql, new Object[]{adminId});
		return chgRoleList(list);
	}
	
	private List<WcRole> chgRoleList(List<Map<String, Object>> list)
	{
		List<WcRole> roleList = new ArrayList<WcRole>();
		WcRole role = null;
		for( Map<String, Object> map : list )
		{
			role = new WcRole();
			role.setWroRoleId(Integer.valueOf(map.get("WRO_ROLE_ID").toString()));
			role.setWroRoleName(map.get("WRO_ROLE_NAME").toString());
			roleList.add(role);
		}
		
		return roleList;
	}
}
