package lesson3.databaseservice;

import lesson3.databaseservice.dao.SimpleUsersDAO;
import lesson3.databaseservice.datasets.SimpleUsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Сервис для работы с БД
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class SimpleDataBaseService
{
	private final Connection connection;

	public SimpleDataBaseService()
	{
		this.connection = getH2Connection();
	}

	public SimpleUsersDataSet getUser(long id) throws DataBaseException
	{
		try
		{
			return (new SimpleUsersDAO(connection).get(id));
		}
		catch (SQLException e)
		{
			throw new DataBaseException(e);
		}
	}

	public long addUser(String name) throws DataBaseException
	{
		try
		{
			connection.setAutoCommit(false);
			SimpleUsersDAO dao = new SimpleUsersDAO(connection);
			dao.createTable();
			dao.insertUser(name);
			connection.commit();
			return dao.getUserId(name);
		}
		catch (SQLException e)
		{
			try
			{
				connection.rollback();
			}
			catch (SQLException ignore)
			{
			}
			throw new DataBaseException(e);
		}
		finally
		{
			try
			{
				connection.setAutoCommit(true);
			}
			catch (SQLException ignore)
			{
			}
		}
	}

	public void cleanUp() throws DataBaseException
	{
		SimpleUsersDAO dao = new SimpleUsersDAO(connection);
		try
		{
			dao.dropTable();
		}
		catch (SQLException e)
		{
			throw new DataBaseException(e);
		}
	}

	public void printConnectInfo()
	{
		try
		{
			System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
			System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
			System.out.println("Driver: " + connection.getMetaData().getDriverName());
			System.out.println("Autocommit: " + connection.getAutoCommit());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings({"UnusedDeclaration", "squid:S2068"})
	public static Connection getMysqlConnection()
	{
		try
		{
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

			StringBuilder url = new StringBuilder();

			url.
				append("jdbc:mysql://").        //db type
				append("localhost:").           //host name
				append("3306/").                //port
				append("db_example?").          //db name
				append("user=tully&").          //login
				append("password=tully");       //password

			System.out.println("URL: " + url + "\n");

			return DriverManager.getConnection(url.toString());
		}
		catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getH2Connection()
	{
		try
		{
			String url = "jdbc:h2:./h2db";
			String name = "tully";
			String pass = "tully";

			JdbcDataSource ds = new JdbcDataSource();
			ds.setURL(url);
			ds.setUser(name);
			ds.setPassword(pass);

			return DriverManager.getConnection(url, name, pass);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
