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
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection close(StringBuffer buffer)
    {               
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection put(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection pull(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult();    
    
        return bodiconnection;
    }
    
    public Bodiconnection open(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection trade(StringBuffer buffer)
    {
        Bodiconnection bodiconnection = new Bodiconnection();
        
        bodiconnection.sessionid = this.getsessionid();
        
        bodiconnection.ttl = this.gettimetolive();
        
        bodiconnection.object = this.getrequestedobject("context", "key");
        
        bodiconnection.result = this.getresult(); 
        
        return bodiconnection;
    }
    
    protected Integer getsessionid() 
    {
        return this.hashCode(); //ok for now
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
