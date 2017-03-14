package apml.drivers;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Stdbloqdriver extends Stddriver
{
    protected final Integer hash = 0x888fe8;

    public Stdbloqdriver(File workingdir)
    {
        super(workingdir);                
    }
    
    @Override
    public void drive()
    {
        
    }

    @Override
    public void init()
    {
        
    }        
}
