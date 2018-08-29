package lesson2.main;

import lesson2.accounts.ProfileService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import lesson2.servlets.SingInServlet;
import lesson2.servlets.SingUpServlet;

/**
 * Точка входа
 *
 * @author rassoll
 * @created 18.08.2018
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		ProfileService accountService = new ProfileService();

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(new SingUpServlet(accountService)), "/signup");
		context.addServlet(new ServletHolder(new SingInServlet(accountService)), "/signin");

		Server server = new Server(8080);
		server.setHandler(context);

		server.start();
		java.util.logging.Logger.getGlobal().info("Server started");
		server.join();
	}
}
