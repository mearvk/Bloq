package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public enum Protocol
{
    HELLO("hello");
    
    Protocol(String val)
    {
        this.val = val;
    }
    
    public String val;
}
