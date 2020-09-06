package com.mahdir;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
		try(
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				)
		{
			while(true)
			{
				String command = dis.readUTF();
				
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
