package com.mahdir.command;

import java.io.IOException;

import com.mahdir.Command;
import com.mahdir.InvalidArgumentException;
/**
 * This command shuts the server computer down whether it has a windows OS or linux.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Shutdown implements Command 
{
	/**
	 * Tries to shutdown the server.
	 */
	@Override
	public String execute(String[] args) throws InvalidArgumentException
	{
		try
		{
			Runtime.getRuntime().exec(args);
		} 
		catch (IOException e)
		{
			throw new InvalidArgumentException(e.getMessage());
		}
		return "ok";
	}
	
	/**
	 * 
	 */
	@Override
	public String getHelp()
	{
		String text = "Shuts down the server pc.\n";
		return text;
	}

}
