package com.innovate.sys.dic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.sys.dic.model.DicCategory;

/**
 * @desc:数据字典分类
 * @package:com.xu.sys.dao
 * @time: 2017年7月5日 下午5:56:17
 * @author IvanHsu 
 */
@Repository("sys.DicCategoryDao")
public interface DicCategoryDao {
	
	/**
	 * 分页查询列表
	 * @param dicCategory
	 * @return
	 * Page<DicCategory>
	 * 2017年7月5日
	 */
	public Page<DicCategory> pageDicCategory(DicCategory dicCategory); 
	
	/**
	 * 保存信息
	 * @param dicCategory
	 * void
	 * 2017年7月5日
	 */
	public void saveDicCategory(DicCategory dicCategory);
	
	/**
	 * 更新操作
	 * @param dicCategory
	 * void
	 * 2017年7月5日
	 */
	public void updateDicCategory(DicCategory dicCategory);
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 * DicCategory
	 * 2017年7月5日
	 */
	public DicCategory getById(String id);

	/**
	 * 查询所有的分类信息
	 * @return
	 * List<DicCategory>
	 * 2017年7月6日
	 */
	public List<DicCategory> listCategoryAll();
	
}
