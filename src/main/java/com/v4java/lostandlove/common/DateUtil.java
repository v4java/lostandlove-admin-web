package com.v4java.lostandlove.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public static String datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	public static String timeFormat = "yyyyMMddHHmmss";
	public static String dateFormat = "yyyy-MM-dd";
	public static String simpleFormat = "yyyyMMdd";
	public static String _FORMAT4_ = "yyyy.MM";
	public static String _FORMAT5_ = "yyyyMM";
	
	/**
	 * 获取两个日期之间所有的月份集合
	 */
	public static List<String> getMonthBetween(String minDate, String maxDate)
			throws Exception {
		List<String> result = new ArrayList<String>();
		Calendar min = Calendar.getInstance();
		min.setTime(DateUtil.str2Date(minDate));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		Calendar max = Calendar.getInstance();
		max.setTime(DateUtil.str2Date(maxDate));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		Calendar current = max;
		while (current.after(min)) {
			result.add(DateUtil.dateToString(current.getTime(), DateUtil._FORMAT4_));
			current.add(Calendar.MONTH, -1);
		}
		return result;
	}
	
	/**
	 * 获取当天的开始时间
	 */
	public static Date getStartDate(){
		Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0); 
        return todayStart.getTime();
	}
	
	/**
	 * 获取指定日期的开始时间
	 * @throws ParseException 
	 */
	public static Date getStartDateByTime(String formatTime,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(formatTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);  
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		calendar.set(Calendar.MILLISECOND, 0); 
        return calendar.getTime();
	}
	/**
	 * 获取指定日期的结束时间
	 */
	public static Date getEndDateByTime(String formatTime,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(formatTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);  
		calendar.set(Calendar.HOUR_OF_DAY, 23);  
		calendar.set(Calendar.MINUTE, 59);  
		calendar.set(Calendar.SECOND, 59);  
		calendar.set(Calendar.MILLISECOND, 999); 
        return calendar.getTime();
	}
	
	
	public static void main(String[] args) throws Exception {
		Date s = DateUtil.getFirstDayOfMonth(new Date());
		Date e = DateUtil.getLastDayOfMonth(new Date());
		
		
		System.out.println(DateUtil.dateToString(s, DateUtil.datetimeFormat));
		System.out.println(DateUtil.dateToString(e, DateUtil.datetimeFormat));
	}
	
	/**
	 * 将字符串转换成yyyy-MM-dd格式日期时间
	 * 
	 * @param date1
	 * @return
	 */
	public static Timestamp str2Date(String str) {
		Timestamp date = null;
		try {
			date = new Timestamp(new SimpleDateFormat(dateFormat).parse(str)
					.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static Date timeToDate(String str) {
		Timestamp date = null;
		try {
			date = new Timestamp(new SimpleDateFormat(timeFormat).parse(str)
					.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static String getTime(Date date) {
		return new SimpleDateFormat(timeFormat).format(date);
	}

	public static String simpleFormat(Date date) {
		return new SimpleDateFormat(simpleFormat).format(date);
	}

	public static String getDate() {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	public static String getTime() {
		return new SimpleDateFormat(timeFormat).format(new Date());
	}

	public static String formatTime() {
		return new SimpleDateFormat(datetimeFormat).format(new Date());
	}

	public static String formatTime(Date date) {
		return new SimpleDateFormat(datetimeFormat).format(date);
	}

	/**
	 * 将日期时间转换成 yyyy-MM-dd HH:mm:ss类型字符串
	 * 
	 * @param date1
	 * @return
	 */
	public static String datetimeToStr(Timestamp datetime) {
		return new SimpleDateFormat(datetimeFormat).format(datetime);
	}

	public static String getEndDatetime(String startDatetime)
			throws ParseException {
		// 通过startDatetime生成当前结束时间
		String endDatetime = startDatetime.substring(0, 13);
		endDatetime += ":59:59";

		return endDatetime;
	}

	public static String getNextStartDatetime(String startDatetime)
			throws ParseException {
		// 通过startDatetime生成下次开始时间
		Calendar calendar = Calendar.getInstance();// 获取系统当前时间
		java.util.Date utilDatetime = new java.util.Date(new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss").parse(startDatetime).getTime());
		calendar.setTime(utilDatetime);

		calendar.add(Calendar.HOUR, +1);// 向后推一个小时
		String nextStartDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(calendar.getTime());

		return nextStartDatetime;
	}

	/**
	 * 日期类型转字符串类型
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String dateToString(Date date, String dateFormat) {
		if (date == null)
			return "";
		try {
			return new SimpleDateFormat(dateFormat).format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 日期类型转字符串类型，默认返回yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static long date2timestamp(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long time = 0l;
		try {
			time = dateFormat.parse(DateUtil.formatTime(date)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static long date2timestamp(String date, String formatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		long time = 0l;
		try {
			time = dateFormat.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static long date2timestampSafe(String date, String formatStr) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
			long time = 0l;
			try {
				time = dateFormat.parse(date).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return time / 1000;
		} catch (Exception e) {
			System.out.println("date格式错误");
			return 0;
		}
	}
	
	
	
	/**
	 * String类型转化为Date类型
	 * @throws ParseException 
	 */
	public static Date string2Date(String time,String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(time);
	}
	/**
	 * 获取指定日期的开始时间
	 * @throws ParseException 
	 */
	public static Date getStartDateByTime(Date d) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);  
		calendar.set(Calendar.HOUR_OF_DAY, 0);  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);  
		calendar.set(Calendar.MILLISECOND, 0); 
        return calendar.getTime();
	}
	/**
	 * 获取指定日期的结束时间
	 */
	public static Date getEndDateByTime(Date d) throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);  
		calendar.set(Calendar.HOUR_OF_DAY, 23);  
		calendar.set(Calendar.MINUTE, 59);  
		calendar.set(Calendar.SECOND, 59);  
		calendar.set(Calendar.MILLISECOND, 999); 
        return calendar.getTime();
	}
	/** 
     * 得到本月第一天的日期 
     * @Methods Name getFirstDayOfMonth 
     * @return Date 
     */  
    public static Date getFirstDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, 1);  
        return cDay.getTime();     
    }     
    /** 
     * 得到本月最后一天的日期 
     * @Methods Name getLastDayOfMonth 
     * @return Date 
     */  
    public static Date getLastDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return cDay.getTime();     
    }  
	public static Date getPushDateByMonth(Date date,int month){
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,month);
		return calendar.getTime();
	}
}
