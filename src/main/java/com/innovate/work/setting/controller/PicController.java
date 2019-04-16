package com.innovate.work.setting.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.FileUtil;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.work.setting.model.PicAtlas;
import com.innovate.work.setting.service.IPicService;

/**
 * 网站图集管理
 * @author IvanHsu
 * @2018年5月2日 下午2:09:13
 */
@Controller("setting.controller.PicController")
public class PicController extends BaseController{

	@Autowired
	private IPicService picService;
	@Autowired
	private FileUtil fileUtil;
	
	/**      
	 * 图集列表
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="pic/listPicAtlas")
	public String listPicAtlas(HttpServletRequest request,Model model,PicAtlas picAtlas,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<PicAtlas> page = picService.listPicAtlas(picAtlas);
		model.addAttribute("page",page);
		model.addAttribute("picAtlas", picAtlas);
		return "picAtlas/picAtlasList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"pic/editPicAtlas"})
	public String modifyBreaing(HttpServletRequest request,Model model,String id)
	{
		PicAtlas picAtlas = new PicAtlas();
		if(!StringUtils.isEmpty(id)){
			picAtlas =  picService.getById(id);
			List<UploadFile> fileList = fileUtil.getFilesByObjectId(id);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("picAtlas", picAtlas);
		
		return "picAtlas/nsm/picAtlasEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pic/savePicAtlas")
	public ResultObject saveBreaing(HttpServletRequest request,Model model, PicAtlas  picAtlas,String[] fileId)
	{
		ResultObject result = new ResultObject();
		String id = picAtlas.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				picAtlas.setUpdateTime(new Date());
				picService.updatePicAtlas(picAtlas,fileId);
				result.setMessage("图集更新成功");
			}else{
				picAtlas.setCreateTime(new Date());
				picAtlas.setUpdateTime(new Date());
				picAtlas.setId(IdUtil.getUUID());
				picService.savePicAtlas(picAtlas,fileId);
				result.setMessage("图集添加成功");
			}
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(this.getClass(),"图集更新保存异常："+e.getMessage());
		}
		return result;
	}
	
	/**
	 * 删除图集
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pic/deleteAtlas")
	public ResultObject deleteAtlas(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				picService.deleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该产品已删除");
			}catch (Exception e) {
				result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
				result.setMessage("操作失败");
			}
		}else{
			result.setResult(ResultObject.OPERATE_RESULT.empty.toString());
			result.setMessage("参数 id 为空");
		}
		return result;
	}
	
}
