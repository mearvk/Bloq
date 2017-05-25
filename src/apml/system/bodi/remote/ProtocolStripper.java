package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class ProtocolStripper
{        
    public static String stripforkey(Bodiservercontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.inputstring.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//key="))
            {
                String substring = string.replace("//key=", "");
                
                return substring;
            }
        }
        
        return null;
    }
    
    public static String stripforvalue(Bodiservercontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.inputstring.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//value="))
            {                
                String substring = string.replace("//value=", "");
                
                return substring;
            }
        }
        
        return null;
    }
    
    public static String stripforcontext(Bodiservercontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.inputstring.split(" ");
        
        for(String string : tokens)
        {
            if(string.trim().startsWith("//context=")) 
            {
                String substring = string.replace("//context=", "");
                
                return substring;
            }
        }
        
        return null;
    }   
    
    public static String stripforprotocoltoken(Bodiservercontext parameterization)
    {
        if(parameterization==null) throw new SecurityException("//bodi/connect");
        
        String tokens[] = parameterization.networkcontext.inqueue.toString().split(" ");
        
        if(tokens==null) return null;
        
        if(tokens.length==0) return null;
        
        if(tokens[0].startsWith("//")) return tokens[0];
        
        return null;
    }
}
