package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TUser;

/**
 * Data access object (DAO) for domain model class TUser.
 * 
 * @see com.model.TUser
 * @author MyEclipse Persistence Tools
 */
/**
 * �û��������
 */
public class TUserDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TUserDAO.class);

	// property constants
	//��ʵ�����ֶ���
	public static final String REALNAME = "realname";
	//�Ա��ֶ���
	public static final String SEX = "sex";
	//�����ֶ���
	public static final String AGE = "age";
	//��ַ�ֶ���
	public static final String ADDRESS = "address";
	//�绰�ֶ���
	public static final String TEL = "tel";
	//�����ֶ���
	public static final String EMAIL = "email";
	//�����ֶ���
	public static final String KAHAO = "kahao";
	//�����ֶ���
	public static final String PS = "ps";
	//���֤���ֶ���
	public static final String SHENFENZHENG = "shenfenzheng";
	//����ֶ���
	public static final String YUE = "yue";
	//״̬�ֶ���
	public static final String DEL = "del";
	
	protected void initDao()
	{
		// do nothing
	}

	/**
	 * �����û���Ϣ
	 * @param transientInstance
	 */
	public void save(TUser transientInstance)
	{
		log.debug("saving TUser instance");
		try
		{
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re)
		{
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * ɾ���û�
	 * @param persistentInstance
	 */
	public void delete(TUser persistentInstance)
	{
		log.debug("deleting TUser instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * �����û�id��ѯ�û�����
	 * @param id
	 * @return
	 */
	public TUser findById(java.lang.Integer id)
	{
		log.debug("getting TUser instance with id: " + id);
		try
		{
			TUser instance = (TUser) getHibernateTemplate().get(
					"com.model.TUser", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * �����û��������Բ�ѯ�û��б�
	 * @param instance
	 * @return
	 */
	public List findByExample(TUser instance)
	{
		log.debug("finding TUser instance by example");
		try
		{
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * ������������ѯ�û��б�
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TUser instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * ������ʵ������ѯ�û��б�
	 * @param realname
	 * @return
	 */
	public List findByRealname(Object realname)
	{
		return findByProperty(REALNAME, realname);
	}

	/**
	 * �����Ա��ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findBySex(Object sex)
	{
		return findByProperty(SEX, sex);
	}

	/**
	 * ���������ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByAge(Object age)
	{
		return findByProperty(AGE, age);
	}

	/**
	 * ���ݵ�ַ��ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByAddress(Object address)
	{
		return findByProperty(ADDRESS, address);
	}

	/**
	 * ���ݵ绰��ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByTel(Object tel)
	{
		return findByProperty(TEL, tel);
	}
	
	/**
	 * ���������ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByEmail(Object email)
	{
		return findByProperty(EMAIL, email);
	}

	/**
	 * ���ݿ��Ų�ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByKahao(Object kahao)
	{
		return findByProperty(KAHAO, kahao);
	}

	/**
	 * ���������ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByPs(Object ps)
	{
		return findByProperty(PS, ps);
	}

	/**
	 * �������֤�Ų�ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByShenfenzheng(Object shenfenzheng)
	{
		return findByProperty(SHENFENZHENG, shenfenzheng);
	}

	/**
	 * ��������ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByYue(Object yue)
	{
		return findByProperty(YUE, yue);
	}

	/**
	 * ����״̬��ѯ�û��б�
	 * @param sex
	 * @return
	 */
	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	/**
	 * ��ѯ�����û��б�
	 * @param sex
	 * @return
	 */
	public List findAll()
	{
		log.debug("finding all TUser instances");
		try
		{
			String queryString = "from TUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * �������������ݿ���,��ת�ɳ־�̬
	 * ���������,�򱣴浽���ݿ���,��ת�ɳ־�̬
	 * @param detachedInstance
	 * @return
	 */
	public TUser merge(TUser detachedInstance)
	{
		log.debug("merging TUser instance");
		try
		{
			TUser result = (TUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * �������������ݿ���,��ת�ɳ־�̬
	 * ���������,�򱣴浽���ݿ���,��ת�ɳ־�̬
	 * @param instance
	 */
	public void attachDirty(TUser instance)
	{
		log.debug("attaching dirty TUser instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * �������Ӧ�����ݿ��¼����Ϊ���������ɴ˿��Ա�֤�޸ĵĴ��л�
	 * @param instance
	 */
	public void attachClean(TUser instance)
	{
		log.debug("attaching clean TUser instance");
		try
		{
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * ��spring�����л�ȡTUserDAO���bean
	 * @param ctx
	 * @return
	 */
	public static TUserDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TUserDAO) ctx.getBean("TUserDAO");
	}
}