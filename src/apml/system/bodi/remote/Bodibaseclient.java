package apml.system.bodi.remote;

import apml.interfaces.BasicSystemElement;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
public abstract class Bodibaseclient implements Runnable, BasicSystemElement
{
    public Socket socket;        
    
    public InputStream inputstream;
    
    public OutputStream outputstream;
    
    public BufferedReader reader;
    
    public BufferedWriter writer;
    
    public StringBuffer inputbuffer;
    
    public StringBuffer outputbuffer;
    
    public Boolean running = false;
    
    public Boolean iswriting = false;
    
    public Boolean isreading = false;
    
    public Boolean isreadready = false;
    
    public Boolean iswriteready = false;
    
    public Baseservicethread thread;
    
    public Object readlock = new Object();
    
    public Object writelock = new Object();
    
    public Bodibaseclient(String host, Integer port)
    {        

        try
        {
            this.thread = new Baseservicethread(this);
            
            this.thread.start();
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("Client polling thread up...");
        }        
        
        try
        {
            socket = new Socket(host, port);
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("New connection established to "+socket.getRemoteSocketAddress());
        }
        
        try
        {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("New inputstream connected.");
        }

        try
        {
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("New outputstream connected.");
        }        
    }
    
    @Override
    public void run()
    {
        while(running)
        {                
            /*------------------- do full write on outputstream ----------------------*/
            try
            {
                
               //check if output is ready     
                if(this.outputbuffer.length()>0)
                {
                    //no reading by parent class until clear with write
                    synchronized(this.readlock)
                    {
                        this.iswriting = true;

                        this.write(this.outputbuffer);

                        this.iswriting = false;
                        
                        this.readlock.notify();
                    }
                }
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
            
            /*------------------- do full read on inputstream ----------------------*/
            try
            {                                
                //check if inputbuffer is ready
                if(this.isreadready)
                {
                    //no writing by parent class until clear with read
                    synchronized(this.writelock)
                    {
                        this.isreading = true;

                        String line = "";

                        while( (line=this.reader.readLine())!=null )
                        {
                            this.inputbuffer.append(line);
                        }
                        
                        this.writelock.notify();

                        this.isreading = false;
                    }
                }
            }
            catch(Exception e)
            {
               //e.printStackTrace();
            }
            
            try
            {
                Thread.currentThread().sleep(150L); //want a better method than freezing main thread here
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
        }
    }
    
    public void write(StringBuffer buffer) throws Exception
    {
        this.writer.write(buffer.toString(), 0, buffer.toString().length());
        
        this.writer.flush();
    }
    
    public StringBuffer read() 
    {
        String line = "";
        
        try
        {
            while((line=this.reader.readLine())!=null)
            {
                this.inputbuffer.append(line);
            }            
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }        
        
        return this.inputbuffer;
    }

    @Override
    public void autostart()
    {
        //
    }

    @Override
    public void init()
    {
        //
    }

    @Override
    public void pause()
    {
        this.running = false;
    }

    @Override
    public void restart()
    {
        this.running = true;
        
        this.init();                
    }

    @Override
    public void start()
    {
        this.running = true;
    }

    @Override
    public void stop()
    {
        this.running = false;
    }

    @Override
    public void resume()
    {
        this.running = true;
        
        this.init();        
    }
}

class Baseservicethread extends Thread
{
    public Bodibaseclient client; 
    
    public Baseservicethread(Bodibaseclient client)
    {
        this.client = client;
    }        
    
    @Override
    public void run()
    {
        try
        {
            while(true)
            {
                try//check if input is ready
                {    
                    /*------------------- do full read on input stream -------------------------*/
                    
                    if(this.client.inputstream.available()>0)
                    {
                        this.client.isreadready = true;
                    }
                    else
                    {
                        this.client.isreadready = false;
                    }
                }
                catch(Exception e)
                {
                    //e.printStackTrace();
                }   
                
                Thread.sleep(250l);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
