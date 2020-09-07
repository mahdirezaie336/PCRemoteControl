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
	private ServerSocket welcomingSocket;
	
	/**
	 * Does nothing
	 * @throws IOException
	 */
	public Listen() {}
	
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
			welcomingSocket = new ServerSocket(port);
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
				Socket connection = welcomingSocket.accept();
				System.out.println("Client accepted. Running session...");
				Thread t = new Thread(new Session(connection));
				t.start();
			} 
			catch (IOException e) 
			{
				System.err.print(e.getMessage());
			}
		}
		
		
		try																		// Closing the port
		{
			welcomingSocket.close();
		} catch (IOException e)
		{
			//throw new InvalidArgumentException(e.getMessage());
		}
		
		return "Listener terminated.";
	}
	
	@Override
	public String getHelp()
	{
		String text = "Listens on a port number for a command.\n\n"
				    + "Usage: listen <portnumber>\n";
		return text;
	}

}
