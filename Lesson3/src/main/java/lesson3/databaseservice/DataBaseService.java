package lesson3.databaseservice;

import lesson3.databaseservice.dao.UsersDAO;
import lesson3.databaseservice.datasets.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rassoll
 * @created 26.08.2018
 */
public class DataBaseService
{
	private static final String HIBERNATE_SHOW_SQL = "false";
	private static final String HIBERNATE_HBM2DDL_AUTO = "update";

	private final SessionFactory sessionFactory;

	public DataBaseService()
	{
		Configuration configuration = getH2Configuration();
		sessionFactory = createSessionFactory(configuration);
	}

	@SuppressWarnings("UnusedDeclaration")
	private Configuration getMySqlConfiguration()
	{
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
		configuration.setProperty("hibernate.connection.username", "tully");
		configuration.setProperty("hibernate.connection.password", "tully");
		configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);

		return configuration;
	}

	private Configuration getH2Configuration()
	{
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
		configuration.setProperty("hibernate.connection.username", "tully");
		configuration.setProperty("hibernate.connection.password", "tully");
		configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		return configuration;
	}

	public User getUser(String login) throws DataBaseException
	{
		try
		{
			Session session = sessionFactory.openSession();
			UsersDAO dao = new UsersDAO(session);
			User dataSet = dao.get(login);
			session.close();
			return dataSet;
		}
		catch (HibernateException e)
		{
			throw new DataBaseException(e);
		}
	}

	public long addUser(User user) throws DataBaseException
	{
		try
		{
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			UsersDAO dao = new UsersDAO(session);
			long id = dao.insertUser(user);
			transaction.commit();
			session.close();
			return id;
		}
		catch (HibernateException e)
		{
			throw new DataBaseException(e);
		}
	}

	private static SessionFactory createSessionFactory(Configuration configuration)
	{
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
}
