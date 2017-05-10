package apml.ui.compilers.java;

import java.io.File;

/**
 * Handles the Bloq UI system files (input files, etc) for Bloq UI driving and output
 * 
 * @author Max Rupplin
 * @since 04.30.2017
 * @version Bloq 1.0
 */
public class Uifileguardian
{
    protected final Integer hash = 0x00888fe8;
    
    public File xmlin;
    
    public File outputdir;
    
    public Uifileguardian()
    {
        try
        {
            this.xmlin = new File("/home/oem/Desktop/UI/UI 3.xml");    
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }        
        
        try
        {
            this.outputdir = new File("/home/oem/Desktop/UI");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
}
