package apml.ui.compilers.java;

import java.io.File;

/**
 *
 * @author max rupplin
 */
public class Uifileguardian
{
    protected final Integer hash = 0x888fe8;
    
    public File xmlin;
    
    public Uifileguardian()
    {
        try
        {
            this.xmlin = new File("/home/oem/Desktop/UI/ui3.xml");    
        }
        catch(Exception err)
        {
            System.err.println(err);
        }        
    }
}
