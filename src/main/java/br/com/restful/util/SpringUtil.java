package br.com.restful.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class SpringUtil {

	private static SpringUtil instance;
	protected AbstractXmlApplicationContext context;

	// Hibernate
	protected HibernateTransactionManager txManager;
	private Session session;

	private SpringUtil() {
		try {
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
		} catch (BeansException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static SpringUtil getInstance() {
		if (instance == null) {
			instance = new SpringUtil();
		}
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public Object getBean(Class c) {
		if (context == null) {
			return null;
		}

		if (session == null) {
			openSession();
		}

		String[] beanNamesForType = context.getBeanNamesForType(c);
		if (beanNamesForType == null || beanNamesForType.length == 0) {
			return null;
		}
		String name = beanNamesForType[0];
		Object bean = getBean(name);
		return bean;
	}

	public Object getBean(String name) {
		if (context == null) {
			return null;
		}

		if (session == null) {
			openSession();
		}
		Object bean = context.getBean(name);
		return bean;
	}

	/*
	 * Deixa a Session viva nesta Thread. MEsma coisa que a thread de uma
	 * requisicao web utilizando ofiltro "OpenSessionInViewFilter"
	 */
	public Session openSession() {
		if (context != null) {
			txManager = (HibernateTransactionManager) context.getBean("transactionManager");
			SessionFactory sessionFactory = txManager.getSessionFactory();
			session = sessionFactory.openSession();
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		}
		return session;

	}

	/*
	 * Remove a Session da thread
	 */
	public void closeSession() {
		if (context != null && txManager != null) {
			SessionFactory sessionFactory = txManager.getSessionFactory();
			TransactionSynchronizationManager.unbindResource(sessionFactory);
			SessionFactoryUtils.closeSession(session);
			session = null;
		}
	}

	public Session getSession() {
		return session;
	}

	public SessionFactory getSessionFactory() {
		return txManager.getSessionFactory();
	}

}
