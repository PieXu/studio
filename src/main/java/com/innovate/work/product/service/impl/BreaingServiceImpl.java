package com.innovate.work.product.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.innovate.sys.file.service.IFileService;
import com.innovate.work.product.dao.BreaingDao;
import com.innovate.work.product.model.Bearing;
import com.innovate.work.product.service.IBreaingService;

@Service("product.service.BreaingServiceImpl")
public class BreaingServiceImpl implements IBreaingService {

	@Autowired
	private BreaingDao breaingDao;
	
	@Autowired
	private IFileService fileService; 

	@Override
	public Page<Bearing> listBreaing(Bearing breaing) {
		return breaingDao.listBreaing(breaing);
	}

	@Override
	public void deleteById(String id) {
		if(!StringUtils.isEmpty(id)){
			breaingDao.deleteById(id);
		}
	}

	@Override
	public Bearing getById(String id) {
		if(!StringUtils.isEmpty(id)){
			return breaingDao.getById(id);
		}
		return null;
	}

	@Override
	public void updateBreaing(Bearing breaing,String[] fileId) {
		if(null!=breaing){
			if(ArrayUtils.isNotEmpty(fileId)){
				String objectId = breaing.getId();
				fileService.saveFiles(fileId, objectId);
				breaing.setCoverImage(fileId[0]);
			}
			breaingDao.updateBreaing(breaing);
		}
		
	}

	@Override
	public void saveBreaing(Bearing breaing,String[] fileId) {
		if(null!=breaing){
			if(ArrayUtils.isNotEmpty(fileId)){
				breaing.setCoverImage(fileId[0]);
			}
			breaingDao.saveBreaing(breaing);
			String objectId = breaing.getId();
			fileService.saveFiles(fileId, objectId);
		}
	}
	
	
}
