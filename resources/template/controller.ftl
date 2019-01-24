package ${packageName}.${packageLevelName};

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${packageName}.${servicePackage}.${className}${serviceClass};
import ${packageName}.${domainPackage}.${className}${domainClass};


@Controller
@RequestMapping("/${attributeName}")
public class ${className}${actionClass}{

	@Autowired
	private ${className}${serviceClass} ${attributeName}${serviceClass};

	/** 
	 * 进入新增 或者 修改页面 
	 * @param  HttpServletRequest request,HttpServletResponse response
	 * @return String
	 */
	@RequestMapping(value="/modify")
	public String modify( HttpServletRequest request,HttpServletResponse response ) 
	{
		String id = request.getParameter("id");
		
		${className}${domainClass} ${attributeName} = new ${className}${domainClass}();
		
		if( null != id && id.trim().length()>0 )
		{
			Integer domainId = Integer.parseInt(id);
			${className}${domainClass} ${attributeName} = (${className}${domainClass})${attributeName}${serviceClass}.findById(domainId);
		}
		
		request.setAttribute("${attributeName}",${attributeName});
		
		return "/${attributeName}/modi";
	}
	
	/**
	 *  保存修改或者信息信息 
 	 * @param  HttpServletRequest request,HttpServletResponse response , ${className}${domainClass} ${attributeName}
	 * @return String
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(${className}${domainClass} ${attributeName},HttpServletRequest request,HttpServletResponse response)
	{
		if( null!= ${attributeName} )
		{
			Integer ${columns[0].columnName} = ${attributeName}.get${columns[0].firstColumnName}();
			
			if(${columns[0].columnName}!=null)
			{
				${attributeName}${serviceClass}.update(${attributeName});
			}
			else ${attributeName}${serviceClass}.save(${attributeName});
		}
		
		return "redirect:/${attributeName}/list";
	}
	
	
	/** 
	* 按照主键删除 
	* @param HttpServletRequest request
	* @return String 
	*/
	@RequestMapping(value="/delete")
	public String delete( HttpServletRequest request ) 
	{
		String id = request.getParameter("id");
		if( null!=id && id.trim().length()>0 )
		{
			Integer domainId = Integer.parseInt(id);
			${attributeName}${serviceClass}.deleteById(domainId);
		}
		return "redirect:/${attributeName}/list";
	}
	
	
	/**
	* 查看详细信息    
	* @param HttpServletRequest request
	* @return String
	*/
	@RequestMapping(value="/view")
	public String show( HttpServletRequest request ){
	
		String id = request.getParameter("id");
		
		if( null != id && id.trim().length()>0 )
		{
			Integer domainId = Integer.parseInt(id);
			${className}${domainClass} ${attributeName} = (${className}${domainClass})${attributeName}${serviceClass}.findById(domainId);
			request.setAttribute("${attributeName}",${attributeName});
		}
		return "/${attributeName}/view";
	}
	
}