package com.innovate.work.setting.service.impl;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.sys.file.service.IFileService;
import com.innovate.work.setting.dao.PicDao;
import com.innovate.work.setting.model.PicAtlas;
import com.innovate.work.setting.service.IPicService;

@Service("setting.service.SettingServiceImpl")
public class PicServiceImpl implements IPicService{

	@Autowired
	private PicDao picDao;
	@Autowired
	private IFileService fileService; 

	@Override
	public Page<PicAtlas> listPicAtlas(PicAtlas picAtlas) {
		return picDao.listPicAtlas(picAtlas);
	}

	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			picDao.deleteById(id);
		}
	}

	@Override
	public void updatePicAtlas(PicAtlas picAtlas, String[] fileId) {
		if(null!=picAtlas){
			if(ArrayUtils.isNotEmpty(fileId)){
				String objectId = picAtlas.getId();
				fileService.saveFiles(fileId, objectId);
				picAtlas.setPicNum(fileId.length);
			}else{
				picAtlas.setPicNum(0);
			}
			picDao.updatePicAtlas(picAtlas);
		}
	}

	@Override
	public void savePicAtlas(PicAtlas picAtlas, String[] fileId) {
		if(null!=picAtlas){
			if(ArrayUtils.isNotEmpty(fileId)){
				picAtlas.setPicNum(fileId.length);
			}
			String objectId = picAtlas.getId();
			fileService.saveFiles(fileId, objectId);
			picDao.savePicAtlas(picAtlas);
		}
	}

	@Override
	public PicAtlas getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return picDao.getById(id);
		}
		return null;
	}

	
	@Override
	public PicAtlas getByCode(String code) {
		if(StringUtils.isNotBlank(code)){
			List<PicAtlas> picList = picDao.getByCode(code);
			if(null!=picList && picList.size()>0){
				return picList.get(0);
			}
		}
		return null;
	}
}
