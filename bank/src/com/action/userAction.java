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
	private int id;
	private String realname;
	private String sex;
	private String age;
	private String address;
	private String tel;
	private String email;
	private String kahao;
	private String ps;
	private String shenfenzheng;
	private int yue;
	 
	private String message;
	private String path;
	
	private TUserDAO userDAO;
	private TCunDAO cunDAO;
	private TQuDAO quDAO;
	private TZhuanzhangDAO zhuanzhangDAO;
	
	public String userAdd()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		
		String sql="from TUser where kahao=?";
		Object c[]={kahao.trim()};
		List userList=userDAO.getHibernateTemplate().find(sql,c);
		if(userList.size()>0)
		{
			request.put("msg", "卡号已占用。请重新输入");
			return "msg";
		}
		
		TUser user=new TUser();
		user.setRealname(realname);
		user.setSex(sex);
		user.setAge(age);
		user.setAddress(address);
		user.setTel(tel);
		user.setEmail(email);
		user.setKahao(kahao.trim());
		user.setPs(ps);
		user.setShenfenzheng(shenfenzheng);
		user.setYue(yue);
		user.setTai("正常");
		userDAO.save(user);
		this.setMessage("操作成功");
		this.setPath("userMana.action");
		return "succeed";
	}
	
	
	
	public String userMana()
	{
		String sql="from TUser where tai !='删除'";
		List userList=userDAO.getHibernateTemplate().find(sql);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String userDel()
	{
		TUser user=userDAO.findById(id);
		user.setTai("删除");
		userDAO.attachDirty(user);
		this.setMessage("删除成功");
		this.setPath("userMana.action");
		return "succeed";
	}
	
	
	public String user_dongjie()
	{
		TUser user=userDAO.findById(id);
		user.setTai("冻结");
		userDAO.attachDirty(user);
		this.setMessage("操作成功");
		this.setPath("userMana.action");
		return "succeed";
	}
	
	
	public String user_jiedong()
	{
		TUser user=userDAO.findById(id);
		user.setTai("正常");
		userDAO.attachDirty(user);
		this.setMessage("操作成功");
		this.setPath("userMana.action");
		return "succeed";
	}
	
	public String userYue()
	{
		TUser user=userDAO.findById(id);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("user", user);
		return ActionSupport.SUCCESS;
	}

	public String cunAdd()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		int jine=Integer.parseInt(req.getParameter("jine"));
		int userId=Integer.parseInt(req.getParameter("userId"));
		String shijian=req.getParameter("shijian");
		
		if(userDAO.findById(userId).getTai().endsWith("冻结"))
		{
			request.put("msg", "您的账户已被冻结，不能存款");
			return "msg";
		}
		else
		{
			
			TCun cun=new TCun();
			cun.setJine(jine);
			cun.setUserId(userId);
			cun.setShijian(shijian);
			
			cunDAO.save(cun);
			
			String sql="update TUser set yue=yue+"+jine+" where id="+userId;
			userDAO.getHibernateTemplate().bulkUpdate(sql);
			
			request.put("msg", "存款成功");
			return "msg";
		}
	}
	
	public String cunMana_user()
	{
		HttpServletRequest req=ServletActionContext.getRequest();
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		String sql="from TCun where userId="+userId;
		List cunList=cunDAO.getHibernateTemplate().find(sql);
		
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("cunList", cunList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String quAdd()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		int jine=Integer.parseInt(req.getParameter("jine"));
		int userId=Integer.parseInt(req.getParameter("userId"));
		String shijian=req.getParameter("shijian");
		
		if(userDAO.findById(userId).getTai().endsWith("冻结"))
		{
			request.put("msg", "您的账户已被冻结，不能存款");
		}
		else
		{
			int yue=userDAO.findById(userId).getYue();
			if(yue<jine)
			{
				request.put("msg", "余额不足。不能取款");
			}
			else
			{
				TQu qu=new TQu();
				qu.setJine(jine);
				qu.setUserId(userId);
				qu.setShijian(shijian);
				quDAO.save(qu);
				
				String sql="update TUser set yue=yue-"+jine+" where id="+userId;
				userDAO.getHibernateTemplate().bulkUpdate(sql);
				
				request.put("msg", "取款成功");
			}
		}
		
		return "msg";
	}
	
	public String quMana_user()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		String sql="from TQu where userId="+userId;
		List quList=cunDAO.getHibernateTemplate().find(sql);
		
		request.put("quList", quList);
		return ActionSupport.SUCCESS;
	}
	
	
	public String zhuanzhangAdd()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		String tokahao=req.getParameter("tokahao").trim();
		int jine=Integer.parseInt(req.getParameter("jine"));
		String shijian=req.getParameter("shijian");
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		if(userDAO.findById(fromUserId).getTai().endsWith("冻结"))
		{
			request.put("msg", "您的账户已被冻结，不能转账");
		}
		else
		{
			String sql="from TUser where tai ='正常' and kahao=?";
			Object c[]={tokahao.trim()};
			List userList=userDAO.getHibernateTemplate().find(sql,c);
			if(userList.size()==0)
			{
				request.put("msg", "卡号不存在或者已被冻结。请重新输入");
			}
			else
			{
				TUser user=(TUser)userList.get(0);
				int toUserId=user.getId();
				
				
				int yue=userDAO.findById(fromUserId).getYue();
				if(yue<jine)
				{
					request.put("msg", "余额不足。不能转账");
				}
				else
				{
					TZhuanzhang zhuanzhang=new TZhuanzhang();
					zhuanzhang.setFromUserId(fromUserId);
					zhuanzhang.setToUserId(toUserId);
					zhuanzhang.setJine(jine);
					zhuanzhang.setShijian(shijian);
					zhuanzhangDAO.save(zhuanzhang);
					
					String sql1="update TUser set yue=yue-"+jine+" where id="+fromUserId;
					userDAO.getHibernateTemplate().bulkUpdate(sql1);
					
					String sql2="update TUser set yue=yue+"+jine+" where id="+toUserId;
					userDAO.getHibernateTemplate().bulkUpdate(sql2);
					
					request.put("msg", "操作成功");
				}
				
			}
		}
		
		return "msg";
	}
	
	
	public String zhuanzhangMana_user()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		String sql="from TZhuanzhang where fromUserId="+fromUserId;
		List zhuanzhangList=zhuanzhangDAO.getHibernateTemplate().find(sql);
		for(int i=0;i<zhuanzhangList.size();i++)
		{
			TZhuanzhang zhuanzhang=(TZhuanzhang)zhuanzhangList.get(i);
			zhuanzhang.setToUser(userDAO.findById(zhuanzhang.getToUserId()));
		}
		
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
