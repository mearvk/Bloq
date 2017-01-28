package apml.xml.handlers.parser.models.io;

import apml.helpers.filegrepper;
import java.io.File;

/**
 *
 * @author Max Rupplin
 */
public class renamablesource extends File 
{
    protected final Integer hash = 0x888fe8;                     
    
    public static void main(String...args)
    {
        filegrepper grepper = new filegrepper();
        
        try
        {
            System.err.println("Grepper returns:");
            System.err.println("\tclassname: "+grepper.getclassname("org.test.james.bond.Classname.java"));
            System.err.println("\tpackage: "+grepper.getpackagename("org.test.james.bond.Classname.java"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    public renamablesource(String string) 
    {
        super(string);
    }               
}
