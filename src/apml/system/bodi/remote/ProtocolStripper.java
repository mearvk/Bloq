package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class ProtocolStripper
{        
    public static String stripforkey(Bodiserverconnectioncontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//key=")) return string;
        }
        
        return null;
    }
    
    public static String stripforvalue(Bodiserverconnectioncontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//value=")) return string;
        }
        
        return null;
    }
    
    public static String stripforcontext(Bodiserverconnectioncontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.input.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//context=")) return string;
        }
        
        return null;
    }   
    
    public static String stripforprotocoltoken(Bodiserverconnectioncontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.input.split(" ");
        
        if(tokens==null) return null;
        
        if(tokens.length==0) return null;
        
        if(tokens[0].startsWith("//")) return tokens[0];
        
        return null;
    }
}
