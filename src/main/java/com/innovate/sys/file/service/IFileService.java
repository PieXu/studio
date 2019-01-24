/**
 * @name:IFileService.java
 * @package:com.xu.sys.service
 * @time: 2017年7月10日 上午10:37:13
 * @author IvanHsu 
 */
package com.innovate.sys.file.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

/**
 * @desc:文件处理
 * @package:com.xu.sys.service
 * @time: 2017年7月10日 上午10:37:13
 * @author IvanHsu 
 */
public interface IFileService extends FileUtil{
	
	public abstract void openFile(HttpServletResponse paramHttpServletResponse, HttpServletRequest paramHttpServletRequest);

	public abstract String saveImagesFile(FileItem item, boolean b, String objectId, String currentUserId);
}
