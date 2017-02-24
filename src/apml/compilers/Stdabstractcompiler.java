package apml.compilers;

/**
 *
 * @author max rupplin
 */
public abstract class Stdabstractcompiler 
{        
    public abstract void setapmlfiles(Bloqabstractfileguardian fileguardian);
    
    public abstract void settempfiles(Bloqabstractapmlmanager apmlmanager);
    
    public abstract void setoutputfiles(Bloqabstractapmlmanager apmlmanager);
    
    public abstract void setsourcefiles(Bloqabstractoutputmanager astmanager);  
}
