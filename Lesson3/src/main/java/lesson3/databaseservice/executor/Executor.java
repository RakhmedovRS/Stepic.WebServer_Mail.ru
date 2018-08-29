package lesson3.databaseservice.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Исполнитель запросов
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class Executor
{
	private final Connection connection;

	/**
	 * Конструктор
	 * @param connection экземпляр подключения к БД
	 */
	public Executor(Connection connection)
	{
		this.connection = connection;
	}

	/**
	 * Выполнить обновление
	 * @param update запрос
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public void executeUpdate(String update) throws SQLException
	{
		try (Statement statement = connection.createStatement())
		{
			statement.execute(update);
		}
	}

	/**
	 * Выполнить запрос к базе
	 *
	 * @param query запрос
	 * @param handler обработчик ответа
	 * @param <T> тип возвращаемого ответа
	 * @return результат выполнения запроса
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	public <T> T executeQuery(String query, ResultHandler<T> handler) throws SQLException
	{
		try (Statement statement = connection.createStatement())
		{
			statement.execute(query);
			ResultSet result = statement.getResultSet();
			T value = handler.handle(result);
			result.close();

			return value;
		}
	}
}
