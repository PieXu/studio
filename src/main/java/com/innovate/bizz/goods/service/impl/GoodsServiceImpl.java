package com.innovate.bizz.goods.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.bizz.goods.dao.GoodsDao;
import com.innovate.bizz.goods.model.Goods;
import com.innovate.bizz.goods.service.IGoodsService;
import com.innovate.sys.file.service.IFileService;

@Service
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private IFileService fileService; 

	@Override
	public Page<Goods> listGoods(Goods goods) {
		return goodsDao.listGoods(goods);
	}

	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			goodsDao.deleteById(id);
		}
	}

	@Override
	public Goods getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return goodsDao.getGoodsById(id);
		}
		return null;
	}

	@Override
	public void updateGoods(Goods goods, String[] fileId) {
		if(null!=goods){
			if(ArrayUtils.isNotEmpty(fileId)){
				String objectId = goods.getId();
				fileService.saveFiles(fileId, objectId);
				goods.setCoverImage(fileId[0]);
			}
			goodsDao.updateGoods(goods);
		}
		
	}

	@Override
	public void saveGoods(Goods goods, String[] fileId) {
		if(null!=goods){
			if(ArrayUtils.isNotEmpty(fileId)){
				goods.setCoverImage(fileId[0]);
			}
			goodsDao.saveGoods(goods);
			String objectId = goods.getId();
			fileService.saveFiles(fileId, objectId);
		}
		
	}
}
