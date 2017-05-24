package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiserverconnectioncontext
{
    public Bodiconnection bodiconnection;
    
    public Bodiremoteserver bodiserver;
    
    public Networkconnectioncontext network;
    
    public String protocol;
    
    public StringBuffer inputbuffer; 
    
    public String input;
    
    public String key;
    
    public String value;
    
    public String context;    
    
    public Bodiserverconnectioncontext()
    {
        
    }
    
    public Bodiserverconnectioncontext(Bodiremoteserver bodiserver, String protocol, String input, Networkconnectioncontext network, Bodiconnection bodiconnection) throws Exception
    {
        if(protocol==null || input==null || bodiserver==null || network==null || bodiconnection==null) throw new SecurityException("//bodi/connect");
            
        this.protocol = protocol;
        
        this.input = input;
        
        this.inputbuffer = network.inqueue;
        
        this.bodiserver = bodiserver;
        
        this.network = network;
        
        this.bodiconnection = bodiconnection;
        
        this.bodiconnection.operation = protocol;
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
        connectioncontext.bodiconnection.processrequest(connectioncontext);
    }
    
    public void processsesponse(Bodiserverconnectioncontext connectioncontext) throws Exception
    {
        connectioncontext.network.processresponse(connectioncontext);
    }
}
