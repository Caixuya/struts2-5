package dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import po.House;
import po.Street;
import po.Type;
import po.User;

/**
 * A data access object (DAO) providing persistence and search support for House
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see po.House
 * @author MyEclipse Persistence Tools
 */

public class HouseDAO extends BaseHibernateDAO{
	private static final Logger log = LoggerFactory.getLogger(HouseDAO.class);

	public void save(House transientInstance) {
		log.debug("saving House instance");
		Transaction tx =null;
		try {
			tx=getSession().beginTransaction();
			//利用序列生成id
			SQLQuery q = getSession().createSQLQuery("select seq_id.nextval from dual");
			BigDecimal uniqueResult = (BigDecimal)q.uniqueResult();			
			transientInstance.setId(uniqueResult.doubleValue());	
			
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("save failed", re);
			throw re;
		}		
	}

	public void delete(House persistentInstance) {
		log.debug("deleting House instance");
		Transaction tx =null;
		try {
			tx=getSession().beginTransaction();
			getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");			
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			tx.rollback();
			throw re;
		}
		finally {closeSession();}
	}

	public House findById(java.lang.Double id) {
		log.debug("getting House instance with id: " + id);		
		try {
			House instance = (House)getSession().get("po.House", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<House> findByExample(House instance) {
		log.debug("finding House instance by example");
		try {
			List<House> results = (List<House>)getSession().createCriteria("po.House").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	//组合条件查询
	public List findByCriteria(User user,String title,Double lowPrice,Double highPrice,Street street,Type type,Double lowFloorage,Double highFloorage) {
		log.debug("finding House instance with Criteria: " +lowPrice+","+highPrice+","+street+","+type+","+lowFloorage+","+highFloorage);
		
		try {
			Criteria cr = getSession().createCriteria(House.class);
			
			if(user!=null) {
				cr.add(Restrictions.eq("user", user));
			}
			if(title!=null) {
				cr.add(Restrictions.ilike("title", "%"+title+"%"));
			}
			if(lowPrice!=null) {
				cr.add(Restrictions.ge("price", lowPrice));
			}
			if(highPrice!=null) {
				cr.add(Restrictions.le("price", highPrice));
			}
			if(street!=null) {
				cr.add(Restrictions.eq("street", street));
			}
			if(type!=null) {
				cr.add(Restrictions.eq("type", type));
			}
			if(lowFloorage!=null) {
				cr.add(Restrictions.ge("floorage", lowFloorage));
			}
			if(highFloorage!=null) {
				cr.add(Restrictions.le("floorage", highFloorage));
			}
			return cr.list();
		}
		catch(Exception e){
			log.debug("findByCriteria failed");
			System.out.println(e.toString());
			throw e;
		}

	
	}
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding House instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from House as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all House instances");
		try {
			String queryString = "from House";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void update(House transientInstance) {
		log.debug("updating House instance");
		Transaction tx =null;
		try {
			tx = getSession().beginTransaction();
			getSession().update(transientInstance);
			tx.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("update failed", re);
			throw re;
		} 
	}
	
	public House merge(House detachedInstance) {
		log.debug("merging House instance");
		try {
			House result = (House) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(House instance) {
		log.debug("attaching dirty House instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(House instance) {
		log.debug("attaching clean House instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}