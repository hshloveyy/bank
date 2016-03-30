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
 * ������Ϣ������
 *
 */
public class lilvAction extends ActionSupport
{
	//����id
	private int id;
	//����
	private double lilv;
	//��Ϣ
	private String message;
	//��ת·��
	private String path;
	
	//���ݿ������->���ʱ������
	private TLilvDAO lilvDAO;
	
	/**
	 * ������Ϣ��ѯ
	 * @return
	 */
	public String lilvMana()
	{
		//ͨ�����ʱ�������ѯ����
		TLilv lilv1=lilvDAO.findById(1);
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//��������Ϣ�ŵ�����request��
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * �༭�޸�������Ϣ
	 * @return
	 */
	public String lilvEdit()
	{
		//��ȡҪ�޸ĵ�������Ϣ
		TLilv lilv1=lilvDAO.findById(1);
		//�����µ�����
		lilv1.setLilv(lilv);
		//ͨ�����ʱ�������޸�����
		lilvDAO.attachDirty(lilv1);
		//������Ϣ
		this.setMessage("�����ɹ�");
		//������ת·��
		this.setPath("lilvMana.action");
		return "succeed";
	}
	
	/**
	 * ������Ϣ�鿴
	 * @return
	 */
	public String lilvChakan()
	{
		//��ȡ���µ�������Ϣ
		TLilv lilv1=lilvDAO.findById(1);
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//��������Ϣ�ŵ�����request��
		request.put("lilv1", lilv1);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * ��Ϣ����
	 */
	public String lixijisuan()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//��ȡ���в�����requestʵ��
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//��ȡ�û�����Ľ����
		int jine=Integer.parseInt(req.getParameter("jine"));
		//��ȡ�û�ѡ��ʼʱ��
		String shijian_sta=req.getParameter("shijian_sta");
		//��ȡ�û�ѡ�����ʱ��
		String shijian_end=req.getParameter("shijian_end");
		
		//���㿪ʼ������ʱ�������
		int tianshu=liuService.getAllDateBettwenTwoDate(shijian_sta, shijian_end).size();
		System.out.println(tianshu);
		
		//������Ϣ,��Ϣ = ��� + ���� x ����
		double lixi=jine + tianshu * lilvDAO.findById(1).getLilv();
		System.out.println(lixi);
		
		//����Ϣ����ŵ�request��,ҳ������ʾ
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
