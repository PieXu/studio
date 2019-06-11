package com.innovate.sys.resource.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.innovate.basic.base.IBaseService;
import com.innovate.sys.resource.model.Opt;

/**
 * 资源操作关系service
 * @author IvanHsu
 * @2018年3月26日 下午2:06:50
 */
public interface IResOptService extends IBaseService<Opt>{

	/**
	 * 菜单下的操作的编号集合
	 * @param id
	 * @return
	 */
	public List<String> getOptIdByResId(String id);
	
	/**
	 * 菜单下的操作的编号集合
	 * @param id
	 * @return
	 */
	public List<Opt> getOptByResId(String resId);

	/**
	 * 菜单操作授权
	 * @param resId
	 * @param optIds
	 */
	public void saveResOpt(String resId, String[] optIds,HttpServletRequest request);

	/**
	 * 
	* <p>Title: getResOptByResId</p>
	* <p>Description: </p>
	* @param resId
	* @return
	 */
	public List<Map<String, Object>> getResOptByResId(String resId);
}
