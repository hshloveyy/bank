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
	//�û�id
	private int id;
	//�û���ʵ����
	private String realname;
	//�Ա�
	private String sex;
	//����
	private String age;
	//��ַ
	private String address;
	//�绰
	private String tel;
	//��������
	private String email;
	//���п���
	private String kahao;
	//�˺ŵ�����
	private String ps;
	//���֤��
	private String shenfenzheng;
	//���
	private int yue;
	 
	//������Ϣ
	private String message;
	//��ת��·��
	private String path;
	
	//�û��������
	private TUserDAO userDAO;
	//���������
	private TCunDAO cunDAO;
	//ȡ��������
	private TQuDAO quDAO;
	//ת�˱������
	private TZhuanzhangDAO zhuanzhangDAO;
	
	/**
	 * ����û�
	 * @return
	 */
	public String userAdd()
	{
		//��ȡrequest����ʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//������ݿ��Ų�ѯ�û���sql
		String sql="from TUser where kahao=?";
		//�����ѯ�Ĳ���,������
		Object c[]={kahao.trim()};
		//ͨ��hibernate��ѯ,���ؼ���
		List userList=userDAO.getHibernateTemplate().find(sql,c);
		//������ϳ��ȴ���0,��ʾ�Ѿ������������,��Ҫ�û���������
		if(userList.size()>0)
		{
			//������Ϣ
			request.put("msg", "������ռ�á�����������");
			return "msg";
		}
		
		//����һ���û�ʵ��
		TUser user=new TUser();
		//�����û���ʵ����
		user.setRealname(realname);
		//�����Ա�
		user.setSex(sex);
		//��������
		user.setAge(age);
		//���õ�ַ
		user.setAddress(address);
		//���õ绰����
		user.setTel(tel);
		//���õ�������
		user.setEmail(email);
		//�������п���
		user.setKahao(kahao.trim());
		//�����˺ŵ�����
		user.setPs(ps);
		//�������֤��
		user.setShenfenzheng(shenfenzheng);
		//�������
		user.setYue(yue);
		//�����˺�״̬
		user.setTai("����");
		//���浽���ݿ�
		userDAO.save(user);
		//������Ϣ
		this.setMessage("�����ɹ�");
		//�������֮����ת����ҳ��
		this.setPath("userMana.action");
		return "succeed";
	}
	
	
	/**
	 * ��ѯ����û�б�ɾ�����û��˺�
	 * @return
	 */
	public String userMana()
	{
		//�����ѯ��sql
		String sql="from TUser where tai !='ɾ��'";
		//ͨ��hibernate��ѯ,���ؼ���
		List userList=userDAO.getHibernateTemplate().find(sql);
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//����ѯ�����ļ��Ϸŵ�requestʵ����,ҳ���ȡ��չʾ
		request.put("userList", userList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * ɾ���û�
	 * @return
	 */
	public String userDel()
	{
		//�����û�id��ѯҪɾ�����û�����
		TUser user=userDAO.findById(id);
		//���û��˺ŵ�״̬�޸�Ϊ"ɾ��"
		user.setTai("ɾ��");
		//���û���Ϣ���µ����ݿ���
		userDAO.attachDirty(user);
		//���÷�����Ϣ
		this.setMessage("ɾ���ɹ�");
		//����Ҫ��ת��ҳ��
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * �û�����
	 * @return
	 */
	public String user_dongjie()
	{
		//�����û�id��ѯҪɾ�����û�����
		TUser user=userDAO.findById(id);
		//���û��˺ŵ�״̬�޸�Ϊ"����"
		user.setTai("����");
		//���û���Ϣ���µ����ݿ���
		userDAO.attachDirty(user);
		//���÷�����Ϣ
		this.setMessage("�����ɹ�");
		//����Ҫ��ת��ҳ��
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * �û��ⶳ,��״̬Ϊ����
	 * @return
	 */
	public String user_jiedong()
	{
		//�����û�id��ѯҪɾ�����û�����
		TUser user=userDAO.findById(id);
		//���û��˺ŵ�״̬�޸�Ϊ"����"
		user.setTai("����");
		//���û���Ϣ���µ����ݿ���
		userDAO.attachDirty(user);
		//���÷�����Ϣ
		this.setMessage("�����ɹ�");
		//����Ҫ��ת��ҳ��
		this.setPath("userMana.action");
		return "succeed";
	}
	
	/**
	 * ��ѯ�û������
	 * @return
	 */
	public String userYue()
	{
		//�����û�id��ѯҪɾ�����û�����
		TUser user=userDAO.findById(id);
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		//����ѯ�������û�����ŵ�requestʵ����,ҳ���ȡ��չʾ
		request.put("user", user);
		return ActionSupport.SUCCESS;
	}

	/**
	 * �û����д��
	 * @return
	 */
	public String cunAdd()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//��ȡ�û����Ľ��
		int jine=Integer.parseInt(req.getParameter("jine"));
		//��ȡ�û�id
		int userId=Integer.parseInt(req.getParameter("userId"));
		//��ȡ�û����ʱ��
		String shijian=req.getParameter("shijian");
		
		//����û���״̬Ϊ"����"״̬,����ʾ�û�,������ʾ��Ϣ
		if(userDAO.findById(userId).getTai().endsWith("����"))
		{
			request.put("msg", "�����˻��ѱ����ᣬ���ܴ��");
			return "msg";
		}
		else
		{//����,������
			//ʵ����һ��������
			TCun cun=new TCun();
			//���ô����
			cun.setJine(jine);
			//���ô���û�id
			cun.setUserId(userId);
			//���ô��ʱ��
			cun.setShijian(shijian);
			
			//�������Ϣ���浽���ݿ���
			cunDAO.save(cun);
			
			//�����޸��û�����ʧsql
			String sql="update TUser set yue=yue+"+jine+" where id="+userId;
			//ͨ��hibernate���û����������´�Ľ��浽���ݿ�
			userDAO.getHibernateTemplate().bulkUpdate(sql);
			
			//������ʾ��Ϣ,��ҳ����ʾ
			request.put("msg", "���ɹ�");
			return "msg";
		}
	}
	
	/**
	 * ��ѯ�û����еĴ���¼
	 * @return
	 */
	public String cunMana_user()
	{
		//��ȡrequestʵ��
		HttpServletRequest req=ServletActionContext.getRequest();
		//��ȡ�û�id
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		//�����ѯ��ǰ�û�����¼��sql
		String sql="from TCun where userId="+userId;
		//ͨ��hibernate��ѯ��ǰ�û��Ĵ���¼,���ؼ���
		List cunList=cunDAO.getHibernateTemplate().find(sql);
		
		//��ȡrequest
		Map request=(Map)ServletActionContext.getContext().get("request");
		//����ѯ�Ľ���ŵ�request��,��ҳ���ȡչʾ
		request.put("cunList", cunList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * �û�ȡ��
	 * @return
	 */
	public String quAdd()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//��ȡȡ����
		int jine=Integer.parseInt(req.getParameter("jine"));
		//��ȡ�û�id
		int userId=Integer.parseInt(req.getParameter("userId"));
		//��ȡȡ��ʱ��
		String shijian=req.getParameter("shijian");
		
		//�����ǰ�û�״̬��"����"״̬,����ʾ�û�
		if(userDAO.findById(userId).getTai().endsWith("����"))
		{
			request.put("msg", "�����˻��ѱ����ᣬ���ܴ��");
		}
		else
		{//����,��ʼȡ�����
			//��ȡ�û������
			int yue=userDAO.findById(userId).getYue();
			//�������ȡ������
			if(yue<jine)
			{//��ʾ�û�����
				request.put("msg", "���㡣����ȡ��");
			}
			else
			{//����û�����ȡ�����
				//ʵ����ȡ�����
				TQu qu=new TQu();
				//����ȡ����
				qu.setJine(jine);
				//����ȡ���û�id
				qu.setUserId(userId);
				//����ȡ��ʱ��
				qu.setShijian(shijian);
				//��ȡ����Ϣ���浽���ݿ���
				quDAO.save(qu);
				
				//�����޸��û�����sql,��Ҫ��ȡ��Ľ�����
				String sql="update TUser set yue=yue-"+jine+" where id="+userId;
				//ͨ��hibernate�޸����ݿ����û������
				userDAO.getHibernateTemplate().bulkUpdate(sql);
				
				//������ʾ��Ϣ
				request.put("msg", "ȡ��ɹ�");
			}
		}
		
		return "msg";
	}
	
	/**
	 * ��ѯ�û�ȡ��ļ�¼
	 * @return
	 */
	public String quMana_user()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		//��ȡ��ǰ�û�id
		int userId=Integer.parseInt(req.getParameter("userId"));
		
		//�����ѯ����ȡ���¼��sql
		String sql="from TQu where userId="+userId;
		//��ѯ���ݿ��е�ǰ�û�������ȡ���¼
		List quList=cunDAO.getHibernateTemplate().find(sql);
		
		//�ŵ�request��,��ҳ���ȡ��չʾ
		request.put("quList", quList);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * �û�ת��
	 * @return
	 */
	public String zhuanzhangAdd()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		
		//��ȡ�û�����Ľ���ת�˵Ŀ���
		String tokahao=req.getParameter("tokahao").trim();
		//��ȡת�˽��
		int jine=Integer.parseInt(req.getParameter("jine"));
		//��ȡת��ʱ��
		String shijian=req.getParameter("shijian");
		//��ȡת�˵��û�id
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		//�ж�ת�˵��û��˺�״̬,�����"����"״̬����ʾ�û�
		if(userDAO.findById(fromUserId).getTai().endsWith("����"))
		{
			//������ʾ��Ϣ
			request.put("msg", "�����˻��ѱ����ᣬ����ת��");
		}
		else
		{
			//�����ѯ����״̬�Ƿ�"����",�����ѯ����ת�˵��û�����
			String sql="from TUser where tai ='����' and kahao=?";
			//�����ѯ����(����)
			Object c[]={tokahao.trim()};
			//ͨ��hibernate��ѯ,���ؼ���
			List userList=userDAO.getHibernateTemplate().find(sql,c);
			//������ϳ��ȵ���0,��ʾ���Ų�����,���߸��û�����״̬������,��ʾ�û�
			if(userList.size()==0)
			{
				//������ʾ��Ϣ
				request.put("msg", "���Ų����ڻ����ѱ����ᡣ����������");
			}
			else
			{
				//��ȡ��ѯ�õ����û�����
				TUser user=(TUser)userList.get(0);
				//��ȡ����ת�˵��û�id
				int toUserId=user.getId();
				
				//��ȡת���û������
				int yue=userDAO.findById(fromUserId).getYue();
				//���ת���û������С��ת�˵Ľ��,����ʾ�û�
				if(yue<jine)
				{
					//������ʾ��Ϣ
					request.put("msg", "���㡣����ת��");
				}
				else
				{//ת�˲���
					//ʵ��һ��ת�˶���
					TZhuanzhang zhuanzhang=new TZhuanzhang();
					//����ת���û�id
					zhuanzhang.setFromUserId(fromUserId);
					//���ý���ת���û�id
					zhuanzhang.setToUserId(toUserId);
					//����ת�˽��
					zhuanzhang.setJine(jine);
					//����ת��ʱ��
					zhuanzhang.setShijian(shijian);
					//��ת����Ϣ���浽���ݿ���
					zhuanzhangDAO.save(zhuanzhang);
					
					//�����޸�ת���û�����sql
					String sql1="update TUser set yue=yue-"+jine+" where id="+fromUserId;
					//�޸�ת���û������,���浽���ݿ�
					userDAO.getHibernateTemplate().bulkUpdate(sql1);
					
					//�����޸Ľ���ת���û�����sql
					String sql2="update TUser set yue=yue+"+jine+" where id="+toUserId;
					//�޸Ľ���ת�˵��û����,���浽���ݿ�
					userDAO.getHibernateTemplate().bulkUpdate(sql2);
					
					//������ʾ��Ϣ
					request.put("msg", "�����ɹ�");
				}
				
			}
		}
		
		return "msg";
	}
	
	/**
	 * ��ѯ��ǰ�û�����ת�˼�¼
	 * @return
	 */
	public String zhuanzhangMana_user()
	{
		//��ȡrequestʵ��
		Map request=(Map)ServletActionContext.getContext().get("request");
		HttpServletRequest req=ServletActionContext.getRequest();
		//��ȡ��ǰ�û�id
		int fromUserId=Integer.parseInt(req.getParameter("fromUserId"));
		
		//�����ѯ�û�����ת�˼�¼��sql
		String sql="from TZhuanzhang where fromUserId="+fromUserId;
		//ͨ��hibernate��ѯ�û�����ת�˼�¼
		List zhuanzhangList=zhuanzhangDAO.getHibernateTemplate().find(sql);
		//ѭ�������е�ת�˼�¼
		for(int i=0;i<zhuanzhangList.size();i++)
		{
			//�����±��ȡ��Ӧ��ת�˼�¼��Ϣ
			TZhuanzhang zhuanzhang=(TZhuanzhang)zhuanzhangList.get(i);
			//����ת�˼�¼�еĽ����û���Ϣ(�ȸ����û�id��ѯ)
			zhuanzhang.setToUser(userDAO.findById(zhuanzhang.getToUserId()));
		}
		//��ת�˼�¼���Ϸŵ�request��,��ҳ���л�ȡ��չʾ
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
