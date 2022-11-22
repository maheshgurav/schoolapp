package com.samarthsoft.prabandhak.connector;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
	private static final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry;

	private HibernateUtil() {
		// Utility class
	}

	public static SessionFactory getSessionFactory() {
		LOG.debug("loading Hibernate Configuration...");
		try {
			// TODO : Call load configuration code via static block
			ConfigurationLoader.loadConfiguration("school_on_web.properties");

			Configuration configuration = new Configuration();
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + System.getProperty("database.host")
					+ ":" + System.getProperty("database.port") + "/" + System.getProperty("database.name"));
			configuration.setProperty("hibernate.connection.username", System.getProperty("database.login"));
			configuration.setProperty("hibernate.connection.password", System.getProperty("database.password"));
			configuration.configure();

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					   configuration.getProperties()). build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception ex) {
			LOG.error("", ex);
		}
		return sessionFactory;
	}
}
