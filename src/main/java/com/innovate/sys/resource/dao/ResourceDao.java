package com.innovate.sys.resource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.innovate.basic.driver.SimpleInsertLangDriver;
import com.innovate.basic.driver.SimpleSelectLangDriver;
import com.innovate.basic.driver.SimpleUpdateLangDriver;
import com.innovate.sys.resource.model.Resource;
import com.innovate.user.role.model.ResRole;
import com.innovate.user.user.model.UserRole;

@Repository("sys.dao.ResourceDao")
public interface ResourceDao {
	
	/**
	 * 父菜单
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME+" where parent is null or parent = '' order by seq_num ")
	public List<Resource> getAllRootResource();
	
	/**
	 * 查找子菜单
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME+" where parent = #{parent} order by seq_num ")
	public List<Resource> getResourceByParent(String parent);
	
	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME+" where id = #{id}")
	public Resource getResourceById(String id);
	
	/**
	 * 保存
	 * @param res
	 * @return
	 */
	@Lang(SimpleInsertLangDriver.class)
	@Insert("insert into "+Resource.TABLE_NAME+" (#{res})")
	public void saveResource(Resource res);
	
	/**
	 * 更新
	 * @param res
	 * @return
	 */
	@Lang(SimpleUpdateLangDriver.class)
	@Update("update "+Resource.TABLE_NAME+" (#{res}) where id = #{id}")
	public void updateResource(Resource res);
	/**
	 * 按主键删除
	 * @param id
	 */
	@Delete("delete from "+Resource.TABLE_NAME+ " where id = #{id} ")
	public void delResource(String id);

	/**
	 * 
	 * @param parent
	 * @return
	 */
	@Select("select count(*) from "+Resource.TABLE_NAME+" where parent = #{parent}")
	public int checkHasChild(String parent);

	/**
	 * 
	 * @param parent
	 * @param preSeq
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME+" where parent = #{parent} and seq_num = #{preSeq}")
	public Resource getResourceBySeq(@Param("parent")String parent, @Param("preSeq")int preSeq);

	/**
	 * 
	 * @param parent
	 * @return
	 */
	@Select("select max(seq_num) from "+Resource.TABLE_NAME+" where parent = #{parent}")
	public Integer getMaxSeqByParent(String parent);

	/**
	 * 角色授权的菜单的列表信息
	 * @param parent
	 * @param currentUserId
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select r.* from " + 
			Resource.TABLE_NAME+ " r, " +
			UserRole.TAB_NAME+  " ur, " + 
			ResRole.TAB_NAME + " rr "
			+ "where r.id = rr.RES_ID and ur.role_id = rr.ROLE_ID and ur.user_id=#{currentUserId} and r.parent=#{parent} order by seq_num ")
	public List<Resource> getPermissionResourceByParent(@Param("parent")String parent, @Param("currentUserId")String currentUserId);

	/**
	 * 按code查找
	 * @param msgCode
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME+" where code = #{msgCode}")
	public Resource getResourceByCode(String msgCode);

	/**
	 * 按code查找
	 * @param msgCode
	 * @return
	 */
	@Lang(SimpleSelectLangDriver.class)
	@Select("select * from "+Resource.TABLE_NAME)
	public List<Resource> getAdminResource();
}
