package com.mahdir.command;

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
		if(!Listen.terminate)
			Listen.terminate = true;
		else
			throw new InvalidArgumentException("Listener is not running.");
		return "ok";
	}

	@Override
	public String getHelp()
	{
		String text = "Closes the listener.";
		return text;
	}

}
