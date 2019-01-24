package com.innovate.sys.dic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.innovate.sys.dic.dao.DicCategoryDao;
import com.innovate.sys.dic.model.DicCategory;
import com.innovate.sys.dic.service.IDicCategoryService;

/**
 * @desc:数据字典分类管理
 * @time: 2017年7月5日 下午5:54:37
 * @author IvanHsu 
 */
@Service("sys.DicCategoryServiceImpl")
public class DicCategoryServiceImpl implements IDicCategoryService{
	
	@Autowired
	private DicCategoryDao dicCategoryDao;

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicCategoryService#pageDicCategory(com.xu.sys.model.DicCategory)
	 */
	@Override
	public Page<DicCategory> pageDicCategory(DicCategory dicCategory)
	{
		// TODO Auto-generated method stub
		return dicCategoryDao.pageDicCategory(dicCategory);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicCategoryService#saveDicCategory(com.xu.sys.model.DicCategory)
	 */
	@Override
	public void saveDicCategory(DicCategory dicCategory)
	{
		if(null!=dicCategory)
			dicCategoryDao.saveDicCategory(dicCategory);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicCategoryService#updateDicCategory(com.xu.sys.model.DicCategory)
	 */
	@Override
	public void updateDicCategory(DicCategory dicCategory)
	{
		if(null!=dicCategory && !StringUtils.isEmpty(dicCategory.getId()))
			dicCategoryDao.updateDicCategory(dicCategory);
		
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicCategoryService#getById(java.lang.String)
	 */
	@Override
	public DicCategory getById(String id)
	{
		if(!StringUtils.isEmpty(id))
			return dicCategoryDao.getById(id);
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicCategoryService#listCategoryAll()
	 */
	@Override
	public List<DicCategory> listCategoryAll()
	{
		return dicCategoryDao.listCategoryAll();
	}
	
}
