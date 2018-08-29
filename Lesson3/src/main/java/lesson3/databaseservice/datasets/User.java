package lesson3.databaseservice.datasets;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Сущность пользователя
 *
 * @author rassoll
 * @created 26.08.2018
 */
@Entity
@Table(name = "user")
@PersistenceUnit
@SuppressWarnings("UnusedDeclaration")
public class User implements Serializable
{
	private static final long serialVersionUID = -8706689714326132798L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", unique = true, updatable = false)
	private String name;

	@Column(name = "password")
	private String password;

	/**
	 * Конструктор
	 */
	public User()
	{
	}

	/**
	 * Конструктор
	 *
	 * @param id идентификатор пользователя
	 * @param name имя пользователя
	 * @param password пароль
	 */
	public User(long id, String name, String password)
	{
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
	}

	/**
	 * Конструктор
	 *
	 * @param name имя пользователя
	 * @param password пароль
	 */
	public User(String name, String password)
	{
		this.setId(-1);
		this.setName(name);
		this.setPassword(password);
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	private void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "UserDataSet{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
