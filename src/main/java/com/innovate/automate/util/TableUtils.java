package com.innovate.automate.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.innovate.automate.code.model.CodeGenSetting;
import com.innovate.automate.code.model.ColumnInfo;
import com.innovate.automate.code.model.TableInfo;
import com.innovate.automate.code.util.CodeGenUtils;
import com.innovate.util.LoggerUtils;

/**
 * 
 * @author IvanHsu
 * @2018年4月16日 上午10:51:35
 */
public class TableUtils {

	/**
	 * 获取连接 及元数据
	* <p>Title: getConnectMetaData</p>
	* <p>Description: </p>
	* @param dbDriver
	* @param dbUrl
	* @param dbUser
	* @param dbPass
	* @return
	 */
	public static DatabaseMetaData getConnectMetaData()
	{
		try {
			CodeGenSetting genSet = CodeGenUtils.getCodeSetting(); 
			Class.forName(genSet.getDbDriver());
			Properties connectionProps = new Properties();
			connectionProps.put("remarks", "true");
	        connectionProps.put("useInformationSchema", "true");
	        connectionProps.put("user", genSet.getDbUser());
	        connectionProps.put("password",  genSet.getDbPass());
	        Connection conn = DriverManager.getConnection(genSet.getDbUrl(), connectionProps);
//			Connection conn = DriverManager.getConnection( genSet.getDbUrl(), genSet.getDbUser(), genSet.getDbPass(),connectionProps);
			return conn.getMetaData();
		}catch (Exception e) {
			LoggerUtils.error(TableUtils.class, "数据库连接失败，请查看配置...");
		}
		return null;
	}
	
