package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiserverparameter
{
    public Bodiconnection bodiconnection;
    
    public Bodiremoteserver bodiserver;
    
    public Connection network;
    
    public String protocol;
    
    public StringBuffer inputbuffer;                        
    
    public Bodiserverparameter()
    {
        
    }
    
    public Bodiserverparameter(String protocol, StringBuffer inputbuffer, Bodiremoteserver bodiserver, Connection network, Bodiconnection bodiconnection)
    {
        this.protocol = protocol;
        
        this.inputbuffer = inputbuffer;
        
        this.bodiserver = bodiserver;
        
        this.network = network;
        
        this.bodiconnection = bodiconnection;
    }
}
