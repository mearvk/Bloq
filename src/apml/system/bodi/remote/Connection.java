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
    
    public StringBuffer inqueue = new StringBuffer();
    
    public StringBuffer outqueue = new StringBuffer();
    
    public BufferedReader reader = null;
    
    public BufferedWriter writer = null;
    
    public Listenerthread thread;
    
    public Boolean isdonewriting;
    
    public Boolean isdonereading;
    
    public Boolean haswriteready;
    
    public Boolean hasreadready;
    
    public Integer sessionid;
    
    public Connection()
    {
        this.sessionid = this.hashCode();
    }
    
    public void appendline(String line) 
    {
        this.inqueue.append(line);
    }
    
    public Boolean inputqueueisready()
    {
        return this.inqueue!=null && this.inqueue.length()>0;
    }   
}
