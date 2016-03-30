package com.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.dao.TCunDAO;
import com.dao.TQuDAO;
import com.dao.TUserDAO;
import com.dao.TZhuanzhangDAO;
import com.model.TAdmin;
import com.model.TCun;
import com.model.TQu;
import com.model.TUser;
import com.model.TZhuanzhang;
import com.opensymphony.xwork2.ActionSupport;
import com.service.liuService;

public class userAction extends ActionSupport
{
	//用户id
	private int id;
	//用户真实姓名
	private String realname;
	//性别
	private String sex;
	//年龄
	private String age;
	//地址
	private String address;
	//电话
	private String tel;
	//电子邮箱
	private String email;
	//银行卡号
	private String kahao;
	//账号的密码
	private String ps;
	//身份证号
	private String shenfenzheng;
	//余额
	private int yue;
	 
	//返回消息
	private String message;
	//跳转的路径
	private String path;
	
	//用户表操作类
	private TUserDAO userDAO;
	//存款表操作类
	private TCunDAO cunDAO;
	//取款表操作类
	private TQuDAO quDAO;
	//转账表操作类
	private TZhuanzhangDAO zhuanzhangDAO;
	
	/**
	 * 添加用户
	 * @return
	 */
	public String userAdd()
	{
		//获取request请求实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//定义根据卡号查询用户的sql
		String sql="from TUser where kahao=?";
		//定义查询的参数,即卡号
		Object c[]={kahao.trim()};
		//通过hibernate查询,返回集合
		List userList=userDAO.getHibernateTemplate().find(sql,c);
		//如果集合长度大于0,表示已经存在这个卡号,需要用户重新输入
		if(userList.size()>0)
		{
			//返回消息
			request.put("msg", "卡号已占用。请重新输入");
			return "msg";
		}
		
		//创建一个用户实例
		TUser user=new TUser();
		//设置用户真实姓名
		user.setRealname(realname);
		//设置性别
		user.setSex(sex);
		//设置年龄
		user.setAge(age);
		//设置地址
		user.setAddress(address);
		//设置电话号码
		user.setTel(tel);
		//设置电子邮箱
		user.setEmail(email);
		//设置银行卡号
		user.setKahao(kahao.trim());
		//设置账号的密码
		user.setPs(ps);
		//设置身份证号
		user.setShenfenzheng(shenfenzheng);
		//设置余额
		user.setYue(yue);
		//设置账号状态
		user.setTai("正常");
		//保存到数据库
		userDAO.save(user);
		//返回消息
		this.setMessage("操作成功");
		//操作完成之后跳转到的页面
		this.setPath("userMana.action");
		return "succeed";
	}
	
	
	/**
	 * 查询所有没有被删除的用户账号
	 * @return
	 */
	public String userMana()
	{
		//定义查询的sql
		String sql="from TUser where tai !='删除'";
		//通过hibernate查询,返回集合
		List userList=userDAO.getHibernateTemplate().find(sql);
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将查询出来的集合放到request实例中,页面获取再展示
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	public String userDel()
	{
		//根据用户id查询要删除的用户对象
		TUser user=userDAO.findById(id);
		//将用户账号的状态修改为"删除"
		user.setTai("删除");
		//将用户信息更新到数据库中
		userDAO.attachDirty(user);
		//设置返回消息
		this.setMessage("删除成功");
		//设置要跳转的页面
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * 用户冻结
	 * @return
	 */
	public String user_dongjie()
	{
		//根据用户id查询要删除的用户对象
		TUser user=userDAO.findById(id);
		//将用户账号的状态修改为"冻结"
		user.setTai("冻结");
		//将用户信息更新到数据库中
		userDAO.attachDirty(user);
		//设置返回消息
		this.setMessage("操作成功");
		//设置要跳转的页面
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * 用户解冻,即状态为正常
	 * @return
	 */
	public String user_jiedong()
	{
		//根据用户id查询要删除的用户对象
		TUser user=userDAO.findById(id);
		//将用户账号的状态修改为"正常"
		user.setTai("正常");
		//将用户信息更新到数据库中
		userDAO.attachDirty(user);
		//设置返回消息
		this.setMessage("操作成功");
		//设置要跳转的页面
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * 查询用户的余额
	 * @return
	 */
	public String userYue()
	{
		//根据用户id查询要删除的用户对象
		TUser user=userDAO.findById(id);
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将查询出来的用户对象放到request实例中,页面获取再展示
		request.put("user", user);
		return ActionSupport.SUCCESS;
	}

	/**
	 * 用户进行存款
	 * @return
	 */
	public String cunAdd()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//获取用户存款的金额
		int jine=Integer.parseInt(req.getParameter("jine"));
		//获取用户id
		int userId=Integer.parseInt(req.getParameter("userId"));
		//获取用户存款时间
		String shijian=req.getParameter("shijian");
		
		//如果用户的状态为"冻结"状态,则提示用户,返回提示信息
		if(userDAO.findById(userId).getTai().endsWith("冻结"))
		{
			request.put("msg", "您的账户已被冻结，不能存款");
			return "msg";
		}
		else
		{//否则,存款操作
			//实例化一个存款对象
			TCun cun=new TCun();
			//设置存款金额
			cun.setJine(jine);
			//设置存款用户id
			cun.setUserId(userId);
			//设置存款时间
			cun.setShijian(shijian);
			
			//将存款信息保存到数据库中
			cunDAO.save(cun);
			
			//定义修改用户余额的失sql
			String sql="update TUser set yue=yue+"+jine+" where id="+userId;
			//通过hibernate将用户的余额加上新存的金额保存到数据库
			userDAO.getHibernateTemplate().bulkUpdate(sql);
			
			//设置提示信息,在页面提示
			request.put("msg", "存款成功");
			return "msg";
		}
	}
	
	/**
	 * 查询用户所有的存款记录
	 * @return
	 */
	public String cunMana_user()
	{
		//获取request实例
		HttpServletRequest req=ServletActionContext.getRequest();
		//获取用户id
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		//定义查询当前用户存款记录的sql
		String sql="from TCun where userId="+userId;
		//通过hibernate查询当前用户的存款记录,返回集合
		List cunList=cunDAO.getHibernateTemplate().find(sql);
		
		//获取request
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将查询的结果放到request中,在页面获取展示
		request.put("cunList", cunList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 用户取款
	 * @return
	 */
	public String quAdd()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//获取取款金额
		int jine=Integer.parseInt(req.getParameter("jine"));
		//获取用户id
		int userId=Integer.parseInt(req.getParameter("userId"));
		//获取取款时间
		String shijian=req.getParameter("shijian");
		
		//如果当前用户状态是"冻结"状态,则提示用户
		if(userDAO.findById(userId).getTai().endsWith("冻结"))
		{
			request.put("msg", "您的账户已被冻结，不能存款");
		}
		else
		{//否则,开始取款操作
			//获取用户的余额
			int yue=userDAO.findById(userId).getYue();
			//如果余额比取款金额少
			if(yue<jine)
			{//提示用户余额不足
				request.put("msg", "余额不足。不能取款");
			}
			else
			{//如果用户余额比取款金额多
				//实例化取款对象
				TQu qu=new TQu();
				//设置取款金额
				qu.setJine(jine);
				//设置取款用户id
				qu.setUserId(userId);
				//设置取款时间
				qu.setShijian(shijian);
				//将取款信息保存到数据库中
				quDAO.save(qu);
				
				//定义修改用户余额的sql,需要将取款的金额减掉
				String sql="update TUser set yue=yue-"+jine+" where id="+userId;
				//通过hibernate修改数据库中用户的余额
				userDAO.getHibernateTemplate().bulkUpdate(sql);
				
				//返回提示信息
				request.put("msg", "取款成功");
			}
		}
		
		return "msg";
	}
	
	/**
	 * 查询用户取款的记录
	 * @return
	 */
	public String quMana_user()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		//获取当前用户id
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		//定义查询所有取款记录的sql
		String sql="from TQu where userId="+userId;
		//查询数据库中当前用户的所有取款记录
		List quList=cunDAO.getHibernateTemplate().find(sql);
		
		//放到request中,在页面获取再展示
		request.put("quList", quList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 用户转账
	 * @return
	 */
	public String zhuanzhangAdd()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//获取用户输入的接收转账的卡号
		String tokahao=req.getParameter("tokahao").trim();
		//获取转账金额
		int jine=Integer.parseInt(req.getParameter("jine"));
		//获取转账时间
		String shijian=req.getParameter("shijian");
		//获取转账的用户id
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		//判断转账的用户账号状态,如果是"冻结"状态则提示用户
		if(userDAO.findById(fromUserId).getTai().endsWith("冻结"))
		{
			//设置提示信息
			request.put("msg", "您的账户已被冻结，不能转账");
		}
		else
		{
			//否则查询卡号状态是否"正常",定义查询接收转账的用户卡号
			String sql="from TUser where tai ='正常' and kahao=?";
			//定义查询参数(卡号)
			Object c[]={tokahao.trim()};
			//通过hibernate查询,返回集合
			List userList=userDAO.getHibernateTemplate().find(sql,c);
			//如果集合长度等于0,表示卡号不存在,或者该用户卡号状态不正常,提示用户
			if(userList.size()==0)
			{
				//设置提示信息
				request.put("msg", "卡号不存在或者已被冻结。请重新输入");
			}
			else
			{
				//获取查询得到的用户对象
				TUser user=(TUser)userList.get(0);
				//获取接收转账的用户id
				int toUserId=user.getId();
				
				//获取转账用户的余额
				int yue=userDAO.findById(fromUserId).getYue();
				//如果转账用户的余额小于转账的金额,则提示用户
				if(yue<jine)
				{
					//设置提示信息
					request.put("msg", "余额不足。不能转账");
				}
				else
				{//转账操作
					//实例一个转账对象
					TZhuanzhang zhuanzhang=new TZhuanzhang();
					//设置转账用户id
					zhuanzhang.setFromUserId(fromUserId);
					//设置接收转账用户id
					zhuanzhang.setToUserId(toUserId);
					//设置转账金额
					zhuanzhang.setJine(jine);
					//设置转账时间
					zhuanzhang.setShijian(shijian);
					//将转账信息保存到数据库中
					zhuanzhangDAO.save(zhuanzhang);
					
					//定义修改转账用户余额的sql
					String sql1="update TUser set yue=yue-"+jine+" where id="+fromUserId;
					//修改转账用户的余额,保存到数据库
					userDAO.getHibernateTemplate().bulkUpdate(sql1);
					
					//定义修改接收转账用户余额的sql
					String sql2="update TUser set yue=yue+"+jine+" where id="+toUserId;
					//修改接收转账的用户余额,保存到数据库
					userDAO.getHibernateTemplate().bulkUpdate(sql2);
					
					//设置提示信息
					request.put("msg", "操作成功");
				}
				
			}
		}
		
		return "msg";
	}
	
	/**
	 * 查询当前用户所有转账记录
	 * @return
	 */
	public String zhuanzhangMana_user()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		//获取当前用户id
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		//定义查询用户所有转账记录的sql
		String sql="from TZhuanzhang where fromUserId="+fromUserId;
		//通过hibernate查询用户所有转账记录
		List zhuanzhangList=zhuanzhangDAO.getHibernateTemplate().find(sql);
		//循环集合中的转账记录
		for(int i=0;i<zhuanzhangList.size();i++)
		{
			//根据下标获取对应的转账记录信息
			TZhuanzhang zhuanzhang=(TZhuanzhang)zhuanzhangList.get(i);
			//设置转账记录中的接收用户信息(先根据用户id查询)
			zhuanzhang.setToUser(userDAO.findById(zhuanzhang.getToUserId()));
		}
		//将转账记录集合放到request中,在页面中获取并展示
		request.put("zhuanzhangList", zhuanzhangList);
		return ActionSupport.SUCCESS;
	}

	

	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getAge()
	{
		return age;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getKahao()
	{
		return kahao;
	}
	public void setKahao(String kahao)
	{
		this.kahao = kahao;
	}
	public String getMessage()
	{
		return message;
	}
	
	public TCunDAO getCunDAO()
	{
		return cunDAO;
	}



	public void setCunDAO(TCunDAO cunDAO)
	{
		this.cunDAO = cunDAO;
	}



	public void setMessage(String message)
	{
		this.message = message;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getPs()
	{
		return ps;
	}
	public void setPs(String ps)
	{
		this.ps = ps;
	}
	public String getRealname()
	{
		return realname;
	}
	public void setRealname(String realname)
	{
		this.realname = realname;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getShenfenzheng()
	{
		return shenfenzheng;
	}
	public void setShenfenzheng(String shenfenzheng)
	{
		this.shenfenzheng = shenfenzheng;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public TUserDAO getUserDAO()
	{
		return userDAO;
	}
	public void setUserDAO(TUserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	public int getYue()
	{
		return yue;
	}
	
	public TQuDAO getQuDAO()
	{
		return quDAO;
	}



	public void setQuDAO(TQuDAO quDAO)
	{
		this.quDAO = quDAO;
	}



	public TZhuanzhangDAO getZhuanzhangDAO()
	{
		return zhuanzhangDAO;
	}



	public void setZhuanzhangDAO(TZhuanzhangDAO zhuanzhangDAO)
	{
		this.zhuanzhangDAO = zhuanzhangDAO;
	}



	public void setYue(int yue)
	{
		this.yue = yue;
	}
}
