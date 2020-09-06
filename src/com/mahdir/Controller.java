package com.mahdir;

import java.util.HashMap;

import com.mahdir.command.Shutdown;

public class Controller 
{
	private HashMap<String, Command> commands;
	
	public Controller()
	{
		commands = new HashMap<String, Command>();
		commands.put("shutdown", new Shutdown());
	}
	
	
}
