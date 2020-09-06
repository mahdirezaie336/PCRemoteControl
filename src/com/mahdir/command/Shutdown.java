package com.mahdir.command;

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
		
		return "ok";
	}
	
	/**
	 * 
	 */
	@Override
	public String getHelp()
	{
		String text = "Shutdowns the server pc.";
		return text;
	}

}
