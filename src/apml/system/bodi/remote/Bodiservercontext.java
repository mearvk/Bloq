package apml.system.bodi.remote;

/**
 * Represents a single connection to a Bodi server
 * 
 * @author Max Rupplin
 */
public class Bodiservercontext
{   
    public Bodiconnection bodicontext;
    
    public Bodiremoteserver bodiserver;
    
    public Networkconnectioncontext networkcontext;
    
    public String protocol;
    
    public String packet;
    
    public StringBuffer inputbuffer; 
    
    public String inputstring;
    
    public String key;
    
    public String value;
    
    public String context;    
    
    public Bodiservercontext()
    {
        
    }
    
    public Bodiservercontext(Bodiremoteserver bodiserver, String protocol, Bodiservercontext bodiservercontext) throws Exception
    {
        if(bodiserver==null || protocol==null || bodiservercontext==null) throw new SecurityException("//bodi/connect");                 
        
        this.bodiserver = bodiserver;
        
        this.protocol = new StringBuffer(protocol).toString();
        
        this.inputbuffer = new StringBuffer(bodiservercontext.networkcontext.inqueue);               
        
        this.inputstring = new StringBuffer(bodiservercontext.networkcontext.inqueue).toString();  
        
        this.networkcontext = bodiservercontext.networkcontext;
        
        this.bodicontext = bodiservercontext.bodicontext;
        
        this.packet = new StringBuffer(bodiservercontext.networkcontext.inqueue).toString();        
    }    
    
    /**
     * Do not fill this with full expectation
     * 
     * @param bodiserver
     * @param networkcontext
     * @throws Exception 
     */
    public Bodiservercontext(Bodiremoteserver bodiserver, Networkconnectioncontext networkcontext) throws Exception
    {
        if(bodiserver==null || networkcontext==null) throw new SecurityException("//bodi/connect"); 
        
        this.bodiserver = bodiserver;                
        
        this.networkcontext = networkcontext;
        
        this.packet = networkcontext.inqueue.toString();
        
        //             
    }
    
    public Bodiservercontext(Bodiremoteserver bodiserver, String protocol, String input, Networkconnectioncontext network, Bodiconnection bodiconnection) throws Exception
    {
        if( bodiserver==null || protocol==null || input==null || network==null || bodiconnection==null) throw new SecurityException("//bodi/connect");
            
        this.protocol = protocol;
        
        this.inputstring = input;
        
        this.inputbuffer = network.inqueue;
        
        this.bodiserver = bodiserver;
        
        this.networkcontext = network;
        
        this.bodicontext = bodiconnection;
        
        this.bodicontext.operation = protocol;
        
        this.packet = new StringBuffer(network.inqueue).toString();
        
        //
        
        if( protocol.startsWith("//other") )
        {                       
            this.bodicontext.cause = "unrecognized protocol"; 
                    
            this.bodicontext.message = "unable to complete request";          
        }
    }
    
    public String getcontext(Bodiservercontext parameterization)
    {
        return ProtocolStripper.stripforcontext(parameterization);
    }
    
    public void processprotocol(Bodiservercontext connectioncontext) throws Exception
    {
        connectioncontext.bodiserver.protocolhandler.parseprotocol(connectioncontext);
    }
    
    public void processrequest(Bodiservercontext connectioncontext) throws Exception
    {
        connectioncontext.bodicontext.processrequest(connectioncontext);
    }
    
    public void processsesponse(Bodiservercontext connectioncontext) throws Exception
    {
        connectioncontext.networkcontext.processresponse(connectioncontext);
    }
}
