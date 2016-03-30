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
	//��������Ա�������
	private TAdminDAO adminDAO;
	//�����û��������
	private TUserDAO userDAO;
	/*����get/set������������Ǹ�springע���ʱ���õ�*/
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
	 * �û���¼
	 */
	public String login(String userName,String userPw,int userType)
	{
		System.out.println("userType"+userType);
		try
		{
			//������ͣ0.7��
			Thread.sleep(700);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���巵�صĽ��Ϊno
		String result="no";
		
		//�ж��Ƿ�Ϊϵͳ����Ա��¼
		if(userType==0)//ϵͳ����Ա��½
		{
			//�������Ա��¼��sql
			String sql="from TAdmin where userName=? and userPw=?";
			//�����ѯ�Ĳ���(�û���/����)
			Object[] con={userName,userPw};
			//ʹ��hibernate��ܲ�ѯ,���ؽ����
			List adminList=adminDAO.getHibernateTemplate().find(sql,con);
			//������ϳ���Ϊ0,��ʾ����Ա�˺����벻��ȷ
			if(adminList.size()==0)
			{
				//��¼���Ϊno
				 result="no";
			}
			else
			{
				//˵����¼�ɹ�,��ȡ������
				 WebContext ctx = WebContextFactory.get(); 
				 //ͨ�������Ļ�ȡsesison�Ự
				 HttpSession session=ctx.getSession(); 
				 //�Ӽ����л�ȡ��¼�Ĺ���Ա��Ϣ
				 TAdmin admin=(TAdmin)adminList.get(0);
				 //���õ�ǰsession�Ự�����ǹ���Ա��¼����
				 session.setAttribute("userType", 0);
				 //���õ�ǰsession�Ự�Ĺ���Ա��Ϣ
	             session.setAttribute("admin", admin);
	             //��¼���Ϊyes
	             result="yes";
			}
		}else if(userType==1)//����
		{
			//���崢����¼��sql
			String sql="from TUser where tai !='ɾ��' and kahao=? and ps=?";
			//�����ѯ�Ĳ���(�û���/����)
			Object[] con={userName,userPw};
			//ʹ��hibernate��ܲ�ѯ,���ؽ����
			List adminList=userDAO.getHibernateTemplate().find(sql,con);
			//������ϳ���Ϊ0,��ʾ�����˺����벻��ȷ
			if(adminList.size()==0)
			{
				//��¼���Ϊno
				 result="no";
			}
			else
			{
				//˵����¼�ɹ�,��ȡ������
				 WebContext ctx = WebContextFactory.get(); 
				//ͨ�������Ļ�ȡsesison�Ự
				 HttpSession session=ctx.getSession(); 
				//�Ӽ����л�ȡ��¼�Ĵ�����Ϣ
				 TUser user=(TUser)adminList.get(0);
				//���õ�ǰsession�Ự�����Ǵ�����¼����
				 session.setAttribute("userType", 1);
				//���õ�ǰsession�Ự�Ĵ�����Ϣ
	             session.setAttribute("user", user);
	             //��¼���Ϊno
	             result="yes";
			}
		}
		//���ؽ��
		return result;
	}

	/**
	 * ����Ա�޸�����
	 */
    public String adminPwEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
    		//����ͣ0.7��
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//��ȡ������
		WebContext ctx = WebContextFactory.get(); 
		//ͨ�������Ļ�ȡsesison�Ự
		HttpSession session=ctx.getSession(); 
		 
		//ͨ��sesison�Ự��ȡ��ǰ��¼�Ĺ���Ա��Ϣ
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		//�޸Ĺ���Ա����
		admin.setUserPw(userPwNew);
		
		//���޸ĺ�Ĺ���Ա��Ϣ(��������)���µ����ݿ���
		adminDAO.getHibernateTemplate().update(admin);
		//���������µĹ���Ա��Ϣ��session�Ự��
		session.setAttribute("admin", admin);
		
		//���ؽ��yes,��ʾ�޸ĳɹ�
		return "yes";
    }
    
    /**
	 * �����޸�����
	 */
    public String userpsEdit(String userPwNew)
    {
		System.out.println("DDDD");
    	try 
		{
    		//����ͣ0.7��
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//��ȡ������
		WebContext ctx = WebContextFactory.get(); 
		//ͨ�������Ļ�ȡsesison�Ự
		HttpSession session=ctx.getSession(); 
		 
		//ͨ��sesison�Ự��ȡ��ǰ��¼�Ĵ�����Ϣ
		TUser user=(TUser)session.getAttribute("user");
		//�޸Ĵ�������
		user.setPs(userPwNew);
		
		//���޸ĺ�Ĵ�����Ϣ(��������)���µ����ݿ���
		userDAO.getHibernateTemplate().update(user);
		//���������µĴ�����Ϣ��session�Ự��
		session.setAttribute("user", user);
		
		//���ؽ��yes,��ʾ�޸ĳɹ�
		return "yes";
    }
	
    /**
     * ����û��Ƿ����
     */
    public String jiance(String userName)
    {
    	System.out.println("DDDD");
    	try 
		{
    		//����ͣ0.7��
			Thread.sleep(700);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//��������û�����ѯ�û����Ƿ���ڵ�sql
		String sql="from TUser where userName='"+userName+"'";
		//��ѯ,���ؼ���
		List list=userDAO.getHibernateTemplate().find(sql);
		//������ϳ��ȴ���0,��ʾ���û��������û�
		if(list.size()>0)
		{
			//���ؽ��no,��ʾ�Ѵ��ڸ��û�
			return "no";
		}
		else
		{
			//���ؽ��yes,��ʾ�����ڸ��û�
			return "yes";
		}
    }
    
    
}
