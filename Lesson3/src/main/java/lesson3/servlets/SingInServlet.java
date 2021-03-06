package lesson3.servlets;

import lesson3.accounts.ProfileService;
import lesson3.databaseservice.datasets.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rassoll
 * @created 26.08.2018
 */
public class SingInServlet extends HttpServlet
{
	private static final String CONTENT_TYPE_TEXT = "text/html";

	private final ProfileService profileService;

	public SingInServlet(ProfileService profileService)
	{
		this.profileService = profileService;
	}

	/**
	 * Вход пользователя
	 *
	 * @param request  запрос
	 * @param response ответ
	 * @throws ServletException в случае ошибок сервлета
	 * @throws IOException в случе ошибок ввода\вывода
	 */
	@Override
	public void doPost(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException, IOException
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login == null || password == null)
		{
			response.setContentType(CONTENT_TYPE_TEXT);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		User user = profileService.getUserByLogin(login);
		if (user == null || !user.getPassword().equals(password))
		{
			response.setContentType(CONTENT_TYPE_TEXT);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("Unauthorized");
			return;
		}

//		profileService.addSession(request.getSession().getId(), profile);
		response.getWriter().println("Authorized: " + login);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
