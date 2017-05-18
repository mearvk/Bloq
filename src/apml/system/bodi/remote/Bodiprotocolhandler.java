package apml.system.bodi.remote;

import java.io.Serializable;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocolhandler
{ 
    protected Bodiconnection parseprotocol( Bodiserverparameter parameterization ) throws Exception
    {                      
        if(parameterization.network!=null)
        {                                    
            switch(parameterization.protocol)
            {                
                //
                case Bodiprotocol.CLOSE:
                    
                    this.processprotocol(parameterization); 
                    
                    this.processcloserequest(parameterization);                    
                    
                    return parameterization.bodiconnection;

                //
                case Bodiprotocol.HANDSHAKE:    
                    
                    this.processprotocol(parameterization); 
                    
                    this.processhandshakerequest(parameterization);
                    
                    return parameterization.bodiconnection;                                                            

                //
                case Bodiprotocol.OPEN:         
                    
                    this.processprotocol(parameterization); 
                    
                    this.processopenrequest(parameterization);                  
                    
                    return parameterization.bodiconnection;                                     

                //
                case Bodiprotocol.PULL:         
                    
                    this.processprotocol(parameterization); 
                                        
                    this.processpullrequest(parameterization);                    
                    
                    return parameterization.bodiconnection;                                  

                //
                case Bodiprotocol.PUT:          
                    
                    this.processprotocol(parameterization); 
                    
                    this.processputrequest(parameterization);                   
                    
                    return parameterization.bodiconnection;                   

                //
                case Bodiprotocol.TRADE:        
                    
                    this.processprotocol(parameterization); 
                    
                    this.processtraderequest(parameterization);                   
                    
                    return parameterization.bodiconnection;
                   
                //error & dirty messages
                default:
                    
                    this.processprotocol(parameterization); 
                    
                    this.dobodiconnectionsetup(parameterization);                    
                    
                    return parameterization.bodiconnection;
            }                        
        }  
        
        throw new NoValidConnectionFoundException("No valid connection found.");
    } 
    
    protected Boolean processprotocol(Bodiserverparameter parameter) throws Exception
    {                       
        String protocol = parameter.protocol;
        
        StringBuffer buffer = parameter.inputbuffer;
                
        //        
        if(!this.subtokenswellformed(buffer)) throw new Exception("One or more tokens malformed; stop.");
        
        //        
        if(protocol.startsWith("//close"))
        {            
            if(!this.containssessionsid(buffer)) throw new Exception("BODI //sessionid token missing; stop.");                
        }        
        
        //
        else if(protocol.startsWith("//handshake"))
        {            
            //
        }
        
        //
        else if(protocol.startsWith("//open"))
        {
            if(!this.containssessionsid(buffer)) throw new Exception("BODI //sessionid token missing; stop.");
            
            if(!this.containscontext(buffer)) throw new Exception("BODI //context token missing; stop.");            

            //something about the bodi context here...
        }

        //
        else if(protocol.startsWith("//pull"))
        {
            if(!this.containssessionsid(buffer)) throw new Exception("BODI //sessionid token missing; stop.");
            
            if(!this.containscontext(buffer)) throw new Exception("BODI //context token missing; stop.");
            
            if(!this.containskey(buffer)) throw new Exception("BODI //key token missing; stop.");
            
            //something about the bodi context here...
        }

        //
        else if(protocol.startsWith("//put"))
        {
            if(!this.containssessionsid(buffer)) throw new Exception("BODI //sessionid token missing; stop.");
            
            if(!this.containscontext(buffer)) throw new Exception("BODI //context token missing; stop.");
            
            if(!this.containskey(buffer)) throw new Exception("BODI //key token missing; stop.");
            
            if(!this.containsvalue(buffer)) throw new Exception("BODI //value token missing; stop.");
            
            //something about the bodi context here...
        }

        //
        else if(protocol.startsWith("//trade"))
        {
            if(!this.containssessionsid(buffer)) throw new Exception("BODI //sessionid token missing; stop.");
            
            if(!this.containscontext(buffer)) throw new Exception("BODI //context token missing; stop.");

            if(!this.containskey(buffer)) throw new Exception("BODI //key token missing; stop.");
            
            //something about the bodi context here...
        }                        
        
        //
        else 
        {
            //return false
        }
        
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
    public Bodiconnection processhandshakerequest(Bodiserverparameter parameter) throws Exception
    {
        Bodiconnection bodiconnection = parameter.bodiconnection;
        
        bodiconnection.op = "//handshake";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processcloserequest(Bodiserverparameter parameter) throws Exception
    {               
        Bodiconnection bodiconnection = parameter.bodiconnection;                           
        
        bodiconnection.op = "//close";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processputrequest(Bodiserverparameter parameter) throws Exception
    {
        Bodiconnection bodiconnection = parameter.bodiconnection;                                                       
        
        bodiconnection.op = "//put";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processpullrequest(Bodiserverparameter parameter) throws Exception
    {
        Bodiconnection bodiconnection = parameter.bodiconnection;                                      
        
        bodiconnection.op = "//pull";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processopenrequest(Bodiserverparameter parameter) throws Exception
    {        
        Bodiconnection bodiconnection = parameter.bodiconnection;                                                  
        
        bodiconnection.op = "//open";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection processtraderequest(Bodiserverparameter parameter) throws Exception
    {
        Bodiconnection bodiconnection = parameter.bodiconnection;                                                       
        
        bodiconnection.op = "//trade";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    }
    
    public Bodiconnection other(Bodiserverparameter parameter) throws Exception
    {
        Bodiconnection bodiconnection = parameter.bodiconnection;                      
        
        bodiconnection.op = "//other";
        
        bodiconnection.getsessionid();
        
        bodiconnection.gettimetolive();                
        
        bodiconnection.getrequestedobject("context", "key");
        
        bodiconnection.getresult();
        
        return bodiconnection;
    } 
    
    protected Boolean subtokenswellformed(StringBuffer buffer)
    {
        String[] tokens = buffer.toString().split(" ");            
        
        for(String token : tokens)
        {
            if(!token.trim().startsWith("//")) return false;
        }        
        
        return true;
    }
    
    protected Boolean containssessionsid(StringBuffer buffer)
    {
        String[] tokens = buffer.toString().split(" ");
        
        Boolean containssessionid = false;
            
        for(String token : tokens)
        {
            if(token.trim().startsWith("//sessionid=")) containssessionid = true;
        }     
        
        return containssessionid;
    }
    
    protected Boolean containscontext(StringBuffer buffer)
    {
        String[] tokens = buffer.toString().split(" ");
        
        Boolean containscontext = false;
            
        for(String token : tokens)
        {
            if(token.trim().startsWith("//context=")) containscontext = true;
        }     
        
        return containscontext;        
    }
    
    protected Boolean containskey(StringBuffer buffer)
    {
        String[] tokens = buffer.toString().split(" ");
        
        Boolean containskey = false;
            
        for(String token : tokens)
        {
            if(token.trim().startsWith("//key=")) containskey = true;
        }     
        
        return containskey;              
    }
    
    protected Boolean containsvalue(StringBuffer buffer)
    {
        String[] tokens = buffer.toString().split(" ");
        
        Boolean containskey = false;
            
        for(String token : tokens)
        {
            if(token.trim().startsWith("//value=")) containskey = true;
        }     
        
        return containskey;        
    }
    
    protected Bodiconnection dobodiconnectionsetup(Bodiserverparameter parameter)
    {
        return null;
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


