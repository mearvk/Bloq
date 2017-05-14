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

import java.util.ArrayList;

/**
 *
 * [^/@jrbodo] New trade era of Service, Protocol, Method [first touch /mr /ok /ss]
 * 
 * @author Max Rupplin
 */
public abstract class Basicserver extends Thread
{
    public String host = "localhost";
    
    public InetAddress address;
    
    public Integer port;
    
    public ServerSocket serversocket;    
    
    public boolean doread;
    
    public boolean dowrite;
    
    public boolean isdonereading;
    
    public boolean isdonewriting;        
    
    public ArrayList<Connection> connections = new ArrayList<Connection>();
    
    public Inputqueue inputqueue = new Inputqueue();
    
    public Basicserver(String host, Integer port)
    {
        this.host = host;
        
        this.port = port;        
        
        this.setName("Basicserverthread");
        
        try
        {
            this.address = InetAddress.getByName(host);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
            return;
        }        
        
        try
        {
            this.serversocket = new ServerSocket(this.port, 4096, this.address);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return;
        }
        finally
        {
            System.out.println("Serversocket created on port "+this.port);
        }           
    }
    
    public Basicserver(Integer port)
    {
        this.port = port;      
        
        this.setName("Basicserverthread");
        
        try
        {
            this.address = InetAddress.getByName(host);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
            return;
        }        
        
        try
        {
            this.serversocket = new ServerSocket(this.port, 4096, this.address);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return;
        }
        finally
        {
            System.out.println("Serversocket created on port "+this.port);
        }                        
    }    
    
    @Override
    public void run()
    {
        while(true)
        {                    
            Connection connection;
            
            connection = new Connection(this);
            
            connection.start();
            
            try
            {
                connection.socket = this.serversocket.accept();                                
            }
            catch(Exception e)
            {
                e.printStackTrace();

                return;
            }
            finally
            {
                System.out.println("> Remote connection established...");
            }

            try
            {
                connection.inputstream = connection.socket.getInputStream();

                connection.reader = new BufferedReader(new InputStreamReader(connection.inputstream));            
            }
            catch(Exception e)
            {
                e.printStackTrace();

                return;
            }
            finally
            {
                System.out.println(">   Server reader established...");
            }

            try
            {
                connection.outputstream = connection.socket.getOutputStream();

                connection.writer = new BufferedWriter(new OutputStreamWriter(connection.outputstream));            
            }
            catch(Exception e)
            {
                e.printStackTrace();

                return;
            }   
            finally
            {
                System.out.println(">   Server writer established...");
            }

            try
            {                        
                connection.thread = new Listenerthread(connection);

                connection.thread.start();                
            }
            catch(Exception e)
            {
                e.printStackTrace();

                return;
            }
            finally
            {
                System.out.println(">   Socket I/O thread established...");
            }
            
            connections.add(connection);
        }
    }
    
    public abstract void write(byte[] bytes);
    
    public abstract byte[] read();   
}