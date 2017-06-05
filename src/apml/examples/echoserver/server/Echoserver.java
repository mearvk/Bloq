package apml.examples.echoserver.server;

import apml.extensions.AbstractResourceServer;

import apml.extensions.ServerContext;

/**
 *
 * @author Max Rupplin
 */
public class Echoserver extends AbstractResourceServer 
{   
    protected final Integer hash = 0x888fe8;        

    public static void main(String...args)
    {
        Echoserver server = new Echoserver("localhost", 8989);
        
        server.start();
        
        server.attend();
    }
    
    public Echoserver(String host, Integer port)
    {
        super(host, port);
    }

    @Override
    public Boolean dovalidateresourcecontext(ServerContext servercontext)
    {
        return true;
    }

    @Override
    public void processprotocol(ServerContext connectioncontext) throws Exception
    {
        return;
    }

    @Override
    public void processrequest(ServerContext connectioncontext) throws Exception
    {
        if(connectioncontext==null) throw new SecurityException("//bodi/connect");
        
        connectioncontext.resourcecontext.value = connectioncontext.inputstring;                
    }

    @Override
    public void processsesponse(ServerContext connectioncontext) throws Exception
    {
        if(connectioncontext==null) throw new SecurityException("//bodi/connect");
        
        if(connectioncontext.networkcontext==null) throw new SecurityException("//bodi/connect");
        
        connectioncontext.networkcontext.processresponse(connectioncontext);
    }
}
        