package apml.examples.echoserver.server;

import apml.extensions.AbstractResourceServer;
import apml.extensions.ServerContext;

/**
 * @author Max Rupplin
 */
public class Echoserver extends AbstractResourceServer
{
	protected final Integer hash = 0x00888fe8;

	public Echoserver(String host, Integer port)
	{
		super(host, port);
	}

	public static void main(String... args)
	{
		Echoserver server = new Echoserver("localhost", 8989);

		server.start();

		server.attend();
	}

	/**
	 * @param servercontext
	 * @return
	 */
	@Override
	public Boolean dovalidateresourcecontext(ServerContext servercontext)
	{
		return true;
	}

	/**
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processprotocol(ServerContext connectioncontext) throws Exception
	{
		return;
	}

	/**
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processrequest(ServerContext connectioncontext) throws Exception
	{
		if (connectioncontext == null)
			throw new SecurityException("//bodi/connect");

		connectioncontext.resourcecontext.value = connectioncontext.inputstring;
	}

	/**
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processsesponse(ServerContext connectioncontext) throws Exception
	{
		if (connectioncontext == null)
			throw new SecurityException("//bodi/connect");

		if (connectioncontext.networkcontext == null)
			throw new SecurityException("//bodi/connect");

		connectioncontext.networkcontext.processresponse(connectioncontext);
	}
}
