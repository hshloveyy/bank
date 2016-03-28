package com.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dao.TLilvDAO;
import com.model.TLilv;
import com.opensymphony.xwork2.ActionSupport;
import com.service.liuService;

public class lilvAction extends ActionSupport
{
	private int id;
	private double lilv;
	private String message;
	private String path;
	
	private TLilvDAO lilvDAO;
	
	
	public String lilvMana()
	{
		TLilv lilv1=lilvDAO.findById(1);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	
	public String lilvEdit()
	{
		TLilv lilv1=lilvDAO.findById(1);
		lilv1.setLilv(lilv);
		lilvDAO.attachDirty(lilv1);
		this.setMessage("²Ù×÷³É¹¦");
		this.setPath("lilvMana.action");
		return "succeed";
	}
	
	public String lilvChakan()
	{
		TLilv lilv1=lilvDAO.findById(1);
		Map request=(Map)ServletActionContext.getContext().get("request");
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	
	public String lixijisuan()
	{
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		int jine=Integer.parseInt(req.getParameter("jine"));
		String shijian_sta=req.getParameter("shijian_sta");
		String shijian_end=req.getParameter("shijian_end");
		
		int tianshu=liuService.getAllDateBettwenTwoDate(shijian_sta, shijian_end).size();
		System.out.println(tianshu);
		
		double lixi=jine + tianshu * lilvDAO.findById(1).getLilv();
		System.out.println(lixi);
		
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
