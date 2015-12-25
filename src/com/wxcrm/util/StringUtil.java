package com.wxcrm.util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class StringUtil 
{
	public static String[] delRepeat(String[] ary)
	{
		Set<String> set = new HashSet<String>();
		for( String str : ary )
		{
			set.add(str);
		}
		return set.toArray(new String[0]);
	}
	
	public static List<Object[]> getObjAryList(String str, String[] strAry)
	{
		if( str == null || strAry == null )
		{
			return null;
		}
		
		List<Object[]> list = new ArrayList<Object[]>();
		for( String s : strAry )
		{
			list.add(new Object[]{str, s});
		}
		
		return list;
	}
	
	public static List<Object[]> getObjAryList(String str, String type, String[] strAry, String startTime, String endTime)
	{
		if( str == null || type == null || strAry == null )
		{
			return null;
		}
		
		List<Object[]> list = new ArrayList<Object[]>();
		for( int i = 0; i < strAry.length; i++ )
		{
			list.add(new Object[]{str, type, strAry[i], startTime, endTime});
		}
		
		return list;
	}
	
	public static List<Object[]> getObjAryList(String[] strAry, String str)
	{
		if( strAry == null || str == null )
		{
			return null;
		}
		
		List<Object[]> list = new ArrayList<Object[]>();
		for( String s : strAry )
		{
			list.add(new Object[]{s, str});
		}
		
		return list;
	}
	
	/**
	 * @author wanglei
	 * created on Mar 16, 2011 11:23:55 PM
	 * @param money
	 * @return
	 * ÈËÃñ±Ò×ª»»´óÐ´
	 */
	public static String parseRMB(double money)
	{
		char[] s1 = {'Áã', 'Ò¼', '·¡', 'Èþ', 'ËÁ', 'Îé', 'Â½', 'Æâ', '°Æ', '¾Á'};
		char[] s4 = {'·Ö', '½Ç', 'Ôª', 'Ê°', '°Û', 'Çª', 'Íò', 'Ê°', '°Û', 'Çª', 'ÒÚ', 'Ê°', '°Û', 'Çª', 'Íò'};
		
		String str = String.valueOf(Math.round(money * 100 + 0.00001));
		int len = str.length();
		String result = "";
		int n = 0;
		for( int i = 0; i < len; i++ )
		{
			n = str.charAt(len - 1 - i) - '0';
			result = s1[n] + "" + s4[i] + result;
		}

		result = result.replaceAll("ÁãÇª", "Áã");
		result = result.replaceAll("Áã°Û", "Áã");
		result = result.replaceAll("ÁãÊ°", "Áã");
		result = result.replaceAll("ÁãÒÚ", "ÒÚ");
		result = result.replaceAll("ÁãÍò", "Íò");
		result = result.replaceAll("ÁãÔª", "Ôª");
		result = result.replaceAll("Áã½Ç", "Áã");
		result = result.replaceAll("Áã·Ö", "Áã");

		result = result.replaceAll("ÁãÁã", "Áã");
		result = result.replaceAll("ÁãÒÚ", "ÒÚ");
		result = result.replaceAll("ÁãÁã", "Áã");
		result = result.replaceAll("ÁãÍò", "Íò");
		result = result.replaceAll("ÁãÁã", "Áã");
		result = result.replaceAll("ÁãÔª", "Ôª");
		result = result.replaceAll("ÒÚÍò", "ÒÚ");

		result = result.replaceAll("Áã$", "");
		result = result.replaceAll("Ôª$", "ÔªÕû");

		return result;
	}
	
	public static String getIp(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
	    if(ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	    {
	    	ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	    {
	    	ip=request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip==null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
	    {
	    	ip = request.getRemoteAddr();
	    }

	    return ip;
	}
	
	public static String formPost(String action, Object object) throws IllegalArgumentException, IllegalAccessException 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<form action=\"" + action + "\" method=\"post\">");
		Field[] fields = object.getClass().getDeclaredFields();
		String fieldName = null;
		String fieldValue = null;
		for( Field field : fields )
		{
			fieldName = field.getName();
			if( fieldName.endsWith("_Q") || fieldName.equals("currentPage") || fieldName.equals("pageSize") )
			{
				field.setAccessible(true);
				if( field.get(object) != null )
				{
					if( field.getType().getSimpleName().endsWith("[]") )
					{
						fieldValue = StringUtils.arrayToCommaDelimitedString((Object[]) field.get(object));
					}
					else 
					{
						fieldValue = field.get(object).toString();
					}
					builder.append("<input id=\"" + fieldName + "\" name=\"" + fieldName + "\" type=\"hidden\" value=\"" 
							+ fieldValue.replace("'", "&#39;").replace("\"", "&quot;") + "\"/>");
				}
			}
		}
		builder.append("</form>");
		return builder.toString();
	}
	
	public static String formGet(String action)
	{
		return "<form action=\"" + action + "\" method=\"get\"></form>";
	}
	
	public static void copyProperties(Object from, Object to) throws IllegalArgumentException, IllegalAccessException
	{
		Field[] fields = from.getClass().getDeclaredFields();
		String fieldName = null;
		for( Field field : fields )
		{
			fieldName = field.getName();
			if( fieldName.endsWith("_Q") || fieldName.equals("currentPage") || fieldName.equals("pageSize") )
			{
				field.setAccessible(true);
				field.set(to, field.get(from));
			}
		}
	}
	
	public static long ipToLong(String strIp) 
	{
        long[] ip = new long[4];
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3+1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

	public static String longToIP(long longIp)
	{
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }
	
	
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(170)|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(mobiles);  
		return m.matches();  
	}  
	
	public static String getJvmMemory()
	{
		StringBuilder builderStr = new StringBuilder("JVM MAX MEMORY: " + Runtime.getRuntime().maxMemory()/1024/1024+"M"+"\n");
		builderStr.append("JVM IS USING MEMORY:" + Runtime.getRuntime().totalMemory()/1024/1024+"M"+"\n");
		builderStr.append("JVM IS FREE MEMORY:" + Runtime.getRuntime().freeMemory()/1024/1024+"M");
		return builderStr.toString();
	}
	
	
	
	
	public static void main(String[] args)
	{
		String aString = "14322333486";
		
		System.out.println(StringUtil.isMobileNO(aString));
		
	}

}



























