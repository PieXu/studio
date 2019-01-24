package com.innovate.sys.dic.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.innovate.sys.dic.model.Dic;

/**
 * @desc:
 * @time: 2017年6月20日 下午3:58:25
 * @author IvanHsu
 */
public interface IDicService extends DicUtil{

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
	 * List<Dic>
	 * 2017年6月20日
	 */
	public Page<Dic> listDicByCondition(Dic dic);

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
