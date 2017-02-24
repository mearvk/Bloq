/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers;

/**
 *
 * @author oem
 */
public abstract class Stdabstractcompiler 
{        
    public abstract void setapmlfiles(Bloqabstractfileguardian fileguardian);
    
    public abstract void settempfiles(Bloqabstractapmlmanager apmlmanager);
    
    public abstract void setoutputfiles(Bloqabstractapmlmanager apmlmanager);
    
    public abstract void setsourcefiles(Bloqabstractoutputmanager astmanager);  
}
