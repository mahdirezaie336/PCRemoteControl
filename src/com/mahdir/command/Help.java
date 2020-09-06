package com.mahdir.command;

import com.mahdir.Command;
import com.mahdir.CommandFactory;
import com.mahdir.InvalidArgumentException;

/**
 * This class is the help command which shows the help for each command or
 * if the user enters no arguments, show the available commands list.
 * 
 * @author Mahdi Rezaie
 *
 */
public class Help implements Command 
{

	@Override
	public String execute(String[] args) throws InvalidArgumentException 
	{
		if( args.length == 1 )
			return getHelp();
		return CommandFactory.getHalp(args);
	}

	@Override
	public String getHelp()
	{
		String response = "Available Commands:\n\n";
		
		for(String name : CommandFactory.getCommandsNames())
			response += name + "\n";
		
		return response;
	}

}
