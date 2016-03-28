package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TQu;

/**
 * Data access object (DAO) for domain model class TQu.
 * 
 * @see com.model.TQu
 * @author MyEclipse Persistence Tools
 */

public class TQuDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TQuDAO.class);

	// property constants
	public static final String USER_ID = "userId";

	public static final String JINE = "jine";

	public static final String SHIJIAN = "shijian";

	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	public void save(TQu transientInstance)
	{
		log.debug("saving TQu instance");
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

	public void delete(TQu persistentInstance)
	{
		log.debug("deleting TQu instance");
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

	public TQu findById(java.lang.Integer id)
	{
		log.debug("getting TQu instance with id: " + id);
		try
		{
			TQu instance = (TQu) getHibernateTemplate()
					.get("com.model.TQu", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TQu instance)
	{
		log.debug("finding TQu instance by example");
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

	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TQu instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TQu as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId)
	{
		return findByProperty(USER_ID, userId);
	}

	public List findByJine(Object jine)
	{
		return findByProperty(JINE, jine);
	}

	public List findByShijian(Object shijian)
	{
		return findByProperty(SHIJIAN, shijian);
	}

	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	public List findAll()
	{
		log.debug("finding all TQu instances");
		try
		{
			String queryString = "from TQu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TQu merge(TQu detachedInstance)
	{
		log.debug("merging TQu instance");
		try
		{
			TQu result = (TQu) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TQu instance)
	{
		log.debug("attaching dirty TQu instance");
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

	public void attachClean(TQu instance)
	{
		log.debug("attaching clean TQu instance");
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

	public static TQuDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TQuDAO) ctx.getBean("TQuDAO");
	}
}