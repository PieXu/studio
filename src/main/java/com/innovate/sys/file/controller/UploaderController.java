/**
 * @name:FileController.java
 * @package:com.xu.sys.controller
 * @time: 2017年7月7日 下午4:10:30
 * @author IvanHsu 
 */
package com.innovate.sys.file.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.sys.file.service.IFileService;
import com.innovate.util.SessionUtils;

/**
 * @desc:文件处理
 * @package:com.xu.sys.controller
 * @time: 2017年7月7日 下午4:10:30
 * @author IvanHsu
 */
@Controller("sys.controller.FileController")
public class UploaderController {

	@Autowired
	private IFileService fileService;

	/**
	 * 单个文件上传
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@ResponseBody
	@RequestMapping(value = { "/upload/file/singleFileUpload" }, produces = { "text/plain;charset=UTF-8" })
	public List<String> uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, FileUploadException
	{
		List<String> fileId  = new ArrayList<String>();
		// 1.创建DiskFileItemFactory对象，配置缓存用
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2. 创建 ServletFileUpload对象
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 3. 设置文件名称编码
			servletFileUpload.setHeaderEncoding("utf-8");
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem item : items) {
				if(!item.isFormField())
					fileId.add(fileService.saveSingleFile(item, false, null, SessionUtils.getCurrentUserId()));
			}
		}
		return  fileId;
	}
	
	/**
	 * 上传图片文件，到对应图片服务器中
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException
	 */
	@ResponseBody
	@RequestMapping(value = { "/upload/file/uploadImages" })
	public List<String> uploadImages(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, FileUploadException
	{
		List<String> fileId  = new ArrayList<String>();
		// 1.创建DiskFileItemFactory对象，配置缓存用
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 2. 创建 ServletFileUpload对象
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// 3. 设置文件名称编码
			servletFileUpload.setHeaderEncoding("utf-8");
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem item : items) {
				if(!item.isFormField())
					fileId.add(fileService.saveImagesFile(item, false, null, SessionUtils.getCurrentUserId()));
			}
		}
		System.out.println("上传的 fileId : " + fileId);
		return  fileId;
	}
	
	/**
	 * 打开文件
	 * @param response
	 * @param request
	 */
	@ResponseBody
	@RequestMapping({ "/file/openFile"})
	public void openFile(HttpServletResponse response, HttpServletRequest request) {
		this.fileService.openFile(response, request);
	}

}
