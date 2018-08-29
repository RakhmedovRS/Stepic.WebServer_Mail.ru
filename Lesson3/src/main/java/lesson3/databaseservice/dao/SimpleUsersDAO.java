package lesson3.databaseservice.dao;

import lesson3.databaseservice.datasets.SimpleUsersDataSet;
import lesson3.databaseservice.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Сущность объекта доступа к данным
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class SimpleUsersDAO
{
	private Executor executor;

	/**
	 * Конструктор
	 *
	 * @param connection экземпляр подключения к БД
	 */
	public SimpleUsersDAO(Connection connection)
	{
		this.executor = new Executor(connection);
	}

	/**
	 * Получить данные о пользователе
	 *
	 * @param id идентификатор
	 * @return набор данных
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public SimpleUsersDataSet get(long id) throws SQLException
	{
		return executor.executeQuery("select * from users where id=" + id, result ->
		{
			result.next();
			return new SimpleUsersDataSet(result.getLong(1), result.getString(2));
		});
	}

	/**
	 * Получить идентификатор пользователя
	 *
	 * @param name имя пользователя
	 * @return идентификатор пользователя
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public long getUserId(String name) throws SQLException
	{
		return executor.executeQuery("select * from users where user_name='" + name + "'", result ->
		{
			result.next();
			return result.getLong(1);
		});
	}

	/**
	 * Вставить данные о пользователе в БД
	 *
	 * @param name имя пользователя
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public void insertUser(String name) throws SQLException
	{
		executor.executeUpdate("insert into users (user_name) values ('" + name + "')");
	}

	/**
	 * Создать таблицу
	 *
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public void createTable() throws SQLException
	{
		executor.executeUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
	}

	/**
	 * Удалить таблицу
	 *
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public void dropTable() throws SQLException
	{
		executor.executeUpdate("drop table users");
	}
}
