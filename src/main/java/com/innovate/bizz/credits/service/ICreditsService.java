package com.innovate.bizz.credits.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.bizz.credits.model.CreditsExchange;
import com.innovate.bizz.credits.model.CreditsProduct;

public interface ICreditsService extends IBaseService{

	/**
	 * 按条件查找
	 * 
	 * @param breaing
	 * @return
	 */
	public Page<CreditsProduct> listCreditsProduct(CreditsProduct product);

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
	public CreditsProduct getById(String id);

	/**
	 * 更新
	 * 
	 * @param breaing
	 */
	public void updateCreditsProduct(CreditsProduct product, String[] fileId);

	/**
	 * 保存
	 * 
	 * @param breaing
	 */
	public void saveCreditsProduct(CreditsProduct product, String[] fileId);
	
	
	/**
	 * 按条件查找
	 * 
	 * @param breaing
	 * @return
	 */
	public Page<CreditsExchange> listCreditsExchange(CreditsExchange exchage);
	
	/**
	 * 更新
	 * 
	 * @param breaing
	 */
	public void updateCreditsExchange(CreditsExchange exchage);

	/**
	 * 保存
	 * 
	 * @param breaing
	 */
	public void saveCreditsExchange(CreditsExchange exchage);

	



	
}
