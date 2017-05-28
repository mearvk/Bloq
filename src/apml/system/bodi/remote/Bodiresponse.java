package apml.system.bodi.remote;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiresponse
{
    public String result;
    
    public String message;
    
    public String cause;
    
    public Object value;
    
    public ArrayList<String> values;
    
    public Long ttl = 60*1000*5L;    
}
