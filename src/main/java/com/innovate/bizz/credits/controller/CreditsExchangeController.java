package com.innovate.bizz.credits.controller;

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
import com.innovate.bizz.credits.model.CreditsExchange;
import com.innovate.bizz.credits.service.ICreditsService;
import com.innovate.bizz.util.BizzUtils;
import com.innovate.util.CommonCons;
import com.innovate.util.ResultObject;

@Controller
public class CreditsExchangeController extends BaseController{

	@Autowired
	private ICreditsService creditsService;
	
	/**      
	 * 商品兑换记录查询
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * String
	 * 2017年6月20日
	 */
	@RequestMapping(value="credits/listExchange")
	public String listCredits(HttpServletRequest request,Model model,CreditsExchange exchange,Integer pageNum)
	{
		pageNum = null == pageNum || pageNum == 0 ? 1 : pageNum;
		PageHelper.startPage(pageNum,CommonCons.DEFAULT_PAGE_SIZE);
		Page<CreditsExchange> page = creditsService.listCreditsExchange(exchange);
		Map<String, String> exchangeStatus = BizzUtils.getCreditsExchangeStatus();
		model.addAttribute("page",page);
		model.addAttribute("exchange", exchange);
		model.addAttribute("exchangeStatus", exchangeStatus);
		return "credits/list/exchangeList";
	}
	
	/**
	 * 确认 
	 * @param request
	 * @param model
	 * @param id
	 * @param isHot
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="credits/checkExchange")
	public ResultObject changeIsHot(HttpServletRequest request,Model model,String id,String status)
	{
		ResultObject result = new ResultObject();
		if(!StringUtils.isEmpty(id)){
			try{
				CreditsExchange exchage = new  CreditsExchange();
				exchage.setId(id);
				exchage.setStatus(status);
				creditsService.updateCreditsExchange(exchage);
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
