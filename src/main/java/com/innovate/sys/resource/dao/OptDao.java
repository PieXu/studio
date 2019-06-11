package com.innovate.sys.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInLangDriver;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.ResOpt;

/**
 * 菜单操作DAO
 * @author IvanHsu
 * @2018年3月26日 上午10:36:37
 */
@Repository("sys.dao.OptDao")
public interface OptDao {

	/**
	 * 所有系统操作列表
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Opt.TAB_NAME+" (#{opt}) ")
	public Page<Opt> getAllOpt(Opt opt);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from " +Opt.TAB_NAME+ " where id = #{id}")
	public Opt getOptById(String id);

	/**
	 * 
	 * @param opt
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+Opt.TAB_NAME+" (#{opt}) ")
	public void saveOpt(Opt opt);

	/**
	 * 
	 * @param opt
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("UPDATE "+ Opt.TAB_NAME +" (#{opt}) WHERE ID = #{id}")
	public void updateOpt(Opt opt);

	/**
	 * 菜单引用
	 * @param optIds
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select count(*) from "+ResOpt.TAB_NAME+" where opt_id in (#{optIds})")
	public int getCountByOptId(@Param("optIds")String[] optIds);

	/**
	 * 删除 物理删除
	 * @param optIds
	 */
	@Lang(SimpleInLangDriver.class)
	@Delete("delete from "+Opt.TAB_NAME+" where id in (#{optIds})")
	public void deleteOpts(@Param("optIds")String[] optIds);

	/**
	 * 
	 * @param optIds
	 * @return
	 */
	@Lang(SimpleInLangDriver.class)
	@Select("select * from "+Opt.TAB_NAME+" where id in (#{optIds})")
	public List<Opt> getOptsByIds(@Param("optIds")String[] optIds);
	
}
