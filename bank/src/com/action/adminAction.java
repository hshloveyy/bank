package com.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.TAdminDAO;
import com.model.TAdmin;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ����Ա��Ϣ����
 *
 */
public class adminAction extends ActionSupport
{
	//��Ա�������û�id����ҳ�洫��ֵ����ʹ�����ǽ���
	private int userId;
	//�û���
	private String userName;
	//����
	private String userPw;
	 
	//���ص�ҳ�����Ϣ
	private String message;
	//·��
	private String path;
	
	//�±꣨������
	private int index=1;

	//���ݿ������->����Ա��Ĳ�����
	private TAdminDAO adminDAO;
	
	/**
	 * ��ӹ���Ա
	 * �˷���������struts.xml
	 * @return
	 */
	public String adminAdd()
	{
		//ʵ����һ������Աʵ����
		TAdmin admin=new TAdmin();
		//���ù���Ա���˺�
		admin.setUserName(userName);
		//���ù���Ա������
		admin.setUserPw(userPw);
		//������Ա��Ϣ���浽���ݿ���
		adminDAO.save(admin);
		//���ص���Ϣ
		this.setMessage("�����ɹ�");
		//�����ɹ�֮����ת��ҳ��
		this.setPath("adminManage.action");
		//��struts.xml���õĹ���result��succeed��Ӧ·��
		return "succeed";
	}
	
	
	/**
	 * ����Ա�б���ѯ���еĹ���Ա��Ϣ
	 * �˷���������struts.xml
	 * @return
	 */
	public String adminManage()
	{
		//ͨ������Ա��������ѯ���й���Ա��Ϣ������һ���б�
		List adminList=adminDAO.findAll();
		//��ȡrequest����ʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//����ѯ�õ��Ĺ���Ա�б�ŵ�����ʵ��request�У�������ΪadminList
		request.put("adminList", adminList);
		//��ת���ɹ�ҳ��
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * ɾ������Ա
	 * @return
	 */
	public String adminDel()
	{
		//ͨ������Ա�������ɾ������Ա��Ϣ
		adminDAO.delete(adminDAO.findById(userId));
		//������Ϣ
		this.setMessage("ɾ���ɹ�");
		//�����ɹ�֮����ת��ҳ��
		this.setPath("adminManage.action");
		return "succeed";
	}
	
	
	/**
	 * ��ȡ����Ա�������ʵ��
	 * @return
	 */
	public TAdminDAO getAdminDAO()
	{
		return adminDAO;
	}

	/**
	 * ���ù���Ա������࣬��springע���л�ʹ��
	 * @param adminDAO
	 */
	public void setAdminDAO(TAdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}

	/**
	 * ��ȡ��Ϣ
	 * @return
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * ��ȡ�±�
	 * @return
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * �����±�
	 * @param index
	 */
	public void setIndex(int index)
	{
		this.index = index;
	}

	/**
	 * ������Ϣ
	 * @param message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * ��ȡ��ת·��
	 * @return
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * ������ת·��
	 * @param path
	 */
	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 * ��ȡ�û�id
	 * @return
	 */
	public int getUserId()
	{
		return userId;
	}

	/**
	 * �����û�id
	 * @param userId
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	/**
	 * ��ȡ�û���
	 * @return
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * �����û���
	 * @param userName
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * ��ȡ�û�����
	 * @return
	 */
	public String getUserPw()
	{
		return userPw;
	}

	/**
	 * �����û�����
	 * @param userPw
	 */
	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}
	 
}
