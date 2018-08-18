package servlets;

import accounts.ProfileService;
import accounts.UserProfile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rassoll
 * @created 18.08.2018
 */
public class SingUpServlet extends HttpServlet
{
	private static final String CONTENT_TYPE_TEXT = "text/html";

	private final ProfileService profileService;

	public SingUpServlet(ProfileService profileService)
	{
		this.profileService = profileService;
	}

	/**
	 * Регистрация пользователя
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

		profileService.addNewUser(new UserProfile(login, password));

		response.setStatus(HttpServletResponse.SC_OK);
	}
}
