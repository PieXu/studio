package com.innovate.bizz.goods.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.bizz.goods.model.Goods;

public interface IGoodsService extends IBaseService {

	/**
	 * 按条件查找
	 * 
	 * @param breaing
	 * @return
	 */
	public Page<Goods> listGoods(Goods goods);

	/**
	 * 按主键删除
	 * 
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 按主键查找
	 * 
	 * @param id
	 * @return
	 */
	public Goods getById(String id);

	/**
	 * 更新
	 * 
	 * @param breaing
	 */
	public void updateGoods(Goods goods, String[] fileId);

	/**
	 * 保存
	 * 
	 * @param breaing
	 */
	public void saveGoods(Goods goods, String[] fileId);

}
