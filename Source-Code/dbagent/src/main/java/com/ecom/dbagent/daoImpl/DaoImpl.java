package com.ecom.dbagent.daoImpl;

import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import com.ecom.dbagent.dao.DaoInterface;

/*
 * reference used
 * https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-jpa-dao-example/
 */

/**
 * @author Varun Hanabe Muralidhara UOttawa ID : 300055628
 */
/** DaoImpl implementation of the DaoInterface **/
public class DaoImpl implements DaoInterface {

	private Session currentSession;

	private Transaction currentTransaction;
	private static SessionFactory sessionFactory;

	public DaoImpl() {
	}

	/**
	 * @param T This method saves entity
	 */
	public <T> void persist(T entity) {
		getCurrentSession().save(entity);
	}

	/**
	 * This method updates the entity in db
	 * 
	 * @param hql
	 * @param paramValues
	 * @return int : updated rows
	 */
	public int update(String hql, Map<String, String> paramValues) {
		Query createQuery = getCurrentSession().createQuery(hql);
		for (Map.Entry<String, String> paramValue : paramValues.entrySet()) {
			createQuery.setParameter(paramValue.getKey(), paramValue.getValue());
		}
		int executeUpdate = createQuery.executeUpdate();
		return executeUpdate;
	}

	/**
	 * this methods returns list of entities
	 * 
	 * @param hql
	 * @return List<T>
	 */
	public <T> List<T> findAll(String hql) {
		@SuppressWarnings("unchecked")
		List<T> entities = (List<T>) getCurrentSession().createQuery(hql).list();
		return entities;
	}

	/**
	 * This method finds the entity
	 * 
	 * @param id
	 * @param classType
	 * @return entity
	 */
	public <T> T findEntity(String id, Class<T> classType) {
		T entity = (T) getCurrentSession().get(classType, id);
		return entity;
	}

	/**
	 * This method find all entities which matches the condition
	 * 
	 * @param hql
	 * @param paramValues
	 * @return List<T>
	 */
	public <T> List<T> findAllWithCondition(String hql, Map<String, String> paramValues) {
		Query createQuery = getCurrentSession().createQuery(hql);
		for (Map.Entry<String, String> paramValue : paramValues.entrySet()) {
			createQuery.setParameter(paramValue.getKey(), paramValue.getValue());
		}
		List<T> resultEntities = (List<T>) createQuery.list();
		return resultEntities;
	}

	/**
	 * This method return the number of rows in table
	 * 
	 * @param entity
	 * @return int
	 */

	public <T> int findCount(Class<T> entity) {

		Number uniqueResult = (Number) getCurrentSession().createCriteria(entity).setProjection(Projections.rowCount())
				.uniqueResult();
		return uniqueResult.intValue();
	}

	/**
	 * This method opens session from factory
	 * 
	 * @return Session
	 */
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	/**
	 * This method opens session from factory with transaction
	 * 
	 * @return Session
	 */
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	/**
	 * This method closes session with transaction
	 * 
	 */
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	/**
	 * This method closes session
	 */
	public void closeCurrentSession() {
		currentSession.close();
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			MetadataSources sources = new MetadataSources(registry);
			Metadata metadata = sources.getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			return sessionFactory;

		}
		return sessionFactory;
	}

}
