package com.innovate.sys.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.sys.resource.model.Opt;
import com.innovate.sys.resource.model.ResOpt;

/**
 * 资源操作对应关系
 * @author IvanHsu
 * @2018年3月26日 下午2:08:17
 */
@Repository("sys.dao.ResOptDao")
public interface ResOptDao {

	/**
	 * 菜单下的操作的编号集合
	 * @param id
	 * @return
	 */
	@Select("select opt_id  from " + ResOpt.TAB_NAME +" where res_id = #{id} ")
	public List<String> getOptIdByResId(String id);

	/**
	 * 删除某个菜单下的所有的操作配置
	 * @param resId
	 */
	@Delete("delete from "+ResOpt.TAB_NAME+" where res_id = #{resId}")
	public void deleteOptByResId(String resId);

	/**
	 * 保存对应关系
	 * @param resOpt
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+ResOpt.TAB_NAME+" (#{resOpt})")
	public void saveResOpt(ResOpt resOpt);

	/**
	 * 通过菜单获取操作的对象集合
	 * @param resId
	 * @return
	 */
	@Select("select * from " + Opt.TAB_NAME +" where id in ( select opt_id from "+ ResOpt.TAB_NAME +" where res_id = #{resId} )")
	public List<Opt> getOptByResId(String resId);

}
