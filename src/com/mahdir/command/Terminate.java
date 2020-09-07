package com.mahdir.command;

import java.io.IOException;

import com.mahdir.Command;
import com.mahdir.InvalidArgumentException;

/**
 * This command is used to terminate the listener.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Terminate implements Command
{
	
	@Override
	public String execute(String[] args) throws InvalidArgumentException
	{
		try
		{
			Listen.closeConnection();
		} 
		catch (IOException e)
		{
			throw new InvalidArgumentException(e.getMessage());
		}
		catch(NullPointerException e)
		{
			throw new InvalidArgumentException("Listener is not running to be terminated.");
		}
		return "ok";
	}

	@Override
	public String getHelp()
	{
		String text = "Closes the listener.";
		return text;
	}

}
