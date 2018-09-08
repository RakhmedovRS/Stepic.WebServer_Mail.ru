package lesson4.chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * @author rassoll
 * @created 08.09.2018
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet
{
	private static final int LOGOUT_TIME = 10 * 60 * 1000;

	public WebSocketChatServlet()
	{
	}

	@Override
	public void configure(WebSocketServletFactory factory)
	{
		factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
		factory.setCreator((req, resp) -> new ChatWebSocket());
	}
}
