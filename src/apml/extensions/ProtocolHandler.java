package apml.extensions;

/**
 *
 * @author Max Rupplin
 */
public abstract class ProtocolHandler
{
    public abstract void processprotocol(String protocoltoken);
    
    public abstract void processprotocol(ServerContext servercontext);
}
