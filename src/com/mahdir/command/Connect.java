package com.mahdir.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import com.mahdir.Command;
import com.mahdir.InvalidArgumentException;

/**
 * This class is the client-side interpreter. It get the command from user and sends 
 * the command as String to the client and get the responses back and prints them.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Connect implements Command
{
	private Socket connection;
	
	/**
	 * The connect command implementation
	 */
	@Override
	public String execute(String[] args) throws InvalidArgumentException
	{
		try																					// Checking wrong arguments
		{
			int port = Integer.parseInt(args[2]);
			connection = new Socket(args[1], port);											// Trying to connect
		}
		catch(NumberFormatException e)
		{
			throw new InvalidArgumentException("Wrong port " + args[2]);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new InvalidArgumentException("Enter a correct host and port number.");
		}
		catch(IOException e)
		{
			throw new InvalidArgumentException(e.getMessage());
		}
		
		try(																				// Sending commands
				Scanner scanner = new Scanner(System.in);
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				)
		{
			System.out.println("Enter 'terminate' to terminate connection.");
			while(true)																		// Client-side loop
			{
				System.out.print(args[1] + ":" + args[2] + "> ");
				String command = scanner.nextLine();
				if(command.equals("terminate"))
					break;
				dos.writeUTF(command);
				String response = dis.readUTF();
				System.out.println(response);
			}
		} 
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		
		return "Connection terminated.";
	}

	@Override
	public String getHelp()
	{
		String text = "Connects to an mcat server.\n"
					+ "Usage: connect <host> <port>\n"
					+ "to terminate connection type 'terminate'.\n";
		return text;
	}

}
