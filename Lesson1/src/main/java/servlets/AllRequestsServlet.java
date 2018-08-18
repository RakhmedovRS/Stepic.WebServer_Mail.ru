package main.java.servlets;

import main.java.templater.PageGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Обработчик поступающих запросов
 *
 * @author rassoll
 * @ created 02.08.2018
 */
public class AllRequestsServlet extends HttpServlet
{
	private static Map<String, Object> createPageVariablesMap(HttpServletRequest request)
	{
		return new HashMap<>();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, Object> pageVariables = createPageVariablesMap(request);
		pageVariables.put("value", request.getParameter("key") == null ? "" : request.getParameter("key"));

		response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Map<String, Object> pageVariables = createPageVariablesMap(request);

		String message = request.getParameter("value");

		response.setContentType("text/html;charset=utf-8");

		if (message == null || message.isEmpty())
		{
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		else

		{
			response.setStatus(HttpServletResponse.SC_OK);
		}
		pageVariables.put("value", message == null ? "" : message);

		response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
	}
}