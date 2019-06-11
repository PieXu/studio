package com.innovate.sys.resource.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovate.sys.resource.dao.ResOptDao;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.ResOpt;
import com.innovate.sys.resource.model.Resource;
import com.innovate.sys.resource.service.IOptService;
import com.innovate.sys.resource.service.IResOptService;
import com.innovate.sys.resource.service.IResourceService;
import com.innovate.util.IdUtil;

/**
 * 
 * @author IvanHsu
 * @2018年3月26日 下午2:07:36
 */
@Service("sys.service.ResOptServiceImpl")
public class ResOptServiceImpl implements IResOptService{

	@Autowired
	private ResOptDao resOptDao;
	@Autowired
	private IOptService optSservice;
	@Autowired
	private IResourceService resourceService;
	
	@Override
	public List<String> getOptIdByResId(String id) {
		if(StringUtils.isNotBlank(id)){
			return resOptDao.getOptIdByResId(id);
		}
		return null;
	}

	@Override
	public void saveResOpt(String resId, String[] optIds,HttpServletRequest request) {
		if(StringUtils.isNotBlank(resId)){
			resOptDao.deleteOptByResId(resId);
			if(ArrayUtils.isNotEmpty(optIds)){
				List<Opt> listOpt = optSservice.getOptsByIds(optIds);
				StringBuffer optNames = new StringBuffer("");
				for(Opt opt : listOpt){
					ResOpt resOpt = new ResOpt();
					resOpt.setId(IdUtil.getUUID());
					resOpt.setCreateTime(new Date());
					resOpt.setUpdateTime(new Date());
					resOpt.setResId(resId);
					resOpt.setOptId( opt.getId());
					optNames.append("[").append(opt.getName()).append("]");
					resOpt.setWidth(request.getParameter(opt.getId()+"_width"));
					resOpt.setHeight(request.getParameter(opt.getId()+"_height"));
					resOpt.setUrl(request.getParameter(opt.getId()+"_url"));
					resOptDao.saveResOpt(resOpt);
				}
				//更新res 显示名称
				Resource res = new Resource();
				res.setId(resId);
				res.setResOpt(optNames.toString());
				resourceService.saveOrUpdateResource(res);
			}
		}
		
	}

	@Override
	public List<Opt> getOptByResId(String resId) {
		if(StringUtils.isNotBlank(resId)){
			return resOptDao.getOptByResId(resId);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getResOptByResId(String resId) {
		if(StringUtils.isNotBlank(resId))
		{
			return resOptDao.getByResId(resId);
		}
		return null;
	}

}
