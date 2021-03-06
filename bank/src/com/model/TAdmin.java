package com.model;

/**
 * TAdmin generated by MyEclipse Persistence Tools
 */
/**
 * 实体类，一般一个数据库表对应一个实体类，将表中的一条记录用实体类包装，通过get/set方法获取与设置
 * 对应了t_admin这张表，里面的字段就对应了实体类的成员变量
 * @ClassName TAdmin
 */
public class TAdmin implements java.io.Serializable {

	// Fields

	//成员变量，用户id
	private Integer userId;
	//用户账号
	private String userName;
	//密码
	private String userPw;

	// Constructors

	//默认的构造函数
	/** default constructor */
	public TAdmin() {
	}

	//带参数的构造函数
	/** full constructor */
	public TAdmin(String userName, String userPw) {
		//参数的用户名设置到当前实例中
		this.userName = userName;
		//参数的密码设置到当前实例中
		this.userPw = userPw;
	}

	// Property accessors

	//获取用户名
	public String getUserName() {
		return this.userName;
	}

	//设置用户名
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//获取用户密码
	public String getUserPw() {
		return this.userPw;
	}
	
	//设置用户密码
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	//获取用户id
	public Integer getUserId()
	{
		return userId;
	}

	//设置用户id
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

}