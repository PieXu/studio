package com.innovate.work.product.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.work.product.model.Bearing;

public interface IBreaingService extends IBaseService{

	/**
	 * 按条件查找
	 * @param breaing
	 * @return
	 */
	public Page<Bearing> listBreaing(Bearing breaing);

	/**
	 * 按主键删除
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	public Bearing getById(String id);

	/**
	 * 更新
	 * @param breaing
	 */
	public void updateBreaing(Bearing breaing,String[] fileId);

	/**
	 * 保存
	 * @param breaing
	 */
	public void saveBreaing(Bearing breaing,String[] fileId);

}
