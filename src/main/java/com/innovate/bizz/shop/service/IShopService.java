package com.innovate.bizz.shop.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.bizz.shop.model.Shop;

public interface IShopService extends IBaseService<Shop> {

	/**
	 * 按条件查找
	 * 
	 * @param breaing
	 * @return
	 */
	public Page<Shop> listShop(Shop shop);

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
	public Shop getById(String id);

	/**
	 * 更新
	 * 
	 * @param breaing
	 */
	public void updateShop(Shop shop, String[] fileId);

	/**
	 * 保存
	 * 
	 * @param breaing
	 */
	public void saveShop(Shop shop, String[] fileId);
}
