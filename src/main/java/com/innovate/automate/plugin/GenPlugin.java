package com.innovate.automate.plugin;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;

public class GenPlugin extends PluginAdapter{

	private Set<String> mappers = new HashSet<String>();
	// 注释生成器  
	private CommentGeneratorConfiguration commentCfg;
	
	@Override
	public boolean validate(List<String> paramList) {
		return true;
	}
	@Override
	public void setContext(Context context) {
		this.context = context;
		// 设置默认的注释生成器  
	    commentCfg = new CommentGeneratorConfiguration();  
	    commentCfg.setConfigurationType(GenCommentGenerator.class.getCanonicalName());  
	    context.setCommentGeneratorConfiguration(commentCfg);  
	    // 支持oracle获取注释#114  
	    context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true"); 
	}
	
	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String mappers = this.properties.getProperty("mappers");
		for (String mapper : mappers.split(",")) {
			this.mappers.add(mapper);
		}
	}
	
	/**
	 * 生成的Mapper接口
	 * @param interfaze
	 * @param topLevelClass
	 * @param introspectedTable
	 * @return
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		// 获取实体类
		FullyQualifiedJavaType entityType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		// import接口
		for (String mapper : mappers) {
			interfaze.addImportedType(new FullyQualifiedJavaType(mapper));
			interfaze.addSuperInterface(new FullyQualifiedJavaType(mapper + "<" + entityType.getShortName() + ">"));
		}
		// import实体类
		interfaze.addImportedType(entityType);
		return true;
	}

	/**
	 * 拼装SQL语句生成Mapper接口映射文件
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		XmlElement rootElement = document.getRootElement();
		// 数据库表名
		String tableName = introspectedTable.getFullyQualifiedTableNameAtRuntime();
		// 主键
		IntrospectedColumn pkColumn = introspectedTable.getPrimaryKeyColumns().get(0);

		// 公共字段
		StringBuilder columnSQL = new StringBuilder();
		// IF判断语句
		StringBuilder ifSQL = new StringBuilder();
		// 要插入的字段(排除自增主键)
		StringBuilder saveColumn = new StringBuilder("insert into ").append(tableName).append("(\n");
		// 要保存的值
		StringBuilder saveValue = new StringBuilder("(\n");
		// 拼装更新字段
		StringBuilder updateSQL = new StringBuilder("update ").append(tableName).append(" set ").append(pkColumn.getActualColumnName())
				.append(" = #{item.").append(pkColumn.getJavaProperty()).append("}\n");

		// 数据库字段名
		String columnName = null;
		// java字段名
		String javaProperty = null;
		for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			columnName = MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn);
			javaProperty = introspectedColumn.getJavaProperty();
			// 拼接字段
			columnSQL.append(columnName).append(",");
			// 拼接IF语句
			ifSQL.append("      <if test=\"null != item.").append(javaProperty).append(" and '' != item.").append(javaProperty).append("\">");
			ifSQL.append("and ").append(columnName).append(" = #{item.").append(javaProperty).append("}</if>\n");

			// 拼接SQL
			if (!introspectedColumn.isAutoIncrement()) {
				saveColumn.append("\t  <if test=\"null != item.").append(javaProperty).append("\">, ").append(columnName).append("</if>\n");

				saveValue.append("\t  <if test=\"null != item.").append(javaProperty).append("\">, ").append("#{item.").append(javaProperty)
						.append("}</if>\n");

				// 时间格式用now()作为值
				/*
				 * if(Types.TIMESTAMP == introspectedColumn.getJdbcType()){
				 * saveValue.append(", now()"); }else{ saveValue.append(
				 * ", #{item.").append(javaProperty).append("}"); }
				 */

				updateSQL.append("      <if test=\"null != item.").append(javaProperty).append("\">");
				updateSQL.append(", ").append(columnName).append(" = #{item.").append(javaProperty).append("}</if>\n");
			}
		}
		String columns = columnSQL.substring(0, columnSQL.length() - 1);
		rootElement.addElement(createSql("sql_columns", columns));

		String whereSQL = MessageFormat.format("<where>\n{0}\t</where>", ifSQL.toString());
		rootElement.addElement(createSql("sql_where", whereSQL));

		rootElement.addElement(createSelect("selectById", tableName, pkColumn));
		rootElement.addElement(createSelect("selectOne", tableName, null));
		rootElement.addElement(createSelect("selectList", tableName, null));
		rootElement.addElement(createSelect("selectPage", tableName, null));

		rootElement.addElement(createSql("sql_save_columns", saveColumn.append("\t) values").toString().replaceFirst(",", "")));
		rootElement.addElement(createSql("sql_save_values", saveValue.append("\t)").toString().replaceFirst(",", "")));
		rootElement.addElement(createSave("save", pkColumn));
		rootElement.addElement(createSave("batchSave", null));

		updateSQL.append("\twhere ").append(pkColumn.getActualColumnName()).append(" = #{item.").append(pkColumn.getJavaProperty()).append("}");
		rootElement.addElement(createSql("sql_update", updateSQL.toString()));

		rootElement.addElement(createUpdate("update"));
		rootElement.addElement(createUpdate("batchUpdate"));

		rootElement.addElement(createDels(tableName, pkColumn, "delArray", "array"));
		rootElement.addElement(createDels(tableName, pkColumn, "delList", "list"));
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	/**
	 * 公共SQL
	 * @param id
	 * @param sqlStr
	 * @return
	 */
	private XmlElement createSql(String id, String sqlStr) {
		XmlElement sql = new XmlElement("sql");
		sql.addAttribute(new Attribute("id", id));
		sql.addElement(new TextElement(sqlStr));
		return sql;
	}

	/**
	 * 查询
	 * @param id
	 * @param tableName
	 * @param pkColumn
	 * @return
	 */
	private XmlElement createSelect(String id, String tableName, IntrospectedColumn pkColumn) {
		XmlElement select = new XmlElement("select");
		select.addAttribute(new Attribute("id", id));
		select.addAttribute(new Attribute("resultMap", "BaseResultMap"));

		StringBuilder selectStr = new StringBuilder("select <include refid=\"sql_columns\" /> from ").append(tableName);
		if (null != pkColumn) {
			selectStr.append(" where ").append(pkColumn.getActualColumnName()).append(" = #{").append(pkColumn.getJavaProperty()).append("}");
		} else {
			selectStr.append(" <include refid=\"sql_where\" />");
		}
		if ("selectPage".equals(id)) {
			selectStr.append(" limit #{page.startRow}, #{page.pageSize}");
		}
		select.addElement(new TextElement(selectStr.toString()));
		return select;
	}

	/**
	 * 保存
	 * @param id
	 * @param pkColumn
	 * @return
	 */
	private XmlElement createSave(String id, IntrospectedColumn pkColumn) {
		XmlElement save = new XmlElement("insert");
		save.addAttribute(new Attribute("id", id));
		if (null != pkColumn) {
			save.addAttribute(new Attribute("keyProperty", "item." + pkColumn.getJavaProperty()));
			save.addAttribute(new Attribute("useGeneratedKeys", "true"));
			save.addElement(new TextElement("<include refid=\"sql_save_columns\" /><include refid=\"sql_save_values\" />"));
		} else {
			StringBuilder saveStr = new StringBuilder(
					"<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" close=\"\">\n\t  ")
							.append("<include refid=\"sql_save_columns\" /><include refid=\"sql_save_values\" />\n\t</foreach>");
			save.addElement(new TextElement(saveStr.toString()));
		}
		return save;
	}

	/**
	 * 更新
	 * @param id
	 * @return
	 */
	private XmlElement createUpdate(String id) {
		XmlElement update = new XmlElement("update");
		update.addAttribute(new Attribute("id", id));
		if ("update".equals(id)) {
			update.addElement(new TextElement("<include refid=\"sql_update\" />"));
		} else {
			update.addElement(new TextElement(
					"<foreach collection=\"list\" index=\"index\" item=\"item\" open=\"\" separator=\";\" close=\"\">\n\t  <include refid=\"sql_update\" />\n\t</foreach>"));
		}
		return update;
	}

	/**
	 * 删除
	 * @param tableName
	 * @param pkColumn
	 * @param method
	 * @param type
	 * @return
	 */
	private XmlElement createDels(String tableName, IntrospectedColumn pkColumn, String method, String type) {
		XmlElement delete = new XmlElement("delete");
		delete.addAttribute(new Attribute("id", method));
		StringBuilder deleteStr = new StringBuilder("delete from ").append(tableName).append(" where ").append(pkColumn.getActualColumnName())
				.append(" in\n\t")
				.append("<foreach collection=\"").append(type)
				.append("\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>");
		delete.addElement(new TextElement(deleteStr.toString()));
		return delete;
	}

	// 以下设置为false,取消生成默认增删查改xml
	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		return false;
	}

	
	
}
