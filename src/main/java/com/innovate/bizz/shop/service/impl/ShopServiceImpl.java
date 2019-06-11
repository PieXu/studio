package com.innovate.bizz.shop.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.bizz.shop.dao.ShopDao;
import com.innovate.bizz.shop.model.Shop;
import com.innovate.bizz.shop.service.IShopService;
import com.innovate.sys.file.service.IFileService;

@Service
public class ShopServiceImpl implements IShopService {

	@Autowired
	private ShopDao shopDao;
	
	@Autowired
	private IFileService fileService; 
	

	@Override
	public Page<Shop> listShop(Shop shop) {
		return shopDao.listShop(shop);
	}

	@Override
	public Shop getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return shopDao.getShopById(id);
		}
		return null;
	}

	@Override
	public void updateShop(Shop shop, String[] fileId) {
		if(null!=shop){
			if(ArrayUtils.isNotEmpty(fileId)){
				String objectId = shop.getId();
				fileService.saveFiles(fileId, objectId);
				shop.setCoverImage(fileId[0]);
			}
			shopDao.updateShop(shop);
		}
	}

	@Override
	public void saveShop(Shop shop, String[] fileId) {
		if(null!=shop){
			if(ArrayUtils.isNotEmpty(fileId)){
				shop.setCoverImage(fileId[0]);
			}
			shopDao.saveShop(shop);
			String objectId = shop.getId();
			fileService.saveFiles(fileId, objectId);
		}
	}

	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			shopDao.deleteById(id);
		}
	}
	
}
