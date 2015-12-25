package com.wxcrm.util;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;



public class FileUtil 
{
	/**
	 * @author wanglei
	 * created on Jan 24, 2011 5:05:24 PM
	 * @param filePath 属性文件的相对路径 /com/skywing/../*.properties
	 * @param key
	 * @return
	 * @throws IOException
	 * 从属性文件中读取key对应的value值
	 */
	public static String getPropValue(String filePath, String key) throws IOException
	{
		Properties p = new Properties();
		p.load(FileUtil.class.getResourceAsStream(filePath));
		
		return p.getProperty(key);
	}
	
	public static String getUUIDNameString()
	{
		//产生一个ＵＵＩＤ的文件名
		return UUID.randomUUID().toString();
	}
	

}
