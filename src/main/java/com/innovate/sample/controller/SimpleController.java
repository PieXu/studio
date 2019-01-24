package com.innovate.sample.controller;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovate.basic.base.BaseController;
import com.innovate.cache.RedisDistributeLockUtils;
import com.innovate.cache.RedisUtils;
import com.innovate.commom.utils.IdUtils;

/**
 * 实例演示 controller
 * @author IvanHsu
 * @2018年9月4日 上午9:44:19
 */
@Controller
@RequestMapping("sample")
public class SimpleController extends BaseController{

	/**
	 * redis取topN的实例演示，前台用highcharts展示数据
	 * @param request
	 * @param model
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("redisTopN")
	public String redisTopN(HttpServletRequest request,Model model)
	{
		String zKey = "sales_top_10";
		String[] citys = new String[]{"上海销售部","北京销售部","东京销售公司","天津销售中心","莫斯科销售分公司","南京销售公司","深圳销售部","广州销售公司","欧洲销售中心","武汉公司"};
		StringBuffer data = new StringBuffer();
		data.append("[");
		//动态的封装数据
		for(String city : citys)
		{
			RedisUtils.addZSet(zKey, city,Double.parseDouble(getRandoSales()));
		}
		
		Set<ZSetOperations.TypedTuple<Object>> tuples = RedisUtils.getZSetRangeWithScore(zKey, 0, 10, true);
		Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator(); 
		while (iterator.hasNext()) { 
			ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
			data.append("[")
			.append("'").append(typedTuple.getValue())
			.append("',").append(typedTuple.getScore()).append("],");
		}
		String str = data.toString().substring(0,data.toString().length()-1); 
		str += "]"; 
		model.addAttribute("data", str);
		return "sample/list/redis";
	}
	
	
	/**
	 * 
	 */
	private static String getRandoSales() {
		DecimalFormat   df   = new DecimalFormat("######0.00"); 
		return df.format(100 * Math.random());
	}
	
	
	/**
	 * 分布式锁的测试
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("sample")
	public String samplePage(HttpServletRequest request,Model model)
	{
		return "sample/list/sample";
	}
	
	/**
	 * 分布式锁的测试
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "lock", produces = "text/plain; charset=utf-8")
	public String redisLock(HttpServletRequest request,Model model)
	{
		String userId = IdUtils.getUUID();
		String msg = "";
		boolean bol = RedisDistributeLockUtils.tryLock("resource",userId, 5000, 90000);
		if(bol){
			 msg = userId+ "获得了锁";
//	         RedisDistributeLockUtils.releaseLock("resource",userId);
		}else{
			 Object lockUser = RedisDistributeLockUtils.getLockUser("resource");
			 if(null != lockUser){msg = "等待"+lockUser.toString()+"释放锁";}
			 
		}
		System.out.println(msg);
		return msg;
	}
	
}


