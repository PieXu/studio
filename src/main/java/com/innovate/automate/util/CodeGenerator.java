/*package com.innovate.automate.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.innovate.automate.code.model.CodeGenSetting;
import com.innovate.automate.code.model.ColumnMap;
import com.innovate.automate.code.model.MetaData;
import com.innovate.util.LoggerUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

*//**
 * 代码生成的工具类
 * @author IvanHsu
 * @2018年4月16日 上午11:26:14
 *//*
public class CodeGenerator
{
	private static Configuration cfg;
	private static CodeGenerator codeGenerator;
	private Map<String, Object> data = new HashMap<String, Object>();

	private List<MetaData> metaColumeList = new ArrayList<MetaData>();

	private String tableName;
	private String tableComment;

	// 列的类型相关信息
	private List<ColumnMap> typeMap = new ArrayList<ColumnMap>();

	private CodeGenerator()
	{
		cfg = new Configuration();
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("/template").getFile());
		cfg.setLocale(Locale.CHINA);
		cfg.setDefaultEncoding("utf-8");
		cfg.setEncoding(Locale.CHINA, "utf-8");
		try{
			cfg.setDirectoryForTemplateLoading(file);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private void initDataMap(String tableNamem,String packageBasePath)
	{
		data.put("domainPackage", ModelConstants.PACKAGE_LEVEL_VO);
		data.put("daoPackage", ModelConstants.PACKAGE_LEVEL_DAO);
		data.put("servicePackage", ModelConstants.PACKAGE_LEVEL_SERVICE);
		data.put("actionPackage", ModelConstants.PACKAGE_LEVEL_ACTION);

		data.put("domainClass", ModelConstants.CLASS_VO);
		data.put("daoClass", ModelConstants.CLASS_LEVEL_DAO);
		data.put("serviceClass", ModelConstants.CLASS_LEVEL_SERVICE);
		data.put("actionClass", ModelConstants.CLASS_LEVEL_ACTION);

		//data.put("commonsProject", PubConstant.getValue("commons.package"));
		data.put("packageName", packageBasePath);

		String className = TableUtils.tableNameTransformClass(tableName);

		data.put("className", className);
		data.put("attributeName", TableUtils.firstToLower(className));
		data.put("tableName", tableName);
		data.put("tableComment", tableComment);
		data.put("columns", typeMap);
	}

	*//**
	 * 初始化表的信息 取得列的注释和表的注释信息 
	 * @param tableName
	 *//*
	public void initTypeMapAndCoomment(String tableName,CodeGenSetting genSet)
	{
		this.tableName = tableName;
		try{
			Class.forName(genSet.getDbDriver());
			Connection conn = DriverManager.getConnection(genSet.getDbUrl(),genSet.getDbUser(),genSet.getDbPass());
			Statement st = conn.createStatement();
			*//**
			 *  获得table列的注释
			 *//*
			ResultSet columnComment = st.executeQuery("Select COLUMN_NAME,COLUMN_COMMENT,COLUMN_KEY from INFORMATION_SCHEMA.COLUMNS Where table_name = '"
						+ tableName
						+ "' AND table_schema = '"
						+ genSet.getDbName() + "'");
			while (columnComment.next())
			{
				MetaData metaVo = new MetaData();
				
				metaVo.setMetaColumnName(columnComment.getString(1));
				metaVo.setMetaColumnCommnet(columnComment.getString(2));
				metaVo.setMetaColumnKey(columnComment.getString(3));
				metaColumeList.add(metaVo);
			}
			ResultSet rs = st.executeQuery("select * from " + tableName);
			typeMap = DataMetaUtil.getMetaDataByList(rs, metaColumeList);
			*//**
			 * 表注释
			 *//*
			ResultSet tableComment = st
					.executeQuery("Select TABLE_COMMENT from INFORMATION_SCHEMA.TABLES Where table_schema = '"
							+ genSet.getDbName()
							+ "' AND table_name = '" + tableName + "'");
			while (tableComment.next())
			{
				LoggerUtils.debug(getClass(), tableComment.getString(1));
				this.tableComment = tableComment.getString(1);
			}
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}
		initDataMap(tableName,genSet.getPackageBasePath());
	}

	public void generatorDomain(String outputDir , String packageDir)
	{
		try{
			data.put("packageLevelName", ModelConstants.PACKAGE_LEVEL_VO);
			Template template = cfg.getTemplate("model.ftl");

			StringBuffer md =  new StringBuffer();
			md.append(outputDir);
			md.append(File.separator);
			md.append(packageDir);
			
			md.append(File.separator);
			md.append(ModelConstants.PACKAGE_LEVEL_VO);

			FileUtils.forceMkdir(new File(md.toString()));
			
			File output = new File(md + File.separator + data.get("className").toString() + ModelConstants.CLASS_VO + ModelConstants.JAVA_FILE);
			output.delete();

			OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream(output), "UTF-8");
			template.process(data, writer);
			writer.flush();
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}
		catch (TemplateException e)
		{
			e.printStackTrace();
		}

	}

	public void generatorDao(String outputDir , String packageDir)
	{
		Template template = null;
		try
		{
			data.put("packageLevelName", ModelConstants.PACKAGE_LEVEL_DAO + "."	+ TableUtils.tableNameToLower(tableName));

			template = cfg.getTemplate("dao.ftl");

			StringBuffer md =  new StringBuffer();
			
			md.append(outputDir);
			md.append(File.separator);
			md.append(packageDir);
			
			md.append(File.separator);
			md.append(ModelConstants.PACKAGE_LEVEL_DAO);
			
			org.apache.commons.io.FileUtils.forceMkdir(new File(md.toString()));
			File output = new File(md + File.separator + data.get("className").toString() + ModelConstants.CLASS_LEVEL_DAO + ModelConstants.JAVA_FILE);
			output.delete();
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), "UTF-8");
			try
			{
				template.process(data, writer);
				writer.flush();
			}
			catch (TemplateException e)
			{
				e.printStackTrace();
			}
			finally
			{
				writer.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public void generatorService(String outputDir , String packageDir)
	{
		Template template = null;
		try
		{
			data.put("packageLevelName", ModelConstants.PACKAGE_LEVEL_SERVICE + "." + TableUtils.tableNameToLower(tableName));
			template = cfg.getTemplate("service.ftl");

			StringBuffer md =  new StringBuffer();
			md.append(outputDir);
			md.append(File.separator);
			md.append(packageDir);
			
			md.append(File.separator);
			md.append(ModelConstants.PACKAGE_LEVEL_SERVICE);
			
			FileUtils.forceMkdir(new File(md.toString()));
			File output = new File(md + File.separator
					+ data.get("className").toString()
					+ ModelConstants.CLASS_LEVEL_SERVICE
					+ ModelConstants.JAVA_FILE);
			output.delete();
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(output), "UTF-8");
			try
			{
				template.process(data, writer);
				writer.flush();
			}
			catch (TemplateException e)
			{
				e.printStackTrace();
			}
			finally
			{
				writer.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void generatorController(String outputDir , String packageDir)
	{
		Template template = null;
		try
		{
			data.put("packageLevelName", ModelConstants.PACKAGE_LEVEL_ACTION + "." + TableUtils.tableNameToLower(tableName));
			template = cfg.getTemplate("controller.ftl");
			StringBuffer md =  new StringBuffer();
			md.append(outputDir);
			md.append(File.separator);
			md.append(packageDir);
			
			md.append(File.separator);
			md.append(ModelConstants.PACKAGE_LEVEL_ACTION);
			org.apache.commons.io.FileUtils.forceMkdir(new File(md.toString()));
			
			File output = new File(md + File.separator + data.get("className").toString() + ModelConstants.CLASS_LEVEL_ACTION + ModelConstants.JAVA_FILE);
			output.delete();
			OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream(output), "UTF-8");
			try
			{
				template.process(data, writer);
				writer.flush();
			}
			catch (TemplateException e)
			{
				e.printStackTrace();
			}
			finally
			{
				writer.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	*//**
	 * 获取实例
	 * @return
	 *//*
	public static CodeGenerator getInstance()
	{
		if(null == codeGenerator){
			codeGenerator = new CodeGenerator();
		}
		return codeGenerator;
	}


}
*/