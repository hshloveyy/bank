package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.model.TAdmin;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 管理员信息操作
 *
 */
public class adminAction extends ActionSupport
{
	//成员变量：用户id，在页面传的值，会使用它们接收
	private int userId;
	//用户名
	private String userName;
	//密码
	private String userPw;
	 
	//返回到页面的消息
	private String message;
	//路径
	private String path;
	
	//下标（索引）
	private int index=1;

	//数据库操作类->管理员表的操作类
	private TAdminDAO adminDAO;
	
	/**
	 * 添加管理员
	 * 此方法配置在struts.xml
	 * @return
	 */
	public String adminAdd()
	{
		//实例化一个管理员实体类
		TAdmin admin=new TAdmin();
		//设置管理员的账号
		admin.setUserName(userName);
		//设置管理员的密码
		admin.setUserPw(userPw);
		//将管理员信息保存到数据库中
		adminDAO.save(admin);
		//返回的信息
		this.setMessage("操作成功");
		//操作成功之后跳转的页面
		this.setPath("adminManage.action");
		//在struts.xml配置的公共result中succeed对应路径
		return "succeed";
	}
	
	
	/**
	 * 管理员列表，查询所有的管理员信息
	 * 此方法配置在struts.xml
	 * @return
	 */
	public String adminManage()
	{
		//通过管理员表操作类查询所有管理员信息，返回一个列表
		List adminList=adminDAO.findAll();
		//获取request请求实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将查询得到的管理员列表放到请求实例request中，并命名为adminList
		request.put("adminList", adminList);
		//跳转到成功页面
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 删除管理员
	 * @return
	 */
	public String adminDel()
	{
		//通过管理员表操作类删除管理员信息
		adminDAO.delete(adminDAO.findById(userId));
		//返回消息
		this.setMessage("删除成功");
		//操作成功之后跳转的页面
		this.setPath("adminManage.action");
		return "succeed";
	}
	
	
	/**
	 * 获取管理员表操作类实例
	 * @return
	 */
	public TAdminDAO getAdminDAO()
	{
		return adminDAO;
	}

	/**
	 * 设置管理员表操作类，在spring注入中会使用
	 * @param adminDAO
	 */
	public void setAdminDAO(TAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}

	/**
	 * 获取消息
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * 获取下标
	 * @return
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * 设置下标
	 * @param index
	 */
	public void setIndex(int index)
	{
		this.index = index;
	}

	/**
	 * 设置消息
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * 获取跳转路径
	 * @return
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * 设置跳转路径
	 * @param path
	 */
	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 * 获取用户id
	 * @return
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * 设置用户id
	 * @param userId
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * 设置用户名
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * 获取用户密码
	 * @return
	 */
	public String getUserPw()
	{
		return userPw;
	}

	/**
	 * 设置用户密码
	 * @param userPw
	 */
	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}
	 
}
