package apml.system.bodi.remote.persistent;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Max Rupplin
 */
public abstract class Protocolserver extends Baseserver implements Runnable
{
	public Socket socket;

	public ServerSocket serversocket;

	public Protocol protocol;

	public Boolean running;

	/**
	 * @param host
	 * @param port
	 */
	public Protocolserver(String host, Integer port)
	{
		super(host, port);

		if (host == null || port == null)
			throw new SecurityException("//bodi/connect");
	}

	/**
	 * @param port
	 */
	public Protocolserver(Integer port)
	{
		super(port);

		if (port == null)
			throw new SecurityException("//bodi/connect");
	}

	/**
	 *
	 */
	@Override
	public void run()
	{
		super.run();
	}

	/**
	 * @param protocol
	 * @param buffer
	 * @param server
	 * @return
	 * @throws Exception
	 */
	protected abstract Object parseprotocol(String protocol, StringBuffer buffer, Bodiprotocolhandler server) throws Exception;
}