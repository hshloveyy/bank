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
/**
 * 存款表操作类
 *
 */
public class TCunDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TCunDAO.class);

	// property constants
	//用户id字段名
	public static final String USER_ID = "userId";
	//金额字段名
	public static final String JINE = "jine";
	//状态字段名
	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	/**
	 * 保存存款信息
	 * @param transientInstance
	 */
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

	/**
	 * 删除存款信息
	 * @param persistentInstance
	 */
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

	/**
	 * 根据用户id查询对象
	 * @param del
	 * @return
	 */
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

	/**
	 * 根据对象属性查询存款信息
	 * @param del
	 * @return
	 */
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

	/**
	 * 根据字段名查询存款信息
	 * @param del
	 * @return
	 */
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

	/**
	 * 根据用户id查询存款信息
	 * @param del
	 * @return
	 */
	public List findByUserId(Object userId)
	{
		return findByProperty(USER_ID, userId);
	}

	/**
	 * 根据金额查询存款信息
	 * @param del
	 * @return
	 */
	public List findByJine(Object jine)
	{
		return findByProperty(JINE, jine);
	}

	/**
	 * 根据删除状态查询存款信息
	 * @param del
	 * @return
	 */
	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	/**
	 * 查询所有存款信息列表
	 * @return
	 */
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

	/**
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param detachedInstance
	 * @return
	 */
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

	/**
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
	 * @param instance
	 */
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

	/**
	 * 将对象对应的数据库记录升级为悲观锁，由此可以保证修改的串行化
	 * @param instance
	 */
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

	/**
	 * 从spring容器中获取TCunDAO这个bean
	 * @param ctx
	 * @return
	 */
	public static TCunDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TCunDAO) ctx.getBean("TCunDAO");
	}
}