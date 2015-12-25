package com.wxcrm.util;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;



public class FileUtil 
{
	/**
	 * @author wanglei
	 * created on Jan 24, 2011 5:05:24 PM
	 * @param filePath �����ļ������·�� /com/skywing/../*.properties
	 * @param key
	 * @return
	 * @throws IOException
	 * �������ļ��ж�ȡkey��Ӧ��valueֵ
	 */
	public static String getPropValue(String filePath, String key) throws IOException
	{
		Properties p = new Properties();
		p.load(FileUtil.class.getResourceAsStream(filePath));
		
		return p.getProperty(key);
	}
	
	public static String getUUIDNameString()
	{
		//����һ���գգɣĵ��ļ���
		return UUID.randomUUID().toString();
	}
	

}
