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
    public Integer hash = 0x008808ef;
    
    /*-------------------------------------------------------------------------*/
    
    public ArrayList<String> lines = new ArrayList();
    
    public Bodi bodi;
    
    public Bodiremoteserver server;
    
    public Boolean verified = true;       
    
    public Boolean islive = true;
            
    public Connection connection;            
    
    public Integer sessionid;
    
    public Long ttl = 60*1000*5l;
    
    public Long day;       
    
    public Serializable object;
    
    public String host;                
    
    public String result;
    
    public String operation = "";        
    
    public String error = "";        
    
    public String context;
    
    public String key;    
    
    public byte[] value;              
    
    public Bodiconnection()
    {
        this.sessionid = this.hashCode();
        
        this.day = System.currentTimeMillis();
    }
    
    public Bodiconnection(Bodiremoteserver server, Connection connection)
    {                
        if(server==null || connection==null) throw new SecurityException("//bodi/connect/exceptions");
        
        this.connection = connection;
        
        this.day = System.currentTimeMillis();
    }
    
    public Boolean processrequest(Bodiserverconnectioncontext connectioncontext) //in fact reach into a bodi reference and see about getting an object etc.
    {                
        StringBuffer buffer = connectioncontext.inputbuffer;
        
        try
        {              
            switch(buffer.toString())
            {
                case "//handshake": 

                    connectioncontext.bodiconnection.processhandshakerequest(connectioncontext);

                    connectioncontext.bodiconnection.processhandshakeresponse(connectioncontext);                                 

                    break;

                case "//close": //do instance a persistent hinge to processcloseresponse if you please  

                    connectioncontext.bodiconnection.processcloserequest(connectioncontext);

                    connectioncontext.bodiconnection.processcloseresponse(connectioncontext); 

                    break;

                case "//open": //do instance a persistent bodi hinge at //context

                    connectioncontext.bodiconnection.processopenrequest(connectioncontext);

                    connectioncontext.bodiconnection.processopenresponse(connectioncontext); 

                    break;

                case "//pull": //do instance a persistent bodi hinge at //context

                    connectioncontext.bodiconnection.processpullrequest(connectioncontext);

                    connectioncontext.bodiconnection.processpullresponse(connectioncontext); 

                    break;

                case "//put": //do instance a persistent bodi hinge at //context

                    connectioncontext.bodiconnection.processputrequest(connectioncontext);

                    connectioncontext.bodiconnection.processputresponse(connectioncontext); 

                    break;   

                case "//trade": //do instance a persistent bodi hinge at //context

                    connectioncontext.bodiconnection.processtraderequest(connectioncontext);

                    connectioncontext.bodiconnection.processtraderesponse(connectioncontext); 

                    break;

                default: 

                    break;
            }        
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
        
        //this.object = this.getrequestedobject(context, key);
        
        return this.object == null;
    }
    
    private Boolean checkconnection(Bodiconnection bodiconnection)            
    {
        if(bodiconnection==null) return false;
        
        if(bodiconnection.ttl<=0) return false;
        
        return true;
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
        
        return this.object = bodicarrier;
    }
    
    protected String getresult()
    {
        return this.result = "ok";
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
        
        return this.ttl = ttl-(now-day);
    }        
    
    @Override
    public String toString()
    {
        switch(this.operation)
        {
            case "//close":
                
                return "//close //sessionid="+sessionid+" //result=TBI //bodicontext=TBI";
                            
            case "//handshake": 
                
                return "//handshake //sessionid="+sessionid+" //result="+result;
            
            case "//open":
                
                return "//open //sessionid="+sessionid+" //result="+result+" //bodicontext=TBI";
            
            case "//pull": 
                
                return "//pull //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            case "//put": 
                
                return "//put //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            case "//trade": 
                
                return "//trade //sessionid="+sessionid+" //result=TBD //bodicontext=TBI";
            
            default: return "//other //result=UNKNOWN";
        }
    }    
    
    public Boolean processcloseresponse(Bodiserverconnectioncontext connectioncontext)
    {
        this.islive = false;       
        
        if(Bodi.hascontextat(connectioncontext.getcontext(connectioncontext)))
        {
            return this.lines.remove(connectioncontext.getcontext(connectioncontext)); //remove a persistent line to bodi instance
        }
        
        return false;
    }
    
    public Boolean processhandshakeresponse(Bodiserverconnectioncontext connectioncontext)
    {
        return false;
    }    
    
    public Boolean processopenresponse(Bodiserverconnectioncontext parameterization)
    {
        this.islive = true;            
        
        if(Bodi.hascontextat(parameterization.getcontext(parameterization))) 
        {
            return this.lines.add(parameterization.getcontext(parameterization)); //remove a persistent line to bodi instance
        }
        
        return false;
    }    
    
    public Boolean processpullresponse(Bodiserverconnectioncontext connectioncontext)
    {
        return true;
    }    
    
    public Boolean processputresponse(Bodiserverconnectioncontext connectioncontext)
    {
        return true;
    }    
    
    public Boolean processtraderesponse(Bodiserverconnectioncontext connectioncontext)
    {
        return true;
    }    
    
    public String stripforkey(Bodiserverconnectioncontext connectioncontext)
    {
        return ProtocolStripper.stripforkey(connectioncontext);
    }
    
    public String stripforvalue(Bodiserverconnectioncontext connectioncontext)
    {
        return ProtocolStripper.stripforvalue(connectioncontext);
    }
    
    public String stripforcontext(Bodiserverconnectioncontext connectioncontext)
    {
        return ProtocolStripper.stripforcontext(connectioncontext);
    }     
    
/**
     * New handshakes should return new Bodiconnection instances with unique sessionid values
     * 
     * Existing Bodiconnections should return updated TTLs possibly more.
     * 
     * @param buffer
     * @return 
     */
    public Bodiconnection processhandshakerequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;
        
        bodiconnection.operation = "//handshake";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processcloserequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {               
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                           
        
        bodiconnection.operation = "//close";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processputrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                       
        
        bodiconnection.operation = "//put";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processpullrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                      
        
        bodiconnection.operation = "//pull";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processopenrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {        
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                  
        
        bodiconnection.operation = "//open";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processtraderequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                       
        
        bodiconnection.operation = "//trade";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection other(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                      
        
        bodiconnection.operation = "//other";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }    
}
