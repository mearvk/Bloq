/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.system.bodi.remote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * [^/@jrbodo] New trade era of Service, Protocol, Method [first touch /mr /ok /ss]
 * 
 * @author Max Rupplin
 */
public abstract class Basicserver implements Runnable
{
    public String host;
    
    public InetAddress address;
    
    public Integer port;
    
    public ServerSocket serversocket;
    
    public Socket socket;
    
    public InputStream inputstream;
    
    public OutputStream outputstream;
    
    public BufferedReader reader;
    
    public BufferedWriter writer;
    
    public boolean doread;
    
    public boolean dowrite;
    
    public boolean isdonereading;
    
    public boolean isdonewriting;
    
    public StringBuffer inqueue;
    
    public StringBuffer outqueue;
    
    ListenerThread thread;
    
    public Basicserver(String host, Integer port)
    {
        this.host = host;
        
        this.port = port;
        
        try
        {
            this.address = InetAddress.getByName(host);
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        
        this.thread = new ListenerThread(this);
    }
    
    public Basicserver(Integer port)
    {
        this.port = port;
        
        this.thread = new ListenerThread(this);
        
        try
        {
            this.address = InetAddress.getByName(host);
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }        
    }
    
    public void run()
    {
        try
        {
            this.serversocket = new ServerSocket(this.port, 0, null);
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("Serversocket created on port "+this.port);
        }
        
        try
        {
            socket = this.serversocket.accept();
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("Serversocket connection established...");
        }
        
        try
        {
            this.inputstream = socket.getInputStream();
            
            this.reader = new BufferedReader(new InputStreamReader(this.inputstream));            
        }
        catch(Exception e)
        {
            
        }
        finally
        {
            System.out.println("Reader established...");
        }
        
        try
        {
            this.outputstream = socket.getOutputStream();
            
            this.writer = new BufferedWriter(new OutputStreamWriter(this.outputstream));            
        }
        catch(Exception e)
        {
            
        }   
        finally
        {
            System.out.println("Writer established...");
        }
        
        try
        {                        
            thread = new ListenerThread(this);
            
            thread.start();
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("Listener thread started...");
        }
    }
    
    public abstract void write(byte[] bytes);
    
    public abstract byte[] read();   
}

class ListenerThread extends Thread
{
    Basicserver server;
    
    public ListenerThread(Basicserver server)
    {
        this.server = server;
    }
    
    @Override
    public void run()
    {
        try
        {
            synchronized(this.server.inputstream)
            {
                StringBuffer inputbuffer = new StringBuffer(); 
                
                while( (inputbuffer.append(this.server.reader.readLine())) == null)
                {
                    Thread.currentThread().sleep(1000);
                }
                
                while( (inputbuffer.append(this.server.reader.readLine())) == null)
                {
                    this.server.isdonereading = false;
                }
                
                this.server.isdonereading = true;
                
                if(inputbuffer.toString().length()>0)
                {
                    this.server.inqueue.append(inputbuffer);
                    
                    this.server.doread = true;
                }
            }
        }
        catch(Exception e)
        {
            //
        }
        finally
        {
            System.out.println("Listener thread running...");
        }
    }
}