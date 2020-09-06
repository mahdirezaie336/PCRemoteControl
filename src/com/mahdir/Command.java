package com.mahdir;

/**
 * This interface is implemented by any command class to be executed by command factory.
 * 
 * @author Mahdi Rezaie
 *
 */
public interface Command 
{
	public String execute(String[] args) throws InvalidArgumentException;
	
	public String getHelp();
}
