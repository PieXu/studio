package com.innovate.util;

/**
 * @desc:系统级别用常量
 * @time: 2017年6月17日 下午5:48:25
 * @author IvanHsu
 */
public class CommonCons {
	
	public static final String UPLOAD_FILEPATH_KEY = "file.local.path";
	
	public static final String OPT_SUCCESS = "success";
	
	public static final String DEFAULE_PASS_WORD = "default.password";
	
	public static final String 	SESSION_USER_LOGIN_NAME_KEY = "_SESSION_LOGIN_USER_NAME__";
	
	public static final String 	SESSION_USER_KEY = "loginUser";
	
	public static final String OUTPUT_FILE_PATH_KEY = "gen.output.path";
	
	/*
	 * 未删除
	 */
	public static final String STATUS_NORMAL = "0";

	/*
	 * 删除
	 */
	public static final String STATUS_DELETE = "-1";

	/*
	 * 默认分页每页的条数
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 10;

	/**
	 * 
	 * @desc: 是否 枚举
	 * @time: 2017年7月10日 上午9:59:39
	 * @author IvanHsu
	 */
	public static enum Y_N_ENUM {
		Y, N;
		private Y_N_ENUM() {
		}
	}
}
