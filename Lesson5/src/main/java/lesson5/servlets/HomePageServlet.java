package lesson5.servlets;

import lesson5.accountServer.AccountServer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rassoll
 * @created 22.09.2018
 */
public class HomePageServlet extends HttpServlet
{
	public static final String PAGE_URL = "/admin";
	private final AccountServer accountServer;

	public HomePageServlet(AccountServer accountServer)
	{
		this.accountServer = accountServer;
	}

	public void doGet(HttpServletRequest request,
	                  HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(accountServer.getUsersLimit());
		return;
	}
}
