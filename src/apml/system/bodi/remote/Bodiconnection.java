package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.io.Serializable;

/**
 *
 * @author Max Rupplin
 */
public class Bodiconnection
{
    public Bodiremoteserver server;
            
    public Connection connection;            
    
    public Integer sessionid;
    
    public Long ttl;
    
    public String result;
    
    public Serializable object;
    
    public String host;
    
    public Bodi bodi;
    
    public Boolean islive = true;
    
    public String op = "";
    
    
    public Bodiconnection()
    {
        this.sessionid = this.hashCode();
    }
    
    public Bodiconnection(Bodiremoteserver server, Connection connection)
    {
        this.server = server;
        
        this.connection = connection;
    }
    
    private Boolean checkconnection(Bodiconnection bodiconnection)            
    {
        if(bodiconnection==null)    return false;
        
        if(bodiconnection.ttl <= 0) return false;
        
        return true;
    }
    
    /**
     * New handshakes should return new Bodiconnection instances with unique sessionid values
     * 
     * Existing Bodiconnections should return updated TTLs possibly more.
     * 
     * @param buffer
     * @return 
     */
    public Bodiconnection handshake(StringBuffer buffer) throws Exception
    {
        Bodiconnection bodiconnection = new Bodiconnection();  
        
        bodiconnection.op = "handshake";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection close(StringBuffer buffer) throws Exception
    {               
        Bodiconnection bodiconnection = this.server.checkforexistingconnection(buffer);                            
        
        bodiconnection.op = "close";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection put(StringBuffer buffer) throws Exception
    {
        Bodiconnection bodiconnection = this.server.checkforexistingconnection(buffer);                            
        
        bodiconnection.op = "put";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection pull(StringBuffer buffer) throws Exception
    {
        Bodiconnection bodiconnection = this.server.checkforexistingconnection(buffer);                            
        
        bodiconnection.op = "pull";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection open(StringBuffer buffer) throws Exception
    {        
        Bodiconnection bodiconnection = this.server.checkforexistingconnection(buffer);                            
        
        bodiconnection.op = "open";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection trade(StringBuffer buffer) throws Exception
    {
        Bodiconnection bodiconnection = this.server.checkforexistingconnection(buffer);                            
        
        bodiconnection.op = "trade";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    protected Integer getsessionid() 
    {
        if(this.sessionid==null || this.sessionid==0)
        {
            return this.hashCode();
        }
        
        return this.sessionid;
    }
    
    protected Long gettimetolive()
    {
        return 60*1000*8l; //8 minutes of default connect
    }
    
    protected Serializable getrequestedobject(String context, String key)
    {
        SerializedCarrier bodicarrier = new SerializedCarrier();
        
        
        try
        {   
            Object object = Bodi.context(context).pull(key);
        
            Class _class = Bodi.context(context).getclass(key);
            
            bodicarrier.object = object;
        
            bodicarrier._class = _class;
        }
        catch(Exception e)
        {
            //
        }                        
        
        return bodicarrier;
    }
    
    protected String getresult()
    {
        return "ok";
    }
}

class SerializedCarrier implements Serializable
{
    public Object object = null;
    
    public Class _class = null;
    
    public SerializedCarrier()
    {
        
    }
    
    public SerializedCarrier(Class _class, Object object)
    {
        this._class = _class;
        
        this.object = object;
    }
}
