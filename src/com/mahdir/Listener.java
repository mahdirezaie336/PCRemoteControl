package com.mahdir;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener 
{
	public static boolean terminate = false;
	private ServerSocket welcomingSocket;
	
	public Listener(int port) throws IOException 
	{
		welcomingSocket = new ServerSocket(port);
	}
	
	public void run()
	{
		while(!terminate)
		{
			Socket connection;
			try 
			{
				connection = welcomingSocket.accept();
				Thread t = new Thread(new Session(connection));
				t.start();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

}
