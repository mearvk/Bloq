package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class BodiDuplicateKeyError extends BodiError
{
    public BodiDuplicateKeyError(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connect");
    }
}
