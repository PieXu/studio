package com.innovate.sys.resource.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.sys.resource.model.Opt;

/**
 * 菜单操作
 * @author IvanHsu
 * @2018年3月26日 上午10:35:07
 */
public interface IOptService extends IBaseService<Opt>{

	/**
	 * 所有操作列表
	 * 不分页
	 * @return
	 */
	public Page<Opt> getAllOpt(Opt opt);

	/**
	 * 主键查找操作
	 * @param id
	 * @return
	 */
	public Opt getOptById(String id);

	/**
	 * 保存或者更新操作
	 * @param opt
	 */
	public void saveOrUpdateOpt(Opt opt);

	/**
	 * 检查操作是否被引用
	 * @param optIds
	 * @return
	 */
	public int checkDelete(String[] optIds);

	/**
	 * 删除 物理删除
	 * @param optIds
	 */
	public void deleteOpts(String[] optIds);

	/**
	 * 
	 * @param optIds
	 * @return
	 */
	public List<Opt> getOptsByIds(String[] optIds);

}
