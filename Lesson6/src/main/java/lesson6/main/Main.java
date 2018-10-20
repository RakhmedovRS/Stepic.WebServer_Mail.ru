package lesson6.main;

import lesson6.servlets.ResourcesServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author rassoll
 * @created 05.10.2018
 */
public class Main
{
	public static void main(String[] args) throws Exception
	{
//		TestResourceI testResource = (TestResource) ReadXMLFileSAX.readXML("./Lesson6/data/Resources.xdb");

		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(new ResourcesServlet()), ResourcesServlet.PAGE_URL);

		server.setHandler(context);

		server.start();
		java.util.logging.Logger.getGlobal().info("Server started");
		server.join();
	}
}
