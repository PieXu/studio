package com.innovate.automate.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.innovate.automate.code.model.ColumnMap;
import com.innovate.automate.code.model.MetaData;
import com.innovate.util.LoggerUtils;

/**
 * @author IvanHsu
 * @E-Mail xupai_911@163.com 2013-12-27下午5:11:28
 */
public class DataMetaUtil
{
	/**
	 * 
	 * @param rs
	 * @param colCommentMap
	 * @return
	 */
	public static List<ColumnMap> getMetaData(ResultSet rs, Map<String, String> colCommentMap)
	{
		List<ColumnMap> metas = new ArrayList<ColumnMap>();
		ResultSetMetaData metaData;
		try
		{
			metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			ColumnMap typeMapVO = null ;
			for (int i = 1; i <= colCount; i++)
			{
				typeMapVO = new ColumnMap();
				
				typeMapVO.setDataColumnName(metaData.getColumnName(i));
				
				typeMapVO.setColumnCommnet(colCommentMap.get(metaData.getColumnName(i)));
				
				typeMapVO.setColumnName(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i)));
				
				typeMapVO.setFirstColumnName(TableUtils.firstToUpper(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i))));
				
				typeMapVO.setDataBaseType(new Integer(metaData.getColumnType(i)));
				
				typeMapVO.setJavaDefType(DBTypeJavaTypeMap.getColumnDefTypeMap().get( new Integer(metaData.getColumnType(i))));
				
				typeMapVO.setJavaType(DBTypeJavaTypeMap.getColumnTypeMap().get(new Integer(metaData.getColumnType(i))));
				
				//判断是否主键  主要根据 字段 是否自动增长方面做判断  ， 如有meteData字段 可以直接替代 ，目前针对mysql无找到 
				if(metaData.isAutoIncrement(i))typeMapVO.setPrimaryKey("PRI");
				
				metas.add(typeMapVO);
			}
		}
		catch (SQLException e){
			LoggerUtils.error(DataMetaUtil.class, e.getMessage());
		}
		return metas;
	}
	
	/**
	 * 
	 * @param rs
	 * @param colCommentMap
	 * @return
	 */
	public static List<ColumnMap> getMetaDataByList(ResultSet rs, List<MetaData> metaColumeList)
	{
		List<ColumnMap> metas = new ArrayList<ColumnMap>();
		ResultSetMetaData metaData;
		try
		{
			metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			ColumnMap typeMapVO = null ;
			MetaData metaVo = null;
			for (int i = 1; i <= colCount; i++)
			{
				typeMapVO = new ColumnMap();
				
				metaVo = metaColumeList.get(i-1);
				
				typeMapVO.setDataColumnName(metaData.getColumnName(i));
				
				typeMapVO.setColumnCommnet(metaVo.getMetaColumnCommnet());
				
				typeMapVO.setColumnName(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i)));
				
				typeMapVO.setFirstColumnName(TableUtils.firstToUpper(TableUtils.columnNameTransformAttributeName(metaData.getColumnName(i))));
				
				typeMapVO.setDataBaseType(new Integer(metaData.getColumnType(i)));
				
				typeMapVO.setJavaDefType(DBTypeJavaTypeMap.getColumnDefTypeMap().get( new Integer(metaData.getColumnType(i))));
				
				typeMapVO.setJavaType(DBTypeJavaTypeMap.getColumnTypeMap().get(new Integer(metaData.getColumnType(i))));
				
				typeMapVO.setPrimaryKey(metaVo.getMetaColumnKey());
				
				metas.add(typeMapVO);
			}
		}
		catch (SQLException e){
			LoggerUtils.error(DataMetaUtil.class, e.getMessage());
		}
		return metas;
	}
	
}