	/**
	 * 获取数据库的表结构元数据
	 * 
	 * @param genSet
	 * @return
	 */
	public static List<TableInfo> getTables() {
		List<TableInfo> tables = new ArrayList<TableInfo>();
		try {
			DatabaseMetaData dm = getConnectMetaData();
			ResultSet rs = dm.getTables(null, null, null, new String[] { "Table" ,"View" });
			while (rs.next()) {
				TableInfo table = new TableInfo();
				table.setTableCat(rs.getString("TABLE_CAT"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setTableType(rs.getString("TABLE_TYPE"));
				table.setRemarks(rs.getString("REMARKS"));
				table.setTableSchem(rs.getString("TABLE_SCHEM"));
				if(!rs.getString("TABLE_NAME").toLowerCase().startsWith("t_")){// 过滤一下系统的表
					tables.add(table);
				}
			}
		} catch (Exception e) {
			LoggerUtils.error(TableUtils.class, e.getMessage());
		} 
		return tables;
	}
	
	/**
	 * 
	* <p>Title: getColumns</p>
	* <p>Description: </p>
	* @param dm
	* @param tableName
	* @return
	 */
	public static List<ColumnInfo> getColumns(String tableName,String[] unExpected ) {
		List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
		try {
			DatabaseMetaData dbMetaData = getConnectMetaData();
			ResultSet rsColimns = dbMetaData.getColumns(null, "%",tableName, "%");
			ColumnInfo column = null ;
			while (rsColimns.next()) {
				if(!ArrayUtils.contains(unExpected, rsColimns.getString("COLUMN_NAME"))){
					column = new ColumnInfo();
					column.setColumnName(rsColimns.getString("COLUMN_NAME"));
					column.setColumnCommnet(rsColimns.getString("REMARKS"));
					column.setAttributeName(TableUtils.columnNameTransformAttributeName(rsColimns.getString("COLUMN_NAME")));
					column.setDbType(new Integer(rsColimns.getString("DATA_TYPE")));
					column.setTypeName(rsColimns.getString("TYPE_NAME"));
					column.setTabelName(rsColimns.getString("TABLE_NAME"));
					column.setTableAlias(TableUtils.columnNameTransformAttributeName(rsColimns.getString("TABLE_NAME")));
					columnList.add(column);
				}
			}
		} catch (Exception e) {
			LoggerUtils.error(TableUtils.class, e.getMessage());
		} 
		return columnList;
	}
	
	/**
	 * 
	* <p>Title: getColumns</p>
	* <p>Description: 合集 </p>
	* @param tableNames
	* @return
	 */
	public static List<ColumnInfo> getColumns(String[] tableNames,String[] unExpected ) {
		List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
		if(!ArrayUtils.isEmpty(tableNames)){
			for(String tableName : tableNames)
			{
				if(StringUtils.isNotBlank(tableName)){
					columnList.addAll(getColumns(tableName,unExpected));
				}
			}
		}
		return columnList;
	}

	public static String tableNameTransformClass(String tableName) {
		String tableNameToken[] = tableName.split("_");
		StringBuffer className = new StringBuffer();
		for (String nameToken : tableNameToken) {
			className.append(firstToUpper(nameToken));
		}
		return className.toString();
	}

	public static String columnNameTransformAttributeName(String columnName) {
		String columnNameToken[] = columnName.split("_");
		StringBuffer columnNameBuf = new StringBuffer();
		for (int i = 0; i < columnNameToken.length; i++) {
			if (i == 0) {
				columnNameBuf.append(firstToLower(columnNameToken[i].toLowerCase()));
			} else {
				columnNameBuf.append(firstToUpper(columnNameToken[i].toLowerCase()));
			}
		}
		return columnNameBuf.toString();
	}

	// 首字母大写
	public static String firstToUpper(String name) {
		String newName = name.substring(0, 1).toUpperCase() + name.substring(1);
		return newName;
	}

	// 首字母小写
	public static String firstToLower(String name) {
		String newName = name.substring(0, 1).toLowerCase() + name.substring(1);
		return newName;
	}

	/**
	 * 构建包目录
	 * 
	 * @param parentPackageName
	 * @param tableName
	 * @return
	 */
	public static String buildPackageMD(String parentPackageName, String tableName) {
		String tableNameToken[] = tableName.split("_");
		StringBuffer classPackage = new StringBuffer();
		for (String nameToken : tableNameToken) {
			classPackage.append(nameToken.toLowerCase());
		}
		return (parentPackageName + "." + classPackage.toString().toLowerCase());
	}

	public static String tableNameToLower(String tableName) {
		String tableNameToken[] = tableName.split("_");
		StringBuffer classPackage = new StringBuffer();
		for (String nameToken : tableNameToken) {
			classPackage.append(nameToken.toLowerCase());
		}
		return classPackage.toString().toLowerCase();
	}

	/**
	 * 包名转目录
	 * 
	 * @param packageName
	 */
	public static String packageNameTransFormMD(String packageName, String level) {
		StringBuffer mdName = new StringBuffer();

		String packageNameToken[] = packageName.split("\\.");
		for (String nameToken : packageNameToken) {
			mdName.append(File.separator + nameToken);
		}
		mdName.append(File.separator + level);
		return mdName.toString();
	}
	
	/**
	 * 
	 * @param rs
	 * @param colCommentMap
	 * @return
	 */
//	public static List<ColumnInfo> getMetaData1(ResultSet rs, Map<String, String> colCommentMap)
//	{
//		List<ColumnInfo> metas = new ArrayList<ColumnInfo>();
//		ResultSetMetaData metaData;
//		try
//		{
//			metaData = rs.getMetaData();
//			int colCount = metaData.getColumnCount();
//			ColumnInfo typeMapVO = null ;
//			for (int i = 1; i <= colCount; i++)
//			{
//				typeMapVO = new ColumnInfo();
//				typeMapVO.setDataColumnName(metaData.getColumnName(i));
//				typeMapVO.setColumnCommnet(colCommentMap.get(metaData.getColumnName(i)));
//				typeMapVO.setColumnName(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i)));
//				typeMapVO.setFirstColumnName(TableUtils.firstToUpper(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i))));
//				typeMapVO.setDataBaseType(new Integer(metaData.getColumnType(i)));
//				typeMapVO.setJavaDefType(DBTypeJavaTypeMap.getColumnDefTypeMap().get( new Integer(metaData.getColumnType(i))));
//				typeMapVO.setJavaType(DBTypeJavaTypeMap.getColumnTypeMap().get(new Integer(metaData.getColumnType(i))));
//				//判断是否主键  主要根据 字段 是否自动增长方面做判断  ， 如有meteData字段 可以直接替代 ，目前针对mysql无找到 
//				if(metaData.isAutoIncrement(i))typeMapVO.setPrimaryKey("PRI");
//				metas.add(typeMapVO);
//			}
//		}
//		catch (SQLException e){
//			LoggerUtils.error(TableUtils.class, e.getMessage());
//		}
//		return metas;
//	}
	
	/**
	 * 
	 * @param rs
	 * @param colCommentMap
	 * @return
	 */
//	public static List<ColumnInfo> getMetaDataByList(ResultSet rs, List<MetaData> metaColumeList)
//	{
//		List<ColumnInfo> metas = new ArrayList<ColumnInfo>();
//		ResultSetMetaData metaData;
//		try
//		{
//			metaData = rs.getMetaData();
//			int colCount = metaData.getColumnCount();
//			ColumnInfo typeMapVO = null ;
//			MetaData metaVo = null;
//			for (int i = 1; i <= colCount; i++)
//			{
//				typeMapVO = new ColumnInfo();
//				metaVo = metaColumeList.get(i-1);
//				typeMapVO.setDataColumnName(metaData.getColumnName(i));
//				typeMapVO.setColumnCommnet(metaVo.getMetaColumnCommnet());
//				typeMapVO.setColumnName(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i)));
//				typeMapVO.setFirstColumnName(TableUtils.firstToUpper(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i))));
//				typeMapVO.setDataBaseType(new Integer(metaData.getColumnType(i)));
//				typeMapVO.setJavaDefType(DBTypeJavaTypeMap.getColumnDefTypeMap().get( new Integer(metaData.getColumnType(i))));
//				typeMapVO.setJavaType(DBTypeJavaTypeMap.getColumnTypeMap().get(new Integer(metaData.getColumnType(i))));
//				typeMapVO.setPrimaryKey(metaVo.getMetaColumnKey());
//				metas.add(typeMapVO);
//			}
//		}
//		catch (SQLException e){
//			LoggerUtils.error(TableUtils.class, e.getMessage());
//		}
//		return metas;
//	}
	
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
