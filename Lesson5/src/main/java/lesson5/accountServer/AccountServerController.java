package lesson5.accountServer;

/**
 * @author rassoll
 * @created 22.09.2018
 */
public class AccountServerController implements AccountServerControllerMBean
{
	private final AccountServer accountServer;

	public AccountServerController(AccountServer accountServer)
	{
		this.accountServer = accountServer;
	}

	@Override
	public int getUsers()
	{
		return accountServer.getUsersCount();
	}

	@Override
	public int getUsersLimit()
	{
		return accountServer.getUsersLimit();
	}

	@Override
	public void setUsersLimit(int bla)
	{
		accountServer.setUsersLimit(bla);
	}
}
