package apml.system.bodi.remote;

import java.net.ServerSocket;

import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
public abstract class Protocolserver extends Basicserver implements Runnable
{
    public Socket socket;
    
    public ServerSocket serversocket;
    
    public Protocol protocol;
    
    public Boolean running;

    public Protocolserver(String host, Integer port)
    {
        super(host, port);
    }
    
    public Protocolserver(Integer port)
    {
        super(port);
    }
    
    @Override
    public void run()
    {
        super.run(); 
    }    
    
    protected abstract Object parseprotocol(String protocol , StringBuffer buffer, Bodiprotocolhandler server) throws Exception;
}