package apml.system.bodi.remote;

import apml.system.bodi.Bodi;
import apml.system.bodi.Bodicontext;

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
    
    public Long ttl = 60*1000*10l;
    
    public Long day;       
    
    public Serializable object;
    
    public String host;                
    
    public String result;
    
    public String operation = ""; 
    
    public String cause;
    
    public String error = "";        
    
    public String context;
    
    public String key;    
    
    public String message;
    
    public String value;     
    
   
    
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
    
     //in fact reach into a bodi reference and see about getting an object etc.
    
    public Boolean processrequest(Bodiserverconnectioncontext connectioncontext)
    {                
        StringBuffer buffer = connectioncontext.inputbuffer;      
        
        String protocol = this.stripforprotocoltoken(connectioncontext);        
        
        try
        {              
            if(this.gettimetolive()<=0) protocol = "//other"; //send an other reply for errant bodi request
            
            switch(protocol)
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
                    
                    connectioncontext.bodiconnection.processotherrequest(connectioncontext);

                    connectioncontext.bodiconnection.processotherresponse(connectioncontext);                    

                    break;
            }        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //this.object = this.getrequestedobject(context, key);
        
        return this.object == null;
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
        connectioncontext.bodiconnection.result = "success";
        
        connectioncontext.bodiconnection.message = "welcome";
        
        return false;
    }    
    
    public Boolean processopenresponse(Bodiserverconnectioncontext connectioncontext)
    {
        this.islive = true;            
        
        if(Bodi.hascontextat(connectioncontext.getcontext(connectioncontext))) 
        {
            connectioncontext.bodiconnection.result = "success";
            
            connectioncontext.bodiconnection.message = "persistent connection established";
            
            return this.lines.add(connectioncontext.getcontext(connectioncontext)); //remove a persistent line to bodi instance
        }
        
        connectioncontext.bodiconnection.result = "failure";
        
        connectioncontext.bodiconnection.cause = "no such context";
        
        return false;
    } 
    
    public Boolean processotherresponse(Bodiserverconnectioncontext connectioncontext)
    {
        connectioncontext.bodiconnection.operation = "//other";
        
        connectioncontext.bodiconnection.result = "rejection";
        
        connectioncontext.bodiconnection.message = "unusual; please recheck";
        
        try
        {
            //no valid TTL 
            if(this.gettimetolive()<=0) 
            {
                connectioncontext.bodiconnection.cause = "TTL expired";    
            }                        
            //no valid session issue
            else if(this.server.isvalidsessionid(connectioncontext.bodiconnection)) 
            {
                connectioncontext.bodiconnection.cause = "Session ID not valid";                
            }
            //unclear cause; tokens may be cause
            else 
            {
                connectioncontext.bodiconnection.cause = "Unclear cause; check all tokens";     
            }
        }
        catch(Exception e)
        {
            //
        }
        finally
        {
            return false;
        }
    }
    
    public Boolean processpullresponse(Bodiserverconnectioncontext connectioncontext)
    {                
        return true;
    }    
    
    public Boolean processputresponse(Bodiserverconnectioncontext connectioncontext)
    {
        this.islive = true;
        
        String context = this.stripforcontext(connectioncontext);
        
        String key = connectioncontext.bodiconnection.key;
        
        String value = connectioncontext.bodiconnection.value;        

        //Bodi.context(context).put
        
        /*if(Bodi.hascontextat(context) && key!=null && value!=null) //comprehensive case
        {
            connectioncontext.bodiconnection.result = "failure";

            connectioncontext.bodiconnection.message = "persistent context already established";            
        }
        else if(Bodi.hascontextat(context))
        {
            Bodi.setcontext(context);

            connectioncontext.bodiconnection.result = "success";

            connectioncontext.bodiconnection.message = "persistent context established";            
        }*/              
        
        return true;
    }    
    
    public Boolean processtraderesponse(Bodiserverconnectioncontext connectioncontext)
    {
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
    public Bodiconnection processhandshakerequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;
        
        bodiconnection.operation = "//handshake";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        //bodiconnection.getrequestedobject("context", "key");
        
        //bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processcloserequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {               
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                           
        
        bodiconnection.operation = "//close";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        //bodiconnection.getrequestedobject("context", "key");
        
        //bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processputrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                       
        
        bodiconnection.operation = "//put";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        //---------------------------------------------------------------------//
        
        bodiconnection.key = this.stripforkey(connectioncontext);
        
        bodiconnection.context = this.stripforcontext(connectioncontext);
        
        bodiconnection.value = this.stripforvalue(connectioncontext);
                        
        try
        {
            if(Bodi.hascontextat(context))
            {
                Bodi.context(bodiconnection.context).put(key, value);
            }
            else
            {
                Bodi.setcontext(context);
                
                Bodi.context(bodiconnection.context).put(key, value);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return bodiconnection;
    }
    
    public Bodiconnection processpullrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                      
        
        bodiconnection.operation = "//pull";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        //---------------------------------------------------------------------//
        
        bodiconnection.key = this.stripforkey(connectioncontext);
        
        bodiconnection.context = this.stripforcontext(connectioncontext);
        
        Object object = null;
        
        object = Bodi.context(bodiconnection.context).pull(key);
        
        if(object==null) 
        {
            bodiconnection.message = "unable to pull that key/context pair; sorry";
        
            bodiconnection.value = null;
        
            bodiconnection.result = "failure";
        }
        else
        {
            SerializedCarrier carrier = new SerializedCarrier(object.getClass(), object);
            
            bodiconnection.message = "key/context pair found.";
            
            bodiconnection.object = carrier;
                          
            bodiconnection.value = carrier.object.toString();
        
            bodiconnection.result = "success";
        }        ;
        
        return bodiconnection;
    }
    
    public Bodiconnection processopenrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {        
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                  
        
        bodiconnection.operation = "//open";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        //bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processotherrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        return connectioncontext.bodiconnection;
    }
    
    public Bodiconnection processtraderequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                                                       
        
        bodiconnection.operation = "//trade";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        //bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection other(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        Bodiconnection bodiconnection = connectioncontext.bodiconnection;                      
        
        bodiconnection.operation = "//other";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        //bodiconnection.getresult();
        
        return bodiconnection;
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
    
    public String stripforprotocoltoken(Bodiserverconnectioncontext connectioncontext)
    {
        return ProtocolStripper.stripforprotocoltoken(connectioncontext);
    }
    
    private Boolean checkconnection(Bodiserverconnectioncontext connectioncontext)            
    {
        if(connectioncontext.bodiconnection==null) return false;
        
        if(connectioncontext.bodiconnection.ttl<=0) return false;
        
        if(this.server.isvalidsession(connectioncontext.bodiconnection)==null) return false;            
        
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
        return this.result = "";
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
        
        if( (this.ttl = ttl-(now-day))<0 )
        {
            return -1L;
        }      
        
        return this.ttl;
    }        
    
    @Override
    public String toString()
    {
        switch(this.operation)
        {
            case "//close":
                
                if(cause!=null)
                {
                    return "//close //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl;
                }
                 
                if(message!=null)
                {
                    return "//close //sessionid="+sessionid+" //result="+result+" //message="+message+" //ttl="+ttl;
                }
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
                
            case "//handshake": 
                
                if(cause!=null)
                {
                    return "//handshake //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl;
                }
                
                if(message!=null)
                {
                    return "//handshake //sessionid="+sessionid+" //result="+result+" //message="+message+" //ttl="+ttl;
                }
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
            
            case "//open":
                
                if(cause!=null)
                {
                    return "//open //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl;
                }
               
                if(message!=null)
                {
                    return "//open //sessionid="+sessionid+" //result="+result+" //message="+message+" //ttl="+ttl;
                }                
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
            
            case "//pull": 
                
                if(message!=null)
                {
                    return "//pull //sessionid="+sessionid+" //result="+result+" //message="+message+" //object="+object+" //ttl="+ttl+" //value="+value;
                }
                
                if(cause!=null)
                {
                    return "//put //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl+" //value="+value;
                }
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
            
            case "//put": 
                
                if(message!=null)
                {
                    return "//put //sessionid="+sessionid+" //result="+result+" //message="+message+" //ttl="+ttl;
                }
                
                if(cause!=null)
                {
                    return "//put //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl;
                }
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
            
            case "//trade": 
                
                if(message!=null)
                {
                    return "//trade //sessionid="+sessionid+" //result="+result+" //message="+message+" //ttl="+ttl;
                }
                
                if(cause!=null)
                {
                    return "//trade //sessionid="+sessionid+" //result="+result+" //cause="+cause+" //ttl="+ttl;
                }
                
                return "//close //sessionid="+sessionid+" //result="+result+" //ttl="+ttl;
            
            default: return "//other //result="+result+" //message="+message+" //cause="+cause;
        }
    }        
}
