package apml.system.bodi.remote;

import apml.system.bodi.Bodi;
import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiconnection
{
    public ArrayList<String> sessionids = new ArrayList<String>();
    
    public Bodi bodi;
    
    public String host;
    
    
    public Bodiconnection(Bodi bodi)
    {
        this.bodi = bodi;
    }
    
    public Bodiconnection(Bodi bodi, String host)
    {
        this.bodi = bodi;
        
        this.host = host;
    }
    
    public void handshake(StringBuffer buffer, Bodi bodi)
    {
        //to be implemented
    }
    
    public void close(StringBuffer buffer, Bodi bodi)
    {
        //to be implemented
    }
    
    public Boolean put(StringBuffer buffer, Bodi bodi)
    {
        return new Boolean("true");
    }
    
    public Object pull(StringBuffer buffer, Bodi bodi)
    {
        return new Object();
    }
    
    public void open(StringBuffer buffer, Bodi bodi)
    {
        //to be implemented
    }
    
    public Object trade(StringBuffer buffer, Bodi bodi)
    {
        return new Object();
    } 
}
