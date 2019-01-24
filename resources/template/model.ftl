package ${packageName}.${packageLevelName};

import com.innovate.basic.base.BaseModel

/**
 * ${tableComment}域对象
 */
public class ${className}${domainClass} extends BaseModel
{
  
   <#list columns as column>
	private ${column.javaDefType} ${column.columnName};   <#if column.columnCommnet!=''>//${column.columnCommnet}</#if> 
   </#list>

   <#list columns as column>
	public void set${column.firstColumnName}(${column.javaDefType} ${column.columnName}) 
	{
		this.${column.columnName} = ${column.columnName};
	}
	public ${column.javaDefType} get${column.firstColumnName}() 
	{
		return ${column.columnName};
	}
   </#list>

}