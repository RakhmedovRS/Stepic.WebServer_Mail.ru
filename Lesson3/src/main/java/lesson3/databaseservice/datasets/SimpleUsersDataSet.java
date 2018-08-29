package lesson3.databaseservice.datasets;

/**
 * Набор данных пользователей
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class SimpleUsersDataSet
{
	private long id;
	private String name;

	/**
	 * Конструктор
	 *
	 * @param id   идентификатор пользователя
	 * @param name имя пользователя
	 */
	public SimpleUsersDataSet(long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * @return имя пользователя
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return идентификатор пользователя
	 */
	public long getId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return "SimpleUsersDataSet{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
