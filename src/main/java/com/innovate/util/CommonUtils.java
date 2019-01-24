package com.innovate.util;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import com.innovate.sys.resource.model.Resource;

public class CommonUtils {

	/**
	 * 获取真实的登录的ip
	 * @param request
	 * @return
	 */
	public static String getLoginIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 把数组转换成set
	 * @param array
	 * @return
	 */
	public static Set<?> array2Set(Object[] array) {
		Set<Object> set = new TreeSet<Object>();
		for (Object id : array) {
			if(null != id){
				set.add(id);
			}
		}
		return set;
	}
	
}
