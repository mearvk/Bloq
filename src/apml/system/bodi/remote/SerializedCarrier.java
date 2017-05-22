package apml.system.bodi.remote;

import java.io.Serializable;

/**
 *
 * @author Max Rupplin
 */
public class SerializedCarrier implements Serializable
{
    public Object object = null;
    
    public Class _class = null;
    
    public SerializedCarrier()
    {
        
    }
    
    public SerializedCarrier(Class _class, Object object)
    {
        this._class = _class;
        
        this.object = object;
    }
}