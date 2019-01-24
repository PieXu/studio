package com.innovate.automate.plugin;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

public class GenCommentGenerator implements CommentGenerator{

	@Override
	public void addClassComment(InnerClass paramInnerClass, IntrospectedTable paramIntrospectedTable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClassComment(InnerClass paramInnerClass, IntrospectedTable paramIntrospectedTable,
			boolean paramBoolean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComment(XmlElement paramXmlElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addConfigurationProperties(Properties paramProperties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEnumComment(InnerEnum paramInnerEnum, IntrospectedTable paramIntrospectedTable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFieldComment(Field paramField, IntrospectedTable paramIntrospectedTable) {
		
	}

	@Override
	public void addFieldComment(Field paramField, IntrospectedTable paramIntrospectedTable,
			IntrospectedColumn paramIntrospectedColumn) {
		if (StringUtility.stringHasValue(paramIntrospectedColumn.getRemarks())) {  
			paramField.addJavaDocLine("//" + paramIntrospectedColumn.getRemarks());  
	    }  
	}

	@Override
	public void addGeneralMethodComment(Method paramMethod, IntrospectedTable paramIntrospectedTable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable paramIntrospectedTable,
			IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();  
	    method.addJavaDocLine("/**");  
	    if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {  
	        sb.append(" * 获取");  
	        sb.append(introspectedColumn.getRemarks());  
	        method.addJavaDocLine(sb.toString());  
	        method.addJavaDocLine(" *");  
	    }  
	    sb.setLength(0);  
	    sb.append(" * @return ");  
	    sb.append(introspectedColumn.getActualColumnName());  
	    if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {  
	        sb.append(" - ");  
	        sb.append(introspectedColumn.getRemarks());  
	    }  
	    method.addJavaDocLine(sb.toString());  
	    method.addJavaDocLine(" */");  
		
	}

	@Override
	public void addJavaFileComment(CompilationUnit paramCompilationUnit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModelClassComment(TopLevelClass paramTopLevelClass, IntrospectedTable paramIntrospectedTable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRootComment(XmlElement paramXmlElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable paramIntrospectedTable,
			IntrospectedColumn introspectedColumn) {
		 StringBuilder sb = new StringBuilder();  
		    method.addJavaDocLine("/**");  
		    if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {  
		        sb.append(" * 设置");  
		        sb.append(introspectedColumn.getRemarks());  
		        method.addJavaDocLine(sb.toString());  
		        method.addJavaDocLine(" *");  
		    }  
		    Parameter parm = method.getParameters().get(0);  
		    sb.setLength(0);  
		    sb.append(" * @param ");  
		    sb.append(parm.getName());  
		    if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {  
		        sb.append(" ");  
		        sb.append(introspectedColumn.getRemarks());  
		    }  
		    method.addJavaDocLine(sb.toString());  
		    method.addJavaDocLine(" */");  
		
	}

}
