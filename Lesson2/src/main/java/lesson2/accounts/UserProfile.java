package lesson2.accounts;

/**
 * Профиль пользователя
 *
 * @author rassoll
 * @created 18.08.2018
 */
public class UserProfile
{
	private final String login;
	private final String password;

	/**
	 * Конструктор
	 *
	 * @param login логин
	 * @param password пароль
	 */
	public UserProfile(String login, String password)
	{
		this.login = login;
		this.password = password;
	}

	/**
	 * @return логин
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * @return пароль
	 */
	public String getPassword()
	{
		return password;
	}

}
