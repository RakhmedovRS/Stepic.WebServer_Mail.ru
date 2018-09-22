package lesson5.accountServer;

/**
 * @author rassoll
 * @created 22.09.2018
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean
{
	int getUsers();

	int getUsersLimit();

	void setUsersLimit(int usersLimit);
}
