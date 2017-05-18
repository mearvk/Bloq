package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiprotocol extends Protocol
{        
    public static final String CLOSE = "//close";
    
    public static final String HANDSHAKE = "//handshake";
            
    public static final String OPEN = "//open";
            
    public static final String PUT = "//put";
            
    public static final String PULL = "//pull";
    
    public static final String TRADE = "//trade";
    
    public static final String OTHER = "//other";
    
    public static final String value = "";
    
    public Bodiprotocol()
    {
        super();
    }
}