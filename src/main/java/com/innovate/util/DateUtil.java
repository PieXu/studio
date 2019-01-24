/**
 * @name:DateUtil.java
 * @time: 2017年7月10日 上午10:53:56
 * @author IvanHsu 
 */
package com.innovate.util;

import java.text.SimpleDateFormat;

/**
 * @time: 2017年7月10日 上午10:53:56
 * @author IvanHsu
 */
public class DateUtil {
	
	public static String getCustomDateString(java.util.Date date, String datePattern)
	{
		SimpleDateFormat parseTime = new SimpleDateFormat(datePattern);
		String sdate = parseTime.format(date);
		return sdate;
	}

}
