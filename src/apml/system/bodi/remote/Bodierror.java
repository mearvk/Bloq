package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodierror extends Error
{
    public Bodierror(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connect");
    }
}
