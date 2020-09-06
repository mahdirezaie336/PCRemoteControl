package com.mahdir;

import java.util.HashMap;
import java.util.Set;

import com.mahdir.command.*;

/**
 * This class keeps a map of commands and can execute them or call their help method.
 * 
 * @author Mahdi Rezaie
 *
 */
public class CommandFactory
{
	private static HashMap<String, Command> commands;
	
	static
	{
		commands = new HashMap<String, Command>();
		
		commands.put("shutdown", new Shutdown());
		commands.put("help", new Help());
		commands.put("listen", new Listen());
		commands.put("connect", new Connect());
	}
	
	/**
	 * Gets the name of available commands.
	 * @return
	 */
	public static Set<String> getCommandsNames()
	{
		return commands.keySet();
	}
	
	/**
	 * Executes a command by arguments.
	 * @param args The arguments about the command
	 * @return The return message of the command
	 * @throws InvalidArgumentException if arguments are wrong throws this exception.
	 */
	public static String doCommand(String[] args) throws InvalidArgumentException
	{
		String response = null;
		try
		{
			response = commands.get(args[0]).execute(args);
		}
		catch(Exception e)
		{
			if(e instanceof InvalidArgumentException) throw e;
			throw new InvalidArgumentException("Command " + args[0] + " not found.\n"
					+ "Enter 'help' to see available commands.");
		}
		return response;
	}
	
	/**
	 * Returns the help message of a command by some arguments.
	 * @param args The arguments about the command
	 * @return The return help message of command
	 * @throws InvalidArgumentException if arguments are wrong throws this exception.
	 */
	public static String getHalp(String[] args) throws InvalidArgumentException
	{
		String response = null;
		try
		{
			response = commands.get(args[1]).getHelp();
		}
		catch(Exception e)
		{
			if(e instanceof InvalidArgumentException) throw e;
			throw new InvalidArgumentException("Command " + args[1] + " not found.\n"
					+ "Enter 'help' to see available commands.");
		}
		return response;
	}
}
