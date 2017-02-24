package apml.compilers;

import apml.modeling.Apmlmodelfile;
import java.util.ArrayList;

/**
 *
 * @author max rupplin
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
