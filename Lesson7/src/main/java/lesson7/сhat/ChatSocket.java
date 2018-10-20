package lesson7.сhat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author rassoll
 * @created 20.10.2018
 */
public class ChatSocket extends Thread
{
	private Socket socket;
	private ChatServer chatServer;

	public ChatSocket(Socket socket, ChatServer chatServer)
	{
		this.socket = socket;
		this.chatServer = chatServer;
	}

	@Override
	public void run()
	{
		try
		{
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String message = in.readUTF();
			java.util.logging.Logger.getGlobal().info(message);
			if (!message.equals("Bye."))
			{
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(message);
				out.flush();
			}
			else
			{
				chatServer.close();
			}
			in.close();
			socket.close();
		}
		catch (IOException io)
		{
			io.printStackTrace();
		}
	}
}
