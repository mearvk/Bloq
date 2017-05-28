package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class SessionStorageError extends Error
{
    public SessionStorageError(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connection");
    }
}
