package apml.extensions;

/**
 * @author Max Rupplin
 */
public class HttpServer extends AbstractResourceServer
{
	/**
	 * @param host
	 * @param port
	 */
	public HttpServer(String host, Integer port)
	{
		super(host, port);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HttpServer server = new HttpServer("localhost", 8091);

		server.start();

		server.attend();
	}

	/**
	 *
	 */
	@Override
	public void attend()
	{
		super.attend();
	}

	/**
	 * Step 1/3 : Process the protocol token
	 *
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processprotocol(ServerContext connectioncontext) throws Exception
	{
		//connectioncontext.protocolhandler.processprotocol(connectioncontext);

		String protocoltoken = connectioncontext.inputstring;

		switch (protocoltoken)
		{
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

			case "OPTIONS":
				break;

			case "CONNECT":
				break;

			case "PATCH":
				break;

			default:
				throw new Exception("//bodi/connect");
		}
	}

	/**
	 * Step 2/3 : Process the request for resource
	 *
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processrequest(ServerContext connectioncontext) throws Exception
	{
		//connectioncontext.resourcecontext.processrequest(connectioncontext);

		String protocoltoken = connectioncontext.inputstring;

		switch (protocoltoken)
		{
			case "GET":
				this.processGET(connectioncontext);
				break;

			case "HEAD":
				this.processHEAD(connectioncontext);
				break;

			case "POST":
				this.processPOST(connectioncontext);
				break;

			case "PUT":
				this.processPUT(connectioncontext);
				break;

			case "DELETE":
				this.processDELETE(connectioncontext);
				break;

			case "TRACE":
				this.processTRACE(connectioncontext);
				break;

			case "OPTIONS":
				this.processOPTIONS(connectioncontext);
				break;

			case "CONNECT":
				this.processCONNECT(connectioncontext);
				break;

			case "PATCH":
				this.processPATCH(connectioncontext);
				break;

			default:
				break;
		}
	}

	/**
	 * Step 3/3 : Process the response for resource
	 *
	 * @param connectioncontext
	 * @throws Exception
	 */
	@Override
	public void processsesponse(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.networkcontext.processresponse(connectioncontext);
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

	protected void processGET(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "GET>Ok..for now";
	}

	protected void processHEAD(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "HEAD>Ok..for now";
	}

	protected void processPOST(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "POST>Ok..for now";
	}

	protected void processPUT(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "PUT>Ok..for now";
	}

	protected void processDELETE(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "DELETE>Ok..for now";
	}

	protected void processTRACE(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "TRACE>Ok..for now";
	}

	protected void processOPTIONS(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "OPTIONS>Ok..for now";
	}

	protected void processCONNECT(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "CONNECT>Ok..for now";
	}

	protected void processPATCH(ServerContext connectioncontext) throws Exception
	{
		connectioncontext.resourcecontext.value = "PATCH>Ok..for now";
	}
}
