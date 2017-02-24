package apml.compilers.java.roaster;

import apml.compilers.java.codemodel.Bloqfileguardian;

/**
 *
 * @author max rupplin
 */
public class Roastercompiler 
{
    public Bloqapmlmanager apmlmanager = new Bloqapmlmanager(); 
    
    public Bloqroastermanager astmanager = new Bloqroastermanager();
    
    public Bloqfileguardian fileguardian = new Bloqfileguardian();    
    
    public static void main(String[] args)
    {
        try
        {
            Roastercompiler bloqcompiler = new Roastercompiler();
            
            bloqcompiler.setapmlfiles(bloqcompiler.fileguardian);
            
            bloqcompiler.settempfiles(bloqcompiler.apmlmanager);
            
            bloqcompiler.setastfiles(bloqcompiler.apmlmanager);         
            
            bloqcompiler.setsourcefiles(bloqcompiler.astmanager);
            
            bloqcompiler.setjarfile();
            
            System.gc();
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
    }
    
    public void setapmlfiles(Bloqfileguardian fileguardian)
    {
        
    }
    
    public void settempfiles(Bloqapmlmanager apmlmanager)
    {
        
    }
    
    public void setastfiles(Bloqapmlmanager apmlmanager)
    {
        
    }
    
    public void setsourcefiles(Bloqroastermanager astmanager)
    {
        
    }
    
    public void setjarfile()
    {
        
    }
}
