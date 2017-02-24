package apml.compilers;

import com.sun.codemodel.JCodeModel;
import java.util.ArrayList;

/**
 *
 * @author max rupplin
 */
public abstract class Bloqabstractoutputmanager 
{
    public ArrayList<JCodeModel> apmlmodels;  
    
    public ArrayList<JCodeModel> definitionmodels;
    
    public ArrayList<JCodeModel> dynamiclistenermodels;
    
    public ArrayList<JCodeModel> listenermodels;
    
    public ArrayList<JCodeModel> objectmodels;
    
    public ArrayList<JCodeModel> subscribermodels;
    
    public ArrayList<JCodeModel> systemmodels;    
}
