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
/**
 * 存款表操作类
 */
public class TQuDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TQuDAO.class);

	// property constants
	//用户id字段名
	public static final String USER_ID = "userId";
	//金额字段名
	public static final String JINE = "jine";
	//时间字段名
	public static final String SHIJIAN = "shijian";
	//状态字段名
	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	/**
	 * 保存取款信息
	 * @param transientInstance
	 */
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

	/**
	 * 删除取款信息
	 * @param persistentInstance
	 */
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

	/**
	 * 根据id查询取款对象
	 * @param id
	 * @return
	 */
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

	/**
	 * 根据取款对象属性查询取款列表
	 * @param instance
	 * @return
	 */
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

	/**
	 * 根据属性名查询取款列表
	 * @param propertyName
	 * @param value
	 * @return
	 */
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

	/**
	 * 根据用户id查询取款信息列表
	 * @param userId
	 * @return
	 */
	public List findByUserId(Object userId)
	{
		return findByProperty(USER_ID, userId);
	}

	/**
	 * 根据金额查询取款列表
	 * @param jine
	 * @return
	 */
	public List findByJine(Object jine)
	{
		return findByProperty(JINE, jine);
	}

	/**
	 * 根据时间查询取款列表
	 * @param shijian
	 * @return
	 */
	public List findByShijian(Object shijian)
	{
		return findByProperty(SHIJIAN, shijian);
	}

	/**
	 * 根据状态查询取款列表
	 * @param del
	 * @return
	 */
	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	/**
	 * 查询所有取款信息列表
	 * @return
	 */
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

	/**
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param detachedInstance
	 * @return
	 */
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

	/**
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param instance
	 */
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

	/**
	 * 将对象对应的数据库记录升级为悲观锁，由此可以保证修改的串行化
	 * @param instance
	 */
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

	/**
	 * 从spring容器中获取TQuDAO这个bean
	 * @param ctx
	 * @return
	 */
	public static TQuDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TQuDAO) ctx.getBean("TQuDAO");
	}
}