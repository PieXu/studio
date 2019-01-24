package ${packageName}.${packageLevelName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${packageName}.${daoPackage}.${className}${daoClass};
import ${packageName}.${domainPackage}.${className}${domainClass};

@Service("${attributeName}${serviceClass}")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ${className}${serviceClass} 
{
	
	@Autowired
	private ${className}${daoClass} ${attributeName}${daoClass};
	
	/**
	  * 依据主键编号ID查找  
	  * @param Integer ${columns[0].columnName}
	  * @return  ${className}${domainClass}
	  */
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly=true)
	public ${className}${domainClass} findById(Integer ${columns[0].columnName})
	{
		${className}${domainClass} ${attributeName} = new ${className}${domainClass}();
		if( null != ${columns[0].columnName} )
		{
			${attributeName} = ${attributeName}${daoClass}.findById(${columns[0].columnName})
		}
		return ${attributeName};
	}
	
	/**
	  * 保存对象信息   
	  * @param Integer ${columns[0].columnName}
	  * @return  Integer
	  */
	public void save(${className}${domainClass} ${attributeName})
	{
		if( ${attributeName}!=null )
		{
			${attributeName}${daoClass}.save(${attributeName});
		}
	}
	
	/**
	  * 依据主键编号ID删除对象  
	  * @param Integer ${columns[0].columnName}
	  * @return  Integer 
	  */
	public int deleteById(Integer ${columns[0].columnName})
	{
		int result = 0 ;
		if( null != ${columns[0].columnName} )
		{
			result = ${attributeName}${daoClass}.deleteById(${columns[0].columnName});
		}
		return result;
	}
	
	/**
	  * 修改更新对象信息   
	  * @param ${className}${domainClass} ${attributeName}
	  * @return  Integer 
	  */
	public int update(${className}${domainClass} ${attributeName})
	{
		if( null != ${attributeName})
		{
			return ${attributeName}${daoClass}.update(${attributeName});
		}
		return 0;
	}
	
}