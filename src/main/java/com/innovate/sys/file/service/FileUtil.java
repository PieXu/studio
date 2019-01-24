/**
 * @name:FileUtil.java
 * @package:com.xu.sys.service
 * @time: 2017年7月10日 下午3:46:53
 * @author IvanHsu 
 */
package com.innovate.sys.file.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.innovate.sys.file.model.UploadFile;

/**
 * @desc:文件处理
 * @package:com.xu.sys.service
 * @time: 2017年7月10日 下午3:46:53
 * @author IvanHsu
 */
public abstract interface FileUtil {

	public abstract String saveSingleFile(FileItem fileItem,boolean isTemp,String objectId,String userId)
			throws IOException;

	public abstract UploadFile getFile(String paramString);

	public abstract List<UploadFile> getFilesByObjectId(String paramString);

	/**
	 * @param fileId
	 * @param id
	 * void
	 * 2017年7月10日
	 */
	public abstract void saveFiles(String[] fileId, String objectId);

}
