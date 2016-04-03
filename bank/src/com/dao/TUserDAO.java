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
 * 用户表操作类
 */
public class TUserDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TUserDAO.class);

	// property constants
	//真实姓名字段名
	public static final String REALNAME = "realname";
	//性别字段名
	public static final String SEX = "sex";
	//年龄字段名
	public static final String AGE = "age";
	//地址字段名
	public static final String ADDRESS = "address";
	//电话字段名
	public static final String TEL = "tel";
	//邮箱字段名
	public static final String EMAIL = "email";
	//卡号字段名
	public static final String KAHAO = "kahao";
	//密码字段名
	public static final String PS = "ps";
	//身份证号字段名
	public static final String SHENFENZHENG = "shenfenzheng";
	//余额字段名
	public static final String YUE = "yue";
	//状态字段名
	public static final String DEL = "del";
	
	protected void initDao()
	{
		// do nothing
	}

	/**
	 * 保存用户信息
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
	 * 删除用户
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
	 * 根据用户id查询用户对象
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
	 * 根据用户对象属性查询用户列表
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
	 * 根据属性名查询用户列表
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
	 * 根据真实姓名查询用户列表
	 * @param realname
	 * @return
	 */
	public List findByRealname(Object realname)
	{
		return findByProperty(REALNAME, realname);
	}

	/**
	 * 根据性别查询用户列表
	 * @param sex
	 * @return
	 */
	public List findBySex(Object sex)
	{
		return findByProperty(SEX, sex);
	}

	/**
	 * 根据年龄查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByAge(Object age)
	{
		return findByProperty(AGE, age);
	}

	/**
	 * 根据地址查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByAddress(Object address)
	{
		return findByProperty(ADDRESS, address);
	}

	/**
	 * 根据电话查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByTel(Object tel)
	{
		return findByProperty(TEL, tel);
	}
	
	/**
	 * 根据邮箱查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByEmail(Object email)
	{
		return findByProperty(EMAIL, email);
	}

	/**
	 * 根据卡号查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByKahao(Object kahao)
	{
		return findByProperty(KAHAO, kahao);
	}

	/**
	 * 根据密码查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByPs(Object ps)
	{
		return findByProperty(PS, ps);
	}

	/**
	 * 根据身份证号查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByShenfenzheng(Object shenfenzheng)
	{
		return findByProperty(SHENFENZHENG, shenfenzheng);
	}

	/**
	 * 根据余额查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByYue(Object yue)
	{
		return findByProperty(YUE, yue);
	}

	/**
	 * 根据状态查询用户列表
	 * @param sex
	 * @return
	 */
	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	/**
	 * 查询所有用户列表
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
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
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
	 * 如果对象存在数据库中,则转成持久态
	 * 如果不存在,则保存到数据库中,再转成持久态
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
	 * 将对象对应的数据库记录升级为悲观锁，由此可以保证修改的串行化
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
	 * 从spring容器中获取TUserDAO这个bean
	 * @param ctx
	 * @return
	 */
	public static TUserDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TUserDAO) ctx.getBean("TUserDAO");
	}
}