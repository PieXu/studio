package com.innovate.work.setting.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.work.setting.model.PicAtlas;

@Repository("setting.dao.SettingDao")
public interface PicDao {

	/**
	 * 图集列表
	 * @param picAtlas
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PicAtlas.TAB_NAME + " (#{picAtlas})")
	public Page<PicAtlas> listPicAtlas(PicAtlas picAtlas);
	
	/**
	 * 按主键 删除
	 * @param id
	 */
	@Delete("delete from "+ PicAtlas.TAB_NAME + " where id = #{id}")
	public void deleteById(String id);

	/**
	 * 更新图集
	 * @param picAtlas
	 * @param fileId
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("UPDATE "+ PicAtlas.TAB_NAME +" (#{picAtlas}) WHERE ID = #{id}")
	public void updatePicAtlas(PicAtlas picAtlas);

	/**
	 * 保存图集
	 * @param picAtlas
	 * @param fileId
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("INSERT INTO "+  PicAtlas.TAB_NAME +" (#{picAtlas})")
	public void savePicAtlas(PicAtlas picAtlas);

	/**
	 * 按主键查找
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PicAtlas.TAB_NAME + " where id = #{id}")
	public PicAtlas getById(String id);

	/**
	 * 按照code查找
	 * @param code
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " + PicAtlas.TAB_NAME + " where atlas_code = #{code}")
	public List<PicAtlas> getByCode(String code);
	

}
