package lesson3.accounts;

import lesson3.databaseservice.DataBaseService;
import lesson3.databaseservice.datasets.User;

/**
 * Сервис для работы с профилями
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class ProfileService
{
	private static final DataBaseService DATA_BASE_SERVICE = new DataBaseService();

	/**
	 * Добавить новый профиль
	 *
	 * @param userProfile профиль
	 */
	public void addNewUser(User userProfile)
	{
		try
		{
			DATA_BASE_SERVICE.addUser(userProfile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Получить профиль по логину
	 *
	 * @param login логин
	 * @return профиль
	 */
	public User getUserByLogin(String login)
	{
		try
		{
			return DATA_BASE_SERVICE.getUser(login);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
