package com.innovate.plat.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.innovate.plat.dao.PageDefineDao;
import com.innovate.plat.dao.PageSetDao;
import com.innovate.plat.model.PageDefine;
import com.innovate.plat.model.PageSet;
import com.innovate.plat.service.IPageDefineService;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;

/**
 * 
* <p>Title: PageDefineServiceImpl</p>
* <p>Description: </p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月15日
 */
@Service
public class PageDefineServiceImpl implements IPageDefineService{

	@Autowired
	private PageDefineDao pageDefineDao;
	
	@Autowired
	private PageSetDao pageSetDao;
	
	@Override
	public Page<PageDefine> listPageDefine(PageDefine pageDefine) {
		return pageDefineDao.listPageDefine(pageDefine);
	}

	@Override
	public PageDefine getById(String id) {
		if(StringUtils.isNotBlank(id)){
			return pageDefineDao.getById(id);
		}
		return null;
	}
	
	/**
	 *  逻辑删除
	 */
	@Override
	public void deleteById(String id) {
		if(StringUtils.isNotBlank(id)){
			PageDefine pageDefine = new PageDefine();
			pageDefine.setId(id);
			pageDefine.setDelFlag(CommonCons.STATUS_DELETE);
			this.updatePageDefine(pageDefine);
		}
	}

	@Override
	public void updatePageDefine(PageDefine pageDefine) {
		if(null!=pageDefine && StringUtils.isNotBlank(pageDefine.getId()))
		{
			pageDefineDao.updatePageDefine(pageDefine);
		}
	}

	@Override
	public void saveOrUpdatePageDefine(PageDefine pageDefine) {
		String id = pageDefine.getId();
		if(StringUtils.isEmpty(id)){
			pageDefine.setId(IdUtil.getUUID());
			pageDefine.setCreateTime(new Date());
			pageDefine.setUpdateTime(new Date());
			pageDefineDao.savePageDefine(pageDefine);
		}else{
			pageDefine.setUpdateTime(new Date());
			pageDefineDao.updatePageDefine(pageDefine);
		}
	}

	@Override
	public void savePageSet(List<PageSet> setList) {
		if(!setList.isEmpty())
		{
			for(PageSet pageset : setList)
			{
				String id = pageset.getId();
				if(StringUtils.isEmpty(id)){
					pageset.setId(IdUtil.getUUID());
					pageSetDao.savePageSet(pageset);
				}else{
					pageset.setUpdateTime(new Date());
					pageSetDao.updatePageSet(pageset);
				}
			}
		}
	}

	@Override
	public PageDefine getPageDefineByCode(String code,String delStatus) {
		List<PageDefine> pageList = pageDefineDao.getPageDefineByCode(code,delStatus);
		if(!pageList.isEmpty()){
			return pageList.get(0);
		}
		return null;
	}

	@Override
	public List<PageSet> getPageSetByPageId(String pageId) {
		if(StringUtils.isNotBlank(pageId)){
			return pageSetDao.getByPageId(pageId);
		}
		return null;
	}

}
