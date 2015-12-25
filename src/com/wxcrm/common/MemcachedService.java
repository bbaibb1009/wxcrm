package com.wxcrm.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danga.MemCached.MemCachedClient;
import com.wxcrm.sys.IAdminService;
import com.wxcrm.sys.IMenuService;
import com.wxcrm.util.SysConstant;
import com.wxcrm.website.IShopAdminService;
import com.wxcrm.weixin.IWeixinService;

@Service
public class MemcachedService implements IMemcachedService
{
	private static Logger log = Logger.getLogger(MemcachedService.class);
	
	// 缓存时间10天
	private static final int STORE_TIME = 10 * 24 * 60 * 60 * 1000;
	
	@Autowired
	private MemCachedClient memCachedClient;
	
	@Autowired
	private IMenuService menuService;
	
//	@Autowired
//	private IDeptService deptService;
	
//	@Autowired
//	private IProductService productService;
//	
//	@Autowired
//	private IProductDataService productDataService;
//	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IShopAdminService adminshopservice;
//	
//	@Autowired
//	private IMailService mailService;
//	
//	@Autowired
//	private IProductDatatabService productDatatabService;
//	
//	@Autowired
//	private IInfoUnitService infounitService;
//	
//	@Autowired 
//	private INewsTemplateService newsTemplateService;
//	
//	@Autowired
//	private IDeviceService deviceService;
//
//	@Autowired
//	private IPackUnitService packUnitService;
//	
//	@Autowired
//	private IInfoPackService infoPackService;
//	
//	@Autowired
//	private ISmsPackService smsPackService;
//	
//	@Autowired
//	private IMeetingPackService meetpackService;
//	
//	@Autowired
//	private IWeiPackService weipackService;
//	
//	@Autowired
//	private IPrtPackService prtpackService;
//	
//	@Autowired
//	private IAdvPackService advPackService;
//	
//	@Autowired
//	private IBlacklistService blacklistService;
//	
//	@Autowired
//	private IDicConfigService dicConfigService;
	
	@Autowired
	private IWeixinService weixinservice;
	
	public void init()
	{
		// 清空缓存
		if( ! memCachedClient.flushAll() )
		{
			log.error("memcached清空失败");
		}
		
		setMenuAll();
		log.info("总后台系统菜单存入memcached成功");

		setAdminNameAll();
		log.info("总后台管理员姓名存入memcached成功");
		
		setShopMenuAll();
		log.info("微信商家系统菜单存入memcached成功");
		setShopAdminNameAll();
		log.info("微信商家管理员姓名存入memcached成功");
		
		
//		setDeptAll();
//		log.info("系统部门存入memcached成功");
//		setDeptNameAll();
//		log.info("所有部门名称存入memcached成功");
//		setDeptName1All();
//		log.info("一级部门名称存入memcached成功");
//		setDeptName2All();
//		log.info("二级部门名称存入memcached成功");
//		setMailConfigAll();
//		log.info("邮箱配置存入memcached成功");
//		setProductDatatabAll();
//		log.info("产品数据表存入memcached成功");
//		setProductUnitAll();
//		log.info("产品数据单元存入memcached成功");
//		setCompNameAll();
//		log.info("装置企业名称存入memcached成功");
//		setTypeNameAll();
//		log.info("装置类型名称存入memcached成功");
//		setInfoUnitAll();
//		log.info("信息单元配置存入memcached成功");
//		setNewsTempNameAll();
//		log.info("新闻模板配置存入memcached成功");
//		setPackUnitNameAll();
//		log.info("套餐单元存入memcached成功");
//		setPackInfoNameAll();
//		log.info("资讯套餐名称存入memcached成功");
//		setPackInfoAll();
//		log.info("资讯套餐树存入memcached成功");
//		setPackSmsNameAll();
//		log.info("短信套餐名称存入memcached成功");
//		setPackSmsAll();
//		log.info("短信套餐树存入memcached成功");
//		setPackLeafSmsNameAll();
//		log.info("短信基础套餐名称存入memcached成功");
//		setPackLeafInfoNameAll();
//		log.info("资讯基础套餐名称存入memcached成功");
//
//		setPackDxtAll();
//		log.info("短讯通套餐树存入memcached成功");
//		setPackDxtNameAll();
//		log.info("短讯通套餐名称存入memcached成功");
//		setPackLeafDxtNameAll();
//		log.info("短讯通基础套餐名称存入memcached成功");
//		
//		setPackMeetingAll();
//		log.info("会议套餐树存入memcached成功");
//		setPackMeetingNameAll();
//		log.info("会议套餐名称存入memcached成功");
//		setPackLeafMeetingNameAll();
//		log.info("会议基础套餐名称存入memcached成功");
//		
//		setPackWeiAll();
//		log.info("微信套餐树存入memcached成功");
//		setPackWeiNameAll();
//		log.info("微信套餐名称存入memcached成功");
//		setPackLeafWeiNameAll();
//		log.info("微信基础套餐名称存入memcached成功");
//		
//		setPackPrtAll();
//		log.info("印刷品套餐树存入memcached成功");
//		setPackPrtNameAll();
//		log.info("印刷品套餐名称存入memcached成功");
//		setPackLeafPrtNameAll();
//		log.info("印刷品基础套餐名称存入memcached成功");
//		
//		
//		setPackLeafAdvNameAll();
//		log.info("广告基础套餐名称存入memcached成功");
//		setPackAdvNameAll();
//		log.info("广告套餐名称存入memcached成功");
//		setPackAdvAll();
//		log.info("广告套餐树存入memcached成功");
//		setBlacktelAll();
//		log.info("黑名单存入memcached成功");
	}
	
//	public void setBlacktelAll()
//	{
//		setBlacktelAll(blacklistService.queryBlacktelToCache());
//	}
//	
//	public void setBlacktelAll(List<Map<String, Object>> blacktelList)
//	{
//		if( ! memCachedClient.set(SysConstant.BLACKTEL_ALL, blacktelList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("黑名单存入memcached失败");
//		}
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getBlacktelAll()
//	{
//		List<Map<String, Object>> blacktelList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.BLACKTEL_ALL);
//		if( blacktelList == null )
//		{
//			blacktelList = blacklistService.queryBlacktelToCache();
//			setBlacktelAll(blacktelList);
//		}
//		
//		return blacktelList;
//	}
	
