package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import po.Street;
import po.User;


/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see po.User
 * @author MyEclipse Persistence Tools
 */

public class UserDAO{
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

	public void save(User transientInstance) {
		log.debug("saving Users instance");
		Transaction tx =null;
		try {
			tx=HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			tx.rollback();
			throw re;
		}
		finally {HibernateUtil.closeSession();}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting Users instance");
		Transaction tx =null;
		try {
			tx=HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			tx.rollback();
			throw re;
		}
		finally {HibernateUtil.closeSession();}
	}

	public void update(User transientInstance) {
		log.debug("updating Users instance");
		Transaction tx =null;
		try {
			tx = HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().update(transientInstance);
			tx.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("update failed", re);
			throw re;
		} finally{
			HibernateUtil.closeSession();
		}
	}
	public User findById(java.lang.Double id) {
		log.debug("getting Users instance with id: " + id);
		try {
			User instance = (User) HibernateUtil.getSession().get("po.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding Users instance by example");
		try {
			List<User> results = (List<User>) HibernateUtil.getSession()
					.createCriteria("po.User").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging Users instance");
		try {
			User result = (User) HibernateUtil.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty Users instance");
		try {
			HibernateUtil.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean Users instance");
		try {
			HibernateUtil.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}