package apml.system.bodi.remote;

/**
 * Represents a single connection to a Bodi server
 * 
 * @author Max Rupplin
 */
public class Bodiserverconnectioncontext
{
    public Bodiconnection bodiconnectioncontext;
    
    public Bodiremoteserver bodiserver;
    
    public Networkconnectioncontext networkconnectioncontext;
    
    public String protocol;
    
    public StringBuffer inputbuffer; 
    
    public String inputstring;
    
    public String key;
    
    public String value;
    
    public String context;    
    
    public Bodiserverconnectioncontext()
    {
        
    }
    
    public Bodiserverconnectioncontext(Bodiremoteserver bodiserver, String protocol, Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        if(protocol==null || inputstring==null || bodiserver==null || connectioncontext.networkconnectioncontext==null || connectioncontext.bodiconnectioncontext==null) throw new SecurityException("//bodi/connect"); 
        
        this.protocol = protocol;
        
        this.inputstring = inputstring;
        
        this.inputbuffer = connectioncontext.networkconnectioncontext.inqueue;
        
        this.bodiserver = connectioncontext.bodiserver;
        
        this.networkconnectioncontext = connectioncontext.networkconnectioncontext;
        
        this.bodiconnectioncontext = connectioncontext.bodiconnectioncontext;        
        
        //
        
        if( protocol.startsWith("//other") )
        {                       
            this.bodiconnectioncontext.cause = "unrecognized protocol"; 
                    
            this.bodiconnectioncontext.message = "unable to complete request";          
        }        
    }
    
    public Bodiserverconnectioncontext(Bodiremoteserver bodiserver, String protocol, String input, Networkconnectioncontext network, Bodiconnection bodiconnection) throws Exception
    {
        if(protocol==null || input==null || bodiserver==null || network==null || bodiconnection==null) throw new SecurityException("//bodi/connect");
            
        this.protocol = protocol;
        
        this.inputstring = input;
        
        this.inputbuffer = network.inqueue;
        
        this.bodiserver = bodiserver;
        
        this.networkconnectioncontext = network;
        
        this.bodiconnectioncontext = bodiconnection;
        
        this.bodiconnectioncontext.operation = protocol;
        
        //
        
        if( protocol.startsWith("//other") )
        {                       
            this.bodiconnectioncontext.cause = "unrecognized protocol"; 
                    
            this.bodiconnectioncontext.message = "unable to complete request";          
        }
    }
    
    public String getcontext(Bodiserverconnectioncontext parameterization)
    {
        return ProtocolStripper.stripforcontext(parameterization);
    }
    
    public void processprotocol(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        connectioncontext.bodiserver.protocolhandler.parseprotocol(connectioncontext);
    }
    
    public void processrequest(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        connectioncontext.bodiconnectioncontext.processrequest(connectioncontext);
    }
    
    public void processsesponse(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        connectioncontext.networkconnectioncontext.processresponse(connectioncontext);
    }
}
