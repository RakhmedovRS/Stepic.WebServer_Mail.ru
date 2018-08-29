package lesson2.servlets;

import lesson2.accounts.ProfileService;
import lesson2.accounts.UserProfile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rassoll
 * @created 18.08.2018
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

		UserProfile profile = profileService.getUserByLogin(login);
		if (profile == null || !profile.getPassword().equals(password))
		{
			response.setContentType(CONTENT_TYPE_TEXT);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		profileService.addSession(request.getSession().getId(), profile);
		response.getWriter().println("Authorized: " + login);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
