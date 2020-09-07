package com.mahdir.command;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.mahdir.Command;
import com.mahdir.InvalidArgumentException;
import com.mahdir.Session;

/**
 * This class is implementation of listen command. This command opens a port
 * on computer and listens on it for commands from the client. If user does
 * not set any ports, the port will be set on 16800 by default.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Listen implements Command
{
	public static boolean terminate = false;
	//private ServerSocket welcomingSocket;
	
	/**
	 * Does nothing
	 * @throws IOException
	 */
	public Listen() {}
	
	private static class Helper
	{
		public static ServerSocket welcomingSocket;
	}
	
	/**
	 * Returns the unique instance of socket.
	 * @return The unique instance
	 */
	public static ServerSocket getInstance()
	{
		return Helper.welcomingSocket;
	}
	
	/**
	 * Runs the listener.
	 */
	@Override
	public String execute(String[] args) throws InvalidArgumentException
	{
		terminate = false;
		
		try																		// Opening the port
		{
			int port;															// Checking default port
			if(args.length < 2)
				port = 16800;
			else
				port = Integer.parseInt(args[1]);
			Helper.welcomingSocket = new ServerSocket(port);
		} 
		catch (NumberFormatException e1)
		{
			throw new InvalidArgumentException("Invalid port number " + args[1]);
		}
		catch(IOException e)
		{
			throw new InvalidArgumentException(e.getMessage());
		}
		
		while(!terminate)														// Running listener loop
		{
			try 
			{
				System.out.println("Waiting for client...");
				Socket connection = Helper.welcomingSocket.accept();
				System.out.println("Client accepted. Running session...");
				Thread t = new Thread(new Session(connection));
				t.start();
			} 
			catch (IOException e) 
			{
				System.err.print(e.getMessage());
			}
		}
		
		return "Listener terminated.";
	}
	
	/**
	 * Tries to close the server socket.
	 * @throws IOException If any exception happens while closing connection, throws this.
	 */
	public static void closeConnection() throws IOException
	{
		terminate = true;
		getInstance().close();
	}
	
	@Override
	public String getHelp()
	{
		String text = "Listens on a port number for a command.\n\n"
				    + "Usage: listen <portnumber>\n";
		return text;
	}

}
