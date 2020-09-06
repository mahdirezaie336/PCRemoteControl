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
		if(Listen.terminate)
			Listen.terminate = true;
		return "ok";
	}

	@Override
	public String getHelp()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
