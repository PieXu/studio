package com.innovate.sys.resource.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.sys.resource.dao.OptDao;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.service.IOptService;
import com.innovate.util.IdUtil;

/**
 * 菜单操作service
 * @author IvanHsu
 * @2018年3月26日 上午10:35:47
 */
@Service("sys.service.OptServiceImpl")
public class OptServiceImpl implements IOptService{

	@Autowired
	private OptDao optDao;

	@Override
	public Page<Opt> getAllOpt(Opt opt) {
		return optDao.getAllOpt(opt);
	}

	@Override
	public Opt getOptById(String id) {
		if(StringUtils.isNotBlank(id)){
			return optDao.getOptById(id);
		}
		return null;
	}

	@Override
	public void saveOrUpdateOpt(Opt opt) {
		String id = opt.getId();
		if(StringUtils.isEmpty(id)){
			opt.setId(IdUtil.getUUID());
			opt.setCreateTime(new Date());
			opt.setUpdateTime(new Date());
			optDao.saveOpt(opt);
		}else{
			opt.setUpdateTime(new Date());
			optDao.updateOpt(opt);
		}
		
	}

	@Override
	public int checkDelete(String[] optIds) {
		if(!ArrayUtils.isEmpty(optIds)){
			return optDao.getCountByOptId(optIds);
		}
		return 0;
	}

	@Override
	public void deleteOpts(String[] optIds) {
		if(!ArrayUtils.isEmpty(optIds)){
			optDao.deleteOpts(optIds);
		}
		
	}

	@Override
	public List<Opt> getOptsByIds(String[] optIds) {
		if(!ArrayUtils.isEmpty(optIds)){
			return optDao.getOptsByIds(optIds);
		}
		return null;
	}
}
