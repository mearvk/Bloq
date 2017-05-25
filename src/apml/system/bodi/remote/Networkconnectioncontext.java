package apml.system.bodi.remote;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.Socket;

/**
 *
 * @author Max Rupplin
 */
class Networkconnectioncontext
{
    public Networkconnectioncontext()
    {
        
    }
        
    public Networkconnectioncontext(Basicserver server)
    {
        if(server==null) throw new SecurityException("//bodi/connect");
        
        this.server = server;
    }    
    
    public Basicserver server;
    
    public volatile Socket socket;
    
    public InputStream inputstream;
    
    public OutputStream outputstream;
    
    public StringBuffer inqueue = new StringBuffer();
    
    public StringBuffer outqueue = new StringBuffer();
    
    public String remoteaddress = null;
    
    public BufferedReader reader = null;
    
    public BufferedWriter writer = null;
    
    public Listenerthread thread;
    
    public Boolean isdonewriting;
    
    public Boolean isdonereading;
    
    public Boolean haswriteready;
    
    public Boolean hasreadready;
    
    public Integer sessionid;
       
    public void appendline(String line) 
    {
        this.inqueue.append(line);
    }
    
    public Boolean inputqueueisready()
    {
        return this.inqueue!=null && this.inqueue.length()>0;
    }   
    
    public Boolean processresponse(Bodiservercontext connectioncontext)
    {
        //set output buffer, flag, and bodi object reference into the output queue
        
        connectioncontext.networkcontext.outqueue.append(connectioncontext.bodicontext.toString());
                                                                        
        connectioncontext.networkcontext.haswriteready = true;
                        
        connectioncontext.networkcontext.thread.outputlistenerthread.haswriteready = true;                                                
                            
        //connectioncontext.networkcontext.inqueue = connectioncontext.networkcontext.inqueue.delete(0, connectioncontext.inputstring.length());      
        
        return true;
    }
    
    public Boolean close(Bodiservercontext connectioncontext) throws Exception
    {
        this.socket.close();
        
        return false;
    }
    
    public Boolean issocketclosed()
    {
        try
        {
            this.writer.write("");
        }
        catch(Exception e)
        {
            return true;
        }
        
        return false;
    }    
    
    public Boolean issocketconnected()
    {
        try
        {
            this.writer.write("");
        }
        catch(Exception e)
        {
            return false;
        }
        
        return true;
    }      
}