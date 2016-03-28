package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TCun;

/**
 * Data access object (DAO) for domain model class TCun.
 * 
 * @see com.model.TCun
 * @author MyEclipse Persistence Tools
 */

public class TCunDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TCunDAO.class);

	// property constants
	public static final String USER_ID = "userId";

	public static final String JINE = "jine";

	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	public void save(TCun transientInstance)
	{
		log.debug("saving TCun instance");
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

	public void delete(TCun persistentInstance)
	{
		log.debug("deleting TCun instance");
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

	public TCun findById(java.lang.Integer id)
	{
		log.debug("getting TCun instance with id: " + id);
		try
		{
			TCun instance = (TCun) getHibernateTemplate().get("com.model.TCun",
					id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TCun instance)
	{
		log.debug("finding TCun instance by example");
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
		log.debug("finding TCun instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TCun as model where model."
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

	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	public List findAll()
	{
		log.debug("finding all TCun instances");
		try
		{
			String queryString = "from TCun";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TCun merge(TCun detachedInstance)
	{
		log.debug("merging TCun instance");
		try
		{
			TCun result = (TCun) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TCun instance)
	{
		log.debug("attaching dirty TCun instance");
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

	public void attachClean(TCun instance)
	{
		log.debug("attaching clean TCun instance");
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

	public static TCunDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TCunDAO) ctx.getBean("TCunDAO");
	}
}