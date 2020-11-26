package dao;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import po.District;


/**
 * A data access object (DAO) providing persistence and search support for
 * District entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see po.District
 * @author MyEclipse Persistence Tools
 */

public class DistrictDAO{
	private static final Logger log = LoggerFactory.getLogger(DistrictDAO.class);
	public void save(District transientInstance) {
		log.debug("saving District instance");
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

	public void delete(District persistentInstance) {
		log.debug("deleting District instance");
		Transaction tx =null;
		try {
			tx=HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().delete(persistentInstance);
			log.debug("delete successful");
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("delete failed", re);
			throw re;
		}
		finally {HibernateUtil.closeSession();}
	}

	public District findById(java.lang.Double id) {
		log.debug("getting District instance with id: " + id);
		try {
			District instance = (District) HibernateUtil.getSession().get("po.District", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<District> findByExample(District instance) {
		log.debug("finding District instance by example");
		try {
			List<District> results = (List<District>) HibernateUtil.getSession()
					.createCriteria("po.District").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding District instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from District as model where model."
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
		log.debug("finding all District instances");
		try {
			String queryString = "from District";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public District merge(District detachedInstance) {
		log.debug("merging District instance");
		try {
			District result = (District) HibernateUtil.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void update(District transientInstance) {
		log.debug("updating District instance");
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
	
	public void attachDirty(District instance) {
		log.debug("attaching dirty District instance");
		try {
			HibernateUtil.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(District instance) {
		log.debug("attaching clean District instance");
		try {
			HibernateUtil.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}