/**
 * @name:FileDao.java
 * @package:com.xu.sys.dao
 * @time: 2017年7月10日 上午11:16:31
 * @author IvanHsu 
 */
package com.innovate.sys.file.dao;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.sys.file.model.UploadFile;

/**
 * @desc:文件处理
 * @package:com.xu.sys.dao
 * @time: 2017年7月10日 上午11:16:31
 * @author IvanHsu 
 */
@Repository("sys.dao.FileDao")
public interface FileDao {

	static final String TAB_NAME = "t_sys_file"; 
	/**
	 * @param file
	 * void
	 * 2017年7月10日
	 */
	public void saveFile(UploadFile file);

	/**
	 * @param fileId
	 * @param objectId
	 * @param string
	 * void
	 * 2017年7月10日
	 */
	public void updateRefFileTag(@Param("fileId")String[] fileId,@Param("objectId") String objectId,@Param("isTemp") String isTemp);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+TAB_NAME+" WHERE ID = #{id}")
	public UploadFile getFile(String id);

	/**
	 * 
	 * @param objectId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("SELECT * FROM "+TAB_NAME+" WHERE object_id = #{objectId} and is_temp = 'N' ")
	public List<UploadFile> getFilesByObjectId(String objectId);

	/**
	 * 
	 * @param objectId
	 * @param fileId
	 */
	public void deleteRefFile(@Param("objectId")String objectId, @Param("fileId")String[] fileId);

}
