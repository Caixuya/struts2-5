package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import po.Street;


/**
 * A data access object (DAO) providing persistence and search support for
 * Street entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see po.Street
 * @author MyEclipse Persistence Tools
 */

public class StreetDAO{
	private static final Logger log = LoggerFactory.getLogger(StreetDAO.class);

	public void save(Street transientInstance) {
		log.debug("saving Street instance");
		Transaction tx =null;
		try {
			tx=HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("save failed", re);
			throw re;
		}
		finally {HibernateUtil.closeSession();}
	}

	public void delete(Street persistentInstance) {
		log.debug("deleting Street instance");
		Transaction tx =null;
		try {
			tx=HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("delete failed", re);
			throw re;
		}
		finally {HibernateUtil.closeSession();}
	}

	public Street findById(java.lang.Double id) {
		log.debug("getting Street instance with id: " + id);
		try {
			Street instance = (Street) HibernateUtil.getSession().get("po.Street", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Street> findByExample(Street instance) {
		log.debug("finding Street instance by example");
		try {
			List<Street> results = (List<Street>) HibernateUtil.getSession()
					.createCriteria("po.Street").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Street instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Street as model where model."
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
		log.debug("finding all Street instances");
		try {
			String queryString = "from Street";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Street merge(Street detachedInstance) {
		log.debug("merging Street instance");
		try {
			Street result = (Street) HibernateUtil.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Street instance) {
		log.debug("attaching dirty Street instance");
		try {
			HibernateUtil.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Street instance) {
		log.debug("attaching clean Street instance");
		try {
			HibernateUtil.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void update(Street transientInstance) {
		log.debug("updating Street instance");
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
}