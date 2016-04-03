package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TZhuanzhang;

/**
 * Data access object (DAO) for domain model class TZhuanzhang.
 * 
 * @see com.model.TZhuanzhang
 * @author MyEclipse Persistence Tools
 */
/**
 * ת�˱������
 */
public class TZhuanzhangDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TZhuanzhangDAO.class);

	// property constants
	//ת�����ֶ���
	public static final String FROM_USER_ID = "fromUserId";
	//����ת�����ֶ���
	public static final String TO_USER_ID = "toUserId";
	//ת�˽���ֶ���
	public static final String JINE = "jine";
	//ת��ʱ���ֶ���
	public static final String SHIJIAN = "shijian";
	//״̬�ֶ���
	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	/**
	 * ����ת����Ϣ
	 * @param transientInstance
	 */
	public void save(TZhuanzhang transientInstance)
	{
		log.debug("saving TZhuanzhang instance");
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
	 * ɾ��ת����Ϣ
	 * @param persistentInstance
	 */
	public void delete(TZhuanzhang persistentInstance)
	{
		log.debug("deleting TZhuanzhang instance");
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
	 * ����id��ѯת�˶���
	 * @param id
	 * @return
	 */
	public TZhuanzhang findById(java.lang.Integer id)
	{
		log.debug("getting TZhuanzhang instance with id: " + id);
		try
		{
			TZhuanzhang instance = (TZhuanzhang) getHibernateTemplate().get(
					"com.model.TZhuanzhang", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * ����ת�˶������Բ�ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByExample(TZhuanzhang instance)
	{
		log.debug("finding TZhuanzhang instance by example");
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
	 * ����ת�����Բ�ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TZhuanzhang instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TZhuanzhang as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * ����ת����id��ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByFromUserId(Object fromUserId)
	{
		return findByProperty(FROM_USER_ID, fromUserId);
	}

	/**
	 * ���ݽ���ת����id��ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByToUserId(Object toUserId)
	{
		return findByProperty(TO_USER_ID, toUserId);
	}

	/**
	 * ����ת�˽���ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByJine(Object jine)
	{
		return findByProperty(JINE, jine);
	}

	/**
	 * ����ת��ʱ���ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByShijian(Object shijian)
	{
		return findByProperty(SHIJIAN, shijian);
	}

	/**
	 * ����ת��״̬��ѯת���б�
	 * @param instance
	 * @return
	 */
	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	/**
	 * ��ѯ����ת���б�
	 * @param instance
	 * @return
	 */
	public List findAll()
	{
		log.debug("finding all TZhuanzhang instances");
		try
		{
			String queryString = "from TZhuanzhang";
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
	public TZhuanzhang merge(TZhuanzhang detachedInstance)
	{
		log.debug("merging TZhuanzhang instance");
		try
		{
			TZhuanzhang result = (TZhuanzhang) getHibernateTemplate().merge(
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
	public void attachDirty(TZhuanzhang instance)
	{
		log.debug("attaching dirty TZhuanzhang instance");
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
	public void attachClean(TZhuanzhang instance)
	{
		log.debug("attaching clean TZhuanzhang instance");
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
	 * ��spring�����л�ȡTZhuanzhangDAO���bean
	 * @param ctx
	 * @return
	 */
	public static TZhuanzhangDAO getFromApplicationContext(
			ApplicationContext ctx)
	{
		return (TZhuanzhangDAO) ctx.getBean("TZhuanzhangDAO");
	}
}