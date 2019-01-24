package com.innovate.bizz.goods.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.innovate.bizz.goods.model.Goods;
import com.innovate.bizz.goods.service.IGoodsService;
import com.innovate.bizz.util.BizzUtils;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.FileUtil;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

@Controller
public class GoodsController extends BaseController{

	@Autowired
	private IGoodsService goodsService;
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
	@RequestMapping(value="goods/listGoods")
	public String listGoods(HttpServletRequest request,Model model,Goods goods,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Goods> page = goodsService.listGoods(goods);
		Map<String, String> goodsType = BizzUtils.getGoodsType();
		model.addAttribute("page",page);
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", goodsType);
		return "goods/list/goodsList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"goods/addGoods","goods/editGoods"})
	public String modifyGoods(HttpServletRequest request,Model model,String id)
	{
		Goods goods = new Goods();
		if(!StringUtils.isEmpty(id)){
			goods =  goodsService.getById(id);
			List<UploadFile> fileList = fileUtil.getFilesByObjectId(id);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("goods", goods);
		model.addAttribute("goodsType", BizzUtils.getGoodsType());
		
		return "goods/nsm/goodsEdit";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="goods/saveGoods")
	public ResultObject saveGoods(HttpServletRequest request,Model model, Goods goods,String[] fileId)
	{
		ResultObject result = new ResultObject();
		String id = goods.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				goods.setUpdateTime(new Date());
				goods.setOperator(SessionUtils.getCurrentUserId());
				goodsService.updateGoods(goods,fileId);
				result.setMessage("产品更新成功");
			}else{
				goods.setCreateTime(new Date());
				goods.setUpdateTime(new Date());
				goods.setId(IdUtil.getUUID());
				goods.setOperator(SessionUtils.getCurrentUserId());
				goodsService.saveGoods(goods,fileId);
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
	@RequestMapping(value="goods/deleteGoods")
	public ResultObject deleteGoods(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				goodsService.deleteById(id);
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
	@RequestMapping(value="goods/changeIsHot")
	public ResultObject changeIsHot(HttpServletRequest request,Model model,String id,String isHot)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				Goods goods = new Goods();
				goods.setId(id);
				goods.setGoodsTag(isHot);
				goodsService.updateGoods(goods,null);
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
