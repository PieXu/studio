/**
 * @name:FileServiceImpl.java
 * @package:com.xu.sys.service.impl
 * @time: 2017年7月10日 上午10:37:32
 * @author IvanHsu 
 */
package com.innovate.sys.file.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.innovate.sys.file.dao.FileDao;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.IFileService;
import com.innovate.util.CommonCons;
import com.innovate.util.DateUtils;
import com.innovate.util.IdUtil;
import com.innovate.util.SystemPropertiesUtil;

/**
 * @desc: 文件处理
 * @package:com.xu.sys.service.impl
 * @time: 2017年7月10日 上午10:37:32
 * @author IvanHsu
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements IFileService {

	@Autowired
	private FileDao fileDao;

	/**
	 * 单个文件保存
	 * @throws IOException 
	 */
	@Override
	public String saveSingleFile(FileItem fileItem, boolean isTemp, String objectId,String createUser) 
			throws IOException
	{
		String savePath = this.getUploadPath();
		String fileId = null;
		try {
			fileId = this.saveFile(fileItem, isTemp, objectId, createUser, savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileId;
	}
	
	/**
	 * 保存图片文件到文件服务器的访问路径上
	 * 配合文件服务器使用
	 * 
	 */
	@Override
	public String saveImagesFile(FileItem fileItem, boolean isTemp, String objectId, String createUser) 
	{
		String savePath = this.getImageServerPath();
		String fileId = null;
		try {
			fileId = this.saveFile(fileItem, isTemp, objectId, createUser, savePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileId;
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.FileUtil#getFile(java.lang.String)
	 */
	@Override
	public UploadFile getFile(String id)
	{
		return fileDao.getFile(id);
	}

	/* (non-Javadoc)
	 * @see com.xu.sys.service.FileUtil#getFilesByObjectId(java.lang.String)
	 */
	@Override
	public List<UploadFile> getFilesByObjectId(String objectId)
	{
		if(StringUtils.isNotBlank(objectId)){
			return fileDao.getFilesByObjectId(objectId);
		}
		return null;
	}


	@Override
	public void saveFiles(String[] fileId, String objectId)
	{
		/*
		 *1、现将页面删除的file信息删除
		 *2、将文件信息关联业务对象，并修改isTemp为false 
		 */
		fileDao.deleteRefFile(objectId,fileId);
		if(!ArrayUtils.isEmpty(fileId)){
			fileDao.updateRefFileTag(fileId,objectId,CommonCons.Y_N_ENUM.N.toString());
		}
	}

	/**
	 * 打开文件
	 * 用于下载，非图片服务器 图片文件的访问使用
	 */
	public void openFile(HttpServletResponse response, HttpServletRequest request) {
		String id = null;
		String type = request.getParameter("type");
		if (StringUtils.isNotEmpty((String) request.getAttribute("id"))) {
			id = (String) request.getAttribute("id");
		} else if (StringUtils.isNotEmpty(request.getParameter("id"))) {
			id = request.getParameter("id");
		} else {
			return;
		}
		openFile(response, id, type);
	}
	  
	private void openFile(HttpServletResponse response, String id,String type) {
		if (StringUtils.isEmpty(id)) {
			return;
		}
		UploadFile uploadFile = getFile(id);
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.addHeader("Content-Length", String.valueOf(uploadFile.getSize()));
		response.setHeader("Content-Disposition", "attachment;filename=" + uploadFile.getName());
		InputStream fis = null;
		String filePath = uploadFile.getPath() + File.separator + uploadFile.getId();
		if(StringUtils.isNotBlank(type) && "image".equalsIgnoreCase(type)){
			filePath +="."+uploadFile.getExt();
		}
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		try {
			fis = new BufferedInputStream(new FileInputStream(filePath));
			FileCopyUtils.copy(fis, response.getOutputStream());
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * 保存文件
	 * @param fileItem
	 * @param isTemp
	 * @param objectId
	 * @param createUser
	 * @param savePath
	 * @return
	 * @throws Exception
	 */
	private String saveFile(FileItem fileItem, boolean isTemp, String objectId, String createUser,String savePath) throws Exception{
		String fileId = null;
		if (null != fileItem) {
			if (isTemp){
				savePath = this.getUploadTmpPath();
			}
			// 根据路径创建目录
			mkdir(savePath);
			fileId = IdUtil.getUUID();
			// 2. 获取文件的实际内容
			InputStream is = fileItem.getInputStream();
			String name = fileItem.getName();
			String subfix = name.substring(name.lastIndexOf("."));
			String ext = name.substring(name.lastIndexOf(".")+1);
			// 3. 保存文件,加扩展名保存， 用于图片文件服务器的读取
			FileUtils.copyInputStreamToFile(is, new File(savePath + File.separator + fileId + subfix ));
			//创建文件对象保存
			UploadFile file = new UploadFile();
			file.setId(fileId);
			file.setName(name);
			file.setPath(savePath);
			file.setCreateTime(new Date());
			file.setUpdateTime(new Date());
			file.setIsTemp(CommonCons.Y_N_ENUM.Y.toString());
			file.setSize(fileItem.getSize());
			file.setExt(ext);
			file.setType(fileItem.getContentType());
			file.setCreateUser(createUser);
			file.setObjectId(objectId);
			fileDao.saveFile(file);
		}
		return fileId;
	}
	
	/**
	 * 文件存储上传路径
	 * @return
	 * String
	 * 2017年7月10日
	 */
	private String getUploadPath()
	{
		String path = SystemPropertiesUtil.getSystemConfigProperties().getProperty(CommonCons.UPLOAD_FILEPATH_KEY)
				+ File.separator;

		path = path + DateUtils.getCustomDateString(new Date(), new StringBuilder().append("yyyy").append(File.separator)
				.append("MM").append(File.separator).append("dd").toString());
		return path;
	}

	/**
	 * 临时文件存储路径
	 * @return
	 * String
	 * 2017年7月10日
	 */
	private String getUploadTmpPath()
	{
		String path = SystemPropertiesUtil.getSystemConfigProperties().getProperty(CommonCons.UPLOAD_FILEPATH_KEY)
				+ File.separator + "tmp";

		return path;
	}

	/**
	 * 临时文件存储路径
	 * @return
	 * String
	 * 2017年7月10日
	 */
	private String getImageServerPath()
	{
		String path = SystemPropertiesUtil.getSystemConfigProperties().getProperty(CommonCons.UPLOAD_FILEPATH_KEY)
				+ File.separator + "images";

		return path;
	}
	/**
	 * 创建目录路径
	 * @param path
	 * void
	 * 2017年7月10日
	 */
	public void mkdir(String path)
	{
		File fd = null;
		try {
			fd = new File(path);
			if (!fd.exists()) {
				fd.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fd = null;
		}
	}

	  
}
