package apml.compilers.java.codemodel;

import java.util.logging.Level;

/**
 *
 * @author Max Rupplin
 */
public class Apmldriver
{
    public static void main(String...args)
    {
        try
        {
            Bloqcompiler bloqcompiler = new Bloqcompiler();
            
            bloqcompiler.setapmlfiles(bloqcompiler.fileguardian);
            
            bloqcompiler.settempfiles(bloqcompiler.inputmanager);
            
            bloqcompiler.setoutputfiles(bloqcompiler.inputmanager);         
            
            bloqcompiler.setsourcefiles(bloqcompiler.outputmanager);
            
            bloqcompiler.setjarfile();
            
            System.gc();
        }
        catch(Exception e)
        {
            Bloqcompiler.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }        
    }
}
