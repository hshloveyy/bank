package com.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.TAdminDAO;
import com.dao.TUserDAO;
import com.model.TAdmin;
import com.model.TUser;

public class loginService
{
	//声明管理员表操作类
	private TAdminDAO adminDAO;
	//声明用户表操作类
	private TUserDAO userDAO;
	/*以下get/set方法定义出来是给spring注入的时候用的*/
	public TAdminDAO getAdminDAO()
	{
		return adminDAO;
	}
	public void setAdminDAO(TAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
	public TUserDAO getUserDAO()
	{
		return userDAO;
	}
	public void setUserDAO(TUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	
	/**
	 * 用户登录
	 */
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			//程序先停0.7秒
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//定义返回的结果为no
		String result="no";
		
		//判断是否为系统管理员登录
		if(userType==0)//系统管理员登陆
		{
			//定义管理员登录的sql
			String sql="from TAdmin where userName=? and userPw=?";
			//定义查询的参数(用户名/密码)
			Object[] con={userName,userPw};
			//使用hibernate框架查询,返回结果集
			List adminList=adminDAO.getHibernateTemplate().find(sql,con);
			//如果集合长度为0,表示管理员账号密码不正确
			if(adminList.size()==0)
			{
				//登录结果为no
				 result="no";
			}
			else
			{
				//说明登录成功,获取上下文
				 WebContext ctx = WebContextFactory.get(); 
				 //通过上下文获取sesison会话
				 HttpSession session=ctx.getSession(); 
				 //从集合中获取登录的管理员信息
				 TAdmin admin=(TAdmin)adminList.get(0);
				 //设置当前session会话类型是管理员登录类型
				 session.setAttribute("userType", 0);
				 //设置当前session会话的管理员信息
	             session.setAttribute("admin", admin);
	             //登录结果为yes
	             result="yes";
			}
		}else if(userType==1)//储户
		{
			//定义储户登录的sql
			String sql="from TUser where tai !='删除' and kahao=? and ps=?";
			//定义查询的参数(用户名/密码)
			Object[] con={userName,userPw};
			//使用hibernate框架查询,返回结果集
			List adminList=userDAO.getHibernateTemplate().find(sql,con);
			//如果集合长度为0,表示储户账号密码不正确
			if(adminList.size()==0)
			{
				//登录结果为no
				 result="no";
			}
			else
			{
				//说明登录成功,获取上下文
				 WebContext ctx = WebContextFactory.get(); 
				//通过上下文获取sesison会话
				 HttpSession session=ctx.getSession(); 
				//从集合中获取登录的储户信息
				 TUser user=(TUser)adminList.get(0);
				//设置当前session会话类型是储户登录类型
				 session.setAttribute("userType", 1);
				//设置当前session会话的储户信息
	             session.setAttribute("user", user);
	             //登录结果为no
	             result="yes";
			}
		}
		//返回结果
		return result;
	}

	/**
	 * 管理员修改密码
	 */
    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
    		//程序停0.7秒
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//获取上下文
		WebContext ctx = WebContextFactory.get(); 
		//通过上下文获取sesison会话
		HttpSession session=ctx.getSession(); 
		 
		//通过sesison会话获取当前登录的管理员信息
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		//修改管理员密码
		admin.setUserPw(userPwNew);
		
		//将修改后的管理员信息(包括密码)更新到数据库中
		adminDAO.getHibernateTemplate().update(admin);
		//重新设置新的管理员信息到session会话中
		session.setAttribute("admin", admin);
		
		//返回结果yes,表示修改成功
		return "yes";
    }
    
    /**
	 * 储户修改密码
	 */
    public String userpsEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
    		//程序停0.7秒
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//获取上下文
		WebContext ctx = WebContextFactory.get(); 
		//通过上下文获取sesison会话
		HttpSession session=ctx.getSession(); 
		 
		//通过sesison会话获取当前登录的储户信息
		TUser user=(TUser)session.getAttribute("user");
		//修改储户密码
		user.setPs(userPwNew);
		
		//将修改后的储户信息(包括密码)更新到数据库中
		userDAO.getHibernateTemplate().update(user);
		//重新设置新的储户信息到session会话中
		session.setAttribute("user", user);
		
		//返回结果yes,表示修改成功
		return "yes";
    }
	
    /**
     * 检测用户是否存在
     */
    public String jiance(String userName)
    {
    	System.out.println("DDDD");
    	try 
		{
    		//程序停0.7秒
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//定义根据用户名查询用户表是否存在的sql
		String sql="from TUser where userName='"+userName+"'";
		//查询,返回集合
		List list=userDAO.getHibernateTemplate().find(sql);
		//如果集合长度大于0,表示该用户名存在用户
		if(list.size()>0)
		{
			//返回结果no,表示已存在该用户
			return "no";
		}
		else
		{
			//返回结果yes,表示不存在该用户
			return "yes";
		}
    }
    
    
}
