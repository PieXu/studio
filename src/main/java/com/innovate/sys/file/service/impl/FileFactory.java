/**
 * @name:DicFactory.java
 * @package:com.xu.factory
 * @time: 2017年6月29日 下午3:15:42
 * @author IvanHsu 
 */
package com.innovate.sys.file.service.impl;

import com.innovate.sys.file.service.FileUtil;

/**
 * @desc:dic工具工厂类
 * @package:com.xu.factory
 * @time: 2017年6月29日 下午3:15:42
 * @author IvanHsu
 */
public class FileFactory {

	private static FileUtil fileService;

	public static FileUtil getFileUtil()
	{
		return fileService;
	}
	public static void setFileService(FileUtil fileServiceImpl)
	{
		fileService = fileServiceImpl;
	}

}
