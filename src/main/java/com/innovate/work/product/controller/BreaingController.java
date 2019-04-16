package com.innovate.work.product.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.basic.base.BaseController;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.FileUtil;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.ResultObject;
import com.innovate.work.product.model.Bearing;
import com.innovate.work.product.service.IBreaingService;

/**
 * 
 */
@Controller("product.controller.BreaingController")
public class BreaingController extends BaseController{
 
	@Autowired
	private IBreaingService breaingService;
	@Autowired
	private DicUtil dicUtil;
	@Autowired
	private FileUtil fileUtil;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**      
	 * 用户信息列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="product/listBreaing")
	public String listBreaing(HttpServletRequest request,Model model,Bearing  breaing,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Bearing> page = breaingService.listBreaing(breaing);
		List<Dic> categoryList = dicUtil.getDicList("BREAING_CATEGORY");
		model.addAttribute("page",page);
		model.addAttribute("breaing", breaing);
		model.addAttribute("categoryList", categoryList);
		return "product.breaingList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"product/addBreaing","product/editBreaing"})
	public String modifyBreaing(HttpServletRequest request,Model model,String id)
	{
		Bearing breaing = new Bearing();
		if(!StringUtils.isEmpty(id)){
			breaing =  breaingService.getById(id);
			List<UploadFile> fileList = fileUtil.getFilesByObjectId(id);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("breaing", breaing);
		
		//页面需要的数据字典
		model.addAttribute("dicList", dicUtil.getDicList("BREAING_CATEGORY"));
		
		return "product/nsm/breaingEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="product/saveBreaing")
	public ResultObject saveBreaing(HttpServletRequest request,Model model, Bearing  breaing,String[] fileId)
	{
		ResultObject result = new ResultObject();
		String id = breaing.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				breaing.setUpdateTime(new Date());
				breaingService.updateBreaing(breaing,fileId);
				result.setMessage("产品更新成功");
			}else{
				breaing.setCreateTime(new Date());
				breaing.setUpdateTime(new Date());
				breaing.setId(IdUtil.getUUID());
				breaingService.saveBreaing(breaing,fileId);
				result.setMessage("产品添加成功");
			}
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			logger.error("产品更新保存异常："+e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 删除对象
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="product/deleteBreaing")
	public ResultObject deleteBreaing(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				breaingService.deleteById(id);
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
	
	/**
	 * 推荐首页 取消
	 * @param request
	 * @param model
	 * @param id
	 * @param isHot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="product/changeIsHot")
	public ResultObject changeIsHot(HttpServletRequest request,Model model,String id,Integer isHot)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				Bearing breaing = new Bearing();
				breaing.setId(id);
				breaing.setIsHot(isHot);
				breaingService.updateBreaing(breaing,null);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("更新操作成功");
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
