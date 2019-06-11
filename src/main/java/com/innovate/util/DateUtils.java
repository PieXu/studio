/**
 * @name:DateUtil.java
 * @time: 2017年7月10日 上午10:53:56
 * @author IvanHsu 
 */
package com.innovate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @time: 2017年7月10日 上午10:53:56
 * @author IvanHsu
 */
public class DateUtils {

	/**
	 * 按照设置格式 获取日期 字符串
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String getCustomDateString(Date date, String datePattern) {
		SimpleDateFormat parseTime = new SimpleDateFormat(datePattern);
		String sdate = parseTime.format(date);
		return sdate;
	}
	
	/**
	 * 
	* <p>Title: praseDate</p>
	* <p>Description: </p>
	* @param dateStr
	* @param datePattern
	* @return
	 */
	public static Date praseDate(String dateStr,String datePattern)
	{
		if(StringUtils.isNotBlank(dateStr)){
			SimpleDateFormat sdf = null;
			if(null!=datePattern && StringUtils.isNotBlank(datePattern)){
				sdf = new SimpleDateFormat(datePattern);
			}else{
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
