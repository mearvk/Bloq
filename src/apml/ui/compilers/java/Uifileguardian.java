package apml.ui.compilers.java;

import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class Uifileguardian
{
    protected final Integer hash = 0x00888fe8;
    
    public File xmlin;
    
    public Uifileguardian()
    {
        try
        {
            this.xmlin = new File("/home/oem/Desktop/UI/UI 3.xml");    
        }
        catch(Exception err)
        {
            System.err.println(err);
        }        
    }
}
