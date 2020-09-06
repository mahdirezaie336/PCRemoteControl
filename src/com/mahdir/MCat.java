package com.mahdir;

import java.util.Scanner;

public class MCat
{
	/**
	 * App driver
	 * @param args
	 */
	public static void main(String[] args)
	{
		boolean quit = false;
		
		if(args.length == 0)											// Command Interpreter
		{
			Scanner scanner = new Scanner(System.in);
			
			while(!quit)
			{
				System.out.print("mcat> ");
				args = scanner.nextLine().split(" ");
				
				if( args.length == 0 ) continue;
				if( args[0].toLowerCase().equals("quit") ) continue;
				
				doCommand(args);
			}
			
			scanner.close();
		}
		else
			doCommand(args);											// Command at once
		
	}
	
	/**
	 * Sends a request to the command factory and prints its response message.
	 * @param args The request arguments
	 */
	private static void doCommand(String[] args)
	{
		try
		{
			String response = CommandFactory.doCommand(args);
			System.out.println(response);
		} catch (InvalidArgumentException e)
		{
			System.err.println(e.getMessage());
		}
	}

}
