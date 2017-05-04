/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system.bodi.remote;

import java.net.ServerSocket;

import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
public class Protocolserver extends Basicserver
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
    public void write(byte[] bytes)
    {
        try
        {
            synchronized(this.outqueue)
            {                
                /*-------------------------------------------------------------*/
                
                this.writer.write(this.outqueue.toString(), 0, this.outqueue.toString().length());
                                            
                this.writer.flush();
                                
                /*-------------------------------------------------------------*/
                
                this.outqueue.delete(0, this.outqueue.length());                                
            }
        }
        catch(Exception e)
        {
            //
        }
        finally
        {
            //
        }
    }

    @Override
    public byte[] read()
    {
        if(!this.doread) return null;
            
        return this.inqueue.toString().getBytes();
    }
}

class ProtocolListenerThread extends Thread
{
    Protocolserver server;
    
    public ProtocolListenerThread(Protocolserver server)
    {
        this.server = server;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                if(this.server.doread)
                {
                    
                }                                
            }
            catch(Exception e)
            {
                
            }
        }
    }
}