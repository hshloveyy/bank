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
 * 管理员表操作类
 */
public class TAdminDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TAdminDAO.class);

	// property constants
	//用户名字段名
	public static final String USER_NAME = "userName";

	//密码字段名
	public static final String USER_PW = "userPw";

	protected void initDao() {
		// do nothing
	}

	/**
	 * 将管理员信息保存到数据库中
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
	 * 将管理员信息从数据库中删除
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
	 * 根据用户id查询管理员信息
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
	 * 根据管理员实例中的参数查询管理员信息
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
	 * 根据管理员实例中的参数查询管理员信息
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
	 * 根据管理员用户名查询管理员信息
	 * @param userName
	 * @return
	 */
	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	/**
	 * 根据管理员密码查询管理员信息
	 * @param userPw
	 * @return
	 */
	public List findByUserPw(Object userPw) {
		return findByProperty(USER_PW, userPw);
	}

	/**
	 * 查询所有管理员信息列表
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
	 * 如果管理员实例存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
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
	 * 如果管理员实例存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
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
	 * 将对象对应的数据库记录升级为悲观锁，由此可以保证修改的串行化
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
	 * 从spring容器中获取TAdminDAO这个bean
	 * @param ctx
	 * @return
	 */
	public static TAdminDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TAdminDAO) ctx.getBean("TAdminDAO");
	}
}