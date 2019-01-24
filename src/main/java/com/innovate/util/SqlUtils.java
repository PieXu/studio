package com.innovate.util;

/**
 * @desc: sql 模糊查询 匹配工具
 * @time: 2017年7月27日 下午1:31:12
 * @author IvanHsu
 */
public class SqlUtils {
	private static final String[] characters = { "%", "_" };
	private static final String HQL_ESCAPE_KEY = "^";

	public static String escape(String likeStr,boolean start,boolean end)
	{
		StringBuffer resultStr = new StringBuffer("'");
		if(start){
			resultStr.append("%");
		}
		if(isNeedEscape(likeStr))
		{
			likeStr = likeStr.replace("_", "^_");
			likeStr = likeStr.replace("%", "^%");
			resultStr.append(likeStr);
			if(end){
				resultStr.append("%");
			}
			resultStr.append("' escape '").append(HQL_ESCAPE_KEY).append("'");
		}else{
			resultStr.append(likeStr);
			if(end)
				resultStr.append("%");
			resultStr.append("'");
		}
		return resultStr.toString();
	}
	
	private static boolean isNeedEscape(String likeStr)
	{
		for (String s : characters) {
			if (likeStr.indexOf(s) > -1) {
				return true;
			}
		}
		return false;
	}
}
