package com.innovate.bizz.credits.controller;

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
import com.innovate.bizz.credits.model.CreditsExchange;
import com.innovate.bizz.credits.model.CreditsProduct;
import com.innovate.bizz.credits.service.ICreditsService;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.FileUtil;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

@Controller
public class CreditsController extends BaseController{

	@Autowired
	private ICreditsService creditsService;
	
	@Autowired
	private FileUtil fileUtil;
	
	/**      
	 * 用户信息列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="credits/listCredits")
	public String listCredits(HttpServletRequest request,Model model,CreditsProduct credits,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<CreditsProduct> page = creditsService.listCreditsProduct(credits);
		model.addAttribute("page",page);
		model.addAttribute("credits", credits);
		return "credits.creditsList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"credits/addCredits","credits/editCredits"})
	public String modifyCreditsProduct(HttpServletRequest request,Model model,String id)
	{
		CreditsProduct credits = new CreditsProduct();
		if(!StringUtils.isEmpty(id)){
			credits =  creditsService.getById(id);
			List<UploadFile> fileList = fileUtil.getFilesByObjectId(id);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("credits", credits);
		
		return "credits/nsm/creditsEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="credits/saveCredits")
	public ResultObject saveCreditsProduct(HttpServletRequest request,Model model, CreditsProduct credits,String[] fileId)
	{
		ResultObject result = new ResultObject();
		String id = credits.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				credits.setUpdateTime(new Date());
				credits.setOperator(SessionUtils.getCurrentUserId());
				creditsService.updateCreditsProduct(credits,fileId);
				result.setMessage("产品更新成功");
			}else{
				credits.setCreateTime(new Date());
				credits.setUpdateTime(new Date());
				credits.setId(IdUtil.getUUID());
				credits.setOperator(SessionUtils.getCurrentUserId());
				creditsService.saveCreditsProduct(credits,fileId);
				result.setMessage("产品添加成功");
			}
			result.setResult(ResultObject.OPERATE_RESULT.success.toString());
		}catch (Exception e) {
			result.setResult(ResultObject.OPERATE_RESULT.fail.toString());
			result.setMessage("操作失败");
			LoggerUtils.error(getClass(), "产品更新保存异常："+e.getMessage(), e);
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
	@RequestMapping(value="credits/deleteCredits")
	public ResultObject deleteCredits(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				creditsService.deleteById(id);
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
	 * 是否上架
	 * @param request
	 * @param model
	 * @param id
	 * @param isHot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="credits/changeIsHot")
	public ResultObject changeIsHot(HttpServletRequest request,Model model,String id,String status)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				CreditsProduct credits = new  CreditsProduct();
				credits.setId(id);
				credits.setStatus(status);
				creditsService.updateCreditsProduct(credits, null);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("操作成功");
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
