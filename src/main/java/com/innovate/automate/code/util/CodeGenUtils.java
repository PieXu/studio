package com.innovate.automate.code.util;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.innovate.automate.code.model.CodeGenSetting;
import com.innovate.util.SystemPropertiesUtil;

public class CodeGenUtils {

	/**
	 * 常量定义
	 */
	public static final String GEN_DB_DRIVER = "gen.db.driver";
	public static final String GEN_DB_URL = "gen.db.url";
	public static final String GEN_DB_USER = "gen.db.user";
	public static final String GEN_DB_PASSWORD = "gen.db.password";
	public static final String GEN_TARGET_PATH = "gen.targetproject";
	public static final String GEN_WORKSPACE_PATH = "gen.work.path";
	
	public static final String GEN_TYPE_XML = "_xml";
	public static final String GEN_TYPE_MODEL = "_model";
	public static final String GEN_TYPE_DAO = "_dao";

	/**
	 * 获取设置信息
	 * @return
	 */
	public static CodeGenSetting getCodeSetting() 
	{
		CodeGenSetting codeSet = null;
		try {
			Properties setPro = SystemPropertiesUtil.getPropertiesFile(SystemPropertiesUtil.class.getResource("/").toURI().getPath() + "config"
							+ File.separator + "gen_config.properties");
			codeSet = new CodeGenSetting();
			if (null != setPro) {
				codeSet.setDbDriver(setPro.getProperty(CodeGenUtils.GEN_DB_DRIVER));
				codeSet.setDbUrl(setPro.getProperty(CodeGenUtils.GEN_DB_URL));
				codeSet.setDbUser(setPro.getProperty(CodeGenUtils.GEN_DB_USER));
				codeSet.setDbPass(setPro.getProperty(CodeGenUtils.GEN_DB_PASSWORD));
				codeSet.setTargetProject(setPro.getProperty(CodeGenUtils.GEN_TARGET_PATH));
				codeSet.setWorkPath(setPro.getProperty(CodeGenUtils.GEN_WORKSPACE_PATH));
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return codeSet;
	}

	/**
	 * 生成diamante代码
	 * @param genSet
	 * @param domainNames
	 * @param genTypesArr
	 * @param workPath
	 */
	public static boolean genMySQLCode(CodeGenSetting genSet, String packagePath,String[] tableNames,String[] domainNames, String[] genTypesArr, String workPath) 
	{
		//警告信息
		List<String> warnings = new ArrayList<String>();
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = null;
		try {
			Configuration config = new Configuration();
			Context context = new Context(ModelType.FLAT);
			context.setId("MySql");
			context.setTargetRuntime("MyBatis3Simple");
			// 基本属性
			context.addProperty("beginningDelimiter", "`");
			context.addProperty("endingDelimiter", "`");
			context.addProperty("javaFileEncoding", "utf-8");
			//plugin
			PluginConfiguration pluginConfiguration = new PluginConfiguration();
			pluginConfiguration.setConfigurationType("com.innovate.automate.plugin.GenPlugin");
			pluginConfiguration.addProperty("mappers", "com.jiujiubujian.base.BaseDao");
			context.addPluginConfiguration(pluginConfiguration );
			
			//将XML中Mapper 改成Dao
			PluginConfiguration xmlReaam = new PluginConfiguration();
			xmlReaam.setConfigurationType("com.innovate.automate.plugin.RenameSqlMapperPlugin");
			xmlReaam.addProperty("searchString", "Mapper");
			xmlReaam.addProperty("replaceString", "Dao");
			context.addPluginConfiguration(xmlReaam);
			//将代码中Mapper 改成Dao
			PluginConfiguration daoReaam = new PluginConfiguration();
			daoReaam.setConfigurationType("com.innovate.automate.plugin.RenameJavaMapperPlugin");
			daoReaam.addProperty("searchString", "Mapper$");
			daoReaam.addProperty("replaceString", "Dao");
			context.addPluginConfiguration(daoReaam);
			
			//commentGenerator
			CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
			commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
			context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
			
			//jdbc 
			JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
			jdbcConnectionConfiguration.setConnectionURL(genSet.getDbUrl());
			jdbcConnectionConfiguration.setDriverClass(genSet.getDbDriver());
			jdbcConnectionConfiguration.setPassword(genSet.getDbPass());
			jdbcConnectionConfiguration.setUserId(genSet.getDbUser());
			context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
			
			//是否包含model类
			if( ArrayUtils.contains(genTypesArr, GEN_TYPE_MODEL)){
				JavaModelGeneratorConfiguration javaModelGenerator = new JavaModelGeneratorConfiguration();
				javaModelGenerator.setTargetPackage(packagePath+".model");
				javaModelGenerator.setTargetProject(workPath);
				context.setJavaModelGeneratorConfiguration(javaModelGenerator);
			}
			//是否包含xml映射文件
			if( ArrayUtils.contains(genTypesArr, GEN_TYPE_XML)){
				SqlMapGeneratorConfiguration sqlMapGenerator = new SqlMapGeneratorConfiguration();
				sqlMapGenerator.setTargetPackage(packagePath+".dao");
				sqlMapGenerator.setTargetProject(workPath);
				context.setSqlMapGeneratorConfiguration(sqlMapGenerator);
			}
			//是否包含dao
			if( ArrayUtils.contains(genTypesArr, GEN_TYPE_DAO)){
				JavaClientGeneratorConfiguration javaClientGenerator = new JavaClientGeneratorConfiguration();
				javaClientGenerator.setTargetPackage(packagePath+".dao");
				javaClientGenerator.setConfigurationType("XMLMAPPER");//ANNOTATEDMAPPER  MIXEDMAPPER MAPPER XMLMAPPER
				javaClientGenerator.setTargetProject(workPath);
				context.setJavaClientGeneratorConfiguration(javaClientGenerator);
			}
			
			//生成哪些表的信息
			List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
			if (!ArrayUtils.isEmpty(tableNames)) {
				for (int i = 0; i < tableNames.length; i++) {
					TableConfiguration tc = new TableConfiguration(context);
					tc.setCountByExampleStatementEnabled(false);
					tc.setDeleteByExampleStatementEnabled(false);
					tc.setSelectByExampleStatementEnabled(false);
					tc.setUpdateByExampleStatementEnabled(false);
					tc.setUpdateByPrimaryKeyStatementEnabled(false);
					tc.setTableName(tableNames[i]);
					tc.setDomainObjectName(domainNames[i]);
					tableConfigurations.add(tc);
				}
			}
			 
			// 代码生成调用
			config.addContext(context);
			myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
