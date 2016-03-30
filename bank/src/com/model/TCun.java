package com.model;

/**
 * TCun generated by MyEclipse Persistence Tools
 * 实体类，一般一个数据库表对应一个实体类，将表中的一条记录用实体类包装，通过get/set方法获取与设置
 * 对应了t_cun这张表，里面的字段就对应了实体类的成员变量
 */
public class TCun implements java.io.Serializable
{

	//记录id
	private Integer id;
	//用户id
	private Integer userId;
	//存款金额
	private Integer jine;
	//存款时间
	private String shijian;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getJine()
	{
		return jine;
	}
	public void setJine(Integer jine)
	{
		this.jine = jine;
	}
	public String getShijian()
	{
		return shijian;
	}
	public void setShijian(String shijian)
	{
		this.shijian = shijian;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

}