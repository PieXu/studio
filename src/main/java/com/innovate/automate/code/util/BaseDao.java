package com.innovate.automate.code.util;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

/**
 * 通用底层的DAO接口
 * @author IvanHsu
 * @2018年6月23日 上午11:03:44
 * @param <T>
 */
public interface BaseDao<T> 
{

	/**
	 * 通过ID查询
	 * 
	 * @param id
	 * @return
	 */
	 T selectById(Serializable id);

	/**
	 * 查询单条记录
	 * 
	 * @param entity
	 * @return
	 */
	T selectOne(@Param("item") T obj);

	/**
	 * 查询记录集合
	 * 
	 * @param entity
	 * @return
	 */
	List<?> selectList(@Param("item") T obj);

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	Page<T> selectPage(@Param("item") T obj);

	/**
	 * 通用的保存方法
	 * 
	 * @param <T>
	 * @param entity
	 */
	void save(@Param("item") T obj);

	/**
	 * 批量保存
	 * 
	 * @param list
	 */
	int batchSave(List<?> list);

	/**
	 * 通用的修改方法
	 * 
	 * @param <T>
	 * @param entity
	 */
	int update(@Param("item") T obj);

	/**
	 * 批量更新
	 * 
	 * @param list
	 * @return
	 */
	int batchUpdate(List<?> list);

	/**
	 * 删除方法
	 * 
	 * @param id
	 */
	int delById(Serializable id);

	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	int delList(List<?> list);

	/**
	 * 批量删除方法
	 * @param ids
	 */
	int delArray(int[] ids);

	/**
	 * 统计查询
	 * @param <T>
	 * @param params 查询参数
	 * @return 总记录条数
	 */
	int count(T obj);
}
