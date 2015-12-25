package com.wxcrm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil 
{
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 8:49:29 PM
	 * @param year
	 * @param month
	 * @return
	 * 获得月末日期
	 */
	public static Date getMonthLastDate(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		
		return calendar.getTime();
	}
	
	/**
	 * @author wanglei
	 * created on Jul 26, 2012 8:32:29 PM
	 * @param date
	 * @param days
	 * @return
	 * 计算某日期的前/后几天的日期
	 */
	public static Date getDateCompute(Date date, int days)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		
		return calendar.getTime();
	}
	
	/**
	 * @author wanglei
	 * created on Mar 31, 2014 8:47:46 AM
	 * @param date
	 * @return
	 * 获得某日期所在周的周一日期
	 */
	public static Date getMonday(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateCompute(date, -1));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return calendar.getTime();
	}
	
	/**
	 * @author wanglei
	 * created on Mar 31, 2014 8:47:46 AM
	 * @param date
	 * @return
	 * 获得某日期所在周的周日日期
	 */
	public static Date getSunday(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDateCompute(date, 6));
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		return calendar.getTime();
	}
	
	/**
	 * @author wanglei
	 * created on Sep 27, 2013 1:52:53 PM
	 * @param curMonth yyyy-MM
	 * @param months
	 * @return
	 * 计算某月份的前/后几月
	 * @throws ParseException 
	 */
	public static String getMonthCompute(String curMonth, int months) throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(curMonth, "yyyy-MM"));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		
		return parseString(calendar.getTime(), "yyyy-MM");
	}
	
	/**
	 * @author wanglei
	 * created on Jan 18, 2011 9:10:18 AM
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 * 获得两个日期间的天数
	 */
	public static long getDaysBetweenDate(Date dateFrom, Date dateTo)
	{
		int per = 24 * 60 * 60 * 1000;
		
		return ( dateTo.getTime() - dateFrom.getTime() ) / per;
	}
	
	/**
	 * @author wanglei
	 * created on Jan 17, 2011 7:47:33 PM
	 * @param date
	 * @return
	 * 把形似日期的字符串转化日期类
	 * @throws ParseException 
	 */
	public static Date parseDate(String date, String pattern) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		
		return dateFormat.parse(date);
	}
	
	/**
	 * @author wanglei
	 * created on Jan 17, 2011 7:42:03 PM
	 * @param date
	 * @param pattern 转化的表达式格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 * 把日期类按表达式转化成对应的格式
	 */
	public static String parseString(Date date, String pattern)
	{
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		
		return dateFormat.format(date);
	}
	
	public static Date getTimeCompute(Date date,int len)
	{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println("计算前："+dateFormat.format(date));
		long time = date.getTime()+(len*60*60*1000);
		date.setTime(time);
		//System.out.println("计算后："+dateFormat.format(date));
		//System.out.println("----------------------------------------------");
		return date;
	}
	
}
