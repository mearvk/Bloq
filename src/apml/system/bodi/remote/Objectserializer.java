
package apml.system.bodi.remote;

/**
 *
 * @author Max Rupplin
 */
public class Objectserializer //hit 'em with direct fuel injection frontal port only!!!
{
    public Objectserializer(Object object)
    {
        if(object==null) throw new SecurityException("//bodi/connect");
        
        //presumably one sets object as serializable via injection        
    }        
}
