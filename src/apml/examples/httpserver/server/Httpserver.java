/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.examples.httpserver.server;

import apml.annotations.ApmlListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author oem
 */
public class Httpserver implements Runnable
{
	public boolean running = true;
	public int port = 80;
	public Socket socket;
	public ServerSocket serversocket;

	public Httpserver()
	{

	}

	@ApmlListener(listener = "OPTIONS_CALL_LISTENER")
	public void doOptions()
	{

	}

	@ApmlListener(listener = "EchoServerOnReadListener") //now care to inform all subscribers
	public void read(Socket socket, String line) throws IOException
	{
		BufferedReader reader;

		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		for (line = ""; line != null; line += reader.readLine() + "\n")
		{
		}

		reader.close();

		switch (line)
		{
			case "OPTIONS":
				doOptions();
				break;
			case "GET":
				break;
			case "HEAD":
				break;
			case "POST":
				break;
			case "PUT":
				break;
			case "DELETE":
				break;
			case "TRACE":
				break;
			case "CONNECT":
				break;

			default:
				return;
		}
	}

	@ApmlListener(listener = "EchoServerOnDispatchListener") //now care to inform all subscribers
	public void write(Socket socket, String line) throws IOException
	{
		BufferedWriter writer;

		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		writer.write(line);

		writer.flush();

		writer.close();
	}

	@ApmlListener(listener = "EchoServerOnConnectListener") //now care to inform all subscribers
	public Socket accept(ServerSocket serversocket) throws Exception
	{
		return serversocket.accept();
	}

	public void run()
	{
		while (running)
		{
			try
			{
				Socket socket = accept(new ServerSocket(80));

				new Thread()
				{
					@Override
					public void run()
					{
						String line = "";

						try
						{
							read(socket, line);

							write(socket, line);
						}
						catch (IOException ioe)
						{
							;
						}
					}
				}.start();
			}
			catch (IOException ioe)
			{
				;
			}
			catch (Exception e)
			{
				;
			}
			finally
			{
				System.out.println("Ok.");
			}
		}
	}

	class OPTIONS_CALL_LISTENER implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			//call listeners for OPTIONS HANDLING /etc
		}
	}
}

class EchoServerOnConnectListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//now inform all subscribers actually
	}
}

class EchoServerOnExitListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//now inform all subscribers actually
	}
}

class EchoServerOnReadListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//now inform all subscribers actually
	}
}

class EchoServerOnReceiptListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//now inform all subscribers actually
	}
}

class EchoServerOnDispatchListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//now inform all subscribers actually
	}
}

class SocketObject
{

}
