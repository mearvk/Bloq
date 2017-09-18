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
    protected final Integer hash = 0x00888FE8;
    
    public File xmlin;
    
    public File outputdir;
    
    public Uifileguardian()
    {
        try
        {
            this.xmlin = new File("/Users/macuser/IdeaProjects/UI_0001/UserInterface.xml");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }        
        
        try
        {
            this.outputdir = new File("/Users/macuser/Desktop/UI");
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
}
