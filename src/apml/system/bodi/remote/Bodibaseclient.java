package apml.system.bodi.remote;

import apml.interfaces.BasicSystemElement;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
public class Bodibaseclient implements Runnable, BasicSystemElement
{
    public Socket socket;           
    
    public BufferedReader keyboardreader;
    
    public BufferedReader reader;
    
    public BufferedWriter writer;
    
    public StringBuffer keyboardinputbuffer = new StringBuffer();
    
    public StringBuffer inputbuffer = new StringBuffer();
    
    public StringBuffer outputbuffer = new StringBuffer();
    
    public Boolean running = true;
    
    public Boolean iswriting = false;
    
    public Boolean isreading = false;
    
    public Boolean iskeyboardreadready = false;
    
    public Boolean isreadready = false;
    
    public Boolean iswriteready = false;
    
    public Baseservicethread thread;
    
    public Object readlock = new Object();
    
    public Object writelock = new Object();
    
    /**
     * 
     * @param args 
     */
    public static void main(String...args)
    {
        new Bodibaseclient("localhost", 8888).run();
    }
    
    /**
     * 
     * @param host
     * @param port 
     */
    public Bodibaseclient(String host, Integer port)
    {        
        if(host==null || port==null) throw new SecurityException();
        
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
        
        /*---------------------------------------------------------------------*/
        
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
        
        /*---------------------------------------------------------------------*/
        
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
            System.out.println("New network inputstream connected.");
        }
        
        /*---------------------------------------------------------------------*/
        
        try
        {            
            this.keyboardreader = new BufferedReader(new InputStreamReader(System.in));
        }
        catch(Exception e)
        {
            return;
        }
        finally
        {
            System.out.println("New keyboard inputstream connected.");
        }        

        /*---------------------------------------------------------------------*/
        
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
            System.out.println("New network outputstream connected.");
        }        
    }
    
    /**
     * 
     */
    @Override
    public void run()
    {
        while(running)
        {                
            try
            {
                this.checkkeyboardin();

                this.checknetworkin();

                this.checknetworkout();           
                
                this.sleepmillis(1000L);
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
            }
     
        }
    }
    
    /**
     * 
     * @param millis
     * @throws Exception 
     */
    public void sleepmillis(Long millis) throws Exception
    {
        Thread.currentThread().sleep(millis); 
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void checknetworkin() throws Exception
    {
            /*------------------- do full read on inputstream ----------------------*/
            try
            {                                
                //check if inputbuffer is ready
                if(this.reader.ready() || this.socket.getInputStream().available()>0 )
                {
                    this.isreading = true;

                    String line=this.reader.readLine();

                    this.inputbuffer.append(line);
                            
                    System.out.println(line);
                        
                    this.isreading = false;
                }
            }
            catch(Exception e)
            {
               //e.printStackTrace();
            }        
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void checkkeyboardin() throws Exception
    {
        if(this.iskeyboardreadready)
        {
            this.isreading = true;

            String line=this.keyboardreader.readLine();
                        
            this.outputbuffer.append(line);
                        
            //this.keyboardinputbuffer.append(line);                   

            this.isreading = false;
                        
            this.iskeyboardreadready = false;
        }
    }

    /**
     * 
     * @throws Exception 
     */
    public void checknetworkout() throws Exception
    {
        if(this.outputbuffer.length()>0)
        {
            this.iswriting = true;

            this.write(this.outputbuffer);                                   
                        
            this.outputbuffer.delete(0, this.outputbuffer.length());
            
            this.iswriting = false;
        }     
    }    
    
    public void write(StringBuffer buffer) throws Exception
    {
        this.writer.write(buffer.toString(), 0, buffer.toString().length());
        
        this.writer.flush();
    }
    
    /**
     * 
     * @return 
     */
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

/**
 * 
 * @author Max Rupplin
 */
class Baseservicethread extends Thread
{
    public Bodibaseclient client; 
    
    public Baseservicethread(Bodibaseclient client)
    {
        this.client = client;
    }        
    
    /**
     * 
     */
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
                    
                    if(this.client.reader.ready())
                    {
                        this.client.isreadready = true;
                    }
                    
                    if(this.client.keyboardreader.ready())
                    {
                        this.client.iskeyboardreadready = true;
                    }
                    
                    else
                    {
                        this.client.isreadready = false;
                        
                        this.client.iskeyboardreadready = false;
                    }
                }
                catch(Exception e)
                {
                    //e.printStackTrace();
                }   
                
                Thread.sleep(1500l);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
