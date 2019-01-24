package com.innovate.automate.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author IvanHsu
 * @E-Mail xupai_911@163.com 
 * @2013-12-27下午5:11:07
 */
public class DBTypeJavaTypeMap
{

	public static Map<Integer, String> getColumnTypeMap()
	{
		Map<Integer, String> hm = new HashMap<Integer, String>();

		hm.put(java.sql.Types.BIGINT, "BIGINT");
		hm.put(java.sql.Types.DECIMAL, "DECIMAL");
		hm.put(java.sql.Types.INTEGER, "INTEGER");
		hm.put(java.sql.Types.TIMESTAMP, "Timestamp");
		hm.put(java.sql.Types.VARCHAR, "VARCHAR");
		hm.put(java.sql.Types.DATE, "DATE");
		hm.put(java.sql.Types.FLOAT, "FLOAT");
		hm.put(java.sql.Types.DOUBLE, "DOUBLE");
		hm.put(java.sql.Types.CHAR, "CHAR");
		hm.put(java.sql.Types.LONGVARCHAR, "LONGVARCHAR");
		hm.put(java.sql.Types.REAL, "REAL");

		hm.put(java.sql.Types.TIME, "TIME");
		hm.put(java.sql.Types.BIT, "BIT");
		hm.put(java.sql.Types.BOOLEAN, "BOOLEAN");
		hm.put(java.sql.Types.SMALLINT, "SMALLINT");
		hm.put(java.sql.Types.TINYINT, "TINYINT");

		return hm;
	}

	public static Map<Integer, String> getColumnDefTypeMap()
	{
		Map<Integer, String> hm = new HashMap<Integer, String>();

		hm.put(java.sql.Types.BIGINT, "Long");
		hm.put(java.sql.Types.DECIMAL, "java.math.BigDecimal");
		hm.put(java.sql.Types.INTEGER, "Integer");
		hm.put(java.sql.Types.TIMESTAMP, "java.sql.Timestamp");
		hm.put(java.sql.Types.VARCHAR, "String");
		hm.put(java.sql.Types.DATE, "java.sql.Timestamp");
		hm.put(java.sql.Types.FLOAT, "Float");
		hm.put(java.sql.Types.DOUBLE, "Double");
		hm.put(java.sql.Types.CHAR, "String");
		hm.put(java.sql.Types.LONGVARCHAR, "String");
		hm.put(java.sql.Types.REAL, "Float");
		hm.put(java.sql.Types.TIME, "java.sql.Time");
		hm.put(java.sql.Types.BIT, "Boolean");
		hm.put(java.sql.Types.BOOLEAN, "Boolean");
		hm.put(java.sql.Types.SMALLINT, "Integer");
		hm.put(java.sql.Types.TINYINT, "Integer");
		return hm;
	}

}
