package com.wxcrm.weixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.util.Page;
import com.wxcrm.weixin.pojo.LzWeiMenu;
@Service
@Transactional
public class WeixinMenuService implements IWeixinMenuService {

	@Autowired
	private IJdbcDao jdbcDao;

	@Autowired
	private IHibernateDao hibernateDao;

	// 菜单创建（POST） 限100（次/天）  
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
	private static Logger log = Logger.getLogger(WeixinMenuService.class);
	
	public Page queryMenu(LzWeiMenu menu) {
		// TODO 远程获取菜单信息
		String sql = "select WMU_ID,WMU_APP_ID,WMU_JSON,WMU_DESC,WMU_STATUS ,WMU_REGISTOR_DATE from LZ_WEI_MENU ";
		String sqlCnt = " select count(*) from LZ_WEI_MENU ";
		List<Object> paraList = new ArrayList<Object>();
	
		Page page = new Page(sql.toString(),sqlCnt.toString(),menu.getCurrentPage(),menu.getPageSize(),paraList.toArray());
		jdbcDao.queryForPage(page);
		return page;
	}

	public int addMenu(LzWeiMenu menu, String accessToken) {
		// TODO Auto-generated method stub
		int result = 0;  
        // 拼装创建菜单的url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
        // 调用接口创建菜单
        String jsonMenu = menu.getWmuJson();
        JSONObject jsonObject = com.oilchem.weixin.http.HttpUtil.httpRequestJson(url, "POST", jsonMenu);  
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
                log.error("创建菜单失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}");  
            }  
            else
            {
            	addLzWeiMenu(menu);
            }
        }  
        return result;  
	}
	
	
	
	public LzWeiMenu getMenuById(Integer wmuId) {
		// TODO Auto-generated method stub
		return hibernateDao.get(LzWeiMenu.class, wmuId);
	}

	public void addLzWeiMenu(LzWeiMenu menu)
	{
		String sql = "select count(*) from LZ_WEI_MENU menu where menu.WMU_APP_ID = ? ";
		int num = jdbcDao.queryForInt(sql,new Object[]{menu.getWmuAppId()});
		if(num>0 && menu.getWmuId()!=null)
		{
			hibernateDao.update(menu);
		}
		else if(num==0)
		{
			hibernateDao.add(menu);
		}
	}
	
	public LzWeiMenu getMenuByAppId(String appId)
	{
		String sql = " select menu.WMU_ID,menu.WMU_APP_ID,menu.WMU_JSON,menu.WMU_DESC,menu.WMU_STATUS  from LZ_WEI_MENU menu where menu.WMU_APP_ID = ? ";
		List<Map<String,Object>> list = jdbcDao.queryForList(sql, new Object[]{appId});
		if(list.size()>0)
		{
			Map<String,Object> map = list.get(0);
			LzWeiMenu menu = new LzWeiMenu();
			menu.setWmuId((Integer)map.get("WMU_ID"));
			menu.setWmuAppId((String)map.get("WMU_APP_ID"));
			menu.setWmuJson((String)map.get("WMU_JSON"));
			menu.setWmuDesc((String)map.get("WMU_DESC"));
			menu.setWmuStatus((String)map.get("WMU_STATUS"));
			return menu;
		}
		else
		{
			return null;
		}
	}
}
