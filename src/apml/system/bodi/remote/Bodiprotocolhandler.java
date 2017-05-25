package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocolhandler
{ 
    public Integer hash = 0x008808ef;
    
    protected Bodiservercontext parseprotocol( Bodiservercontext connectioncontext ) throws Exception
    {                        
        if(connectioncontext==null) throw new SecurityException("//bodi/connect");
        
        if(connectioncontext.networkcontext!=null) 
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
    
    protected Boolean processprotocol(Bodiservercontext connectioncontext) throws Exception, SecurityException
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

    private void processcloseprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {        
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//close")) throw new SecurityException("//bodi/connect");        
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping."); 
        
        /*---------------------------------------------------------------------*/                
    }

    private void processhandshakeprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");
        
        if(!this.startsswith(connectioncontext, "//handshake")) throw new SecurityException("//bodi/connect");              
        
        /*---------------------------------------------------------------------*/        
    }
    
    private void processopenprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//open")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");                
        
        /*---------------------------------------------------------------------*/       
    }
    
    private void processpullprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//pull")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");
            
        if(!this.containskey(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //key token missing; stopping.");
        
        /*---------------------------------------------------------------------*/       
    }
    
    private void processputprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {        
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//put")) throw new SecurityException("//bodi/connect");
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");
            
        if( !this.containskey(connectioncontext.inputbuffer) && this.containsvalue(connectioncontext.inputbuffer) )
        {
            throw new SecurityException("Bodi //key token missing; stopping.");
        }
        
        if( this.containskey(connectioncontext.inputbuffer) && !this.containsvalue(connectioncontext.inputbuffer) )
        {
            throw new SecurityException("Bodi //value token missing; stopping.");
        }        
        
        /*---------------------------------------------------------------------*/        
    }
    
    private void processtradeprotocol(Bodiservercontext connectioncontext) throws SecurityException
    {
        if(!this.subtokenswellformed(connectioncontext)) throw new SecurityException("//bodi/connect");    
        
        if(!this.startsswith(connectioncontext, "//trade")) throw new SecurityException("//bodi/connect");        
        
        if(!this.containssessionsid(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //sessionid token missing; stopping.");
            
        if(!this.containscontext(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //context token missing; stopping.");

        if(!this.containskey(connectioncontext.inputbuffer)) throw new SecurityException("Bodi //key token missing; stopping.");
        
        /*---------------------------------------------------------------------*/
    }
    
    
    /*-------------------------------------------------------------------------*/
    
    
    public Boolean startsswith(Bodiservercontext connectioncontext, String comparator)
    {
        String input = connectioncontext.inputstring;
        
        return input.startsWith(comparator);
    }
    
    public String stripforkey(Bodiservercontext bodiserverconnectioncontext)
    {
        return ProtocolStripper.stripforkey(bodiserverconnectioncontext);
    }
    
    public String stripforvalue(Bodiservercontext bodiserverconnectioncontext)
    {
        return ProtocolStripper.stripforvalue(bodiserverconnectioncontext);
    }
    
    public String stripforcontext(Bodiservercontext bodiserverconnectioncontext)
    {
        return ProtocolStripper.stripforcontext(bodiserverconnectioncontext);
    }   
    
    public String stripforprotocoltoken(Bodiservercontext bodiserverconnectioncontext)
    {
        return ProtocolStripper.stripforprotocoltoken(bodiserverconnectioncontext);
    }
    
    protected Boolean subtokenswellformed(Bodiservercontext connectioncontext)
    {
        String[] tokens = connectioncontext.inputstring.split(" ");            
        
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
}



