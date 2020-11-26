package dao;
import org.hibernate.Hibernate;
import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO {	
	public Session getSession() {
		return HibernateUtil.getSession();
	}	
	public void closeSession(){
		HibernateUtil.closeSession();
	}	
}