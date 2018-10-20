package lesson7.main;

import lesson7.сhat.ChatServer;

import java.io.IOException;

/**
 * @author rassoll
 * @created 20.10.2018
 */
public class Main
{
	public static void main(String[] args) throws IOException
	{
		try
		{
			ChatServer server = new ChatServer(5050);
			server.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
