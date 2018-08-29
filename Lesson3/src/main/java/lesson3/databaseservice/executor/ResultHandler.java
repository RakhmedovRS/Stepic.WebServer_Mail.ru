package lesson3.databaseservice.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс обработчика результата запроса в БД
 *
 * @author rassoll
 * @created 26.08.2018
 */
public interface ResultHandler<T>
{
	/**
	 * Обработать результирующий набор
	 *
	 * @param resultSet результирующий набор
	 * @return результат
	 * @throws SQLException в случае ошибок при работе с БД
	 */
	T handle(ResultSet resultSet) throws SQLException;
}
