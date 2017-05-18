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
    
    public Long ttl = 60*1000*8l;
    
    public String result;
    
    public Serializable object;
    
    public String host;
    
    public Bodi bodi;
    
    public Boolean islive = true;
    
    public String op = "";
    
    public Boolean verified = true;
    
    public String error = "";
    
    public Long day;
    
    public String context;
    
    public String key;
    
    public Bodiconnection()
    {
        this.sessionid = this.hashCode();
        
        this.day = System.currentTimeMillis();
    }
    
    public Bodiconnection(Bodiremoteserver server, Connection connection)
    {
        this.server = server;
        
        this.connection = connection;
        
        this.day = System.currentTimeMillis();
    }
    
    private Boolean checkconnection(Bodiconnection bodiconnection)            
    {
        if(bodiconnection==null)    return false;
        
        if(bodiconnection.ttl <= 0) return false;
        
        return true;
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
        Long now = System.currentTimeMillis();        
        
        Long day = this.day;
        
        Long ttl = this.ttl;
                
        //
        
        return ttl-(now-day);
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
        return this.result = "ok";
    }    
       
    public Boolean cycle(Bodiserverparameter parameterization) //in fact reach into a bodi reference and see about getting an object etc.
    {                
        this.object = this.getrequestedobject(context, key);
        
        return this.object == null;
    }
    
    @Override
    public String toString()
    {
        switch(this.op)
        {
            case "//close": 
                return "//close //sessionid="+sessionid+" //result=TBI //bodicontext=TBI";
                            
            case "//handshake": 
                return "//handshake //sessionid="+sessionid+" //result="+result;
            
            case "//open":
                return "//open //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            case "//pull": 
                return "//pull //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            case "//put": 
                return "//put //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            case "//trade": 
                return "//trade //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            default: return "//other //result=UNKNOWN";
        }
    }
}
