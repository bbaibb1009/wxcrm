package com.wxcrm.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener 
{
	private static Logger log = Logger.getLogger(SessionListener.class);
	
	public void sessionCreated(HttpSessionEvent event) 
	{
		log.info("SESSIONID: " + event.getSession().getId() + " ´´½¨");
	}

	public void sessionDestroyed(HttpSessionEvent event) 
	{
		log.info("SESSIONID: " + event.getSession().getId()+" Ïú»Ù");
	}
}
