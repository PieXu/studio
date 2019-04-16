package com.innovate.sys.dic.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.innovate.sys.dic.model.Dic;
import com.innovate.sys.dic.model.DicCategory;
import com.innovate.sys.dic.service.IDicCategoryService;
import com.innovate.sys.dic.service.IDicService;
import com.innovate.util.CommonCons;

/**
 * @desc:系统数据字典
 * @time: 2017年6月20日 下午3:58:08
 * @author IvanHsu 
 */
@Controller
public class DicController {
	@Autowired
	private IDicService dicService;
	@Autowired
	private IDicCategoryService dicCategoryService;

	/**
	 * 数据字典分页查询
	 * @param request
	 * @param model
	 * @param dicCateogry
	 * @param pageNum
	 * @return
	 * String
	 * 2017年7月5日
	 */
	@RequestMapping(value="dic/dicCategoryList")
	public String listDicCategory(HttpServletRequest request,Model model,DicCategory dicCateogry,Integer pageNum){
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<DicCategory> page = dicCategoryService.pageDicCategory(dicCateogry);
		model.addAttribute("page",page);
		model.addAttribute("dicCateogry", dicCateogry);
		return "dic/dicCategoryList";
	}
	
	/**
	 *  编辑分类
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"dic/editDicCategory","dic/addDicCategory"})
	public String editDicCategory(HttpServletRequest request,Model model,String id)
	{
		DicCategory dicCategory = new DicCategory();
		if(StringUtils.hasText(id))
			dicCategory =  dicCategoryService.getById(id);
		model.addAttribute("dic", dicCategory);
		
		return "dic/nsm/dicCategoryEdit";
	}
	
	
	/**
	 * 数据字典分类保存
	 * @param request
	 * @param model
	 * @param dic
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"dic/saveDicCategory"})
	public String saveDicCategory(HttpServletRequest request,Model model,DicCategory dicCategory)
	{
		String id = dicCategory.getId();
		if(StringUtils.hasText(id))
		{
			dicCategory.setUpdateTime(new Date());
			dicCategoryService.updateDicCategory(dicCategory);
		}else{
			dicCategory.setCreateTime(new Date());
			dicCategory.setUpdateTime(new Date());
			dicCategory.setStatus(CommonCons.STATUS_NORMAL);
			dicCategoryService.saveDicCategory(dicCategory);
		}
		return "success";
	}
	
	/**
	 * 列表
	 * @param request
	 * @param model
	 * @param dic
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="dic/dicList")
	public String listDic(HttpServletRequest request,Model model,Dic dic,Integer pageNum){
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE); // 核心分页代码  
		Page<Dic> Page = dicService.listDicByCondition(dic);
		model.addAttribute("dicList",Page);
		model.addAttribute("dic", dic);
		model.addAttribute("categoryList", dicCategoryService.listCategoryAll());
		return "dic/dicList";
	}
	
	
	/**
	 *  编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value={"dic/dicEdit","dic/dicAdd"})
	public String dicEdit(HttpServletRequest request,Model model,String id)
	{
		Dic dic = new Dic();
		if(StringUtils.hasText(id))
			dic =  dicService.getDicById(id);
		model.addAttribute("dic", dic);
		model.addAttribute("categoryList", dicCategoryService.listCategoryAll());
		
		return "dic/nsm/dicEdit";
	}
	
	
	/**
	 * 数据字典保存
	 * @param request
	 * @param model
	 * @param dic
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"dic/saveDic"})
	public String saveDic(HttpServletRequest request,Model model,Dic dic)
	{
		String id = dic.getId();
		if(StringUtils.hasText(id))
		{
			dic.setUpdateTime(new Date());
			dicService.updateDic(dic);
		}else{
			dic.setCreateTime(new Date());
			dic.setUpdateTime(new Date());
			dic.setStatus(CommonCons.STATUS_NORMAL);
			dicService.saveDic(dic);
		}
		return "success";
	}
	
	/**
	 * 数据字典删除，逻辑删除
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@ResponseBody
	@RequestMapping(value={"dic/dicDelete"})
	public String dicDelete(HttpServletRequest request,Model model,String id)
	{
		String restult =  "error";
		if(StringUtils.hasText(id))
		{
			Dic dic = new Dic();
			dic.setId(id);
			dic.setUpdateTime(new Date());
			dic.setStatus(CommonCons.STATUS_DELETE);
			dicService.updateDic(dic);
			restult = "success";
		}
		return restult;
	}
	
	
}
