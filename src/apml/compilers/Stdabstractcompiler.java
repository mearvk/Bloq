package apml.compilers;

import apml.compilers.java.codemodel.Bloqapmlmanager;

import apml.compilers.java.codemodel.Bloqfileguardian;

import apml.compilers.java.codemodel.Bloqjcmmanager;

/**
 *
 * @author max rupplin
 */
public abstract class Stdabstractcompiler 
{        
    public abstract void setapmlfiles(Bloqfileguardian fileguardian);
    
    public abstract void settempfiles(Bloqapmlmanager apmlmanager);
    
    public abstract void setoutputfiles(Bloqapmlmanager apmlmanager);
    
    public abstract void setsourcefiles(Bloqjcmmanager astmanager);  
}
