package apml.system.bodi.remote;

import apml.system.bodi.Bodi;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 */
public class Bodiconnection
{
    public ArrayList<String> sessionids = new ArrayList();
    
    public String sessionid = "0x123456789abcdef";
    
    public String host;
    
    public Bodi bodi;
    
    public Boolean islive = false;
    
    
    public Bodiconnection(Bodi bodi)
    {
        this.bodi = bodi;
    }
    
    public Bodiconnection(Bodi bodi, String host)
    {
        this.bodi = bodi;
        
        this.host = host;
    }
    
    public Bodiconnection handshake(StringBuffer buffer, Bodiremoteserver server)
    {
        return null;
    }
    
    public Bodiconnection close(StringBuffer buffer, Bodiremoteserver server)
    {               
        return null;
    }
    
    public Bodiconnection put(StringBuffer buffer, Bodiremoteserver server)
    {
        return null;
    }
    
    public Bodiconnection pull(StringBuffer buffer, Bodiremoteserver server)
    {
        return null;
    }
    
    public Bodiconnection open(StringBuffer buffer, Bodiremoteserver server)
    {
        return null;
    }
    
    public Bodiconnection trade(StringBuffer buffer, Bodiremoteserver server)
    {
        return null;
    } 
}
