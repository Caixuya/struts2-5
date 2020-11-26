package dao;

import static org.hibernate.criterion.Example.create;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import po.House;
import po.Type;


public class TypeDAO {
	private static final Logger log = LoggerFactory.getLogger(TypeDAO.class);

	public void save(Type transientInstance) {
		log.debug("saving Type instance");
		try {
			HibernateUtil.getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Type persistentInstance) {
		log.debug("deleting Type instance");
		try {
			HibernateUtil.getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(Type transientInstance) {
		log.debug("updating Type instance");
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

	public Type findById(java.lang.Double id) {
		log.debug("getting Type instance with id: " + id);
		try {
			Type instance = (Type) HibernateUtil.getSession().get("po.Type", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Type> findByExample(Type instance) {
		log.debug("finding Type instance by example");
		try {
			List<Type> results = (List<Type>) HibernateUtil.getSession()
					.createCriteria("po.Type").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Type instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Type as model where model."
					+ propertyName + "= ?";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Type> findAll() {
		log.debug("finding all Type instances");
		try {
			String queryString = "from Type";
			Query queryObject = HibernateUtil.getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Type merge(Type detachedInstance) {
		log.debug("merging Type instance");
		try {
			Type result = (Type) HibernateUtil.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Type instance) {
		log.debug("attaching dirty Type instance");
		try {
			HibernateUtil.getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Type instance) {
		log.debug("attaching clean Type instance");
		try {
			HibernateUtil.getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}
