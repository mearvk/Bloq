package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Bodiduplicatekey extends Bodierror
{
    /**
     * 
     * @param msg 
     */
    public Bodiduplicatekey(String msg)
    {
        super(msg);
        
        if(msg==null) throw new SecurityException("//bodi/connect");
    }
}
