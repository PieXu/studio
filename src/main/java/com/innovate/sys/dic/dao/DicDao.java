package com.innovate.sys.dic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.sys.dic.model.Dic;

/**
 * @time: 2017年6月20日 下午4:01:33
 * @author IvanHsu 
 */
@Repository("sys.DicDao")
public interface DicDao {
	
	/**
	 * 查询所有
	 * @return
	 * List<Dic>
	 * 2017年6月20日
	 */
	public List<Dic> listAllDic();
	
	/**
	 * 按照条件查询，之后需要换成分页
	 * @return
	 * Page<Dic>
	 * 2017年6月20日
	 */
	public Page<Dic> listDicByCondition(Dic dic);

	/**
	 * 按照分类查询数据字典集合
	 * @param categoryCode
	 * @return
	 * List<Dic>
	 * 2017年6月20日
	 */
	public List<Dic> getDicList(String categoryCode);

	/**
	 * 查找具体的数据字典项
	 * @param categoryCode
	 * @param code
	 * @return
	 * Dic
	 * 2017年6月20日
	 */
	public Dic getDicInfo(@Param("categoryCode")String categoryCode, @Param("code")String code);

	/**
	 * 删除数据字典，物理删除
	 * @param dicId
	 * @return
	 * Dic
	 * 2017年6月20日
	 */
	public Dic deleteDic(String dicId);

	/**
	 * 保存数据字典信息
	 * @param dic
	 * void
	 * 2017年6月20日
	 */
	public void saveDic(Dic dic);

	/**
	 * 更新数据字典信息
	 * @param dic
	 * void
	 * 2017年6月20日
	 */
	public void updateDic(Dic dic);
	
	/**
	 * 主键查找
	 * @param id
	 * @return
	 * Dic
	 * 2017年6月20日
	 */
	public Dic getDicById(String id);
	
}
