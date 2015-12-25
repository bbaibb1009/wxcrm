package com.wxcrm.util;

import java.text.SimpleDateFormat;

public class ValidateUtil 
{
	public static boolean isInteger(String str)
	{  
		boolean result = true;
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public static boolean isLong(String str)
	{  
		boolean result = true;
		try {
			Long.parseLong(str);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public static boolean isFloat(String str)
	{  
		boolean result = true;
		try {
			Float.parseFloat(str);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	public static boolean isDate(String str)
	{
		return isDate(str, "yyyy-MM-dd");
	}
	
	public static boolean isDate(String str, String pattern)
	{  
		boolean result = true;
		try {
			new SimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 判断是否是国内电话 如:0533-2591111
	 * */
	public static boolean isTel(String str)
	{
		boolean result = false;
		try
		{
			result = str.matches("(\\d{3}|\\d{4})?-?(\\d{7}|\\d{8})");
		}
		catch (Exception e) 
		{
			result = false;
		}
		return result;
	}
}
