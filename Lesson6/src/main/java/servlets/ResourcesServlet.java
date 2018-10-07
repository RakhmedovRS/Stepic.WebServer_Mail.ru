package servlets;

import resources.ResourceServerController;
import resources.TestResourceI;
import sax.ReadXMLFileSAX;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rassoll
 * @created 05.10.2018
 */
public class ResourcesServlet extends HttpServlet
{
	public static final String PAGE_URL = "/resources";
	private static TestResourceI testResource;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException, IOException
	{
		String path = request.getParameter("path");

		java.util.logging.Logger.getGlobal().log(Level.WARNING, path);
		if (testResource == null)
		{
			testResource = (TestResourceI) ReadXMLFileSAX.readXML(path);
		}

		try
		{
			ResourceServerController resourceController = new ResourceServerController(testResource);
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("Admin:type=ResourceServerController");
			if (!mbs.isRegistered(name))
			{
				mbs.registerMBean(resourceController, name);
			}
		}
		catch (Exception e)
		{
			throw new ServletException(e);
		}

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		return;
	}
}

