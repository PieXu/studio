package com.innovate.plat.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;

@Repository
public interface RequestProcessDao {

	/**
	 * 
	* <p>Title: superManagerSelect</p>
	* <p>Description: </p>
	* @param sql
	* @return
	 */
	public Page<LinkedHashMap<String, Object>> superProcessSelect(@Param("params")Map<String, Object> params);

	/**
	* <p>Title: baseUpdate</p>
	* <p>Description: </p>
	* @param params
	 */
	public void updateObject(@Param("params")Map<String, Object> params,@Param("sqlVal") String sqlVal);

	/**
	 * 
	* <p>Title: getObject</p>
	* <p>Description: </p>
	* @param sql
	* @return
	 */
	public LinkedHashMap<String, Object> getObject(@Param("params")Map<String, Object> params);
	
	/**
	 * 
	* Title: saveObject
	* Description: 
	* @param params
	* @param sqlVal
	 */
	public void saveObject(@Param("params")Map<String, Object> params,@Param("sqlVal") String sqlVal);

	/**
	 * 
	* Title: deleteObject
	* Description: 
	* @param sqlVal
	* @param params
	 */
	public void deleteObject(@Param("sqlVal") String sqlVal, @Param("params")Map<String, Object> params);
}
