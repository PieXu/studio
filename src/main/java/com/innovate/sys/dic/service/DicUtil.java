package com.innovate.sys.dic.service;

import java.util.List;

import com.innovate.sys.dic.model.Dic;

/**
 * @desc: 数据字典工具类
 * @time: 2017年6月29日 下午3:10:49
 * @author IvanHsu 
 */
public abstract interface DicUtil {
	
	/**
	 * 按照分类查询数据字典集合
	 * @param categoryCode
	 * @return
	 * List<Dic>
	 * 2017年6月20日
	 */
	public List<Dic> getDicList(String categoryCode);

	/**
	 * 查找具体的数据字典项
	 * @param categoryCode
	 * @param code
	 * @return
	 * Dic
	 * 2017年6月20日
	 */
	public Dic getDicInfo(String categoryCode, String code);
	
	/**
	 * 按照Id查找
	 * @param dicId
	 * @return
	 */
	public Dic getDicInfo(String dicId);
	
	/**
	 * 用户状态正常
	 * @return
	 */
	public Dic getUserStatusEnable();
	
	/**
	 * 是否集合 用的比较多
	* <p>Title: getSystemYAndN</p>
	* <p>Description: </p>
	* @return
	 */
	public List<Dic> getSystemYAndN();
	
	/**
	 * 
	* <p>Title: getDicY</p>
	* <p>Description: </p>
	* @return
	 */
	public Dic getDicY();
	
	/**
	 * 
	* <p>Title: getDicN</p>
	* <p>Description: </p>
	* @return
	 */
	public Dic getDicN();

}
