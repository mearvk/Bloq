package apml.system;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Max Rupplin
 */
public class Jndi 
{
    public static Map<Integer, Object> hashmap = new HashMap();
    public static Map<String, Object> apmlmap = new HashMap();
    
    public static void main(String[] args)
    {        
        Object one = new Object(); 
        Object two = new Object();
        Object three = new Object();
        
        Jndi.setresource(one);
        Jndi.setresource(two);
        Jndi.setresource(three);
    }
    
    public static Object getresourcebyapmlmap(Integer hashcode)
    {
        return hashmap.get(hashcode);
    }    
    
    public static Object getresourcebyhashcode(Integer hashcode)
    {
        return hashmap.get(hashcode);
    }
    
    public static void setresource(Object object)
    {
        setresourcebyapmlmap(object);
        setresourcebyhashcode(object);
    }    
    
    private static void setresourcebyapmlmap(Object object)
    {
        StackTraceElement stackTraceElements[] = (new Throwable()).getStackTrace();
        
        for(StackTraceElement ste : stackTraceElements)
        {
            if(ste.toString().contains("apml.system.Jndi.main"))
            {
                System.err.println(ste.toString());
            }
        }
        
        String s = object.getClass().getName();
        String p = object.getClass().getPackage().getName();
        
        apmlmap.put(s, object);
    }    
    
    private static void setresourcebyhashcode(Object object)
    {
        hashmap.put(object.hashCode(), object);
    }      
}
