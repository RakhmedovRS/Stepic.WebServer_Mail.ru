package lesson3.main;

import lesson3.accounts.ProfileService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import lesson3.servlets.SingInServlet;
import lesson3.servlets.SingUpServlet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Точка входа
 *
 * @author rassoll
 * @created 26.08.2018
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
		Logger.getGlobal().setLevel(Level.OFF);

		ProfileService profileService = new ProfileService();

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(new SingUpServlet(profileService)), "/signup");
		context.addServlet(new ServletHolder(new SingInServlet(profileService)), "/signin");

		Server server = new Server(8080);
		server.setHandler(context);

		server.start();
		java.util.logging.Logger.getGlobal().info("Server started");
		server.join();
	}
}
