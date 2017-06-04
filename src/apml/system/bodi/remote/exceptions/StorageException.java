package apml.system.bodi.remote.exceptions;

/**
 *
 * @author Max Rupplin
 */
public class StorageException extends Error
{
    /**
     * 
     * @param msg 
     */
    public StorageException(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connection");
    }
}
