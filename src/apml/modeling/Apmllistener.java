package apml.modeling;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 * @since 03.29.2017
 */
public class Apmllistener 
{
    protected final Integer hash = 0x888fe8;
        
    public boolean startable;
    
    public boolean autostartable;
    
    /* ------------------------------------------------------------------------*/
    
    public String alias;
    
    public String classname;
    
    public String extension;
    
    public String id;    
    
    public String packagename;
    
    public ArrayList<Apmlsubscriber> subscribers = new ArrayList();
}
