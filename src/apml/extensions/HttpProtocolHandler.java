package apml.extensions;

/**
 *
 * @author Max Rupplin
 */
public class HttpProtocolHandler extends ProtocolHandler
{
    /**
     * 
     * @param protocoltoken 
     */
    @Override
    public void processprotocol(String protocoltoken)
    {
        switch(protocoltoken)
        {
            case "GET": break;
            
            case "HEAD": break;
            
            case "POST": break;
            
            case "PUT": break;
            
            case "DELETE": break;
            
            case "TRACE": break;
            
            case "OPTIONS": break;
            
            case "CONNECT": break;
            
            case "PATCH": break;
            
            default: break;             
        }
    }    

    /**
     * 
     * @param servercontext 
     */
    @Override
    public void processprotocol(ServerContext servercontext)
    {
        String protocoltoken = "GET";
        
        switch(protocoltoken)
        {
            case "GET": break;
            
            case "HEAD": break;
            
            case "POST": break;
            
            case "PUT": break;
            
            case "DELETE": break;
            
            case "TRACE": break;
            
            case "OPTIONS": break;
            
            case "CONNECT": break;
            
            case "PATCH": break;
            
            default: break;            
        }
    }
}
