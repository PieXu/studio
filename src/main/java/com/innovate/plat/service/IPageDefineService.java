package com.innovate.plat.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.plat.model.PageDefine;
import com.innovate.plat.model.PageSet;

public interface IPageDefineService extends IBaseService<PageDefine>{

	public Page<PageDefine> listPageDefine(PageDefine pageDefine);

	public PageDefine getById(String id);
	
	public void updatePageDefine(PageDefine pageDefine);

	public void deleteById(String id);

	public void saveOrUpdatePageDefine(PageDefine pageDefine);

	public void savePageSet(List<PageSet> setList);

	public PageDefine getPageDefineByCode(String code,String delStatus);

	public List<PageSet> getPageSetByPageId(String id);

}
