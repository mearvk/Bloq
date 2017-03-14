/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.drivers;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public abstract class Stddriver 
{
    protected final Integer hash = 0x888fe8;
    
    protected File workingdir;
        
    public Stddriver()
    {
        
    }
    
    public Stddriver(File workingdir)
    {
        this.workingdir = workingdir;
    }
    
    public abstract void drive();
    
    public abstract void init();
}
