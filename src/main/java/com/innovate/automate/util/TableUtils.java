package com.innovate.automate.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.innovate.automate.code.model.TableInfo;
import com.innovate.util.LoggerUtils;

/**
 * 
 * @author IvanHsu
 * @2018年4月16日 上午10:51:35
 */
public class TableUtils {

	/**
	 * 获取数据库的表结构元数据
	 * 
	 * @param genSet
	 * @return
	 */
	public static List<TableInfo> getTables(String dbDriver,String dbUrl,String dbUser, String dbPass) {
		List<TableInfo> tables = new ArrayList<TableInfo>();
		try {
			Class.forName(dbDriver);
			Connection conn = DriverManager.getConnection(dbUrl, dbUser,dbPass);
			DatabaseMetaData dm = conn.getMetaData();
			ResultSet rs = dm.getTables(null, null, null, new String[] { "Table" });
			while (rs.next()) {
				TableInfo table = new TableInfo();
				table.setTableCat(rs.getString("TABLE_CAT"));
				table.setTableName(rs.getString("TABLE_NAME"));
				table.setTableType(rs.getString("TABLE_TYPE"));
				table.setRemarks(rs.getString("REMARKS"));
				table.setTableSchem(rs.getString("TABLE_SCHEM"));
				LoggerUtils.debug(TableUtils.class," getRemarks===>"
						+table.getRemarks()+" : getTableCat===>" 
						+table.getTableCat() +" : getTableSchem===>"
						+table.getTableSchem() +" : getTableType===>"
						+table.getTableType() +" : getTableName===>"
						+table.getTableName()
						);
				tables.add(table);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
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

}
