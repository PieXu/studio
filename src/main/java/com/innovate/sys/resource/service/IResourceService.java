package com.innovate.sys.resource.service;

import java.util.List;

import com.innovate.basic.base.IBaseService;
import com.innovate.sys.resource.model.Resource;

/**
 * 资源管理
 *
 */
public interface IResourceService extends IBaseService{

	/**
	 * 第一级别菜单
	 * @return
	 */
	public List<Resource> getAllRootResource();
	
	/**
	 * 查找子菜单
	 * @return
	 */
	public List<Resource> getResourceByParent(String parent);
	
	/**
	 * 主键查找
	 * @param id
	 * @return
	 */
	public Resource getResourceById(String id);
	
	/**
	 * 更新或者保存
	 * @param res
	 * @return
	 */
	public void saveOrUpdateResource(Resource res);
	
	/**
	 * 按逐渐删除
	 * @param id
	 */
	public void delResource(String id);
	
	/**
	 * 
	 * @param parent
	 * @return
	 */
	public int checkHasChild(String parent);
	

	/**
	 * 下移
	 * @param id
	 * @param parent
	 * @param seq
	 */
	public void dowResSeq(String id, String parent, Integer seq);

	/**
	 * 上移
	 * @param id
	 * @param parent
	 * @param seq
	 */
	public void upResSeq(String id, String parent, Integer seq);
	
	/**
	 * 获取有权限的菜单列表
	 * @param id
	 * @param currentUserId
	 * @return
	 */
	public List<Resource> getPermissionResourceByParent(String id, String currentUserId);

	/**
	 * code查找菜单
	 * @param msgCode
	 * @return
	 */
	public Resource getResourceByCode(String msgCode);

	/**
	 * 所有菜单的列表
	 * @return
	 */
	public List<Resource> getAdminResource();
	
}
