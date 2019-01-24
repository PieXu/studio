package com.innovate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc:属性文件读取
 * @time: 2017年7月10日 上午10:01:46
 * @author IvanHsu
 */
public class SystemPropertiesUtil {
	private static Logger log = LoggerFactory.getLogger(SystemPropertiesUtil.class);

	private static Properties systemConfig = null;

	public static Properties getSystemConfigProperties()
	{
		if (systemConfig == null) {
			try {
				systemConfig = getPropertiesFile(SystemPropertiesUtil.class.getResource("/").toURI().getPath()
						+ "config" + File.separator + "system_config.properties");
				return systemConfig;
			} catch (URISyntaxException e) {
				e.printStackTrace();
				log.error("读取系统配置文件system_config.properties失败", e);
				return null;
			}
		}
		return systemConfig;
	}

	public static Properties getPropertiesFile(String path)
	{
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			log.warn("配置文件" + path + "不存在");
			e.printStackTrace();
		} catch (IOException e) {
			log.warn("读取配置文件" + path + "失败");
			e.printStackTrace();
		} finally {
		}
		return props;
	}
}
