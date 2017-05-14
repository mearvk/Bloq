package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.io.Serializable;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiconnection
{
    public Bodiremoteserver server;
            
    public Connection connection;
            
    public ArrayList<String> sessionids = new ArrayList();
    
    public Integer sessionid;
    
    public Long ttl;
    
    public String result;
    
    public Serializable object;
    
    public String host;
    
    public Bodi bodi;
    
    public Boolean islive = true;
    
    public Bodiconnection()
    {
        
    }
    
    public Bodiconnection(Bodiremoteserver server, Connection connection)
    {
        this.server = server;
        
        this.connection = connection;
    }
    
    public Bodiconnection handshake(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection close(StringBuffer buffer)
    {               
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection put(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection pull(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection open(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection trade(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject();
        
        bodiconnection.result = this.getresult(); 
        
        return bodiconnection;
    }
    
    protected Integer getsessionid() 
    {
        return -1;
    }
    
    protected Long gettimetolive()
    {
        return -1l;
    }
    
    protected Integer getrequestedobject()
    {
        return -1;
    }
    
    protected String getresult()
    {
        return "ok";
    }
}
