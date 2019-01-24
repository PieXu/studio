package com.innovate.sys.log.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.sys.log.model.VisitLog;

/**
 * 访问日志Dao
 * @author IvanHsu
 * @2018年3月31日 下午5:06:27
 */
@Repository("log.dao.VisitLogDao")
public interface VisitLogDao {

	/**
	 * 保存
	 * @param visitLog
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+VisitLog.TAB_NAME+" (#{visitLog})")
	public void saveVisitLog(VisitLog visitLog);
	
	/**
	 * 访问日志的列表 分页查询
	 * @param log
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+VisitLog.TAB_NAME + "  (#{log}) order by visit_date desc ")
	public Page<VisitLog> pageVisitLog(VisitLog log);

	/**
	 * 登录次数
	 * @param userId
	 * @param type
	 * @return
	 */
	@Select("select count(*) from "+VisitLog.TAB_NAME + " where user_id=#{userId} and visit_type=#{type} ")
	public int countVisitTimesByType(@Param("userId")String userId, @Param("type")String type);

	/**
	 * 登录信息的查询
	 * @return
	 */
	@Select("select date_format(visit_date, '%y-%m-%d') as visitdate, "
			+ "sum(visit_type = '登录') as 'loginnum', "
			+ "sum(visit_type = '退出') as 'logoutmum' from "+ VisitLog.TAB_NAME 
			+ " group by date_format(visit_date, '%y-%m-%d') "
			+ "order by date_format(visit_date, '%y-%m-%d')")
	public List<Map<String, Object>> countVisitLog();
		
}
