package com.mahdir.command;

import com.mahdir.Command;

public class Shutdown implements Command 
{

	@Override
	public String execute(String[] args) 
	{
		
		return "ok";
	}

}
