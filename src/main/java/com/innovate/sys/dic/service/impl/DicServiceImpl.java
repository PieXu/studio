package com.innovate.sys.dic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.innovate.sys.dic.dao.DicDao;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.IDicService;

/**
 * @time: 2017年6月20日 下午3:58:53
 * @author IvanHsu 
 */
@Service("sys.dicServiceImpl")
public class DicServiceImpl implements IDicService {
	
	@Autowired
	private DicDao dicDao;

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#listAllDic()
	 */
	@Override
	public List<Dic> listAllDic()
	{
		return dicDao.listAllDic();
	}
	
	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#listDicByCondition(com.xu.sys.model.Dic)
	 */
	@Override
	public Page<Dic> listDicByCondition(Dic dic)
	{
		return dicDao.listDicByCondition(dic);
	}


	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#getDicList(java.lang.String)
	 */
	@Override
	@Cacheable(value={"com.innovate.sys.service.impl.DicServiceImpl"}, key="#categoryCode + 'getDicList'")
	public List<Dic> getDicList(String categoryCode)
	{
		return dicDao.getDicList(categoryCode);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#getDicInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@Cacheable(value={"com.innovate.sys.service.impl.DicServiceImpl"}, key="#categoryCode + #code + 'getDicInfo'")
	public Dic getDicInfo(String categoryCode, String code)
	{
		return dicDao.getDicInfo(categoryCode, code);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#deleteDic(java.lang.String)
	 */
	@Override
	@CacheEvict(value={"com.innovate.sys.service.impl.DicServiceImpl"},allEntries=true)
	public Dic deleteDic(String dicId)
	{
		return dicDao.deleteDic(dicId);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#saveDic(com.xu.sys.model.Dic)
	 */
	@Override
	@CacheEvict(value={"com.innovate.sys.service.impl.DicServiceImpl"}, allEntries=true)
	public void saveDic(Dic dic)
	{
		dicDao.saveDic(dic);
		
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#updateDic(com.xu.sys.model.Dic)
	 */
	@Override
	@CacheEvict(value={"com.innovate.sys.service.impl.DicServiceImpl"}, allEntries=true)
	public void updateDic(Dic dic)
	{
		dicDao.updateDic(dic);
		
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.IDicService#getDicById(java.lang.String)
	 */
	@Override
	@CacheEvict(value={"com.innovate.sys.service.impl.DicServiceImpl"}, key="#id + 'getDicById'")
	public Dic getDicById(String id)
	{
		if(!StringUtils.isEmpty(id))
			return dicDao.getDicById(id);
		
		return null;
	}

	@Override
	@Cacheable(value={"com.innovate.sys.service.impl.DicServiceImpl"}, key="#dicId + 'getDicInfo'")
	public Dic getDicInfo(String dicId) 
	{
		return getDicById(dicId);
	}

	@Override
	public Dic getUserStatusEnable() {
		return this.getDicInfo("USER_STATE", "USER_STATE_ENABLE");
	}

}
