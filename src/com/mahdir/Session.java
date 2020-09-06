package com.mahdir;

import java.net.Socket;

public class Session implements Runnable
{
	private Socket connection;
	
	public Session(Socket connection) 
	{
		this.connection = connection;
	}

	@Override
	public void run() 
	{
		
	}

}
