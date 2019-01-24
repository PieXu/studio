package ${packageName}.${packageLevelName};

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.frame.base.BaseHibernateDao;
import ${packageName}.${domainPackage}.${className}${domainClass};


@Repository("${attributeName}${daoClass}")
public class ${className}${daoClass} extends BaseHibernateDao
{

	private Logger log = Logger.getLogger(${className}${daoClass}.class);
	
	/**
	  * 依据主键编号ID查找  
	  * @param   Integer ${columns[0].columnName}
	  * @return  ${className}${domainClass}
	  */
	public ${className}${domainClass} findById( Integer ${columns[0].columnName}) 
	{
		${className}${domainClass} ${attributeName} = new ${className}${domainClass}();
		if( null != ${columns[0].columnName} )
		{
			${attributeName} = getHibernateTemplate().get(${className}${domainClass}.class, ${columns[0].columnName})
		}
		return ${attributeName};	
	}
	
	/**
	 * 信息保存
	 * @param   ${className}${domainClass} ${attributeName}
	 * @return  Integer
	 */
	public void save( ${className}${domainClass} ${attributeName}) 
	{
		if( ${attributeName}!=null )
		{
			getHibernateTemplate().save(${attributeName});
		}
	}

	/**
	 * 按照主键删除信息 
	 * @param   Integer ${columns[0].columnName}
	 * @return  Integer
	 */
	public int deleteById( Integer ${columns[0].columnName}) 
	{
		if( ${columns[0].columnName} !=null )
		{
			${className}${domainClass} ${attributeName} = new ${className}${domainClass}();
			${attributeName}.set${columns[0].firstColumnName}(${columns[0].columnName});
			getHibernateTemplate().delete(${attributeName});
		}
	}
	
	/**
	 * 信息更新修改 
	 * @param   ${className}${domainClass} ${attributeName}
	 * @return  Integer
	 */
	public int update( ${className}${domainClass} ${attributeName}) 
	{
		if( ${attributeName}!=null )
		{
			getHibernateTemplate().update(${attributeName});
		}
	}
		
}