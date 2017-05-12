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
public abstract class Basicserver implements Runnable
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
    
    public Inputqueue inputqueue;
    
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
    
    public void run()
    {
        while(true)
        {                    
            Connection connection = new Connection(this);
            
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
                System.out.println("Serversocket connection established...");
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
                System.out.println("Server reader established...");
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
                System.out.println("Server writer established...");
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
                System.out.println("Basicserver listener thread started...");
            }
            
            connections.add(connection);
        }
    }
    
    public abstract void write(byte[] bytes);
    
    public abstract byte[] read();   
}

class Listenerthread extends Thread
{
    public Connection connection;
    
    public Boolean running = true;
    
    public Basicserver server;
    
    public Inputlistenerthread inputlistenerthrread;
    
    public Outputlistenerthread outputlistenerthrread;
    
    public Listenerthread(Connection connection)
    {
        this.connection = connection;
                
        /*---------------------------------------------------------------------*/
        
        this.setName("Listenerthread");
        
        /*---------------------------------------------------------------------*/
        
        this.inputlistenerthrread = new Inputlistenerthread(this);
        
        this.inputlistenerthrread.start();        
                
        /*---------------------------------------------------------------------*/
        
        this.outputlistenerthrread = new Outputlistenerthread(this);                
        
        this.outputlistenerthrread.start();
    }
    
    @Override
    public void run()
    {
        System.out.println("Basicserver listener thread running...");
        
        while(running)
        {
            try
            {
                if(this.inputlistenerthrread.hasreadready)
                {
                    this.inputlistenerthrread.checkinputqueue();
                    
                    this.server.isdonereading = true;
                }

                if(this.outputlistenerthrread.haswriteready)
                {
                    this.outputlistenerthrread.checkoutputqueue();
                    
                    this.server.isdonewriting = true;
                }
                
                Thread.currentThread().sleep(400); //sleep 400 ms
            }
            catch(InterruptedException ie)
            {
                System.out.println("Basicserver listener thread interrupted; exiting...");
                
                running = false;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                //System.gc();
            }
        }
        
        System.out.println("Basicserver listener thread exiting...");
    }       
}

class Inputlistenerthread extends Thread
{
    public Boolean hasreadready = false;
    
    public Boolean running = true;
    
    public Listenerthread parent;
    
    public Inputlistenerthread(Listenerthread parent)
    {
        this.parent = parent;
        
        this.setName("Inputlistenerthread");                
    }
    
    public Boolean checkinputqueue()
    {
        StringBuffer inputbuffer = new StringBuffer();
        
        try
        {
            synchronized(this.parent.connection.inqueue)
            {                           
                String line = null;
                
                while( (line = this.parent.connection.reader.readLine()) != null) 
                {
                    inputbuffer.append(line);
                    
                    this.parent.server.isdonereading = false;
                }

                if(inputbuffer.toString().length()>0) 
                {
                    this.parent.connection.inqueue.append(inputbuffer);

                    this.parent.server.inputqueue.add(this.parent.connection);
                }                                
                
                this.parent.connection.inqueue.delete(0, this.parent.connection.inqueue.length());
                                                
                this.parent.connection.inqueue.notifyAll();                                    
                
                this.parent.server.isdonereading = true;
            }             
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            inputbuffer = null;
        }
           
        return true;
    }  
    
    @Override
    public void run()
    {
        System.out.println("Inputlistenerthread running...");
        
        try
        {
            while(running)
            {                
                try
                {
                    //if(this.parent.server.reader.lines().count()>0 || true)
                    if(true)
                    {
                        this.hasreadready = true;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Thread.currentThread().sleep(250);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

class Outputlistenerthread extends Thread
{
    public Boolean haswriteready = false;
    
    public Boolean running = true;
    
    public Listenerthread parent;
    
    public Outputlistenerthread(Listenerthread parent)
    {
        this.parent = parent;
        
        this.setName("Outputlistenerthread");                
    }
    
    public Boolean checkoutputqueue()
    {
        StringBuffer outputbuffer = this.parent.connection.outqueue;
        
        try
        {            
            if(this.haswriteready == false) return true;
            
            synchronized(this.parent.connection.outqueue)
            {                              
                /*-------------------------------------------------------------*/
                
                this.parent.connection.isdonewriting = false;
                
                this.parent.connection.writer.write(outputbuffer.toString(), 0, outputbuffer.toString().length());         
                
                this.parent.connection.outqueue.delete(0, this.parent.connection.outqueue.length());
                
                this.parent.connection.isdonewriting = true;
                
                /*-------------------------------------------------------------*/
                
                this.parent.connection.outqueue.notifyAll();
            }                    
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }      
        finally
        {
            outputbuffer = null;
        }
        
        return true;
    }    
    
    @Override
    public void run()
    {
        System.out.println("Outputlistenerthread running...");
        
        try
        {
            while(running)
            {
                try
                {
                    if(this.parent.connection.outqueue!=null && this.parent.connection.outqueue.length()>0)
                    {
                        this.haswriteready = true;
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Thread.currentThread().sleep(250);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
}

class Connection
{
    public Connection(Basicserver server)
    {
        this.server = server;
    }
    
    public Basicserver server;
    
    public Socket socket;
    
    public InputStream inputstream;
    
    public OutputStream outputstream;
    
    public StringBuffer inqueue;
    
    public StringBuffer outqueue;
    
    public BufferedReader reader;
    
    public BufferedWriter writer;
    
    public Listenerthread thread;
    
    public Boolean isdonewriting;
    
    public Boolean isdonereading;
    
    public Boolean haswriteready;
    
    public Boolean hasreadready;
    
    public Boolean inputqueueisready()
    {
        return this.inqueue!=null && this.inqueue.length()>0;
    }
}