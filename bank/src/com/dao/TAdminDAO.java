package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TAdmin;

/**
 * Data access object (DAO) for domain model class TAdmin.
 * 
 * @see com.model.TAdmin
 * @author MyEclipse Persistence Tools
 */
/**
 * ����Ա�������
 */
public class TAdminDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TAdminDAO.class);

	// property constants
	//�û����ֶ���
	public static final String USER_NAME = "userName";

	//�����ֶ���
	public static final String USER_PW = "userPw";

	protected void initDao() {
		// do nothing
	}

	/**
	 * ������Ա��Ϣ���浽���ݿ���
	 * @param transientInstance
	 */
	public void save(TAdmin transientInstance) {
		log.debug("saving TAdmin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * ������Ա��Ϣ�����ݿ���ɾ��
	 * @param persistentInstance
	 */
	public void delete(TAdmin persistentInstance) {
		log.debug("deleting TAdmin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * �����û�id��ѯ����Ա��Ϣ
	 * @param id
	 * @return
	 */
	public TAdmin findById(java.lang.Integer id) {
		log.debug("getting TAdmin instance with id: " + id);
		try {
			TAdmin instance = (TAdmin) getHibernateTemplate().get(
					"com.model.TAdmin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * ���ݹ���Աʵ���еĲ�����ѯ����Ա��Ϣ
	 * @param instance
	 * @return
	 */
	public List findByExample(TAdmin instance) {
		log.debug("finding TAdmin instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/**
	 * ���ݹ���Աʵ���еĲ�����ѯ����Ա��Ϣ
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAdmin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TAdmin as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * ���ݹ���Ա�û�����ѯ����Ա��Ϣ
	 * @param userName
	 * @return
	 */
	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	/**
	 * ���ݹ���Ա�����ѯ����Ա��Ϣ
	 * @param userPw
	 * @return
	 */
	public List findByUserPw(Object userPw) {
		return findByProperty(USER_PW, userPw);
	}

	/**
	 * ��ѯ���й���Ա��Ϣ�б�
	 * @return
	 */
	public List findAll() {
		log.debug("finding all TAdmin instances");
		try {
			String queryString = "from TAdmin";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * �������Աʵ���������ݿ���,��ת�ɳ־�̬
	 * ���������,�򱣴浽���ݿ���,��ת�ɳ־�̬
	 * @param detachedInstance
	 * @return
	 */
	public TAdmin merge(TAdmin detachedInstance) {
		log.debug("merging TAdmin instance");
		try {
			TAdmin result = (TAdmin) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/**
	 * �������Աʵ���������ݿ���,��ת�ɳ־�̬
	 * ���������,�򱣴浽���ݿ���,��ת�ɳ־�̬
	 * @param instance
	 */
	public void attachDirty(TAdmin instance) {
		log.debug("attaching dirty TAdmin instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * �������Ӧ�����ݿ��¼����Ϊ���������ɴ˿��Ա�֤�޸ĵĴ��л�
	 * @param instance
	 */
	public void attachClean(TAdmin instance) {
		log.debug("attaching clean TAdmin instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * ��spring�����л�ȡTAdminDAO���bean
	 * @param ctx
	 * @return
	 */
	public static TAdminDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TAdminDAO) ctx.getBean("TAdminDAO");
	}
}