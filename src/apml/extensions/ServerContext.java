package apml.extensions;

/**
 * Represents a single connection to a server
 *
 * @author Max Rupplin
 */
public class ServerContext
{
	public Integer hash = 0x008808EF;

	public AbstractResourceServer resourceserver;

	public ResourceContext resourcecontext;

	public ProtocolHandler protocolhandler;

	public NetworkContext networkcontext;

	public String protocol;

	public String packet;

	public StringBuffer inputbuffer;

	public String inputstring;

	public String key;

	public String value;

	public String context;

	/**
	 *
	 */
	public ServerContext()
	{

	}

	/**
	 * @param server
	 * @param protocol
	 * @param servercontext
	 * @throws Exception
	 */
	public ServerContext(AbstractResourceServer server, String protocol, ServerContext servercontext) throws Exception
	{
		if (server == null || protocol == null || servercontext == null)
			throw new SecurityException("//bodi/connect");

		this.resourceserver = server;

		this.protocol = new StringBuffer(protocol).toString();

		this.inputbuffer = new StringBuffer(servercontext.networkcontext.inqueue);

		this.inputstring = new StringBuffer(servercontext.networkcontext.inqueue).toString();

		this.networkcontext = servercontext.networkcontext;

		this.resourcecontext = servercontext.resourcecontext;

		this.packet = new StringBuffer(servercontext.networkcontext.inqueue).toString();
	}

	/**
	 * Quick fill for primary loop on Bodiserver; not a total Constructor for BRS at this time. /mr /ok /ss
	 *
	 * @param server
	 * @param networkcontext
	 * @param resourcecontext
	 * @throws Exception
	 */
	public ServerContext(AbstractResourceServer server, NetworkContext networkcontext, ResourceContext resourcecontext) throws Exception
	{
		if (server == null || networkcontext == null /* || resourcecontext==null */)
			throw new SecurityException("//bodi/connect");

		this.resourceserver = server;

		this.networkcontext = networkcontext;

		this.resourcecontext = resourcecontext;

		this.packet = networkcontext.inqueue.toString();

		this.inputstring = networkcontext.inqueue.toString();
	}

	/**
	 * @param resourceserver
	 * @param protocol
	 * @param input
	 * @param networkcontext
	 * @param resourcecontext
	 * @throws Exception
	 */
	public ServerContext(AbstractResourceServer resourceserver, String protocol, String input, NetworkContext networkcontext, ResourceContext resourcecontext) throws Exception
	{
		if (resourceserver == null || protocol == null || input == null || networkcontext == null || resourcecontext == null)
			throw new SecurityException("//bodi/connect");

		this.protocol = protocol;

		this.inputstring = input;

		this.inputbuffer = networkcontext.inqueue;

		this.resourceserver = resourceserver;

		this.networkcontext = networkcontext;

		this.resourcecontext = resourcecontext;

		this.resourcecontext.operation = protocol;

		this.packet = new StringBuffer(networkcontext.inqueue).toString();
	}

	/**
	 * @param parameterization
	 * @return
	 */
	public String getcontext(ServerContext parameterization)
	{
		return null;
	}


}
