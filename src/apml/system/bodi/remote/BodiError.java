package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class BodiError extends Error
{
    public BodiError(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connect");
    }
}
