package apml.system.bodi.remote;

import java.io.Serializable;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocolhandler
{ 
    public Integer hash = 0x008808ef;
    
    protected Bodiserverconnectioncontext parseprotocol( Bodiserverconnectioncontext connectioncontext ) throws Exception
    {                        
        if(connectioncontext==null) throw new SecurityException("//bodi/connect");
        
        if(connectioncontext.network!=null) 
        {                                    
            switch(connectioncontext.protocol)
            {     
                //
                case Bodiprotocol.CLOSE:
                    
                    this.processcloseprotocol(connectioncontext);                                                            
                    
                    return connectioncontext;

                //
                case Bodiprotocol.HANDSHAKE:    
                    
                    this.processhandshakeprotocol(connectioncontext);                     
                    
                    return connectioncontext;                                                            

                //
                case Bodiprotocol.OPEN:         
                    
                    this.processopenprotocol(connectioncontext); 
                                                   
                    return connectioncontext;                                     

                //
                case Bodiprotocol.PULL:         
                    
                    this.processpullprotocol(connectioncontext);                                         
                    
                    return connectioncontext;                                  

                //
                case Bodiprotocol.PUT:          
                    
                    this.processputprotocol(connectioncontext);                     
                    
                    return connectioncontext;                   

                //
                case Bodiprotocol.TRADE:        
                    
                    this.processtradeprotocol(connectioncontext);                                        
                    
                    return connectioncontext;
                   
                //miscellaneous messaging
                default:
                    
                    this.processprotocol(connectioncontext);                                         
                    
                    return connectioncontext;
            }                        
        }  
        
        throw new NoValidConnectionException("No valid connection found.");
    } 
    
    protected Boolean processprotocol(Bodiserverconnectioncontext connectioncontext) throws Exception, SecurityException
    {                       
        String protocol = connectioncontext.protocol;
        
        StringBuffer buffer = connectioncontext.inputbuffer;
                
        switch(buffer.toString())
        {
            case "//close": 
                
                //aheam a drum
                
                break;
            
            case "//handshake": 
                
                //clear use case if any
                
                break;
            
            case "//open": 
                
                //autid a did
                
                break;
            
            case "//pull": 
                

            
                //something about the bodi context here...
                
                break;
            
            case "//put": 
                
                //1234$###$$$@$1419
            
                break;
            
            case "//trade": 
                
                //1040040012344444
                
                break;
            
            default: break;
        }
        
        //        
        //if(!this.subtokenswellformed(buffer)) throw new Exception("One or more tokens malformed; stop.");       
        
        return true;
    }           

    private void processcloseprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {        
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//close")) throw new SecurityException("//bodi/connect");        
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping."); 
    }

    private void processhandshakeprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");
        
        if(!this.startsswith(connectioncontext, "//handshake")) throw new SecurityException("//bodi/connect");              
    }
    
    private void processopenprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//open")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");                
    }
    
    private void processpullprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//pull")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");
            
        if(!this.containskey(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //key token missing; stopping.");
                                    
        connectioncontext.key = "";
                    
        connectioncontext.context = "";
    }
    
    private void processputprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {        
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//put")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");
            
        if(!this.containskey(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //key token missing; stopping.");
            
        if(!this.containsvalue(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //value token missing; stopping.");
            
        //something about the bodi context here...
    }
    
    private void processtradeprotocol(Bodiserverconnectioncontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//trade")) throw new SecurityException("//bodi/connect");        
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");

        if(!this.containskey(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //key token missing; stopping.");
            
        //something about the bodi context here...        
    }
    
    
    /*-------------------------------------------------------------------------*/
    
    
    public Boolean startsswith(Bodiserverconnectioncontext connectioncontext, String comparator)
    {
        String input = connectioncontext.input;
        
        return input.startsWith(comparator);
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
    
    protected Boolean subtokenswellformed(Bodiserverconnectioncontext connectioncontext)
    {
        String[] tokens = connectioncontext.input.split(" ");            
        
        for(String token : tokens)
        {
            if(!token.trim().startsWith("//")) return false;
            
            //contains =
                        
            //so forth
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


