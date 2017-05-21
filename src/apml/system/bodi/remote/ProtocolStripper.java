package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class ProtocolStripper
{        
    public static String stripforkey(Bodiserverconnectioncontext parameterization)
    {
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//key=")) return string;
        }
        
        return null;
    }
    
    public static String stripforvalue(Bodiserverconnectioncontext parameterization)
    {
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//value=")) return string;
        }
        
        return null;
    }
    
    public static String stripforcontext(Bodiserverconnectioncontext parameterization)
    {
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//context=")) return string;
        }
        
        return null;
    }   
}
