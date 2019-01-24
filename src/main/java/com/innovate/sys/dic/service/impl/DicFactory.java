package com.innovate.sys.dic.service.impl;

import com.innovate.sys.dic.service.DicUtil;

/**
 * @desc:dic工具工厂类
 * @time: 2017年6月29日 下午3:15:42
 * @author IvanHsu
 */
public class DicFactory {

	private static DicUtil dicService;

	public static DicUtil getDicUtil()
	{
		return dicService;
	}

	public void setDicService(DicUtil dicServiceImpl)
	{
		dicService = dicServiceImpl;
	}
	
	
}
