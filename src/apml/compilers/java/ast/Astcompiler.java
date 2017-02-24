package apml.compilers.java.ast;

import apml.compilers.java.codemodel.Bloqapmlmanager;
import apml.compilers.java.codemodel.Bloqfileguardian;

/**
 * 
 * @author max rupplin
 */
public class Astcompiler 
{
    public Bloqapmlmanager apmlmanager = new Bloqapmlmanager(); 
    
    public Bloqastmanager astmanager = new Bloqastmanager();
    
    public Bloqfileguardian fileguardian = new Bloqfileguardian();    
    
    public static void main(String[] args)
    {
        try
        {
            Astcompiler bloqcompiler = new Astcompiler();
            
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
    
    public void setsourcefiles(Bloqastmanager astmanager)
    {
        
    }
    
    public void setjarfile()
    {
        
    }
}
