package com.innovate.work.setting.service;

import com.github.pagehelper.Page;
import com.innovate.basic.base.IBaseService;
import com.innovate.work.setting.model.PicAtlas;

/**
 * @author IvanHsu
 * @2018年5月2日 下午2:08:52
 */
public interface IPicService extends IBaseService{

	/**
	 * 图集列表
	 * @param picAtlas
	 * @return
	 */
	public Page<PicAtlas> listPicAtlas(PicAtlas picAtlas);

	/**
	 * 按主键 删除
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 更新图集
	 * @param picAtlas
	 * @param fileId
	 */
	public void updatePicAtlas(PicAtlas picAtlas, String[] fileId);

	/**
	 * 保存图集
	 * @param picAtlas
	 * @param fileId
	 */
	public void savePicAtlas(PicAtlas picAtlas, String[] fileId);

	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	public PicAtlas getById(String id);

	/**
	 * 按CODE查找
	 * @param string
	 * @return
	 */
	public PicAtlas getByCode(String string);

}
