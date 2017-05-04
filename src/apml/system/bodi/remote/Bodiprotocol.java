package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public enum Bodiprotocol
{        
    CLOSE("//close"),
    
    HANDSHAKE("//handshake"),
            
    OPEN("//open"),
            
    PULL("//pull"),            
            
    PUT("//put"),
    
    TRADE("//trade");
    
    public String value;
    
    Bodiprotocol(String args)
    {
        this.value = args;
    }
}