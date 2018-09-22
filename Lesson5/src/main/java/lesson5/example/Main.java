package lesson5.example;

import lesson5.accountServer.AccountServer;
import lesson5.accountServer.AccountServerController;
import lesson5.accountServer.AccountServerControllerMBean;
import lesson5.accountServer.AccountServerImpl;
import lesson5.servlets.HomePageServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author rassoll
 * @created 22.09.2018
 */
public class Main
{
	private static final Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) throws Exception
	{
		if (args.length != 1) {
			logger.error("Use port as the first argument");
			System.exit(1);
		}

		int port = Integer.parseInt(args[0]);

		AccountServer accountServer = new AccountServerImpl(10);

		AccountServerControllerMBean serverStatistics = new AccountServerController(accountServer);
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
		mbs.registerMBean(serverStatistics, name);

		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);

		server.setHandler(context);

		server.start();
		java.util.logging.Logger.getGlobal().info("Server started");
		server.join();
	}
}
