package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TLilv;

/**
 * Data access object (DAO) for domain model class TLilv.
 * 
 * @see com.model.TLilv
 * @author MyEclipse Persistence Tools
 */
/**
 * 利率表操作类
 */
public class TLilvDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TLilvDAO.class);

	// property constants
	//利率字段名
	public static final String LILV = "lilv";

	protected void initDao()
	{
		// do nothing
	}

	/**
	 * 保存利率信息
	 * @param transientInstance
	 */
	public void save(TLilv transientInstance)
	{
		log.debug("saving TLilv instance");
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
	 * 删除利率信息
	 * @param persistentInstance
	 */
	public void delete(TLilv persistentInstance)
	{
		log.debug("deleting TLilv instance");
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
	 * 根据利率id查询对象
	 * @param id
	 * @return
	 */
	public TLilv findById(java.lang.Integer id)
	{
		log.debug("getting TLilv instance with id: " + id);
		try
		{
			TLilv instance = (TLilv) getHibernateTemplate().get(
					"com.model.TLilv", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 根据利率信息属性查询利率列表
	 * @param instance
	 * @return
	 */
	public List findByExample(TLilv instance)
	{
		log.debug("finding TLilv instance by example");
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
	 * 根据利率属性查询列表
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TLilv instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TLilv as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 根据利率查询对象
	 * @param lilv
	 * @return
	 */
	public List findByLilv(Object lilv)
	{
		return findByProperty(LILV, lilv);
	}

	/**
	 * 查询所有利率信息列表
	 * @return
	 */
	public List findAll()
	{
		log.debug("finding all TLilv instances");
		try
		{
			String queryString = "from TLilv";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param detachedInstance
	 * @return
	 */
	public TLilv merge(TLilv detachedInstance)
	{
		log.debug("merging TLilv instance");
		try
		{
			TLilv result = (TLilv) getHibernateTemplate().merge(
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
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param instance
	 */
	public void attachDirty(TLilv instance)
	{
		log.debug("attaching dirty TLilv instance");
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
	 * 将对象对应的数据库记录升级为悲观锁，由此可以保证修改的串行化
	 * @param instance
	 */
	public void attachClean(TLilv instance)
	{
		log.debug("attaching clean TLilv instance");
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
	 * 从spring容器中获取TLilvDAO这个bean
	 * @param ctx
	 * @return
	 */
	public static TLilvDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TLilvDAO) ctx.getBean("TLilvDAO");
	}
}