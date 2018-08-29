package lesson3.databaseservice.dao;

import lesson3.databaseservice.datasets.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Сущность объекта доступа к данным
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class UsersDAO
{
	private Session session;

	/**
	 * Конструктор
	 * @param session экземпляр сессии
	 */
	public UsersDAO(Session session)
	{
		this.session = session;
	}

	/**
	 * Получить пользователя по имени
	 *
	 * @param name имя пользователя
	 * @return идентификатор пользователя
	 */
	public User get(String name)
	{
		Criteria criteria = session.createCriteria(User.class);
		return ((User) criteria.add(Restrictions.eq("name", name)).uniqueResult());
	}

	/**
	 * Выполнить вставку данных о пользователе
	 *
	 * @param user пользователь
	 * @return идентификатор пользователя
	 */
	public long insertUser(User user)
	{
		return (Long) session.save(user);
	}
}
