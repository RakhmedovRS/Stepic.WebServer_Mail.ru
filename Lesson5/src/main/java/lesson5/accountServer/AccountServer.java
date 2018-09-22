package lesson5.accountServer;

/**
 * @author rassoll
 * @created 22.09.2018
 */
public interface AccountServer
{
	int getUsersLimit();

	void setUsersLimit(int usersLimit);

	int getUsersCount();
}
