/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.compilers;

import apml.modeling.Apmlmodelfile;
import java.util.ArrayList;

/**
 *
 * @author oem
 */
public abstract class Bloqabstractapmlmanager 
{
    public ArrayList<Apmlmodelfile> apmlmodels;  
    
    public ArrayList<Apmlmodelfile> definitionmodels;
    
    public ArrayList<Apmlmodelfile> dynamiclistenermodels;
    
    public ArrayList<Apmlmodelfile> listenermodels;
    
    public ArrayList<Apmlmodelfile> objectmodels;
    
    public ArrayList<Apmlmodelfile> subscribermodels;
    
    public ArrayList<Apmlmodelfile> systemmodels;
}
