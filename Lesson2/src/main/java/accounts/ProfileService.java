package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для работы с профилями
 *
 * @author rassoll
 * @created 18.08.2018
 */
public class ProfileService
{
	private final Map<String, UserProfile> loginToProfile;
	private final Map<String, UserProfile> sessionIDToProfile;

	public ProfileService()
	{
		loginToProfile = new HashMap<>();
		sessionIDToProfile = new HashMap<>();
	}

	/**
	 * Добавить новый профиль
	 *
	 * @param userProfile профиль
	 */
	public void addNewUser(UserProfile userProfile)
	{
		loginToProfile.put(userProfile.getLogin(), userProfile);
	}

	/**
	 * Получить профиль по логину
	 *
	 * @param login логин
	 * @return профиль
	 */
	public UserProfile getUserByLogin(String login)
	{
		return loginToProfile.get(login);
	}

	/**
	 * Получить профиль по логину
	 *
	 * @param sessionID идентификатор сессии
	 * @return профиль
	 */
	public UserProfile getUserBySessionID(String sessionID)
	{
		return sessionIDToProfile.get(sessionID);
	}

	/**
	 * Добавить информацию о сессии пользователя
	 *
	 * @param sessionID   идентификатор сессии
	 * @param userProfile профиль
	 */
	public void addSession(String sessionID, UserProfile userProfile)
	{
		sessionIDToProfile.put(sessionID, userProfile);
	}

	/**
	 * Удалить данные о сессии
	 *
	 * @param sessionID идентификатор сессии
	 */
	public void deleteSession(String sessionID)
	{
		sessionIDToProfile.remove(sessionID);
	}
}
