package com.innovate.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import org.apache.shiro.crypto.hash.SimpleHash;

import sun.misc.BASE64Encoder;

/**
 * @time: 2017年6月19日 下午5:35:03
 * @author IvanHsu
 */
@SuppressWarnings("restriction")
public class MD5Utils {

	
	/**
	 * 加密
	 * @param str
	 * @return
	 * String
	 * 2017年6月19日
	 */
	public static String encoderPassword(String salt,String password)
	{
		return new SimpleHash("MD5",password,salt,1024).toHex();
	}
	
	/**
	 * 加密
	 * @param str
	 * @return
	 * String
	 * 2017年6月19日
	 */
	public static String encoder(String str)
	{
		String newstr = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}

	/**
	 * 取盐值
	 * @param length
	 * @return
	 * String
	 * 2017年6月19日
	 */
	public static String getSalt(int length)
	{
		String base = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	
	public static void main(String[] args)
	{
		String salt = getSalt(4);
		System.out.println(salt);
	new Date().getTime();
		System.out.println(MD5Utils.encoder("admin123456"+new Date().getTime()));
	}
}
