package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Storageerror extends Error
{
    /**
     * 
     * @param msg 
     */
    public Storageerror(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connection");
    }
}
