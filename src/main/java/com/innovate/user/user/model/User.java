package com.innovate.user.user.model;

import java.util.Date;

import com.innovate.basic.annotation.Invisible;
import com.innovate.basic.annotation.LikeQuery;
import com.innovate.basic.base.BaseModel;

/**
 * @desc:用户信息
 * @time: 2017年6月19日 下午4:51:26
 * @author IvanHsu 
 */
public class User extends BaseModel{

	@Invisible
	public static final String TAB_NAME = "T_SYS_USER";
	@Invisible
	private static final long serialVersionUID = -7746339803925425766L;
	
	@LikeQuery("right")
	private String userName;
	private String loginName;
	private String password;
	private String salt;
	private String userType;
	private String status;
	private String gender;
	private int age;
	private Date regTime;//注册时间
	private String isSuperUser;
	 /**最后登录时间*/
    private Date lastLoginTime;
    /**最后登录时间*/
    private String lastLoginIp;
    private String delFlag;

	public User() {
		super();
	}
	
	public User(User user) {
		super.setId(user.getId());
		this.userName = user.getUserName();
		this.salt = user.getLoginName();
		this.password = user.getPassword();
		this.salt = user.getSalt();
		this.lastLoginTime = user.getLastLoginTime();
		this.userType = user.getSalt();
		this.status = user.getStatus();
		this.gender = user.getGender();
		this.age = user.getAge();
		this.regTime = user.getRegTime();
		super.setCreateTime(user.getCreateTime());
		super.setUpdateTime(user.getUpdateTime());
		super.setComments(user.getComments());
	}
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getLoginName()
	{
		return loginName;
	}
	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getSalt()
	{
		return salt;
	}
	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getIsSuperUser() {
		return isSuperUser;
	}

	public void setIsSuperUser(String isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
}
