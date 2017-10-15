package apml.modeling;

import apml.system.Apmlbasesystem;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Max Rupplin
 * @since 03.29.2017
 */
public class Apmlsubscriber implements Observer
{
    protected final Integer hash = 0x888fe8;

    public Apmlbasesystem monitor;

    //

    public Boolean autostartable;
    
    public Boolean startable;                
    
    public String id;
    
    public String alias;    
    
    public String classname;
    
    public String extension;
    
    public String packagename;        
    
    //
    
    @Override
    public void update(Observable o, Object o1) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}