package apml.system.bodi.remote;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.Socket;

import java.util.ArrayList;

import java.util.stream.Stream;

/**
 *
 * @author Max Rupplin
 */
class Connection extends Thread
{
    public Connection(Basicserver server)
    {
        this.server = server;
    }
    
    public Stream<String> lines1;
    
    public Stream<String> lines2;
    
    public Stream<String> lines3;
    
    public Stream<String> lines4;
    
    public Stream<String> markslines1to8;
    
    public Stream<String> sharonslifeguardline;
    
    public Stream<String> append;
    
    public Basicserver server;
    
    public Socket socket;
    
    public InputStream inputstream;
    
    public OutputStream outputstream;
    
    public StringBuffer inqueue = new StringBuffer();
    
    public StringBuffer outqueue = new StringBuffer();
    
    public BufferedReader reader = null;
    
    public BufferedWriter writer = null;
    
    public Listenerthread thread;
    
    public Boolean isdonewriting;
    
    public Boolean isdonereading;
    
    public Boolean haswriteready;
    
    public Boolean hasreadready;
    
    public Connection()
    {
        this.append = new ArrayList<String>().stream();
    }
    
    public void appendline(String line) 
    {
        this.inqueue.append(line);
    }
    
    public Boolean inputqueueisready()
    {
        return this.inqueue!=null && this.inqueue.length()>0;
    }
    
    @Override
    public void run()
    {
        while(true)
        {           
            try
            {
                //synchronized(this)
                //{                              
                    if(this.append!=null && this.append.count()>0)
                    {
                        this.append.forEach(inqueue::append);    

                        this.server.inputqueue.add(this);                                    

                        this.hasreadready = true;

                        this.isdonereading = true;                      
                    }
                //}
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    Thread.sleep(400l);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            
            //this.notify();
        }
    }
}
