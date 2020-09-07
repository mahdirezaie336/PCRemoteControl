package com.mahdir;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class is the opened session between client and server. It keeps the connection
 * and tries to execute commands coming from connection input stream. Then writes the
 * response message back to the client.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Session implements Runnable
{
	private Socket connection;
	
	/**
	 * Sets the connection between client and server.
	 * @param connection The client connection
	 */
	public Session(Socket connection) 
	{
		this.connection = connection;
	}
	
	/**
	 * Begins the request and response loop.
	 */
	@Override
	public void run() 
	{
		try(
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				)
		{
			while(true)																// The request and response loop
			{
				String[] args = dis.readUTF().split(" ");							// Getting command
				String response;
				try																	// Executing command
				{
					System.out.println("Trying to execute command '" + args[0] +"'");
					response = CommandFactory.doCommand(args);
				} 
				catch (InvalidArgumentException e)
				{
					dos.writeUTF(e.getMessage());
					continue;
				}
				dos.writeUTF(response);												// Sending back command response
			}
		} 
		catch (IOException e) 
		{
			System.err.println("Session terminated: " + e.getMessage() + "\n");
		}
	}

}
