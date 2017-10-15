package apml.modeling;

import apml.system.Apmlbasesystem;

import java.util.ArrayList;

/**
 *
 * @author Max Rupplin
 * @since 03.29.2017
 */
public class Apmlobject 
{
    protected final Integer hash = 0x888fe8;

    public Apmlbasesystem monitor;

    //

    public boolean startable;
    
    public boolean autostartable;
    
    //
    
    public String alias;
    
    public String classname;
    
    public String extension;
    
    public String id;
    
    public String packagename;
    
    //
    
    public ArrayList<Apmlobject> objects = new ArrayList();
    
    public ArrayList<Apmllistener> listeners = new ArrayList();
}
