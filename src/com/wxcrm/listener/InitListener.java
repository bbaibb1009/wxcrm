package com.wxcrm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wxcrm.common.IMemcachedService;
import com.wxcrm.util.SysConstant;

public class InitListener implements ServletContextListener 
{
	private WebApplicationContext applicationContext;
	
	private IMemcachedService memcachedService;
	
	public void contextInitialized(ServletContextEvent event) 
	{
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		memcachedService = (IMemcachedService) applicationContext.getBean("memcachedService");
		memcachedService.init();
		initSysStartupDate(applicationContext);
	}

	public void contextDestroyed(ServletContextEvent event) 
	{
		
	}
	
	public void initSysStartupDate(WebApplicationContext webApplicationContext)
	{
		javax.servlet.ServletContext applicationContext = webApplicationContext.getServletContext();
		applicationContext.setAttribute(SysConstant.SYS_STARTUP,webApplicationContext.getStartupDate());
	}
}
