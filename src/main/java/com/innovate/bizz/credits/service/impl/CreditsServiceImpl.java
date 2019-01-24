package com.innovate.bizz.credits.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.bizz.credits.dao.CreditsExchangeDao;
import com.innovate.bizz.credits.dao.CreditsProductDao;
import com.innovate.bizz.credits.model.CreditsExchange;
import com.innovate.bizz.credits.model.CreditsProduct;
import com.innovate.bizz.credits.service.ICreditsService;
import com.innovate.sys.file.service.IFileService;

@Service
public class CreditsServiceImpl implements ICreditsService {

	@Autowired
	private CreditsProductDao creditsProductDao;
	
	@Autowired
	private CreditsExchangeDao  creditsExchangeDao;
	

	@Autowired
	private IFileService fileService; 

	@Override
	public Page<CreditsProduct> listCreditsProduct(CreditsProduct product) {
		return creditsProductDao.listCreditsProduct(product);
	}

	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			creditsProductDao.deleteById(id);
		}
	}

	@Override
	public CreditsProduct getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return creditsProductDao.getCreditsProductById(id);
		}
		return null;
	}

	@Override
	public void updateCreditsProduct(CreditsProduct product, String[] fileId) {
		if(null!=product){
			if(ArrayUtils.isNotEmpty(fileId)){
				String objectId = product.getId();
				fileService.saveFiles(fileId, objectId);
				product.setProductImage(fileId[0]);
			}
			creditsProductDao.updateGoods(product);
		}
		
	}

	@Override
	public void saveCreditsProduct(CreditsProduct product, String[] fileId) {
		if(null!=product){
			if(ArrayUtils.isNotEmpty(fileId)){
				product.setProductImage(fileId[0]);
			}
			creditsProductDao.saveCreditsProduct(product);
			String objectId = product.getId();
			fileService.saveFiles(fileId, objectId);
		}
	}

	@Override
	public Page<CreditsExchange> listCreditsExchange(CreditsExchange exchage) {
		return creditsExchangeDao.listCreditsExchange(exchage);
	}

	@Override
	public void updateCreditsExchange(CreditsExchange exchage) {
		if(null!=exchage){
			creditsExchangeDao.updateCreditsExchange(exchage);
		}
		
	}

	@Override
	public void saveCreditsExchange(CreditsExchange exchage) {
		if(null!=exchage){
			creditsExchangeDao.saveCreditsExchange(exchage);
		}
	}
	
	
}
