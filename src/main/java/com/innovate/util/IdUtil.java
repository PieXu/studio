package com.innovate.util;

import java.util.UUID;

/**
 * @desc:
 * @time: 2017年7月10日 上午10:59:06
 * @author IvanHsu
 */
public class IdUtil {
	
	/**
	 * UUID 生成
	 * @return
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}

}
