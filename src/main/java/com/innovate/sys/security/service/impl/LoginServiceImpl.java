package com.innovate.sys.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.innovate.sys.security.dao.LoginDao;
import com.innovate.sys.security.service.ILoginService;
import com.innovate.user.user.model.User;

/**
 * @time: 2017年6月19日 下午5:33:17
 * @author IvanHsu 
 */
@Service("security.LoginServiceImpl")
public class LoginServiceImpl implements ILoginService{

	@Autowired
	private LoginDao loginDao;
	
	/* (non-Javadoc)
	 * @see com.xu.security.service.ILoginService#queryUserByLoginName(java.lang.String)
	 */
	@Override
	public User queryUserByLoginName(String loginName)
	{
		if(StringUtils.hasText(loginName))
			return loginDao.queryUserByLoginName(loginName);
		return null;
	}
	
	
	
}
