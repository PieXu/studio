package com.innovate.bizz.shop.controller;

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
import com.innovate.bizz.shop.model.Shop;
import com.innovate.bizz.shop.service.IShopService;
import com.innovate.bizz.util.BizzUtils;
import com.innovate.sys.dic.service.DicUtil;
import com.innovate.sys.file.model.UploadFile;
import com.innovate.sys.file.service.FileUtil;
import com.innovate.util.CommonCons;
import com.innovate.util.IdUtil;
import com.innovate.util.LoggerUtils;
import com.innovate.util.ResultObject;
import com.innovate.util.SessionUtils;

/**
 * 
* <p>Title: ShopController</p>
* <p>Description: 商户管理</p>
* <p>Company: easysoft.ltd</p> 
* @author IvanHsu
* @date 2019年5月15日
 */
@Controller
public class ShopController extends BaseController{

	private final String SHOP_ACCESS_STATE = "SHOP_ACCESS_STATE";
	@Autowired
	private IShopService shopService;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private DicUtil dicUtil;
	
	/**      
	 * 列表查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="shop/listShop")
	public String listGoods(HttpServletRequest request,Model model,Shop shop,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<Shop> page = shopService.listShop(shop);
		model.addAttribute("page",page);
		model.addAttribute("shop", shop);
		model.addAttribute("shopStateList", dicUtil.getDicList(SHOP_ACCESS_STATE));
		return "shop/shopList";
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"shop/addShop","shop/editShop"})
	public String modifyShop(HttpServletRequest request,Model model,String id)
	{
		Shop shop = new Shop();
		if(!StringUtils.isEmpty(id)){
			shop =  shopService.getById(id);
			List<UploadFile> fileList = fileUtil.getFilesByObjectId(id);
			model.addAttribute("fileList", fileList);
		}
		model.addAttribute("shop", shop);
		model.addAttribute("shopState",dicUtil.getDicList(SHOP_ACCESS_STATE));
		
		return "shop/nsm/shopEdit";
	}
	
	/**
	 * 保存
	 * @param request
	 * @param model
	 * @param breaing
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="shop/saveShop")
	public ResultObject saveShop(HttpServletRequest request,Model model, Shop shop,String[] fileId)
	{
		ResultObject result = new ResultObject();
		String id = shop.getId();
		try{
			if(!StringUtils.isEmpty(id)){
				shop.setUpdateTime(new Date());
				shop.setOperator(SessionUtils.getCurrentUserId());
				shopService.updateShop(shop,fileId);
				result.setMessage("产品更新成功");
			}else{
				shop.setCreateTime(new Date());
				shop.setUpdateTime(new Date());
				shop.setId(IdUtil.getUUID());
				shop.setOperator(SessionUtils.getCurrentUserId());
				shopService.saveShop(shop,fileId);
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
	 * 删除
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="shop/deleteShop")
	public ResultObject deleteShop(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				shopService.deleteById(id);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("该商铺已删除");
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
	 * 授权商铺可以访问
	 * @param request
	 * @param model
	 * @param id
	 * @param isHot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="shop/grant")
	public ResultObject shopGrant(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				Shop shop = new Shop();
				shop.setId(id);
				shopService.updateShop(shop,null);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("授权操作成功");
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
	 * 
	* <p>Title: shopRevoke</p>
	* <p>Description: 收回授权 </p>
	* @param request
	* @param model
	* @param id
	* @param isHot
	* @return
	 */
	@ResponseBody
	@RequestMapping(value="shop/revoke")
	public ResultObject shopRevoke(HttpServletRequest request,Model model,String id)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				Shop shop = new Shop();
				shop.setId(id);
				shopService.updateShop(shop,null);
				result.setResult(ResultObject.OPERATE_RESULT.success.toString());
				result.setMessage("授权收回成功");
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
