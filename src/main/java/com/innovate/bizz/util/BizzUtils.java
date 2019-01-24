package com.innovate.bizz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.innovate.util.SystemPropertiesUtil;

public class BizzUtils {

	/**
	 * @param type
	 * @return
	 */
	public static String getNoticeNameByType(String type)
	{
		String name = "";
		Map<String,String> noticeMap = getNoticeType();
		if(!noticeMap.isEmpty()){
			name = noticeMap.get(type);
		}
		return name;
	}
	
	/**
	 * 消息列别
	 * @return
	 */
	public static Map<String,String> getNoticeType()
	{
		Map<String,String> noticeTypeMap = new HashMap<String,String>(); 
		Properties pro = SystemPropertiesUtil.getSystemConfigProperties();
		if(null!=pro){
			String typeArray = (String) pro.get("notice_type");
			if(StringUtils.isNotBlank(typeArray)){
				String[] types = typeArray.split(";");
				for(String str : types){
					noticeTypeMap.put(str.substring(0, str.indexOf("#")),str.substring(str.indexOf("#")+1));
				}
			}
		}
		return noticeTypeMap;
	}
	
	
	/**
	 * 产品类型
	 * @return
	 */
	public static Map<String,String> getGoodsType()
	{
		Map<String,String> goodsMap = new HashMap<String,String>(); 
		Properties pro = SystemPropertiesUtil.getSystemConfigProperties();
		if(null!=pro){
			String typeArray = (String) pro.get("goods_type");
			if(StringUtils.isNotBlank(typeArray)){
				String[] types = typeArray.split(";");
				for(String str : types){
					goodsMap.put(str.substring(0, str.indexOf("#")),str.substring(str.indexOf("#")+1));
				}
			}
		}
		return goodsMap;
	}
	
	
	/**
	 * @return
	 */
	public static Map<String,String> getOrderStatus()
	{
		Map<String,String> orderStatusMap = new HashMap<String,String>(); 
		Properties pro = SystemPropertiesUtil.getSystemConfigProperties();
		if(null!=pro){
			String typeArray = (String) pro.get("order_status");
			if(StringUtils.isNotBlank(typeArray)){
				String[] types = typeArray.split(";");
				for(String str : types){
					orderStatusMap.put(str.substring(0, str.indexOf("#")),str.substring(str.indexOf("#")+1));
				}
			}
		}
		return orderStatusMap;
	}

	public static Map<String, String> getCreditsExchangeStatus() {
		Map<String,String> exchangeStatus = new HashMap<String,String>(); 
		Properties pro = SystemPropertiesUtil.getSystemConfigProperties();
		if(null!=pro){
			String typeArray = (String) pro.get("exchange_status");
			if(StringUtils.isNotBlank(typeArray)){
				String[] types = typeArray.split(";");
				for(String str : types){
					exchangeStatus.put(str.substring(0, str.indexOf("#")),str.substring(str.indexOf("#")+1));
				}
			}
		}
		return exchangeStatus;
	}
	
}
