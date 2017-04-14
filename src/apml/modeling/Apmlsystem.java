/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.modeling;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Max Rupplin
 */
public class Apmlsystem implements Observer
{
    protected final Integer hash = 0x888fe8;
        
    /* -------------------------------------------------------------------------*/

    public Boolean autostartable;
    
    public Boolean startable;                
    
    public String id;
    
    public String alias;    
    
    public String classname;
    
    public String extension;
    
    public String packagename;        
    
    /* -------------------------------------------------------------------------*/
    
    @Override
    public void update(Observable o, Object o1) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
