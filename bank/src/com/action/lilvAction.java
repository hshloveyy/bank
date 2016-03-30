package com.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.TLilvDAO;
import com.model.TLilv;
import com.opensymphony.xwork2.ActionSupport;
import com.service.liuService;

/**
 * 利率信息操作类
 *
 */
public class lilvAction extends ActionSupport
{
	//利率id
	private int id;
	//利率
	private double lilv;
	//消息
	private String message;
	//跳转路径
	private String path;
	
	//数据库操作类->利率表操作类
	private TLilvDAO lilvDAO;
	
	/**
	 * 利率信息查询
	 * @return
	 */
	public String lilvMana()
	{
		//通过利率表操作类查询利率
		TLilv lilv1=lilvDAO.findById(1);
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将利率信息放到请求request中
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 编辑修改利率信息
	 * @return
	 */
	public String lilvEdit()
	{
		//获取要修改的利率信息
		TLilv lilv1=lilvDAO.findById(1);
		//设置新的利率
		lilv1.setLilv(lilv);
		//通过利率表操作类修改利率
		lilvDAO.attachDirty(lilv1);
		//返回消息
		this.setMessage("操作成功");
		//设置跳转路径
		this.setPath("lilvMana.action");
		return "succeed";
	}
	
	/**
	 * 利率信息查看
	 * @return
	 */
	public String lilvChakan()
	{
		//获取最新的利率信息
		TLilv lilv1=lilvDAO.findById(1);
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//将利率信息放到请求request中
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * 利息计算
	 */
	public String lixijisuan()
	{
		//获取request实例
		Map request=(Map)ServletActionContext.getContext().get("request");
		//获取带有参数的request实例
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//获取用户输入的金额数
		int jine=Integer.parseInt(req.getParameter("jine"));
		//获取用户选择开始时间
		String shijian_sta=req.getParameter("shijian_sta");
		//获取用户选择结束时间
		String shijian_end=req.getParameter("shijian_end");
		
		//计算开始到结束时间的天数
		int tianshu=liuService.getAllDateBettwenTwoDate(shijian_sta, shijian_end).size();
		System.out.println(tianshu);
		
		//计算利息,利息 = 金额 + 天数 x 利率
		double lixi=jine + tianshu * lilvDAO.findById(1).getLilv();
		System.out.println(lixi);
		
		//将利息结果放到request中,页面做显示
		request.put("lixi", lixi);
		return ActionSupport.SUCCESS;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}


	public TLilvDAO getLilvDAO()
	{
		return lilvDAO;
	}

	public void setLilvDAO(TLilvDAO lilvDAO)
	{
		this.lilvDAO = lilvDAO;
	}

	public String getMessage()
	{
		return message;
	}

	public double getLilv()
	{
		return lilv;
	}

	public void setLilv(double lilv)
	{
		this.lilv = lilv;
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
	
	
}