	public void setMenuAll()
	{
		setMenuAll(menuService.queryMenuToCache());
	}
	
	public void setMenuAll(List<Map<String, Object>> menuList)
	{
		if( ! memCachedClient.set(SysConstant.MENU_ALL, menuList,
				new Date(System.currentTimeMillis() + STORE_TIME)) )
		{
			log.error("系统菜单存入memcached失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMenuAll()
	{
		List<Map<String, Object>> menuList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.MENU_ALL);
		if( menuList == null )
		{
			menuList = menuService.queryMenuToCache();
			setMenuAll(menuList);
		}
		
		return menuList;
	}
	
	public void setAdminNameAll()
	{
		setAdminNameAll(adminService.queryAdminNameToCache());
	}
	
	public void setAdminNameAll(List<Map<String, Object>> adminNameList)
	{
		if( ! memCachedClient.set(SysConstant.ADMIN_NAME_ALL, adminNameList,
				new Date(System.currentTimeMillis() + STORE_TIME)) )
		{
			log.error("管理员姓名存入memcached失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAdminNameAll()
	{
		List<Map<String, Object>> adminNameList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.ADMIN_NAME_ALL);
		if( adminNameList == null )
		{
			adminNameList = adminService.queryAdminNameToCache();
			setAdminNameAll(adminNameList);
		}
		
		return adminNameList;
	}
	
	public void setShopMenuAll()
	{
		setShopMenuAll(menuService.queryShopMenuToCache());
	}
	
	public void setShopMenuAll(List<Map<String, Object>> menuList)
	{
		if( ! memCachedClient.set(SysConstant.SHOP_MENU_ALL, menuList,
				new Date(System.currentTimeMillis() + STORE_TIME)) )
		{
			log.error("微信商家系统菜单存入memcached失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getShopMenuAll()
	{
		List<Map<String, Object>> menuList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.SHOP_MENU_ALL);
		if( menuList == null )
		{
			menuList = menuService.queryShopMenuToCache();
			setShopMenuAll(menuList);
		}
		
		return menuList;
	}
	
	
	
	
	public void setShopAdminNameAll()
	{
		setShopAdminNameAll(adminshopservice.queryShopAdminNameToCache());
	}
	
	public void setShopAdminNameAll(List<Map<String, Object>> adminNameList)
	{
		if(!memCachedClient.set(SysConstant.SHOP_ADMIN_NAME_ALL, adminNameList,new Date(System.currentTimeMillis() + STORE_TIME)) )
		{
			log.error("微信商家管理员姓名存入memcached失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getShopAdminNameAll()
	{
		List<Map<String, Object>> adminNameList = 
			(List<Map<String, Object>>) memCachedClient.get(SysConstant.SHOP_ADMIN_NAME_ALL);
		if( adminNameList == null )
		{
			adminNameList = adminshopservice.queryShopAdminNameToCache();
			setShopAdminNameAll(adminNameList);
		}
		
		return adminNameList;
	}
	
//	
//	public void setDeptAll()
//	{
//		setDeptAll(deptService.queryDeptToCache());
//	}
//	
//	public void setDeptAll(List<Map<String, Object>> deptList)
//	{
//		if( ! memCachedClient.set(SysConstant.DEPT_ALL, deptList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("系统部门存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getDeptAll()
//	{
//		List<Map<String, Object>> deptList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.DEPT_ALL);
//		if( deptList == null )
//		{
//			deptList = deptService.queryDeptToCache();
//			setDeptAll(deptList);
//		}
//		
//		return deptList;
//	}
//	
//	public void setProNameAll()
//	{
//		setProNameAll(productService.queryProNameToCache());
//	}
//	
//	public void setProNameAll(List<Map<String, Object>> proNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PRO_NAME_ALL, proNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("产品名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getProNameAll()
//	{
//		List<Map<String, Object>> proNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PRO_NAME_ALL);
//		if( proNameList == null )
//		{
//			proNameList = productService.queryProNameToCache();
//			setProNameAll(proNameList);
//		}
//		
//		return proNameList;
//	}
//	
//	public void setProductTemplateAll()
//	{
//		setProductTemplateAll(productDataService.queryProductTemplateToCache());
//	}
//	
//	public void setProductTemplateAll(List<Map<String, Object>> productTemplateList)
//	{
//		if( ! memCachedClient.set(SysConstant.PRODUCT_TEMPLATE_ALL, productTemplateList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("产品数据组存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getProductTemplateAll()
//	{
//		List<Map<String, Object>> productTemplateList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PRODUCT_TEMPLATE_ALL);
//		if( productTemplateList == null )
//		{
//			productTemplateList = productDataService.queryProductTemplateToCache();
//			setProductTemplateAll(productTemplateList);
//		}
//		
//		return productTemplateList;
//	}
//	
//	
//	
//	public void setDeptNameAll()
//	{
//		setDeptNameAll(deptService.queryDeptNameToCache());
//	}
//	
//	public void setDeptNameAll(List<Map<String, Object>> deptNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.DEPT_NAME_ALL, deptNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("所有部门名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getDeptNameAll()
//	{
//		List<Map<String, Object>> deptNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.DEPT_NAME_ALL);
//		if( deptNameList == null )
//		{
//			deptNameList = deptService.queryDeptNameToCache();
//			setDeptNameAll(deptNameList);
//		}
//		
//		return deptNameList;
//	}
//	
//	public void setDeptName1All()
//	{
//		setDeptName1All(deptService.queryDeptName1ToCache());
//	}
//	
//	public void setDeptName1All(List<Map<String, Object>> deptName1List)
//	{
//		if( ! memCachedClient.set(SysConstant.DEPT_NAME1_ALL, deptName1List,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("一级部门名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getDeptName1All()
//	{
//		List<Map<String, Object>> deptName1List = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.DEPT_NAME1_ALL);
//		if( deptName1List == null )
//		{
//			deptName1List = deptService.queryDeptName1ToCache();
//			setDeptName1All(deptName1List);
//		}
//		
//		return deptName1List;
//	}
//	
//	public void setDeptName2All()
//	{
//		setDeptName2All(deptService.queryDeptName2ToCache());
//	}
//	
//	public void setDeptName2All(List<Map<String, Object>> deptName2List)
//	{
//		if( ! memCachedClient.set(SysConstant.DEPT_NAME2_ALL, deptName2List,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("二级部门名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getDeptName2All()
//	{
//		List<Map<String, Object>> deptName2List = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.DEPT_NAME2_ALL);
//		if( deptName2List == null )
//		{
//			deptName2List = deptService.queryDeptName2ToCache();
//			setDeptName2All(deptName2List);
//		}
//		
//		return deptName2List;
//	}
//	
//	public void setMailConfigAll()
//	{
//		setMailConfigAll(mailService.queryMailConfigToCache());
//	}
//	
//	public void setMailConfigAll(List<LzMailConfig> mailConfigList)
//	{
//		if( ! memCachedClient.set(SysConstant.MAIL_CONFIG_ALL, mailConfigList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("邮箱配置存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<LzMailConfig> getMailConfigAll()
//	{
//		List<LzMailConfig> mailConfigList = 
//			(List<LzMailConfig>) memCachedClient.get(SysConstant.MAIL_CONFIG_ALL);
//		if( mailConfigList == null )
//		{
//			mailConfigList = mailService.queryMailConfigToCache();
//			setMailConfigAll(mailConfigList);
//		}
//		
//		return mailConfigList;
//	}
//	
//	public void setProductDatatabAll()
//	{
//		setProductDatatabAll(productDatatabService.queryProductDatatabToCache());
//	}
//	
//	public void setProductDatatabAll(List<Map<String, Object>> productDatatabList)
//	{
//		if( ! memCachedClient.set(SysConstant.PRODUCT_DATATAB_ALL, productDatatabList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("产品数据表存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getProductDatatabAll()
//	{
//		List<Map<String, Object>> productDatatabList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PRODUCT_DATATAB_ALL);
//		if( productDatatabList == null )
//		{
//			productDatatabList = productDatatabService.queryProductDatatabToCache();
//			setProductDatatabAll(productDatatabList);
//		}
//		
//		return productDatatabList;
//	}
//	
//	public void setProductUnitAll()
//	{
//		setProductUnitAll(productDataService.queryProductUnitToCache());
//	}
//	
//	public void setProductUnitAll(List<Map<String, Object>> productUnitList)
//	{
//		if( ! memCachedClient.set(SysConstant.PRODUCT_UNIT_ALL, productUnitList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("产品数据单元存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getProductUnitAll()
//	{
//		List<Map<String, Object>> productUnitList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PRODUCT_UNIT_ALL);
//		if( productUnitList == null )
//		{
//			productUnitList = productDataService.queryProductUnitToCache();
//			setProductUnitAll(productUnitList);
//		}
//		
//		return productUnitList;
//	}
//	
//	public void setCompNameAll()
//	{
//		setCompNameAll(deviceService.queryCompNameToCache());
//	}
//	
//	public void setCompNameAll(List<Map<String, Object>> compNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.COMP_NAME_ALL, compNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("装置企业名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getCompNameAll()
//	{
//		List<Map<String, Object>> compNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.COMP_NAME_ALL);
//		if( compNameList == null )
//		{
//			compNameList = deviceService.queryCompNameToCache();
//			setCompNameAll(compNameList);
//		}
//		
//		return compNameList;
//	}
//	
//	public void setTypeNameAll()
//	{
//		setTypeNameAll(deviceService.queryTypeNameToCache());
//	}
//	
//	public void setTypeNameAll(List<Map<String, Object>> typeNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.TYPE_NAME_ALL, typeNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("装置类型名称存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getTypeNameAll()
//	{
//		List<Map<String, Object>> typeNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.TYPE_NAME_ALL);
//		if( typeNameList == null )
//		{
//			typeNameList = deviceService.queryTypeNameToCache();
//			setTypeNameAll(typeNameList);
//		}
//		
//		return typeNameList;
//	}
//	
//	public void setIPYAll()
//	{
//		setIPYAll(dicConfigService.queryIPYToCache());
//	}
//	
//	public void setIPYAll(List<LzDictionaryConfigure> ipyList)
//	{
//		if( ! memCachedClient.set(SysConstant.IPY_ALL, ipyList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("IP白名单存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<LzDictionaryConfigure> getIPYAll()
//	{
//		List<LzDictionaryConfigure> ipyList = 
//			(List<LzDictionaryConfigure>) memCachedClient.get(SysConstant.IPY_ALL);
//		if( ipyList == null )
//		{
//			ipyList = dicConfigService.queryIPYToCache();
//			setIPYAll(ipyList);
//		}
//		
//		return ipyList;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getInfoUnitNameAll() {
//		
//		List<Map<String, Object>> infoUnitList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.INFO_UNIT_ALL);
//		if( infoUnitList == null )
//		{
//			infoUnitList = infounitService.queryInfoUnitToCache();
//			setInfoUnitAll(infoUnitList);
//		}
//		
//		return infoUnitList;
//	}
//	public void setInfoUnitAll()
//	{
//		setInfoUnitAll(infounitService.queryInfoUnitToCache());
//	}
//	
//	public void setInfoUnitAll(List<Map<String, Object>> infoUnitList) 
//	{
//		if( ! memCachedClient.set(SysConstant.INFO_UNIT_ALL, infoUnitList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("信息单元存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getNewsTempNameAll() {
//		
//		List<Map<String, Object>> newsTempList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.NEWS_TEMPLATE_ALL);
//		if( newsTempList == null )
//		{
//			newsTempList = newsTemplateService.queryNewsTemplateToCache();
//			setNewsTempNameAll(newsTempList);
//		}
//		return newsTempList;
//	}
//	
//	public void setNewsTempNameAll()
//	{
//		setNewsTempNameAll(newsTemplateService.queryNewsTemplateToCache());
//	}
//	
//	public void setNewsTempNameAll(List<Map<String, Object>> newsTempList)
//	{
//		if( ! memCachedClient.set(SysConstant.NEWS_TEMPLATE_ALL, newsTempList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("新闻模板存入memcached失败");
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackUnitAll() {
//			List<Map<String, Object>> packUnitList = 
//				(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACKUNIT_ALL);
//			if( packUnitList == null )
//			{
//				packUnitList = packUnitService.queryPackUnitToCache();
//				setPackUnitNameAll(packUnitList);
//			}
//			return packUnitList;
//	}
//	
//	public void setPackUnitNameAll()
//	{
//		setPackUnitNameAll(packUnitService.queryPackUnitToCache());
//	}
//	
//	public void setPackUnitNameAll(List<Map<String, Object>> packUnitList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACKUNIT_ALL, packUnitList,
//				new Date(System.currentTimeMillis() + STORE_TIME)) )
//		{
//			log.error("套餐单元存入memcached失败");
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackInfoNameAll() {
//		
//		List<Map<String, Object>> packInfoNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_INFO_NAME_ALL);
//		if( packInfoNameList == null )
//		{
//			packInfoNameList = infoPackService.queryInfoPackNameToCache();
//			setPackInfoNameAll(packInfoNameList);
//		}
//		return packInfoNameList;
//	}
//	
//	public void setPackInfoNameAll(List<Map<String, Object>> PackInfoNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_INFO_NAME_ALL, PackInfoNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("资讯套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackInfoNameAll()
//	{
//		setPackInfoNameAll(infoPackService.queryInfoPackNameToCache());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackInfoAll()
//	{
//		List<Map<String, Object>> packInfoList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_INFO_ALL);
//		if( packInfoList == null )
//		{
//			packInfoList = infoPackService.queryInfoPackToCache();
//			setPackInfoAll(packInfoList);
//		}
//		return packInfoList;
//	}
//	
//	public void setPackInfoAll(List<Map<String, Object>> PackInfoList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_INFO_ALL, PackInfoList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("资讯套餐树存入memcached失败");
//		}
//	}
//	
//	public void setPackInfoAll()
//	{
//		setPackInfoAll(infoPackService.queryInfoPackToCache());
//	}
//	
//	///////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackSmsNameAll() {
//		
//		List<Map<String, Object>> packSmsNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_SMS_NAME_ALL);
//		if( packSmsNameList == null )
//		{
//			packSmsNameList = smsPackService.querySmsPackNameToCache();
//			setPackSmsNameAll(packSmsNameList);
//		}
//		return packSmsNameList;
//	}
//	
//	public void setPackSmsNameAll(List<Map<String, Object>> PackSmsNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_SMS_NAME_ALL, PackSmsNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短信套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackSmsNameAll()
//	{
//		setPackSmsNameAll(smsPackService.querySmsPackNameToCache());
//	}
//	///////////////////////////////////////////////////////////////////////////////
//	
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackSmsAll()
//	{
//		List<Map<String, Object>> packSmsList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_SMS_ALL);
//		if( packSmsList == null )
//		{
//			packSmsList = smsPackService.querySmsPackToCache();
//			setPackSmsAll(packSmsList);
//		}
//		return packSmsList;
//	}
//	
//	public void setPackSmsAll(List<Map<String, Object>> PackSmsList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_SMS_ALL, PackSmsList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短信套餐树存入memcached失败");
//		}
//	}
//	
//	public void setPackSmsAll()
//	{
//		setPackSmsAll(smsPackService.querySmsPackToCache());
//	}
//	
//	
//	///////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafSmsNameAll() {
//		
//		List<Map<String, Object>> packSmsNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_SMS_NAME_ALL);
//		if( packSmsNameList == null )
//		{
//			packSmsNameList = smsPackService.querySmsLeafPackNameToCache();
//			setPackLeafSmsNameAll(packSmsNameList);
//		}
//		return packSmsNameList;
//	}
//	
//	public void setPackLeafSmsNameAll(List<Map<String, Object>> PackLeafSmsNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_LEAF_SMS_NAME_ALL, PackLeafSmsNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短信基础套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackLeafSmsNameAll()
//	{
//		setPackLeafSmsNameAll(smsPackService.querySmsLeafPackNameToCache());
//	}
//	///////////////////////////////////////////////////////////////////////////////
//	
/////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafInfoNameAll() {
//		
//		List<Map<String, Object>> packInfoNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_INFO_NAME_ALL);
//		if( packInfoNameList == null )
//		{
//			packInfoNameList = infoPackService.queryInfoLeafPackNameToCache();
//			setPackLeafInfoNameAll(packInfoNameList);
//		}
//		return packInfoNameList;
//	}
//	
//	public void setPackLeafInfoNameAll(List<Map<String, Object>> PackLeafInfoNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_LEAF_INFO_NAME_ALL, PackLeafInfoNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("资讯基础套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackLeafInfoNameAll()
//	{
//		setPackLeafInfoNameAll(infoPackService.queryInfoLeafPackNameToCache());
//	}
//	///////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackDxtAll() {
//		List<Map<String, Object>> packDxtList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_DXT_ALL);
//		if( packDxtList == null )
//		{
//			packDxtList = smsPackService.queryDxtPackToCache();
//			setPackSmsAll(packDxtList);
//		}
//		return packDxtList;
//	}
//	
//
//
//	public void setPackDxtAll() {
//		setPackDxtAll(smsPackService.queryDxtPackToCache());
//	}
//	
//	public void setPackDxtAll(List<Map<String, Object>> PackDxtList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_DXT_ALL, PackDxtList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短讯通套餐树存入memcached失败");
//		}
//	}
//	
//	//////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackDxtNameAll() {
//		List<Map<String, Object>> packDxtNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_DXT_NAME_ALL);
//		if( packDxtNameList == null )
//		{
//			packDxtNameList = smsPackService.queryDxtPackNameToCache();
//			setPackDxtNameAll(packDxtNameList);
//		}
//		return packDxtNameList;
//	}
//	
//	
//	
//	public void setPackDxtNameAll() {
//		setPackDxtNameAll(smsPackService.queryDxtPackNameToCache());
//	}
//	
//	public void setPackDxtNameAll(List<Map<String, Object>> PackDxtNameList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_DXT_NAME_ALL, PackDxtNameList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短讯通基础套餐名称存入memcached失败");
//		}
//	}
//
//
//	//////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafDxtNameAll() {
//		List<Map<String, Object>> packDxtNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_DXT_NAME_ALL);
//		if( packDxtNameList == null )
//		{
//			packDxtNameList = smsPackService.queryDxtLeafPackNameToCache();
//			setPackDxtNameAll(packDxtNameList);
//		}
//		return packDxtNameList;
//	}
//
//	public void setPackLeafDxtNameAll() {
//		setPackLeafDxtNameAll(smsPackService.queryDxtLeafPackNameToCache());
//	}
//	
//	public void setPackLeafDxtNameAll(List<Map<String, Object>> PackLeafDxtList) {
//		if(!memCachedClient.set(SysConstant.PACK_LEAF_DXT_NAME_ALL, PackLeafDxtList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("短讯通叶子节点存入memcached失败");
//		}
//	}
//
//	//////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackMeetingAll() {
//		List<Map<String, Object>> packMeetingList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_MEETING_ALL);
//		if( packMeetingList == null )
//		{
//			packMeetingList = meetpackService.queryMeetPackToCache();
//			setPackMeetingAll(packMeetingList);
//		}
//		return packMeetingList;
//	}
//	public void setPackMeetingAll() {
//		setPackMeetingAll(meetpackService.queryMeetPackToCache());
//	}
//	
//	public void setPackMeetingAll(List<Map<String, Object>> packMeetingList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_MEETING_ALL, packMeetingList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("会议套餐树存入memcached失败");
//		}
//	}
//	//////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafMeetingNameAll() {
//		List<Map<String, Object>> packLeafMeetingNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_MEETING_NAME_ALL);
//		if( packLeafMeetingNameList == null )
//		{
//			packLeafMeetingNameList = meetpackService.queryMeetLeafPackNameToCache();
//			setPackLeafMeetingNameAll(packLeafMeetingNameList);
//		}
//		return packLeafMeetingNameList;
//	}
//	public void setPackLeafMeetingNameAll() {
//		setPackLeafMeetingNameAll(meetpackService.queryMeetLeafPackNameToCache());
//		
//	}
//	public void setPackLeafMeetingNameAll(List<Map<String, Object>> packLeafMeetingNameList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_LEAF_MEETING_NAME_ALL, packLeafMeetingNameList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("会议基础套餐名称存入memcached失败");
//		}
//	}
//	////////////////////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackMeetingNameAll() {
//		List<Map<String, Object>> packMeetingNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_MEETING_NAME_ALL);
//		if( packMeetingNameList == null )
//		{
//			packMeetingNameList = meetpackService.queryMeetPackNameToCache();
//			setPackMeetingNameAll(packMeetingNameList);
//		}
//		return packMeetingNameList;
//	}
//
//	public void setPackMeetingNameAll() {
//		setPackMeetingNameAll(meetpackService.queryMeetPackNameToCache());
//	}
//	
//	public void setPackMeetingNameAll(List<Map<String, Object>> packMeetingNameList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_MEETING_NAME_ALL, packMeetingNameList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("会议套餐名称存入memcached失败");
//		}
//	}
//	
//	///////////////////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackWeiAll() {
//		List<Map<String, Object>> packWeiList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_WEI_ALL);
//		if( packWeiList == null )
//		{
//			packWeiList = weipackService.queryWeiPackToCache();
//			setPackWeiAll(packWeiList);
//		}
//		return packWeiList;
//	}
//
//	public void setPackWeiAll() {
//		setPackWeiAll(weipackService.queryWeiPackToCache());
//	}
//	
//	public void setPackWeiAll(List<Map<String, Object>> packWeiList) {
//		if(!memCachedClient.set(SysConstant.PACK_WEI_ALL, packWeiList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("微信套餐存入memcached失败");
//		}
//	}
//	////////////////////////////////////////////////////////////////////////////////////////////
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackWeiNameAll() {
//		List<Map<String, Object>> packWeiNameList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_WEI_NAME_ALL);
//		if( packWeiNameList == null )
//		{
//			packWeiNameList = weipackService.queryWeiPackNameToCache();
//			setPackWeiNameAll(packWeiNameList);
//		}
//		return packWeiNameList;
//	}
//
//	public void setPackWeiNameAll() {
//		setPackWeiNameAll(weipackService.queryWeiPackNameToCache());
//	}
//	
//	public void setPackWeiNameAll(List<Map<String, Object>> packWeiNameList) {
//		if(!memCachedClient.set(SysConstant.PACK_WEI_NAME_ALL, packWeiNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("微信套餐名称存入memcached失败");
//		}
//	}
//	
//	//////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafWeiNameAll() {
//		List<Map<String, Object>> packLeafWeiNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_WEI_NAME_ALL);
//		if( packLeafWeiNameList == null )
//		{
//			packLeafWeiNameList = weipackService.queryWeiLeafPackNameToCache();
//			setPackLeafWeiNameAll(packLeafWeiNameList);
//		}
//		return packLeafWeiNameList;
//	}
//	public void setPackLeafWeiNameAll() {
//		setPackLeafWeiNameAll(weipackService.queryWeiLeafPackNameToCache());
//		
//	}
//	public void setPackLeafWeiNameAll(List<Map<String, Object>> packLeafWeiNameList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_LEAF_WEI_NAME_ALL, packLeafWeiNameList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("微信基础套餐名称存入memcached失败");
//		}
//	}
//	
//	/*&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*/
//	
//	
//	///////////////////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackPrtAll() {
//		List<Map<String, Object>> packPrtList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_PRT_ALL);
//		if( packPrtList == null )
//		{
//			packPrtList = prtpackService.queryPrtPackToCache();
//			setPackPrtAll(packPrtList);
//		}
//		return packPrtList;
//	}
//
//	public void setPackPrtAll() {
//		setPackPrtAll(prtpackService.queryPrtPackToCache());
//	}
//	
//	public void setPackPrtAll(List<Map<String, Object>> packPrtList) {
//		if(!memCachedClient.set(SysConstant.PACK_PRT_ALL, packPrtList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("印刷品套餐存入memcached失败");
//		}
//	}
//	////////////////////////////////////////////////////////////////////////////////////////////
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackPrtNameAll() {
//		List<Map<String, Object>> packPrtNameList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_PRT_NAME_ALL);
//		if( packPrtNameList == null )
//		{
//			packPrtNameList = prtpackService.queryPrtPackNameToCache();
//			setPackPrtNameAll(packPrtNameList);
//		}
//		return packPrtNameList;
//	}
//
//	public void setPackPrtNameAll() {
//		setPackPrtNameAll(prtpackService.queryPrtPackNameToCache());
//	}
//	
//	public void setPackPrtNameAll(List<Map<String, Object>> packPrtNameList) {
//		if(!memCachedClient.set(SysConstant.PACK_PRT_NAME_ALL, packPrtNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("印刷品套餐名称存入memcached失败");
//		}
//	}
//	
//	//////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafPrtNameAll() {
//		List<Map<String, Object>> packLeafPrtNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_PRT_NAME_ALL);
//		if( packLeafPrtNameList == null )
//		{
//			packLeafPrtNameList = prtpackService.queryPrtLeafPackNameToCache();
//			setPackLeafPrtNameAll(packLeafPrtNameList);
//		}
//		return packLeafPrtNameList;
//	}
//	public void setPackLeafPrtNameAll() {
//		setPackLeafPrtNameAll(prtpackService.queryPrtLeafPackNameToCache());
//		
//	}
//	public void setPackLeafPrtNameAll(List<Map<String, Object>> packLeafPrtNameList)
//	{
//		if(!memCachedClient.set(SysConstant.PACK_LEAF_PRT_NAME_ALL, packLeafPrtNameList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("印刷品基础套餐名称存入memcached失败");
//		}
//	}
//
//	/////////////////////////////////////////////////////////////////////////////////////////////
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackAdvNameAll() {
//
//		List<Map<String, Object>> packAdvNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_ADV_NAME_ALL);
//		if( packAdvNameList == null )
//		{
//			packAdvNameList = advPackService.queryAdvPackNameToCache();
//			setPackAdvNameAll(packAdvNameList);
//		}
//		return packAdvNameList;
//	}
//	
//	public void setPackAdvNameAll(List<Map<String, Object>> PackAdvNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_ADV_NAME_ALL, PackAdvNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("广告套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackAdvNameAll()
//	{
//		setPackAdvNameAll(advPackService.queryAdvPackNameToCache());
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackAdvAll()
//	{
//		List<Map<String, Object>> packAdvList = (List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_ADV_ALL);
//		if( packAdvList == null )
//		{
//			packAdvList = advPackService.queryAdvPackToCache();
//			setPackAdvAll(packAdvList);
//		}
//		return packAdvList;
//	}
//	
//	public void setPackAdvAll(List<Map<String, Object>> PackAdvList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_ADV_ALL, PackAdvList,
//			new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("广告套餐树存入memcached失败");
//		}
//	}
//	
//	public void setPackAdvAll()
//	{
//		setPackAdvAll(advPackService.queryAdvPackToCache());
//	}
//	@SuppressWarnings("unchecked")
//	public List<Map<String, Object>> getPackLeafAdvNameAll() {
//		
//		List<Map<String, Object>> packAdvNameList = 
//			(List<Map<String, Object>>) memCachedClient.get(SysConstant.PACK_LEAF_ADV_NAME_ALL);
//		if( packAdvNameList == null )
//		{
//			packAdvNameList = advPackService.queryAdvLeafPackNameToCache();
//			setPackLeafAdvNameAll(packAdvNameList);
//		}
//		return packAdvNameList;
//	}
//	
//	public void setPackLeafAdvNameAll(List<Map<String, Object>> PackLeafAdvNameList)
//	{
//		if( ! memCachedClient.set(SysConstant.PACK_LEAF_ADV_NAME_ALL, PackLeafAdvNameList,
//				new Date(System.currentTimeMillis() + STORE_TIME)))
//		{
//			log.error("广告基础套餐名称存入memcached失败");
//		}
//	}
//	
//	public void setPackLeafAdvNameAll()
//	{
//		setPackLeafAdvNameAll(advPackService.queryAdvLeafPackNameToCache());
//	}

//	public String getAccessToken() throws Exception {
//      //TODO Auto-generated method stub
//		List<LzWeiAccesstoken> accessToken = (List<LzWeiAccesstoken>)memCachedClient.get(SysConstant.ACCESS_TOKEN);
//		if(accessToken == null&&(!weixinservice.testAccessToken(accessToken)))
//		{
//			accessToken = weixinservice.getAccessToken();
//			setAccessToken(accessToken);
//		}
//		return accessToken;
//	}

//	public void setAccessToken(String accessToken) {
//		// TODO Auto-generated method stub
//		if(!memCachedClient.set(SysConstant.ACCESS_TOKEN, accessToken, new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000)))
//		{
//			log.error("微信服务号存入memcached失败");
//		}
//	}

//	public Date getAccessTokenExpireDate() {
//		// TODO Auto-generated method stub
//		Date expireDate = (Date)memCachedClient.get(SysConstant.ACCESSTOKEN_EXPIRE);
//		if(expireDate==null)
//		{
//			expireDate = weixinservice.getExpireDate();
//			setAccessTokenExpireDate(expireDate);
//		}
//		
//		return expireDate;
//	}
//	
//	public void setAccessTokenExpireDate(Date expireDate) {
//		// TODO Auto-generated method stub
//		if(!memCachedClient.set(SysConstant.ACCESSTOKEN_EXPIRE, expireDate, new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000)))
//		{
//			log.error("accessToken最新过期时间存入memcached失败");
//		}
//	}
}















